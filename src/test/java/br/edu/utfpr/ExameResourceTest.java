package br.edu.utfpr;

import br.edu.utfpr.exame.ExameDTO;
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
public class ExameResourceTest {

    @TestHTTPResource("/exame")
    URL apiURL;

    @TestHTTPResource("/exame/1")
    URL idURL;

    @TestHTTPResource("/exame/321")
    URL erroURL;

    @Test
    @Order(1)
    @DisplayName("Deve criar exame com sucesso.")
    public void createExameTest() throws SQLException {
        ExameDTO exameDTO = new ExameDTO();
        exameDTO.setNome("Teste");
        exameDTO.setDataHora(LocalDateTime.parse("1980-04-09T08:20:45", DateTimeFormatter.ISO_DATE_TIME));
        exameDTO.setLocal("local");
        exameDTO.setLaudo("prescrição");
        exameDTO.setEspecialidade(1L);
        exameDTO.setProfissional(1L);
        exameDTO.setResidente(1L);

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

        DriverManager.registerDriver(new org.h2.Driver());
        Connection c2 = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt2 = c2.prepareStatement("INSERT INTO ESPECIALIDADE (" +
                "\tNOME)\n" +
                "VALUES (" +
                "'teste'\n" +
                ");");
        stmt2.execute();
        stmt2.close();
        c2.close();

        DriverManager.registerDriver(new org.h2.Driver());
        Connection c3 = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt3 = c3.prepareStatement("INSERT INTO PROFISSIONAL (" +
                "\tFUNCAO,\n" +
                "\tDATAADMISSAO,\n" +
                "\tSALARIO,\n" +
                "\tSITUACAO,\n" +
                "\tNOME,\n" +
                "\tIDADE,\n" +
                "\tCPF,\n" +
                "\tTELEFONE,\n" +
                "\tEMAIL) \n" +
                "VALUES (" +
                "'FUNCIONARIO',\n" +
                "'2023-05-05',\n" +
                "0,\n" +
                "'ATIVO',\n" +
                "'teste',\n" +
                "1,\n" +
                "'000.000.000-00',\n" +
                "'(00)0000-0000',\n" +
                "'teste@gmail.com'\n" +
                ");");
        stmt3.execute();
        stmt3.close();

        c3.close();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(exameDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 201, response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Deve falhar ao criar exame.")
    public void createExameValidationErrorTest(){
        ExameDTO exameDTO = new ExameDTO();
        exameDTO.setNome(null);
        exameDTO.setDataHora(null);
        exameDTO.setLocal(null);
        exameDTO.setLaudo(null);
        exameDTO.setEspecialidade(null);
        exameDTO.setProfissional(null);
        exameDTO.setResidente(null);



        Response response = given()
                .contentType(ContentType.JSON)
                .body(exameDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(3)
    @Test
    @DisplayName("Deve atualizar exame com sucesso.")
    public void updateExameTest() throws SQLException {

        ExameDTO exameDTO = new ExameDTO();
        exameDTO.setNome("Teste");
        exameDTO.setDataHora(LocalDateTime.parse("1980-04-09T08:20:45", DateTimeFormatter.ISO_DATE_TIME));
        exameDTO.setLocal("local");
        exameDTO.setLaudo("prescrição");
        exameDTO.setEspecialidade(1L);
        exameDTO.setProfissional(1L);
        exameDTO.setResidente(1L);

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

        DriverManager.registerDriver(new org.h2.Driver());
        Connection c2 = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt2 = c2.prepareStatement("INSERT INTO ESPECIALIDADE (" +
                "\tNOME)\n" +
                "VALUES (" +
                "'teste'\n" +
                ");");
        stmt2.execute();
        stmt2.close();
        c2.close();

        DriverManager.registerDriver(new org.h2.Driver());
        Connection c3 = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt3 = c3.prepareStatement("INSERT INTO PROFISSIONAL (" +
                "\tFUNCAO,\n" +
                "\tDATAADMISSAO,\n" +
                "\tSALARIO,\n" +
                "\tSITUACAO,\n" +
                "\tNOME,\n" +
                "\tIDADE,\n" +
                "\tCPF,\n" +
                "\tTELEFONE,\n" +
                "\tEMAIL) \n" +
                "VALUES (" +
                "'FUNCIONARIO',\n" +
                "'2023-05-05',\n" +
                "0,\n" +
                "'ATIVO',\n" +
                "'teste',\n" +
                "1,\n" +
                "'000.000.000-00',\n" +
                "'(00)0000-0000',\n" +
                "'teste@gmail.com'\n" +
                ");");
        stmt3.execute();
        stmt3.close();

        c3.close();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(exameDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Order(4)
    @Test
    @DisplayName("Deve falhar ao atualizar exame.")
    public void updateExameValidationErrorTest(){

        ExameDTO exameDTO = new ExameDTO();
        exameDTO.setNome(null);
        exameDTO.setDataHora(null);
        exameDTO.setLocal(null);
        exameDTO.setLaudo(null);
        exameDTO.setEspecialidade(null);
        exameDTO.setProfissional(null);
        exameDTO.setResidente(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(exameDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(5)
    @Test
    @DisplayName("Deve buscar exame por id com sucesso.")
    public void getByIdExameTest(){


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
    @DisplayName("Deve falhar ao buscar exame por id.")
    public void getByIdExameValidationErrorTest(){


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
    @DisplayName("Deve buscar todas as exames com sucesso.")
    public void getAllExameTest(){


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
    @DisplayName("Deve deletar por id a exame com sucesso.")
    public void deleteExameTest(){


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
    @DisplayName("Deve falhar ao deletar por id a exame.")
    public void deleteExameErrorTest(){


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
    @DisplayName("Deve falhar ao buscar todos os exames.")
    public void getAllExameErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from exame");
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
