package bd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import clases.Pelicula;

public class FicheroABaseDatos {

	private static DefaultListModel<Pelicula> mPelicula = new DefaultListModel<>();
	private static List<String> nPeliculas = new ArrayList();
	private static List<Pelicula> lPeliculas = new ArrayList<Pelicula>();


	public static void createTable() {

	}

	public static void main(String[] args) throws Exception {
		Class.forName("org.sqlite.JDBC");

		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Data/VideoClub.db")) {
			try (Statement stmte = conn.createStatement()) {
				stmte.executeUpdate("DROP TABLE IF EXISTS Series");
				stmte.executeUpdate("DROP TABLE IF EXISTS Productos");
				stmte.executeUpdate("CREATE TABLE Productos (id_producto INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, nombre	TEXT, director	TEXT, id_genero	INTEGER,  anyo	INTEGER, precio	INTEGER, cantidad	INTEGER, descripcion	TEXT, imagen TEXT)");
				stmte.executeUpdate("CREATE TABLE Series (id_serie INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, id_producto INT, nombre TEXT, director TEXT, id_genero INT, anyo INT, temporadas INT, precio DOUBLE, cantidad INT, descripcion TEXT, imagen TEXT)");				
				stmte.close();
			}

			Pelicula pelicula = null;
			try(PreparedStatement stmt = conn.prepareStatement("INSERT INTO Productos (id_producto, nombre, director, id_genero, anyo, precio, cantidad, descripcion, imagen) VALUES(?,?,?,?,?,?,?,?,?)")){
				try(PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Peliculas")){
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getInt("id_producto"), rs.getString("nombre"), rs.getString("director"), rs.getInt("id_genero"), rs.getInt("anyo"), rs.getDouble("precio"), rs.getInt("cantidad"), rs.getString("descripcion"), rs.getString("imagen"));

					//	stmt.setInt(1, pelicula.getId_producto());
						stmt.setString(2, pelicula.getNombre());
						stmt.setString(3, pelicula.getDirector());
						stmt.setInt(4, pelicula.getId_genero());
						stmt.setInt(5, pelicula.getAnyo());
						stmt.setDouble(6, pelicula.getPrecio());
						stmt.setInt(7, pelicula.getCantidad());
						stmt.setString(8, pelicula.getDescripcion());
						stmt.setString(9, pelicula.getImagen());
						stmt.executeUpdate();
					}
					pstmt.close();
					rs.close();
				}
				stmt.close();
			}

			try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Series (id_serie, id_producto, nombre, director, id_genero, anyo, temporadas, precio, cantidad, descripcion, imagen) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
				try (BufferedReader reader = new BufferedReader(new FileReader("Data/Series.csv"))) {

					String line = null;
					while ((line = reader.readLine()) != null) {
						String[] colupna = line.split(";");
						String nombre = colupna[2];
						String director = colupna[3];

						int id_serie = colupna[0].isEmpty() ? 0 : Integer.parseInt(colupna[0]);
						int id_producto = colupna[1].isEmpty() ? 0 : Integer.parseInt(colupna[1]);
						int id_genero = colupna[4].isEmpty() ? 0 : Integer.parseInt(colupna[4]);
						int anyo = colupna[5].isEmpty() ? 0 : Integer.parseInt(colupna[5]);
						int temporadas = colupna[6].isEmpty() ? 0 : Integer.parseInt(colupna[6]);
						double precio = colupna[7].isEmpty() ? 0 : Double.parseDouble(colupna[7]);
						int cantidad = colupna[8].isEmpty() ? 0 : Integer.parseInt(colupna[8]);
						String descripcion = colupna[9];
						String imagen = colupna[10];

						try {


							stmt.setInt(1, id_serie);
							stmt.setInt(2, id_producto);
							stmt.setString(3, nombre);
							stmt.setString(4, director);
							stmt.setInt(5, id_genero);
							stmt.setInt(6, anyo);
							stmt.setInt(7, temporadas);
							stmt.setDouble(8, precio);
							stmt.setInt(9, cantidad);
							stmt.setString(10, descripcion);
							stmt.setString(11, imagen);

							stmt.executeUpdate();
						} catch (DateTimeParseException e) {
							break;
						}
						try(PreparedStatement stmtp = conn.prepareStatement("INSERT INTO Productos (id_producto, nombre, director, id_genero, anyo, precio, cantidad, descripcion, imagen) VALUES(?,?,?,?,?,?,?,?,?)")){
							stmtp.setInt(1, id_producto);
							stmtp.setString(2, nombre);
							stmtp.setString(3, director);
							stmtp.setInt(4, id_genero);
							stmtp.setInt(5, anyo);
							stmtp.setDouble(6, precio);
							stmtp.setInt(7, cantidad);
							stmtp.setString(8, descripcion);
							stmtp.setString(9, imagen);

							stmtp.executeUpdate();
							stmtp.close();
						}
					}
				}
				stmt.close();
				conn.close();
			}

		}

	}

}


