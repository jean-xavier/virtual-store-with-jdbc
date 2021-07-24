package dao;

import model.Categoria;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriaDAO {
    private final Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();

        final String sql = "SELECT ID, NOME FROM categoria";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();
            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Categoria categoria = new Categoria(rst.getLong("id"), rst.getString("nome"));
                    categorias.add(categoria);
                }
            }
        }

        return categorias;
    }

    public List<Categoria> listarComProdutos() throws SQLException {

        List<Categoria> categorias = new ArrayList<>();

        final String sql = "select c.id, c.nome, p.id, p.nome, p.descricao" +
                " from categoria c" +
                " inner join produto p" +
                " on c.id = p.categoria_id";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();
            Categoria ultimaCategoria = null;

            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    if (Objects.isNull(ultimaCategoria) || !ultimaCategoria.getNome().equals(rst.getString(2))) {
                        Categoria categoria = new Categoria(rst.getLong(1), rst.getString(2));
                        ultimaCategoria = categoria;
                        categorias.add(categoria);
                    }

                    Produto produto = new Produto(rst.getLong(3), rst.getString(4), rst.getString(5));
                    ultimaCategoria.setProdutos(produto);
                }
            }
        }

        return categorias;
    }

}
