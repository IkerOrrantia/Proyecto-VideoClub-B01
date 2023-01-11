package bd;


import static org.junit.Assert.*;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bd.BD;
import clases.Cliente;
import clases.Cuenta;
import clases.Pelicula;
import clases.Serie;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BDTest {

	private BD bd;
	private Cuenta cuenta;
	private Cliente cliente;
	private  List<Cuenta> cuentas;
	private  List<Cliente> clientes;


	@Before
	public void setUp() throws Exception {
		bd = new BD();
		bd.connect();
		cuenta = new Cuenta(0, "usuario1", "Nombre 1", "Apellidos 1", "DNI 1", "correo@gmail.com", "contraseña1", 123456789, "Dirección 1", "conexión 1", 0, 1);
		cliente = new Cliente(1, "usuario1", 0);
		cuentas = new ArrayList<>();
		clientes = new ArrayList<>();
		

	}

	@After
	public void tearDown() throws Exception {
		bd.borrarCuentaExportada(cuenta);
		bd.borrarClienteExportado(cliente);
		bd.disconnect();
	}

	@Test
	public void testExportCuentaToDataBase() throws SQLException {
		// Añadir cuenta a la base de datos
		bd.exportCuentaToDataBase(cuenta);


		// Comprobar que la cuenta se ha añadido correctamente
		cuentas = bd.importarCuentaFromDataBase();
		assertEquals(cuenta.getUsuario(), cuentas.get(cuentas.size()-1).getUsuario());
		assertEquals(cuenta.getNombre(), cuentas.get(cuentas.size()-1).getNombre());
		assertEquals(cuenta.getApellidos(), cuentas.get(cuentas.size()-1).getApellidos());
		assertEquals(cuenta.getDNI(), cuentas.get(cuentas.size()-1).getDNI());
		assertEquals(cuenta.getCorreo(), cuentas.get(cuentas.size()-1).getCorreo());
		assertEquals(cuenta.getContrasenya(), cuentas.get(cuentas.size()-1).getContrasenya());
		assertEquals(cuenta.getTelefono(), cuentas.get(cuentas.size()-1).getTelefono());
		assertEquals(cuenta.getDireccion(), cuentas.get(cuentas.size()-1).getDireccion());
		assertEquals(cuenta.getConexion(), cuentas.get(cuentas.size()-1).getConexion());
		assertEquals(cuenta.getRol(), cuentas.get(cuentas.size()-1).getRol());
	}

	@Test
	public void testExportarClienteToDataBase() throws SQLException {
		bd.exportarClienteToDataBase(cliente);


		// Comprobar que la cuenta se ha añadido correctamente
		clientes = bd.importarClienteFromDataBase();
		assertEquals(cliente.getId(), clientes.get(clientes.size()-1).getId());
		assertEquals(cliente.getUsuario(), clientes.get(clientes.size()-1).getUsuario());
		assertEquals(cliente.getPeliculasAlquiladas(), clientes.get(clientes.size()-1).getPeliculasAlquiladas());

	}
	@Test
	public void testLoggin() throws SQLException {
        // Arrange
        String usuario = "usuario1";
        String contrasenya = "contraseña1";
        //Act
        bd.loggin(usuario, contrasenya);
        // Assert
        assertNotNull(cuenta);
        assertEquals("usuario1", cuenta.getUsuario());
        assertEquals("contraseña1", cuenta.getContrasenya());
    }
	
	@Test
    public void testLogginFailure() throws SQLException {
        // Arrange
        String usuario = "usuario1";
        String contrasenya = "contraseña1";
        Cuenta cuenta = null;
        //Act
        bd.loggin(usuario, contrasenya);
        // Assert
        assertNull(cuenta);
    }
	
	@Test
    public void testImportarNombresPelicula() throws SQLException {
        // Act
        List<String> nombresPeliculas = bd.importarNombresPelicula();

        // Assert
        assertEquals(22, nombresPeliculas.size());
        assertTrue(nombresPeliculas.contains(nombresPeliculas.get(0)));
        assertTrue(nombresPeliculas.contains(nombresPeliculas.get(1)));
        assertTrue(nombresPeliculas.contains(nombresPeliculas.get(2)));
    }

    @Test
    public void testImportarNombresSerie() throws SQLException {
        // Act
        List<String> nombresSeries = bd.importarNombresSerie();

        // Assert
        assertEquals(20, nombresSeries.size());
        assertTrue(nombresSeries.contains(nombresSeries.get(0)));
        assertTrue(nombresSeries.contains(nombresSeries.get(1)));
    }

    @Test
    public void testImportarDatosPelicula() throws SQLException {
      
        // Act
        List<String> nombresPeliculas = bd.importarNombresPelicula();

        // Assert
        	Pelicula pelicula = bd.importarDatosPelicula(nombresPeliculas.get(0));
        assertNotNull(pelicula);
        assertEquals(0, pelicula.getId());
        assertEquals("It", pelicula.getNombre());
        assertEquals("Tommy Lee Wallace", pelicula.getDirector());
        assertEquals(6, pelicula.getId_genero());
        assertEquals(1990, pelicula.getAnyo());
        assertEquals(3.45, pelicula.getPrecio(), 0.000001);
        assertEquals(10, pelicula.getCantidad());
        assertNotNull(pelicula.getDescripcion());
        assertEquals("img/p00.jpeg", pelicula.getImagen());



    }

    @Test
    public void testImportarDatosSerie() throws SQLException {
    	// Act
        List<String> nombresSeries = bd.importarNombresSerie();

        // Assert
        	Serie serie = bd.importarDatosSerie(nombresSeries.get(0));
        assertNotNull(serie);
        assertEquals(1, serie.getId());
        assertEquals("1899", serie.getNombre());
        assertEquals("Baran bo Odar, Jantje Friese", serie.getCreador()); 
        assertEquals(1, serie.getTemporadas());
        assertEquals(2022, serie.getAnyo());       
        assertEquals(7, serie.getId_genero());
        assertEquals(7.24, serie.getPrecio(), 0.001);
        assertEquals(5, serie.getCantidad());
        assertNotNull(serie.getDescripcion());
        assertEquals("img/s01.jpeg", serie.getImagen());
        
    }

	
	

}

