package ventanas;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bd.BD;
import clases.Cuenta;
import clases.Pelicula;

public class VentanaGestionPeliculas extends JFrame{

	private JTable tablaPeliculas;
	private DefaultTableModel modeloTabla;
	private JButton btnEliminar;
	private List<Pelicula> listaPeliculas;
	private BD bd;
	private Pelicula peliculaAnyadir;
	// FALTA TRAER LOS DATOS DE USUARIOS DE LA BASE DE DATOS
	public VentanaGestionPeliculas() throws SQLException{
		// Configura la ventana
		setTitle("Ventana Gestión de Usuarios");
		setBounds(525, 250, 900, 600); // (Posicion x, Posicion Y, Anchura, Altura)
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Crea el modelo de la tabla
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("ID");
		modeloTabla.addColumn("Nombre");
		modeloTabla.addColumn("Director");
		modeloTabla.addColumn("Genero");
		modeloTabla.addColumn("Anyo");
		modeloTabla.addColumn("Precio");
		modeloTabla.addColumn("Cantidad");
		modeloTabla.addColumn("Descripcion");

		// Crea la tabla
		tablaPeliculas = new JTable(modeloTabla);

		// Agrega la tabla a un scroll pane y lo agrega a la ventana
		JScrollPane scrollPane = new JScrollPane(tablaPeliculas);
		add(scrollPane);

	
		// Carga los datos de la base de datos en la tabla

		setVisible(true);
		bd = new BD();
		try {
			bd.connect();
		       List<String> nPeliculas = bd.importarNombresPelicula();
	            for(String s : nPeliculas) {
	                listaPeliculas.add(bd.importarDatosPelicula(s));
	            }
			for (Pelicula p : listaPeliculas) {
				modeloTabla.addRow(new Object[] {
						p.getId(),
						p.getNombre(),
						p.getDirector(),
						p.getId_genero(),
						p.getAnyo(),
						p.getPrecio(),
						p.getCantidad(),
						p.getDescripcion()
				});
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		// Agrega cada fila de resultados al modelo de la tabla
	}
}

