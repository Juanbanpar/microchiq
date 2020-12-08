package g16.microchiq.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import g16.microchiq.dto.Usuario;
import g16.microchiq.dto.Producto;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
	
	@Query("Select u from Usuario u")
	public @ResponseBody List<Usuario> getAllUsers();
	
	public Usuario findByEmail(String email);
	
	public Usuario deleteByEmail(String email);
}
