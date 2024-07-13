package br.edu.utfpr;

import br.edu.utfpr.endereco.EnderecoDTO;
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

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EnderecoResourceTest {

    @TestHTTPResource("/endereco")
    URL apiURL;

    @TestHTTPResource("/endereco/1")
    URL idURL;

    @TestHTTPResource("/endereco/321")
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
    @DisplayName("Deve criar endereco com sucesso.")
    void createEnderecoTest(){
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro("Teste");
        enderecoDTO.setBairro("Teste");
        enderecoDTO.setNumero("123");
        enderecoDTO.setCep("12345678");
        enderecoDTO.setMunicipio("Teste");
        enderecoDTO.setEstado("Teste");
        enderecoDTO.setPais("Teste");


        Response response = given()
                .contentType(ContentType.JSON)
                .body(enderecoDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 201, response.getStatusCode());
    }

    @Order(2)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao criar endereco.")
    void createEnderecoValidationErrorTest(){
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro(null);
        enderecoDTO.setBairro(null);
        enderecoDTO.setNumero(null);
        enderecoDTO.setCep(null);
        enderecoDTO.setMunicipio(null);
        enderecoDTO.setEstado(null);
        enderecoDTO.setPais(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(enderecoDTO)
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
    @DisplayName("Deve atualizar endereco com sucesso.")
    void updateEnderecoTest(){

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro("Teste");
        enderecoDTO.setBairro("Teste");
        enderecoDTO.setNumero("123");
        enderecoDTO.setCep("12345678");
        enderecoDTO.setMunicipio("Teste");
        enderecoDTO.setEstado("Teste");
        enderecoDTO.setPais("Teste");


        Response response = given()
                .contentType(ContentType.JSON)
                .body(enderecoDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Order(4)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao atualizar endereco.")
    void updateEnderecoValidationErrorTest(){

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setLogradouro(null);
        enderecoDTO.setBairro(null);
        enderecoDTO.setNumero(null);
        enderecoDTO.setCep(null);
        enderecoDTO.setMunicipio(null);
        enderecoDTO.setEstado(null);
        enderecoDTO.setPais(null);


        Response response = given()
                .contentType(ContentType.JSON)
                .body(enderecoDTO)
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
    @DisplayName("Deve buscar endereco por id com sucesso.")
    void getByIdEnderecoTest(){


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
    @DisplayName("Deve falhar ao buscar endereco por id.")
    void getByIdEnderecoValidationErrorTest(){


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
    @DisplayName("Deve buscar todas as enderecos com sucesso.")
    void getAllEnderecoTest(){


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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve deletar por id a endereco com sucesso.")
    void deleteEnderecoTest(){


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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao deletar por id a endereco.")
    void deleteEnderecoErrorTest(){


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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar todas as enderecos.")
    void getAllEnderecoErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from endereco");
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
