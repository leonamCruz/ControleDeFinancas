# ControleDeFinancas
Controle de Finanças com SpringBoot

<h3>Sobre o projeto</h3>
<p>
A linguagem do projeto é Java na sua Versão 17 e o Framework SpringBoot na sua versão 3.0.3.
</p>
<p>
</p>
<h1> END POINT'S</h1>
<h1>POST:</h1> 
<h3> /home <br></h3>
Você deve enviar uma JSON contendo gasto,date,descricao,opc. Nessa respectiva ordem, o UUID é gerado automaticamente. Retorna Estado 201 se der certo.
Exemplo:
<br>
[<br>
{
<br>
    "gasto": "50.25",<br>
    "date": "2023-03-01",<br>
    "descricao": "Pizza",<br>
    "opc": "comida"<br>
}
<br>
]

# GET:

<h3>/home<br></h3>

<p align="justify">
Retorna todos os gatos o banco de dados em JSON.
</p>

<h3>/home/sumAll<br></h3>

<p align="justify">
Retorna o somatório de todos os gastos da tabela em texto.
Obs: Não há filtros, são gastos independente do mês e ano.

<h3>/home/getDocument<br></h3>

<p align="justify">
Retorna um arquivo.CSV para download com Gasto, Data, Descrição e com o que foi gasto. Porém, vem na ordem cadastrada.
</p>

<h3>home/getBigExpense<br></h3>

<p align="justify">
Retorna uma Matriz com qual categoria é o maior gasto com quantas vezes aquela categoria se repete.
EX: ['lazer', 3]
</p>

<h3>home/getSmallExpense</h3>

<p align="justify">
Retorna uma Matriz com qual categoria é o menor gasto com quantas vezes aquela categoria se repete.
EX: <br>[<br>'comida',<br> 1<br>]
</p>

<h3>home/getBiggestExpense</h3>

<p align="justify">
Retorna uma Matriz com todas as informações do gasto mais alto.
OBS: não vem o UUID.<br>
EX: <br>[<br>'Aluguel',<br> '2023-02-27',<br> 'moradia',<br> 785.6<br>]
</p>

<h3> /ascOrDesc/{Coloque aqui true ou false sem as chaves} </h3>
<p align="justify">
Se você inserir true, ele retorna em ordem crescente.
Se for false, ele retorna em ordem decrescente.<br>
</p>

<h3>/getBySpecificMonth/{Coloque aqui o mes e o ano que deseja sem as chaves}</h3>
<p align="justify">
O formato que deve ser inserido é MM_AAAA. Desta forma será retornado somente os gastos daquele mês na ordem cadastrado.
<br>
</p>


# Delete
<h3>home/{Coloque aqui seu UUID sem as chaves}</h3>
<p align="justify">
Se ele não encontrar o UUID, retornará um BADREQUEST com o texto "ID não localizado"<br>
Se ocorrer tudo bem, retorna 201 mais o texto "Deletado"<br>
</p>


