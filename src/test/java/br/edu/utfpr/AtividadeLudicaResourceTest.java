package br.edu.utfpr;

import br.edu.utfpr.atividadeludica.AtividadeLudicaDTO;
import br.edu.utfpr.enums.Situacao;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;

import jakarta.inject.Inject;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AtividadeLudicaResourceTest {

    @TestHTTPResource("/atividadeludica")
    URL apiURL;

    @TestHTTPResource("/atividadeludica/pagesort/0/1/id/true")
    URL pageSortURL;

    @TestHTTPResource("/atividadeludica/1")
    URL idURL;

    @TestHTTPResource("/atividadeludica/321")
    URL erroURL;

    @Inject
    Flyway flyway;

    @BeforeAll
    void cleanUp(){
        flyway.clean();
        flyway.migrate();
    }

    @Order(1)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve criar atividade lúdica com sucesso.")
    void createAtividadeLudicaTest(){
        AtividadeLudicaDTO atividadeDTO = new AtividadeLudicaDTO();
        atividadeDTO.setNome("Infantil");
        atividadeDTO.setSituacao(Situacao.ATIVO);
        atividadeDTO.setDataHora(LocalDateTime.parse("1980-04-09T08:20:45", DateTimeFormatter.ISO_DATE_TIME));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(atividadeDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 201, response.getStatusCode());
    }

    @Order(2)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao criar atividade.")
    void createAtividadeLudicaValidationErrorTest(){
        AtividadeLudicaDTO atividadeDTO = new AtividadeLudicaDTO();
        atividadeDTO.setNome(null);
        atividadeDTO.setSituacao(null);
        atividadeDTO.setDataHora(null);


        Response response = given()
                .contentType(ContentType.JSON)
                .body(atividadeDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(3)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve atualizar atividade com sucesso.")
    void updateAtividadeLudicaTest(){

        AtividadeLudicaDTO atividadeDTO = new AtividadeLudicaDTO();
        atividadeDTO.setNome("Juvenil");
        atividadeDTO.setSituacao(Situacao.INATIVO);
        atividadeDTO.setDataHora(LocalDateTime.parse("1980-04-09T08:20:45", DateTimeFormatter.ISO_DATE_TIME));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(atividadeDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Order(4)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao atualizar atividade.")
    void updateAtividadeLudicaValidationErrorTest(){

        AtividadeLudicaDTO atividadeDTO = new AtividadeLudicaDTO();
        atividadeDTO.setNome(null);
        atividadeDTO.setSituacao(null);
        atividadeDTO.setDataHora(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(atividadeDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(5)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve buscar atividade por id com sucesso.")
    void getByIdAtividadeLudicaTest(){


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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar atividade por id.")
    void getByIdAtividadeLudicaValidationErrorTest(){


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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve buscar todas as atividades com sucesso.")
    void getAllAtividadeLudicaTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(apiURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(9)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve buscar as atividades paginadas e ordenadas com sucesso.")
    void pageSortAtividadeLudicaTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(pageSortURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(10)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve deletar por id a atividade com sucesso.")
    void deleteAtividadeLudicaTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(idURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(11)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao deletar por id a atividade.")
    void deleteAtividadeLudicaErrorTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(erroURL)
                .then()
                .extract().response();

        assertEquals( 404, response.getStatusCode());
    }

    @Order(12)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar todas as atividades.")
    void getAllAtividadeLudicaErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from atividadeludica");
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

    @Order(14)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar as atividades paginadas e ordenadas.")
    void pageSortAtividadeLudicaErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from atividadeludica");
        stmt.execute();
        stmt.close();
        c.close();

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(pageSortURL)
                .then()
                .extract().response();

        response.then().assertThat().statusCode(200)
                .body("lista", equalTo(Collections.emptyList()))
                .body("pages", equalTo(1))
                .body("total", equalTo(0));
    }
}
