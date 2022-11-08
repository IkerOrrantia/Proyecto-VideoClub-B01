package clases;

public class TPagoNomina {
	
	private int aCodNomina;
	private double aValorMensual;
	private int aCodEmpleadoFK;
	
	
	public TPagoNomina() {}
	
	
	/**
	 * @return the aCodNomina
	 */
	public int getCodNomina() {
		return aCodNomina;
	}
	/**
	 * @param aCodNomina the aCodNomina to set
	 */
	public void setCodNomina(int aCodNomina) {
		this.aCodNomina = aCodNomina;
	}
	/**
	 * @return the aValorMensual
	 */
	public double getValorMensual() {
		return aValorMensual;
	}
	/**
	 * @param aValorMensual the aValorMensual to set
	 */
	public void setValorMensual(double aValorMensual) {
		this.aValorMensual = aValorMensual;
	}
	/**
	 * @return the aCodEmpleadoFK
	 */
	public int getCodEmpleadoFK() {
		return aCodEmpleadoFK;
	}
	/**
	 * @param aCodEmpleadoFK the aCodEmpleadoFK to set
	 */
	public void setCodEmpleadoFK(int aCodEmpleadoFK) {
		this.aCodEmpleadoFK = aCodEmpleadoFK;
	}

}
