# Pets API

## Set Up

1. Make sure your mysql database server is running. 
   start learn-mysql`. 
2. Open the [production SQL script](./sql/pet-schema-prod.sql) in MySQL WorkBench and execute it. Note that it adds 
   some starter production data.
3. Open this folder in Intellij. 
4. Go to Run, Edit Configurations, Edit Configuration Templates, and create a new configuration template for 
   Application. Set your environmental variables for `DB_USERNAME` and `DB_PASSWORD`. See screenshot below.
5. Run your App. Open the endpoints in the [http directory](./http/requests.http) in VS Code and test that CRUD is 
   functional. 
6. 
![IntelliJ edit configurations window](https://imgur.com/zT92RJk)

