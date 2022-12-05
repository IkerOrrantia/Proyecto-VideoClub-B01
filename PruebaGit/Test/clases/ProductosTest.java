package clases;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import clases.Genero;
import clases.Producto;

public class ProductosTest {

		private Pelicula p;
		
		@Before
		public void setUp() {
			p = new Pelicula(1, "Django Desencadenado","Tarantino", Genero.ACCION, 2013, 3.24);
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

