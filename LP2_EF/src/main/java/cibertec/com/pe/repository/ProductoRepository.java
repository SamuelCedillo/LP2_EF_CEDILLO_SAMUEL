package cibertec.com.pe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cibertec.com.pe.model.ProductoEntity;
@Repository
public interface ProductoRepository  extends JpaRepository<ProductoEntity, Integer>{

}
