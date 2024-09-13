package model;

import java.time.LocalDate;

public class Emprestimo {
    private Livro livro;
    private Leitor leitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private LocalDate dataDevolucaoReal;

    public Emprestimo(Livro livro, Leitor leitor, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        if (livro == null || leitor == null || dataEmprestimo == null || dataDevolucao == null) {
            throw new IllegalArgumentException("Nenhum parâmetro pode ser nulo.");
        }
        if (dataDevolucao.isBefore(dataEmprestimo)) {
            throw new IllegalArgumentException("A data de devolução não pode ser anterior à data de empréstimo.");
        }
        this.livro = livro;
        this.leitor = leitor;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.dataDevolucaoReal = null;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("O livro não pode ser nulo.");
        }
        this.livro = livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        if (leitor == null) {
            throw new IllegalArgumentException("O leitor não pode ser nulo.");
        }
        this.leitor = leitor;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        if (dataEmprestimo == null) {
            throw new IllegalArgumentException("A data de empréstimo não pode ser nula.");
        }
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        if (dataDevolucao == null) {
            throw new IllegalArgumentException("A data de devolução não pode ser nula.");
        }
        if (dataDevolucao.isBefore(dataEmprestimo)) {
            throw new IllegalArgumentException("A data de devolução não pode ser anterior à data de empréstimo.");
        }
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        if (dataDevolucaoReal != null && dataDevolucaoReal.isBefore(dataEmprestimo)) {
            throw new IllegalArgumentException("A data de devolução real não pode ser anterior à data de empréstimo.");
        }
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public boolean isAtrasado() {
        if (dataDevolucaoReal == null) {
            return LocalDate.now().isAfter(dataDevolucao);
        }
        return dataDevolucaoReal.isAfter(dataDevolucao);
    }

    public long calcularAtraso() {
        if (dataDevolucaoReal == null) {
            return LocalDate.now().isAfter(dataDevolucao) ? LocalDate.now().toEpochDay() - dataDevolucao.toEpochDay()
                    : 0;
        }
        return dataDevolucaoReal.isAfter(dataDevolucao) ? dataDevolucaoReal.toEpochDay() - dataDevolucao.toEpochDay()
                : 0;
    }
}
