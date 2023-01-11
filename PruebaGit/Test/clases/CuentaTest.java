package clases;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.Cuenta;
import clases.Genero;

public class CuentaTest {

		private Cuenta p;
		
		@Before
		public void setUp() {
			p = new Cuenta( 8, "Fermin101", "Fermin", "Garcia","21304542A", "Fermin@gmail.com", "Perro33", 688925075,"Calle el Redentor", "Desconectado", 0, 2);
		}
		
	@Test
	public void testgetUsuario() {
		assertEquals("Fermin101",p.getUsuario());
	}
	
	@Test
	public void testgetNombre() {
		assertEquals("Fermin", p.getNombre());
	}
	
	@Test
	public void testgetApellidos() {
		assertEquals("Garcia", p.getApellidos());
	}	
	
	@Test
	public void testgetCorreo() {
		assertEquals("Fermin@gmail.com", p.getCorreo());
	}
	
	@Test
	public void testgetContrasenya() {
		assertEquals("Perro33", p.getContrasenya());
	}
	
	@Test
	public void testgetTelefono() {
		assertEquals(688925075, p.getTelefono());
	}
	
	@Test
	public void testgetDireccion() {
		assertEquals("Calle el Redentor", p.getDireccion());
	}
	
	@Test
	public void testgetConexion() {
		assertEquals("Desconectado", p.getConexion());
	}
	
	@Test
	public void testgetPeliculasAlquiladas() {
		assertEquals(0, p.getPeliculasAlquiladas());
	}	
	
	@Test
	public void testgetRol() {
		assertEquals(2, p.getRol());
	}	
	
	@Test
	public void testtoString() {
		String esperado = "Cuenta [ Id=8 , Usuario=Fermin101, Nombre=Fermin, Apellidos=Garcia, DNI=21304542A, Correo=Fermin@gmail.com, Contrasenya=Perro33, Telefono=688925075, Direccion=Calle el Redentor, Conexion=Desconectado, PeliculasAlquiladas=0, Rol=2]";
		assertEquals(esperado, p.toString());
	}
	
}
