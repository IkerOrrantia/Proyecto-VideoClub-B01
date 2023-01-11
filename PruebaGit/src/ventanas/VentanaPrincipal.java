package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import bd.*;
import clases.*;
import ventanas.*;

public class VentanaPrincipal extends JFrame {
	private DefaultListModel<Pelicula> mPeliculas = new DefaultListModel<>();
	private JList<Pelicula> lPeliculas = new JList<>(mPeliculas);
	private DefaultListModel<Serie> mSeries = new DefaultListModel<>();
	private JList<Serie> lSeries = new JList<>(mSeries);
	private DefaultListModel<String> mNombresP = new DefaultListModel<>();
	private DefaultListModel<String> mNombresS = new DefaultListModel<>();
	private BD BD;
	private JPanel panelCatalogo;
	private JTextField campoBuscadorP;
	private JTextField campoBuscadorS;
	private JTable tablaProductos;
	private DefaultTableModel modeloDatos;
	private ArrayList<Pelicula> tablePeli = new ArrayList<Pelicula>();
	private JLabelAjustado lFoto = new JLabelAjustado( null );
	private JButton bAlquilar = new JButton( "Alquilar producto" );
	TableRowSorter<DefaultTableModel> sorter;
	private List<String> nombresP;
	private List<String> nombresS;
	private DefaultListModel<String> nombresPModel = new DefaultListModel<>();
	private DefaultListModel<String> nombresSModel = new DefaultListModel<>();
	
	private void initTables (){
		Vector<String> cabecera = new Vector<String>(Arrays.asList("ID", "NOMBRE", "DIRECTOR", "GENERO", "ANYO", "PRECIO", "CANTIDAD", "DESCRIPCION"));
		// creamos modelo de datos
		this.modeloDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);
		// se crea tabla utilizado el modelo
		this.tablaProductos = new JTable(this.modeloDatos);
		this.tablaProductos.setAutoCreateRowSorter(true);
		// modificamos para seleccionar una fila
		this.tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Crea un TableRowSorter y asigna el modelo de datos de tu JTable
		sorter = new TableRowSorter<>(modeloDatos);
		tablaProductos.setRowSorter(sorter);

		// Crea un RowFilter utilizando el texto del campo de búsqueda
		RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(campoBuscadorP.getText(), 1);

