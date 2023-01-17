package clases;

public class Alquilar {
	private int id_alquiler;
	private int id_cliente;
	private int id_producto;
	private String fecha_alquiler;
	private String fecha_devolucion;
	private Estado estado;
	
	public Alquilar(int id_alquiler, int id_cliente, int id_producto, String fecha_alquiler, String fecha_devolucion, Estado estado) {
		this.id_alquiler=id_alquiler;
		this.id_cliente=id_cliente;
		this.id_producto=id_producto;
		this.fecha_alquiler=fecha_alquiler;
		this.fecha_devolucion=fecha_devolucion;
		this.estado=estado;

		
	}
	
	
	public int getId_alquiler() {
		return id_alquiler;
	}


	public void setId_alquiler(int id_alquiler) {
		this.id_alquiler = id_alquiler;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_producto() {
		return id_producto;
	}


	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}


	public String getFecha_alquiler() {
		return fecha_alquiler;
	}

	public void setFecha_alquiler(String fecha_alquiler) {
		this.fecha_alquiler = fecha_alquiler;
	}

	public String getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(String fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Alquilar [id_alquiler=" + id_alquiler + ", id_cliente=" + id_cliente + ", id_producto=" + id_producto
				+ ", fecha_alquiler=" + fecha_alquiler + ", fecha_devolucion="
				+ fecha_devolucion + ", estado=" + estado + "]";
	}
	
	
	
}
