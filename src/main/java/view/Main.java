package view;

import javax.swing.*;

public class Main {
    static void main(String[] args) {
        new JanelaPrincipal();
        /*
        String [] botoes = {"Cadastrar", "Consultar", "Alterar", "Excluir", "Sair"};
        String [] botoesTab = {"Alunos", "Aulas", "Cursos", "Dia da Semana", "Financeiro", "Modalidades", "Professores", "Salas"};
        int opcao, opcaoTab, id = 0;
        String resultado = "";

        do {
            opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção", "SIGEM", 0, 1, null, botoes, 0);
            switch (opcao){
                case 0:
                    opcaoTab = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Cadastrar", 0, 1, null, botoesTab, 0);
                    switch (opcaoTab){
                        case 0:
                            GerenciadorAlunos.cadastrar();
                            break;
                        case 1:
                            GerenciadorAulas.cadastrar();
                            break;
                        case 2:
                            GerenciadorCursos.cadastrar();
                            break;

                        case 3:
                            GerenciadorDiaSemana.cadastrar();
                            break;

                        case 4:
                            GerenciadorFinanceiro.cadastrar();
                            break;

                        case 5:
                            GerenciadorModalidades.cadastrar();
                            break;

                        case 6:
                            GerenciadorProfessores.cadastrar();
                            break;

                        case 7:
                            GerenciadorSalas.cadastrar();
                            break;
                    }
                    JOptionPane.showMessageDialog(null, "Registro cadastrado com sucesso");
                    break;

                case 1:
                    opcaoTab = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Consultar", 0, 1, null, botoesTab, 0);
                    switch (opcaoTab){
                        case 0:
                            resultado = GerenciadorAlunos.consultar();
                            break;
                        case 1:
                            resultado = GerenciadorAulas.consultar();
                            break;
                        case 2:
                            resultado = GerenciadorCursos.consultar();
                            break;

                        case 3:
                            resultado = GerenciadorDiaSemana.consultar();
                            break;

                        case 4:
                            resultado = GerenciadorFinanceiro.consultar();

                        case 5:
                            resultado = GerenciadorModalidades.consultar();
                            break;

                        case 6:
                            resultado = GerenciadorProfessores.consultar();
                            break;

                        case 7:
                            resultado = GerenciadorSalas.consultar();
                            break;
                    }

                    JTextArea areaTexto = new JTextArea(10, 60);
                    areaTexto.setText(resultado);
                    areaTexto.setEditable(false);
                    JScrollPane painel = new JScrollPane(areaTexto);
                    JOptionPane.showMessageDialog(null, painel);
                    break;

                case 2:
                    opcaoTab = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Alterar", 0, 1, null, botoesTab, 0);
                    switch (opcaoTab) {
                        case 0:
                            resultado = GerenciadorAlunos.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja alterar", 1));
                            GerenciadorAlunos.alterar(id);
                            break;

                        case 1:
                            resultado = GerenciadorAulas.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja alterar", 1));
                            GerenciadorAulas.alterar(id);
                            break;

                        case 2:
                            resultado = GerenciadorCursos.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja alterar", 1));
                            GerenciadorCursos.alterar(id);
                            break;

                        case 3:
                            resultado = GerenciadorDiaSemana.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja alterar", 1));
                            GerenciadorDiaSemana.alterar(id);
                            break;

                        case 4:
                            resultado = GerenciadorFinanceiro.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja alterar", 1));
                            GerenciadorFinanceiro.alterar(id);
                            break;
                        case 5:
                            resultado = GerenciadorModalidades.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja alterar", 1));
                            GerenciadorModalidades.alterar(id);
                            break;

                        case 6:
                            resultado = GerenciadorProfessores.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja alterar", 1));
                            GerenciadorSalas.alterar(id);
                            break;

                        case 7:
                            resultado = GerenciadorSalas.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja alterar", 1));
                            GerenciadorSalas.alterar(id);
                            break;
                    }
                    JOptionPane.showMessageDialog(null, "Registro alterado com sucesso");
                    break;

                case 3:
                    opcaoTab = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Excluir", 0, 1, null, botoesTab, 0);
                    switch (opcaoTab) {
                        case 0:
                            resultado = GerenciadorAlunos.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja excluir", 1));
                            GerenciadorAlunos.excluir(id);
                            break;

                        case 2:
                            resultado = GerenciadorCursos.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja excluir", 1));
                            GerenciadorCursos.excluir(id);
                            break;

                        case 3:
                            resultado = GerenciadorDiaSemana.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja excluir", 1));
                            GerenciadorDiaSemana.excluir(id);
                            break;

                        case 4:
                            resultado = GerenciadorFinanceiro.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja excluir", 1));
                            GerenciadorFinanceiro.excluir(id);
                            break;

                        case 5:
                            resultado = GerenciadorModalidades.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja excluir", 1));
                            GerenciadorModalidades.excluir(id);
                            break;

                        case 6:
                            resultado = GerenciadorProfessores.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja excluir", 1));
                            GerenciadorSalas.excluir(id);
                            break;

                        case 7:
                            resultado = GerenciadorSalas.consultar();
                            areaTexto = new JTextArea(10, 60);
                            areaTexto.setText(resultado);
                            areaTexto.setEditable(false);
                            painel = new JScrollPane(areaTexto);

                            id = Integer.parseInt(JOptionPane.showInputDialog(null, painel, "Insira o ID que deseja excluir", 1));
                            GerenciadorSalas.excluir(id);
                            break;
                    }
                    JOptionPane.showMessageDialog(null, "Registro excluído com sucesso");
                    break;
            }
        } while (opcao !=4);
        */
    }
}
