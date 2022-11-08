package clases;

public class Cliente extends Cuenta {
	// Variable
	private int NumerodePeliculas;
	private Genero Genero;
	// Anyadir numero de peliculas por tipo ---> For TipoPelicula...
	public Cliente(String usuario, String nombre, String apellidos, String dNI, String correo, String contrasenya, int telefono, String direccion, int NumerodePeliculas) {
		super(usuario, nombre, apellidos, dNI, correo, contrasenya, telefono, direccion);
		this.NumerodePeliculas = NumerodePeliculas;
	}
	public int getNumerodePeliculas() {
		return NumerodePeliculas;
	}
	public void setNumerodePeliculas(int numerodePeliculas) {
		NumerodePeliculas = numerodePeliculas;
	}
	@Override
	public String toString() {
		return "Cliente [NumerodePeliculas=" + NumerodePeliculas + "]";
	}
	
}
