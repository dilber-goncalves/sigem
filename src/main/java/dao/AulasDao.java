package dao;

import model.Aulas;

import javax.persistence.EntityManager;
import java.util.List;

public class AulasDao {
    private EntityManager em;

    public AulasDao (EntityManager em) {
        this.em = em;
    }

    public void cadastrar (Aulas aula) {
        this.em.persist(aula);
    }

    public List<Aulas> buscarTodos() {
        String jpql = "select x from Aulas x";
        return em.createQuery(jpql, Aulas.class).getResultList();
    }

    public Aulas buscarPorId(int id) {
        return em.find(Aulas.class, id);
    }

    public void alterar (Aulas aula) {
        this.em.merge(aula);
    }

    public void excluir (Aulas aula) {
        this.em.merge(aula);
        this.em.remove(aula);
    }
}
