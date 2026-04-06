package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaPrincipal implements ActionListener {
    JMenuItem sair,
            alunoCad, alunoCons, alunoAlt, alunoExc,
            aulaCad, aulaCons, aulaAlt, aulaExc,
            cursoCad, cursoCons, cursoAlt, cursoExc,
            diaSemanaCons,
            financeiroCad, financeiroCons, financeiroAlt, financeiroExc,
            modalidadeCons,
            presencaCad, presencaCons, presencaAlt, presencaExc,
            professorCad, professorCons, professorAlt, professorExc,
            salaCad, salaCons, salaAlt, salaExc;

    public JanelaPrincipal () {
        JFrame janela = new JFrame("SiGEM");
        janela.setSize(800, 600);
        janela.setLocationRelativeTo(null);

        JMenuBar jMenuBar = new JMenuBar();
        janela.setJMenuBar(jMenuBar);

        //JMenu cadastro = new JMenu("Cadastrar");
        JMenu consultas = new JMenu("Registros");
        JMenu financeiro = new JMenu("Financeiro");
        JMenu presenca = new JMenu("Presenças");
        JMenu encerra = new JMenu("Encerrar");

        //abas da janela principal
        //jMenuBar.add(cadastro);
        jMenuBar.add(consultas);
        jMenuBar.add(financeiro);
        jMenuBar.add(presenca);
        jMenuBar.add(encerra);

        //Cadastro
        alunoCad = new JMenuItem("Aluno");
        aulaCad = new JMenuItem("Aula");
        cursoCad = new JMenuItem("Curso");
        presencaCad = new JMenuItem("Presenças");
        professorCad = new JMenuItem("Professor");
        salaCad = new JMenuItem("Sala");

        //Consulta
        alunoCons = new JMenuItem("Alunos");
        aulaCons = new JMenuItem("Aulas");
        cursoCons = new JMenuItem("Cursos");

        //Financeiro
        alunoAlt = new JMenuItem("Alunos");
        alunoExc = new JMenuItem("Alunos");

        //Presenças
        aulaAlt = new JMenuItem("Aula");
        aulaExc = new JMenuItem("Aula");

        //Encerrar
        sair = new JMenuItem("Sair");


        cursoAlt= new JMenuItem("Curso");
        cursoExc = new JMenuItem("Curso");

        diaSemanaCons = new JMenuItem("Dias da aulas");

        financeiroCad = new JMenuItem("Financeiro");
        financeiroCons = new JMenuItem("Financeiro");
        financeiroAlt = new JMenuItem("Financeiro");
        financeiroExc = new JMenuItem("Financeiro");

        modalidadeCons = new JMenuItem("Modalidades");

        presencaCons = new JMenuItem("Presenças");
        presencaAlt = new JMenuItem("Presenças");
        presencaExc = new JMenuItem("Presenças");

        professorCons = new JMenuItem("Professores");
        professorAlt = new JMenuItem("Professor");
        professorExc = new JMenuItem("Professor");

        salaCons = new JMenuItem("Salas");
        salaAlt = new JMenuItem("Sala");
        salaExc = new JMenuItem("Sala");



        //abas que ativam as funções
//        cadastro.add(alunoCad);
//        cadastro.add(aulaCad);
//        cadastro.add(cursoCad);
//        cadastro.add(presencaCad);
//        cadastro.add(professorCad);
//        cadastro.add(salaCad);

        consultas.add(alunoCons);
        consultas.add(aulaCons);
        consultas.add(cursoCons);
        consultas.add(diaSemanaCons);
        consultas.add(modalidadeCons);
        consultas.add(professorCons);
        consultas.add(presencaCons);
        consultas.add(diaSemanaCons);
        consultas.add(salaCons);

        financeiro.add(financeiroCons);

        presenca.add(presencaCons);


        encerra.add(sair);

        //fica esperando o clique e executa o comando
        alunoCad.addActionListener(this);
        alunoCons.addActionListener(this);
        alunoAlt.addActionListener(this);
        alunoExc.addActionListener(this);

        aulaCad.addActionListener(this);
        aulaCons.addActionListener(this);
        aulaAlt.addActionListener(this);
        aulaExc.addActionListener(this);

        cursoCad.addActionListener(this);
        cursoCons.addActionListener(this);
        cursoAlt.addActionListener(this);
        cursoExc.addActionListener(this);

        diaSemanaCons.addActionListener(this);

        financeiroCad.addActionListener(this);
        financeiroCons.addActionListener(this);
        financeiroAlt.addActionListener(this);
        financeiroExc.addActionListener(this);

        modalidadeCons.addActionListener(this);

        presencaCad.addActionListener(this);
        presencaCons.addActionListener(this);
        presencaAlt.addActionListener(this);
        presencaExc.addActionListener(this);

        professorCad.addActionListener(this);
        professorCons.addActionListener(this);
        professorAlt.addActionListener(this);
        professorExc.addActionListener(this);

        salaCad.addActionListener(this);
        salaCons.addActionListener(this);
        salaAlt.addActionListener(this);
        salaExc.addActionListener(this);

        sair.addActionListener(this);

        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String resultado = "";
        int id = 0;
        JTextArea areaTexto = new JTextArea(10, 60);
        areaTexto.setEditable(false);
        JScrollPane painel = new JScrollPane(areaTexto);

        if (e.getSource() == sair) {
            System.exit(0);

        } else if (e.getSource() == alunoCad) {
            GerenciadorAlunos.cadastrar();
            JOptionPane.showMessageDialog(null, "Aluno cadastrado");

        } else if (e.getSource() == alunoCons || e.getSource() == alunoAlt || e.getSource() == alunoExc) {
            new JanelaAlunosTabela().setVisible(true);

        } else if (e.getSource() == aulaCad) {
            GerenciadorAulas.cadastrar();
            JOptionPane.showMessageDialog(null, "Aula cadastrada");

        } else if (e.getSource() == aulaCons || e.getSource() == aulaAlt || e.getSource() == aulaExc) {
            new JanelaAulasTabela().setVisible(true);

        } else if (e.getSource() == cursoCad) {
            GerenciadorCursos.cadastrar();
            JOptionPane.showMessageDialog(null, "Curso cadastrado");

        } else if (e.getSource() == cursoCons || e.getSource() == cursoAlt || e.getSource() == cursoExc) {
            new JanelaCursosTabela().setVisible(true);

        } else if (e.getSource() == diaSemanaCons) {
            new JanelaDiaSemanaTabela().setVisible(true);

        } else if (e.getSource() == financeiroCad) {
            GerenciadorFinanceiro.cadastrar();
            JOptionPane.showMessageDialog(null, "Registro cadastrado");

        } else if (e.getSource() == financeiroCons || e.getSource() == financeiroAlt || e.getSource() == financeiroExc) {
            new JanelaFinanceiroTabela().setVisible(true);

        } else if (e.getSource() == modalidadeCons) {
            new JanelaModalidadesTabela().setVisible(true);

        } else if (e.getSource() == presencaCad) {
            GerenciadorPresencas.cadastrar();
            JOptionPane.showMessageDialog(null, "Registro cadastrado");

        } else if (e.getSource() == presencaCons || e.getSource() == presencaAlt || e.getSource() == presencaExc) {
            new JanelaPresencasTabela().setVisible(true);

        } else if (e.getSource() == professorCad) {
            GerenciadorProfessores.cadastrar();
            JOptionPane.showMessageDialog(null, "Registro cadastrado");

        } else if (e.getSource() == professorCons || e.getSource() == professorAlt || e.getSource() == professorExc) {
            new JanelaProfessoresTabela().setVisible(true);

        } else if (e.getSource() == salaCad) {
            GerenciadorSalas.cadastrar();
            JOptionPane.showMessageDialog(null, "Registro cadastrado");

        } else if (e.getSource() == salaCons || e.getSource() == salaAlt || e.getSource() == salaExc) {
            new JanelaSalasTabela().setVisible(true);
        }
    }
}
