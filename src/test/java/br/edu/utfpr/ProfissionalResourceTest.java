package br.edu.utfpr;

import br.edu.utfpr.endereco.EnderecoModel;
import br.edu.utfpr.enums.Funcao;
import br.edu.utfpr.enums.Situacao;
import br.edu.utfpr.profissional.ProfissionalDTO;
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
import java.time.LocalDate;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfissionalResourceTest {

    @TestHTTPResource("/profissional")
    URL apiURL;

    @TestHTTPResource("/profissional/pagesort/0/1/id/true")
    URL pageSortURL;

    @TestHTTPResource("/profissional/2")
    URL idURL;

    @TestHTTPResource("/profissional/321")
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
    @DisplayName("Deve criar profissional com sucesso.")
    public void createProfissionalTest() throws SQLException {
        ProfissionalDTO profissionalDTO = new ProfissionalDTO();
        profissionalDTO.setNome("Teste");
        profissionalDTO.setIdade(45);
        profissionalDTO.setCpf("97610046000");
        profissionalDTO.setTelefone("12233456278");
        profissionalDTO.setEmail("teste@teste.com");
        profissionalDTO.setDataAdmissao(LocalDate.now());
        profissionalDTO.setSalario(123F);
        profissionalDTO.setSituacao(Situacao.ATIVO);
        profissionalDTO.setFuncao(Funcao.CUIDADOR);

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

        profissionalDTO.setEspecialidade(1L);


        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro("Teste");
        enderecoModel.setBairro("Teste");
        enderecoModel.setNumero("123");
        enderecoModel.setCep("12345678");
        enderecoModel.setMunicipio("Teste");
        enderecoModel.setEstado("Teste");
        enderecoModel.setPais("Teste");
        profissionalDTO.setEndereco(enderecoModel);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(profissionalDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 201, response.getStatusCode());
    }

    @Order(2)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao criar profissional.")
    public void createProfissionalValidationErrorTest(){
        ProfissionalDTO profissionalDTO = new ProfissionalDTO();
        profissionalDTO.setNome(null);
        profissionalDTO.setIdade(-1);
        profissionalDTO.setCpf(null);
        profissionalDTO.setTelefone(null);
        profissionalDTO.setEmail(null);
        profissionalDTO.setSituacao(null);
        profissionalDTO.setDataAdmissao(null);
        profissionalDTO.setSalario(null);
        profissionalDTO.setSituacao(null);
        profissionalDTO.setFuncao(null);

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro(null);
        enderecoModel.setBairro(null);
        enderecoModel.setNumero(null);
        enderecoModel.setCep(null);
        enderecoModel.setMunicipio(null);
        enderecoModel.setEstado(null);
        enderecoModel.setPais(null);
        profissionalDTO.setEndereco(enderecoModel);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(profissionalDTO)
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
    @DisplayName("Deve atualizar profissional com sucesso.")
    public void updateProfissionalTest(){

        ProfissionalDTO profissionalDTO = new ProfissionalDTO();
        profissionalDTO.setNome("Teste");
        profissionalDTO.setIdade(45);
        profissionalDTO.setCpf("97610046000");
        profissionalDTO.setTelefone("12233456278");
        profissionalDTO.setEmail("teste@teste.com");
        profissionalDTO.setSituacao(Situacao.ATIVO);
        profissionalDTO.setDataAdmissao(LocalDate.now());
        profissionalDTO.setSalario(123F);
        profissionalDTO.setSituacao(Situacao.ATIVO);
        profissionalDTO.setFuncao(Funcao.CUIDADOR);
        profissionalDTO.setEspecialidade(1L);

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro("Teste");
        enderecoModel.setBairro("Teste");
        enderecoModel.setNumero("123");
        enderecoModel.setCep("12345678");
        enderecoModel.setMunicipio("Teste");
        enderecoModel.setEstado("Teste");
        enderecoModel.setPais("Teste");
        profissionalDTO.setEndereco(enderecoModel);


        Response response = given()
                .contentType(ContentType.JSON)
                .body(profissionalDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Order(4)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve falhar ao atualizar profissional.")
    public void updateProfissionalValidationErrorTest(){

        ProfissionalDTO profissionalDTO = new ProfissionalDTO();
        profissionalDTO.setNome(null);
        profissionalDTO.setIdade(-1);
        profissionalDTO.setCpf(null);
        profissionalDTO.setTelefone(null);
        profissionalDTO.setEmail(null);
        profissionalDTO.setSituacao(null);
        profissionalDTO.setDataAdmissao(null);
        profissionalDTO.setSalario(null);
        profissionalDTO.setSituacao(null);
        profissionalDTO.setFuncao(null);


        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro(null);
        enderecoModel.setBairro(null);
        enderecoModel.setNumero(null);
        enderecoModel.setCep(null);
        enderecoModel.setMunicipio(null);
        enderecoModel.setEstado(null);
        enderecoModel.setPais(null);
        profissionalDTO.setEndereco(enderecoModel);


        Response response = given()
                .contentType(ContentType.JSON)
                .body(profissionalDTO)
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
    @DisplayName("Deve buscar profissional por id com sucesso.")
    public void getByIdProfissionalTest(){


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
    @DisplayName("Deve falhar ao buscar profissional por id.")
    public void getByIdProfissionalValidationErrorTest(){


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
    @DisplayName("Deve buscar todos os profissionais com sucesso.")
    public void getAllProfissionalTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(apiURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(9)
    @Test
    @TestSecurity(user = "testUser", roles = {"ADMIN"})
    @DisplayName("Deve buscar todos os profissionais paginados e ordenados com sucesso.")
    public void pageSortProfissionalTest(){


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
    @DisplayName("Deve deletar por id o profissional com sucesso.")
    public void deleteProfissionalTest(){


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
    @DisplayName("Deve falhar ao deletar por id o profissional.")
    public void deleteProfissionalErrorTest(){


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
    @DisplayName("Deve falhar ao buscar todos os profissionais.")
    public void getAllProfissionalErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt1 = c.prepareStatement("delete from usuario");
        stmt1.execute();
        stmt1.close();
        PreparedStatement stmt2 = c.prepareStatement("delete from profissional");
        stmt2.execute();
        stmt2.close();
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
    @DisplayName("Deve falhar ao buscar os profissionais paginados e ordenados.")
    public void pageSortProfissionalErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt1 = c.prepareStatement("delete from usuario");
        stmt1.execute();
        stmt1.close();
        PreparedStatement stmt2 = c.prepareStatement("delete from profissional");
        stmt2.execute();
        stmt2.close();
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
