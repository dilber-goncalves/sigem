package dao;

import model.Financeiro;

import javax.persistence.EntityManager;
import java.util.List;

public class FinanceiroDao {
    private EntityManager em;

    public FinanceiroDao (EntityManager em) {
        this.em = em;
    }

    public void cadastrar (Financeiro financeiro) {
        this.em.persist(financeiro);
    }

    public List<Financeiro> buscarTodos() {
        String jpql = "select x from Financeiro x";
        return em.createQuery(jpql, Financeiro.class).getResultList();
    }

    public Financeiro buscarPorId(int id) {
        return em.find(Financeiro.class, id);
    }

    public void alterar(Financeiro financeiro) {
        this.em.merge(financeiro);
    }

    public void excluir(Financeiro financeiro) {
        this.em.merge(financeiro);
        this.em.remove(financeiro);
    }
}
