package aplicacao;

import model.Administrador;
import model.Leitor;
import model.Livro;
import model.Emprestimo;
import model.Usuario;
import services.BibliotecaService;
import ExcepUtils.Notificacao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private BibliotecaService bibliotecaService;
    private Notificacao notificacao;
    private Scanner scanner;

    public BibliotecaApp() {
        bibliotecaService = new BibliotecaService();
        notificacao = new Notificacao();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.run();
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("Bem-vindo ao Sistema de Biblioteca");
            System.out.println("1. Cadastro de Usuários");
            System.out.println("2. Gestão de Livros");
            System.out.println("3. Empréstimos e Devoluções");
            System.out.println("4. Relatórios e Estatísticas");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha

            switch (choice) {
                case 1:
                    gerenciarUsuarios();
                    break;
                case 2:
                    gerenciarLivros();
                    break;
                case 3:
                    gerenciarEmprestimos();
                    break;
                case 4:
                    gerarRelatorios();
                    break;
                case 5:
                    running = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void gerenciarUsuarios() {
        System.out.println("Gerenciamento de Usuários");
        System.out.println("1. Cadastrar Leitor");
        System.out.println("2. Cadastrar Administrador");
        System.out.println("3. Login");
        System.out.println("4. Perfil de Usuário");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha

        switch (choice) {
            case 1:
                cadastrarLeitor();
                break;
            case 2:
                cadastrarAdministrador();
                break;
            case 3:
                login();
                break;
            case 4:
                perfilUsuario();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void cadastrarLeitor() {
        System.out.print("Nome do Leitor: ");
        String nome = scanner.nextLine();
        System.out.print("ID do Leitor: ");
        String id = scanner.nextLine();

        Leitor leitor = new Leitor(nome, id);
        bibliotecaService.cadastrarUsuario(leitor);
        System.out.println("Leitor cadastrado com sucesso!");
    }

    private void cadastrarAdministrador() {
        System.out.print("Nome do Administrador: ");
        String nome = scanner.nextLine();
        System.out.print("ID do Administrador: ");
        String id = scanner.nextLine();

        Administrador administrador = new Administrador(bibliotecaService, nome, id);
        bibliotecaService.cadastrarUsuario(administrador);
        System.out.println("Administrador cadastrado com sucesso!");
    }

    private void login() {
        System.out.print("ID do Usuário: ");
        String id = scanner.nextLine();

        // Aqui você pode adicionar lógica para verificar a existência do usuário
        Usuario usuario = bibliotecaService.buscarUsuario(id);
        if (usuario != null) {
            System.out.println("Login bem-sucedido. Bem-vindo, " + usuario.getNome() + "!");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private void perfilUsuario() {
        System.out.print("ID do Usuário: ");
        String id = scanner.nextLine();

        Usuario usuario = bibliotecaService.buscarUsuario(id);
        if (usuario instanceof Leitor) {
            Leitor leitor = (Leitor) usuario;
            System.out.println("Perfil do Leitor: " + leitor.getNome());
            List<Emprestimo> emprestimos = leitor.verHistorico();
            System.out.println("Histórico de Empréstimos:");
            for (Emprestimo emprestimo : emprestimos) {
                System.out.println(
                        emprestimo.getLivro().getTitulo() + " - Data de Empréstimo: " + emprestimo.getDataEmprestimo());
            }
        } else {
            System.out.println("Usuário não encontrado ou não é um leitor.");
        }
    }

    private void gerenciarLivros() {
        System.out.println("Gestão de Livros");
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Remover Livro");
        System.out.println("3. Editar Livro");
        System.out.println("4. Buscar Livro");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha

        switch (choice) {
            case 1:
                adicionarLivro();
                break;
            case 2:
                removerLivro();
                break;
            case 3:
                editarLivro();
                break;
            case 4:
                buscarLivro();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void adicionarLivro() {
        System.out.print("Título do Livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor do Livro: ");
        String autor = scanner.nextLine();
        System.out.print("ISBN do Livro: ");
        String isbn = scanner.nextLine();
        System.out.print("Quantidade em Estoque: ");
        int estoque = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha
        System.out.print("Categoria do Livro: ");
        String categoria = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, isbn, estoque, categoria);
        bibliotecaService.cadastrarLivro(livro);
        System.out.println("Livro adicionado com sucesso!");
    }

    private void removerLivro() {
        System.out.print("ISBN do Livro a ser removido: ");
        String isbn = scanner.nextLine();
        bibliotecaService.removerLivro(isbn);
        System.out.println("Livro removido com sucesso!");
    }

    private void editarLivro() {
        System.out.print("ISBN do Livro a ser editado: ");
        String isbn = scanner.nextLine();
        Livro livroExistente = bibliotecaService.buscarLivro(isbn);

        if (livroExistente != null) {
            System.out.print("Novo Título (deixe em branco para manter o atual): ");
            String titulo = scanner.nextLine();
            if (!titulo.isEmpty()) {
                livroExistente.setTitulo(titulo);
            }
            System.out.print("Novo Autor (deixe em branco para manter o atual): ");
            String autor = scanner.nextLine();
            if (!autor.isEmpty()) {
                livroExistente.setAutor(autor);
            }
            System.out.print("Nova Quantidade em Estoque (deixe em branco para manter a atual): ");
            String estoqueStr = scanner.nextLine();
            if (!estoqueStr.isEmpty()) {
                livroExistente.setEstoque(Integer.parseInt(estoqueStr));
            }
            System.out.print("Nova Categoria (deixe em branco para manter a atual): ");
            String categoria = scanner.nextLine();
            if (!categoria.isEmpty()) {
                livroExistente.setCategoria(categoria);
            }
            bibliotecaService.atualizarLivro(livroExistente);
            System.out.println("Livro atualizado com sucesso!");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private void buscarLivro() {
        System.out.println("Buscar Livro");
        System.out.println("1. Por Título");
        System.out.println("2. Por Autor");
        System.out.println("3. Por Categoria");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha

        List<Livro> livros;
        switch (choice) {
            case 1:
                System.out.print("Título do Livro: ");
                String titulo = scanner.nextLine();
                livros = bibliotecaService.listarLivros(); // Implementar filtro de título
                livros.stream()
                        .filter(l -> l.getTitulo().contains(titulo))
                        .forEach(l -> System.out.println(l.exibirInformacoes()));
                break;
            case 2:
                System.out.print("Autor do Livro: ");
                String autor = scanner.nextLine();
                livros = bibliotecaService.listarLivros(); // Implementar filtro de autor
                livros.stream()
                        .filter(l -> l.getAutor().contains(autor))
                        .forEach(l -> System.out.println(l.exibirInformacoes()));
                break;
            case 3:
                System.out.print("Categoria do Livro: ");
                String categoria = scanner.nextLine();
                livros = bibliotecaService.listarLivros(); // Implementar filtro de categoria
                livros.stream()
                        .filter(l -> l.getCategoria().contains(categoria))
                        .forEach(l -> System.out.println(l.exibirInformacoes()));
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void gerenciarEmprestimos() {
        System.out.println("Empréstimos e Devoluções");
        System.out.println("1. Emprestar Livro");
        System.out.println("2. Devolver Livro");
        System.out.println("3. Histórico de Empréstimos");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha

        switch (choice) {
            case 1:
                emprestarLivro();
                break;
            case 2:
                devolverLivro();
                break;
            case 3:
                historicoEmprestimos();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void emprestarLivro() {
        System.out.print("ISBN do Livro: ");
        String isbn = scanner.nextLine();
        System.out.print("ID do Leitor: ");
        String idLeitor = scanner.nextLine();
        System.out.print("Data de Devolução (yyyy-MM-dd): ");
        LocalDate dataDevolucao = LocalDate.parse(scanner.nextLine());

        bibliotecaService.emprestarLivro(isbn, idLeitor, dataDevolucao);
        System.out.println("Livro emprestado com sucesso!");
        notificacao.enviarNotificacaoEmprestimo("Empréstimo realizado",
                "Você emprestou um livro. Data de devolução: " + dataDevolucao);
    }

    private void devolverLivro() {
        System.out.print("ISBN do Livro: ");
        String isbn = scanner.nextLine();
        System.out.print("ID do Leitor: ");
        String idLeitor = scanner.nextLine();

        Livro livro = bibliotecaService.buscarLivro(isbn);
        Usuario usuario = bibliotecaService.buscarUsuario(idLeitor);

        if (livro != null && usuario instanceof Leitor) {
            Leitor leitor = (Leitor) usuario;
            Emprestimo emprestimo = leitor.getEmprestimoPorLivro(livro);
            if (emprestimo != null) {
                bibliotecaService.devolverLivro(emprestimo);
                System.out.println("Livro devolvido com sucesso!");
            } else {
                System.out.println("Empréstimo não encontrado.");
            }
        } else {
            System.out.println("Livro ou leitor não encontrado.");
        }
    }

    private void historicoEmprestimos() {
        System.out.println("Histórico de Empréstimos");
        List<Emprestimo> emprestimos = bibliotecaService.listarEmprestimos();
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo.getLivro().getTitulo() + " - " + emprestimo.getLeitor().getNome()
                    + " - Data de Empréstimo: " + emprestimo.getDataEmprestimo());
        }
    }

    private void gerarRelatorios() {
        System.out.println("Relatórios e Estatísticas");
        System.out.println("1. Livros Mais Emprestados");
        System.out.println("2. Estatísticas da Biblioteca");
        System.out.print("Escolha uma opção: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consome a nova linha

        switch (choice) {
            case 1:
                relatorioLivrosEmprestados();
                break;
            case 2:
                estatisticasBiblioteca();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void relatorioLivrosEmprestados() {
        System.out.println("Livros Mais Emprestados:");
        // Adicione lógica para gerar relatório de livros mais emprestados
    }

    private void estatisticasBiblioteca() {
        System.out.println("Estatísticas da Biblioteca:");
        // Adicione lógica para gerar estatísticas da biblioteca
    }
}
