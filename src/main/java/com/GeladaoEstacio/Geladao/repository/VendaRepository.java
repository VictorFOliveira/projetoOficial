package com.GeladaoEstacio.Geladao.repository;

import com.GeladaoEstacio.Geladao.entities.Usuario;
import com.GeladaoEstacio.Geladao.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("SELECT v FROM Venda v WHERE v.usuario = :usuario")
    List<Venda> findByUsuario(Usuario usuario);

    List<Venda> findByDataVendaBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

}
