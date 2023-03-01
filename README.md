# ControleDeFinancas
Controle de Finanças com SpringBoot

# Configuração:
No banco de dados MySql você deve criar um banco de dados chamado "bd_financa"

Em aplication.properties.

spring.jpa.hibernate.ddl-auto=update // É auto update para criar as tabelas automaticamente.
spring.datasource.url=jdbc:mysql://localhost:3306/bd_financa // Aqui você altera onde está seu banco de dados.
spring.datasource.username=root // Seu usuário
spring.datasource.password=senha123 // Sua Senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver // É o JDBC, serve para fazer a ponte entre o Java e o banco de dados

# END POINT'S

# POST:
/home
Você deve enviar uma JSON contendo gasto,date,descricao,opc. Nessa respectiva ordem, o UUID é gerado automaticamente. Retorna Status 201 se der certo.
Exemplo:
{
    "gasto": "50.25",
    "date": "2023-03-01",
    "descricao": "Pizza",
    "opc": "comida"
}

# GET:
/home
Retorna todos os gatos o banco de dados em JSON.

/home/sumAll
Retorna o somatório de todos os gastos da tabela.
Obs: Não há filtros, é gastos independente do mês.

/home/getDocument
Retorna um arquivo .CSV para download com Gasto, Data, Descrição e com o que foi gasto. Porém, vem na ordem que foi cadastrada.

home/getBigExpense
Retorna um JSON com qual categoria é o maior gasto com quantas vezes aquela categoria se repete.
EX: ['lazer', 3]

home/getSmallExpense
Retorna um JSON com qual categoria é o menor gasto com quantas vezes aquela categoria se repete.
EX:  ['comida', 1]

home/getBiggestExpense
Retorna um JSON com todas as informações do gasto mais alto.
OBS: Não vem o UUID
EX: ['Aluguel', '2023-02-27', 'moradia', 785.6]
