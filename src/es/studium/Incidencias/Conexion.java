package es.studium.Incidencias;

import java.awt.Choice;
import java.awt.TextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/incidencias";
	String login = "userIncidencias";
	String password = "Studium2023;";
	String sentencia = "";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;

	Conexion()
	{
		connection = this.conectar();
	}

	public Connection conectar()
	{
		try
		{
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			return(DriverManager.getConnection(url, login, password));
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-"+cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return null;
	}

	public int comprobarCredenciales(String u, String c)
	{
		String cadena = "SELECT * FROM usuarios WHERE nombreUsuario = '"+ u + "' AND claveUsuario = SHA2('" + c + "',256);";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			rs = statement.executeQuery(cadena);
			if(rs.next())
			{
				return rs.getInt("tipoUsuario");
			}
			else
			{
				return -1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 3-"+sqle.getMessage());
		}
		return -1;
	}

	public int altaUsuario(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 4-"+sqle.getMessage());
			return 1;
		}
	}

	public void rellenarListadoUsuarios(TextArea txaListado)
	{
		String sentencia = "SELECT idUsuario, nombreUsuario, correoElectronicoUsuario FROM usuarios;";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultado = statement.executeQuery(sentencia);
			while(resultado.next())
			{
				txaListado.append(resultado.getString("idUsuario")+" ");
				txaListado.append(resultado.getString("nombreUsuario")+" ");
				txaListado.append(resultado.getString("correoElectronicoUsuario")+"\n");

			}

		}
		catch (SQLException sqle)
		{
			System.out.println("Error 5-"+sqle.getMessage());
		}
	}

	public void rellenarChoiceUsuarios(Choice choUsuarios)
	{
		String sentencia = "SELECT idUsuario, nombreUsuario FROM usuarios ORDER BY 1;";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultado = statement.executeQuery(sentencia);
			choUsuarios.add("Elegir usuario...");
			while(resultado.next())
			{
				choUsuarios.add(resultado.getString("idUsuario")+"-"+resultado.getString("nombreUsuario"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 6-"+sqle.getMessage());
		}


	}

	public int eliminarUsuario(String idUsuario)
	{
		String sentencia = "DELETE FROM usuarios WHERE idUsuario = " + idUsuario;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 7-"+sqle.getMessage());
			return 1;
		}
	}

	public String getDatosEdicion(String idUsuario)
	{
		String resultado = "";
		String sentencia = "SELECT * FROM usuarios WHERE idUsuario = " + idUsuario;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultSet = statement.executeQuery(sentencia);
			resultSet.next();
			resultado =(resultSet.getString("idUsuario")+"-"+resultSet.getString("nombreUsuario")+"-"+resultSet.getString("claveUsuario")+"-"+resultSet.getString("correoElectronicoUsuario"));
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 8-"+sqle.getMessage());
		}
		return resultado;
	}

	public int modificarUsuario(String sentencia)
	{
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 9-"+sqle.getMessage());
			return 1;
		}
	}
}
