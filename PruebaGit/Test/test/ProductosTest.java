package test;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.Genero;
import clases.Productos;

public class ProductosTest {

		private Productos p;
		
		@Before
		public void setUp() {
			p = new Productos("Django Desencadenado","Tarantino",3.24,Genero.ACCION,2013);
		}
	@Test
	public void testgetnombre() {
		assertEquals("Django Desencadenado",p.getNombre());
	}
	@Test
	public void testgetDirector() {
		assertEquals("Tarantino", p.getDirector());
	}
	@Test
	public void testgetPrecio() {
		assertEquals(3.24, p.getPrecio(),0.001);
	}
	@Test
	public void testgetGenero() {
		assertEquals(Genero.ACCION, p.getGenero());
	}
	@Test
	public void testgetAnyo() {
		assertEquals(2013, p.getAnyo());
	}			
}

