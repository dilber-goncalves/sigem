package dao;

import model.Salas;

import javax.persistence.EntityManager;
import java.util.List;

public class SalasDao {
    private EntityManager em;

    public SalasDao (EntityManager em) {
        this.em = em;
    }
    public void cadastrar(Salas sala) {
        this.em.persist(sala);
    }
    public List<Salas> buscarTodos () {
        String jpql = "select x from Salas x";
        return em.createQuery(jpql, Salas.class).getResultList();
    }
    public Salas buscarPorId(int id) {
        return em.find(Salas.class, id);
    }
    public void alterar (Salas sala) {
        this.em.merge(sala);
    }
    public void excluir(Salas sala) {
        this.em.merge(sala);
        this.em.remove(sala);
    }
}