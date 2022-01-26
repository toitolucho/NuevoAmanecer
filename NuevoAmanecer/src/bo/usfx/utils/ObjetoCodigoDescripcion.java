package bo.usfx.utils;

public class ObjetoCodigoDescripcion {
	private String codigoObjeto;
	private String descripcionObjeto;
	/**
	 * @param codigoObjeto
	 * @param descripcionObjeto
	 */
	public ObjetoCodigoDescripcion(String codigoObjeto, String descripcionObjeto) {
		super();
		this.codigoObjeto = codigoObjeto;
		this.descripcionObjeto = descripcionObjeto;
	}
	/**
	 * @return the codigoObjeto
	 */
	public String getCodigoObjeto() {
		return codigoObjeto;
	}
	/**
	 * @param codigoObjeto the codigoObjeto to set
	 */
	public void setCodigoObjeto(String codigoObjeto) {
		this.codigoObjeto = codigoObjeto;
	}
	/**
	 * @return the descripcionObjeto
	 */
	public String getDescripcionObjeto() {
		return descripcionObjeto;
	}
	/**
	 * @param descripcionObjeto the descripcionObjeto to set
	 */
	public void setDescripcionObjeto(String descripcionObjeto) {
		this.descripcionObjeto = descripcionObjeto;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return descripcionObjeto;
		
	}
	
	

}
