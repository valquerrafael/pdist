package com.gugawag.pdist.ejb.session;

import com.gugawag.pdist.modelo.Mensagem;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class MensagemDAO {
    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public void inserir(Mensagem mensagem) {
        em.persist(mensagem);
    }

    public List<Mensagem> listar() {
        return em.createQuery("SELECT m FROM tb_mensagem_pdist m").getResultList();
    }

    public Mensagem pesquisarPorId(long id) {
        return (Mensagem) em.createQuery("SELECT m FROM tb_mensagem_pdist m WHERE m.id = " + id).getResultList().get(0);
    }
}
