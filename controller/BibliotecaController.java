package controller;

import services.BibliotecaService;
import model.Livro;

public class BibliotecaController {
    private BibliotecaService bibliotecaService = new BibliotecaService();

    public void adicionarLivro(Livro livro) {
        bibliotecaService.cadastrarLivro(livro);
    }

    public Livro buscarLivro(String isbn) {
        return bibliotecaService.buscarLivro(isbn);
    }

    // Outros métodos para interagir com a biblioteca
}
