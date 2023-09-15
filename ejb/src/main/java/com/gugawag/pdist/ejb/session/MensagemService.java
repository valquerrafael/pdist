package com.gugawag.pdist.ejb.session;

import com.gugawag.pdist.modelo.Mensagem;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless(name = "mensagemService")
public class MensagemService {
    @EJB
    private MensagemDAO mensagemDAO;

    public void inserir(long id, String texto) {
        Mensagem mensagem = new Mensagem(id, texto);
        this.mensagemDAO.inserir(mensagem);
    }

    public List<Mensagem> listar() {
        return this.mensagemDAO.listar();
    }

    public Mensagem pesquisarPorId(long id) {
        return this.mensagemDAO.pesquisarPorId(id);
    }
}
