package clases;

import java.io.Serializable;

public class Serie implements Pagable, Serializable {
	protected int id;
	protected String nombre;
	protected int id_genero;
	protected int temporadas;
	protected double precio;
	protected String creador;
	protected int anyo;
	protected int cantidad;
	protected String descripcion;

	/**
	 * Constructor con argumentos
	 * 
	 * @param id        identificativo único
	 * @param nombre    del producto
	 * @param categoria del mueble
	 * @param precio    del mueble
	 */
	public Serie(int id, String nombre, String creador, int anyo, int temporadas, int id_genero, double precio, int cantidad, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.creador = creador;
		this.temporadas = temporadas;
		this.id_genero = id_genero;
		this.anyo = anyo;
		this.setPrecio(precio);
		this.cantidad = cantidad;
		this.descripcion = descripcion;
	}

	/**
	 * Constructor por defecto, crea una pelicula con id -1, "Sin nombre" de Genero
	 * precio 0
	 */
	public Serie() {
		super();
		this.setPrecio(0);
	}

	//Getter y setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getId_genero() {
		return id_genero;
	}

	public void setId_genero(int id_genero) {
		this.id_genero = id_genero;
	}

	public int getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return nombre;
	}

	//To String
	
	

	

}
