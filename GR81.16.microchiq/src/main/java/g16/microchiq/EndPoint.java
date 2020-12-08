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

import java.util.List;

@RestController
public class EndPoint {

	@Autowired
	ProductoDAO productoDAO;
	
	@RequestMapping(value="/productos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> getAllProducts(){
		List<Producto> list = productoDAO.getAllProducts();
		ResponseEntity<List<Producto>> r = ResponseEntity.status(HttpStatus.OK).body(list);
		
		return r;
	}
	
	@RequestMapping(value="/productos/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> findById(@PathVariable int id){
		Producto product = productoDAO.findById(id);
		ResponseEntity<Producto> r = ResponseEntity.status(HttpStatus.OK).body(product);
		
		return r;
	}
	
	
	@RequestMapping(value="/productos/comprador", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> getAllProductsBuyed(@RequestBody Usuario comprador){
		List<Producto> list = productoDAO.getAllProductsBuyed(comprador);
		ResponseEntity<List<Producto>> r = ResponseEntity.status(HttpStatus.OK).body(list);
		
		return r;
	}
	
	@RequestMapping(value="/productos/vendedor", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> getAllProductsbyUser(@RequestBody Usuario vendedor){
		List<Producto> list = productoDAO.getAllProductsbyUser(vendedor);
		ResponseEntity<List<Producto>> r = ResponseEntity.status(HttpStatus.OK).body(list);
		
		return r;
	}
	
	
	@RequestMapping(value = "/productos/buscar/{search}",  method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Producto> getAllProductsByString(@PathVariable String search){
		return productoDAO.getAllProductsByString(search);
	}
	
	@RequestMapping(value = "/productos/buscaravanzado/{categoria}/{search}",  method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Producto> getAllProductsByStringAndCategoria(@PathVariable("categoria") String categoria, @PathVariable("search") String search){
		return productoDAO.getAllProductsByStringAndCategoria(categoria, search);
	}
	
	@RequestMapping(value = "/productos/delete/{id}",  method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable int id){
		Producto productoBorrar = productoDAO.findById(id);
		
		productoDAO.delete(productoBorrar);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/productos/new",  method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> crearProductos(@RequestBody Producto product){
		productoDAO.save(product);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/productos/edit",  method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> editarProductos(@RequestBody Producto product){
		productoDAO.save(product);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/productos/buy",  method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> buyProductos(@RequestBody Producto product, @RequestBody Usuario comprador){
		product.setEstado("Vendido");
		product.setComprador(comprador);
		
		productoDAO.save(product);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
