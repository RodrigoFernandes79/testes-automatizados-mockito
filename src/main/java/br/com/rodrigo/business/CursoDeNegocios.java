package br.com.rodrigo.business;

import br.com.rodrigo.service.CursoService;

import java.util.ArrayList;
import java.util.List;

//System (method) Under Test - SUT
public class CursoDeNegocios {

    //CursoService é uma dependency
    private CursoService cursoService;

    public CursoDeNegocios(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    public List<String> buscandoTodosOsCursosDeSpringBootOndeOEstudanteEstaInscrito(String aluno) {

        List<String> listaDeCursosSpringBootFiltrado = new ArrayList<>();
        List<String> filtrandoTodosOsCursos = cursoService.recuperarCursos(aluno);

        for (String cursoSpringBoot : filtrandoTodosOsCursos) {
            if (cursoSpringBoot.contains("Spring")) {
                listaDeCursosSpringBootFiltrado.add(cursoSpringBoot);
            }
        }
        return listaDeCursosSpringBootFiltrado;
    }


}
