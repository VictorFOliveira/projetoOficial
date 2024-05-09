package com.GeladaoEstacio.Geladao.web.controller;

import com.GeladaoEstacio.Geladao.entities.Usuario;
import com.GeladaoEstacio.Geladao.entities.Venda;
import com.GeladaoEstacio.Geladao.service.UsuarioService;
import com.GeladaoEstacio.Geladao.service.VendaService;
import com.GeladaoEstacio.Geladao.web.dtos.UsuarioDTO;
import com.GeladaoEstacio.Geladao.web.dtos.VendaDTO;
import com.GeladaoEstacio.Geladao.web.exception.UsuarioException;
import com.GeladaoEstacio.Geladao.web.exception.VendaException;
import com.GeladaoEstacio.Geladao.web.returnDtos.ReturnVendaDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.xml.bind.ValidationException;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Tag(name = "Vendas", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de uma venda.")
@RestController
@RequestMapping("/api/v1/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/vender")
    public ResponseEntity<ReturnVendaDTO> realizarVenda(@RequestBody VendaDTO vendaDTO) {

        Venda venda = modelMapper.map(vendaDTO, Venda.class);
        Venda vendaSalva = vendaService.realizarVenda(venda);

        if (vendaSalva == null) {
            throw new RuntimeException("Venda não salva");
        }
        ReturnVendaDTO returnVendaDTO = modelMapper.map(vendaSalva, ReturnVendaDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnVendaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnVendaDTO> getVendaId(@PathVariable Long id) {
        Venda venda = vendaService.buscarVendaId(id);
        if (venda == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        ReturnVendaDTO returnVendaDTO = modelMapper.map(venda, ReturnVendaDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnVendaDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> excluirVenda(@PathVariable Long id){
        try {
            String mensagem = vendaService.cancelarVenda(id);
            return ResponseEntity.ok(mensagem);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
