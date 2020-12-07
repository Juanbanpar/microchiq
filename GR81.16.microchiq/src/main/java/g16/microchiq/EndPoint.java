package g16.microchiq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import g16.microchiq.*;
import g16.microchiq.dao.ProductoDAO;
import g16.microchiq.dto.*;

import java.util.List;

@Controller
public class EndPoint {

	@Autowired
	ProductoDAO productoDAO;
	
	@RequestMapping(value="/productos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Producto> getAllProducts(){
		return productoDAO.getAllProducts();
	}
	
}
