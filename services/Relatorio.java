package services;

import model.Livro;
import model.Emprestimo;
import java.util.*;
import java.util.stream.Collectors;

public class Relatorio {

    private List<Livro> livros;
    private List<Emprestimo> emprestimos;

    private BibliotecaService bibliotecaService;

    // Construtor que aceita BibliotecaService
    public Relatorio(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
        this.livros = bibliotecaService.listarLivros();
        this.emprestimos = bibliotecaService.listarEmprestimos();
    }

    public List<Livro> obterLivrosDisponiveis() {
        return bibliotecaService.listarLivros().stream()
                .filter(Livro::isDisponivel)
                .collect(Collectors.toList());
    }

    // Construtor que aceita Map e List
    public Relatorio(Map<String, Livro> livrosMap, List<Emprestimo> emprestimos) {
        // Converte o Map de livros em uma List de livros
        this.livros = new ArrayList<>(livrosMap.values());
        this.emprestimos = emprestimos;
    }

    // a. Relatório de livros mais emprestados
    public List<Livro> gerarRelatorioLivrosMaisEmprestados() {
        return livros.stream()
                .sorted(Comparator.comparingInt(Livro::getQuantEmprestimos).reversed())
                .collect(Collectors.toList());
    }

    // b. Estatísticas sobre a biblioteca
    public Map<String, Object> gerarEstatisticasBiblioteca() {
        Map<String, Object> estatisticas = new HashMap<>();
        int totalEmprestimos = emprestimos.size(); // Total de empréstimos
        long livrosDisponiveis = livros.stream().filter(Livro::isDisponivel).count(); // Livros disponíveis
        long livrosIndisponiveis = livros.size() - livrosDisponiveis; // Livros indisponíveis
        int totalLivros = livros.size(); // Total de livros

        estatisticas.put("totalEmprestimos", totalEmprestimos);
        estatisticas.put("livrosDisponiveis", livrosDisponiveis);
        estatisticas.put("livrosIndisponiveis", livrosIndisponiveis);
        estatisticas.put("totalLivros", totalLivros);

        return estatisticas;
    }
}
