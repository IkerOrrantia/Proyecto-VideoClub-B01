package bd;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.JOptionPane;

import clases.*;
import ventanas.*;
import means.*;

public class BD {
	private Connection conn; // conexión a la base de datos

	/**
	 * M�todo para conectar a la base de datos.
	 * 
	 * @throws ClassNotFoundException si se produce un error al cargar el driver de
	 *                                base de datos.
	 * @throws SQLException           si se produce un error al establecer la
	 *                                conexión con la base de datos.
	 */
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");

		conn = DriverManager.getConnection("jdbc:sqlite:data/VideoClub.db");
	}

	/**
	 * Cierra la conexión a la base de datos.
	 * 
	 * @throws SQLException si se produce un error al intentar cerrar la conexión a
	 *                      la base de datos.
	 */
	public void disconnect() throws SQLException {
		conn.close();
	}

	// Exportar datos de registro
	public void exportCuentaToDataBase(Cuenta cuenta) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO Usuario (usuario, nombre, apellidos, dni, correo, contrasenya, telefono, direccion, conexion, peliculasAlquiladas, rol) Values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
			// stmt.setInt(1, 0);
			stmt.setString(1, cuenta.getUsuario());
			stmt.setString(2, cuenta.getNombre());
			stmt.setString(3, cuenta.getApellidos());
			stmt.setString(4, cuenta.getDNI());
			stmt.setString(5, cuenta.getCorreo());
			stmt.setString(6, cuenta.getContrasenya());
			stmt.setInt(7, cuenta.getTelefono());
			stmt.setString(8, cuenta.getDireccion());
			stmt.setString(9, cuenta.getConexion());
			stmt.setInt(10, 0);
			stmt.setInt(11, cuenta.getRol());
			stmt.executeUpdate();
		}
	}

	// Exportar datos de registro referentes a la tabla de clientes
	public void exportarClienteToDataBase(Cliente cliente) throws SQLException {
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Cliente Values(?, ?, ?)")) {
			// stmt.setInt(1, cliente.getId_Cliente());
			stmt.setString(2, cliente.getUsuario());
			stmt.setInt(3, 0);
			stmt.executeUpdate();
		}

	}

	// Importar los datos de los clientes para el inicio de sesion
	public List<Cuenta> importarCuentaToDataBase() throws SQLException {
		List<Cuenta> cuentas = new ArrayList<>();
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM Usuario");
			while (rs.next()) {
				Cuenta cuenta = new Cuenta(rs.getInt("id_usuario"), rs.getString("usuario"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("dni"), rs.getString("correo"),
						rs.getString("contrasenya"), rs.getInt("telefono"), rs.getString("direccion"),
						rs.getString("conexion"), rs.getInt("peliculasAlquiladas"), rs.getInt("rol"));
				cuentas.add(cuenta);
			}
		}
		return cuentas;
	}

	public Cuenta loggin(String usuario, String contrasenya) throws SQLException {
		Cuenta cuenta = null;
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Usuario WHERE usuario = ? AND contrasenya = ?")){
				pstmt.setString(1, usuario);
				pstmt.setString(2, contrasenya);
				ResultSet rsLog = pstmt.executeQuery();
				if (rsLog.next()) {
				  cuenta = new Cuenta(rsLog.getInt(1), rsLog.getString(2), rsLog.getString(3), rsLog.getString(4),
				      rsLog.getString(5), rsLog.getString(6), rsLog.getString(7), rsLog.getInt(8), rsLog.getString(9),
				      rsLog.getString(10), rsLog.getInt(11), rsLog.getInt(12));
				}
				pstmt.close();
				rsLog.close();
		}
		return cuenta;
	}

}