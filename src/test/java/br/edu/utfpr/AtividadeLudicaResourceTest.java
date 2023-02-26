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
import java.time.LocalDate;
import java.time.LocalTime;

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
    public void createEspecialidadeTest(){
        AtividadeLudicaDTO atividadeDTO = new AtividadeLudicaDTO();
        atividadeDTO.setNome("Infantil");
        atividadeDTO.setSituacao(Situacao.ATIVO);
        atividadeDTO.setData(LocalDate.now());
        atividadeDTO.setHora(LocalTime.now());


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
    public void createEspecialidadeValidationErrorTest(){
        AtividadeLudicaDTO atividadeDTO = new AtividadeLudicaDTO();
        atividadeDTO.setNome(null);
        atividadeDTO.setSituacao(null);
        atividadeDTO.setData(null);
        atividadeDTO.setHora(null);

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
    public void updateEspecialidadeTest(){

        AtividadeLudicaDTO atividadeDTO = new AtividadeLudicaDTO();
        atividadeDTO.setNome("Juvenil");
        atividadeDTO.setSituacao(Situacao.INATIVO);
        atividadeDTO.setData(LocalDate.now());
        atividadeDTO.setHora(LocalTime.now());

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
    public void updateEspecialidadeValidationErrorTest(){

        AtividadeLudicaDTO atividadeDTO = new AtividadeLudicaDTO();
        atividadeDTO.setNome(null);
        atividadeDTO.setSituacao(null);
        atividadeDTO.setData(null);
        atividadeDTO.setHora(null);

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
    @DisplayName("Deve falhar ao buscar atividade por id.")
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
    @DisplayName("Deve buscar todas as atividades com sucesso.")
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
    @DisplayName("Deve deletar por id a atividade com sucesso.")
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
    @DisplayName("Deve falhar ao deletar por id a atividade.")
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
    @DisplayName("Deve falhar ao buscar todas as atividades.")
    public void getAllEspecialidadeErrorTest() throws SQLException {
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
