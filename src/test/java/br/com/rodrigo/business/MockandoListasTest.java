package br.com.rodrigo.business;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class MockandoListasTest {
    List<?> mocklist;

    @BeforeEach
    void beforeEachMethod() {
        //Arrange / Given
        mocklist = mock(List.class);
    }

    @Test
    @DisplayName("TestDeMockComListas_Quando_ChamarOSize_DeveRetornar10")
    public void TestDeMockComListas() {
//Arrange / Given
        given(mocklist.size()).willReturn(10);

        //When / Act
        int tamanhoAtual = mocklist.size();
        // Then / Assertion
        assertEquals(10, tamanhoAtual);
    }

    @Test
    @DisplayName("TestDeMockComListas_Quando_ChamarOSize_DeveRetornarMultiplosSizes")
    public void TestDeMockComListas_cenario02() {
//Arrange / Given
        given(mocklist.size()).willReturn(10).willReturn(20);

        //When / Act
        int tamanhoAtual1 = mocklist.size();
        int tamanhoAtual2 = mocklist.size();
        // Then / Assertion
        assertEquals(10, tamanhoAtual1);
        assertEquals(20, tamanhoAtual2);
    }

    @Test
    @DisplayName("TestDeMockComListas_Quando_ChamarOGetNaPosicao0_DeveRetornarRodrigo")
    public void TestDeMockComListas_cenario03() {
//Arrange / Given
        List<String> mockList2 = mock(List.class);
        given(mockList2.get(0)).willReturn("Rodrigo");

        //When / Act
        var stringAtual = mockList2.get(0);
        // Then / Assertion
        assertEquals("Rodrigo", stringAtual);
        assertNull(mockList2.get(1));
    }

    @Test
    @DisplayName("TestDeMockComListas_Quando_ChamarOGetNaPosicao0_DeveRetornarUmaException")
    public void TestDeMockComListas_cenario04() {
//Arrange / Given
        List<String> mockList2 = mock(List.class);
        given(mockList2.get(0)).willThrow(new RuntimeException("Lançando uma Exception"));

        //Then / Assertion
        assertThrows(RuntimeException.class,
                //When / Act
                () -> {
                    mockList2.get(0);
                },
                () -> "Deveria retornar uma exceção");
        assertNull(mockList2.get(1));
    }
}
