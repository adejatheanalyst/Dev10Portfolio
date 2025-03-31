package learn.foraging;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;

public class DataHelper {
    public static DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        // These are environment variables.
        dataSource.setUrl(System.getenv("DB_URL"));
        dataSource.setUser(System.getenv("DB_USERNAME"));
        dataSource.setPassword(System.getenv("DB_PASSWORD"));
        return dataSource;
    }

    public static JdbcClient getJdbcClient() {
        return JdbcClient.create(getDataSource());
    }
}
