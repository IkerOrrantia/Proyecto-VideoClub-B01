package clases;

public class Cuenta {
	//Variables 
	private int Id;
	private String Usuario;
	private String Nombre;
	private String Apellidos;
	private String DNI;
	private String Correo;
	private String Contrasenya;
	private int Telefono;
	private String Direccion;
	private String Conexion;
	private int PeliculasAlquiladas;





	private int Rol;
	
	
	//Constructor
	
	public Cuenta(int id, String usuario, String nombre, String apellidos, String dni, String correo, String contrasenya,
			int telefono, String direccion, String conexion, int peliculasAlquiladas,  int rol) {
		super();
		Id = id;
		Usuario = usuario;
		Nombre = nombre;
		Apellidos = apellidos;
		DNI = dni;
		Correo = correo;
		Contrasenya = contrasenya;
		Telefono = telefono;
		Direccion = direccion;
		Conexion  = conexion;
		PeliculasAlquiladas = peliculasAlquiladas;
		Rol= rol;
		
	}

	public Cuenta(int id2, String usuario2) {
	}

	// Getters Y Setters
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	
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


	public void setDNI(String dni) {
		DNI = dni;
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
	
	public String getConexion() {
		return Conexion;
	}

	public void setConexion(String conexion) {
		Conexion = conexion;
	}

	public int getPeliculasAlquiladas() {
		return PeliculasAlquiladas;
	}

	public void setPeliculasAlquiladas(int peliculasAlquiladas) {
		PeliculasAlquiladas = peliculasAlquiladas;
	}	

	public int getRol() {
		return Rol;
	}

	public void setRol(int rol) {
		Rol = rol;
	}

	//ToString
	@Override
	public String toString() {
		return "Cuenta [ Id=" + Id +" , Usuario=" + Usuario + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", DNI=" + DNI + ", Correo=" + Correo
				+ ", Contrasenya=" + Contrasenya + ", Telefono=" + Telefono + ", Direccion=" + Direccion + ", Conexion=" + Conexion + ", PeliculasAlquiladas=" + PeliculasAlquiladas + ", Rol=" + Rol + "]";
	}

}