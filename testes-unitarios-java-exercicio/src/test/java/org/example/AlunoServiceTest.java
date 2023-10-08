package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @InjectMocks
    AlunoService alunoService;

    @Mock
    AlunoDAO alunoDAO;

    Aluno aluno;

    @BeforeEach
    void config() {
        aluno = new Aluno();
        aluno.setId(1);
        aluno.setNome("Teste");
        aluno.setIdade(20);
        aluno.setMatricula("12345678911");
        aluno.setCpf("123.456.789-10");
    }

    @Test
    void deveriaInserirAluno() {
        Mockito.doNothing().when(alunoDAO).inserirAluno(aluno);

        alunoService.inserirAluno(aluno);

        Mockito.verify(alunoDAO).inserirAluno(aluno);
    }

    @Test
    void naoDeveriaInserirAlunoComMatriculaJaCadastrada() {
        Mockito.doReturn(true).when(alunoDAO).existeMatriculaAtiva(aluno.getMatricula());

        assertThrows(IllegalArgumentException.class, () -> alunoService.inserirAluno(aluno));
    }

    @Test
    void naoDeveriaInserirAlunoComCPFJaCadastrado() {
        Mockito.doReturn(true).when(alunoDAO).existeAlunoComCPF(aluno.getCpf());

        assertThrows(IllegalArgumentException.class, () -> alunoService.inserirAluno(aluno));
    }

    @Test
    void naoDeveriaInserirAlunoMenorDeIdade() {
        aluno.setIdade(17);

        assertThrows(IllegalArgumentException.class, () -> alunoService.inserirAluno(aluno));
    }

    @Test
    void deveriaRemoverAluno() {
        Mockito.doNothing().when(alunoDAO).removerAluno(aluno.getId());

        alunoService.removerAluno(aluno.getId());

        Mockito.verify(alunoDAO).removerAluno(aluno.getId());
    }

    @Test
    void deveriaAtualizarAluno() {
        Mockito.doNothing().when(alunoDAO).atualizarAluno(aluno);

        alunoService.atualizarAluno(aluno);

        Mockito.verify(alunoDAO).atualizarAluno(aluno);
    }

    @Test
    void deveriaRecuperarAlunoPorId() {
        Mockito.doReturn(aluno).when(alunoDAO).recuperarAlunoPorId(aluno.getId());

        var resultado = alunoService.recuperarAlunoPorId(aluno.getId());

        assertEquals(aluno, resultado);
    }

    @Test
     void deveriaListarTodosAlunos() {
        var alunos = new ArrayList<Aluno>();
        alunos.add(aluno);

        Mockito.doReturn(alunos).when(alunoDAO).listarTodosAlunos();

        var resultado = alunoService.listarTodosAlunos();

        assertEquals(alunos, resultado);
    }
}