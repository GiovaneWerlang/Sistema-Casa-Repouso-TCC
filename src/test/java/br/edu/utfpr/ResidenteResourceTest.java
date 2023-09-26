package br.edu.utfpr;

import br.edu.utfpr.endereco.EnderecoModel;
import br.edu.utfpr.enums.Situacao;
import br.edu.utfpr.enums.TipoEstadia;
import br.edu.utfpr.residente.ResidenteDTO;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ResidenteResourceTest {

    @TestHTTPResource("/residente")
    URL apiURL;

    @TestHTTPResource("/residente/page/0/1")
    URL pageURL;

    @TestHTTPResource("/residente/1")
    URL idURL;

    @TestHTTPResource("/residente/321")
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
    @DisplayName("Deve criar residente com sucesso.")
    public void createResidenteTest(){
        ResidenteDTO residenteDTO = new ResidenteDTO();
        residenteDTO.setNome("Teste");
        residenteDTO.setIdade(45);
        residenteDTO.setCpf("90874109051");
        residenteDTO.setTelefone("12233456278");
        residenteDTO.setEmail("teste@teste.com");
        residenteDTO.setSituacao(Situacao.ATIVO);
        residenteDTO.setTipoEstadia(TipoEstadia.PADRAO);
        residenteDTO.setDataHoraIngresso(LocalDateTime.parse("1980-04-09T08:20:45", DateTimeFormatter.ISO_DATE_TIME));

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro("Teste");
        enderecoModel.setBairro("Teste");
        enderecoModel.setNumero("123");
        enderecoModel.setCep("12345-678");
        enderecoModel.setMunicipio("Teste");
        enderecoModel.setEstado("Teste");
        enderecoModel.setPais("Teste");
        residenteDTO.setEndereco(enderecoModel);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(residenteDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 201, response.getStatusCode());
    }

    @Test
    @Order(2)
    @DisplayName("Deve falhar ao criar residente.")
    public void createResidenteValidationErrorTest(){
        ResidenteDTO residenteDTO = new ResidenteDTO();
        residenteDTO.setNome(null);
        residenteDTO.setIdade(-1);
        residenteDTO.setCpf(null);
        residenteDTO.setTelefone(null);
        residenteDTO.setEmail(null);
        residenteDTO.setSituacao(null);
        residenteDTO.setTipoEstadia(null);
        residenteDTO.setDataHoraIngresso(null);

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro(null);
        enderecoModel.setBairro(null);
        enderecoModel.setNumero(null);
        enderecoModel.setCep(null);
        enderecoModel.setMunicipio(null);
        enderecoModel.setEstado(null);
        enderecoModel.setPais(null);
        residenteDTO.setEndereco(enderecoModel);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(residenteDTO)
                .when()
                .post(apiURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(3)
    @Test
    @DisplayName("Deve atualizar residente com sucesso.")
    public void updateResidenteTest(){

        ResidenteDTO residenteDTO = new ResidenteDTO();
        residenteDTO.setNome("Teste");
        residenteDTO.setIdade(45);
        residenteDTO.setCpf("90874109051");
        residenteDTO.setTelefone("12233456278");
        residenteDTO.setEmail("teste@teste.com");
        residenteDTO.setSituacao(Situacao.ATIVO);
        residenteDTO.setTipoEstadia(TipoEstadia.PADRAO);
        residenteDTO.setDataHoraIngresso(LocalDateTime.parse("1980-04-09T08:20:45", DateTimeFormatter.ISO_DATE_TIME));

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro("Teste");
        enderecoModel.setBairro("Teste");
        enderecoModel.setNumero("123");
        enderecoModel.setCep("12345-678");
        enderecoModel.setMunicipio("Teste");
        enderecoModel.setEstado("Teste");
        enderecoModel.setPais("Teste");
        residenteDTO.setEndereco(enderecoModel);


        Response response = given()
                .contentType(ContentType.JSON)
                .body(residenteDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals(201, response.getStatusCode());
    }

    @Order(4)
    @Test
    @DisplayName("Deve falhar ao atualizar residente.")
    public void updateResidenteValidationErrorTest(){

        ResidenteDTO residenteDTO = new ResidenteDTO();
        residenteDTO.setNome(null);
        residenteDTO.setIdade(-1);
        residenteDTO.setCpf(null);
        residenteDTO.setTelefone(null);
        residenteDTO.setEmail(null);
        residenteDTO.setSituacao(null);
        residenteDTO.setTipoEstadia(null);
        residenteDTO.setDataHoraIngresso(null);

        EnderecoModel enderecoModel = new EnderecoModel();
        enderecoModel.setLogradouro(null);
        enderecoModel.setBairro(null);
        enderecoModel.setNumero(null);
        enderecoModel.setCep(null);
        enderecoModel.setMunicipio(null);
        enderecoModel.setEstado(null);
        enderecoModel.setPais(null);
        residenteDTO.setEndereco(enderecoModel);


        Response response = given()
                .contentType(ContentType.JSON)
                .body(residenteDTO)
                .when()
                .put(idURL)
                .then()
                .extract().response();

        assertEquals( 422, response.getStatusCode());
        assertEquals("Erro de validação de campos.", response.jsonPath().getString("message"));

    }

    @Order(5)
    @Test
    @DisplayName("Deve buscar residente por id com sucesso.")
    public void getByIdResidenteTest(){


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
    @DisplayName("Deve falhar ao buscar residente por id.")
    public void getByIdResidenteValidationErrorTest(){


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
    @DisplayName("Deve buscar todos os residentes com sucesso.")
    public void getAllResidenteTest(){


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
    @DisplayName("Deve buscar todos os residentes paginados com sucesso.")
    public void pageResidenteTest(){

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
    @DisplayName("Deve deletar por id a residente com sucesso.")
    public void deleteResidenteTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(idURL)
                .then()
                .extract().response();

        assertEquals( 200, response.getStatusCode());
    }

    @Order(10)
    @Test
    @DisplayName("Deve falhar ao deletar por id a residente.")
    public void deleteResidenteErrorTest(){


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(erroURL)
                .then()
                .extract().response();

        assertEquals( 404, response.getStatusCode());
    }

    @Order(11)
    @Test
    @DisplayName("Deve falhar ao buscar todos os residentes.")
    public void getAllResidenteErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from residente");
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

    @Order(12)
    @Test
    @DisplayName("Deve falhar ao buscar todos os residentes paginados.")
    public void pageResidenteErrorTest() throws SQLException {
        DriverManager.registerDriver(new org.h2.Driver());
        Connection c = DriverManager.getConnection("jdbc:h2:mem:db;IFEXISTS=TRUE", "sa", "sa");
        PreparedStatement stmt = c.prepareStatement("delete from residente");
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
