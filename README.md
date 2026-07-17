# <a href="https://devsuperior.com.br"><img src="https://i.ibb.co/S42fsBL4/Devsuperior-logo.png" alt="DevSuperior logo" width="300"></a> Java Spring Expert - DESAFIO DSMovie RestAssured

![Java](https://img.shields.io/badge/Java-25-orange) ![Spring
Boot](https://img.shields.io/badge/Spring_Boot-API_REST-green)
![RestAssured](https://img.shields.io/badge/RestAssured-API_Tests-success)
![JUnit 5](https://img.shields.io/badge/JUnit_5-Tests-blue) ![H2
Database](https://img.shields.io/badge/H2-Database-lightgrey)

## 👨‍💻 Desenvolvido por

**Marcos Shirafuchi**

-   GitHub: https://github.com/marcosfshirafuchi
-   Desenvolvedor Backend Java

------------------------------------------------------------------------

# 📚 Sobre o Projeto

Este desafio faz parte do curso **Java Spring Expert**, ministrado pelo
professor **Nélio Alves**, na plataforma **DevSuperior**.

O objetivo é implementar **testes de API utilizando RestAssured** para
validar os endpoints REST da aplicação DSMovie.

------------------------------------------------------------------------

# 📦 Estrutura dos Projetos

Este desafio possui dois projetos:

## dsmovie-projeto-base

Projeto principal Spring Boot que disponibiliza a API REST.

> **Este projeto deve estar em execução antes da execução dos testes.**

## dsmovie-restassured

Projeto contendo exclusivamente os testes automatizados utilizando
RestAssured.

Todos os testes enviam requisições HTTP para a API executada pelo
projeto **dsmovie-projeto-base**.

------------------------------------------------------------------------

# 🎯 Objetivo do Desafio

Implementar corretamente todos os testes de API utilizando
**RestAssured**.

**Mínimo para aprovação: 8 dos 10 testes.**

------------------------------------------------------------------------

# 🏗️ Modelo Conceitual

<p align="center">
    <a href="https://ibb.co/h14N1JPP"><img src="https://i.ibb.co/DgvFgHxx/Chat-GPT-Image-16-de-jul-de-2026-21-21-08.png" alt="Chat-GPT-Image-16-de-jul-de-2026-21-21-08" width="900" border="0"></a>
</p>

-   Movie
-   User
-   Score

A entidade **Score** representa a avaliação realizada por um usuário
para um filme.

------------------------------------------------------------------------

# ✅ Critérios de Correção

## MovieControllerRA

-   findAllShouldReturnOkWhenMovieNoArgumentsGiven
-   findAllShouldReturnPagedMoviesWhenMovieTitleParamIsNotEmpty
-   findByIdShouldReturnMovieWhenIdExists
-   findByIdShouldReturnNotFoundWhenIdDoesNotExist
-   insertShouldReturnUnprocessableEntityWhenAdminLoggedAndBlankTitle
-   insertShouldReturnForbiddenWhenClientLogged
-   insertShouldReturnUnauthorizedWhenInvalidToken

## ScoreControllerRA

-   saveScoreShouldReturnNotFoundWhenMovieIdDoesNotExist
-   saveScoreShouldReturnUnprocessableEntityWhenMissingMovieId
-   saveScoreShouldReturnUnprocessableEntityWhenScoreIsLessThanZero

------------------------------------------------------------------------

# 📋 Cenários Avaliados

-   GET /movies retorna 200 sem argumentos.
-   GET /movies retorna página de filmes quando informado um título.
-   GET /movies/{id} retorna 200 quando o filme existir.
-   GET /movies/{id} retorna 404 quando o filme não existir.
-   POST /movies retorna 422 quando o título estiver em branco.
-   POST /movies retorna 403 quando CLIENT estiver autenticado.
-   POST /movies retorna 401 quando o token for inválido.
-   PUT /scores retorna 404 quando o filme não existir.
-   PUT /scores retorna 422 quando movieId não for informado.
-   PUT /scores retorna 422 quando score for menor que zero.

------------------------------------------------------------------------

# 🧪 Competências Avaliadas

-   RestAssured
-   Testes de API REST
-   Spring Boot
-   Autenticação
-   Autorização
-   Validação de JSON
-   Status HTTP
-   Tratamento de erros

------------------------------------------------------------------------

# 🚀 Tecnologias

-   Java
-   Spring Boot
-   Spring Security
-   Spring Data JPA
-   RestAssured
-   JUnit 5
-   Maven
-   H2 Database

------------------------------------------------------------------------

# ▶️ Como Executar

## 1. Execute o projeto principal

``` bash
cd dsmovie-projeto-base
mvn spring-boot:run
```

## 2. Execute o projeto de testes

``` bash
cd dsmovie-restassured
mvn test
```

> Os testes somente funcionarão com o projeto **dsmovie-projeto-base**
> em execução.

------------------------------------------------------------------------

# ✨ Endpoints Testados

-   GET /movies
-   GET /movies/{id}
-   POST /movies
-   PUT /scores

------------------------------------------------------------------------

# 📖 Aprendizados

-   RestAssured
-   Testes de API
-   Testes de autenticação
-   Testes de autorização
-   Testes de validação
-   Validação de payload JSON
-   Organização de testes automatizados

------------------------------------------------------------------------

# 🎓 Curso

**Java Spring Expert**

Professor: **Nélio Alves**

Plataforma: **DevSuperior**

https://devsuperior.com.br
