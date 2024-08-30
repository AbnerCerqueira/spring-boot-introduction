# Exemplo simples de cadastro/login
- Somente usuários autenticados com Bearer token podem acessar a rota protegida.

# SECRET KEY
- [RSA KEY GENERATOR](https://www.csfieldguide.org.nz/en/interactives/rsa-key-generator/)
- SELECIONE 2048 bits no Key Size
- SELECIONE PKCS #8 (base64) no Format Scheme, gere a public e private key
- No diretório `imo/backend/src/main/resources`
- Crie o arquivo **app.pub** coloque nele a chave PUBLICA
- Crie o arquivo  **app.key** coloque nele a chave PRIVADA

# Run
```
docker-compose up -d
./mvnw spring:boot-run
```
