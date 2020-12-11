package g16.microchiq.dao;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import g16.microchiq.dto.Compra;

public interface CompraDAO extends CrudRepository<Compra, Integer>{

	@Query("Select m from Compra m")
    public @ResponseBody List<Compra> getAllCompras();
	
	public Compra findById(int id);
	public List<Compra> findByTarjeta(String tarjeta);
	public List<Compra> findByVendedor(String vendedor);
	public List<Compra> findByVendedorAndComprador(String vendedor, String comprador);
}
