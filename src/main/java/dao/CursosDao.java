package dao;

import model.Cursos;

import javax.persistence.EntityManager;
import java.util.List;

public class CursosDao {
    private EntityManager em;

    public CursosDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cursos curso) {
        this.em.persist(curso);
    }

    public List<Cursos> buscarTodos () {
        String jpql = "select x from Cursos x";
        return em.createQuery(jpql, Cursos.class).getResultList();
    }

    public Cursos buscarPorId(int id) {
        return em.find(Cursos.class, id);
    }

    public void alterar (Cursos curso) {
        this.em.merge(curso);
    }

    public void excluir(Cursos curso) {
        this.em.merge(curso);
        this.em.remove(curso);
    }
}
