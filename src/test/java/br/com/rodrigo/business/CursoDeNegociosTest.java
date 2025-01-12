package br.com.rodrigo.business;

import br.com.rodrigo.service.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class CursoDeNegociosTest {
    CursoService cursoServiceMock;
    CursoDeNegocios cursoDeNegocios;

    @BeforeEach
    void beforeEachMethod() {
        //Arrange /Given
        cursoServiceMock = mock(CursoService.class);
        cursoDeNegocios = new CursoDeNegocios(cursoServiceMock);
    }

    @Test
    @DisplayName("TestandoBuscaDeCursosRelacionadosASpringBoot_Quando_UsandoUmMock")
    void buscandoTodosOsCursosDeSpringBootOndeOEstudanteEstaInscrito() {

        //When / Act
        List<String> filtrandoTodosOsCursos = cursoDeNegocios
                .buscandoTodosOsCursosDeSpringBootOndeOEstudanteEstaInscrito("Rodrigo");
        //Then / Assert
        assertEquals(4, filtrandoTodosOsCursos.size());
    }
}