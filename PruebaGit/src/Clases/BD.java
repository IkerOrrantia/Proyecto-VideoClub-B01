package Clases;
import java.sql.*;
import java.util.*; 
import java.util.logging.*;

import Clases.TEmpleado;
import Clases.TPagoNomina;


public class BD {
	
	private static Exception lastError = null;  // Informaci�n de �ltimo error SQL ocurrido	
	
	/** Inicializa una BD SQLITE y devuelve una conexi�n con ella
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexi�n con la base de datos indicada. Si hay alg�n error, se devuelve null
	 */
	public static Statement initBD( String nombreBD ) {
		try 
		{
			Class.forName("org.sqlite.JDBC");
			String dburl = "jdbc:sqlite:" + nombreBD;
			Connection conexion = DriverManager.getConnection(dburl);
				 
			Statement st = conexion.createStatement();
			log( Level.INFO, "Conectada base de datos " + nombreBD, null );
			return st;
		} catch (ClassNotFoundException | SQLException e) {
			lastError = e;
			log( Level.SEVERE, "Error en conexi�n de base de datos " + nombreBD, e );
			e.printStackTrace();
			return null;
		}
	}
	
	/** Devuelve statement para usar la base de datos
	 * @param con	Conexi�n ya creada y abierta a la base de datos
	 * @return	sentencia de trabajo si se crea correctamente, null si hay cualquier error
	 */
	public static Statement usarBD( Connection con ) {
		try {
			Statement statement = con.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
			return statement;
		} catch (SQLException e) {
			lastError = e;
			log( Level.SEVERE, "Error en uso de base de datos", e );
			e.printStackTrace();
			return null;
		}
	}
	
	/** Cierra la base de datos abierta
	 * @param con	Conexi�n abierta de la BD
	 * @param st	Sentencia abierta de la BD
	 */
	public static void cerrarBD( Connection con, Statement st ) {
		try {
			if (st!=null) st.close();
			if (con!=null) con.close();
			log( Level.INFO, "Cierre de base de datos", null );
		} catch (SQLException e) {
			lastError = e;
			log( Level.SEVERE, "Error en cierre de base de datos", e );
			e.printStackTrace();
		}
	}
	
	
	/** Devuelve la informaci�n de excepci�n del �ltimo error producido por cualquiera 
	 * de los m�todos de gesti�n de base de datos
	 */
	public static Exception getLastError() {
		return lastError;
	}
	
	/////////////////////////////////////////////////////////////////////
	//                      Operaciones con Empleados                  //
	/////////////////////////////////////////////////////////////////////
	
