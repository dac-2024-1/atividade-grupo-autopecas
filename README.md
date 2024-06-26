# Atividade Colaborativa: AutoPeças
Repositório para a atividade colaborativa da disciplina DAC 2024.1.

## Grupo:
- [Gabriella Braga](https://github.com/gabs44)
- [Maria Clara](https://github.com/marysclair)
- [Marianna Lopes](https://github.com/MariLopes1223)
- [Mauricio Bernardo](https://github.com/maueici0)
- [Veríssimo Terceiro](https://github.com/verissimon)

## Modelo relacional:
![Modelo relacional](modeloConceitual.png)

## Script para criação das tabelas:
```sql
CREATE TABLE cliente (
	id serial PRIMARY KEY,
	nome varchar(100) NOT NULL,
	endereco varchar(150) NOT NULL,
	telefone varchar(15) NOT NULL
);

CREATE TABLE usuario (
    id serial PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE funcionario (
	id serial PRIMARY KEY,
	nome varchar(150) NOT NULL,
	cargo varchar(30) NOT NULL,
	endereco varchar(150) NOT NULL,
	telefone varchar(15) NOT NULL,
	salario float NOT NULL,
	dataDeContratacao date NOT NULL,

	idusuario int,
    FOREIGN KEY (idusuario) REFERENCES usuario(id)
);

CREATE TABLE peca (
	id serial PRIMARY KEY,
	nome varchar(50) NOT NULL,
	descricao varchar(200) NOT NULL,
	preco float NOT NULL,
	quantidadeEstoque int NOT NULL
);

CREATE TABLE veiculo (
	id serial PRIMARY KEY,
	marca varchar(30) NOT NULL,
	modelo varchar(30) NOT NULL,
	ano varchar(4) NOT NULL,
    placa varchar(8) NOT NULL,
    
	idCliente int,
	FOREIGN KEY (idCliente) REFERENCES Cliente(id)
);

CREATE TABLE venda (
	id serial PRIMARY KEY,
	data date NOT NULL,
	totalVenda float NOT NULL,

	idCliente int,
	idFuncionario int,
	FOREIGN KEY (idCliente) REFERENCES cliente(id),
	FOREIGN KEY (idFuncionario) REFERENCES funcionario(id)
);

CREATE TABLE servico (
	id serial PRIMARY KEY,
	descricao varchar(150) NOT NULL,
	preco float NOT NULL,
	data date NOT NULL,

	idVeiculo int,
	idFuncionario int,
	FOREIGN KEY (idVeiculo) REFERENCES veiculo(id),
	FOREIGN KEY (idFuncionario) REFERENCES funcionario(id)
);

CREATE TABLE vendaPeca (
	id serial PRIMARY KEY,

	idVenda int,
	idPeca int,
	FOREIGN KEY (idVenda) REFERENCES venda(id),
	FOREIGN KEY (idPeca) REFERENCES peca(id)
);

CREATE TABLE servicoPeca (
	id serial PRIMARY KEY,

	idServico int,
	idPeca int,
	FOREIGN KEY (idServico) REFERENCES servico(id),
	FOREIGN KEY (idPeca) REFERENCES peca(id)
);

```

## Conexão com o banco de dados
Para se conectar com o banco de dados, altere a classe `VarAmbiente` com os dados do seu usuário do Postgres e do banco de dados criado por você. <br>
Por exemplo:
`public static final String DB_NAME = "dac-autopecas";` troque pelo nome do banco criado por você e `public static final String DB_PASSWORD = "123456";` troque pela senha do seu usuário no PGAdmin.
