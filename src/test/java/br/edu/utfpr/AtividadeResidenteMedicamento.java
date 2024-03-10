package br.edu.utfpr;

import br.edu.utfpr.atividadesresidente.AtividadeResidenteDTO;
import br.edu.utfpr.enums.SituacaoAtividade;
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
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AtividadeResidenteMedicamento {
    @TestHTTPResource("/atividademedicamento")
    URL apiURL;

    @TestHTTPResource("/atividademedicamento/pagesort/0/1/id/true")
    URL pageSortURL;

    @TestHTTPResource("/atividademedicamento/1")
    URL idURL;

    @TestHTTPResource("/atividademedicamento/321")
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
    @DisplayName("Deve atualizar atividade com sucesso.")
    public void updateAtividadeLudicaTest() throws SQLException {

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
        PreparedStatement stmt2 = c2.prepareStatement("INSERT INTO MEDICAMENTOESTOQUE (NOME, PRINCIPIOATIVO, QTDE) VALUES ('DIPIRONA', 'DIPIRONA 500MG', 20);");
        stmt2.execute();
        stmt2.close();
        c2.close();

        DriverManager.registerDriver(new org.h2.Driver());
        Connection c3 = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt3 = c3.prepareStatement("INSERT INTO MEDICAMENTOUSO (" +
                "\tINTERVALO,\n" +
                "\tQTDEVEZESAODIA,\n" +
                "\tDATAHORAINICIO,\n" +
                "\tQTDEDIASUSO,\n" +
                "\tQTDEMEDICAMENTO,\n" +
                "\tIDRESIDENTE,\n" +
                "\tIDMEDICAMENTO) \n" +
                "VALUES (" +
                "24," +
                "1," +
                "'" + LocalDateTime.now().withHour(13) + "',"+
                "1," +
                "1," +
                "1," +
                "1" +
                ");");
        stmt3.execute();
        stmt3.close();
        c3.close();

        DriverManager.registerDriver(new org.h2.Driver());
        Connection c4 = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt4 = c4.prepareStatement("INSERT INTO ATIVIDADEMEDICAMENTORESIDENTE (DESCRICAO, DATAHORA, SITUACAOATIVIDADE, IDMEDICAMENTO)" +
                " VALUES ('teste'," +
                "'" + LocalDateTime.now().withHour(13) + "',"+
                "'PENDENTE',1);");
        stmt4.execute();
        stmt4.close();
        c4.close();

        AtividadeResidenteDTO atividadeResidenteDTO = new AtividadeResidenteDTO();
        atividadeResidenteDTO.setSituacao(SituacaoAtividade.PENDENTE);
        atividadeResidenteDTO.setProfissional(1L);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(atividadeResidenteDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Order(2)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao atualizar atividade.")
    public void updateAtividadeLudicaValidationErrorTest(){

        AtividadeResidenteDTO atividadeResidenteDTO = new AtividadeResidenteDTO();
        atividadeResidenteDTO.setSituacao(null);
        atividadeResidenteDTO.setProfissional(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(atividadeResidenteDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(3)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
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

    @Order(4)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
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

    @Order(5)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
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

    @Order(6)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve buscar as atividades paginadas e ordenadas com sucesso.")
    public void pageSortAtividadeLudicaTest(){

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(pageSortURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(7)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar todas as atividades.")
    public void getAllAtividadeLudicaErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from ATIVIDADEMEDICAMENTORESIDENTE");
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

    @Order(8)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar as atividades paginadas e ordenadas.")
    public void pageSortAtividadeLudicaErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from ATIVIDADEMEDICAMENTORESIDENTE");
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
