package br.edu.utfpr;

import br.edu.utfpr.endereco.EnderecoModel;
import br.edu.utfpr.enums.Funcao;
import br.edu.utfpr.enums.Situacao;
import br.edu.utfpr.profissional.ProfissionalDTO;
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
import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
//@TestTransaction
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfissionalResourceTest {

    @TestHTTPResource("/profissional")
    URL apiURL;

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

    @Test
    @Order(1)
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
        enderecoModel.setCep("12345-678");
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

    @Test
    @Order(2)
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
        enderecoModel.setCep("12345-678");
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
    @DisplayName("Deve buscar todas os profissionals com sucesso.")
    public void getAllProfissionalTest(){


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

    @Order(9)
    @Test
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

    @Order(10)
    @Test
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
}
