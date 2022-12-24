package clases;

public class Cliente extends Cuenta {
	// Variables
	private int Id_Cliente;
	private String Usuario;
	private int PeliculasAlquiladas;
	
	static Cuenta cuenta;
	// Anyadir numero de peliculas por tipo ---> For TipoPelicula...
	public Cliente(int id, String usuario, int peliculasAlquiladas) {
		super(id, usuario);
	//	super(id, usuario, cuenta.getNombre(), cuenta.getApellidos(), cuenta.getDNI(), cuenta.getCorreo(), cuenta.getContrasenya(), cuenta.getTelefono(), cuenta.getDireccion(), cuenta.getConexion(), cuenta.getRol());
		this.Id_Cliente = id;
		this.Usuario = usuario;
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
		return "Cliente [Id_Cliente=" + Id_Cliente + " , Usuario=" + Usuario + " , PeliculasAlquiladas="
				+ PeliculasAlquiladas + "]";
	}
	
	
	
	
	
}
