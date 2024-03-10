package br.edu.utfpr;

import br.edu.utfpr.exame.ExameDTO;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExameResourceTest {

    @TestHTTPResource("/exame")
    URL apiURL;

    @TestHTTPResource("/exame/page/0/1")
    URL pageURL;

    @TestHTTPResource("/exame/pagesort/0/1/id/true")
    URL pageSortURL;

    @TestHTTPResource("/exame/1")
    URL idURL;

    @TestHTTPResource("/exame/321")
    URL erroURL;

    @Inject
    Flyway flyway;

    @BeforeAll
    public void cleanUp(){
        flyway.clean();
        flyway.migrate();
    }

    @Order(1)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve criar exame com sucesso.")
    public void createExameTest() throws SQLException {
        ExameDTO exameDTO = new ExameDTO();
        exameDTO.setDescricao("Teste");
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
                "'00000000000',\n" +
                "'0000000000',\n" +
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
                "'00000000000',\n" +
                "'0000000000',\n" +
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

    @Order(2)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao criar exame.")
    public void createExameValidationErrorTest(){
        ExameDTO exameDTO = new ExameDTO();
        exameDTO.setDescricao(null);
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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve atualizar exame com sucesso.")
    public void updateExameTest() throws SQLException {

        ExameDTO exameDTO = new ExameDTO();
        exameDTO.setDescricao("Teste");
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
                "'00000000000',\n" +
                "'0000000000',\n" +
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
                "'00000000000',\n" +
                "'0000000000',\n" +
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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao atualizar exame.")
    public void updateExameValidationErrorTest(){

        ExameDTO exameDTO = new ExameDTO();
        exameDTO.setDescricao(null);
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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve buscar todos os exames com sucesso.")
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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve buscar os exames paginados com sucesso.")
    public void pageExameTest(){

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(pageURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(9)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve buscar os exames paginados e ordenados com sucesso.")
    public void pageSortExameTest(){

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

    @Order(11)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
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

    @Order(12)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar todos os exames.")
    public void getAllExameErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());

        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from atividadeexameresidente");
        stmt.execute();
        stmt.close();
        c.close();

        Connection c2 = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt2 = c2.prepareStatement("delete from exame");
        stmt2.execute();
        stmt2.close();
        c2.close();

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(apiURL)
                .then()
                .extract().response();

        assertEquals( 404, response.getStatusCode());
    }

    @Order(13)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar os exames paginados.")
    public void pageExameErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());

        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from atividadeexameresidente");
        stmt.execute();
        stmt.close();
        c.close();

        Connection c2 = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt2 = c2.prepareStatement("delete from exame");
        stmt2.execute();
        stmt2.close();
        c2.close();

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(pageURL)
                .then()
                .extract().response();

        response.then().assertThat().statusCode(200)
                .body("lista", equalTo(Collections.emptyList()))
                .body("pages", equalTo(1))
                .body("total", equalTo(0));
    }

    @Order(14)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar os exames paginados e ordenados.")
    public void pageSortExameErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());

        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from atividadeexameresidente");
        stmt.execute();
        stmt.close();
        c.close();

        Connection c2 = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt2 = c2.prepareStatement("delete from exame");
        stmt2.execute();
        stmt2.close();
        c2.close();

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
