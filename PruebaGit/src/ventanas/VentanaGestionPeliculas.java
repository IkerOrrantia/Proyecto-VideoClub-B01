package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import bd.BD;
import clases.Cuenta;
import clases.Pelicula;
import clases.Serie;
import ventanas.VentanaPrincipal.MultiLineCellRenderer;

public class VentanaGestionPeliculas extends JFrame{
	private DefaultListModel<Pelicula> mPeliculas = new DefaultListModel<>();
	private JList<Pelicula> lPeliculas = new JList<>(mPeliculas);
	private BD BD;
	private JPanel panelCatalogo;
	private JTextField campoBuscadorP;
	private JTable tablaProductos;
	private DefaultTableModel modeloDatos;
	private ArrayList<Pelicula> tablePeli = new ArrayList<Pelicula>();
	private JLabelAjustado lFoto = new JLabelAjustado( null );
	private JButton bAumentarStock = new JButton( "Aumentar Stock" );
	private Pelicula ultimaselec;
	private TableRowSorter<DefaultTableModel> sorter;

	private void initTables (){
		Vector<String> cabecera = new Vector<String>(Arrays.asList("NOMBRE", "DIRECTOR", "GENERO", "ANYO", "PRECIO", "CANTIDAD", "DESCRIPCION"));
		// creamos modelo de datos
		this.modeloDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);
		// se crea tabla utilizado el modelo
		this.tablaProductos = new JTable(this.modeloDatos);
		tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(75);
		tablaProductos.getColumnModel().getColumn(2).setPreferredWidth(50);
		tablaProductos.getColumnModel().getColumn(3).setPreferredWidth(50);
		tablaProductos.getColumnModel().getColumn(4).setPreferredWidth(50);
		tablaProductos.getColumnModel().getColumn(5).setPreferredWidth(50);
		tablaProductos.getColumnModel().getColumn(6).setPreferredWidth(300);
		this.tablaProductos.setAutoCreateRowSorter(true);
		tablaProductos.setFont(tablaProductos.getFont().deriveFont(12f));


		// modificamos para seleccionar una fila
		this.tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Crea un TableRowSorter y asigna el modelo de datos de tu JTable
		sorter = new TableRowSorter<>(modeloDatos);
		tablaProductos.setRowSorter(sorter);



		// Crea un RowFilter utilizando el texto del campo de busqueda
		RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(campoBuscadorP.getText(), 1);

