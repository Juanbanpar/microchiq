package g16.microchiq.dto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Base64;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.apache.commons.io.IOUtils;

import g16.microchiq.dto.Usuario;

@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public Producto(int idproduct, String categoria, String descripcion, String estado, String imagen, int precio,
			String titulo, g16.microchiq.dto.Usuario comprador, g16.microchiq.dto.Usuario vendedor) {
		super();
		this.idproduct = idproduct;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.estado = estado;
		this.imagen = imagen;
		this.precio = precio;
		this.titulo = titulo;
		this.comprador = comprador;
		this.vendedor = vendedor;
	}
	public Producto() {
		super();
	}
	
	@Id
	private int idproduct;
	private String categoria;
	private String descripcion;
	private String estado;
	private String imagen;
	private int precio;
	private String titulo;
	
	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="comprador")
	private Usuario comprador;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="vendedor")
	private Usuario vendedor;

		
	
	public int getIdproduct() {
		return idproduct;
	}
	
	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(InputStream imagen) {
		this.imagen = encodeFileToBase64Binary(imagen);
	}
	
	public void setBase64(String imagen) {
		this.imagen = imagen;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	private String encodeFileToBase64Binary(InputStream inputStream){
        String encodedfile = null;
        try {
        	byte[] bytes = IOUtils.toByteArray(inputStream);
        	encodedfile = Base64.getEncoder().encodeToString(bytes);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }
}
