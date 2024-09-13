package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String id; // identifica o usuário
    private String perfil; // (leitor ou adm)

    public Usuario(String nome, String id, String perfil) {
        this.nome = nome;
        this.id = id;
        this.perfil = perfil;
    }

    public String getNome() { // Retorna o valor do atributo na classe
        return nome;
    }

    public void setNome(String nome) { // Define ou altera o valor do atributo na classe
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}

class Leitor extends Usuario {
    private List<Emprestimo> historicoDeEmprestimos;

    public Leitor(String nome, String id) {
        super(nome, id, "leitor");
        this.historicoDeEmprestimos = new ArrayList<>();
    }

    // Adiciona o empréstimo ao histórico do leitor
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        historicoDeEmprestimos.add(emprestimo);
    }

    // Remover um empréstimo do histórico
    public void removerEmprestimo(Emprestimo emprestimo) {
        historicoDeEmprestimos.remove(emprestimo);
    }

    // Ver o histórico completo de empréstimos
    public List<Emprestimo> verHistorico() {
        return historicoDeEmprestimos;
    }
}