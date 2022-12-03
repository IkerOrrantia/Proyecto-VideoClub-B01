package clases;

public abstract class Producto {
	//Variables
	protected String nombre;
	protected int id;
	
	
	//Constructor
	public Producto(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		
	}
	public Producto() {
		super();
		this.id = -1;
		this.nombre = "Sin nombre";
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	//Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	//toString
	@Override
	public String toString() {
		return "Producto " + nombre + " (id: " + id+ ")";
	}

}
