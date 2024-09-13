package model;

import services.BibliotecaService;

public class Administrador extends Usuario {
    private BibliotecaService bibliotecaService;

    public Administrador(BibliotecaService bibliotecaService, String nome, String id) {
        super(nome, id, "administrador");
        this.bibliotecaService = bibliotecaService;
    }

    // Adiciona um livro ao sistema
    public void adicionarLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("O livro não pode ser nulo.");
        }
        bibliotecaService.cadastrarLivro(livro);
    }

    // Remove um livro pelo ISBN
    public void removerLivro(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("O ISBN não pode ser nulo ou vazio.");
        }
        Livro livro = bibliotecaService.buscarLivro(isbn);
        if (livro != null) {
            bibliotecaService.removerLivro(isbn);
        } else {
            System.out.println("Livro com ISBN " + isbn + " não encontrado.");
        }
    }

    // Atualiza as informações de um livro
    public void atualizarLivro(Livro livroAtualizado) {
        if (livroAtualizado == null) {
            throw new IllegalArgumentException("O livro atualizado não pode ser nulo.");
        }
        bibliotecaService.atualizarLivro(livroAtualizado);
    }
}
