package g16.microchiq.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import g16.microchiq.dto.Producto;

public interface ProductoDAO extends CrudRepository<Producto, Integer> {

	@Query("Select p from Producto p")
	public @ResponseBody List<Producto> getAllProducts();
	
	//@Query("Select p from Producto p WHERE (p.titulo LIKE '%:cad%' OR p.descripcion LIKE '%:cad%') AND p.categoria = :cat  AND p.estado = 'Disponible'")//:estado?
	//public List<List<Producto>> getAllProductsbyCategory(String cadena);
	
}