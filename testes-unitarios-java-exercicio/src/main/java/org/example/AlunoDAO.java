package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoDAO {
    private List<Aluno> alunos; // Simulação de um banco de dados

    public AlunoDAO(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    // Método para inserir um aluno no banco de dados
    public void inserirAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    // Método para remover um aluno do banco de dados
    public void removerAluno(int id) {
        alunos.removeIf(a -> a.getId() == id);
    }

    // Método para atualizar os dados de um aluno no banco de dados
    public void atualizarAluno(Aluno aluno) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getId() == aluno.getId()) {
                alunos.set(i, aluno);
                break;
            }
        }
    }

    // Método para recuperar um aluno pelo ID do banco de dados
    public Aluno recuperarAlunoPorId(int id) {
        return alunos.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Método para listar todos os alunos no banco de dados
    public List<Aluno> listarTodosAlunos() {
        return alunos;
    }

    // Método para verificar se existe uma matrícula ativa
    public boolean existeMatriculaAtiva(String matricula) {
        return alunos.stream()
                .anyMatch(a -> a.getMatricula() != null && a.getMatricula().equals(matricula));
    }

    // Método para verificar se existe um aluno com o mesmo CPF
    public boolean existeAlunoComCPF(String cpf) {
        return alunos.stream()
                .anyMatch(a -> a.getCpf() != null && a.getCpf().equals(cpf));
    }

    // Método para recuperar um aluno pelo CPF
    public Aluno recuperarAlunoPorCPF(String cpf) {
        return alunos.stream()
                .filter(a -> a.getCpf() != null && a.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }
}