		// Actualiza el filtro del TableRowSorter
		sorter.setRowFilter(filter);
		// pasar render
		this.tablaProductos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());

		
	}


	private void loadTables (){
		this.tablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.tablaProductos.setAutoCreateColumnsFromModel(true);
		tablaProductos.sizeColumnsToFit(-1);

		tablaProductos.setRowHeight(150);
		tablaProductos.setDefaultRenderer(Object.class, new MultiLineCellRenderer());
		tablaProductos.setPreferredSize(new Dimension(1000, 150));
		
		// borrar datos
		//this.modeloDatos.setRowCount(0);
		// a�adir fila por peli
		//		for (Pelicula p : this.tablePeli) {
		//			this.modeloDatos.addRow(new Object[] { p.getNombre(), p.getDirector(), p.getId_genero(), p.getAnyo(), p.getPrecio(), p.getCantidad(), p.getDescripcion(), p.getImagen()});
		//		}
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




	public VentanaGestionPeliculas() throws SQLException {

		// configuracion de la ventana
		setTitle("Gestion Peliculas");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel pNorte = new JPanel(); // Panel norte
		//pNorte.add(  );
		getContentPane().add( pNorte, BorderLayout.NORTH );

		JSplitPane pOeste = new JSplitPane( JSplitPane.VERTICAL_SPLIT ); // Listas oeste

		JPanel pPeliculas = new JPanel( new BorderLayout() );

		pPeliculas.add( new JLabel( "Peliculas:" ), BorderLayout.NORTH );
		pPeliculas.add( new JScrollPane(lPeliculas), BorderLayout.CENTER );
		pOeste.setTopComponent( pPeliculas );
		getContentPane().add( pOeste, BorderLayout.WEST ); 

		JPanel pPrincipal = new JPanel( new BorderLayout() ); // Panel central (tabla)
		pPrincipal.add( new JLabel( "Datos del Producto:" ), BorderLayout.NORTH );
		pPrincipal.add( new JScrollPane( tablaProductos ), BorderLayout.CENTER );

		getContentPane().add( pPrincipal, BorderLayout.CENTER );
		getContentPane().add( lFoto, BorderLayout.EAST );  // Foto este

		JPanel pBotonera = new JPanel(); // Panel inferior (botonera)
		pBotonera.add( bAumentarStock );
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



		List<String> nombresP = BD.importarNombresPelicula();
		for (String p: nombresP) {
			mPeliculas.addElement(BD.importarDatosPelicula(p));;
		}


		// creacion de campo de busqueda y filtros
		JPanel panelFiltros = new JPanel();
		panelFiltros.setLayout(new FlowLayout());

		JLabel labelPelicula = new JLabel("Filtrar Pelicula: ");
		campoBuscadorP = new JTextField(20);


		ultimaselec = null;
		lPeliculas.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				if (!evt.getValueIsAdjusting()) {
					tablaProductos.clearSelection();
					Pelicula seleccionada = (Pelicula) lPeliculas.getSelectedValue();
					if(seleccionada != ultimaselec){
						modeloDatos.setRowCount(0);
						String nombreGeneroP = null;
						try {
							nombreGeneroP = (BD.importarNombreGenero(seleccionada.getId_genero())).getNombre_genero();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						modeloDatos.addRow(new Object[] { seleccionada.getNombre(), seleccionada.getDirector(), nombreGeneroP , seleccionada.getAnyo(), seleccionada.getPrecio(), seleccionada.getCantidad(), seleccionada.getDescripcion(), seleccionada.getImagen()});
						ultimaselec = seleccionada;
					}
				}
			}
		});


		bAumentarStock.addActionListener(e -> {
			//mostrar ventana emergente
			String input = JOptionPane.showInputDialog("Introduce la cantidad a aumentar:");
			//validar que el input no sea vac�o o cancelado
			if(input != null && !input.isEmpty()){
				try{
					//convertir input a entero
					int cantidad = Integer.parseInt(input);
					//actualizar la cantidad en la tabla
					int nuevaCantidad = ultimaselec.getCantidad() + cantidad;
					ultimaselec.setCantidad(nuevaCantidad);
					try {
						BD.modificarCantidadPeliculas(ultimaselec.getNombre(), nuevaCantidad);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					modeloDatos.setValueAt(ultimaselec.getCantidad(), tablaProductos.getSelectedRow(), 5);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(this, "Por favor, introduce un n�mero v�lido.");
				}
			}
		});	

		campoBuscadorP.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {//Se ejecuta cuando se libera una tecla
				JTextField textField = (JTextField) e.getSource();
				//obtiene contenido del textfield
				String text = textField.getText();
				if (text.trim().length() > 0) {
					//nuevo Model temporal
					DefaultListModel<Pelicula> tmp = new DefaultListModel();
					for (int i = 0; i < mPeliculas.getSize(); i++) {//recorre Model original
						//si encuentra coincidencias agrega a model temporal
						if (mPeliculas.getElementAt(i).getNombre().toLowerCase().contains(text.toLowerCase())) {
							tmp.addElement(mPeliculas.getElementAt(i));
						}
					}
					//agrega nuevo modelo a JList
					lPeliculas.setModel(tmp);
				} else {//si esta vacio muestra el Model original
					lPeliculas.setModel(mPeliculas);
				}
			}

		});


		panelFiltros.add(labelPelicula);
		panelFiltros.add(campoBuscadorP);


		initTables();
		loadTables();

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(() -> loadTables(), 0, 3, TimeUnit.SECONDS);

		// agregar componentes al panel principal
		panelCatalogo.add(panelFiltros, BorderLayout.NORTH);
		panelCatalogo.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

		// agregar panel principal a la ventana
		add(panelCatalogo);

	}

	private static class JLabelAjustado extends JLabel {
		private ImageIcon imagen; 
		private int tamX;
		private int tamY;
		/** Crea un jlabel que ajusta una imagen cualquiera con fondo blanco a su tamanyo (a la que ajustemos de las dos escalas, horizontal o vertical)
		 * @param imagen	Imagen a visualizar en el label
		 */
		public JLabelAjustado( ImageIcon imagen ) {
			setImagen( imagen );
		}
		/** Modifica la imagen
		 * @param imagen	Nueva imagen a visualizar en el label
		 */
		public void setImagen( ImageIcon imagen ) {
			this.imagen = imagen;
			if (imagen==null) {
				tamX = 0;
				tamY = 0;
			} else {
				this.tamX = imagen.getIconWidth();
				this.tamY = imagen.getIconHeight();
			}
		}
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;  // El Graphics realmente es Graphics2D
			g2.setColor( Color.WHITE );
			g2.fillRect( 0, 0, getWidth(), getHeight() );
			if (imagen!=null && tamX>0 && tamY>0) {
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
				double escalaX = 1.0 * getWidth() / tamX;
				double escalaY = 1.0 * getHeight() / tamY;
				double escala = escalaX;
				int x = 0;
				int y = 0;
				if (escalaY < escala) {
					escala = escalaY;
					x = (int) ((getWidth() - (tamX * escala)) / 2);
				} else {
					y = (int) ((getHeight() - (tamY * escala)) / 2);
				}
				g2.drawImage( imagen.getImage(), x, y, (int) (tamX*escala), (int) (tamY*escala), null );
			}
		}
	}
}
