import dao.ProdutoDAO;
import model.Produto;

import java.sql.*;
import java.util.List;

public class TestaInsercaoListagemProduto {
    public static void main(String... args) {
        Produto comoda = new Produto("Cômoda", "Cômoda Vertical");

        try (Connection connection = new ConnectionFactory().getConexao()) {
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            produtoDAO.salvar(comoda);
            List<Produto> produtos = produtoDAO.listar();
            produtos.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(comoda);
    }
}
