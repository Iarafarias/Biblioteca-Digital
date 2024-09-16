package model;

public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private int estoque;
    private String categoria;
    private int quantEmprestimos; // Conta os empréstimos por livro
    private boolean disponivel;

    public Livro(String titulo, String autor, String isbn, int estoque, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.estoque = estoque;
        this.categoria = categoria;
        this.disponivel = true;
        this.quantEmprestimos = 0; // Inicializa em Zero
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

    // Getter para verificar se o livro está disponível
    public boolean isDisponivel() {
        return disponivel;
    }

    // Setter para mudar o status de disponibilidade do livro
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public int getQuantEmprestimos() {
        return quantEmprestimos;
    }

    public void somarEmprestimos() {
        quantEmprestimos++;
    }

    // Método para exibir as informações do livro
    public String exibirInformacoes() {
        return "Título: " + titulo + "\nAutor: " + autor + "\nISBN: " + isbn + "\nQuantidade: " + estoque
                + "Categoria: " + categoria + "Disponível:" + disponivel + "\nQuantidade de empréstimos realizados: "
                + quantEmprestimos;
    }
}
