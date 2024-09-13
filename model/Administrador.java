package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void atualizarLivro(Livro livroAtualizado) {
        Optional<Livro> livroExiste = livros.stream()
                .filter(Livro -> Livro.getIsbn().equals(livroAtualizado.getIsbn()))
                .findFirst();

        if (livroExiste.isPresent()) {
            Livro livroExistente = livroExiste.get();
            livroExistente.setTitulo(livroAtualizado.getTitulo());
            livroExistente.setAutor(livroAtualizado.getAutor());
            livroExistente.setEstoque(livroAtualizado.getEstoque());
            livroExistente.setCategoria(livroAtualizado.getCategoria());
        } else {
            System.out.println("Livro com ISBN " + livroAtualizado.getIsbn() + " não encontrado.");
        }
    }

    // Busca um livro pelo ISBN
    public Livro buscarLivroPorIsbn(String isbn) {
        return livros.stream()
                .filter(livro -> livro.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    // Lista todos os livros
    public List<Livro> listarLivros() {
        return new ArrayList<>(livros);
    }
}

public class Administrador extends Usuario {

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