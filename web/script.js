document.addEventListener('DOMContentLoaded', function() {
    const form = document.querySelector('form')
    const totalLabel = document.querySelector('#total')
    const somarButton = document.querySelector('#somarTudo')
    const msiorGastoButton = document.querySelector('#comOQueMaisGasto')
    const labelGastoMaior = document.querySelector('#comOQueMaisGasta')
    const labelGastoMenor = document.querySelector('#comOQueMenosGasta')
    const menorGastoButton = document.querySelector('#comOQueMenosGasto')
    const botaoGastoAlto = document.querySelector('#gastoMaisAlto')
    const labelGastoAlto = document.querySelector('#gastoMaisAltoLabel')

    document.querySelector('#download');
    form.addEventListener('submit', function (event) {
        event.preventDefault()

        const dinheiroInput = document.querySelector('#dinheiro')
        const dataInput = document.querySelector('#data')
        const descricaoInput = document.querySelector('#descricao')
        const gastoComOQueInput = document.querySelector('#opc')

        const gasto = dinheiroInput.value
        const date = dataInput.value
        const descricao = descricaoInput.value
        const opc = gastoComOQueInput.value;

        const dados = {
            gasto,
            date,
            descricao,
            opc
        }
        console.log(dados)
        fetch('http://localhost:8080/home', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(dados)
        }).then(function (res) {
            console.log(res.status);
        }).catch(function (){
            alert('Liga o servidor filho da puta');
        })
    })

    somarButton.addEventListener('click', function (event) {
        event.preventDefault()

        fetch('http://localhost:8080/home/sumAll', {
            method: 'GET',
            headers: {
                'Content-type': 'application/json',
            }
        }).then(function (res) {
            return res.json()
        }).then(function (data) {
            console.log(data)
            totalLabel.textContent = data
        }).catch(function(error) {
            alert('Liga o servidor filho da puta');
            console.error(error);
        })
    })
    msiorGastoButton.addEventListener('click',function (event){
        event.preventDefault()

        fetch('http://localhost:8080/home/getBigExpense',{
            method:'GET',
            headers:{
                'Content-type':'application/json',
            }
        }).then(function (res){
            return res.json()
        }).then(function (data){
            console.log(data)
            labelGastoMaior.textContent = data
        })
    })
    menorGastoButton.addEventListener('click',function (event){
        event.preventDefault()

        fetch('http://localhost:8080/home/getSmallExpense',{
            method:'GET',
            headers:{
                'Content-type':'application/json',
            }
        }).then(function (res){
            return res.json()
        }).then(function (data){
            console.log(data)
            labelGastoMenor.textContent = data
        })
    })
    botaoGastoAlto.addEventListener('click',function (event){
        event.preventDefault()

        fetch('http://localhost:8080/home/getBiggestExpense',{
            method:'GET',
            headers:{
                'Content-type':'application/json',
            }
        }).then(function (res){
            return res.json()
        }).then(function (data){
            console.log(data)
            labelGastoAlto.textContent = data
        })
    })
})
