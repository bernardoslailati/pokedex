# Desafio Squadra - Android Nativo

## Descição do Desafio

*Crie um app simples utilizando Java ou Kotlin e a API do Dragon Ball.*

Funcionalidades esperadas:
- Página para listar Personagens, com campo de busca. Cada personagem deve ser apresentado em um card com nome e imagem (se houver).
- Página para listar Planetas, com campo de busca. Na lista de planetas o usuário poderá ir para a página de residentes do planeta.
- Seja criativo na elaboração do layout da aplicação

[Link: Desafio SQUADRA - Teste Android Nativo](https://www.dropbox.com/s/cfggf15f4d42efz/Teste%20Android.pdf?dl=0)

## Solução Proposta - Pokédex

<div><img alt="Android" src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
<img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white" /></div>

<p align="center">Aplicativo simples e intuitivo de listagem de pokémons por geração e tipos, com opção de análise detalhada de cada pokémon.</p>

### Funcionalidades e Demonstrativos

- Tema Claro / Escuro

<p align="center">
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/tema_claro_escuro.gif" />
</p>

- Lista de Pokémons por Geração e Tipo

<p align="center">
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/lista_pokemon.jpeg" />
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/lista_tipo.jpeg" />
</p>

- Mecanismo de Busca em Listas

<p align="center">
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/pesquisar_pokemon.gif" />
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/pesquisar_tipo.gif" />
</p>

- Detalhes do Pokémon

<p align="center">
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/detalhes_pokemon.gif" />
</p>

### Ferramentas gerais de desenvolvimento

- IDE: **Android Studio 4.1.1**
- Versão Android SDK Alvo/Compilada: **29**
- Versão Android SDK Mínima: **21**
- APIS consumidas:
  - [PokéDexAPI](https://pokedevs.gitbook.io/pokedex/): limite de 500 requisições à cada 12 horas
  - [PokéAPI](https://pokeapi.co/): uso ilimitado

### Principas dependências utilizadas

- **Glide**: apresentar GIFs em tela e realizar lazy loading de imagens ([https://github.com/bumptech/glide](https://github.com/bumptech/glide), 30.8k stars | 5.6k forks | boa documentação)
- **Retrofit2**: cliente HTTP de tipagem segura para Android e Java ([https://github.com/square/retrofit](https://github.com/square/retrofit), 37.7k stars | 6.8k forks | boa documentação)
- **Room**
- **LiveData**
- **ViewModel**

### Escolhas e Por quês

- Uso de ViewBinding
  - Remover boilerplate do método "findViewById" 

- RecyclerView / Spinner + Adapters Customizados
  - Utilizados para gerar uma melhor interação com o usuário, minimizar o uso de memória e otimizar layout

- Banco de dados local SQLite
  - Gerenciado pela biblioteca ROOM (Database, DAO e Entity), fortemente recomendada e inserida no Jetpack Components (junto com a biblioteca SQLite)
  - Acesso via Repository + ViewModel para otimizar buscas e evitar travamento de telas
  - Inserção em banco de dados dos Pokémons buscados via requisição HTTP (remover necessidade de busca repetida, a cada novo acesso à aplicação, de todos os dados de Pokémons)
  
- TabLayout + PageViewer + Fragments
  - Aprimorar acesso dos usuário às listas através tabs deslizantes intuitivas (que podem ser até mesmo reaproveitadas), padrão inserido no Jetpack Components

- Requisições HTTP Assíncronas
  - A fim de não travar a interação do usuário na aplicação, foram utilizadas requisições assíncronas via método "enqueue" abstraído pela biblioteca Retrofit2

- Monitoramento de alterações no banco de dados
  - Usado método "observer" de LiveData para preencher as listas de items inseridos nos RecyclerViews

- SearchView + RecyclerView para buscas
  - Realização de buscas de items mostrados dentro de RecyclerViews, no padrão de atualização da busca a cada modificação de caracter (tempo real)

- Lazy loading de imagens
  - Imagens utilizadas de boa qualidade e resolução, utilizada então a biblioteca Glide para carregar de forma assíncrona as figuras relacionadas aos pokémons e os tipos (grama, fogo, água, etc.)
  - Também utilizado o Glide para gerar a animação de GIF
