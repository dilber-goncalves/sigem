package dao;

import model.Presencas;

import javax.persistence.EntityManager;
import java.util.List;

public class PresencasDao {
    private EntityManager em;

    public PresencasDao (EntityManager em) {
        this.em = em;
    }

    public void cadastrar (Presencas presenca) {
        this.em.persist(presenca);
    }

    public List<Presencas> buscarTodos() {
        String jpql = "select x from Presencas x";
        return em.createQuery(jpql, Presencas.class).getResultList();
    }

    public Presencas buscarPorId(int id) {
        return em.find(Presencas.class, id);
    }

    public void alterar (Presencas presenca) {
        this.em.merge(presenca);
    }

    public void excluir (Presencas presenca) {
        this.em.merge(presenca);
        this.em.remove(presenca);
    }
}
