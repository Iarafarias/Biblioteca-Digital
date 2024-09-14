package ExcepUtils;

import javax.swing.JOptionPane;

public class Notificacao {

    // Método para enviar notificação de empréstimo
    public void enviarNotificacaoEmprestimo(String usuario, String livro) {
        String mensagem = String.format("Olá %s, o livro '%s' foi emprestado com sucesso.", usuario, livro);
        exibirNotificacao("Notificação de Empréstimo", mensagem);
    }

    // Método para enviar notificação de devolução
    public void enviarNotificacaoDevolucao(String usuario, String livro) {
        String mensagem = String.format("Olá %s, o livro '%s' foi devolvido com sucesso.", usuario, livro);
        exibirNotificacao("Notificação de Devolução", mensagem);
    }

    public void enviarNotificacao(String titulo, String mensagem) {
        System.out.println("Notificação Novo leitor:  " + titulo);
        System.out.println("Mensagem: " + mensagem);

        exibirNotificacao(titulo, mensagem);
    }

    // Método auxiliar para exibir a notificação na aplicação
    private void exibirNotificacao(String titulo, String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
