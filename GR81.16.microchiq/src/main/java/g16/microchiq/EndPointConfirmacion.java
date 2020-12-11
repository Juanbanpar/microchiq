package g16.microchiq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import g16.microchiq.dao.CompraDAO;
import g16.microchiq.dto.Compra;
import g16.microchiq.dto.Mensaje;


@RestController
public class EndPointConfirmacion {
	@Autowired
	CompraDAO CompraDAO;
	
	//FUNCIONALIDADES PARA ADMINISTRADOR:
	
	
	//Permite mostrar todos los mensajes existentes 
		@RequestMapping(value="/Allcompras", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Compra>> getAllCompras(){
			List<Compra> compras = CompraDAO.getAllCompras();
			ResponseEntity<List<Compra>> r = ResponseEntity.status(HttpStatus.OK).body(compras);
			return r;
		}
		
		//Permite borrar todos los mensajes
		@RequestMapping(value = "/deleteAllcompras", method = RequestMethod.DELETE)
		public ResponseEntity<Void> deleteAll(){
			List<Compra> compras = CompraDAO.getAllCompras();
			CompraDAO.deleteAll(compras);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		//Muestra el mensaje por su identificador de compra
		@RequestMapping(value="/comprasId/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Compra> findById(@PathVariable int id){
			Compra compras = CompraDAO.findById(id);
			ResponseEntity<Compra> r = ResponseEntity.status(HttpStatus.OK).body(compras);
			return r;
		}
		
		//Muestra los mensajes que tengan una determinada tarjeta de credito asignada
		@RequestMapping(value="/compras/{tarjeta}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Compra>> findByTarjeta(@PathVariable String tarjeta){
			List<Compra> compras = CompraDAO.findByTarjeta(tarjeta);
			ResponseEntity<List<Compra>> r = ResponseEntity.status(HttpStatus.OK).body(compras);
			return r;
		}
		
		//Borra un mensaje con un determinado Id de compra
		@RequestMapping(value = "/compras/delete/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<Void> deleteById(@PathVariable int id){
			Compra compraBorrar = CompraDAO.findById(id);
			CompraDAO.delete(compraBorrar);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		//-----------------------------------------------------------------------
		
		//FUNCIONALIDADES PARA VENDEDORES:
		
		//Muestra todos los mensajes de un determinado vendedor
		@RequestMapping(value="/MisCompras/{vendedor}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Compra>> findByVendedor(@PathVariable String vendedor){
			List<Compra> mensajesVendedor = CompraDAO.findByVendedor(vendedor);
			ResponseEntity<List<Compra>> r = ResponseEntity.status(HttpStatus.OK).body(mensajesVendedor);
			return r;
		}
		
		//Muestra todos los mensajes de un vendedor y que vienen de un determinado cliente
		@RequestMapping(value="/VendedorAndComprador/{vendedor}/{comprador}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Compra>> findByVendedorAndComprador(@PathVariable String vendedor, @PathVariable String comprador){
			List<Compra> mensajesVendedor = CompraDAO.findByVendedorAndComprador(vendedor, comprador);
			ResponseEntity<List<Compra>> r = ResponseEntity.status(HttpStatus.OK).body(mensajesVendedor);
			return r;
		}
		
		//Muestra todos los mensajes de un vendedor y un determinado producto
		@RequestMapping(value="/VendedorAndProducto/{vendedor}/{producto}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<Compra>> findByVendedorAndProducto(@PathVariable String vendedor, @PathVariable String producto){
			List<Compra> mensajesVendedor = CompraDAO.findByVendedorAndComprador(vendedor, producto);
			ResponseEntity<List<Compra>> r = ResponseEntity.status(HttpStatus.OK).body(mensajesVendedor);
			return r;
		}
		
		@RequestMapping(value = "/compras/new",  method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Void> crearCompras(@RequestBody Compra compra){
			CompraDAO.save(compra);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		
}
