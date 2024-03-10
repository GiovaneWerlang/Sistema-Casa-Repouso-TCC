package br.edu.utfpr;

import br.edu.utfpr.enums.TipoMovimentacao;
import br.edu.utfpr.movimentacaoestoque.MovimentacaoEstoqueDTO;
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
public class MovimentacaoEstoqueResourceTest {

    @TestHTTPResource("/movimentacaoestoque")
    URL apiURL;

    @TestHTTPResource("/movimentacaoestoque/page/0/1")
    URL pageURL;

    @TestHTTPResource("/movimentacaoestoque/pagesort/0/1/id/true")
    URL pageSortURL;

    @TestHTTPResource("/movimentacaoestoque/1")
    URL idURL;

    @TestHTTPResource("/movimentacaoestoque/321")
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
    @DisplayName("Deve criar movimentação de estoque com sucesso.")
    public void createMovimentacaoEstoqueTest() throws SQLException {

        MovimentacaoEstoqueDTO movimentacaoestoqueDTO = new MovimentacaoEstoqueDTO();
        movimentacaoestoqueDTO.setQtde(1);
        movimentacaoestoqueDTO.setTipo(TipoMovimentacao.ENTRADA);
        movimentacaoestoqueDTO.setMedicamento(1L);

        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("INSERT INTO MEDICAMENTOESTOQUE (NOME, PRINCIPIOATIVO, QTDE) VALUES ('DIPIRONA', 'DIPIRONA 500MG', 20);");
        stmt.execute();
        stmt.close();
        c.close();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(movimentacaoestoqueDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 201, response.getStatusCode());
    }

    @Order(2)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao criar movimentação de estoque.")
    public void createMovimentacaoEstoqueValidationErrorTest(){

        MovimentacaoEstoqueDTO movimentacaoestoqueDTO = new MovimentacaoEstoqueDTO();
        movimentacaoestoqueDTO.setTipo(null);
        movimentacaoestoqueDTO.setMedicamento(null);
        movimentacaoestoqueDTO.setQtde(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(movimentacaoestoqueDTO)
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
    @DisplayName("Deve atualizar movimentação de estoque com sucesso.")
    public void updateMovimentacaoEstoqueTest(){

        MovimentacaoEstoqueDTO movimentacaoestoqueDTO = new MovimentacaoEstoqueDTO();
        movimentacaoestoqueDTO.setQtde(5);
        movimentacaoestoqueDTO.setTipo(TipoMovimentacao.SAIDA);
        movimentacaoestoqueDTO.setMedicamento(1L);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(movimentacaoestoqueDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Order(4)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao atualizar movimentação de estoque.")
    public void updateMovimentacaoEstoqueValidationErrorTest(){

        MovimentacaoEstoqueDTO movimentacaoestoqueDTO = new MovimentacaoEstoqueDTO();
        movimentacaoestoqueDTO.setQtde(null);
        movimentacaoestoqueDTO.setTipo(null);
        movimentacaoestoqueDTO.setMedicamento(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(movimentacaoestoqueDTO)
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
    @DisplayName("Deve buscar movimentação de estoque por id com sucesso.")
    public void getByIdMovimentacaoEstoqueTest(){

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
    @DisplayName("Deve falhar ao buscar movimentação de estoque por id.")
    public void getByIdMovimentacaoEstoqueValidationErrorTest(){

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
    @DisplayName("Deve buscar todas as movimentações de estoque com sucesso.")
    public void getAllMovimentacaoEstoqueTest(){

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
    @DisplayName("Deve buscar as movimentações de estoque paginadas com sucesso.")
    public void pageMovimentacaoEstoqueTest(){

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
    @DisplayName("Deve buscar as movimentações de estoque paginadas e ordenadas com sucesso.")
    public void pageSortMovimentacaoEstoqueTest(){

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
    @DisplayName("Deve deletar por id a movimentação de estoque com sucesso.")
    public void deleteMovimentacaoEstoqueTest(){

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
    @DisplayName("Deve falhar ao deletar por id a movimentação de estoque.")
    public void deleteMovimentacaoEstoqueErrorTest(){


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
    @DisplayName("Deve falhar ao buscar todas os movimentações de estoque.")
    public void getAllMovimentacaoEstoqueErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from movimentacaoestoque");
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

    @Order(13)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar as movimentações de estoque paginadas.")
    public void pageMovimentacaoEstoqueErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from movimentacaoestoque");
        stmt.execute();
        stmt.close();
        c.close();

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(pageURL)
                .then()
                .extract().response();

        assertEquals( 404, response.getStatusCode());
    }

    @Order(14)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar as movimentações de estoque paginadas e ordenadas.")
    public void pageSortMovimentacaoEstoqueErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from movimentacaoestoque");
        stmt.execute();
        stmt.close();
        c.close();

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(pageSortURL)
                .then()
                .extract().response();

        assertEquals( 404, response.getStatusCode());
    }
}
