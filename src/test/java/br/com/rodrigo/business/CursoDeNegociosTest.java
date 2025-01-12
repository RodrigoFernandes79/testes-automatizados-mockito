package br.com.rodrigo.business;

import br.com.rodrigo.service.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class CursoDeNegociosTest {
    CursoService cursoServiceMock;
    CursoDeNegocios cursoDeNegocios;
    List<String> listaDeCursosDoAluno;

    @BeforeEach
    void beforeEachMethod() {
        //Arrange /Given
        cursoServiceMock = mock(CursoService.class);
        cursoDeNegocios = new CursoDeNegocios(cursoServiceMock);
        listaDeCursosDoAluno = Arrays.asList(
                "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
        );
    }

    @Test
    @DisplayName("TestandoBuscaDeCursosRelacionadosASpringBoot_Quando_UsandoUmMock")
    void buscandoTodosOsCursosDeSpringBootOndeOEstudanteEstaInscrito() {
//Arrange / Given
        given(cursoServiceMock.recuperarCursos("Rodrigo"))
                .willReturn(listaDeCursosDoAluno);

        //When / Act
        List<String> filtrandoTodosOsCursos = cursoDeNegocios
                .buscandoTodosOsCursosDeSpringBootOndeOEstudanteEstaInscrito("Rodrigo");
        //Then / Assert
        assertEquals(4, filtrandoTodosOsCursos.size());
    }

    @Test
    @DisplayName("TestandoDeletarCursosQueNãoSãoRelacionadosASpringBoot_Quando_UsandoVerify")
    void deletarCursosQueNaoSaoRelacionadosASpringBoot() {


        //Arrange / Given
        given(cursoServiceMock.recuperarCursos("Rodrigo"))
                .willReturn(listaDeCursosDoAluno);
        //When / Act
        cursoDeNegocios.deletarCursosQueNaoSaoRelacionadosASpringBoot("Rodrigo"); //esse método retorna void

        //Then / Assert
        //Testando com Verify ( o Verify faz verfificações quando o metodo a ser testado retorna void
        verify(cursoServiceMock).deletarCurso("REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker");//verificando se o mockService deletou o curso
        verify(cursoServiceMock).deletarCurso("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        verify(cursoServiceMock, never())
                .deletarCurso("REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker"); // o never() indica que esse verify nunca deve ser chamado quando tiver o nome "Spring"
    }

    @Test
    @DisplayName("TestandoDeletarCursosQueNãoSãoRelacionadosASpringBoot_Quando_PassandoOArgumentCaptor")
    void deletarCursosQueNaoSaoRelacionadosASpringBoot_cenario2() {


        //Arrange / Given
        given(cursoServiceMock.recuperarCursos("Rodrigo"))
                .willReturn(listaDeCursosDoAluno);
        String cursoAzure = "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker";
        //passando o argumentCaptor que capturar os argumentos que sao passados como parametro
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        //When / Act
        cursoDeNegocios.deletarCursosQueNaoSaoRelacionadosASpringBoot("Rodrigo"); //esse método retorna void

        //Then / Assert
        //Testando com Verify ( o Verify faz verfificações quando o metodo a ser testado retorna void
        verify(cursoServiceMock, times(7)).deletarCurso(argumentCaptor.capture());//verificando se o mockService deletou o curso passado como parametro de um argument captor mockado
        assertEquals(argumentCaptor.getAllValues().get(0), cursoAzure);
        assertEquals(argumentCaptor.getAllValues().size(), 7); //verificando os cursos não relacionados a spring que não foram deletados

    }
}