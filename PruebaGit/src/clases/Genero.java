package clases;

public class Genero {
	
	private int id_genero;
	private String nombre_genero;
	private String descripcion;
	
	@Override
	public String toString() {
		return "Genero [id_genero=" + id_genero + ", nombre_genero=" + nombre_genero + ", descripcion=" + descripcion
				+ "]";
	}
	
	/**
	 * @return the id_genero
	 */
	public int getId_genero() {
		return id_genero;
	}
	/**
	 * @param id_genero the id_genero to set
	 */
	public void setId_genero(int id_genero) {
		this.id_genero = id_genero;
	}
	
	/**
	 * @return the nombre_genero
	 */
	public String getNombre_genero() {
		return nombre_genero;
	}
	/**
	 * @param nombre_genero the nombre_genero to set
	 */
	public void setNombre_genero(String nombre_genero) {
		this.nombre_genero = nombre_genero;
	}
	
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
