package view;

import model.DiaSemana;

import java.util.List;

public class JanelaDiaSemanaTabela extends TabelaCrud<DiaSemana> {
    public JanelaDiaSemanaTabela() {
        super("Dias da Semana");
        carregarTabela();
    }

    @Override
    protected String[] getColunas() {
        return new String[]{"ID", "Dia"};
    }

    @Override
    protected int[] getLargurasColunas() {
        return new int[]{50, 140};
    }

    @Override
    protected List<DiaSemana> carregarDados() {
        return GerenciadorDiaSemana.listarTodos();
    }

    @Override
    protected Object getValorColuna(DiaSemana item, int coluna) {
        return coluna == 0 ? item.getIdDiasemana() : item.getDia();
    }

    @Override
    protected Integer getId(DiaSemana item) {
        return item.getIdDiasemana();
    }

    @Override
    protected void cadastrarRegistro() {
        GerenciadorDiaSemana.cadastrar();
    }

    @Override
    protected void editarRegistro(Integer id) {
        GerenciadorDiaSemana.alterar(id);
    }

    @Override
    protected void excluirRegistro(Integer id) {
        GerenciadorDiaSemana.excluir(id);
    }
}
