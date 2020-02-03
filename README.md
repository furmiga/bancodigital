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
