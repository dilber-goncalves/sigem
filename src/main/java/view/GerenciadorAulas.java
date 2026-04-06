package view;

import dao.AlunosDao;
import dao.AulasDao;
import dao.CursosDao;
import dao.DiaSemanaDao;
import dao.ModalidadesDao;
import dao.ProfessoresDao;
import dao.SalasDao;
import model.Alunos;
import model.Aulas;
import model.Cursos;
import model.DiaSemana;
import model.Modalidades;
import model.Professores;
import model.Salas;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GerenciadorAulas {
    public static List<Aulas> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            AulasDao aulasDao = new AulasDao(em);
            return aulasDao.buscarTodos();
        } finally {
            em.close();
        }
    }

    public static void cadastrar() {
        EntityManager em = JPAUtil.getEntityManager();
        AulasDao aulasDao = new AulasDao(em);

        String resultado = GerenciadorAlunos.consultar();
        int idAluno = retornaId(resultado);

        resultado = GerenciadorProfessores.consultar();
        int idProfessor = retornaId(resultado);

        resultado = GerenciadorCursos.consultar();
        int idCurso = retornaId(resultado);

        resultado = GerenciadorModalidades.consultar();
        int idModalidade = retornaId(resultado);

        resultado = GerenciadorSalas.consultar();
        int idSalas = retornaId(resultado);

        resultado = GerenciadorDiaSemana.consultar();
        int idDiaSemana = retornaId(resultado);

        Date dataInicio;
        String dataDigitada = JOptionPane.showInputDialog("insira a data de inicio da aula do aluno");
        try {
            dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(dataDigitada);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        Aulas aulas = new Aulas(idAluno, idProfessor, idCurso, idModalidade, idSalas, idDiaSemana, dataInicio);
        em.getTransaction().begin();
        aulasDao.cadastrar(aulas);
        em.getTransaction().commit();
        em.close();
    }

    public static String consultar() {
        EntityManager em = JPAUtil.getEntityManager();
        AulasDao aulasDao = new AulasDao(em);
        AlunosDao alunosDao = new AlunosDao(em);
        ProfessoresDao professoresDao = new ProfessoresDao(em);
        CursosDao cursosDao = new CursosDao(em);
        ModalidadesDao modalidadesDao = new ModalidadesDao(em);
        SalasDao salasDao = new SalasDao(em);
        DiaSemanaDao diaSemanaDao = new DiaSemanaDao(em);

        List<Aulas> todosRegistros = aulasDao.buscarTodos();
        int tamanho = todosRegistros.size();
        String resultado = "ID\tNOME DO ALUNO\tPROFESSOR\tCURSO\tMODALIDADE\tSALA\tDIA\n";

        for (int i = 0; i < tamanho; i++) {
            Alunos alunos = alunosDao.buscarPorId(todosRegistros.get(i).getIdAluno());
            Professores professores = professoresDao.buscarPorId(todosRegistros.get(i).getIdProfessor());
            Cursos cursos = cursosDao.buscarPorId(todosRegistros.get(i).getIdCurso());
            Modalidades modalidades = modalidadesDao.buscarPorId(todosRegistros.get(i).getIdModalidade());
            Salas salas = salasDao.buscarPorId(todosRegistros.get(i).getIdSala());
            DiaSemana diaSemana = diaSemanaDao.buscarPorId(todosRegistros.get(i).getIdDiaSemana());

            resultado += todosRegistros.get(i).getIdAula() + "\t" +
                    alunos.getNomeAluno() + "\t" +
                    professores.getNomeProfessor() + "\t" +
                    cursos.getDescricao() + "\t" +
                    modalidades.getDescricao() + "\t" +
                    salas.getNomeSala() + "\t" +
                    diaSemana.getDia() + "\n";
        }
        em.close();
        return resultado;
    }

    public static void alterar(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        AulasDao aulasDao = new AulasDao(em);
        Aulas aulas = aulasDao.buscarPorId(id);

        String[] botoes = {"Aluno", "Professor", "Curso", "Modalidade", "Sala", "Dia", "Sair"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha a opcao que deseja alterar", "ALTERACOES", 0, 1, null, botoes, 0);
        if (opcao == 6 || opcao == JOptionPane.CLOSED_OPTION) {
            em.close();
            return;
        }

        JTextArea areaTexto = new JTextArea(10, 60);
        areaTexto.setEditable(false);
        JScrollPane painel = new JScrollPane(areaTexto);

        em.getTransaction().begin();
        switch (opcao) {
            case 0:
                areaTexto.setText(GerenciadorAlunos.consultar());
                JOptionPane.showMessageDialog(null, painel);
                aulas.setIdAluno(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do novo aluno")));
                break;
            case 1:
                areaTexto.setText(GerenciadorProfessores.consultar());
                aulas.setIdProfessor(Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Digite o ID do novo professor", 1)));
                break;
            case 2:
                areaTexto.setText(GerenciadorCursos.consultar());
                aulas.setIdCurso(Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Digite o ID do novo curso", 1)));
                break;
            case 3:
                areaTexto.setText(GerenciadorModalidades.consultar());
                aulas.setIdModalidade(Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Digite o ID da nova modalidade", 1)));
                break;
            case 4:
                areaTexto.setText(GerenciadorSalas.consultar());
                aulas.setIdSala(Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Digite o ID da nova sala", 1)));
                break;
            case 5:
                areaTexto.setText(GerenciadorDiaSemana.consultar());
                aulas.setIdDiaSemana(Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Digite o ID do novo dia de aula", 1)));
                break;
            default:
                break;
        }
        em.getTransaction().commit();
        em.close();
    }

    public static void excluir(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        AulasDao aulasDao = new AulasDao(em);

        Aulas aulas = aulasDao.buscarPorId(id);
        em.getTransaction().begin();
        aulasDao.excluir(aulas);
        em.getTransaction().commit();
        em.close();
    }

    public static int retornaId(String resultado) {
        JTextArea areaTexto = new JTextArea(5, 10);
        areaTexto.setEditable(false);
        areaTexto.setText(resultado);
        JScrollPane painel = new JScrollPane(areaTexto);
        return Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Digite o ID correspondente", 1));
    }
}