	public static boolean InsertarEmpleado(TEmpleado objEmp) {
		String sentSQL = "";
		try {
			sentSQL = "insert into empleados values(" +
					""+ objEmp.getCodEmpleado() + "," +
					"'" + objEmp.getNombre() + "'," +
					"'" + objEmp.getApell() + "'," +
					"'" + objEmp.getTelefono() + "'," +
					"" + objEmp.getNomina() + ")";
			
			Statement st = initBD("NominaEmpleado.db");
			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD tabla empleados añadida " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que a�adir 1 - error si no
				log( Level.SEVERE, "Error en insert de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			lastError = e;
			e.printStackTrace();
			return false;
		}		
	}
	
	public static boolean InsertarPagoNomina(TPagoNomina objPagoNom) {
		String sentSQL = "";
		try {
			sentSQL = "insert into pagoEmpleado values(" +
					""+ objPagoNom.getCodNomina() + "" +
					"" + objPagoNom.getValorMensual() + "," +					
					"" + objPagoNom.getCodEmpleadoFK() + ")";
			
			Statement st = initBD("NominaEmpleado.db");
			int val = st.executeUpdate( sentSQL );
			log( Level.INFO, "BD tabla pagoEmpleado añadida " + val + " fila\t" + sentSQL, null );
			if (val!=1) {  // Se tiene que a�adir 1 - error si no
				log( Level.SEVERE, "Error en insert de BD\t" + sentSQL, null );
				return false;  
			}
			return true;
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			lastError = e;
			e.printStackTrace();
			return false;
		}		
	}
	
	public static ArrayList<TEmpleado> getEmpleados(){
		String sentSQL = "";
		ArrayList<TEmpleado> lsEmpleado = new ArrayList<TEmpleado>();
		try {
			 Statement st = initBD("NominaEmpleado.db");
			 sentSQL = "Select * from empleados";
			 ResultSet rs = st.executeQuery(sentSQL);
		
			 while (rs.next()) {
				TEmpleado objEmp = new TEmpleado(rs.getInt( "codEmpleados" ), rs.getString( "nombre" ), rs.getString( "apellido" ), rs.getString( "telefono" ), rs.getDouble( "pagoNomina" ), null);
				lsEmpleado.add(objEmp);
			}
			rs.close();
			log( Level.INFO, "BD\t" + sentSQL, null );
			return lsEmpleado;
			
			
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			lastError = e;
			e.printStackTrace();
			return null;
		}

	}
	
	public static ArrayList<TPagoNomina> getPagoNomina(){
		
		String sentSQL = "";
		ArrayList<TPagoNomina> lsP = new ArrayList<TPagoNomina>();
		try {
			 Statement st = initBD("NominaEmpleado.db");
			 sentSQL = "Select * from pagoEmpleado";
			 ResultSet rs = st.executeQuery(sentSQL);
		
			 while (rs.next()) 
			 {
				 TPagoNomina objP = new TPagoNomina();
				 objP.setCodNomina(rs.getInt("codPago"));
				 objP.setValorMensual(rs.getDouble("valorMensual"));
				 objP.setCodEmpleadoFK(rs.getInt("codEmpleadoFK"));
				 lsP.add(objP);
			 }
			 rs.close();
			 log( Level.INFO, "BD\t" + sentSQL, null );
			 return lsP;
			
			
		} catch (SQLException e) {
			log( Level.SEVERE, "Error en BD\t" + sentSQL, e );
			lastError = e;
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean ModificarEmpleado(TEmpleado objEmp)
	{
			return false;
			
	}
	
	/////////////////////////////////////////////////////////////////////
	//                      Métodos privados                           //
	/////////////////////////////////////////////////////////////////////

	// Devuelve el string "securizado" para volcarlo en SQL
	// (Implementacion 1) Sustituye ' por '' y quita saltos de l�nea
	// (Implementacion 2) Mantiene solo los caracteres seguros en espa�ol
	private static String secu( String string ) {
		// Implementacion (1)
		// return string.replaceAll( "'",  "''" ).replaceAll( "\\n", "" );
		// Implementacion (2)
		StringBuffer ret = new StringBuffer();
		for (char c : string.toCharArray()) {
			if ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ��������������.,:;-_(){}[]-+*=<>'\"�?�!&%$@#/\\0123456789".indexOf(c)>=0) ret.append(c);
		}
		return ret.toString();
	}
		
	
	/////////////////////////////////////////////////////////////////////
	//                      Logging                                    //
	/////////////////////////////////////////////////////////////////////
	
	public static Logger logger = null;  // cambio en tarea 2 para poderlo utilizar desde all�
	// M�todo p�blico para asignar un logger externo
	/** Asigna un logger ya creado para que se haga log de las operaciones de base de datos
	 * @param logger	Logger ya creado
	 */
	public static void setLogger( Logger logger ) {
		BD.logger = logger;
	}
	// Metodo local para loggear (si no se asigna un logger externo, se asigna uno local)
	private static void log( Level level, String msg, Throwable excepcion ) {
		if (logger==null) {  // Logger por defecto local:
			logger = Logger.getLogger( BD.class.getName() );  // Nombre del logger - el de la clase
			logger.setLevel( Level.ALL );  // Loguea todos los niveles
			try {
				// logger.addHandler( new FileHandler( "bd-" + System.currentTimeMillis() + ".log.xml" ) );  // Y saca el log a fichero xml
				logger.addHandler( new FileHandler( "bd.log.xml", true ) );  // Y saca el log a fichero xml
			} catch (Exception e) {
				logger.log( Level.SEVERE, "No se pudo crear fichero de log", e );
			}
		}
		if (excepcion==null)
			logger.log( level, msg );
		else
			logger.log( level, msg, excepcion );
	}
	

}