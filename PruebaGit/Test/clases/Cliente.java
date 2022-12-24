package clases;

public class Cliente extends Cuenta {
	// Variable
	private int PeliculasAlquiladas;
	private Genero Genero;
	// Anyadir numero de peliculas por tipo ---> For TipoPelicula...
	public Cliente(String usuario, String nombre, String apellidos, String dni, String correo, String contrasenya, int telefono, String direccion, String conexion, int peliculasAlquiladas) {
		super(usuario, nombre, apellidos, dni, correo, contrasenya, telefono, direccion, conexion);
		this.PeliculasAlquiladas = peliculasAlquiladas;
	}
	public int getPeliculasAlquiladas() {
		return PeliculasAlquiladas;
	}
	public void setPeliculasAlquiladas(int peliculasAlquiladas) {
		this.PeliculasAlquiladas = peliculasAlquiladas;
	}
	@Override
	public String toString() {
		return "Cliente [PeliculasCompradas=" + PeliculasAlquiladas + "]";
	}
	
}
