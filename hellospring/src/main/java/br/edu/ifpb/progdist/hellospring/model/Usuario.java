package br.edu.ifpb.progdist.hellospring.model;

public class Usuario {
    private int codigo;
    private String nome;
    private int idade;

    public Usuario(int codigo, String nome, int idade) {
        this.codigo = codigo;
        this.nome = nome;
        this.idade = idade;
    }
    
    public int getCodigo() {
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public int getIdade() {
        return this.idade;
    }
}
