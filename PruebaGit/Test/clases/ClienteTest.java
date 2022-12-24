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
		c = new Cliente(8 , "juanillo23", 4);
	}
	@Test
	public void testgetPeliculasAlquiladas() {
		assertEquals(4, c.getPeliculasAlquiladas());
	}
 	@Test
 	public void testtoString() {
 		String esperado = "Cliente [Id_Cliente=8 , Usuario=juanillo23 , PeliculasAlquiladas=4]";
 		assertEquals(esperado, c.toString());
 	}
}