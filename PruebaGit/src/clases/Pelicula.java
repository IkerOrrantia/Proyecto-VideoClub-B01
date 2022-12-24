package clases;

import java.io.Serializable;

public class Pelicula extends Producto implements Pagable, Serializable {
	protected Genero genero;
	protected double precio;
	protected String director;
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
	public Pelicula(int id, String nombre, String director, Genero genero, int anyo, double precio, int cantidad, String descripcion) {
		super(id, nombre);
		this.director = director;
		this.genero = genero;
		this.anyo = anyo;
		this.setPrecio(precio);
		this.cantidad = cantidad;
		this.descripcion = descripcion;
	}

	/**
	 * Constructor por defecto, crea una pelicula con id -1, "Sin nombre" de Genero
	 * Accion y precio 0
	 */
	public Pelicula() {
		super();
		this.genero = Genero.ACCION;
		this.setPrecio(0);
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		if (precio >= 0) {
			this.precio = precio;
		}
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
		return "Pelicula [id=" + id + ", nombre=" + nombre + ", director=" + director + ", genero=" + genero + ", anyo="
				+ anyo + ", precio=" + precio + ", cantidad=" + cantidad + ", descripcion=" + descripcion +"]";
	}

}