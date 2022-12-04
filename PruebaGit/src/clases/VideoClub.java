package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class VideoClub {
	protected ArrayList<Pedido> pedidos;
	protected ArrayList<Producto> productos;
	private HashMap<Estado, ArrayList<Pedido>> pedidosPorEstado;
	
	/** Constructor con argumentos
	 * @param pedidos almacenados
	 * @param productos disponibles
	 * @param pedidosPorEstado pedidos clasificados por su estado
	 */
	public VideoClub(ArrayList<Pedido> pedidos, ArrayList<Producto> productos,
			HashMap<Estado, ArrayList<Pedido>> pedidosPorEstado) {
		super();
		this.setPedidos(pedidos);
		this.setProductos(productos);
		this.setPedidosPorEstado(pedidosPorEstado);
	}
	
	/** Constructor por defecto, con todas las estructuras de datos vacías
	 */
	public VideoClub() {
		super();
		this.pedidos = new ArrayList<Pedido>();
		this.productos = new ArrayList<Producto>();
		this.pedidosPorEstado = new HashMap<Estado, ArrayList<Pedido>>();
		for (Estado estado : Estado.values()) {
			this.pedidosPorEstado.put(estado, new ArrayList<Pedido>());
		}
	}

	/**
	 * @return pedidos almacenados
	 */
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	/**
	 * @param pedidos almacenados
	 */
	public void setPedidos(ArrayList<Pedido> pedidos) {
		if (pedidos != null) {
			this.pedidos = pedidos;
		}
	}

	/**
	 * @return productos disponibles
	 */
	public ArrayList<Producto> getProductos() {
		return productos;
	}

	/**
	 * @param productos disponibles
	 */
	public void setProductos(ArrayList<Producto> productos) {
		if (pedidos != null) {
			this.productos = productos;
		}
	}

	/**
	 * @return pedidosPorEstado pedidos clasificados por su estado
	 */
	public HashMap<Estado, ArrayList<Pedido>> getPedidosPorEstado() {
		return pedidosPorEstado;
	}

	/**
	 * @param pedidosPorEstado pedidos clasificados por su estado
	 */
	public void setPedidosPorEstado(HashMap<Estado, ArrayList<Pedido>> pedidosPorEstado) {
		if (pedidosPorEstado != null && pedidosPorEstado.keySet().size() == Estado.values().length) {
			this.pedidosPorEstado = pedidosPorEstado;
		}
	}

	/** Convierte un VideoClub en String
	 */
	@Override
	public String toString() {
		return "VideoClub [pedidos=" + pedidos + ", productos=" + productos + ", pedidosPorEstado=" + pedidosPorEstado + "]";
	}
	
	/**
	 * @param pedidosPorEstado pedidos clasificados por su estado
	 */
	public void inicializar() {
		ArrayList<Pelicula> muebles = new ArrayList<Pelicula>(); 
		
		// Cargamos los muebles desde fichero
		try {
			Scanner sc = new Scanner(new File("data/Peliculas.db"));
			String[] genero = { "ANIMACION", "TERROR" , "ROMANTICA" , "ACCION" , "CIENCIAFICCION" };
						
			while(sc.hasNextLine()) {
				String linea = sc.nextLine();
				String[] campos = linea.split(";");
				int pos = Arrays.asList(genero).indexOf(campos[3]);
				Pelicula nueva = new Pelicula(Integer.parseInt(campos[0]), campos[1], campos[2], Genero.values()[pos], Integer.parseInt(campos[4]), Double.parseDouble(campos[5]));
				this.productos.add(nueva);
				muebles.add(nueva);
			}
			
			sc.close();
			
		} catch (IOException e) {
			System.err.println("Error cargando productos");
		}
	}
		
	
	/** Añade un pedido a las estructuras de datos del video club
	 * @param pedido que se añade
	 */
	public void anyadirPedido(Pedido pedido) {
		pedidos.add(pedido);
		pedidosPorEstado.get(pedido.getEstado()).add(pedido);
	}
	
	/** Borra un pedido de las estructuras de datos del video club
	 * @param pedido que se borra
	 */
	public void borrarPedido(Pedido pedido) {
		pedidos.remove(pedido);
		pedidosPorEstado.get(pedido.getEstado()).remove(pedido);
	}
	
	/** Actualiza el estado de un pedido y las estructuras de datos del video club.
	 * @param pedido que se actualiza
	 * @param estado nuevo
	 */
	public void actualizarEstadoPedido(Pedido pedido, Estado estado) {
		int posicion = pedidos.lastIndexOf(pedido);
		pedidosPorEstado.get(pedido.getEstado()).remove(pedido);
		pedidosPorEstado.get(estado).add(pedido);
		pedidos.get(posicion).setEstado(estado);
	}
	
	/** Regenera el mapa pedidosPorEstado
	 */
	public void regenerarPedidosPorEstado() {
		for (Estado estado : Estado.values()) {
			this.pedidosPorEstado.put(estado, new ArrayList<Pedido>());
		}
		for (Pedido pedido : pedidos) {
			this.pedidosPorEstado.get(pedido.getEstado()).add(pedido);
		}
	}
	
	/** Guarda los datos de pedidos en un fichero binario
	 * @param fichero en el que se guardan los datos
	 */
	public void guardarPedidos(String fichero) {
		try {
			FileOutputStream fos = new FileOutputStream(fichero);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(pedidos);
			
			oos.close();
			fos.close();
		} catch (IOException e) {
			System.err.println("Error guardando pedidos en " + fichero);
			e.printStackTrace();
		}
	}

	/** Carga los datos de pedidos desde un fichero binario
	 * @param fichero desde el que se cargan los datos
	 */
	public void cargarPedidos(String fichero) {
		try {
			FileInputStream fis = new FileInputStream(fichero);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			this.pedidos = (ArrayList<Pedido>) ois.readObject();
			regenerarPedidosPorEstado();
			
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error leyendo pedidos en " + fichero);
		}
	

}

}
