
# Sistema de Conversão de Moedas entre Ouro Real e Tibar

## Descrição do Projeto

Este projeto é um sistema de conversão de moedas desenvolvido para atender às necessidades dos mercadores da cidade de Wefin, no reino de SRM. Com a chegada de anãos comerciantes que utilizam a moeda Tibar, surgiu a necessidade de um sistema que permita a conversão eficiente entre Ouro Real e Tibar, garantindo que as negociações continuem fluindo sem entraves.

O sistema oferece:

- **Mapeamento e atualização de taxas de câmbio**: Permite que as taxas entre Ouro Real e Tibar sejam atualizadas conforme a demanda do mercado.
- **Conversão personalizada por produto**: Implementa fórmulas de conversão específicas para cada tipo de produto, considerando sua natureza e reino de origem.
- **Persistência resiliente**: Garante que todas as transações sejam registradas de forma segura, evitando perdas em caso de falhas.
- **API interativa e documentada**: Fornece endpoints para consulta de taxas, realização de trocas e acesso a históricos detalhados.
- **Consultas avançadas**: Oferece filtros para buscas por moeda, reino, data da taxa e tipo de transação.

## Tecnologias Utilizadas

- **Linguagem**: Java 17
- **Framework**: Spring Boot, Spring Validation, Spring MVC, Spring Data JPA
- **Banco de Dados**: H2
- **ORM**: Spring Data JPA (Hibernate)
- **Liquibase**: Migrações de base de dados
- **Documentação da API**: Springdoc OpenAPI (Swagger UI)
- **MapStruct**: Biblioteca de mapeamento de objetos Java
- **Controle de Dependências**: Maven
- **Testes**: JUnit 5, Mockito

## Como Executar o Projeto

### Pré-requisitos

- **Docker** instalado.
- **Maven** instalado.

### Passos para Execução

1. **Clonar o Repositório**

   ```bash
   git clone https://github.com/laerciothleal/code-challenge-srm.git
   cd code-challenge-srm
   ```
   
2. **Construir e Executar a Aplicação**

   Utilize o seguinte arquivo `docker-compose.yml`:

   ```yaml
   services:
     backend:
       build:
         dockerfile: ./Dockerfile
       ports:
         - "8080:8080"
       volumes:
         - .m2:/root/.m2
       stdin_open: true
       tty: true
   ```

   - **Comandos para subir o ambiente**:
     ```bash
     docker-compose up --build
     ```
3. **Executar teste**
`mvn clean test`

4. **Acessar a Documentação da API**

   - Swagger: `http://localhost:8080/swagger-ui.html`

5. **Acessar o Banco de Dados**

   h2 console `http://localhost:8080/h2/login.jsp`

   Credencias:
      - JDBC URL:  jdbc:h2:mem:crud
     - User Name: crud
     - Password:  crud

![H2](h2.png)

6. **Script SQL**

- Se encontra nesse caminho dentro do projeto: `src/main/resources/db/changelog/changes/001-initial_script.sql`


   ```sql
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
       quantidade decimal(10) not null,
       preco_base decimal(10, 2) not null,
       quantidade_total decimal(10, 2) not null,
       valor_convertido decimal(10, 2) not null,
       constraint fk_nome_item foreign key (nome_item) references tb_item_comercializado(nome_item),
       constraint fk_moeda_origem foreign key (moeda_origem) references tb_moeda(nome_moeda),
       constraint fk_moeda_destino foreign key (moeda_destino) references tb_moeda(nome_moeda)
   
   );
   ```

- **DER**:
  ![DER](der.png)


## Contato

- **Desenvolvedor**: Laercio Leal
- **LinkedIn**: https://www.linkedin.com/in/laercioth/

