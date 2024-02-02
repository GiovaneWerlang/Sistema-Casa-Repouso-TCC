package br.edu.utfpr;

import br.edu.utfpr.usuario.UsuarioDTO;
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
public class UsuarioResourceTest {

    @TestHTTPResource("/usuario")
    URL apiURL;

    @TestHTTPResource("/usuario/page/0/1")
    URL pageURL;

    @TestHTTPResource("/usuario/2")
    URL idURL;

    @TestHTTPResource("/usuario/321")
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
    @DisplayName("Deve criar usuário com sucesso.")
    public void createUsuarioTest() throws SQLException {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Teste");
        usuarioDTO.setSenha("123456");
        usuarioDTO.setProfissional(1L);

        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("INSERT INTO PROFISSIONAL (FUNCAO,\n" +
                "\tDATAADMISSAO,\n" +
                "\tSALARIO,\n" +
                "\tSITUACAO,\n" +
                "\tNOME,\n" +
                "\tIDADE,\n" +
                "\tCPF,\n" +
                "\tTELEFONE,\n" +
                "\tEMAIL) \n" +
                "VALUES ('FUNCIONARIO',\n" +
                "'2023-05-05',\n" +
                "0,\n" +
                "'ATIVO',\n" +
                "'TYMED',\n" +
                "0,\n" +
                "'00000000000',\n" +
                "'0000000000',\n" +
                "'tymed@gmail.com'\n" +
                ");");
        stmt.execute();
        stmt.close();
        c.close();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(usuarioDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 201, response.getStatusCode());
    }

    @Order(2)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao criar usuario.")
    public void createUsuarioValidationErrorTest(){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin(null);
        usuarioDTO.setSenha(null);
        usuarioDTO.setProfissional(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(usuarioDTO)
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
    @DisplayName("Deve falhar ao criar usuário com login já existente.")
    public void createUsuarioLoginJaExisteTest() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Teste");
        usuarioDTO.setSenha("123456");
        usuarioDTO.setProfissional(1L);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(usuarioDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 409, response.getStatusCode());
    }

    @Order(4)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve atualizar usuario com sucesso.")
    public void updateUsuarioTest(){

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Teste");
        usuarioDTO.setSenha("123456");
        usuarioDTO.setProfissional(1L);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(usuarioDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Order(5)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao atualizar usuario.")
    public void updateUsuarioValidationErrorTest(){

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin(null);
        usuarioDTO.setSenha(null);
        usuarioDTO.setProfissional(null);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(usuarioDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(6)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao atualizar usuário com login já existente.")
    public void updateUsuarioLoginJaExisteTest() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Teste");
        usuarioDTO.setSenha("123456");
        usuarioDTO.setProfissional(1L);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(usuarioDTO)
                .when()
                .put(apiURL+"/1")
                .then()
                .extract().response();

        assertEquals( 409, response.getStatusCode());
    }

    @Order(7)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve buscar usuario por id com sucesso.")
    public void getByIdUsuarioTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(idURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(8)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar usuario por id.")
    public void getByIdUsuarioValidationErrorTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(erroURL)
                .then()
                .extract().response();

        assertEquals( 404, response.getStatusCode());
    }

    @Order(9)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve buscar todos os usuarios com sucesso.")
    public void getAllUsuarioTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(apiURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(10)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve buscar os usuarios paginados com sucesso.")
    public void pageUsuarioTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(pageURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(11)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve deletar por id a usuario com sucesso.")
    public void deleteUsuarioTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(idURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(12)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao deletar por id a usuario.")
    public void deleteUsuarioErrorTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(erroURL)
                .then()
                .extract().response();

        assertEquals( 404, response.getStatusCode());
    }

    @Order(13)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar todos os usuarios.")
    public void getAllUsuarioErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from usuario");
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

    @Order(14)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao buscar todos os usuarios paginados.")
    public void pageUsuarioErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from usuario");
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
}
