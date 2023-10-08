package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    Aluno aluno;

    @BeforeEach
    void config() {
        aluno = new Aluno();
    }

    @Test
    void deveriaAtribuirMatricula() {
        //config -> AlunoTest.config()
        var matricula = "12345678910";
        //exec
        aluno.setMatricula(matricula);

        assertEquals(matricula, aluno.getMatricula());
    }

    @Test
    void naoDeveriaAtribuirMatriculaComMenosDe11Digitos() {
        //config -> AlunoTest.config()
        var matricula = "1234567890";
        //exec
        assertThrows(RuntimeException.class, () -> aluno.setMatricula(matricula));
    }

    @Test
    void naoDeveriaAtribuirMatriculaComMaisDe11Digitos() {
        //config -> AlunoTest.config()
        var matricula = "123456789012";
        //exec
        assertThrows(RuntimeException.class, () -> aluno.setMatricula(matricula));
    }

    @Test
    void naoDeveriaAtribuirMatriculaComCaracteresEspeciais() {
        var matricula = "1234567890!";
        assertThrows(RuntimeException.class, () -> aluno.setMatricula(matricula));
    }

    @Test
    void naoDeveriaAtribuirMatriculaComLetras() {
        var matricula = "1234567890a";
        assertThrows(RuntimeException.class, () -> aluno.setMatricula(matricula));
    }

}