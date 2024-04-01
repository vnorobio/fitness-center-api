# fitness-center-api
Mamba Negra CAF backend api

### Generate key files
```
ssh-keygen -t rsa -b 4096 -m PEM -f jwtRS256.key
openssl rsa -in jwtRS256.key -pubout -outform PEM -out jwtRS256.key.pub
```
```
DATABASE_URL=jdbc:postgresql://localhost:5432/black_mamba;DATABASE_USER_NAME=postgres;DATABASE_PASSWORD=m4mb4
```