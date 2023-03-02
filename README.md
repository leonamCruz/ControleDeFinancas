# ControleDeFinancas
Controle de Finanças com SpringBoot

END POINT'S

# POST:
 /home <br>
Você deve enviar uma JSON contendo gasto,date,descricao,opc. Nessa respectiva ordem, o UUID é gerado automaticamente. Retorna Status 201 se der certo.
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
/home<br>
Retorna todos os gatos o banco de dados em JSON.

/home/sumAll<br>
Retorna o somatório de todos os gastos da tabela em texto.
Obs: Não há filtros, é gastos independente do mês e ano.

/home/getDocument<br>
Retorna um arquivo .CSV para download com Gasto, Data, Descrição e com o que foi gasto. Porém, vem na ordem que foi cadastrada.

home/getBigExpense<br>
Retorna uma Matriz com qual categoria é o maior gasto com quantas vezes aquela categoria se repete.
EX: ['lazer', 3]

home/getSmallExpense
Retorna uma Matriz com qual categoria é o menor gasto com quantas vezes aquela categoria se repete.
EX: <br>[<br>'comida',<br> 1<br>]

home/getBiggestExpense
Retorna uma Matriz com todas as informações do gasto mais alto.
OBS: Não vem o UUID.<br>
EX: <br>[<br>'Aluguel',<br> '2023-02-27',<br> 'moradia',<br> 785.6<br>]

/getPerMonth/{Coloque aqui true ou false sem as chaves}<br>
Se você inserir true, ele retorna em ordem crescente.
Se for false, ele retorna em ordem decrescente.<br>

/getBySpecificMonth/{Coloque aqui o mes e o ano que deseja sem as chaves}
<br>
O formato que deve ser inserido é MM_AAAA. Desta forma será retornado somente os gastos daquele mês na ordem que foi cadastrado.
<br>


# Delete
home/{Coloque aqui seu UUID sem as chaves}<br>
Se ele não encontrar o o UUID, retornará um BADREQUEST com o texto "ID não localizado"<br>
Se ocorrer tudo bem, retorna 201 mais o texto "Deletado"<br>



