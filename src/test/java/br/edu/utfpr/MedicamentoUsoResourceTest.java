package br.edu.utfpr;

import br.edu.utfpr.medicamentouso.MedicamentoUsoDTO;
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
public class MedicamentoUsoResourceTest {

    @TestHTTPResource("/medicamentouso")
    URL apiURL;

    @TestHTTPResource("/medicamentouso/1")
    URL idURL;

    @TestHTTPResource("/medicamentouso/321")
    URL erroURL;

    @Test
    @Order(1)
    @DisplayName("Deve criar medicamento uso com sucesso.")
    public void createMedicamentoUsoTest() throws SQLException {
        MedicamentoUsoDTO medicamentousoDTO = new MedicamentoUsoDTO();
        medicamentousoDTO.setIntervalo(4);
        medicamentousoDTO.setQtdeVezesAoDia(3);
        medicamentousoDTO.setDataHoraInicio(LocalDateTime.parse("1980-04-09T08:20:45", DateTimeFormatter.ISO_DATE_TIME));
        medicamentousoDTO.setQtdeDiasUso(7);
        medicamentousoDTO.setQtdeMedicamento(1);
        medicamentousoDTO.setResidente(1L);
        medicamentousoDTO.setMedicamento(1L);

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
        PreparedStatement stmt2 = c2.prepareStatement("INSERT INTO MEDICAMENTOESTOQUE (NOME, PRINCIPIOATIVO, QTDE) VALUES ('DIPIRONA', 'DIPIRONA 500MG', 20);");
        stmt2.execute();
        stmt2.close();
        c2.close();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(medicamentousoDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 201, response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Deve falhar ao criar medicamento uso.")
    public void createMedicamentoUsoValidationErrorTest(){
        MedicamentoUsoDTO medicamentousoDTO = new MedicamentoUsoDTO();
        medicamentousoDTO.setIntervalo(null);
        medicamentousoDTO.setQtdeVezesAoDia(null);
        medicamentousoDTO.setDataHoraInicio(null);
        medicamentousoDTO.setQtdeDiasUso(null);
        medicamentousoDTO.setQtdeMedicamento(null);
        medicamentousoDTO.setResidente(null);
        medicamentousoDTO.setMedicamento(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(medicamentousoDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(3)
    @Test
    @DisplayName("Deve atualizar medicamento uso com sucesso.")
    public void updateMedicamentoUsoTest(){

        MedicamentoUsoDTO medicamentousoDTO = new MedicamentoUsoDTO();
        medicamentousoDTO.setIntervalo(4);
        medicamentousoDTO.setQtdeVezesAoDia(3);
        medicamentousoDTO.setDataHoraInicio(LocalDateTime.parse("1980-04-09T08:20:45", DateTimeFormatter.ISO_DATE_TIME));
        medicamentousoDTO.setQtdeDiasUso(7);
        medicamentousoDTO.setQtdeMedicamento(1);
        medicamentousoDTO.setResidente(1L);
        medicamentousoDTO.setMedicamento(1L);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(medicamentousoDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Order(4)
    @Test
    @DisplayName("Deve falhar ao atualizar medicamento uso.")
    public void updateMedicamentoUsoValidationErrorTest(){

        MedicamentoUsoDTO medicamentousoDTO = new MedicamentoUsoDTO();
        medicamentousoDTO.setIntervalo(null);
        medicamentousoDTO.setQtdeVezesAoDia(null);
        medicamentousoDTO.setDataHoraInicio(null);
        medicamentousoDTO.setQtdeDiasUso(null);
        medicamentousoDTO.setQtdeMedicamento(null);
        medicamentousoDTO.setResidente(null);
        medicamentousoDTO.setMedicamento(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(medicamentousoDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(5)
    @Test
    @DisplayName("Deve buscar medicamento uso por id com sucesso.")
    public void getByIdMedicamentoUsoTest(){

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
    @DisplayName("Deve falhar ao buscar medicamento uso por id.")
    public void getByIdMedicamentoUsoValidationErrorTest(){


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
    @DisplayName("Deve buscar todas os medicamento usos com sucesso.")
    public void getAllMedicamentoUsoTest(){


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
    @DisplayName("Deve deletar por id o medicamento uso com sucesso.")
    public void deleteMedicamentoUsoTest(){


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
    @DisplayName("Deve falhar ao deletar por id o medicamento uso.")
    public void deleteMedicamentoUsoErrorTest(){


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
    @DisplayName("Deve falhar ao buscar todas os medicamento usos.")
    public void getAllMedicamentoUsoErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from medicamentouso");
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
