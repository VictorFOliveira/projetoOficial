function buscarTodosProdutos() {
    fetch(`/api/v1/produtos/buscarProdutos`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Erro ao buscar produtos');
            }
        })
        .then(data => {
            const todosProdutosElement = document.getElementById('todosProdutos');
            todosProdutosElement.innerHTML = ''; // Limpar conteúdo anterior

            data.forEach(produto => {
                const produtoElement = document.createElement('div');
                produtoElement.classList.add('produto-info');
                produtoElement.innerHTML = `
                    <div>
                        <label>ID do Produto:</label>
                        <p>${produto.produtoId}</p>
                    </div>
                    <div>
                        <label>Nome do Produto:</label>
                        <p>${produto.nomeProduto}</p>
                    </div>
                    <div>
                        <label>Preço:</label>
                        <p>R$ ${produto.preco.toFixed(2)}</p>
                    </div>
                    <div>
                        <label>Data de Validade:</label>
                        <p>${new Date(produto.dataValidade).toLocaleDateString()}</p>
                    </div>
                    <div>
                        <label>Quantidade:</label>
                        <p>${produto.quantidade}</p>
                    </div>
                `;

                todosProdutosElement.appendChild(produtoElement);
            });
        })
        .catch(error => {
            console.error('Erro ao buscar produtos:', error);
        });
}
