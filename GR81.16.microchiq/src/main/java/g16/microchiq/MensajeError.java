package g16.microchiq;

import java.io.Serializable;

public class MensajeError implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MensajeError(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	public MensajeError() {
		super();
	}
	
	private String codigo;
	private String mensaje;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
