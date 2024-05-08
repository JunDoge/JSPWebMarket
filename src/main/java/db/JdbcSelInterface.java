package db;


import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcSelInterface {
	int executeQuery(ResultSet rs) throws SQLException;

}
