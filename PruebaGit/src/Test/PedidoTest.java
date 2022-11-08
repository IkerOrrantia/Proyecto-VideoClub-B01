package Test;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import Clases.Estado;
import Clases.Pedido;

public class PedidoTest {

	private Pedido p;
	
	@Before
	public void setUp(){
		p = new Pedido(null, Date.valueOf("2017-11-10"), null , Estado.PREPARACION);
	}
	
	
	@Test
	public void testgetEstado() {
		assertEquals(Estado.PREPARACION, p.getEstado());
	}
	
	public void testgetfecha() {
		assertEquals(Date.valueOf("2017-11-10"), p.getFecha());
		}
	
}
