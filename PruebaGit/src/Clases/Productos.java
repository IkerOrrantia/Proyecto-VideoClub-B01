package Clases;

public class Productos {
	//Variables
	private String nombre;
	private String director;
	private double precio;
	private Genero genero;
	private int anyo;
	private String tipo;
	
	//Constructor
	public Productos(String nombre, String director, double precio, Genero genero, int anyo, String tipo) {
		super();
		this.nombre = nombre;
		this.director = director;
		this.precio = precio;
		this.genero = genero;
		this.anyo = anyo;
		this.tipo = tipo;
	}
	//Getters y Setters
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
