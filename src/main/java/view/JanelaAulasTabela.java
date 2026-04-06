package view;

import model.Alunos;
import model.Cursos;
import model.DiaSemana;
import model.Modalidades;
import model.Professores;
import model.Salas;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JanelaAulasTabela extends TabelaCrud<JanelaAulasTabela.AulaLinha> {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public JanelaAulasTabela() {
        super("Aulas");
        carregarTabela();
    }

    @Override
    protected String[] getColunas() {
        return new String[]{"ID", "Aluno", "Professor", "Curso", "Modalidade", "Sala", "Dia", "Inicio"};
    }

    @Override
    protected int[] getLargurasColunas() {
        return new int[]{50, 180, 180, 140, 140, 100, 100, 90};
    }

    @Override
    protected List<AulaLinha> carregarDados() {
        Map<Integer, String> alunos = GerenciadorAlunos.listarTodos().stream()
                .collect(Collectors.toMap(Alunos::getIdAluno, Alunos::getNomeAluno, (a, b) -> a, HashMap::new));
        Map<Integer, String> professores = GerenciadorProfessores.listarTodos().stream()
                .collect(Collectors.toMap(Professores::getIdProfessor, Professores::getNomeProfessor, (a, b) -> a, HashMap::new));
        Map<Integer, String> cursos = GerenciadorCursos.listarTodos().stream()
                .collect(Collectors.toMap(Cursos::getIdCurso, Cursos::getDescricao, (a, b) -> a, HashMap::new));
        Map<Integer, String> modalidades = GerenciadorModalidades.listarTodos().stream()
                .collect(Collectors.toMap(Modalidades::getIdModalidade, Modalidades::getDescricao, (a, b) -> a, HashMap::new));
        Map<Integer, String> salas = GerenciadorSalas.listarTodos().stream()
                .collect(Collectors.toMap(Salas::getIdSala, Salas::getNomeSala, (a, b) -> a, HashMap::new));
        Map<Integer, String> dias = GerenciadorDiaSemana.listarTodos().stream()
                .collect(Collectors.toMap(DiaSemana::getIdDiasemana, DiaSemana::getDia, (a, b) -> a, HashMap::new));

        return GerenciadorAulas.listarTodos().stream()
                .map(aula -> new AulaLinha(
                        aula.getIdAula(),
                        alunos.getOrDefault(aula.getIdAluno(), "Aluno " + aula.getIdAluno()),
                        professores.getOrDefault(aula.getIdProfessor(), "Professor " + aula.getIdProfessor()),
                        cursos.getOrDefault(aula.getIdCurso(), "Curso " + aula.getIdCurso()),
                        modalidades.getOrDefault(aula.getIdModalidade(), "Modalidade " + aula.getIdModalidade()),
                        salas.getOrDefault(aula.getIdSala(), "Sala " + aula.getIdSala()),
                        dias.getOrDefault(aula.getIdDiaSemana(), "Dia " + aula.getIdDiaSemana()),
                        aula.getDataInicio() == null ? "" : sdf.format(aula.getDataInicio())
                ))
                .collect(Collectors.toList());
    }

    @Override
    protected Object getValorColuna(AulaLinha item, int coluna) {
        return switch (coluna) {
            case 0 -> item.getId();
            case 1 -> item.getAluno();
            case 2 -> item.getProfessor();
            case 3 -> item.getCurso();
            case 4 -> item.getModalidade();
            case 5 -> item.getSala();
            case 6 -> item.getDia();
            case 7 -> item.getInicio();
            default -> "";
        };
    }

    @Override
    protected Integer getId(AulaLinha item) {
        return item.getId();
    }

    @Override
    protected void cadastrarRegistro() {
        GerenciadorAulas.cadastrar();
    }

    @Override
    protected void editarRegistro(Integer id) {
        GerenciadorAulas.alterar(id);
    }

    @Override
    protected void excluirRegistro(Integer id) {
        GerenciadorAulas.excluir(id);
    }

    static class AulaLinha {
        private final int id;
        private final String aluno;
        private final String professor;
        private final String curso;
        private final String modalidade;
        private final String sala;
        private final String dia;
        private final String inicio;

        private AulaLinha(int id, String aluno, String professor, String curso, String modalidade, String sala, String dia, String inicio) {
            this.id = id;
            this.aluno = aluno;
            this.professor = professor;
            this.curso = curso;
            this.modalidade = modalidade;
            this.sala = sala;
            this.dia = dia;
            this.inicio = inicio;
        }

        public int getId() {
            return id;
        }

        public String getAluno() {
            return aluno;
        }

        public String getProfessor() {
            return professor;
        }

        public String getCurso() {
            return curso;
        }

        public String getModalidade() {
            return modalidade;
        }

        public String getSala() {
            return sala;
        }

        public String getDia() {
            return dia;
        }

        public String getInicio() {
            return inicio;
        }
    }
}
