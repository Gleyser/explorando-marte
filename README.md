# explorando-marte

Neste microserviço é possível criar, posicionar, rotacionar e mover sondas em uma malha.

Um conjunto de sondas foi enviado pela NASA à Marte e irá pousar num planalto. Esse planalto, que curiosamente é retangular, deve ser explorado pelas sondas para que suas câmera embutidas consigam ter uma visão completa da área e enviar as imagens de volta para a Terra.

A posição e direção de uma sonda são representadas por uma combinação de coordenadas x-y e uma letra representando a direção cardinal para qual a sonda aponta, seguindo a rosa dos ventos em inglês.

O planalto é divido numa malha para simplificar a navegação. Um exemplo de posição seria (0, 0, N), que indica que a sonda está no canto inferior esquerdo e apontando para o Norte.

Para controlar as sondas, a NASA envia uma simples sequência de letras. As letras possíveis são "L", "R" e "M". Destas, "L" e "R" fazem a sonda virar 90 graus para a esquerda ou direita, respectivamente, sem mover a sonda. "M" faz com que a sonda mova-se para a frente um ponto da malha, mantendo a mesma direção.

Nesta malha o ponto ao norte de (x,y) é sempre (x, y+1).

### Pré-requisitos

Antes de começar, é preciso ter instalado em sua máquina as seguintes ferramentas:
Java 11 (Java version: 11.0.11), Maven (Apache Maven 3.8.1) e Docker 3.3.1. O código foi produzido utilizando o Eclipse.

### 🎲 Rodando o Back End (servidor)

- Clone este repositório
- Importe o arquivo Maven para a sua IDE
- Após isso você pode rodar o ExplorandoMarteApplication. A API estará disponível na porta 8080.

### Recurso: sonda

O recurso Sonda contém a direção em que a sonda está apontando. A localização atual e o id da malha.

Exemplo:
```javascript
{
    "direcao": "NORTH",
    "localizacaoAtual": {
    "coordenadaX": 1,
    "coordenadaY": 2
    },
    "idMalha": 1
}
```

O microserviço possui os seguintes endpoints para o recurso sonda:

| Verbo HTTP  |  Resource path                   |           Descrição                                    |
|-------------|:--------------------------------:|-------------------------------------------------------:|
| GET         |  /api/v1/sondas                  |   Retorna todas as sondas cadastradas                  |
| GET         |  /api/v1/sondas/{id}             |   Retorna uma sonda com o ID fornecido                 |
| POST        |  /api/v1/sondas                  |   Salva uma sonda                                      |
| DELETE      |  /api/v1/sondas/{id}             |   Deleta uma sonda com o ID fornecido                  |
| PUT         |  /api/v1/sondas/{id}/esquerda    |   Gira a sonda que contém o ID fornecido para esquerda |
| PUT         |  /api/v1/sondas/{id}/direita     |   Gira a sonda que contém o ID fornecido para direita  |
| PUT         |  /api/v1/sondas/{id}/mover       |   Move a sonda que contém o ID fornecido               |
| PUT         |  /api/v1/sondas/{id}/instrucoes  |   Executa instruções na sonda que contém o ID fornecido|

### Recurso: malha

O recurso malha contém o pontoInferiorEsquerdo e pontoSuperiorDireito.

Exemplo:
```javascript
{

    "pontoInferiorEsquerdo": {
        "coordenadaX": 0,
        "coordenadaY": 0
    },
    "pontoSuperiorDireito": {
        "coordenadaX": 20,
        "coordenadaY": 450
    }
}
```

O microserviço possui os seguintes endpoints para o recurso malha:

| Verbo HTTP  |  Resource path       |           Descrição                       |
|-------------|:--------------------:|------------------------------------------:|
| GET         |  /api/v1/malhas      |   Retorna todas as malhas cadastradas     |
| GET         |  /api/v1/malhas/{id} |   Retorna uma malha com o ID fornecido    |
| POST        |  /api/v1/malhas      |   Salva uma malha                         |
| DELETE      |  /api/v1/malhas/{id} |   Deleta uma malha com o ID fornecido     |

### GET 



