package ventanas;

import java.awt.*;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Vector;
import java.util.spi.CurrencyNameProvider;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import bd.BD;
import clases.*;
import ventanas.*;

public class VentanaPrincipal extends JFrame{
	private JPanel panelCatalogo;
	private JTextField campoBuscador;
	private JComboBox filtros;
	private JTable tablaProductos;
	private DefaultTableModel modeloDatos;
	private ArrayList<Pelicula> tablePeli = new ArrayList<Pelicula>();

	TableRowSorter<DefaultTableModel> sorter;
	
	private void initTables (){
		Vector<String> cabecera = new Vector<String>(Arrays.asList("ID", "TITULO", "DIRECTOR", "GENERO", "ANYO", "PRECIO"));
		// creamos modelo de datos
		this.modeloDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);
		// se crea tabla utilizado el modelo
		this.tablaProductos = new JTable(this.modeloDatos);
		this.tablaProductos.setAutoCreateRowSorter(true);
		// modificamos para seleccionar una fila
		this.tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sorter = new TableRowSorter<>(this.modeloDatos);
		// pasar render
		this.tablaProductos.setDefaultRenderer(Object.class, null);
	}
	
	private void loadTables (){
		// borrar datos
		this.modeloDatos.setRowCount(0);
		// añadir fila por peli
		for (Pelicula p : this.tablePeli) {
			this.modeloDatos.addRow(new Object[] {p.getId(), p.getNombre(), p.getDirector(), p.getGenero(),p.getAnyo(),p.getPrecio()});
		}
	}
	
	public void filter() {
		try {
			sorter.setRowFilter(RowFilter.regexFilter("?i");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public VentanaPrincipal() {
		
	    // configuraci�n de la ventana
	    setTitle("Catalogo de productos");
	    setSize(600, 400);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    // creacion de panel principal
	    panelCatalogo = new JPanel();
	    panelCatalogo.setLayout(new BorderLayout());

	    // creacion de campo de busqueda y filtros
	    JPanel panelFiltros = new JPanel();
	    panelFiltros.setLayout(new FlowLayout());

	    campoBuscador = new JTextField(20);
	    filtros = new JComboBox();
	    JButton aceptarBusqueda = new JButton("Filtrar");
	    
	    filtros.addItem("ID");
	    filtros.addItem("Titulo");
	    filtros.addItem("Director");
	    filtros.addItem("Precio");
	    filtros.addItem("Genero");
	    filtros.addItem("Anyo");
	    
	    panelFiltros.add(filtros);
	    panelFiltros.add(campoBuscador);
	    panelFiltros.add(aceptarBusqueda);

	    
	    // creacion de tabla de productos
	    String[] columnas = {"ID", "Titulo", "Director", "Genero", "Anyo", "Precio"};
	    String[][] datos = {
	        {"1", "Pelicula 1", "Director 1", "Genero 1", "2001", "10.00"},
	        {"2", "Pelicula 2", "Director 2", "Genero 2", "2002", "20.00"},
	        {"3", "Pelicula 3", "Director 3", "Genero 3", "2003", "30.00"},
	        {"4", "Pelicula 4", "Director 4", "Genero 4", "2004", "40.00"},
	        {"5", "Pelicula 5", "Director 5", "Genero 5", "2005", "50.00"}
	    };
	    // creacion de tabla de productos
	    // Establecemos los detalles de la conexion a la base de datos
	    String url = "data/Peliculas.db";

	    try {
	      // establecemos la conexion a la base de datos
	      java.sql.Statement st = BD.initBD("data/Peliculas.db");

	      // creamos una consulta SQL para obtener los datos para transferir
	      String query = "SELECT id, titulo, director, genero, anyo, precio FROM VideoClub";
	      // java.sql.Statement stmt = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      // iteramos sobre los resultados de la consulta y los transferimos a la tabla
	      while (rs.next()) {
	        int id = rs.getInt("ID");
	        String titulo = rs.getString("Titulo");
	        String director = rs.getString("Director");
	        String genero = rs.getString("Genero");
	        Genero generoP = Genero.ACCION;
	        int anyo = rs.getInt("Anyo");
	        double precio = rs.getDouble("Precio");
	        
	        switch (genero) {
			case "ANIMACION":
				generoP = Genero.ANIMACION;
				
				break
			;
			case "TERROR":
				generoP = Genero.TERROR;
				break
			;
			case "ROMANTICA":
				generoP = Genero.ROMANTICA;
				break
			;
			case "ACCION":
				generoP = Genero.ACCION;
				break
			;
			case "CIENCIAFICCION":
				generoP = Genero.CIENCIAFICCION;
				break
			;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + rs.getString("Genero"));
			}
	        
	        Pelicula p = new Pelicula(id, titulo, director, generoP, anyo, precio);
	        // codigo para insertar estos datos en la tabla
	        tablePeli.add(p);
	        
	      }

	      // cerramos la conexion
	      st.close();
	    } catch (SQLException ex) {
	      // en caso de error, imprimimos un mensaje de error en la consola
	      System.out.println("Error al transferir los datos: " + ex.getMessage());
	    }
	  
	    tablaProductos = new JTable(tablePeli); //REVISA ESTO

	    // agregar componentes al panel principal
	    panelCatalogo.add(panelFiltros, BorderLayout.NORTH);
	    panelCatalogo.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

	    // agregar panel principal a la ventana
	    add(panelCatalogo);
	    
	}
}
