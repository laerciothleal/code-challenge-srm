-- criação da tabela de moedas
create table tb_moeda (
    id bigint auto_increment primary key,
    nome_moeda varchar(50) not null unique,
    taxa_cambio decimal(10, 2) not null
);

-- inserção das moeda iniciais
insert into tb_moeda (nome_moeda, taxa_cambio) values
('ouro real', 1.00),
('tibar', 0.40);

-- criação da tabela de item comercializado
create table tb_item_comercializado (
    id bigint auto_increment primary key,
    nome_item varchar(50) not null unique,
    reino_origem varchar(50),
    preco_base decimal(10, 2) not null
);

-- inserção de itens iniciais
insert into tb_item_comercializado (nome_item, reino_origem, preco_base) values
('peles', 'reino das montanhas', 100.00),
('madeira', 'reino da floresta', 50.00),
('hidromel', 'reino dos vales', 30.00);

-- criação da tabela de transações
create table tb_transacao (
    id bigint auto_increment primary key,
    data_transacao timestamp not null,
    nome_item varchar(50) not null,
    moeda_origem varchar(50) not null,
    moeda_destino varchar(50) not null,
    valor decimal(10, 2) not null,
    valor_convertido decimal(10, 2) not null,
    constraint fk_nome_item foreign key (nome_item) references tb_item_comercializado(nome_item),
    constraint fk_moeda_origem foreign key (moeda_origem) references tb_moeda(nome_moeda),
    constraint fk_moeda_destino foreign key (moeda_destino) references tb_moeda(nome_moeda)
);