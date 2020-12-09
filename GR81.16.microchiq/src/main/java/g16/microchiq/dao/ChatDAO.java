package g16.microchiq.dao;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.ResponseBody;

import g16.microchiq.dto.Mensaje;


	public interface ChatDAO extends CrudRepository<Mensaje, Integer>{
		@Query("Select m from Mensaje m")
	    public @ResponseBody List<Mensaje> getAllMessages();
		
		public Mensaje findById(int id);
		public List<Mensaje> findByDestino(String destino);
		public List<Mensaje> findByDestinoAndOrigen(String destino, String origen);
		public List<Mensaje> findByDestinoAndContenidoContaining(String destino, String contenido);    
	}