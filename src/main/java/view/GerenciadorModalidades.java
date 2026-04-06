package view;

import dao.ModalidadesDao;
import model.Modalidades;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class GerenciadorModalidades {
    public static List<Modalidades> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            ModalidadesDao modalidadesDao = new ModalidadesDao(em);
            return modalidadesDao.buscarTodos();
        } finally {
            em.close();
        }
    }

    public static void cadastrar() {
        EntityManager em = JPAUtil.getEntityManager();
        ModalidadesDao modalidadesDao = new ModalidadesDao(em);

        String descricao = JOptionPane.showInputDialog("Insira o tipo da modalidade");
        Modalidades modalidades = new Modalidades(descricao);
        em.getTransaction().begin();
        modalidadesDao.cadastrar(modalidades);
        em.getTransaction().commit();
        em.close();
    }

    public static String consultar() {
        List<Modalidades> todasModalidades = listarTodos();
        int tamanho = todasModalidades.size();
        String resultado = "ID \tDescrição\n";
        for (int i = 0; i < tamanho; i++) {
            resultado += todasModalidades.get(i).getIdModalidade()
                    + "\t" + todasModalidades.get(i).getDescricao()
                    + "\n";
        }
        return resultado;
    }

    public static void alterar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        ModalidadesDao modalidadesDao = new ModalidadesDao(em);

        String descricao = JOptionPane.showInputDialog("Insira o tipo da modalidade");
        Modalidades modalidades = modalidadesDao.buscarPorId(id);
        em.getTransaction().begin();
        modalidades.setDescricao(descricao);
        em.getTransaction().commit();
        em.close();
    }

    public static void excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        ModalidadesDao modalidadesDao = new ModalidadesDao(em);

        Modalidades modalidades = modalidadesDao.buscarPorId(id);
        em.getTransaction().begin();
        modalidadesDao.excluir(modalidades);
        em.getTransaction().commit();
        em.close();
    }
}
