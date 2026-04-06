package dao;

import model.Professores;

import javax.persistence.EntityManager;
import java.util.List;

public class ProfessoresDao {
    private EntityManager em;

    public ProfessoresDao (EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Professores professor) {
        this.em.persist(professor);
    }

    public List<Professores> buscarTodos() {
        String jpql = "select x from Professores x";
        return em.createQuery(jpql, Professores.class).getResultList();
    }

    public Professores buscarPorId(int id) {
        return em.find(Professores.class,id);
    }

    public void alterar (Professores professor) {
        this.em.merge(professor);
    }

    public void excluir(Professores professor) {
        this.em.merge(professor);
        this.em.remove(professor);
    }
}
