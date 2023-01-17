package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GeneroTest {
	
	@Test
    public void testGetterSetter() {
        int id_genero = 1;
        String nombre_genero = "Accion";
        String descripcion = "Peliculas de accion";
        
        Genero genero = new Genero(id_genero, nombre_genero, descripcion);
        genero.setId_genero(id_genero);
        genero.setNombre_genero(nombre_genero);
        genero.setDescripcion(descripcion);
        
        assertEquals(id_genero, genero.getId_genero());
        assertEquals(nombre_genero, genero.getNombre_genero());
        assertEquals(descripcion, genero.getDescripcion());
    }
    
    @Test
    public void testToString() {
        int id_genero = 1;
        String nombre_genero = "Accion";
        String descripcion = "Peliculas de accion";
        
        Genero genero = new Genero(id_genero, nombre_genero, descripcion);
        genero.setId_genero(id_genero);
        genero.setNombre_genero(nombre_genero);
        genero.setDescripcion(descripcion);
        
        String expectedToString = "Genero [id_genero=" + id_genero + ", nombre_genero=" + nombre_genero 
            + ", descripcion=" + descripcion + "]";
        assertEquals(expectedToString, genero.toString());
    }
}
