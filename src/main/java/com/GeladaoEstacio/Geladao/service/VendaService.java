package com.GeladaoEstacio.Geladao.service;

import com.GeladaoEstacio.Geladao.entities.ItensVendidos;
import com.GeladaoEstacio.Geladao.entities.Produto;
import com.GeladaoEstacio.Geladao.entities.Usuario;
import com.GeladaoEstacio.Geladao.entities.Venda;
import com.GeladaoEstacio.Geladao.repository.ItensVendidosRepository;
import com.GeladaoEstacio.Geladao.repository.ProdutoRepository;
import com.GeladaoEstacio.Geladao.repository.UsuarioRepository;
import com.GeladaoEstacio.Geladao.repository.VendaRepository;
import com.GeladaoEstacio.Geladao.web.dtos.UsuarioDTO;
import com.GeladaoEstacio.Geladao.web.exception.ProdutoException;
import com.GeladaoEstacio.Geladao.web.exception.UsuarioException;
import com.GeladaoEstacio.Geladao.web.exception.VendaException;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    VendaRepository vendaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ItensVendidosRepository itensVendidosRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    @Transactional
    public Venda realizarVenda(Venda venda) throws VendaException {
        try {
            calcularValorTotalEVenderProdutos(venda);

            venda.setDataVenda(LocalDateTime.now());

            validarVendedor(venda);

            return vendaRepository.save(venda);
        } catch (Exception e) {
            throw new VendaException("Não foi possível salvar a venda" + e);
        }
    }

    private void calcularValorTotalEVenderProdutos(Venda venda) {
        double valorTotal = 0.0;
        for (ItensVendidos item : venda.getItensVendidos()) {
            Produto produto = obterProdutoPorId(item.getProduto().getProdutoId());

            int novaQuantidade = produto.getQuantidade() - item.getQuantidade();
            if (novaQuantidade < 0) {
                throw new VendaException("Quantidade insuficiente do produto: " + produto.getNomeProduto());
            }

            produto.setQuantidade(novaQuantidade);
            produtoRepository.save(produto);

            valorTotal += produto.getPreco() * item.getQuantidade();
        }

        venda.setValorTotal(valorTotal);
    }

    private Produto obterProdutoPorId(Long produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoException(ProdutoException.PRODUTO_NAO_ENCONTRADO));
    }

    private void validarVendedor(Venda venda) {
        if (venda.getUsuario() == null || venda.getUsuario().getId() == null) {
            throw new VendaException("ID do vendedor não foi fornecido");
        }

        for (ItensVendidos item : venda.getItensVendidos()) {
            Produto produto = obterProdutoPorId(item.getProduto().getProdutoId());
            item.setProduto(produto);
            item.setVenda(venda);
        }
    }

    @Transactional(readOnly = true)
    public Venda buscarVendaId(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new VendaException(VendaException.VENDA_NAO_LOCALIZADA));
    }

    @Transactional(readOnly = true)
    public List<Venda> buscarTodasVendas() {
        return vendaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Venda> buscarVendaPorUsuario(Usuario usuario) {
        return vendaRepository.findByUsuario(usuario);
    }
}


