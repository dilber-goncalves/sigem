package view;

import dao.AlunosDao;
import dao.ProfessoresDao;
import model.Alunos;
import model.Professores;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GerenciadorProfessores {
    public static List<Professores> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            ProfessoresDao professoresDao = new ProfessoresDao(em);
            return professoresDao.buscarTodos();
        } finally {
            em.close();
        }
    }

    public static void cadastrar() {
        EntityManager em = JPAUtil.getEntityManager();
        ProfessoresDao professoresDao = new ProfessoresDao(em);

        String nomeProfessor = JOptionPane.showInputDialog("Insira o nome do professor");
        String contatoProfessor = JOptionPane.showInputDialog("Insira o contato do professor");
        Date dataNascProfessor;
        String dataDigitada = JOptionPane.showInputDialog("insira a data de nascimento do professor");
        try {
            dataNascProfessor = new SimpleDateFormat("dd/MM/yyyy").parse(dataDigitada);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        Professores professor = new Professores(nomeProfessor, contatoProfessor, dataNascProfessor);

        em.getTransaction().begin();
        professoresDao.cadastrar(professor);
        em.getTransaction().commit();
        em.close();
    }

    public static String consultar() {
        List<Professores> todosProfessores = listarTodos();
        int tamanho = todosProfessores.size();
        String resultado = "ID \tNome do professor\tContato\t\tData de nascimento\n";
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < tamanho; i++) {
            resultado += todosProfessores.get(i).getIdProfessor()
                    + "\t" + todosProfessores.get(i).getNomeProfessor()
                    + "\t" + todosProfessores.get(i).getContatoProfessor()
                    + "\t" + sdfData.format(todosProfessores.get(i).getDataNascProfessor())
                    +"\n";
        }
        return resultado;

    }

    public static void alterar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        ProfessoresDao professoresDao = new ProfessoresDao(em);

        String botoes [] = {"Nome", "Contato", "Data de nascimento"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha a opção que deseja alterar", "ALTERAÇÕES", 0, 1, null, botoes, 0);
        if (opcao == 0) {
            String professor = JOptionPane.showInputDialog("Insira o nome do professor");
            Professores professores = professoresDao.buscarPorId(id);
            em.getTransaction().begin();
            professores.setNomeProfessor(professor);
            em.getTransaction().commit();
            em.close();

        } else if (opcao == 1) {
            String professor = JOptionPane.showInputDialog("Insira o contato do professor");
            Professores professores = professoresDao.buscarPorId(id);
            em.getTransaction().begin();
            professores.setContatoProfessor(professor);
            em.getTransaction().commit();
            em.close();

        } else {

            String dataDigitada = JOptionPane.showInputDialog("insira a data de nascimento do professor");
            Date dataNascProfessor;
            try {
                dataNascProfessor = new SimpleDateFormat("dd/MM/yyyy").parse(dataDigitada);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            Professores professores = professoresDao.buscarPorId(id);
            em.getTransaction().begin();
            professores.setDataNascProfessor(dataNascProfessor);
            em.getTransaction().commit();
            em.close();
        }
    }

    public static void  excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        ProfessoresDao professoresDao = new ProfessoresDao(em);

        Professores professores = professoresDao.buscarPorId(id);
        em.getTransaction().begin();
        professoresDao.excluir(professores);
        em.getTransaction().commit();
        em.close();
    }
}
