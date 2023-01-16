package clases;

import java.io.Serializable;

public class Producto implements Pagable, Serializable {
	protected int id_producto;
	protected String nombre;
	protected String director;
	protected int id_genero;
	protected int anyo;
	protected double precio;
	protected int cantidad;
	protected String descripcion;
	protected String imagen;

	public Producto(int id_producto , String nombre, String director, int id_genero, int anyo, double precio, int cantidad, String descripcion, String imagen) {
		this.nombre = nombre;
		this.director = director;
		this.id_genero = id_genero;
		this.anyo = anyo;
		this.setPrecio(precio);
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}

	// Getters y setters
	

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getId_genero() {
		return id_genero;
	}

	public void setId_genero(int id_genero) {
		this.id_genero = id_genero;
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
		this.precio = precio;
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
	

	// ToString
	@Override
	public String toString() {
		return nombre;
	}

}
