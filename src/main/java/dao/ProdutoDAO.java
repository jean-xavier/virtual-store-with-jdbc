package dao;

import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdutoDAO {

    private final Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Produto produto) throws SQLException {

        String sql = "INSERT INTO produto (NOME, DESCRICAO) VALUES (?, ?)";

        try (PreparedStatement pstm = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());

            pstm.execute();

            try (ResultSet resultSet = pstm.getGeneratedKeys()) {
                while (resultSet.next()) {
                    produto.setId(resultSet.getLong(1));
                }
            }
        }
    }

    public List<Produto> listar() throws SQLException {

        List<Produto> produtos = new ArrayList<>();

        final String sql = "SELECT ID, NOME, DESCRICAO FROM produto";

        PreparedStatement stm = connection.prepareStatement(sql);

        stm.execute();

        ResultSet resultSet = stm.getResultSet();

        while (resultSet.next()) {
            final Long id = resultSet.getLong("id");
            final String nome = resultSet.getString("nome");
            final String descricao = resultSet.getString("descricao");

            produtos.add(new Produto(id, nome, descricao));
        }

        return produtos;
    }
}
