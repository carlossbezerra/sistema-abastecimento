# sistema-abastecimento

# Sistema de Controle de Abastecimento para Transporte

Este projeto consiste em um sistema para gerenciar abastecimentos de veículos, utilizando Angular no frontend e Spring Boot no backend.

## Tecnologias Utilizadas

*   **Frontend:** Angular com Angular Material
*   **Backend:** Spring Boot com Spring Data JPA
*   **Banco de Dados:** H2 (em memória)
*   **Arquitetura:** Clean Architecture

## Pré-requisitos

*   [Node.js](https://nodejs.org/) (com npm ou yarn)
*   [JDK (Java Development Kit)](https://www.oracle.com/java/technologies/downloads/) (para o backend)
*   [Maven](https://maven.apache.org/) (para o backend)
*   [Angular CLI](https://angular.io/cli) (instalado globalmente com `npm install -g @angular/cli`)
*   Uma IDE (e.g., VS Code, IntelliJ IDEA, Eclipse)

## Configuração do Backend

1.  **Importe o Projeto:**
    *   Importe o projeto Spring Boot em sua IDE (IntelliJ IDEA, Eclipse, VS Code).
2.  **Execute a Aplicação:**
    *   Execute a classe principal do Spring Boot (`AbastecimentoApplication.java`).
    *   O backend estará disponível em `http://localhost:8080`.

## Configuração do Frontend

1.  **Instale as Dependências:**
    *   Abra o terminal na pasta raiz do frontend (`abastecimento-frontend`).
    *   Execute o comando `npm install` ou `yarn install` para instalar as dependências.
2.  **Execute a Aplicação:**
    *   Execute o comando `ng serve` para rodar o projeto Angular.
    *   O frontend estará disponível em `http://localhost:4200`.

## Funcionalidades

### Frontend
1. **Listagem de Abastecimentos:**
 * Lista todos os abastecimentos cadastrados com paginação.
 * Permite filtrar os abastecimentos pela placa do veículo.
 * Possibilita a remoção de abastecimentos.
2. **Adição de Abastecimento:**
  * Permite o cadastro de novos abastecimentos.
  * Realiza validações nos campos do formulário (data e hora, placa, quilometragem, valor total).

### Backend
1. **Endpoints REST:**
  * `GET /abastecimentos`: Lista todos os abastecimentos com suporte a filtro por placa.
  * `POST /abastecimentos`: Adiciona um novo abastecimento.
  * `DELETE /abastecimentos/{id}`: Remove um abastecimento.
2. **Validações:**
  * O backend realiza validações de quilometragem, placa, data e hora e valor total.
  * Todos os campos obrigatórios são verificados.

## Estrutura do Projeto

### Backend (Spring Boot)
*   `src/main/java/com.sistema_controle_abastecimento`
    *   `api`: Controladores REST.
    *   `application`: Lógica de negócio e DTOs.
    *   `domain`: Entidades e regras de negócio.
    *   `infrastructure`: Repositórios e configurações.
* `src/test/java/com.sistema_controle_abastecimento`: Testes unitários

### Frontend (Angular)

*   `src/app`:
    *   `abastecimento-list`: Componente para a listagem de abastecimentos.
    *   `abastecimento-add`: Componente para a adição de abastecimentos.
    *   `shared`: Componentes reutilizáveis e serviços.
    *    `app-routing.module.ts`: Rotas da aplicação
    * `app.component.ts`: Componente principal da aplicação
    * `app.module.ts`: Módulo principal da aplicação

## Informações Adicionais

*   O banco de dados H2 é configurado em memória, e não requer configurações adicionais.
*   O projeto utiliza Lombok para reduzir o boilerplate do Java.
*   A arquitetura do projeto segue os princípios da Clean Architecture.

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests.

## Licença

Este projeto não possui licença.
