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
import g16.microchiq.dao.ChatDAO;
import g16.microchiq.dto.Mensaje;
import g16.microchiq.dto.Producto;
import g16.microchiq.dto.Usuario;

import java.util.List;

@RestController
public class EndPointMensaje {
	@Autowired
	ChatDAO chatDAO;
	
	//FUNCIONALIDADES PARA ADMINISTRADOR:
	
	
	//Permite mostrar todos los mensajes existentes 
	@RequestMapping(value="/Allmensajes", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Mensaje>> getAllMessages(){
		List<Mensaje> mensaje = chatDAO.getAllMessages();
		ResponseEntity<List<Mensaje>> r = ResponseEntity.status(HttpStatus.OK).body(mensaje);
		
		return r;
	}
	 //Permite borrar todos los mensajes
	@RequestMapping(value = "/deleteAllmensajes", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAll(){
		List<Mensaje> mensajeBorrar = chatDAO.getAllMessages();
		chatDAO.deleteAll(mensajeBorrar);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	//Muestra el mensaje por su identificador
	@RequestMapping(value="/mensajes/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Mensaje> findById(@PathVariable int id){
		Mensaje mensaje = chatDAO.findById(id);
		ResponseEntity<Mensaje> r = ResponseEntity.status(HttpStatus.OK).body(mensaje);
		return r;
	}
	//Borra un mensaje con un determinado Id
	@RequestMapping(value = "/mensajes/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable int id){
		Mensaje mensajeBorrar = chatDAO.findById(id);
		chatDAO.delete(mensajeBorrar);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//-----------------------------------------------------------------------------------------
	
	
	//FUNCIONALIDADES PARA CLIENTES:
	
	//Permite mostrar los mensajes que te envian
	@RequestMapping(value="/mensajes/destino/{destino}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Mensaje>> findByDestino(@PathVariable String destino){
		List<Mensaje> mensajes = chatDAO.findByDestino(destino);
		ResponseEntity<List<Mensaje>> r = ResponseEntity.status(HttpStatus.OK).body(mensajes);
		return r;
	}
	
	//Permite mostrar los mensajes que te ha enviado un usuario en concreto
	@RequestMapping(value="/mensajes/DestinoYOrigen/{destino}/{origen}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Mensaje>> findByDestinoAndOrigen(@PathVariable String destino, @PathVariable String origen){
		List<Mensaje> mensajes = chatDAO.findByDestinoAndOrigen(destino, origen);
		ResponseEntity<List<Mensaje>> r = ResponseEntity.status(HttpStatus.OK).body(mensajes);
		return r;
	}
	//Permite mostrar los mensajes que te envian y que poseen un determinado contenido en el cuerpo del mensaje
	@RequestMapping(value="/mensajes/DestinoYContenido/{destino}/{contenido}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Mensaje>> findByDestinoAndContenidoContaining(@PathVariable String destino, @PathVariable String contenido){
		List<Mensaje> mensajes = chatDAO.findByDestinoAndContenidoContaining(destino, contenido);
		ResponseEntity<List<Mensaje>> r = ResponseEntity.status(HttpStatus.OK).body(mensajes);
		return r;
	}
	
	//Permite borrar todos los mensajes que has recibido, 
	//tambien valdria para administrador (borrar los mensajes que ha recibido una determinada persona)
	@RequestMapping(value = "/mensajes/deleteDestino/{destino}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteByDestino(@PathVariable String destino){
		List<Mensaje> mensajeBorrar = chatDAO.findByDestino(destino);
		chatDAO.deleteAll(mensajeBorrar);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	//Permite borrar un mensaje que hayas recibido con un determinado Id
	//tambien valdria para administrador (borrar un determinado mensaje de un usuario)
	@RequestMapping(value = "/mensajes/deleteByIdAndDestino/{id}/{destino}", method = RequestMethod.DELETE)
		public ResponseEntity<Void> deleteByIdAndDestino(@PathVariable int id, @PathVariable String destino){
			Mensaje mensajeBorrar = chatDAO.findById(id);
			chatDAO.delete(mensajeBorrar);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
}
