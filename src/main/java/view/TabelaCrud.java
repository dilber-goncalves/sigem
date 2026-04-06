package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class TabelaCrud<T> extends JFrame {
    private final GenericTableModel tableModel = new GenericTableModel();
    private final JTable tabela = new JTable(tableModel);
    private final TableRowSorter<GenericTableModel> sorter = new TableRowSorter<>(tableModel);
    private final JTextField campoBusca = new JTextField();

    protected TabelaCrud(String titulo) {
        super(titulo);
        configurarJanela();
    }

    protected abstract String[] getColunas();

    protected int[] getLargurasColunas() {
        return null;
    }

    protected abstract List<T> carregarDados();

    protected abstract Object getValorColuna(T item, int coluna);

    protected abstract Integer getId(T item);

    protected abstract void cadastrarRegistro();

    protected abstract void editarRegistro(Integer id);

    protected abstract void excluirRegistro(Integer id);

    private void configurarJanela() {
        setSize(950, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel topo = new JPanel(new BorderLayout(8, 8));
        topo.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        topo.add(new JLabel("Buscar:"), BorderLayout.WEST);
        topo.add(campoBusca, BorderLayout.CENTER);

        tabela.setRowSorter(sorter);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setFillsViewportHeight(true);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton botaoNovo = new JButton("Novo");
        JButton botaoEditar = new JButton("Editar");
        JButton botaoExcluir = new JButton("Excluir");
        JButton botaoAtualizar = new JButton("Atualizar");
        JButton botaoFechar = new JButton("Fechar");

        botoes.add(botaoNovo);
        botoes.add(botaoEditar);
        botoes.add(botaoExcluir);
        botoes.add(botaoAtualizar);
        botoes.add(botaoFechar);

        add(topo, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        campoBusca.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                aplicarFiltro();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aplicarFiltro();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aplicarFiltro();
            }
        });

        botaoNovo.addActionListener(e -> {
            cadastrarRegistro();
            carregarTabela();
        });
        botaoEditar.addActionListener(e -> editarSelecionado());
        botaoExcluir.addActionListener(e -> excluirSelecionado());
        botaoAtualizar.addActionListener(e -> carregarTabela());
        botaoFechar.addActionListener(e -> dispose());

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    editarSelecionado();
                }
            }
        });
    }

    protected void carregarTabela() {
        tableModel.setDados(carregarDados());
        aplicarLargurasColunas();
        aplicarFiltro();
    }

    private void aplicarLargurasColunas() {
        int[] larguras = getLargurasColunas();
        if (larguras == null) {
            return;
        }

        for (int i = 0; i < larguras.length && i < tabela.getColumnModel().getColumnCount(); i++) {
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            coluna.setPreferredWidth(larguras[i]);
        }
    }

    private void aplicarFiltro() {
        String texto = campoBusca.getText().trim();
        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
            return;
        }
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(texto)));
    }

    private T getSelecionado() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um registro na tabela.");
            return null;
        }
        int linhaModelo = tabela.convertRowIndexToModel(linhaSelecionada);
        return tableModel.getItem(linhaModelo);
    }

    private void editarSelecionado() {
        T item = getSelecionado();
        if (item == null) {
            return;
        }
        editarRegistro(getId(item));
        carregarTabela();
    }

    private void excluirSelecionado() {
        T item = getSelecionado();
        if (item == null) {
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "Deseja excluir o registro selecionado?",
                "Confirmar exclusao",
                JOptionPane.YES_NO_OPTION
        );
        if (confirmacao == JOptionPane.YES_OPTION) {
            excluirRegistro(getId(item));
            carregarTabela();
        }
    }

    private class GenericTableModel extends AbstractTableModel {
        private List<T> dados = new ArrayList<>();

        public void setDados(List<T> dados) {
            this.dados = new ArrayList<>(dados);
            fireTableDataChanged();
        }

        public T getItem(int rowIndex) {
            return dados.get(rowIndex);
        }

        @Override
        public int getRowCount() {
            return dados.size();
        }

        @Override
        public int getColumnCount() {
            return getColunas().length;
        }

        @Override
        public String getColumnName(int column) {
            return getColunas()[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return getValorColuna(dados.get(rowIndex), columnIndex);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Object valor = dados.isEmpty() ? null : getValueAt(0, columnIndex);
            return valor == null ? Object.class : valor.getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }
}
