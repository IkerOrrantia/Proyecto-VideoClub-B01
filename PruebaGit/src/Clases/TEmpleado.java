package Clases;

import java.util.ArrayList;

public class TEmpleado {
	
	private int aCodEmpleado;
	private String aNombre;
	private String aApell;
	private int aTelefono;
	private Double aNomina;
	
	private ArrayList<TPagoNomina> lsPagoNomina;
	
	public TEmpleado(int pCodEmp, String pNomb, String pApell, int pTelef, Double pNomina, ArrayList<TPagoNomina> lsPN) {
		setLsPagoNomina(lsPN);
		this.aCodEmpleado = pCodEmp;
		this.aNombre = pNomb;
		this.aApell = pApell;
		this.aTelefono = pTelef;
		this.aNomina = pNomina;
	}

	/**
	 * @return the aCodEmpleado
	 */
	public int getCodEmpleado() {
		return aCodEmpleado;
	}

	/**
	 * @param aCodEmpleado the aCodEmpleado to set
	 */
	public void setCodEmpleado(int aCodEmpleado) {
		this.aCodEmpleado = aCodEmpleado;
	}

	/**
	 * @return the aNombre
	 */
	public String getNombre() {
		return aNombre;
	}

	/**
	 * @param aNombre the aNombre to set
	 */
	public void setNombre(String aNombre) {
		this.aNombre = aNombre;
	}

	/**
	 * @return the aApell
	 */
	public String getApell() {
		return aApell;
	}

	/**
	 * @param aApell the aApell to set
	 */
	public void setApell(String aApell) {
		this.aApell = aApell;
	}

	/**
	 * @return the aTelefono
	 */
	public int getTelefono() {
		return aTelefono;
	}

	/**
	 * @param aTelefono the aTelefono to set
	 */
	public void setTelefono(int aTelefono) {
		this.aTelefono = aTelefono;
	}

	/**
	 * @return the aNomina
	 */
	public Double getNomina() {
		return aNomina;
	}

	/**
	 * @param aNomina the aNomina to set
	 */
	public void setNomina(Double aNomina) {
		this.aNomina = aNomina;
	}

	/**
	 * @return the lsPagoNomina
	 */
	public ArrayList<TPagoNomina> getLsPagoNomina() {
		return lsPagoNomina;
	}

	/**
	 * @param lsPagoNomina the lsPagoNomina to set
	 */
	public void setLsPagoNomina(ArrayList<TPagoNomina> lsPagoNomina) {
		this.lsPagoNomina = lsPagoNomina;
	}
	
	

}
