document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form'); // seleciona o elemento <form> no DOM
    const totalLabel = document.querySelector('#total');

    form.addEventListener('submit', function (event) {
        event.preventDefault(); // previne o envio do formulário padrão

        // seleciona os elementos <input> relevantes no DOM
        const dinheiroInput = document.querySelector('#dinheiro');
        const dataInput = document.querySelector('#data');

        // extrai os valores desses inputs
        const gasto = dinheiroInput.value;
        const date = dataInput.value;

        // cria um objeto com os dados a serem enviados para a API
        const dados = {
            gasto,
            date
        };

        fetch('http://localhost:8080/inserir', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(dados)
        }).then(function (res) {
            console.log(res.status);
        });
    });

    const somarButton = document.querySelector('#somarTudo');
    somarButton.addEventListener('click', function (event) {
        event.preventDefault();

        fetch('http://localhost:8080/inserir/sumAll', {
            method: 'GET',
            headers: {
                'Content-type': 'application/json',
            }
        }).then(function (res) {
            return res.json();
        }).then(function (data) {
            console.log(data)
            totalLabel.textContent = data
        }).catch(function(error) {
            console.error(error);
        });
    });
});
