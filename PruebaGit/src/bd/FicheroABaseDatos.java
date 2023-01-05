package bd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class FicheroABaseDatos {




	public static void createTable() {

	}

	public static void main(String[] args) throws Exception {
		Class.forName("org.sqlite.JDBC");

		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:Data/VideoClub.db")) {
			try (Statement stmt = conn.createStatement()) {
				stmt.executeUpdate("DROP TABLE IF EXISTS Series");
				stmt.executeUpdate("CREATE TABLE Series (id_serie INT NOT NULL UNIQUE, nombre TEXT, creador TEXT, anyo INT, temporadas INT, id_genero INT, precio DOUBLE, cantidad INT, descripcion TEXT)");				
			}

			try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO Series (id_serie, nombre, creador, anyo, temporadas, id_genero, precio, cantidad, descripcion) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
				try (BufferedReader reader = new BufferedReader(new FileReader("Data/Series.csv"))) {
					reader.readLine();
					reader.readLine();
					

					String line = null;
					while ((line = reader.readLine()) != null) {
						String[] colupna = line.split(";");
						String nombre = colupna[2];
						String creador = colupna[3];
						

						try {
							int id_serie = colupna[1].isEmpty() ? 0 : Integer.parseInt(colupna[1]);
							int anyo = colupna[4].isEmpty() ? 0 : Integer.parseInt(colupna[4]);
							int temporadas = colupna[5].isEmpty() ? 0 : Integer.parseInt(colupna[5]);
							int id_genero = colupna[6].isEmpty() ? 0 : Integer.parseInt(colupna[6]);
							double precio = colupna[7].isEmpty() ? 0 : Double.parseDouble(colupna[7]);
							int cantidad = colupna[8].isEmpty() ? 0 : Integer.parseInt(colupna[8]);
							String descripcion = colupna[9];

							stmt.setInt(1, id_serie);
							stmt.setString(2, nombre);
							stmt.setString(3, creador);
							stmt.setInt(4, anyo);
							stmt.setInt(5, temporadas);
							stmt.setInt(6, id_genero);
							stmt.setDouble(7, precio);
							stmt.setInt(8, cantidad);
							stmt.setString(9, descripcion);
							
							stmt.executeUpdate();
						} catch (DateTimeParseException e) {
							break;
						}
					}
				}
				stmt.close();
				conn.close();
			}
		}
	}

}


