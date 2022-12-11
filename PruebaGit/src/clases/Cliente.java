package clases;

public class Cliente extends Cuenta {
	// Variable
	private int PeliculasCompradas;
	private Genero Genero;
	// Anyadir numero de peliculas por tipo ---> For TipoPelicula...
	public Cliente(String usuario, String nombre, String apellidos, String dni, String correo, String contrasenya, int telefono, String direccion, String conexion, int peliculascompradas) {
		super(usuario, nombre, apellidos, dni, correo, contrasenya, telefono, direccion, conexion);
		this.PeliculasCompradas = peliculascompradas;
	}
	public int getPeliculasCompradas() {
		return PeliculasCompradas;
	}
	public void setPeliculasCompradas(int peliculascompradas) {
		this.PeliculasCompradas = peliculascompradas;
	}
	@Override
	public String toString() {
		return "Cliente [PeliculasCompradas=" + PeliculasCompradas + "]";
	}
	
}
