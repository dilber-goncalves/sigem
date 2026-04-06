package view;

import dao.DiaSemanaDao;
import model.DiaSemana;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class GerenciadorDiaSemana {
    public static List<DiaSemana> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            DiaSemanaDao diaSemanaDao = new DiaSemanaDao(em);
            return diaSemanaDao.buscarTodos();
        } finally {
            em.close();
        }
    }

    public static void cadastrar() {
        EntityManager em = JPAUtil.getEntityManager();
        DiaSemanaDao diaSemanaDao = new DiaSemanaDao(em);

        String dia = JOptionPane.showInputDialog("Insira o dia da semana");
        DiaSemana diaSemana = new DiaSemana(dia);
        em.getTransaction().begin();
        diaSemanaDao.cadastrar(diaSemana);
        em.getTransaction().commit();
        em.close();
    }

    public static String consultar() {
        List<DiaSemana> todosDiaSemana = listarTodos();
        int tamanho = todosDiaSemana.size();
        String resultado = "ID \tDia da Semana\n";
        for (int i = 0; i < tamanho; i++) {
            resultado += todosDiaSemana.get(i).getIdDiasemana()
                    + "\t" + todosDiaSemana.get(i).getDia()
                    + "\n";
        }
        return resultado;
    }

    public static void alterar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        DiaSemanaDao diaSemanaDao = new DiaSemanaDao(em);

        String dia = JOptionPane.showInputDialog("Insira o dia da semana");
        DiaSemana diaSemana = diaSemanaDao.buscarPorId(id);
        em.getTransaction().begin();
        diaSemana.setDia(dia);
        em.getTransaction().commit();
        em.close();
    }

    public static void excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        DiaSemanaDao diaSemanaDao = new DiaSemanaDao(em);

        DiaSemana diaSemana = diaSemanaDao.buscarPorId(id);
        em.getTransaction().begin();
        diaSemanaDao.excluir(diaSemana);
        em.getTransaction().commit();
        em.close();
    }
}
