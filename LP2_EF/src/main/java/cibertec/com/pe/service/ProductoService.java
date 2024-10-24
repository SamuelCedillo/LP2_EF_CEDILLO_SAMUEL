package cibertec.com.pe.service;

import java.util.List;

import cibertec.com.pe.model.ProductoEntity;

public interface ProductoService {
	List<ProductoEntity>buscarTodosProductos();
	ProductoEntity buscarProductosPorId(Integer id);
}
