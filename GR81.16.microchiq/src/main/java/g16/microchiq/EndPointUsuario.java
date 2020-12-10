package g16.microchiq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import g16.microchiq.dao.ProductoDAO;
import g16.microchiq.dao.UsuarioDAO;
import g16.microchiq.dto.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

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
	
	@RequestMapping(value = "/usuarios/new",  method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> crearUsuarios(@RequestBody Usuario usuario){
		usuarioDAO.save(usuario);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/usuarios/edit/{email}",  method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> editarUsuarios(@PathVariable String email, @RequestBody Usuario usuarioR){
		Usuario usuarioEdit = usuarioDAO.findByEmail(email);
		
		ResponseEntity<Usuario> response;
		
		if(usuarioEdit != null) {
			MessageDigest md;
			String hash = "";
			try {
				md = MessageDigest.getInstance("MD5");
				md.update(usuarioR.getPasswd().getBytes());
			    byte[] digest = md.digest();
			    hash = DatatypeConverter.printHexBinary(digest);
			    hash = hash.toLowerCase();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			usuarioEdit.setPasswd(hash);
			usuarioEdit.setEmail(usuarioR.getEmail());
			usuarioEdit.setNombre(usuarioR.getNombre());
			usuarioEdit.setApellido1(usuarioR.getApellido1());
			usuarioEdit.setApellido2(usuarioR.getApellido2());
			usuarioEdit.setCiudad(usuarioR.getCiudad());
			
			usuarioDAO.save(usuarioEdit);
			
			response = new ResponseEntity<>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
}
