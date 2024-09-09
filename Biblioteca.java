// Cadastro de usuário (leitor e adm)

import java.util.ArrayList;

abstract class Biblioteca {
    private String nome;
    private String id; // identifica o usuário
    private String perfil; // (leitor ou adm)

    public Biblioteca(String nome, String id, String perfil) {
        this.nome = nome;
        this.id = id;
        this.perfil = perfil;
    }

    public String getNome() { // Retorna o valor do atributo na classe
        return nome;
    }

    public String setNome(String nome) { // Define ou altera o valor do atributo na classe
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public String setId(String id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public String setPerfil(String perfil) {
        this.perfil = perfil;
    }
}

class Leitor extends Usuario {
    private List<Emprestimo> historicoDeEmprestimos;

    public Leitor(String nome, String id) {
        super(nome, id, "leitor");
        this.historicoDeEmprestimos = new ArrayList<>();
    }

    // Continuar com os métodos para adicionar histórico e etc
}

class Administrador extends Usuario {

    public Administrador(String nome, String id) {
        super(nome, id, "administrador");
    }

    // Continuar com métodos específicos para ADM
}