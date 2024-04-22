package com.GeladaoEstacio.Geladao.repository;


import com.GeladaoEstacio.Geladao.entities.ItensVendidos;
import com.GeladaoEstacio.Geladao.entities.Produto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ItensVendidosRepository extends JpaRepository<ItensVendidos, Long> {

}
