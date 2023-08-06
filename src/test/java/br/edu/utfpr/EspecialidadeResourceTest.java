package br.edu.utfpr;

import br.edu.utfpr.especialidade.EspecialidadeDTO;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;

import javax.inject.Inject;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EspecialidadeResourceTest {

    @TestHTTPResource("/especialidade")
    URL apiURL;

    @TestHTTPResource("/especialidade/1")
    URL idURL;

    @TestHTTPResource("/especialidade/321")
    URL erroURL;

    @Inject
    Flyway flyway;

    @BeforeAll
    public void cleanUp(){
        flyway.clean();
        flyway.migrate();
    }

    @Test
    @Order(1)
    @DisplayName("Deve criar especialidade com sucesso.")
    public void createEspecialidadeTest(){
        EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO();
        especialidadeDTO.setNome("Infantil");


        Response response = given()
                .contentType(ContentType.JSON)
                .body(especialidadeDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 201, response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Deve falhar ao criar especialidade.")
    public void createEspecialidadeValidationErrorTest(){
        EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO();
        especialidadeDTO.setNome(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(especialidadeDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(3)
    @Test
    @DisplayName("Deve atualizar especialidade com sucesso.")
    public void updateEspecialidadeTest(){

        EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO();
        especialidadeDTO.setNome("Juvenil");


        Response response = given()
                .contentType(ContentType.JSON)
                .body(especialidadeDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Order(4)
    @Test
    @DisplayName("Deve falhar ao atualizar especialidade.")
    public void updateEspecialidadeValidationErrorTest(){

        EspecialidadeDTO especialidadeDTO = new EspecialidadeDTO();
        especialidadeDTO.setNome(null);


        Response response = given()
                .contentType(ContentType.JSON)
                .body(especialidadeDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(5)
    @Test
    @DisplayName("Deve buscar especialidade por id com sucesso.")
    public void getByIdEspecialidadeTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(idURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(6)
    @Test
    @DisplayName("Deve falhar ao buscar especialidade por id.")
    public void getByIdEspecialidadeValidationErrorTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(erroURL)
                .then()
                .extract().response();

        assertEquals( 404, response.getStatusCode());
    }

    @Order(7)
    @Test
    @TestSecurity(user = "testUser", roles = {"FUNCIONARIO"})
    @DisplayName("Deve buscar todas as especialidades com sucesso.")
    public void getAllEspecialidadeTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(apiURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(8)
    @Test
    @DisplayName("Deve deletar por id a especialidade com sucesso.")
    public void deleteEspecialidadeTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(idURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(9)
    @Test
    @DisplayName("Deve falhar ao deletar por id a especialidade.")
    public void deleteEspecialidadeErrorTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(erroURL)
                .then()
                .extract().response();

        assertEquals( 404, response.getStatusCode());
    }

    @Order(10)
    @Test
    @TestSecurity(user = "testUser", roles = {"FUNCIONARIO"})
    @DisplayName("Deve falhar ao buscar todas as especialidades.")
    public void getAllEspecialidadeErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from especialidade");
        stmt.execute();
        stmt.close();
        c.close();

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(apiURL)
                .then()
                .extract().response();

        assertEquals( 404, response.getStatusCode());
    }
}
