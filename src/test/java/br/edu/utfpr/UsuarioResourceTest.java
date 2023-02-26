package br.edu.utfpr;

import br.edu.utfpr.usuario.UsuarioDTO;
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
public class UsuarioResourceTest {

    @TestHTTPResource("/usuario")
    URL apiURL;

    @TestHTTPResource("/usuario/1")
    URL idURL;

    @TestHTTPResource("/usuario/321")
    URL erroURL;

    @Test
    @Order(1)
    @DisplayName("Deve criar usuário com sucesso.")
    public void createUsuarioTest() throws SQLException {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Teste");
        usuarioDTO.setSenha("12345");
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
                "'000.000.000-00',\n" +
                "'(00)0000-0000',\n" +
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

    @Test
    @Order(2)
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
    @DisplayName("Deve atualizar usuario com sucesso.")
    public void updateUsuarioTest(){

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Teste");
        usuarioDTO.setSenha("12345");
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

    @Order(4)
    @Test
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

    @Order(5)
    @Test
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

    @Order(6)
    @Test
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

    @Order(7)
    @Test
    @DisplayName("Deve buscar todas as usuarios com sucesso.")
    public void getAllUsuarioTest(){


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

    @Order(9)
    @Test
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

    @Order(10)
    @Test
    @DisplayName("Deve falhar ao buscar todas as usuarios.")
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
}
