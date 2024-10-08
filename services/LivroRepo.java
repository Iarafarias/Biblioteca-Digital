package services;

import model.Livro;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class LivroRepo {
    private Map<String, Livro> livros = new HashMap<>();

    public void adicionarLivro(Livro livro) {
        livros.put(livro.getIsbn(), livro);
    }

    public Livro buscarLivroPorIsbn(String isbn) {
        return livros.get(isbn);
    }

    public void removerLivro(String isbn) {
        livros.remove(isbn);
    }

    public List<Livro> listarLivros() {
        return new ArrayList<>(livros.values());
    }
}
