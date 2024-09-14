package controller;

import services.BibliotecaService;
import model.Livro;
import model.Usuario;
import model.Emprestimo;
import java.time.LocalDate;

import ExcepUtils.BibliotecaException;

public class BibliotecaController {
    private BibliotecaService bibliotecaService = new BibliotecaService();

    public void adicionarLivro(Livro livro) throws BibliotecaException {
        if (livro == null) {
            throw new BibliotecaException("O livro precisa ser válido.");
        }
        bibliotecaService.cadastrarLivro(livro);
    }

    public Livro buscarLivro(String isbn) throws BibliotecaException {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new BibliotecaException("O ISBN não pode ser nulo ou vazio.");
        }
        return bibliotecaService.buscarLivro(isbn);
    }

    public void removerLivro(String isbn) throws BibliotecaException {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new BibliotecaException("O ISBN não pode ser nulo ou vazio.");
        }
        bibliotecaService.removerLivro(isbn);
    }

    public void atualizarLivro(Livro livro) throws BibliotecaException {
        if (livro == null) {
            throw new BibliotecaException("O livro precisa ser válido.");
        }
        bibliotecaService.atualizarLivro(livro);
    }

    public void cadastrarUsuario(Usuario usuario) throws BibliotecaException {
        if (usuario == null) {
            throw new BibliotecaException("O usuário não pode ser nulo.");
        }
        bibliotecaService.cadastrarUsuario(usuario);
    }

    public Usuario buscarUsuario(String id) throws BibliotecaException {
        if (id == null || id.trim().isEmpty()) {
            throw new BibliotecaException("O ID não pode ser nulo ou vazio.");
        }
        return bibliotecaService.buscarUsuario(id);
    }

    public void emprestarLivro(String isbn, String usuarioId, LocalDate dataDevolucao) throws BibliotecaException {
        if (isbn == null || isbn.trim().isEmpty() || usuarioId == null || usuarioId.trim().isEmpty()) {
            throw new BibliotecaException("ISBN e ID do usuário não podem ser nulos ou vazios.");
        }
        bibliotecaService.emprestarLivro(isbn, usuarioId, dataDevolucao);
    }

    public void devolverLivro(Emprestimo emprestimo) throws BibliotecaException {
        if (emprestimo == null) {
            throw new BibliotecaException("O empréstimo não pode ser nulo.");
        }
        bibliotecaService.devolverLivro(emprestimo);
    }

    // Outros métodos para interagir com a biblioteca
}
