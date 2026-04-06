package view;

import dao.AlunosDao;
import model.Alunos;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GerenciadorAlunos {
    public static List<Alunos> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            AlunosDao alunosDao = new AlunosDao(em);
            return alunosDao.buscarTodos();
        } finally {
            em.close();
        }
    }

    public static void cadastrar() {
        EntityManager em = JPAUtil.getEntityManager();
        AlunosDao alunosDao = new AlunosDao(em);

        String nomeAluno = JOptionPane.showInputDialog("Insira o nome do aluno");
        Date dataNascimento;
        String dataDigitada = JOptionPane.showInputDialog("Insira a data de nascimento do aluno");
        try {
            dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataDigitada);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        String nivel = JOptionPane.showInputDialog("Insira o nível do aluno: (I)niciante - (M)édio - (A)vançado");
        String contatoAluno = JOptionPane.showInputDialog("Insira o contato do aluno");
        String nomeResponsavel = JOptionPane.showInputDialog("Insira o nome do responsável do aluno (Caso for menor de idade)");

        Alunos aluno = new Alunos(nomeAluno, nivel, contatoAluno, nomeResponsavel, dataNascimento);

        em.getTransaction().begin();
        alunosDao.cadastrar(aluno);
        em.getTransaction().commit();
        em.close();
    }

    public static String consultar() {
        List<Alunos>todosAlunos = listarTodos();
        int tamanho = todosAlunos.size();
        String resultado = "ID \tNome do aluno\t\tData de nascimento\tNível\tContato\t\tResponsável\t\n";
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < tamanho; i++) {
            resultado += todosAlunos.get(i).getIdAluno()
                    + "\t" +todosAlunos.get(i).getNomeAluno()
                    + "\t" +sdfData.format(todosAlunos.get(i).getDataNascimento())
                    + "\t\t" +todosAlunos.get(i).getNivel()
                    + "\t" +todosAlunos.get(i).getContatoAluno()
                    + "\t" +todosAlunos.get(i).getNomeResponsavel()
                    + "\n";
        }
        return resultado;
    }

    public static void alterar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        AlunosDao alunosDao = new AlunosDao(em);

        String botoes [] = {"Nome", "Data de nascimento", "Nível", "Contato", "Responsável"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha a opção que deseja alterar", "ALTERAÇÕES", 0, 1, null, botoes, 0);
        if (opcao == 0) {
            String nome = JOptionPane.showInputDialog("Insira o nome do aluno");
            Alunos alunos = alunosDao.buscarPorId(id);
            em.getTransaction().begin();
            alunos.setNomeAluno(nome);
            em.getTransaction().commit();
            em.close();

        } else if (opcao == 1) {
            String dataDigitada = JOptionPane.showInputDialog("insira a data de nascimento do aluno");
            Date dataNascAluno;
            try {
                dataNascAluno = new SimpleDateFormat("dd/MM/yyyy").parse(dataDigitada);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            Alunos alunos = alunosDao.buscarPorId(id);
            em.getTransaction().begin();
            alunos.setDataNascimento(dataNascAluno);
            em.getTransaction().commit();
            em.close();

        } else if (opcao == 2){
            String nivel = JOptionPane.showInputDialog("Insira o nível do aluno: (I)niciante - (M)édio - (A)vançado");
            Alunos alunos = alunosDao.buscarPorId(id);
            em.getTransaction().begin();
            alunos.setNivel(nivel);
            em.getTransaction().commit();
            em.close();

        } else if (opcao == 3) {
            String contato = JOptionPane.showInputDialog("Insira o contato do aluno");
            Alunos alunos = alunosDao.buscarPorId(id);
            em.getTransaction().begin();
            alunos.setContatoAluno(contato);
            em.getTransaction().commit();
            em.close();
        } else {
            String responsavel = JOptionPane.showInputDialog("Insira o nome do responsável do aluno");
            Alunos alunos = alunosDao.buscarPorId(id);
            em.getTransaction().begin();
            alunos.setNomeResponsavel(responsavel);
            em.getTransaction().commit();
            em.close();
        }
    }

    public static void  excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        AlunosDao alunosDao = new AlunosDao(em);

        Alunos alunos = alunosDao.buscarPorId(id);
        em.getTransaction().begin();
        alunosDao.excluir(alunos);
        em.getTransaction().commit();
        em.close();
    }
}
