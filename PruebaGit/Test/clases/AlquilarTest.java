package clases;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AlquilarTest {
	
	 @Test
	    public void testGetterSetter() {
	        int id_alquiler = 1;
	        int id_cliente = 2;
	        int id_producto = 3;
	        Calendar calendar = Calendar.getInstance();
	        Date fecha_alquiler = calendar.getTime();
	        calendar.add(Calendar.DATE, 7);
	        Date fecha_devolucion = calendar.getTime();
	        
	        Alquilar alquiler = new Alquilar(id_alquiler,id_cliente,id_producto, fecha_alquiler, fecha_devolucion, null);
	        alquiler.setId_alquiler(id_alquiler);
	        alquiler.setId_cliente(id_cliente);
	        alquiler.setId_producto(id_producto);
	        alquiler.setFecha_alquiler(fecha_alquiler);
	        alquiler.setFecha_devolucion(fecha_devolucion);
	        
	        assertEquals(id_alquiler, alquiler.getId_alquiler());
	        assertEquals(id_cliente, alquiler.getId_cliente());
	        assertEquals(id_producto, alquiler.getId_producto());
	        assertEquals(fecha_alquiler, alquiler.getFecha_alquiler());
	        assertEquals(fecha_devolucion, alquiler.getFecha_devolucion());
	    }
	    
	    @Test
	    public void testToString() {
	        int id_alquiler = 1;
	        int id_cliente = 2;
	        int id_producto = 3;
	        Calendar calendar = Calendar.getInstance();
	        Date fecha_alquiler = calendar.getTime();
	        calendar.add(Calendar.DATE, 7);
	        Date fecha_devolucion = calendar.getTime();

	        Estado estado = null;
	        
	        Alquilar alquiler = new Alquilar(id_alquiler,id_cliente,id_producto, fecha_alquiler, fecha_devolucion, null);
	        alquiler.setId_alquiler(id_alquiler);
	        alquiler.setId_cliente(id_cliente);
	        alquiler.setId_producto(id_producto);
	        alquiler.setFecha_alquiler(fecha_alquiler);
	        alquiler.setFecha_devolucion(fecha_devolucion);
	        
	        String expectedToString = "Alquilar [id_alquiler=" + id_alquiler + ", id_cliente=" + id_cliente 
	            + ", id_producto=" + id_producto + ", fecha_alquiler=" + fecha_alquiler 
	            + ", fecha_devolucion=" + fecha_devolucion + ", estado=" + estado + "]";
	        assertEquals(expectedToString, alquiler.toString());
	    }
}
