package view;

import dao.CursosDao;
import model.Cursos;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class GerenciadorCursos {
    public static List<Cursos> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            CursosDao cursosDao = new CursosDao(em);
            return cursosDao.buscarTodos();
        } finally {
            em.close();
        }
    }

    public static void cadastrar() {
        EntityManager em = JPAUtil.getEntityManager();
        CursosDao cursosDao = new CursosDao(em);

        String descricao = JOptionPane.showInputDialog("Insira o nome do curso");
        Cursos cursos = new Cursos(descricao);
        em.getTransaction().begin();
        cursosDao.cadastrar(cursos);
        em.getTransaction().commit();
        em.close();
    }

    public static String consultar() {
        List<Cursos> todosCursos = listarTodos();
        int tamanho = todosCursos.size();
        String resultado = "ID \tNome do Curso\n";
        for (int i = 0; i < tamanho; i++) {
            resultado += todosCursos.get(i).getIdCurso()
                    + "\t" + todosCursos.get(i).getDescricao()
                    + "\n";
        }
        return resultado;
    }

    public static void alterar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        CursosDao cursosDao = new CursosDao(em);

        String descricao = JOptionPane.showInputDialog("Insira o nome do curso");
        Cursos cursos = cursosDao.buscarPorId(id);
        em.getTransaction().begin();
        cursos.setDescricao(descricao);
        em.getTransaction().commit();
        em.close();
    }

    public static void excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        CursosDao cursosDao = new CursosDao(em);

        Cursos cursos = cursosDao.buscarPorId(id);
        em.getTransaction().begin();
        cursosDao.excluir(cursos);
        em.getTransaction().commit();
        em.close();
    }


}
