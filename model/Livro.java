package model;

public class Livro {
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
