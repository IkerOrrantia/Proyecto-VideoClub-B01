package clases;

public class Alquilar {
	private int id_alquiler;
	private int id_cliente;
	private int id_pelicula;
	private String fecha_alquiler;
	private String fecha_devolucion;
	
	@Override
	public String toString() {
		return "Alquilar [id_alquiler=" + id_alquiler + ", id_cliente=" + id_cliente + ", id_pelicula=" + id_pelicula
				+ ", fecha_alquiler=" + fecha_alquiler + ", fecha_devolucion=" + fecha_devolucion + "]";
	}
	
	/**
	 * @return the id_alquiler
	 */
	public int getId_alquiler() {
		return id_alquiler;
	}
	/**
	 * @param id_alquiler the id_alquiler to set
	 */
	public void setId_alquiler(int id_alquiler) {
		this.id_alquiler = id_alquiler;
	}
	/**
	 * @return the id_cliente
	 */
	public int getId_cliente() {
		return id_cliente;
	}
	/**
	 * @param id_cliente the id_cliente to set
	 */
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	/**
	 * @return the id_pelicula
	 */
	public int getId_pelicula() {
		return id_pelicula;
	}
	/**
	 * @param id_pelicula the id_pelicula to set
	 */
	public void setId_pelicula(int id_pelicula) {
		this.id_pelicula = id_pelicula;
	}
	/**
	 * @return the fecha_alquiler
	 */
	public String getFecha_alquiler() {
		return fecha_alquiler;
	}
	/**
	 * @param fecha_alquiler the fecha_alquiler to set
	 */
	public void setFecha_alquiler(String fecha_alquiler) {
		this.fecha_alquiler = fecha_alquiler;
	}
	/**
	 * @return the fecha_devolucion
	 */
	public String getFecha_devolucion() {
		return fecha_devolucion;
	}
	/**
	 * @param fecha_devolucion the fecha_devolucion to set
	 */
	public void setFecha_devolucion(String fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}
	
}
