package bd;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import javax.swing.JOptionPane;

import org.hamcrest.core.StringContains;

import clases.*;
import ventanas.*;

public class BD {
	private Connection conn; // conexi贸n a la base de datos
	private String nombreUsuarioOn = null;
	private int Npedidos = 0;

	/**
	 * M锟絫odo para conectar a la base de datos.
	 * 
	 * @throws ClassNotFoundException si se produce un error al cargar el driver de
	 *                                base de datos.
	 * @throws SQLException           si se produce un error al establecer la
	 *                                conexi贸n con la base de datos.
	 */
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");

		conn = DriverManager.getConnection("jdbc:sqlite:data/VideoClub.db");
	}

	/**
	 * Cierra la conexi贸n a la base de datos.
	 * 
	 * @throws SQLException si se produce un error al intentar cerrar la conexi贸n a
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

	public void borrarCuentaExportada(Cuenta cuenta) throws SQLException{
		try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM Usuario WHERE usuario = ?")) {
			stmt.setString(1, cuenta.getUsuario());
			stmt.executeUpdate();
			stmt.close();

		}
	}

	public void borrarClienteExportado(Cliente cliente) throws SQLException {
		// Borrar datos a馻didos en la base de datos despu閟 de ejecutar cada test
		try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM Cliente WHERE usuario = ?")) {
			stmt.setString(1, cliente.getUsuario());
			stmt.executeUpdate();
			stmt.close();
		}


	}

	// Importar los datos de los clientes 
	public List<Cuenta> importarCuentaFromDataBase() throws SQLException {
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
	public List<Cliente> importarClienteFromDataBase() throws SQLException { 
		List<Cliente> clientes = new ArrayList<>();
		try(Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM Cliente");
			while (rs.next()) {
				Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("usuario"), rs.getInt("num_pedidos"));
				clientes.add(cliente);
			}
		}
		return clientes;
	}
	
	public int importarIdCliente() throws SQLException{
		int id_cliente = 0;
		try(PreparedStatement stmt = conn.prepareStatement("SELECT id_cliente,num_pedidos FROM Cliente WHERE usuario = ?")){
			stmt.setString(1, nombreUsuarioOn);
			ResultSet rs = stmt.executeQuery();
			id_cliente = rs.getInt("id_cliente");
			Npedidos = rs.getInt("num_pedidos");
		}
		return id_cliente;
	}
	// Metodo para comprobar en la base de datos si los datos son correctos 
	public Cuenta loggin(String usuario, String contrasenya) throws SQLException {
		Cuenta cuenta = null;
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Usuario WHERE usuario = ? AND contrasenya = ?")){
			pstmt.setString(1, usuario);
			pstmt.setString(2, contrasenya);
			nombreUsuarioOn = usuario;
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
	//Cambiamos el valor de id de los generos a un valor string con su nombre
	public Genero importarNombreGenero(int id_genero) throws SQLException {
		Genero genero = null;
		try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Genero WHERE id_genero = ?")){
			stmt.setInt(1, id_genero);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				genero = new Genero(id_genero, rs.getString("nombre_genero"), rs.getString("descripcion"));
			}
			stmt.close();
			rs.close();
		}
		return genero;

	}


	//importamos todos los nombres de las peliculas y los guardamos en una lista
	public List<String> importarNombresPelicula() throws SQLException {
		List<String> NPelicula = new ArrayList<>();
		try(PreparedStatement pstmt = conn.prepareStatement("SELECT nombre FROM Peliculas")){
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				NPelicula.add(rs.getString("nombre"));
			}
			pstmt.close();
			rs.close();
		}
		return NPelicula;
	}

	//importamos todos los nombres de las series y los guardamos en una lista
	public List<String> importarNombresSerie() throws SQLException {
		List<String> NSerie = new ArrayList<>();
		try(PreparedStatement pstmt = conn.prepareStatement("SELECT nombre FROM Series")){
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				NSerie.add(rs.getString("nombre"));
			}
			pstmt.close();
			rs.close();
		}
		return NSerie;
	}

	//Importamos datos de una pelicula en concreto
	public Pelicula importarDatosPelicula(String nombre) throws SQLException {
		Pelicula pelicula = null;
		try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Peliculas WHERE nombre = ?")){
			pstmt.setString(1, nombre);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getInt("id_producto"), rs.getString("nombre"), rs.getString("director"), rs.getInt("id_genero"), rs.getInt("anyo"), rs.getDouble("precio"), rs.getInt("cantidad"), rs.getString("descripcion"), rs.getString("imagen"));			}
			pstmt.close();
			rs.close();
		}
		return pelicula;
	}

	//Importamos datos de una serie en concreto
	public Serie importarDatosSerie(String nombre) throws SQLException {
		Serie serie = null;
		try(PreparedStatement pstmt = conn.prepareStatement("SELECT * From Series WHERE nombre = ?")){
			pstmt.setString(1, nombre);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				serie = new Serie(rs.getInt(1), rs.getInt(2) , rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getDouble(8), rs.getInt(9), rs.getString(10), rs.getString(11));
			}
			pstmt.close();
			rs.close();
		}
		return serie;
	}

	public void modificarCantidadPeliculas(String nombre, int cantidad) throws SQLException{
		try(PreparedStatement stmt = conn.prepareStatement("UPDATE Peliculas SET cantidad = ? WHERE nombre = ?")){
			stmt.setInt(1, cantidad);
			stmt.setString(2, nombre);
			stmt.executeUpdate();
			stmt.close();
		}

	}

	public void modificarCantidadSeries(String nombre, int cantidad) throws SQLException{
		try(PreparedStatement stmt = conn.prepareStatement("UPDATE Series SET cantidad = ? WHERE nombre = ?")){
			stmt.setInt(1, cantidad);
			stmt.setString(2, nombre);
			stmt.executeUpdate();
			stmt.close();
		}

	}

	public void cargarDatosSeriesEnFichero(String rutaFichero) throws SQLException{
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Series")) {
			ResultSet rs = pstmt.executeQuery();
			try (FileWriter fw = new FileWriter(rutaFichero)) {
				while (rs.next()) {
					fw.write(rs.getInt("id_serie") + ";");
					fw.write(rs.getInt("id_producto") + ";");
					fw.write(rs.getString("nombre") + ";");
					fw.write(rs.getString("director") + ";");
					fw.write(rs.getInt("id_genero") + ";");
					fw.write(rs.getInt("anyo") + ";");
					fw.write(rs.getInt("temporadas") + ";");
					fw.write(rs.getDouble("precio") + ";");
					fw.write(rs.getInt("cantidad") + ";");
					fw.write(rs.getString("descripcion") + ";");
					fw.write(rs.getString("imagen") + "\n");
				}
				fw.close();
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void importarAlquilarBD(Alquilar alquiler) throws SQLException{
		int id_cliente = 0;
		try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Alquiler (id_cliente, id_producto, fecha_alquiler, fecha_devolucion, estado) VALUES ()")){
			id_cliente = importarIdCliente();
			stmt.setInt(1, id_cliente);
			stmt.setInt(2, alquiler.getId_producto());
			stmt.setString(3, alquiler.getFecha_alquiler());
			stmt.setString(4, alquiler.getFecha_devolucion());
			stmt.setString(5, alquiler.getEstado().toString());
			stmt.executeUpdate();
			try(PreparedStatement ustmt = conn.prepareStatement("UPDATE Cliente SET num_pedidos = ? WHERE usuario = ?")){
				ustmt.setInt(1, Npedidos+1);
				ustmt.setString(2, nombreUsuarioOn);
				ustmt.executeUpdate();
				ustmt.close();
			}
			try(PreparedStatement ustmt = conn.prepareStatement("UPDATE Usuario SET peliculasAlquiladas = ? WHERE usuario = ?")){
				ustmt.setInt(1, Npedidos+1);
				ustmt.setString(2, nombreUsuarioOn);
				ustmt.executeUpdate();
				ustmt.close();
			}
			
			stmt.close();
		}
		
	}
}