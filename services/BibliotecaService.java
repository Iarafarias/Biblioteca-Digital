package services;

import model.Livro;
import model.Usuario;
import model.Leitor;
import model.Emprestimo;
import java.time.LocalDate;
import java.util.List;

public class BibliotecaService {
    private LivroRepo livroRepositorio = new LivroRepo();
    private UsuarioRepo usuarioRepositorio = new UsuarioRepo();

    public void cadastrarLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("O livro precisa ser válido");
        }
        livroRepositorio.adicionarLivro(livro);
    }

    public Livro buscarLivro(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("O ISBN não pode ser nulo ou vazio.");
        }
        return livroRepositorio.buscarLivroPorIsbn(isbn);
    }

    public void atualizarLivro(Livro livroAtualizado) {
        if (livroAtualizado == null) {
            throw new IllegalArgumentException("O livro atualizado não pode ser nulo.");
        }
        Livro livroExistente = livroRepositorio.buscarLivroPorIsbn(livroAtualizado.getIsbn());
        if (livroExistente != null) {
            livroExistente.setTitulo(livroAtualizado.getTitulo());
            livroExistente.setAutor(livroAtualizado.getAutor());
            livroExistente.setEstoque(livroAtualizado.getEstoque());
            livroExistente.setCategoria(livroAtualizado.getCategoria());
            livroRepositorio.adicionarLivro(livroExistente);
        } else {
            throw new IllegalArgumentException("Livro não encontrado para atualização.");
        }
    }

    public void removerLivro(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("O ISBN não pode ser nulo ou vazio.");
        }
        livroRepositorio.removerLivro(isbn);
    }

    public void cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }
        usuarioRepositorio.adicionarUsuario(usuario);
    }

    public Usuario buscarUsuario(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("O ID não pode ser nulo ou vazio.");
        }
        return usuarioRepositorio.buscarUsuarioPorId(id);
    }

    public void emprestarLivro(String isbn, String usuarioId, LocalDate dataDevolucao) {
        if (isbn == null || isbn.trim().isEmpty() || usuarioId == null || usuarioId.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN e ID do usuário não podem ser nulos ou vazios.");
        }

        Livro livro = livroRepositorio.buscarLivroPorIsbn(isbn);
        Usuario usuario = usuarioRepositorio.buscarUsuarioPorId(usuarioId);

        if (livro == null) {
            throw new IllegalArgumentException("Livro não encontrado.");
        }

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if (!(usuario instanceof Leitor)) {
            throw new IllegalArgumentException("O usuário deve ser um leitor para emprestar um livro.");
        }

        Leitor leitor = (Leitor) usuario;

        if (livro.getEstoque() <= 0) {
            throw new IllegalStateException("Não há estoque suficiente para o livro.");
        }

        Emprestimo emprestimo = new Emprestimo(livro, leitor, LocalDate.now(), dataDevolucao);
        leitor.adicionarEmprestimo(emprestimo);

        livro.setEstoque(livro.getEstoque() - 1);
        livroRepositorio.adicionarLivro(livro); // Atualiza o livro no repositório
    }

    public void devolverLivro(Emprestimo emprestimo) {
        if (emprestimo == null) {
            throw new IllegalArgumentException("O empréstimo não pode ser nulo.");
        }
        Livro livro = emprestimo.getLivro();
        Usuario usuario = emprestimo.getLeitor();
        livro.setEstoque(livro.getEstoque() + 1);
        ((Leitor) usuario).removerEmprestimo(emprestimo);
        emprestimo.setDataDevolucaoReal(LocalDate.now());
        livroRepositorio.adicionarLivro(livro); // Atualiza o livro no repositório
    }

    public List<Livro> listarLivros() {
        return livroRepositorio.listarLivros();
    }

    public List<Emprestimo> listarEmprestimos() {
        return usuarioRepositorio.listarEmprestimos();
    }
}
