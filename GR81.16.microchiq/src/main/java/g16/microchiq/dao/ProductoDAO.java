package g16.microchiq.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import g16.microchiq.dto.Usuario;
import g16.microchiq.dto.Producto;

public interface ProductoDAO extends CrudRepository<Producto, Integer> {

	@Query("Select p from Producto p")
	public @ResponseBody List<Producto> getAllProducts();
	
	public Producto findById(int id);
	
	//@Query("Select p from Producto p WHERE (p.titulo LIKE '%:cad%' OR p.descripcion LIKE '%:cad%') AND p.categoria = :cat  AND p.estado = 'Disponible'")//:estado?
	//public List<List<Producto>> getAllProductsbyCategory(String cadena);
	
	@Query("FROM Producto p WHERE (p.vendedor = ?1)")
	public List<Producto> getAllProductsbyUser(Usuario vendedor);
	@Query("FROM Producto p WHERE (p.comprador = ?1)")
	public List<Producto> getAllProductsBuyed(Usuario comprador);
	
		
	@Query("FROM Producto p WHERE ((p.titulo like %?1%) OR (p.descripcion like %?1%) AND (p.estado = 'Disponible'))")
	public List<Producto> getAllProductsByString(String search);
}
