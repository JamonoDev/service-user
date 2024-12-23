version: '3.8'

services:
  user-service:
    build: .
    ports:
      - "8083:8083"  # Mappe le port 8083 du container à 8083 sur la machine hôte
    environment:
      SPRING_PROFILES_ACTIVE: docker
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/usersdb
      SPRING_DATASOURCE_USERNAME: user
      SPRING_DATASOURCE_PASSWORD: password
    depends_on:
      - postgres  # Assure-toi que `user-service` attend que PostgreSQL soit prêt
    networks:
      - mynetwork

  postgres:
    image: postgres:latest  # Utilisation de l'image officielle de PostgreSQL
    environment:
      POSTGRES_USER: user
      POSTGRES_PASSWORD: password
      POSTGRES_DB: usersdb
    ports:
      - "5432:5432"  # Expose le port PostgreSQL 5432
    networks:
      - mynetwork

networks:
  mynetwork:
    driver: bridge
