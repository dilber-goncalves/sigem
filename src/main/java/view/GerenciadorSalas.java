package view;

import dao.SalasDao;
import model.Salas;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class GerenciadorSalas {
    public static List<Salas> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            SalasDao salasDao = new SalasDao(em);
            return salasDao.buscarTodos();
        } finally {
            em.close();
        }
    }

    public static void cadastrar() {
        EntityManager em = JPAUtil.getEntityManager();
        SalasDao salasDao = new SalasDao(em);

        String nomeSala = JOptionPane.showInputDialog("Insira o nome da sala");
        Salas salas = new Salas(nomeSala);
        em.getTransaction().begin();
        salasDao.cadastrar(salas);
        em.getTransaction().commit();
        em.close();
    }

    public static String consultar() {
        List<Salas> todasSalas = listarTodos();
        int tamanho = todasSalas.size();
        String resultado = "ID \tNome da Sala\n";
        for (int i = 0; i < tamanho; i++) {
            resultado += todasSalas.get(i).getIdSala()
                    + "\t" + todasSalas.get(i).getNomeSala()
                    + "\n";
        }
        return resultado;
    }

    public static void alterar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        SalasDao salasDao = new SalasDao(em);

        String nomeSala = JOptionPane.showInputDialog("Insira o nome da sala");
        Salas salas = salasDao.buscarPorId(id);
        em.getTransaction().begin();
        salas.setNomeSala(nomeSala);
        em.getTransaction().commit();
        em.close();
    }

    public static void excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        SalasDao salasDao = new SalasDao(em);

        Salas salas = salasDao.buscarPorId(id);
        em.getTransaction().begin();
        salasDao.excluir(salas);
        em.getTransaction().commit();
        em.close();
    }
}
