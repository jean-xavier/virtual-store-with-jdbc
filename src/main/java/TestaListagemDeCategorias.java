import dao.CategoriaDAO;
import model.Categoria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaListagemDeCategorias {
    public static void main(String[] args) {

        try (Connection connection = new ConnectionFactory().getConexao()) {
            final CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
            final List<Categoria> categorias = categoriaDAO.listarComProdutos();
            categorias.forEach(categoria -> {
                System.out.println("Categoria -> " + categoria.getNome());
                System.out.println("Produtos:");
                categoria.getProdutos().forEach(produto -> System.out.println(produto.getNome()));
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
