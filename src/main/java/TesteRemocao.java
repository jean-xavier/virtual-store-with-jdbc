import java.sql.*;

public class TesteRemocao {
    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionFactory().getConexao();

        final String sql = "DELETE FROM produto WHERE id > ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, 2);

        stm.execute();

        int count = stm.getUpdateCount();

        System.out.println("Registro apagados -> " + count);

        connection.close();
    }
}
