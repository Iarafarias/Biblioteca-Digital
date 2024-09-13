package services;

import model.Emprestimo;
import model.Leitor;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioRepo {
    private List<Usuario> usuarios = new ArrayList<>();

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuarioPorId(String id) {
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        return usuario.orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public List<Emprestimo> listarEmprestimos() {
        List<Emprestimo> emprestimos = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Leitor) {
                Leitor leitor = (Leitor) usuario;
                emprestimos.addAll(leitor.verHistorico());
            }
        }

        return emprestimos;
    }

}
