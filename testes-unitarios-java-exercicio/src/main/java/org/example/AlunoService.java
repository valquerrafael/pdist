package org.example;

import java.util.List;

public class AlunoService {

    private AlunoDAO alunoDAO;

    public AlunoService(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    // Método para inserir um aluno com validações
    public void inserirAluno(Aluno aluno) throws IllegalArgumentException {
        // Validação: Não pode cadastrar aluno com a mesma matrícula
        if (alunoDAO.existeMatriculaAtiva(aluno.getMatricula())) {
            throw new IllegalArgumentException("Já existe um aluno com a mesma matrícula ativa.");
        }

        // Validação: Não pode cadastrar aluno com o mesmo CPF
        if (alunoDAO.existeAlunoComCPF(aluno.getCpf())) {
            throw new IllegalArgumentException("Já existe um aluno com o mesmo CPF.");
        }

        // Validação: Não pode matricular aluno menor de idade
        if (aluno.getIdade() < 18) {
            throw new IllegalArgumentException("O aluno deve ser maior de idade para se matricular.");
        }

        // Outras validações podem ser adicionadas aqui

        // Se todas as validações passarem, insira o aluno
        alunoDAO.inserirAluno(aluno);
    }

    // Método para remover um aluno
    public void removerAluno(int id) {
        alunoDAO.removerAluno(id);
    }

    // Método para atualizar os dados de um aluno
    public void atualizarAluno(Aluno aluno) {
        alunoDAO.atualizarAluno(aluno);
    }

    // Método para recuperar um aluno pelo ID
    public Aluno recuperarAlunoPorId(int id) {
        return alunoDAO.recuperarAlunoPorId(id);
    }

    // Método para listar todos os alunos
    public List<Aluno> listarTodosAlunos() {
        return alunoDAO.listarTodosAlunos();
    }
}
