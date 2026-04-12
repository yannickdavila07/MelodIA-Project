Neste projeto, utilizei a JPQL (Java Persistence Query Language) para gerenciar as consultas ao banco de dados. Diferente do SQL nativo, o JPQL opera sobre o modelo de entidades Java, o que traz benefícios cruciais:

- Independência de Banco de Dados (Database Agnostic): Ao utilizar JPQL, a aplicação não fica presa a dialetos específicos de um banco de dados (como o PostgreSQL). Se no futuro houver a necessidade de migrar para MySQL, Oracle ou SQL Server, o código das queries permanece o mesmo, delegando ao Hibernate a tradução correta para o novo banco.

- Segurança e Tipagem: As consultas são validadas em tempo de compilação com base nas classes do projeto, reduzindo erros de sintaxe comuns em SQL puro.

- Abstração de Alto Nível: Focamos na lógica de negócio e nos objetos (Entidades) em vez de nos preocuparmos com a estrutura física das tabelas, mantendo o código mais limpo e alinhado aos princípios de Orientação a Objetos.


Desenvolver um sistema que consome IA moderna é um exercício de persistência. Durante a construção deste projeto, o maior desafio não foi a lógica do backend em Java, mas sim encontrar um provedor de IA que oferecesse um equilíbrio real entre gratuidade, estabilidade e facilidade de integração.
