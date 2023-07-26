<h1 align="center">
  Recipes Api
</h1>

<p align="center">

</p>

API para gerenciar Receitas com foco no sem gluten e lactose , com autenticação por token e registro de usuarios , podendo entao listar as receitas , salvar novas  e ate mesmo gerar uma receita pelo chatGpt , focada em um ingrediente ou palavra chave , por exemplo pizza .



## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [PostgresSql](https://www.postgresql.org/docs/)
- [Spring Security](https://spring.io/projects/spring-security)

## Práticas adotadas

- SOLID
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3
- Testes automatizados
- Autenticação com JWT e com token

## Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/Sg-0.0.1-SNAPSHOT.jar
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints , antes de qualquer requisiçao, realizar o login , para gerar o token e coloca-lo para realizar as demais requisiçoes

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/downloads/):

- Criar Usuario  
```
$ http POST :8080/auth/register

[
  {
    
    "email": "teste@hotmail.com.br",
    "name": "testeJn",
    "password": "teste",
    "role": "USER"
}
]
```

- Login
```
$ http POST :8080/auth/login

[
  {
    "email": "natajn@outlook.com.br",
    "password": "nata"
}
]
```

- Buscar todas as receitas
```
$ http GET :8080/recipes/getAll

[
  {
        "id": 1,
        "nameRecip": "pão sem gluten",
        "tipo": "g",
        "prep": "Em uma tigela grande, misture o fermento biológico com o açúcar até ficar líquido.\nAdicione o leite, a manteiga, os ovos, as batatas e o sal e mexa bem.\nAcrescente a fécula de batata, aos poucos, até que a massa esteja homogênea e grude levemente nas mãos.\nCubra com filme plástico e deixe descansar por cerca de 30 minutos.\nDivida a massa em 2 partes iguais, coloque em 2 formas de bolo inglês (25 x 9 x 7 cm) untadas com manteiga e deixe crescer por cerca 30 minutos.\nAsse em forno médio (180º C), pré-aquecido, por cerca de 30 minutos ou até dourar."
    },
    {
        "id": 2,
        "nameRecip": "panqueca sem gluten",
        "tipo": "g",
        "prep": "Coloque todos os ingredientes no liquidificador e bata.\nColoque uma concha da massa em uma frigideira de teflon.\nApós vire a massa, quando ao redor estiver dourado.\nRepita o processo até terminar a massa.\nSe preferir, pode colocar manteiga ou carne."
    },
    {
        "id": 3,
        "nameRecip": "pão de queijo zero lactose",
        "tipo": "l",
        "prep": "Primeiramente, misture o polvilho doce e polvilho azedo com o sal.\nFerva o óleo e a água juntos e despeje sobre a mistura seca, escaldando o polvilho.\nPor último, acrescente o purê e amasse com as mãos até obter uma mistura homogênea. Moldar em bolinhas e assar em forno em temperatura baixa (preferencialmente mínima) por 1 hora. Não é necessário untar a forma.\nSimples, prático e fácil!"
    }
]
```
- Buscar receitas por tipo (gluten (g) , lactose(l)
```
$ http GET :8080/recipes/getByType?type=g

[
   {
        "id": 1,
        "nameRecip": "pão sem gluten",
        "tipo": "g",
        "prep": "Em uma tigela grande, misture o fermento biológico com o açúcar até ficar líquido.\nAdicione o leite, a manteiga, os ovos, as batatas e o sal e mexa bem.\nAcrescente a fécula de batata, aos poucos, até que a massa esteja homogênea e grude levemente nas mãos.\nCubra com filme plástico e deixe descansar por cerca de 30 minutos.\nDivida a massa em 2 partes iguais, coloque em 2 formas de bolo inglês (25 x 9 x 7 cm) untadas com manteiga e deixe crescer por cerca 30 minutos.\nAsse em forno médio (180º C), pré-aquecido, por cerca de 30 minutos ou até dourar."
    },
    {
        "id": 2,
        "nameRecip": "panqueca sem gluten",
        "tipo": "g",
        "prep": "Coloque todos os ingredientes no liquidificador e bata.\nColoque uma concha da massa em uma frigideira de teflon.\nApós vire a massa, quando ao redor estiver dourado.\nRepita o processo até terminar a massa.\nSe preferir, pode colocar manteiga ou carne."
    }
]
```
- Criar receita , passando o id do usuario que esta logado
```
$ http POST :8080/recipes?idUser=1

[
   {
    "nameRecipe":"teste",
    "preparation":"testando",
    "tipo" :"g"
}
]
```
- Criar receita a partir do chatGpt , passando um atributo relacionado como parametro , no exemplo gerou uma receita relacionada com lasanha sem gluten
```
$ http POST :8080/recipes/chat?teste=lasanha

[
   {
}
]
```

- Buscar todos os ingredientes do banco
```
$ http GET :8080/ingredients/getAll

[
   {
        "id": 3,
        "nameIngredient": "ovos",
        "value": 3.0,
        "quantityKg": 0.0,
        "quantityG": 0.0,
        "quantidade": 2
    },
    {
        "id": 4,
        "nameIngredient": "sal",
        "value": 0.5,
        "quantityKg": 0.0,
        "quantityG": 40.0,
        "quantidade": 0
    }
]
```

- Buscar todos os ingredientes do banco de acordo com a receita, que é passada e buscada pelo seu id 
```
$ http GET :8080/ingredients/getByRecipe?id=1

[
   {
        "id": 3,
        "nameIngredient": "ovos",
        "value": 3.0,
        "quantityKg": 0.0,
        "quantityG": 0.0,
        "quantidade": 2
    },
    {
        "id": 4,
        "nameIngredient": "sal",
        "value": 0.5,
        "quantityKg": 0.0,
        "quantityG": 40.0,
        "quantidade": 0
    },
    {
        "id": 5,
        "nameIngredient": "açucar",
        "value": 0.2,
        "quantityKg": 0.0,
        "quantityG": 3.0,
        "quantidade": 0
    },
    {
        "id": 6,
        "nameIngredient": "manteiga",
        "value": 0.8,
        "quantityKg": 0.0,
        "quantityG": 100.0,
        "quantidade": 0
    },
    {
        "id": 7,
        "nameIngredient": "batatas cozidas",
        "value": 0.08,
        "quantityKg": 0.0,
        "quantityG": 500.0,
        "quantidade": 0
    },
    {
        "id": 8,
        "nameIngredient": "fecula de batata",
        "value": 0.02,
        "quantityKg": 0.0,
        "quantityG": 650.0,
        "quantidade": 0
    },
    {
        "id": 1,
        "nameIngredient": "fermento biologico",
        "value": 0.21,
        "quantityKg": 0.0,
        "quantityG": 30.0,
        "quantidade": 0
    },
    {
        "id": 2,
        "nameIngredient": "leite",
        "value": 0.5,
        "quantityKg": 0.0,
        "quantityG": 194.0,
        "quantidade": 0
    }
]
```
- Salvar ingrediente, passando o id da receita que corresponde aquele ingrediente
```
$ http GET :8080/ingredients?id=2

[
  {
    "nameIngredient":"fermento",
    "value":2.5,
    "quantityG" :0.0,
    "quantityKg":0.0,
    "quantity":0

}
]
```

- Remover Receita , passando o id da mesma desejada
```
http DELETE :8080/recipes/delete?id=1

[ ]
```