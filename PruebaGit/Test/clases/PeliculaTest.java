package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PeliculaTest {
	
	private Pelicula pelicula;
	@Before
	public void setUp() {
		pelicula = new Pelicula(0, 0, "Pelicula 1", "Director 1", 2, 2010, 10.0, 10, "Ejemplo descripcion Pelicula 1", "imagen/foto1");
	}

  @Test
  public void testConstructorConArgumentos() {
    assertEquals(0, pelicula.getId());
    assertEquals(0, pelicula.getId_producto());
    assertEquals("Pelicula 1", pelicula.getNombre());
    assertEquals("Director 1", pelicula.getDirector());
    assertEquals(2, pelicula.getId_genero());
    assertEquals(2010, pelicula.getAnyo());
    assertEquals(10.0, pelicula.getPrecio(),0.001);
    assertEquals(10, pelicula.getCantidad());
    assertEquals("Ejemplo descripcion Pelicula 1", pelicula.getDescripcion());
    assertEquals("imagen/foto1", pelicula.getImagen());

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
    String esperado = "Pelicula 1";
    assertEquals(esperado, pelicula.toString());
  }
}