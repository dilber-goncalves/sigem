package dao;

import model.Alunos;

import javax.persistence.EntityManager;
import java.util.List;

public class AlunosDao {
    private EntityManager em;

    public AlunosDao (EntityManager em) {
        this.em = em;
    }
    public void cadastrar(Alunos aluno) {
        this.em.persist(aluno);
    }
    public List<Alunos> buscarTodos () {
        String jpql = "select x from Alunos x";
        return em.createQuery(jpql, Alunos.class).getResultList();
    }
    public Alunos buscarPorId(int id) {
        return em.find(Alunos.class, id);
    }
    public void alterar (Alunos aluno) {
        this.em.merge(aluno);
    }
    public void excluir(Alunos aluno) {
        this.em.merge(aluno);
        this.em.remove(aluno);
    }
}
