# Pokedéx

<div><img alt="Android" src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" />
<img alt="Java" src="https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white" /></div>

<p align="center">Aplicativo de listagem de pokémons filtrado por geração (**TODAS AS 8 GERAÇÕES!**) ou tipo, com opção de análise detalhada de cada pokémon, funcinando nos cenários online e offline.</p>

### Funcionalidades e Demonstrativos

- #### Tema Claro / Escuro

<p align="center">
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/tema_claro_escuro.gif" />
</p>

- #### Lista de Pokémons por Geração e Tipo

  - TODOS os pokémons são requeridos inicialmente via API no momento de clique do botão "Entrar" e, ao serem retornados corretamente, são armazenados em um banco de dados SQLite local para futuros acessos offline, no padrão cache;
  - Será mostrado ao usuário uma mensagem e um ProgressBar circular solicitando a espera no momento da busca pelos pokémons de cada geração.

<p align="center">
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/lista_pokemon_geracao1.jpg" />
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/lista_pokemon_geracao2.jpg" />
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/lista_tipo.jpg" />
</p>

- #### Mecanismo de Busca em Listas
  
  - Implementado modelo de busca por nome de pokémons em tempo real, a cada alteração de caractere.
  
<p align="center">
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/pesquisar_pokemon.gif" />
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/pesquisar_tipo.gif" />
</p>

- #### Detalhes do Pokémon

  - As informações de PESO e ALTURA do pokémon são SEMPRE buscadas de forma assíncrona, via API em tempo real! (não ficam armazenadas no banco de dados);
  - Enquanto é solicitada a busca, é mostrado um ProgressBar para informar ao usuário que essa informação está sendo processada.
  
<p align="center">
  <img height="600px" src="https://github.com/BernardoSlailati/DesafioSquadra-Android-Pokedex/blob/main/readme-media/detalhes_pokemon.gif" />
</p>

### Ferramentas gerais de desenvolvimento

- IDE: **Android Studio 4.1.1**
- Versão Android SDK Alvo/Compilada: **29**
- Versão Android SDK Mínima: **21 -> Android 5.0 (LOLLIPOP)**
- APIS consumidas:
  - [PokéAPI](https://pokeapi.co/): uso ilimitado

### Principas bibliotecas utilizadas

- **Glide**: apresentar GIFs em tela e aplicar lazy loading em imagens ([https://github.com/bumptech/glide](https://github.com/bumptech/glide), 30.8k stars | 5.6k forks | boa documentação)
- **Retrofit2**: cliente HTTP de tipagem segura para Android e Java com métodos síncronos e assíncronos ([https://github.com/square/retrofit](https://github.com/square/retrofit), 37.7k stars | 6.8k forks | boa documentação)
- **Room**: abstração para criar, armazenar e gerenciar banco de dados SQLite (Jetpack Components)
- **LiveData**: fornece uma classe que armazena dados observáveis atrelado com o reconhecimento de ciclo de vida dos componentes da aplicação (Jetpack Components)
- **ViewModel**: projetado para armazenar e gerenciar dados relacionados à IU de uma maneira consciente do ciclo de vida (Jetpack Components)
- **Fragment**: gera várias telas independentes que se atrelam uma Activity, aplicado no padrão de tabs (Jetpack Components)
- **RecyclerView**: recomendado para mostrar grandes conjuntos de dados por minimizar o uso de memória (Jetpack Components)

### Escolhas e Por quês

- Uso de ViewBinding
  - Remover boilerplate do método "findViewById" 

- RecyclerView / Spinner + Adapters Customizados
  - Utilizados para gerar uma melhor interação com o usuário, minimizar o uso de memória e otimizar layout

- Banco de dados local SQLite
  - Inserção em banco de dados dos Pokémons buscados via requisição HTTP (remover necessidade de busca repetida, a cada novo acesso à aplicação, de todos os dados de Pokémons)
  - Gerenciado pela biblioteca ROOM (Database, DAO e Entity), fortemente recomendada e inserida no Jetpack Components (junto com a biblioteca SQLite)
  - Acesso via Repository + ViewModel para otimizar buscas e evitar travamento de telas
  
- TabLayout + PageViewer + Fragments
  - Aprimorar acesso dos usuário às listas através tabs deslizantes intuitivas e reaproveitáveis (aplicado na listagem de pokémons por geração, juntamente com um ViewModel relacionado, que informa a View qual a geração de pokémons de cada tela)

- Requisições HTTP Assíncronas
  - A fim de não travar a interação do usuário na aplicação, foram utilizadas requisições assíncronas via método "enqueue" abstraído pela biblioteca Retrofit2

- Monitoramento de alterações no banco de dados
  - Usado método "observer" de LiveData para preencher as listas de itens inseridos nos RecyclerViews

- SearchView + RecyclerView para buscas
  - Realização de buscas de itens mostrados dentro de RecyclerViews, no padrão de atualização da busca a cada modificação de caracter (tempo real)

- Lazy loading de imagens
  - Imagens utilizadas de boa qualidade e resolução, utilizada então a biblioteca Glide para carregar de forma assíncrona as figuras relacionadas aos pokémons e os tipos (grama, fogo, água, etc.)
  - Também utilizado o Glide para gerar a animação de GIF

- Uso de ProgressBar em momentos oportunos
  - Visando aprimorar a interatividade do usuário, mostrando que algo está sendo carregado


__*Observação*__: percebeu-se no final do desafio que a classe utilizada *AsyncTask* será depreciada a partir da API 30 ([ver mais](https://developer.android.com/reference/android/os/AsyncTask)). Com isso, para futuras correções, a mais importante, seria a alteração dessa para classes como *Executor* ou *FutureTask* do pacote *java.util.concurrent*.

### Outras funcionalidades que ainda podem ser adicionadas

- Opção de favoritar pokémons (adicionar ícone de estrela a ser preenchida em cada pokémon), sendo estes os primeiros a serem listados (necessário adicionar um campo booleano "favorito" na tabela "pokemons" SQLite);
- Adicionar outro Spinner de escolha para incrementar a busca por tipo de pokémons (cada pokémon pode ter no máximo duas tipagens);
- Adicionar tela de "lista vazia" para ser mostrada na aba de busca por tipos;
- Obter mais informações de cada pokémon para serem mostradas (evoluções, golpes, fraquezas, etc.);
- Implementar animações utilizando a biblioteca Animation (inserida no conjunto Jetpack Compose) em itens de RecyclerViews e transição de Activities para melhorar interação com o usuário em momentos de espera;
- Alterar elemento SearchView para conjunto ImageView + EditText para otimizar experiência e layout de busca;
- Modificar busca para nome OU também número.
