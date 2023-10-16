package br.edu.utfpr;

import br.edu.utfpr.usuario.UsuarioDTO;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
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
public class AutenticacaoResourceTest {

    @TestHTTPResource("/autenticacao/login")
    URL loginURL;

    @Inject
    Flyway flyway;

    @BeforeAll
    public void cleanUp(){
        flyway.clean();
        flyway.migrate();
    }

    @Test
    @Order(1)
    @DisplayName("Deve logar usuário com sucesso.")
    public void loginUsuarioTest() throws SQLException {

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
        PreparedStatement stmt2 = c.prepareStatement("INSERT INTO USUARIO (LOGIN,\n" +
                "\tSENHA,\n" +
                "\tIDPROFISSIONAL) \n" +
                "VALUES ('Teste',\n" +
                "'$2a$10$tbsQYULD5FfW3XyhW/7cauWgK.q.i/FwMh7J8JzyjFp53Ss.wdRYC',\n" +
                "1\n" +
                ");");
        stmt2.execute();
        stmt2.close();
        c.close();

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Teste");
        usuarioDTO.setSenha("123456");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(usuarioDTO)
                .when()
                .post(loginURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Deve falhar logar usuário com senha incorreta.")
    public void loginUsuarioSenhaIncorretaTest() throws SQLException {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("Teste");
        usuarioDTO.setSenha("asdfghj");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(usuarioDTO)
                .when()
                .post(loginURL)
                .then()
                .extract().response();

        assertEquals( 401, response.getStatusCode());
    }

    @Test
    @Order(3)
    @DisplayName("Deve falhar logar usuário não encontrado.")
    public void loginUsuarioNaoEncontradoTest() throws SQLException {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setLogin("asdfjj");
        usuarioDTO.setSenha("asdfghj");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(usuarioDTO)
                .when()
                .post(loginURL)
                .then()
                .extract().response();

        assertEquals( 404, response.getStatusCode());
    }

}
