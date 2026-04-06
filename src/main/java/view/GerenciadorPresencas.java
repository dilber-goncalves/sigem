package view;

import dao.AlunosDao;
import dao.PresencasDao;
import model.Alunos;
import model.Presencas;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static view.GerenciadorAulas.retornaId;

public class GerenciadorPresencas {
    public static List<Presencas> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            PresencasDao presencasDao = new PresencasDao(em);
            return presencasDao.buscarTodos();
        } finally {
            em.close();
        }
    }

    public static void cadastrar() {
        EntityManager em = JPAUtil.getEntityManager();
        PresencasDao presencasDao = new PresencasDao(em);

        String resultado = GerenciadorAlunos.consultar();
        int idAluno = retornaId(resultado);
        Date dataPresenca;
        String dataDigitada = JOptionPane.showInputDialog("insira a data da aula");
        try {
            dataPresenca = new SimpleDateFormat("dd/MM/yyyy").parse(dataDigitada);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        String conteudo = JOptionPane.showInputDialog("Insira o conteúdo do dia");
        String status = JOptionPane.showInputDialog("Digite a presença (P/F)");

        Presencas presencas = new Presencas(dataPresenca, idAluno, conteudo, status);
        em.getTransaction().begin();
        presencasDao.cadastrar(presencas);
        em.getTransaction().commit();
        em.close();
    }

    public static String consultar() {
        EntityManager em = JPAUtil.getEntityManager();
        PresencasDao presencasDao = new PresencasDao(em);
        AlunosDao alunosDao = new AlunosDao(em);

        List<Presencas>todosRegistros = presencasDao.buscarTodos();
        int tamanho = todosRegistros.size();
        String resultado = "ID\tDATA\tALUNO\t\tCONTEÚDO DO DIA\t\tPRESENÇA\n";
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < tamanho; i++) {
            Presencas presencas = presencasDao.buscarPorId(todosRegistros.get(i).getIdPresenca());
            Alunos alunos = alunosDao.buscarPorId(todosRegistros.get(i).getIdAluno());

            resultado += todosRegistros.get(i).getIdPresenca() + "\t" +
                    sdfData.format(presencas.getData()) + "\t" +
                    alunos.getNomeAluno() + "\t" +
                    presencas.getConteudo() + "\t\t" +
                    presencas.getStatus()+ "\n";
        }
        return resultado;
    }

    public static void alterar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        PresencasDao presencasDao = new PresencasDao(em);
        AlunosDao alunosDao = new AlunosDao(em);
        Presencas presencas = presencasDao.buscarPorId(id);

        String botoes [] = {"Aluno", "Data", "Conteúdo", "Presença"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha a opção que deseja alterar", "ALTERAÇÕES", 0, 1, null, botoes, 0);
        if (opcao == 0) {
            String resultado = GerenciadorAlunos.consultar();
            JTextArea areaTexto = new JTextArea(10, 60);
            areaTexto.setText(resultado);
            areaTexto.setEditable(false);
            JScrollPane painel = new JScrollPane(areaTexto);
            int novoId = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Digite o ID do aluno", 1));
            em.getTransaction().begin();
            presencas.setIdAluno(novoId);
            em.getTransaction().commit();
            em.close();

        } else if (opcao == 1) {
            String dataDigitada = JOptionPane.showInputDialog("insira a data da aula");
            Date dataPresenca;
            try {
                dataPresenca = new SimpleDateFormat("dd/MM/yyyy").parse(dataDigitada);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            Presencas presenca = presencasDao.buscarPorId(id);
            em.getTransaction().begin();
            presenca.setData(dataPresenca);
            em.getTransaction().commit();
            em.close();

        } else if (opcao == 2){
            String conteudo = JOptionPane.showInputDialog("Insira o conteúdo do dia");
            Presencas presenca = presencasDao.buscarPorId(id);
            em.getTransaction().begin();
            presenca.setConteudo(conteudo);
            em.getTransaction().commit();
            em.close();

        } else {
            String status = JOptionPane.showInputDialog("Digite a presença (P/F)");
            Presencas presenca = presencasDao.buscarPorId(id);
            em.getTransaction().begin();
            presenca.setStatus(status);
            em.getTransaction().commit();
            em.close();
        }
    }

    public static void excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        PresencasDao presencasDao = new PresencasDao(em);

        Presencas presencas = presencasDao.buscarPorId(id);
        em.getTransaction().begin();
        presencasDao.excluir(presencas);
        em.getTransaction().commit();
        em.close();
    }
}
