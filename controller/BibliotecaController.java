package controller;

import services.BibliotecaService;
import model.Livro;
import model.Usuario;
import model.Administrador;
import model.Emprestimo;
import java.time.LocalDate;

import ExcepUtils.BibliotecaException;

public class BibliotecaController {
    private BibliotecaService bibliotecaService = new BibliotecaService();

    private Usuario usuarioLogado;

    public BibliotecaController(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    // Método de login
    public void login(String id) throws BibliotecaException {
        Usuario usuario = bibliotecaService.buscarUsuario(id);
        if (usuario == null) {
            throw new BibliotecaException("Usuário não encontrado!");
        }
        this.usuarioLogado = usuario;
        System.out.println(
                "Login efetuado com sucesso! Usuário: " + usuario.getNome() + ", Perfil: " + usuario.getPerfil());
    }

    // Verificação de permissões
    public boolean verificarPermissaoAdmin() throws BibliotecaException {
        if (usuarioLogado == null) {
            throw new BibliotecaException("Nenhum usuário logado!");
        }
        return usuarioLogado.getPerfil().equalsIgnoreCase("administrador");
    }

    // Método para adicionar livro (apenas administrador)
    public void adicionarLivroComoAdmin(Livro livro) throws BibliotecaException {
        if (!verificarPermissaoAdmin()) {
            throw new BibliotecaException("Permissão negada! Apenas administradores podem adicionar livros.");
        }
        ((Administrador) usuarioLogado).adicionarLivro(livro);
    }

    // Método para listar livros (disponível para todos)
    public void listarLivros() {
        bibliotecaService.listarLivros().forEach(livro -> System.out.println(livro.exibirInformacoes()));
    }

    // Método para emprestar livro (apenas leitores)
    public void emprestarLivroComoLeitor(String isbn, LocalDate dataDevolucao) throws BibliotecaException {
        if (usuarioLogado == null || !usuarioLogado.getPerfil().equalsIgnoreCase("leitor")) {
            throw new BibliotecaException("Apenas leitores podem emprestar livros.");
        }
        bibliotecaService.emprestarLivro(isbn, usuarioLogado.getId(), dataDevolucao);
    }

    // Métodos existentes (manter para flexibilidade)
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
}