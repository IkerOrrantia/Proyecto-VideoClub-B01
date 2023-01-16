package clases;

public enum Estado {
	PENDIENTE("PENDIENTE"), RECOGIDO("RECOGIDO"), DEVUELTO("DEVUELTO");
	
	private String estado;
	
	Estado(String estado){
		this.estado = estado;
	}
	
	public String getEstado() {
		return estado;
	}
}
