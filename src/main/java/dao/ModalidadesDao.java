package dao;

import model.Modalidades;

import javax.persistence.EntityManager;
import java.util.List;

public class ModalidadesDao {
    private EntityManager em;

    public ModalidadesDao (EntityManager em) {
        this.em = em;
    }
    public void cadastrar(Modalidades modalidade) {
        this.em.persist(modalidade);
    }
    public List<Modalidades> buscarTodos () {
        String jpql = "select x from Modalidades x";
        return em.createQuery(jpql, Modalidades.class).getResultList();
    }
    public Modalidades buscarPorId(int id) {
        return em.find(Modalidades.class, id);
    }
    public void alterar (Modalidades modalidade) {
        this.em.merge(modalidade);
    }
    public void excluir(Modalidades modalidade) {
        this.em.merge(modalidade);
        this.em.remove(modalidade);
    }
}