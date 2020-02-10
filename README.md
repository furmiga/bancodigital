# Banco digital

## Descrição
Projeto feito para fins didáticos, afim de demonstrar as iterações básicas em um BD (CRUD)

## Informações técnicas
- Java 8
- Spring Boot
- Spring Data
- Spring security
- Spring actuator
- Swagger
- mysql
- FlyWay

## Heroku

A aplicação foi instalada no HEROKU (PaaS). Abaixo segue a URI de acesso ao swagger (Documentação detalhada dos serviços expostos) e o endpoint base da aplicação

>  **Swagger:**  https://bancodigital-victordantas.herokuapp.com/swagger-ui.html

> **Base:**
https://bancodigital-victordantas.herokuapp.com/

## Credenciais para acessar enpoints

Como foi utilizado springsecurity, sera necessário se autenticar para visualizar e usufruir dos serviços disponibilizados, abaixo segue tais informações: 

>  **Usuario:** victor

> **Senha:** 123

**Obs:** Para acessar as URIs do actuator não tem necessidade de se autenticar, pois para essas URIs foi dado permissão de livre acesso

## Postman
Caso os testes sejam feitos utilizando o Postman, foi criado na raiz do projeto uma pasta chamada postman com um arquivo contendo todos os testes da aplicação. Basta importar este arquivo para dentro do postman.

## Docker

**Path da pasta com arquivos docker:** `/docker`

Foi disponibilizado um ambiente docker, para facilitar a portabilidade de instalação, a solução docker foi divida em alguns arquivos:
- `Dockerfile`
		Arquivo responsável por criar a imagem do gestor de contas. Esta imagem foi baseada na openjdk e nela já é feita a instalação do mavem, o clone do app através do github e por fim o empacotamento do mesmo.

- `docker-compose.yml`
	Arquivo responsável por criar o meu ambiente docker (subir os contêineres necessários para o APP funcionar), nele estão inseridos tanto a imagem oficial do mysql quanto a imagem do app gestor de contas.

- `docker-entrypoint.sh`
	Shell script que sera executado ao iniciar o contêiner. Basicamente inicializa nossa aplicação

- `wait-for`
	Shell script que tambem sera executado ao iniciar o contêiner (sera executado antes do docker-entrypoint.sh). Ele tem como função verificar o momento em que o serviço do mysql esta no ar, para que possa dar seguimento no script docker-entrypoint.sh, pois caso isso não ocorra a aplicação ira iniciar antes do serviço do banco de dados, logo quando o spring for inicializar o datasource, não ira conseguir abrir uma conexão com o BD e o app não ira subir.

- `start-app.sh`
	Script responsavel por subir todo o ambiente descrito acima: Ele ira buildar a nova imagem e subir o docker-compose.