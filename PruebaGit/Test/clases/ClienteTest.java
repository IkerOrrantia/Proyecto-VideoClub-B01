package clases;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.Cliente;

public class ClienteTest {
	private Cliente c;
	
	@Before
	public void SetUp() {
		c = new Cliente("Fermin101", "Fermin", "Garcia","21304542A", "Fermin@gmail.com", "Perro33",688925075 , "Calle el Redentor","Data/Clientes.txt", 4);
	}
	@Test
	public void testgetNumerodePeliculas() {
		assertEquals(4, c.getPeliculasCompradas());
	}
}
