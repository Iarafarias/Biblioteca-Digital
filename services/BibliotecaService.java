package services;

import model.Livro;
import model.Usuario;
import model.Leitor;
import model.Emprestimo;
import ExcepUtils.BibliotecaException;
import java.time.LocalDate;
import java.util.*;

public class BibliotecaService {
    private LivroRepo livroRepositorio = new LivroRepo();
    private UsuarioRepo usuarioRepositorio = new UsuarioRepo();

    private Map<String, Livro> livros;
    private List<Emprestimo> emprestimos;

    public BibliotecaService() {
        this.livros = new HashMap<>();
        this.emprestimos = new ArrayList<>();
    }

    // Método para cadastrar um novo livro
    public void cadastrarLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("O livro precisa ser válido");
        }
        livroRepositorio.adicionarLivro(livro);
    }

    // Método para buscar um livro pelo ISBN
    public Livro buscarLivro(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("O ISBN não pode ser nulo ou vazio.");
        }
        return livroRepositorio.buscarLivroPorIsbn(isbn);
    }

    // Método para atualizar as informações de um livro
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

    // Método para remover um livro pelo ISBN
    public void removerLivro(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("O ISBN não pode ser nulo ou vazio.");
        }
        livroRepositorio.removerLivro(isbn);
    }

    // Método para cadastrar um usuário
    public void cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }
        usuarioRepositorio.adicionarUsuario(usuario);
    }

    // Método para buscar um usuário pelo ID
    public Usuario buscarUsuario(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("O ID não pode ser nulo ou vazio.");
        }
        return usuarioRepositorio.buscarUsuarioPorId(id);
    }

    // Método para emprestar um livro
    public void emprestarLivro(String isbn, String usuarioId, LocalDate dataDevolucao) throws BibliotecaException {
        Livro livro = buscarLivro(isbn);
        Usuario usuario = buscarUsuario(usuarioId);

        // Verificação se o livro existe
        if (livro == null) {
            throw new BibliotecaException("Livro não encontrado.");
        }

        // Verificação se o usuário existe e é um Leitor
        if (usuario == null || !(usuario instanceof Leitor)) {
            throw new BibliotecaException("Usuário não encontrado ou não é um Leitor.");
        }

        // Verificação se há exemplares disponíveis
        if (livro.getEstoque() <= 0) {
            throw new BibliotecaException("Livro indisponível para empréstimo.");
        }

        // Realiza o empréstimo
        Leitor leitor = (Leitor) usuario;
        Emprestimo emprestimo = new Emprestimo(livro, leitor, dataDevolucao, null);
        leitor.adicionarEmprestimo(emprestimo);
        emprestimos.add(emprestimo);

        // Atualiza o estoque do livro
        livro.setEstoque(livro.getEstoque() - 1);

        // Se o estoque for zero, marca o livro como indisponível
        if (livro.getEstoque() == 0) {
            livro.setDisponivel(false);
        }

        // Atualiza o livro no repositório
        livroRepositorio.adicionarLivro(livro);

        System.out.println("Livro '" + livro.getTitulo() + "' emprestado com sucesso para " + leitor.getNome() + ".");
    }

    // Método para devolver um livro
    public void devolverLivro(Emprestimo emprestimo) {
        if (emprestimo == null) {
            throw new IllegalArgumentException("O empréstimo não pode ser nulo.");
        }
        Livro livro = emprestimo.getLivro();
        Usuario usuario = emprestimo.getLeitor();
        livro.setEstoque(livro.getEstoque() + 1); // Atualiza o estoque
        livro.setDisponivel(true); // Marca o livro como disponível
        ((Leitor) usuario).removerEmprestimo(emprestimo);
        emprestimo.setDataDevolucaoReal(LocalDate.now());
        livroRepositorio.adicionarLivro(livro); // Atualiza o livro no repositório
    }

    // Método para listar todos os livros cadastrados
    public List<Livro> listarLivros() {
        return livroRepositorio.listarLivros();
    }

    // Método para listar todos os empréstimos registrados
    public List<Emprestimo> listarEmprestimos() {
        return usuarioRepositorio.listarEmprestimos();
    }

    // Relatório de livros mais emprestados
    public List<Livro> relatorioLivrosMaisEmprestados() {
        Relatorio relatorio = new Relatorio(livros, emprestimos);
        return relatorio.gerarRelatorioLivrosMaisEmprestados();
    }

    // Estatísticas da biblioteca
    public Map<String, Object> estatisticasBiblioteca() {
        Relatorio relatorio = new Relatorio(livros, emprestimos);
        return relatorio.gerarEstatisticasBiblioteca();
    }
}
