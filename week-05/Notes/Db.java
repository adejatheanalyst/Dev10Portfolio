import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Db {

//    private DataSource initDataSource() {
//        DataSource dataSource = new MysqlDataSource();
//        // connection string is:
//        // [db-tech]:[db-vendor]://[host]:[port]/[database-name]
//        dataSource.setUrl("jdbc:mysql://localhost:3306/explore_venus");
//        dataSource.setUser("your-username-here");
//        dataSource.setPassword("your-password-here");
//
//        return dataSource;
//    }


    // Ask the DataSource for a Connection.
//try(
//    Connection conn = dataSource.getConnection())
//
//    {
//        // TODO: select, insert, update, or delete
//    } catch(
//    SQLException ex)
//
//    {
//        // SQL classes have many checked exceptions.
//        ex.printStackTrace();
//    }

//
//    ArrayList<Pet> result = new ArrayList<>();
//
//    final String sql = "select pet_id, `name`, `type` from pet;";
//
//try(
//    Connection conn = dataSource.getConnection();
//    Statement statement = conn.createStatement();
//    // Statement executes a SQL query
//    ResultSet rs = statement.executeQuery(sql))
//
//    {
//
//        // Process a row at a time until there are no more.
//        while (rs.next()) {
//            Pet pet = new Pet();
//            // Column values are for the current row.
//            pet.setPetId(rs.getInt("pet_id"));
//            pet.setName(rs.getString("name"));
//            pet.setType(rs.getString("type"));
//            result.add(pet);
//        }
//
//    } catch(
//    SQLException ex)
//
//    {
//        ex.printStackTrace();
//    }
//
//return result;
//}
