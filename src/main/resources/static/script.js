document.getElementById('gold-price-form').addEventListener('submit', function(event) {
    event.preventDefault();
    const currency = document.getElementById('currency').value;

    fetch(`http://localhost:8081/api/gold-price?currency=${currency}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (data.status === 'success') {
                document.getElementById('result').innerText = `O preço de um grama de ouro em ${currency} está no valor de ${data.price.toFixed(2)}`;
            } else {
                document.getElementById('result').innerText = data.status;
            }
        })
        .catch(error => {
            document.getElementById('result').innerText = 'Erro ao obter preço do ouro.';
            console.error('Erro:', error);
        });
});
