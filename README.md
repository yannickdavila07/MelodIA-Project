SEJA BEM-VINDO!

Criei essa aplicação Java/Spring Boot que gerencia um catálogo musical e utiliza a API da Groq (Llama 3) para gerar biografias de artistas, contando com uma camada de persistência em PostgreSQL para cache inteligente de dados.

Um diferencial técnico deste projeto é a implementação das consultas customizadas utilizando JPQL. Ao contrário do SQL nativo, o JPQL foca nas entidades Java em vez das tabelas do banco de dados. Isso garante que a aplicação mantenha sua independência de fornecedor (Database Agnostic). Caso haja a necessidade de migrar do PostgreSQL para outro banco de dados relacional, como MySQL ou Oracle, a camada de dados permanecerá íntegra e funcional, sem a necessidade de reescrever queries complexas, respeitando os princípios de baixo acoplamento do ecossistema Spring.


Desenvolver um sistema que consome IA moderna é um exercício de persistência. Durante a construção deste projeto, o maior desafio não foi a lógica do backend em Java, mas sim encontrar um provedor de IA que oferecesse um equilíbrio real entre gratuidade, estabilidade e facilidade de integração.
