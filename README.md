# LibrayMicronaut

## Resumo do projeto
Este projeto foi concebido como parte integrante da disciplina de Tópicos Especiais em Programação Optativa I, ministrado na Universidade Estadual do Tocantins - UNITINS. O seu propósito central consiste na criação de um sistema de gestão bibliotecária denominado LibraySpring.

O sistema está sendo desenvolvido em java com o framework Micronaut seguindo os princípios da orientação a objetos. O banco de dados utilizado será o MySQL.

## ✔️ Técnicas e tecnologias utilizadas

- ``Java 21``
- ``Visual Studio Code``
- ``Micronaut``
- ``Microsserviço Docker``
- ``Programação Orientada a Objetos``

## ▶️ Como executar o projeto

Para executar o projeto localmente, siga os passos abaixo:

### 📌 Pré-requisito
Certifique-se de que o **Docker** esteja instalado e aberto em sua máquina.

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

### Clone este repositório

```bash
    git clone https://github.com/emannuelop/LibrayMicronaut.git
```

### 📁 Acessar o diretório do projeto
Abra o terminal e navegue até a pasta onde o projeto foi clonado. Dentro dela, localize a pasta `LibrarySpring`, onde estão os arquivos do projeto e a pasta `src`.

```bash
    cd .\LibrayMicronaut-main\LibrayMicronaut\
```

Dentro da pasta `LibrarySpring`, execute o seguinte comando no terminal:

```bash
    docker-compose up --build
```

Após o build, abra o navegador de sua preferência e acesse: 

```bash
    http://localhost:8080
```
Você verá a interface do Swagger, onde poderá interagir com os CRUDs do projeto.

### Criando um Usuário

Para criar um novo usuário, certifique-se de preencher corretamente os campos necessários. Um dos campos obrigatórios é o **`idCargo`**, que define o tipo de usuário. Utilize os seguintes valores:

- `1` → **Administrador**
- `2` → **Funcionário**
- `3` → **Cliente**

## 👨‍💻 Autores

| [<img src="https://github.com/emannuelop/LibrayMicronaut/blob/main/imagens/danilo.png" width=115><br><sub>Danilo Da Silva</sub>](https://github.com/DaniloDaSilvaMoreira) |  [<img src="https://github.com/emannuelop/LibrayMicronaut/blob/main/imagens/emannuel.png" width=115><br><sub>Emannuel Oliveira</sub>](https://github.com/emannuelop) |  [<img src="https://github.com/emannuelop/LibrayMicronaut/blob/main/imagens/erick.jpg" width=115><br><sub>Erick Santos</sub>](https://github.com/ericksantos37) |
| :---: | :---: | :---: | 