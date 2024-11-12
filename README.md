[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[H2]:https://img.shields.io/badge/h2-%234ea94b.svg?style=for-the-badge&logo=h2&logoColor=white
[LOMBOK]:https://img.shields.io/badge/lombok-%234ea94b.svg?style=for-the-badge&logo=lombok&logoColor=white

<h1 align="center" style="font-weight: bold;">Assembleia de Votação 💻</h1>


![spring][SPRING_BADGE]
![java][JAVA_BADGE]
![h2][H2]
![lombok][LOMBOK]

<p align="center">
  <b>Este projeto tem como objetivo criar uma aplicação back-everrmnd para gerenciar e contabilizar votos em pautas.</b>
</p>


<h2 id="started">🚀 Iniciando</h2>

Para rodar o projeto localmente, é necessário que você tenha instalado em sua máquina o JDK, Postman, uma IDE (como IntelliJ IDEA, Eclipse ou VS Code), Git e Maven.

<h3>Pré-requisitos</h3>

- [JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Postman](https://www.postman.com/downloads/)
- [IDE (IntelliJ IDEA, Eclipse ou VS Code)](https://www.jetbrains.com/idea/)
- [Git](https://git-scm.com/downloads)
- [Maven](https://maven.apache.org/download.cgi)


<h3>Clonando o repositório</h3>

Como clonar seu projeto:

```bash
git clone https://github.com/RhuanSantos135/assembleia-votacao.git
```
<h3>Iniciando</h3>

Como iniciar o seu projeto:

```bash
# Navegue até o diretório do projeto
cd C:\projetosgui\assembleia-votacao

# Se estiver usando Maven, rode o comando:
mvn spring-boot:run
 ```

<h2 id="routes">📍 Endpoints da API</h2>

Aqui você pode listar as principais rotas da sua API e os corpos de requisição e resposta esperados.

| Rota                         | Descrição                                                                      |
|------------------------------|--------------------------------------------------------------------------------|
| <kbd>GET/user</kbd>          | Buscas as informações do usuário. Veja [detalhes da resposta](#get-busca-user) |
| <kbd>POST/user</kbd>         | Cria o usuário na aplicação. Veja [detalhes da requisição](#post-cria-user)    |
| <kbd>POST/pauta</kbd>        | Cria pauta na aplicação. Veja [detalhes da requisição](#post-cria-pauta)       |
| <kbd>POST/pauta/sessao</kbd> | Abre sessão para pauta. Veja [detalhes da requisição](#post-cria-sessao-pauta) |               |                                                                                |

<h3 id="get-busca-user">GET /user/{id}</h3>

**OBTER**
```json
{
  "idAssociado": 1,
  "nome": "Rhuan Lucas Gomes Dos Santos",
  "email": "rhuansantos507@gmail.com",
  "senha": "rhuan135"
}
```

<h3 id="post-cria-user">POST /user</h3>

**CADASTRA USUARIO**
```json
{
  "nome": "Lucas Antonio Vilas Boas",
  "email": "lucasantonio@gmail.com",
  "senha": "lucas145"
}
```

<h3 id="post-cria-pauta">POST /pauta</h3>

**CADASTRA PAUTA**
```json
{
  "descricao": "Fazer churrasco domingo",
  "prazoPauta": ""
}
```

<h3 id="post-cria-sessao-pauta">POST /pauta</h3>
**ABRE SESSAO PARA A PAUTA**
```json
{
  "id":1,
  "prazoPauta" : ""
}
```

<h2 id="colab">🤝 Colaboradoes</h2>

Agradecemos especialmente a [Guilherme Pinheiro] pela valiosa contribuição para este projeto.

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/101673957?v=4" width="100px;" alt="Guilherme Pinheiro"/><br>
        <sub>
          <b>Guilherme Pinheiro</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
