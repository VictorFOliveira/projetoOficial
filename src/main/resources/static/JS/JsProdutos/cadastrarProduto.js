function cadastrarProduto() {
    const nome = document.getElementById('nome').value;
    const preco = parseFloat(document.getElementById('preco').value);
    const quantidade = parseInt(document.getElementById('quantidade').value);
    const dataValidade = document.getElementById('dataValidade').value;

    const produto = {
        nomeProduto: nome,
        preco: preco,
        quantidade: quantidade,
        dataValidade: dataValidade
    };

    fetch('/api/v1/produtos/criar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(produto)
    })
        .then(response => {
            if (response.ok) {
                alert('Produto cadastrado com sucesso!');
                document.getElementById('criarProduto').reset();
            } else {
                alert('Erro ao cadastrar produto');
            }
        })
        .catch(error => {
            console.error('Erro ao cadastrar produto:', error);
            alert('Erro ao cadastrar produto');
        });
}
