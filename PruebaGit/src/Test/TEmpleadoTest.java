package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Clases.TEmpleado;

public class TEmpleadoTest {
		
	private TEmpleado p;
	
	@Before
	public void setUp() {
		p=new TEmpleado(01, "Juan", "Martinez", 633834978, 8.90, null);
	}
	@Test
	public void testgetaCodEmpleado() {
		assertEquals(01,p.getCodEmpleado());
	}
	@Test
	public void testgetaNombre() {
		assertEquals("Juan",p.getNombre());
	}
	@Test
	public void testgetaApellido() {
		assertEquals("Martinez",p.getApell());
	}
	@Test
	public void testgetaTelefono() {
		assertEquals(633834978,p.getTelefono());
	}	
}
