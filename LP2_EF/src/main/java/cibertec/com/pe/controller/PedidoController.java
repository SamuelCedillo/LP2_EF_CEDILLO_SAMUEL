package cibertec.com.pe.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import cibertec.com.pe.model.DetallePedidoEntity;
import cibertec.com.pe.model.Pedido;
import cibertec.com.pe.model.PedidoEntity;
import cibertec.com.pe.model.UsuarioEntity;
import jakarta.servlet.http.HttpSession;

public class PedidoController {
	private final PedidoService pedidoService;
	
	@GetMapping("/guardar_compra")
	public String guardarFactura(HttpSession session) {
		String correoUsuario = session.getAttribute("usuario").toString();
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setCorreo(correoUsuario);
		
		PedidoEntity pedidoEntity= new PedidoEntity();
		PedidoEntity.setFechaCompra (LocalDate.now());
		pedidoEntity.setUsuarioEntity(usuarioEntity);
		
		List<DetallePedidoEntity>detallePedidoEntityList = new ArrayList<DetallePedidoEntity>();
		
		List<Pedido>productoSesion= null;
		if (session..getAttribute("carrito") ==null) {
			productoSesion = new ArrayList<Pedido>();
		} else {
			productoSesion= (List<Pedido>)session.getAttribute("carrito");
		}
		for(Pedido ped: productoSesion) {
			DetallePedidoEntity detallePedidoEntity = new DetallePedidoEntity();
			ProductoEntity productoEntity = new ProductoEntity();
			productoEntity.setProductoId(ped.getProductoId());
			
			detallePedidoEntity.setProductoEntity(productoEntity);
			detallePedidoEntity.setCantidad(ped.getCantidad());
			detallePedidoEntity.setPedidoEntity(pedidoEntity);
			detallePedidoEntityList.add(detallePedidoEntity);
		}
		
		pedidoEntity.setDetallePedido(detallePedidoEntityList);
		pedidoService.crearPedido(pedidoEntity);
		session.removeAttribute("carrito");
		return "redirect:/menu";
	}
}
