package clases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RolTets {
	
	@Test
    public void testGetterSetter() {
        int id_rol = 1;
        String nombre = "Admin";

        Rol rol = new Rol();
        rol.setId_rol(id_rol);
        rol.setNombre(nombre);
        
        assertEquals(id_rol, rol.getId_rol());
        assertEquals(nombre, rol.getNombre());
    }
    
    @Test
    public void testToString() {
        int id_rol = 1;
        String nombre = "Admin";
        
        Rol rol = new Rol();
        rol.setId_rol(id_rol);
        rol.setNombre(nombre);
        
        String expectedToString = "Rol [id_rol=" + id_rol + ", nombre=" + nombre + "]";
        assertEquals(expectedToString, rol.toString());
    }
}

