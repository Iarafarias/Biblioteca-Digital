import java.util.ArrayList;
import java.util.List;

class Biblioteca {
    private List<Livro> livros;

    public Biblioteca() {
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }

    public void atualizarLivro(Livro livro) {
        // Criar lógica para a atualização dos livros
    }
}

class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private int estoque;
    private String categoria;

    public Livro(String titulo, String autor, String isbn, int estoque, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    // Geovane 11/09/2024
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Método para exibir as informações do livro
    public String exibirInformacoes() {
        return "Título: " + titulo + ", Autor: " + autor + ", ISBN: " + isbn + ", Quantidade: " + estoque
                + ", Categoria: " + categoria;
    }
}

class Emprestimo {
    private Livro livro;
    private Leitor leitor;
    private String dataEmprestimo;
    private String dataDevolucao;

    public Emprestimo(Livro livro, Leitor leitor, String dataEmprestimo, String dataDevolucao) {
        this.livro = livro;
        this.leitor = leitor;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    // Criar getters e setters
}

// Cadastro de usuário (leitor e adm)
abstract class Usuario {
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

class Administrador extends Usuario {

    public Administrador(String nome, String id) {
        super(nome, id, "administrador");
    }

    // Adiciona o livro ao acervo da biblioteca
    public void adicionarLivro(Livro livro, Biblioteca biblioteca) {
        biblioteca.adicionarLivro(livro);
    }

    // Remover livro do acervo
    public void removerLivro(Livro livro, Biblioteca biblioteca) {
        biblioteca.removerLivro(livro);
    }

    // Atualizar o acervo
    public void atualizarLivro(Livro livro, Biblioteca biblioteca) {
        biblioteca.atualizarLivro(livro);
    }

}