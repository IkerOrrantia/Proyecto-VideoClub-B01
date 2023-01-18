package ventanas;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import bd.BD;
import clases.Alquilar;
import clases.Pelicula;
import clases.Serie;
import ventanas.VentanaGestionPeliculas.MultiLineCellRenderer;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VentanaAlquilar extends JFrame implements ActionListener {

	private DefaultListModel<Pelicula> mPeliculas = new DefaultListModel<>();
	private JList<Pelicula> lPeliculas = new JList<>(mPeliculas);
	private DefaultListModel<Serie> mSeries = new DefaultListModel<>();
	private JList<Serie> lSeries = new JList<>(mSeries);
	private BD BD;
	private JPanel panelCatalogo;
	private JTextField campoBuscadorP;
	private JTextField campoBuscadorS;
	private JTable tablaAlquiler;
	private DefaultTableModel modeloDatos;
	private ArrayList<Pelicula> tablePeli = new ArrayList<Pelicula>();
	private JLabel foto = new JLabel();
	private JButton bAlquilar = new JButton( "Alquilar producto" );
	private Pelicula ultimaselecP;
	private Serie ultimaselecS;
	private TableRowSorter<DefaultTableModel> sorter;
	private boolean Pselect = false;

	private void initTables (){
		// creamos modelo de datos
		Vector<String> cabecera = new Vector<String>(Arrays.asList("PRODUCTO", "FECHA ALQUILER", "FECHA DEVOLUCION", "ESTADO"));
		this.modeloDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);
		// se crea tabla utilizado el modelo
		this.tablaAlquiler = new JTable(this.modeloDatos);
		this.tablaAlquiler.setAutoCreateRowSorter(true);
		tablaAlquiler.setFont(tablaAlquiler.getFont().deriveFont(12f));
		
		tablaAlquiler.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaAlquiler.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaAlquiler.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaAlquiler.getColumnModel().getColumn(3).setPreferredWidth(100);
		
		// modificamos para seleccionar una fila
		this.tablaAlquiler.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Crea un TableRowSorter y asigna el modelo de datos de tu JTable
		sorter = new TableRowSorter<>(modeloDatos);
		tablaAlquiler.setRowSorter(sorter);

		// pasar render
		this.tablaAlquiler.setDefaultRenderer(Object.class, new MultiLineCellRenderer());	}


	private void loadTables (){
		this.tablaAlquiler.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.tablaAlquiler.setAutoCreateColumnsFromModel(true);
		tablaAlquiler.sizeColumnsToFit(-1);

		tablaAlquiler.setRowHeight(150);
		tablaAlquiler.setDefaultRenderer(Object.class, new MultiLineCellRenderer());
		tablaAlquiler.setPreferredSize(new Dimension(1000, 150));
		
		

	}
	public class MultiLineCellRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			JTextArea textArea = new JTextArea();
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setEditable(false);
			textArea.setText(table.getValueAt(row, column).toString());
			return textArea;
		}
	}




	public VentanaAlquilar() throws SQLException {

		// configuraci�n de la ventana
		setTitle("Alquilar");
		setBounds(700, 300, 600, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel pNorte = new JPanel(); // Panel norte
		//pNorte.add(  );
		getContentPane().add( pNorte, BorderLayout.NORTH );

		JPanel pPrincipal = new JPanel( new BorderLayout() ); // Panel central (tabla)
		pPrincipal.add( new JLabel( "Datos del Producto:" ), BorderLayout.NORTH );
		JScrollPane scrollPane = new JScrollPane(tablaAlquiler);
		pPrincipal.add(scrollPane, BorderLayout.CENTER);
		//		pPrincipal.add(new JScrollPane(foto) , BorderLayout.SOUTH);

		getContentPane().add( pPrincipal, BorderLayout.CENTER );

		JPanel pBotonera = new JPanel(); // Panel inferior (botonera)
		JButton botonCancelar = new JButton("Cancelar");
		JButton botonSeguirAlquiler = new JButton("Seguir Alquilando");
		JButton botonFinalizarAlquiler = new JButton("Finalizar Pedido");
		
		pBotonera.add(botonCancelar);
		pBotonera.add(botonSeguirAlquiler);
		pBotonera.add(botonFinalizarAlquiler);
		
		getContentPane().add( pBotonera, BorderLayout.SOUTH );
		
		
		
		// creacion de panel principal
		panelCatalogo = new JPanel();
		panelCatalogo.setLayout(new BorderLayout());

		//
		BD = new BD();
		try {
			BD.connect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				dispose();

			}

		});
		
		botonSeguirAlquiler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				setVisible(false); // Oculta la ventana actual
			}
		});
		
		botonFinalizarAlquiler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				setVisible(false);
			}
		});
		
		initTables();
		loadTables();

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(() -> loadTables(), 0, 200, TimeUnit.MILLISECONDS);

		// agregar componentes al panel principal
		panelCatalogo.add(new JScrollPane(tablaAlquiler), BorderLayout.CENTER);

		// agregar panel principal a la ventana
		add(panelCatalogo);
		
		
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
