package services;

import model.Livro;
import model.Usuario;
import services.LivroRepo;

public class BibliotecaService {

    private LivroRepo livroRepositorio = new LivroRepo();

    public void cadastrarLivro(Livro livro) {
        livroRepositorio.adicionarLivro(livro);
    }

    public Livro buscarLivro(String isbn) {
        return livroRepositorio.buscarLivroPorIsbn(isbn);
    }

    // Outros métodos relacionados ao gerenciamento de livros e usuários
}
