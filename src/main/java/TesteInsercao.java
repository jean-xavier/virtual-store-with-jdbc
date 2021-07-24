import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsercao {
    public static void main(String[] args) throws SQLException {
        Connection connection = new ConnectionFactory().getConexao();

        Statement stm = connection.createStatement();

        stm.execute("INSERT INTO produto(nome, descricao) VALUES ('Mouse', 'Mouse sem fio')", Statement.RETURN_GENERATED_KEYS);

        ResultSet rts = stm.getGeneratedKeys();

        while (rts.next()) {
            final int id = rts.getInt(1);
            System.out.println("ID -> " + id);
        }

        connection.close();
    }
}
