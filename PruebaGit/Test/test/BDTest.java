package test;


import static org.junit.Assert.*;

import java.beans.Statement;
import java.sql.Connection;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import clases.BD;
import clases.TEmpleado;
import clases.TPagoNomina;

public class BDTest {

	@Before
	public void setUp() throws Exception {
		BD.initBD("NominaEmpleado.db");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEmpleados() {
		ArrayList<TEmpleado> lP = BD.getEmpleados();
		assertEquals( 4, lP.size() );
		int antCod = -1;
		for (TEmpleado p : lP) {
			assertTrue( p.getCodEmpleado() > antCod );
			antCod = p.getCodEmpleado();
		}
	}
	
	@Test
	public void testMayorValor() {
		ArrayList<TEmpleado> lP = BD.getEmpleados();
		ArrayList<TPagoNomina> lPN = BD.getPagoNomina();
		double mayor = 0;
		int cod=0;
		for(TPagoNomina p: lPN)
		{
			if(p.getValorMensual() > mayor)
			{
				mayor = p.getValorMensual();
				cod = p.getCodEmpleadoFK();
			}
				
		}
		double valor = lP.get(cod-1).getNomina();
		assertTrue(mayor >= valor);
		TEmpleado objE = new TEmpleado(7, "Jaime", "Altazona", 600000005, 1750.00, null);
		assertTrue(BD.InsertarEmpleado(objE)); //ESte metodo al crear un nuevo usuario causara error en el anterior, y al modificar el anterior para que cuadre pasara el caso contrario
	}

}
