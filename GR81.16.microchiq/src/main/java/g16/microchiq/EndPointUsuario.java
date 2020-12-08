package g16.microchiq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import g16.microchiq.dao.ProductoDAO;
import g16.microchiq.dao.UsuarioDAO;
import g16.microchiq.dto.*;

import java.util.List;

@RestController
public class EndPointUsuario {
	@Autowired
	UsuarioDAO usuarioDAO;
	
	@RequestMapping(value="/usuarios", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Usuario>> getAllUsers(){
		List<Usuario> list = usuarioDAO.getAllUsers();
		ResponseEntity<List<Usuario>> r = ResponseEntity.status(HttpStatus.OK).body(list);
		
		return r;
	}
	
	@RequestMapping(value = "/usuarios/{email}",  method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> findByEmail(@PathVariable String email){
		Usuario user = usuarioDAO.findByEmail(email);
		
		ResponseEntity<Usuario> r = ResponseEntity.status(HttpStatus.OK).body(user);
		return r;
	}
	
	@RequestMapping(value = "/usuarios/delete/{email}",  method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteByEmail(@PathVariable String email){
		Usuario usuarioBorrar = usuarioDAO.findByEmail(email);
		
		usuarioDAO.delete(usuarioBorrar);
		
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
