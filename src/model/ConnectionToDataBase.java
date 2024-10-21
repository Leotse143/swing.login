package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDataBase {

	private static final String url = "jdbc:postgresql://localhost:5432/";
	private static final String user = "";
	private static final String password = "";

	public static Connection conectar() throws SQLException { //MMETODO PA CONECTAR A LA BASE DE DATOS
		return DriverManager.getConnection(url, user, password);

	}
}