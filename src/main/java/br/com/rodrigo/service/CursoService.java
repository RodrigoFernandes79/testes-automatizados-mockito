package br.com.rodrigo.service;

import java.util.List;

public interface CursoService {

    List<String> recuperarCursos(String aluno);

    //Mockito Verify usado para métodos que nao retornam nada (void)
    public void deletarCurso(String curso);
}
