import java.sql.*;

public class TestaListagem {
    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionFactory().getConexao();

        final String sql = "SELECT ID, NOME, DESCRICAO FROM produto";

        PreparedStatement stm = connection.prepareStatement(sql);

        stm.execute();

        ResultSet resultSet = stm.getResultSet();

        while (resultSet.next()) {
            final Integer id = resultSet.getInt("id");
            final String nome = resultSet.getString("nome");
            final String descricao = resultSet.getString("descricao");

            System.out.println(id + " | " + nome + " | " + descricao);
        }


        connection.close();
    }
}
