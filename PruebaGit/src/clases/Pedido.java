package clases;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Pedido implements Serializable {
	protected Cliente cliente;
	protected Date fecha;
	protected ArrayList<Pagable> elementos;
	protected Estado estado;
	
	/** Constructor con argumenos
	 * @param cliente String con el nº de tarjeta de credito
	 * @param fecha del pedido
	 * @param elementos que componen el pedido
	 * @param estado del pedido (PREPARACION, LISTO, FINALIZADO)
	 */
	public Pedido(Cliente cliente, Date fecha, ArrayList<Pagable> elementos, Estado estado) {
		super();
		this.setCliente(cliente);
		this.setFecha(fecha);
		this.setElementos(elementos);
		this.estado = estado;
	}
	
	/** Constructor por defecto, crea un pedido sin tarjeta, hoy, sin elementos y en PREPARACIÓN
	 */
	public Pedido() {
		super();
		this.cliente = new Cliente(cliente.getUsuario(), cliente.getNombre(), cliente.getApellidos(), cliente.getDNI(), cliente.getCorreo(), cliente.getContrasenya() ,cliente.getTelefono(), cliente.getDireccion(), cliente.getConexion(),0);
		this.fecha = new Date();
		this.elementos = new ArrayList<Pagable>();
		this.estado = Estado.PREPARACION;
	}

	/**
	 * @return cliente String con el nº de tarjeta de credito
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente String con el nº de tarjeta de credito
	 */
	public void setCliente(Cliente cliente) {
		if (cliente != null) {
			this.cliente = cliente;
		}
	}

	/**
	 * @return fecha del pedido
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha del pedido
	 */
	public void setFecha(Date fecha) {
		if (fecha != null) {
			this.fecha = fecha;
		}
	}

	/**
	 * @return the elementos
	 */
	public ArrayList<Pagable> getElementos() {
		return elementos;
	}

	/**
	 * @param elementos que componen el pedido
	 */
	public void setElementos(ArrayList<Pagable> elementos) {
		if (elementos != null) {
			this.elementos = elementos;
		}
	}

	/**
	 * @return elementos que componen el pedido
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado del pedido (PREPARACION, LISTO, FINALIZADO)
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return precio total del pedido
	 */
	public double getPrecio() {
		double precio = 0;
		
		for (Pagable pagable : elementos) {
			precio += pagable.getPrecio();
		}
		
		return precio;
	}
	
	/** Convierte un Pedido en String 
	 */
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return "Pedido de " + cliente + ", " + sdf.format(fecha) + ", " + this.getPrecio() + " euros (" + estado + ")";
	}
	
}