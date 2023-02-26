package br.edu.utfpr;

import br.edu.utfpr.entradasaida.EntradaSaidaDTO;
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
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntradaSaidaResourceTest {

    @TestHTTPResource("/entradasaida")
    URL apiURL;

    @TestHTTPResource("/entradasaida/1")
    URL idURL;

    @TestHTTPResource("/entradasaida/321")
    URL erroURL;

    @Test
    @Order(1)
    @DisplayName("Deve criar usuário com sucesso.")
    public void createEntradaSaidaTest() throws SQLException {
        EntradaSaidaDTO entradasaidaDTO = new EntradaSaidaDTO();
        entradasaidaDTO.setDataHoraEntrada(OffsetDateTime.parse("1980-04-09T08:20:45+07:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        entradasaidaDTO.setDataHoraSaida(OffsetDateTime.parse("1980-04-09T08:20:45+07:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        entradasaidaDTO.setDescricao("Teste");
        entradasaidaDTO.setResidente(1L);

        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("INSERT INTO RESIDENTE (" +
                "\tDATAHORAINGRESSO,\n" +
                "\tDATAHORAPREVISAOSAIDA,\n" +
                "\tTIPOESTADIA,\n" +
                "\tSITUACAO,\n" +
                "\tNOME,\n" +
                "\tIDADE,\n" +
                "\tCPF,\n" +
                "\tTELEFONE,\n" +
                "\tEMAIL) \n" +
                "VALUES (" +
                "'2023-05-05',\n" +
                "'2023-05-05',\n" +
                "'PADRAO',\n" +
                "'ATIVO',\n" +
                "'teste',\n" +
                "0,\n" +
                "'000.000.000-00',\n" +
                "'(00)0000-0000',\n" +
                "'teste@gmail.com'\n" +
                ");");
        stmt.execute();
        stmt.close();
        c.close();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(entradasaidaDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 201, response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Deve falhar ao criar entradasaida.")
    public void createEntradaSaidaValidationErrorTest(){
        EntradaSaidaDTO entradasaidaDTO = new EntradaSaidaDTO();
        entradasaidaDTO.setDataHoraEntrada(null);
        entradasaidaDTO.setDataHoraSaida(null);
        entradasaidaDTO.setDescricao(null);
        entradasaidaDTO.setResidente(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(entradasaidaDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(3)
    @Test
    @DisplayName("Deve atualizar entradasaida com sucesso.")
    public void updateEntradaSaidaTest(){

        EntradaSaidaDTO entradasaidaDTO = new EntradaSaidaDTO();
        entradasaidaDTO.setDataHoraEntrada(OffsetDateTime.parse("1980-04-09T08:20:45+07:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        entradasaidaDTO.setDataHoraSaida(OffsetDateTime.parse("1980-04-09T08:20:45+07:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        entradasaidaDTO.setDescricao("Teste");
        entradasaidaDTO.setResidente(1L);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(entradasaidaDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Order(4)
    @Test
    @DisplayName("Deve falhar ao atualizar entradasaida.")
    public void updateEntradaSaidaValidationErrorTest(){

        EntradaSaidaDTO entradasaidaDTO = new EntradaSaidaDTO();
        entradasaidaDTO.setDataHoraEntrada(null);
        entradasaidaDTO.setDataHoraSaida(null);
        entradasaidaDTO.setDescricao(null);
        entradasaidaDTO.setResidente(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(entradasaidaDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(5)
    @Test
    @DisplayName("Deve buscar entradasaida por id com sucesso.")
    public void getByIdEntradaSaidaTest(){


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
    @DisplayName("Deve falhar ao buscar entradasaida por id.")
    public void getByIdEntradaSaidaValidationErrorTest(){


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
    @DisplayName("Deve buscar todas as entradasaidas com sucesso.")
    public void getAllEntradaSaidaTest(){


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
    @DisplayName("Deve deletar por id a entradasaida com sucesso.")
    public void deleteEntradaSaidaTest(){


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
    @DisplayName("Deve falhar ao deletar por id a entradasaida.")
    public void deleteEntradaSaidaErrorTest(){


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
    @DisplayName("Deve falhar ao buscar todas as entradasaidas.")
    public void getAllEntradaSaidaErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from entradasaida");
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
