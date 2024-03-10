package br.edu.utfpr;

import br.edu.utfpr.medicamentoestoque.MedicamentoEstoqueDTO;
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
public class MedicamentoEstoqueResourceTest {

    @TestHTTPResource("/medicamentoestoque")
    URL apiURL;

    @TestHTTPResource("/medicamentoestoque/page/0/1")
    URL pageURL;

    @TestHTTPResource("/medicamentoestoque/pagesort/0/1/id/true")
    URL pageSortURL;

    @TestHTTPResource("/medicamentoestoque/1")
    URL idURL;

    @TestHTTPResource("/medicamentoestoque/321")
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
    @DisplayName("Deve criar medicamentoestoque com sucesso.")
    public void createMedicamentoEstoqueTest(){
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

    @Order(2)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao criar medicamentoestoque.")
    public void createMedicamentoEstoqueValidationErrorTest(){
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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve atualizar medicamentoestoque com sucesso.")
    public void updateMedicamentoEstoqueTest(){

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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao atualizar medicamentoestoque.")
    public void updateMedicamentoEstoqueValidationErrorTest(){

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
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve buscar medicamentoestoque por id com sucesso.")
    public void getByIdMedicamentoEstoqueTest(){


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
    @DisplayName("Deve falhar ao buscar medicamentoestoque por id.")
    public void getByIdMedicamentoEstoqueValidationErrorTest(){


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
    @DisplayName("Deve buscar todos os medicamentoestoques com sucesso.")
    public void getAllMedicamentoEstoqueTest(){


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
    @DisplayName("Deve buscar os medicamentoestoques paginados com sucesso.")
    public void pageMedicamentoEstoqueTest(){


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
    @DisplayName("Deve buscar os medicamentoestoques paginados e ordenados com sucesso.")
    public void pageSortMedicamentoEstoqueTest(){


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
    @DisplayName("Deve deletar por id o medicamentoestoque com sucesso.")
    public void deleteMedicamentoEstoqueTest(){


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
    @DisplayName("Deve falhar ao deletar por id o medicamentoestoque.")
    public void deleteMedicamentoEstoqueErrorTest(){


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
    @DisplayName("Deve falhar ao buscar todos os medicamentoestoques.")
    public void getAllMedicamentoEstoqueErrorTest() throws SQLException {
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

    @Order(13)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar os medicamentoestoques paginados.")
    public void pageMedicamentoEstoqueErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from medicamentoestoque");
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
    @DisplayName("Deve falhar ao buscar os medicamentoestoques paginados e ordenados.")
    public void pageSortMedicamentoEstoqueErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from medicamentoestoque");
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
