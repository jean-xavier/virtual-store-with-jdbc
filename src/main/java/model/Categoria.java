package model;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private Long id;
    private String nome;
    private List<Produto> produtos;

    public Categoria() {
        produtos = new ArrayList<>();
    }

    public Categoria(Long id, String nome) {
        this();
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void setProdutos(Produto produto) {
        this.produtos.add(produto);
    }

}
