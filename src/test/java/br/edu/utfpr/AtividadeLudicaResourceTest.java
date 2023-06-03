package br.edu.utfpr;

import br.edu.utfpr.atividadeludica.AtividadeLudicaDTO;
import br.edu.utfpr.enums.Situacao;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AtividadeLudicaResourceTest {

    @TestHTTPResource("/atividadeludica")
    URL apiURL;

    @TestHTTPResource("/atividadeludica/1")
    URL idURL;

    @TestHTTPResource("/atividadeludica/321")
    URL erroURL;

    @Test
    @Order(1)
    @DisplayName("Deve criar atividade lúdica com sucesso.")
    public void createAtividadeLudicaTest(){
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

    @Test
    @Order(2)
    @DisplayName("Deve falhar ao criar atividade.")
    public void createAtividadeLudicaValidationErrorTest(){
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
    @DisplayName("Deve atualizar atividade com sucesso.")
    public void updateAtividadeLudicaTest(){

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
    @DisplayName("Deve falhar ao atualizar atividade.")
    public void updateAtividadeLudicaValidationErrorTest(){

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
    @DisplayName("Deve buscar atividade por id com sucesso.")
    public void getByIdAtividadeLudicaTest(){


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
    @DisplayName("Deve falhar ao buscar atividade por id.")
    public void getByIdAtividadeLudicaValidationErrorTest(){


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
    @DisplayName("Deve buscar todas as atividades com sucesso.")
    public void getAllAtividadeLudicaTest(){


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
    @DisplayName("Deve deletar por id a atividade com sucesso.")
    public void deleteAtividadeLudicaTest(){


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
    @DisplayName("Deve falhar ao deletar por id a atividade.")
    public void deleteAtividadeLudicaErrorTest(){


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
    @DisplayName("Deve falhar ao buscar todas as atividades.")
    public void getAllAtividadeLudicaErrorTest() throws SQLException {
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
}
