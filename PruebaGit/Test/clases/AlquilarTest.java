package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AlquilarTest {
	
	 @Test
	    public void testGetterSetter() {
	        int id_alquiler = 1;
	        int id_cliente = 2;
	        int id_pelicula = 3;
	        String fecha_alquiler = "01/01/2020";
	        String fecha_devolucion = "01/01/2021";
	        
	        Alquilar alquiler = new Alquilar();
	        alquiler.setId_alquiler(id_alquiler);
	        alquiler.setId_cliente(id_cliente);
	        alquiler.setId_pelicula(id_pelicula);
	        alquiler.setFecha_alquiler(fecha_alquiler);
	        alquiler.setFecha_devolucion(fecha_devolucion);
	        
	        assertEquals(id_alquiler, alquiler.getId_alquiler());
	        assertEquals(id_cliente, alquiler.getId_cliente());
	        assertEquals(id_pelicula, alquiler.getId_pelicula());
	        assertEquals(fecha_alquiler, alquiler.getFecha_alquiler());
	        assertEquals(fecha_devolucion, alquiler.getFecha_devolucion());
	    }
	    
	    @Test
	    public void testToString() {
	        int id_alquiler = 1;
	        int id_cliente = 2;
	        int id_pelicula = 3;
	        String fecha_alquiler = "01/01/2020";
	        String fecha_devolucion = "01/01/2021";
	        
	        Alquilar alquiler = new Alquilar();
	        alquiler.setId_alquiler(id_alquiler);
	        alquiler.setId_cliente(id_cliente);
	        alquiler.setId_pelicula(id_pelicula);
	        alquiler.setFecha_alquiler(fecha_alquiler);
	        alquiler.setFecha_devolucion(fecha_devolucion);
	        
	        String expectedToString = "Alquilar [id_alquiler=" + id_alquiler + ", id_cliente=" + id_cliente 
	            + ", id_pelicula=" + id_pelicula + ", fecha_alquiler=" + fecha_alquiler 
	            + ", fecha_devolucion=" + fecha_devolucion + "]";
	        assertEquals(expectedToString, alquiler.toString());
	    }
}
