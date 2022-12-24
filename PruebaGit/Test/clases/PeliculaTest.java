package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PeliculaTest {
	
	private Pelicula pelicula;
	@Before
	public void setUp() {
		pelicula = new Pelicula(1, "Pelicula 1", "Director 1", Genero.TERROR, 2010, 10.0);
	}
	
  @Test
  public void testConstructorConArgumentos() {
    assertEquals(1, pelicula.getId());
    assertEquals("Pelicula 1", pelicula.getNombre());
    assertEquals("Director 1", pelicula.getDirector());
    assertEquals(Genero.TERROR, pelicula.getGenero());
    assertEquals(2010, pelicula.getAnyo());
    assertEquals(10.0, pelicula.getPrecio(),0.001);
  }

  @Test
  public void testSetPrecio() {
     pelicula.setPrecio(10.0);
    assertEquals(10.0, pelicula.getPrecio(),0.001);

    pelicula.setPrecio(-1.0);
    assertEquals(10.0, pelicula.getPrecio(),0.001);
  }

  @Test
  public void testToString() {
    String esperado = "Pelicula [id=1, nombre=Pelicula 1, director=Director 1, genero=TERROR, anyo=2010, precio=10.0]";
    assertEquals(esperado, pelicula.toString());
  }
}