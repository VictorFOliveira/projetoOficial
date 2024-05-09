package com.GeladaoEstacio.Geladao.web.controller;

import com.GeladaoEstacio.Geladao.entities.Produto;
import com.GeladaoEstacio.Geladao.service.ProdutoService;
import com.GeladaoEstacio.Geladao.web.dtos.ProdutoDTO;
import com.GeladaoEstacio.Geladao.web.exception.ErrorMessage;
import com.GeladaoEstacio.Geladao.web.exception.ProdutoException;
import com.GeladaoEstacio.Geladao.web.returnDtos.ReturnProdutoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Produtos", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de um produto.")
@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ModelMapper modelMapper;


    @Operation(summary = "Criar um novo produto", description = "Recurso para criar um novo produto",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class))),
                    @ApiResponse(responseCode = "409", description = "Produto já cadastrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping("/criar")
    public ResponseEntity<ReturnProdutoDTO> createProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        Produto produtoSalvo = modelMapper.map(produtoDTO, Produto.class);
        produtoService.salvarProduto(produtoSalvo);
        ReturnProdutoDTO returnProdutoDTO = modelMapper.map(produtoSalvo, ReturnProdutoDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnProdutoDTO);
    }


    @Operation(summary = "buscar um  produto", description = "Recurso para buscar um produto")
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ReturnProdutoDTO> buscarProdutoId(@PathVariable Long id) {
        Produto produto = produtoService.getProdutoById(id);
        ReturnProdutoDTO returnProdutoDTO = modelMapper.map(produto, ReturnProdutoDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnProdutoDTO);
    }

    @Operation(summary = "buscar todos produtos", description = "Recurso para buscar todos os produtos")
    @GetMapping("/buscarProdutos")
    public ResponseEntity<List<ReturnProdutoDTO>> buscarTodosProdutos() {
        List<Produto> produtos = produtoService.getAllProdutos();
        List<ReturnProdutoDTO> returnProdutoDTOs = produtos.stream()
                .map(produto -> modelMapper.map(produto, ReturnProdutoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(returnProdutoDTOs);
    }

    @DeleteMapping("/deletarProduto/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable Long id) {
        try {
            produtoService.deletarProdutoPorId(id);
            return ResponseEntity.noContent().build();
        } catch (ProdutoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

}


