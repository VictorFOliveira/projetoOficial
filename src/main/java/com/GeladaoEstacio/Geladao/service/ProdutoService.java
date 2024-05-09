package com.GeladaoEstacio.Geladao.service;

import com.GeladaoEstacio.Geladao.entities.Produto;
import com.GeladaoEstacio.Geladao.repository.ProdutoRepository;
import com.GeladaoEstacio.Geladao.web.exception.ProdutoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvarProduto(Produto produto){
        validarProduto(produto);
        if(produtoRepository.findByNomeProduto(produto.getNomeProduto()) != null) {
            throw new ProdutoException(ProdutoException.PRODUTO_EXISTENTE);
        }
        return produtoRepository.save(produto);
    }

    @Transactional(readOnly = true)
    public Produto getProdutoById(Long id){
        return produtoRepository.findById(id).orElseThrow(() -> new ProdutoException(ProdutoException.PRODUTO_NAO_ENCONTRADO));
    }

    @Transactional(readOnly = true)
    public List<Produto> getAllProdutos(){
        return produtoRepository.findAll();
    }

    @Transactional
    public Produto editarProdutoPrecoQuantidadeEData(Long id, Produto novoProduto){
        Produto produto = getProdutoById(id);

        validarProduto(novoProduto);
        produto.setNomeProduto(novoProduto.getNomeProduto());
        produto.setPreco(novoProduto.getPreco());
        produto.setQuantidade(novoProduto.getQuantidade());
        produto.setDataValidade(novoProduto.getDataValidade());

        return produtoRepository.save(produto);
    }

    @Transactional
    public String deletarProdutoPorId(Long id){
        Produto produto = getProdutoById(id);
        if(produto == null){
            throw new ProdutoException(ProdutoException.PRODUTO_NAO_ENCONTRADO);
        }
        if(!(produto == null)){
            produtoRepository.delete(produto);
        }
        return "Produto deletado com sucesso";
    }

    private void validarProduto(Produto produto) {
        if (produto.getPreco() <= 0)
            throw new ProdutoException(ProdutoException.PRODUTO_SEM_PRECO);
        if (produto.getQuantidade() <= 0)
            throw new ProdutoException(ProdutoException.PRODUTO_SEM_QUANTIDADE_OU_ZERADA);

        Date dataAtual = new Date();

        if (produto.getDataValidade() == null)
            throw new ProdutoException(ProdutoException.PRODUTO_SEM_DATA_DE_VALIDADE);

        if (produto.getDataValidade().before(dataAtual))
            throw new ProdutoException(ProdutoException.PRODUTO_COM_DATA_MENOR_QUE_DATA_ATUAL);

    }
}
