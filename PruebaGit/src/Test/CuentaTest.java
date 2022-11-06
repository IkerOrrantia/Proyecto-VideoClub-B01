package Test;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Clases.Cuenta;
import Clases.Genero;
import Clases.Productos;

public class CuentaTest {

		private Cuenta p;
		
		@Before
		public void setUp() {
			p = new Cuenta("Fermin101", "Fermin", "Garcia","21304542A", "Fermin@gmail.com", "Perro33",688925075 , "Calle el Redentor");
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
			
}
