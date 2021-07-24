import java.sql.*;

public class TestaInsercaoComParametro {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = new ConnectionFactory().getConexao()) {
            connection.setAutoCommit(false);
            final String sql = "INSERT INTO produto(nome, descricao) VALUES (?, ?)";

            try (PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                adicionarVariavel("Mouse", "Mouse sem fio", stm);
                adicionarVariavel("Teclado", "Teclado sem fio", stm);
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Rollback executado");
                connection.rollback();
            }
        }
    }

    private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
        stm.setString(1, nome);
        stm.setString(2, descricao);

        stm.execute();

        try (ResultSet rts = stm.getGeneratedKeys()) {
            while (rts.next()) {
                final int id = rts.getInt(1);
                System.out.println("ID -> " + id);
            }
        }
    }
}
