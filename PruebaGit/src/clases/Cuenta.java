package clases;

public class Cuenta {
	//Variables 
	private String Usuario;
	private String Nombre;
	private String Apellidos;
	private String DNI;
	private String Correo;
	private String Contrasenya;
	private int Telefono;
	private String Direccion;
	
	
	//Constructor
	
	public Cuenta(String usuario, String nombre, String apellidos, String dNI, String correo, String contrasenya,
			int telefono, String direccion) {
		super();
		Usuario = usuario;
		Nombre = nombre;
		Apellidos = apellidos;
		DNI = dNI;
		Correo = correo;
		Contrasenya = contrasenya;
		Telefono = telefono;
		Direccion = direccion;
	}

	// Getters Y Setters
	
	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public String getApellidos() {
		return Apellidos;
	}


	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}


	public String getDNI() {
		return DNI;
	}


	public void setDNI(String dNI) {
		DNI = dNI;
	}


	public String getCorreo() {
		return Correo;
	}


	public void setCorreo(String correo) {
		Correo = correo;
	}


	public String getContrasenya() {
		return Contrasenya;
	}


	public void setContrasenya(String contrasenya) {
		Contrasenya = contrasenya;
	}


	public int getTelefono() {
		return Telefono;
	}


	public void setTelefono(int telefono) {
		Telefono = telefono;
	}


	public String getDireccion() {
		return Direccion;
	}


	public void setDireccion(String direccion) {
		Direccion = direccion;
	}


	//ToString
	@Override
	public String toString() {
		return "Cuenta [ Usuario=" + Usuario + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", DNI=" + DNI + ", Correo=" + Correo
				+ ", Contrasenya=" + Contrasenya + ", Telefono=" + Telefono + ", Direccion=" + Direccion + "]";
	}
	
	



}
