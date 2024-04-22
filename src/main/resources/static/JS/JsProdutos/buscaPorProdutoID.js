function buscarProdutoPorId(event) {
    event.preventDefault(); // Previne o envio do formulário

    const idProduto = document.getElementById('idProduto').value;

    fetch(`/api/v1/produtos/buscarPorId/${idProduto}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Erro ao buscar produto');
            }
        })
        .then(data => {
            const resultadoElement = document.getElementById('resultado');
            resultadoElement.innerHTML = ''; // Limpar conteúdo anterior

            const produtoElement = document.createElement('div');
            produtoElement.classList.add('produto-info');
            produtoElement.innerHTML = `


                <div>
                   <label>ID do Produto:</label>
                   <p>${data.produtoId}</p>
                </div>

                <div>
                    <label>Nome do Produto:</label>
                    <p>${data.nomeProduto}</p>
                </div>
                          
                
               <div>
                    <label>Preço:</label>
                    <p>R$ ${data.preco.toFixed(2)}</p>
                </div>
                <div>
                    <label>Data de Validade:</label>
                    <p>${new Date(data.dataValidade).toLocaleDateString()}</p>
                </div>
                <div>
                    <label>Quantidade:</label>
                    <p>${data.quantidade}</p>
                </div>
            `;

            resultadoElement.appendChild(produtoElement);
        })
        .catch(error => {
            console.error('Erro ao buscar produto:', error);
        });
}
