package clases;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class SerieTest {
	private Serie s;
	@Before
	public void setUp() {
		 s = new Serie(1, "Serie 1", "Creador 1", 2010, 3, 1, 20.0, 10, "Descripción de la serie 1", "imagen/serie1");
	}
    @Test
   public void testConstructorConArgumentos() {
        // Creamos una nueva serie usando el constructor con argumentos
        
        
        // Comprobamos que los valores se han inicializado correctamente
        assertEquals(1, s.getId());
        assertEquals("Serie 1", s.getNombre());
        assertEquals("Creador 1", s.getCreador());
        assertEquals(2010, s.getAnyo());
        assertEquals(3, s.getTemporadas());
        assertEquals(1, s.getId_genero());
        assertEquals(20.0, s.getPrecio(), 0.001);
        assertEquals(10, s.getCantidad());
        assertEquals("Descripción de la serie 1", s.getDescripcion());
        assertEquals("imagen/serie1", s.getImagen());
    }
    
    @Test
    public void testConstructorPorDefecto() {
        // Creamos una nueva serie usando el constructor por defecto
        Serie serie = new Serie(-1, "Sin nombre", "", 0, 0, 0, 0.0, 0, "", "");
        
        // Comprobamos que los valores se han inicializado correctamente
        assertEquals(-1, serie.getId());
        assertEquals("Sin nombre", serie.getNombre());
        assertEquals("", serie.getCreador());
        assertEquals(0, serie.getAnyo());
        assertEquals(0, serie.getTemporadas());
        assertEquals(0, serie.getId_genero());
        assertEquals(0.0, serie.getPrecio(), 0.001);
        assertEquals(0, serie.getCantidad());
        assertEquals("", serie.getDescripcion());
        assertEquals("", serie.getImagen());
    }
    
    @Test
    public void testSetters() {
        // Creamos una nueva serie usando el constructor por defecto
        Serie s = new Serie();
        
        // Utilizamos los setters para cambiar los valores de la serie
        s.setId(2);
        s.setNombre("Serie 2");
        s.setCreador("Creador 2");
        s.setAnyo(2011);
        s.setTemporadas(5);
        s.setId_genero(2);
        s.setPrecio(30.0);
        s.setCantidad(15);
        s.setDescripcion("Descripción de la serie 2");
        s.setImagen("imagen/serie2");
    }
}