package cibertec.com.pe.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes.Name;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import cibertec.com.pe.model.DetallePedidoEntity;
import cibertec.com.pe.model.Pedido;
import cibertec.com.pe.model.ProductoEntity;
import cibertec.com.pe.model.UsuarioEntity;
import cibertec.com.pe.service.ProductoService;
import cibertec.com.pe.service.UsuarioService;
import cibertec.com.pe.service.impl.PdfService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.engine.AttributeName;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class ProductoController {
	private final ProductoService productoService;
	private final UsuarioService usuarioService;
	private final PdfService pdfService;
	
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}


	public ProductoController(ProductoService productoService, UsuarioService usuarioService, PdfService pdfService) {
		super();
		this.productoService = productoService;
		this.usuarioService = usuarioService;
		this.pdfService = pdfService;
	}


	public ProductoService getProductoService() {
		return productoService;
	}


	@GetMapping("/menu")
    public String menu(Model model, HttpSession session) {
        if(session.getAttribute("usuario") == null) {
            return "redirect:/";
        }
        
        String correoSesion = session.getAttribute("usuario").toString();
        UsuarioEntity usuarioEncontrado = usuarioService.buscarUsuarioPorCorreo(
        		correoSesion);
        model.addAttribute("foto", usuarioEncontrado.getUrlImagen());

        List<ProductoEntity> listaProductos = productoService.buscarTodosProductos();
        model.addAttribute("productos", listaProductos);
       
        //cantidad de pedidos
        List<Pedido>productoSesion = null;
        if (session.getAttribute("carrito")== null) {
			productoSesion = new ArrayList<Pedido>();
		}else {
			productoSesion = (List<Pedido>)session.getAttribute("carrito");
		}
        model.addAttribute("cant_carrito",productoSesion.size());
        
        //mostrar los datos del carrito
        List<DetallePedidoEntity>detallePedidoEntity = new ArrayList<DetallePedidoEntity>();
        Double totalPedido = 0.0;
        
        for(Pedido ped: productoSesion) {
        	DetallePedidoEntity detPed = new DetallePedidoEntity();
        	ProductoEntity productoEntity = productoService.buscarProductosPorId(ped.getProductoId());
        	detPed.setProductoEntity(productoEntity);
        	detPed.setCantidad(ped.getCantidad());
        	detallePedidoEntity.add(detPed);
        	totalPedido += ped.getCantidad() * productoEntity.getPrecioProducto();
        }
        model.addAttribute("carrito", detallePedidoEntity);
        model.addAttribute("total", totalPedido);
        
        return "menu";
    }
	@PostMapping("/agregar_producto")
	public String agregarProducto(HttpSession sesion, @RequestParam("prodId")String prodId, @RequestParam("cant")String cant) {
		
		List<Pedido>productosSesion = null;
		if (sesion.getAttribute("carrito")== null) {
			productosSesion = new ArrayList<Pedido>();
		} else {
			productosSesion = (List<Pedido>)sesion.getAttribute("carrito");
		}
	Integer cantidad = Integer.parseInt(cant);
	Integer productoId = Integer.parseInt(prodId);
	Pedido pedidoNuevo = new Pedido(cantidad, productoId);
	productosSesion.add(pedidoNuevo);
	sesion.setAttribute("carrito", productosSesion);
	return "redirect:/menu";
	}
	
	@GetMapping("/generar_pdf")
    public ResponseEntity<InputStreamResource>generarPDf(HttpSession sesion) throws IOException{
    	// formar los datos para pasarle al pdf
    	List<Pedido>productoSesion = null;
    	if(sesion.getAttribute("carrito")  == null) {
    		productoSesion = new ArrayList<Pedido>();
    	}else {
    		productoSesion = (List<Pedido>)sesion.getAttribute("carrito");
    	}
    	List<DetallePedidoEntity>detallePedidoEntities = new ArrayList<DetallePedidoEntity>();
    	Double totalPedido = 0.0;
    	
    	for(Pedido ped: productoSesion) {
    		DetallePedidoEntity det = new DetallePedidoEntity();
    		ProductoEntity productoEntity = productoService.buscarProductosPorId(ped.getProductoId());
    		det.setProductoEntity(productoEntity);
    		det.setCantidad(ped.getCantidad());
    		detallePedidoEntities.add(det);
    		totalPedido += productoEntity.getPrecioProducto() * ped.getCantidad();
    	}
    	
    	Map<String, Object> datosPdf = new HashMap<String, Object>();
    	datosPdf.put("factura", detallePedidoEntities);
    	datosPdf.put("precio_total", totalPedido);
    	
    	ByteArrayInputStream pdfBytes = pdfService.generarPdf("template_pdf", datosPdf);
    	
    	org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
    	headers.add("Content-Disposition", "inline; filename=productos.pdf");
    	
    	return ResponseEntity.ok()
    			.headers(headers)
    			.contentType(MediaType.APPLICATION_PDF)
    			.body(new InputStreamResource(pdfBytes));
    }
}
