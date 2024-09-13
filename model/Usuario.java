package model;

public class Usuario {
    private String nome;
    private String id;
    private String perfil;

    public Usuario(String nome, String id, String perfil) {
        this.nome = nome;
        this.id = id;
        this.perfil = perfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
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
