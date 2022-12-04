package ventanas;

import java.awt.*;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import clases.*;
import ventanas.*;

public class VentanaPrincipal extends JFrame{
	private JPanel panelCatalogo;
	private JTextField campoBuscador;
	private JComboBox filtros;
	private JTable tablaProductos;

	public VentanaPrincipal() {
	    // configuraci�n de la ventana
	    setTitle("Cat�logo de productos");
	    setSize(600, 400);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    // creaci�n de panel principal
	    panelCatalogo = new JPanel();
	    panelCatalogo.setLayout(new BorderLayout());

	    // creaci�n de campo de b�squeda y filtros
	    JPanel panelFiltros = new JPanel();
	    panelFiltros.setLayout(new FlowLayout());

	    campoBuscador = new JTextField(20);
	    filtros = new JComboBox();
	    filtros.addItem("ID");
	    filtros.addItem("Titulo");
	    filtros.addItem("Director");
	    filtros.addItem("Precio");
	    filtros.addItem("Genero");
	    filtros.addItem("Anyo");
	    panelFiltros.add(campoBuscador);
	    panelFiltros.add(filtros);

	    // creaci�n de tabla de productos
	    String[] columnas = {"ID", "Titulo", "Director", "Genero", "Anyo", "Precio"};
	    String[][] datos = {
	        {"1", "Pelicula 1", "Director 1", "Genero 1", "2001", "10.00"},
	        {"2", "Pelicula 2", "Director 2", "Genero 2", "2002", "20.00"},
	        {"3", "Pelicula 3", "Director 3", "Genero 3", "2003", "30.00"},
	        {"4", "Pelicula 4", "Director 4", "Genero 4", "2004", "40.00"},
	        {"5", "Pelicula 5", "Director 5", "Genero 5", "2005", "50.00"}
	    };
	    tablaProductos = new JTable(datos, columnas);

	    // agregar componentes al panel principal
	    panelCatalogo.add(panelFiltros, BorderLayout.NORTH);
	    panelCatalogo.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

	    // agregar panel principal a la ventana
	    add(panelCatalogo);
	    
	}
}
