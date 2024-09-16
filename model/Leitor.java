package model;

import java.util.ArrayList;
import java.util.List;

public class Leitor extends Usuario {
    private List<Emprestimo> historicoDeEmprestimos;
    private List<Emprestimo> emprestimos;

    public Leitor(String nome, String id) {
        super(nome, id, "leitor");
        this.historicoDeEmprestimos = new ArrayList<>();
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        historicoDeEmprestimos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        historicoDeEmprestimos.remove(emprestimo);
    }

    public boolean temEmprestimo(Emprestimo emprestimo) {
        if (emprestimo == null) {
            return false;
        }
        return emprestimos.contains(emprestimo);
    }

    public List<Emprestimo> verHistorico() {
        return historicoDeEmprestimos;
    }

    // Método para encontrar um empréstimo por livro
    public Emprestimo getEmprestimoPorLivro(Livro livro) {
        for (Emprestimo emprestimo : historicoDeEmprestimos) {
            if (emprestimo.getLivro().equals(livro)) {
                return emprestimo;
            }
        }
        return null; // Retorna null se o empréstimo não for encontrado
    }
}