		// Actualiza el filtro del TableRowSorter
		sorter.setRowFilter(filter);
		// pasar render
		this.tablaProductos.setDefaultRenderer(Object.class, null);
	}
	
	
	private void loadTables (){
		// borrar datos
		this.modeloDatos.setRowCount(0);
		// aÃ±adir fila por peli
		for (Pelicula p : this.tablePeli) {
			this.modeloDatos.addRow(new Object[] { p.getNombre()});
		}
	}
	
	public ListModel<String> toListModelP(List<String> list) {
	    for (String item : list) {
	        nombresPModel.addElement(item);
	    }
	    return nombresPModel;
	}

	public ListModel<String> toListModelS(List<String> list) {
	    for (String item : list) {
	        nombresSModel.addElement(item);
	    }
	    return nombresSModel;
	}

	public void filterP() throws SQLException{
		campoBuscadorP.getDocument().addDocumentListener(new DocumentListener(){
	        @Override public void insertUpdate(DocumentEvent e) { filter(); }
	        @Override public void removeUpdate(DocumentEvent e) { filter(); }
	        @Override public void changedUpdate(DocumentEvent e) {}
	        private void filter() {
	            String filter = campoBuscadorP.getText();
	            filterModelP(nombresPModel, filter);
	        }
	    });
	}

	public void filterModelP(DefaultListModel<String> model, String filter) {
	    for (String s : nombresP) {
	        if (!s.startsWith(filter)) {
	            if (model.contains(s)) {
	                model.removeElement(s);
	            }
	        } else {
	            if (!model.contains(s)) {
	                model.addElement(s);
	            }
	        }
	    }
	}

	public void filterS() throws SQLException {
		  campoBuscadorS.getDocument().addDocumentListener(new DocumentListener(){
	            @Override public void insertUpdate(DocumentEvent e) { filter(); }
	            @Override public void removeUpdate(DocumentEvent e) { filter(); }
	            @Override public void changedUpdate(DocumentEvent e) {}
	            private void filter() {
	                String filter = campoBuscadorS.getText();
	                filterModelS(nombresSModel, filter);
	            }
	        });
	}

	public void filterModelS(DefaultListModel<String> model, String filter) {
	    for (String s : nombresS) {
	        if (!s.startsWith(filter)) {
	            if (model.contains(s)) {
	                model.removeElement(s);
	            }
	        } else {
	            if (!model.contains(s)) {
	                model.addElement(s);
	            }
	        }
	    }
	}
	
	public VentanaPrincipal() throws SQLException {
		
	    // configuraciï¿½n de la ventana
	    setTitle("Catalogo de productos");
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
		JPanel pSeries = new JPanel( new BorderLayout() );
		pSeries.add( new JLabel( "Series:" ), BorderLayout.NORTH );
		pSeries.add( new JScrollPane(lSeries), BorderLayout.CENTER );
		pOeste.setBottomComponent(pSeries);
		getContentPane().add( pOeste, BorderLayout.WEST ); 
		JPanel pPrincipal = new JPanel( new BorderLayout() ); // Panel central (tabla)
		pPrincipal.add( new JLabel( "Datos del Productp:" ), BorderLayout.NORTH );
		pPrincipal.add( new JScrollPane( tablaProductos ), BorderLayout.CENTER );
		getContentPane().add( pPrincipal, BorderLayout.CENTER );
		getContentPane().add( lFoto, BorderLayout.EAST );  // Foto este
		JPanel pBotonera = new JPanel(); // Panel inferior (botonera)
		pBotonera.add( bAlquilar );
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
	    
	    List<String> nombresS = BD.importarNombresSerie();
	    for (String s: nombresS) {
	        mSeries.addElement(BD.importarDatosSerie(s));
	    }
	    
	    
	    // creacion de campo de busqueda y filtros
	    JPanel panelFiltros = new JPanel();
	    panelFiltros.setLayout(new FlowLayout());

	    campoBuscadorP = new JTextField(20);
	    JButton aceptarBusquedaP = new JButton("Filtrar Pelicula");
	    
	    campoBuscadorP.getDocument().addDocumentListener(new DocumentListener() {
	    	  @Override
	    	  public void insertUpdate(DocumentEvent e) {
	    	    updateFilter();
	    	  }

	    	  @Override
	    	  public void removeUpdate(DocumentEvent e) {
	    	    updateFilter();
	    	  }

	    	  @Override
	    	  public void changedUpdate(DocumentEvent e) {
	    	    updateFilter();
	    	  }

	    	  private void updateFilter() {
	    	    // Crea un RowFilter utilizando el texto del campo de búsqueda
	    	    RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(campoBuscadorP.getText(),1);

	    	    // Actualiza el filtro del TableRowSorter
	    	    sorter.setRowFilter(filter);
	    	  }
	    	});
	    
	    campoBuscadorS = new JTextField(20);
	    JButton aceptarBusquedaS = new JButton("Filtrar Serie");
	    
	    campoBuscadorS.getDocument().addDocumentListener(new DocumentListener() {
	    	  @Override
	    	  public void insertUpdate(DocumentEvent e) {
	    	    updateFilter();
	    	  }

	    	  @Override
	    	  public void removeUpdate(DocumentEvent e) {
	    	    updateFilter();
	    	  }

	    	  @Override
	    	  public void changedUpdate(DocumentEvent e) {
	    	    updateFilter();
	    	  }

	    	  private void updateFilter() {
	    	    // Crea un RowFilter utilizando el texto del campo de búsqueda
	    	    RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(campoBuscadorP.getText(),1);

	    	    // Actualiza el filtro del TableRowSorter
	    	    sorter.setRowFilter(filter);
	    	  }
	    	});
	    
	    panelFiltros.add(campoBuscadorP);
	    panelFiltros.add(aceptarBusquedaP);
	    panelFiltros.add(campoBuscadorS);
	    panelFiltros.add(aceptarBusquedaS);


        aceptarBusquedaP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					filterP();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	  
        aceptarBusquedaS.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	    initTables();
	    loadTables();

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
		/** Crea un jlabel que ajusta una imagen cualquiera con fondo blanco a su tamaÃ±o (a la que ajuste mÃ¡s de las dos escalas, horizontal o vertical)
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