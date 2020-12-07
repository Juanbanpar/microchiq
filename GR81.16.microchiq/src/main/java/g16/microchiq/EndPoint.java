package g16.microchiq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import g16.microchiq.dao.ProductoDAO;
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
	
}
