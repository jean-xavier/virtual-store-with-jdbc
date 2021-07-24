import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private final DataSource dataSource;

    private final String uri = "jdbc:mysql://localhost:3306/loja_virtual?useTimezone=true&serverTimezone=UTC";
    private final String user = "";
    private final String password = "";

    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(uri);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        comboPooledDataSource.setMaxPoolSize(15);

        this.dataSource = comboPooledDataSource;
    }

    public Connection getConexao() throws SQLException {
        return this.dataSource.getConnection();
    }
}
