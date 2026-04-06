package dao;

import model.DiaSemana;

import javax.persistence.EntityManager;
import java.util.List;

public class DiaSemanaDao {
    private EntityManager em;

    public DiaSemanaDao (EntityManager em) {
        this.em = em;
    }
    public void cadastrar(DiaSemana diasemana) {
        this.em.persist(diasemana);
    }
    public List<DiaSemana> buscarTodos () {
        String jpql = "select x from DiaSemana x";
        return em.createQuery(jpql, DiaSemana.class).getResultList();
    }
    public DiaSemana buscarPorId(int id) {
        return em.find(DiaSemana.class, id);
    }
    public void alterar (DiaSemana diasemana) {
        this.em.merge(diasemana);
    }
    public void excluir(DiaSemana diasemana) {
        this.em.merge(diasemana);
        this.em.remove(diasemana);
    }
}
