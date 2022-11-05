package Test;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Clases.Genero;
import Clases.Productos;

public class ProductosTest {

		private Productos p;
		
		@Before
		public void setUp() {
			p = new Productos("Django","Tarantino",3.24,Genero.DRAMA,2017);
		}
	@Test
	public void testgetnombre() {
		assertEquals("Django",p.getNombre());
	}
	@Test
	public void testgetDirector() {
		assertEquals("Tarantino", p.getDirector());
	}
	@Test
	public void testgetAnyo() {
		assertEquals(2017, p.getAnyo());
	}	
	@Test
	public void testgetPrecio() {
		assertEquals(3.24, p.getPrecio());
	}
			
}

