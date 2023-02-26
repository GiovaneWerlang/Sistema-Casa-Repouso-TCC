package br.edu.utfpr;

import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueDTO;
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

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicamentoEstoqueResourceTest {

    @TestHTTPResource("/medicamentoestoque")
    URL apiURL;

    @TestHTTPResource("/medicamentoestoque/1")
    URL idURL;

    @TestHTTPResource("/medicamentoestoque/321")
    URL erroURL;

    @Test
    @Order(1)
    @DisplayName("Deve criar medicamentoestoque com sucesso.")
    public void createEnderecoTest(){
        MedicamentoEstoqueDTO medicamentoestoqueDTO = new MedicamentoEstoqueDTO();
        medicamentoestoqueDTO.setNome("Teste");
        medicamentoestoqueDTO.setPrincipioAtivo("Teste");
        medicamentoestoqueDTO.setQtde(123);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(medicamentoestoqueDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 201, response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Deve falhar ao criar medicamentoestoque.")
    public void createEnderecoValidationErrorTest(){
        MedicamentoEstoqueDTO medicamentoestoqueDTO = new MedicamentoEstoqueDTO();
        medicamentoestoqueDTO.setNome(null);
        medicamentoestoqueDTO.setPrincipioAtivo(null);
        medicamentoestoqueDTO.setQtde(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(medicamentoestoqueDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(3)
    @Test
    @DisplayName("Deve atualizar medicamentoestoque com sucesso.")
    public void updateEnderecoTest(){

        MedicamentoEstoqueDTO medicamentoestoqueDTO = new MedicamentoEstoqueDTO();
        medicamentoestoqueDTO.setNome("Teste");
        medicamentoestoqueDTO.setPrincipioAtivo("Teste");
        medicamentoestoqueDTO.setQtde(123);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(medicamentoestoqueDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Order(4)
    @Test
    @DisplayName("Deve falhar ao atualizar medicamentoestoque.")
    public void updateEnderecoValidationErrorTest(){

        MedicamentoEstoqueDTO medicamentoestoqueDTO = new MedicamentoEstoqueDTO();
        medicamentoestoqueDTO.setNome(null);
        medicamentoestoqueDTO.setPrincipioAtivo(null);
        medicamentoestoqueDTO.setQtde(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(medicamentoestoqueDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(5)
    @Test
    @DisplayName("Deve buscar medicamentoestoque por id com sucesso.")
    public void getByIdEnderecoTest(){


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
    @DisplayName("Deve falhar ao buscar medicamentoestoque por id.")
    public void getByIdEnderecoValidationErrorTest(){


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
    @DisplayName("Deve buscar todas os medicamentoestoques com sucesso.")
    public void getAllEnderecoTest(){


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
    @DisplayName("Deve deletar por id o medicamentoestoque com sucesso.")
    public void deleteEnderecoTest(){


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
    @DisplayName("Deve falhar ao deletar por id o medicamentoestoque.")
    public void deleteEnderecoErrorTest(){


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
    @DisplayName("Deve falhar ao buscar todas os medicamentoestoques.")
    public void getAllEnderecoErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from medicamentoestoque");
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
