package model;

import java.util.ArrayList;
import java.util.List;

public class Leitor extends Usuario {
    private List<Emprestimo> historicoDeEmprestimos;

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

    public List<Emprestimo> verHistorico() {
        return historicoDeEmprestimos;
    }
}