package clases;

import java.io.Serializable;

public class Serie extends Producto {
	protected int id;
	protected int temporadas;

	/**
	 * Constructor con argumentos
	 * 
	 * @param id        identificativo único
	 * @param nombre    del producto
	 * @param categoria del mueble
	 * @param precio    del mueble
	 */
	public Serie(int id, int id_producto, String nombre, String director, int id_genero, int anyo, int temporadas, double precio, int cantidad, String descripcion, String imagen) {
		super(id_producto, nombre, director, id_genero, anyo, precio, cantidad, descripcion, imagen);
		this.id = id;
		this.temporadas = temporadas;
	
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

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	//To String
	@Override
	public String toString() {
		return nombre;
	}

}
