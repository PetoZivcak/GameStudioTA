# Docs
## Local Development

```
code
```

## Structure
src/main/java

- entity - spolocne, preto moze byt top-level
- client
  - **SpringClient.java**
  - services/
    - JPA and JDBC, ale idealne by si mal mat uz len rest 
    - ScoreServiceREST
  - minesweeper/
  - stones/
- server
  - **GameStudioServer.java**
  - jpa/ | repository | hibernate dao - data access object
    - ScoreJPA
  - webservice | controllers
    - ScoreWebServiceRest | ScoreRestController

src/main/WEB-INF/pages
- html
- css
- js

ConsoleUI -> ScoreJPA -> DB // no client, server model
ConsoleUI -> ScoreRestClient (client) ------HTTP------> ScoreWebServicesRest ->ScoreJPA -> DB (server)