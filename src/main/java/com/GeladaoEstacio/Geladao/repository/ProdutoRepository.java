package com.GeladaoEstacio.Geladao.repository;


import com.GeladaoEstacio.Geladao.entities.Produto;
import com.GeladaoEstacio.Geladao.entities.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Produto p SET p.quantidade = ?2 WHERE p.produtoId = ?1")
    void atualizarEstoque(Long id, Integer quantidade);


    Produto findByNomeProduto(String nomeProduto);

}
