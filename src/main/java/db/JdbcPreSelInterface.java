package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcPreSelInterface {
	void prepared(PreparedStatement ps) throws SQLException;
	int executeQuery(ResultSet rs) throws SQLException;

}
