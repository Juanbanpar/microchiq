package g16.microchiq.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
public class Compra implements Serializable {

	private static final long serialVersionUID = 1L;
   
	@Id
	private int id;
	private String tarjeta;
    private int precio;
    private String producto;
    private int cantidad;
    private String vendedor;
    private String comprador;
	
    public Compra(int id, String tarjeta, int precio, String producto, int cantidad, String vendedor,
			String comprador) {
		super();
		this.id = id;
		this.tarjeta = tarjeta;
		this.precio = precio;
		this.producto = producto;
		this.cantidad = cantidad;
		this.vendedor = vendedor;
		this.comprador = comprador;
	}
    
    public Compra() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}

}
