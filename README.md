# Cabinet Médical - Application Monolithique

## Informations du Projet

**Programme** : Master IPS - Systèmes Distribués Basés sur les Microservices  
**Institution** : Faculté des Sciences de Rabat  
**Auteur** : Mustapha Kassimi  
**Date** : 23 Décembre 2025

---

## Table des Matières

1. [Introduction](#introduction)
2. [Architecture du Projet](#architecture-du-projet)
3. [Modèle de Données](#modèle-de-données)
4. [API REST](#api-rest)
5. [Tests et Validation](#tests-et-validation)
6. [Conclusion](#conclusion)

---

## Introduction

Ce projet représente le premier travail pratique du module **Systèmes Distribués Basés sur les Microservices**. Il consiste en le développement d'une application monolithique utilisant Spring Boot pour la gestion d'un cabinet médical. Cette application constitue la base d'une future migration vers une architecture microservices.

### Objectifs du Projet

- Création d'une application Spring Boot monolithique
- Implémentation d'une architecture en couches (Web, Service, Repository, Modèle)
- Modélisation des entités métier
- Exposition d'opérations CRUD via API REST
- Préparation à la transition microservices

---

## Architecture du Projet

### Structure des Packages

```
ma.fsr.tp1.cabinetmedical/
├── CabinetMedicalTp1Application.java    # Point d'entrée de l'application
├── model/                               # Entités JPA
│   ├── Patient.java
│   ├── Medecin.java
│   ├── RendezVous.java
│   └── Consultation.java
├── repository/                          # Couche d'accès aux données
│   ├── PatientRepository.java
│   ├── MedecinRepository.java
│   ├── RendezVousRepository.java
│   └── ConsultationRepository.java
├── service/                             # Logique métier
│   ├── PatientService.java
│   ├── MedecinService.java
│   ├── RendezVousService.java
│   └── ConsultationService.java
└── web/                                 # Contrôleurs REST
    ├── PatientController.java
    ├── MedecinController.java
    ├── RendezVousController.java
    └── ConsultationController.java
```

### Technologies Utilisées

| Technologie | Version/Description |
|------------|---------------------|
| Spring Boot | 4.0.1 |
| Spring Web | Inclus |
| Spring Data JPA | Inclus |
| Base de données | H2 (en mémoire) |
| Lombok | Réduction du code boilerplate |
| Java | 17+ |

### Configuration

```properties
spring.application.name=cabinetMedicalTp1
spring.datasource.url=jdbc:h2:mem:cabinetMedicalTp1DB
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```

---

## Modèle de Données

### Entité Patient

```java
@Entity
public class Patient {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private LocalDate dateNaissance;
    private String telephone;
    private String genre; // "M" ou "F"
}
```

### Entité Medecin

```java
@Entity
public class Medecin {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String specialite;
    private String email;
}
```

### Entité RendezVous

```java
@Entity
public class RendezVous {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateRdv;
    private String statut; // "CONFIRME", "EN_ATTENTE", "ANNULE"
    
    @ManyToOne
    private Patient patient;
    
    @ManyToOne
    private Medecin medecin;
}
```

### Entité Consultation

```java
@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consultation_seq")
    private Long id;
    private LocalDate dateConsultation;
    private String rapport;
    
    @OneToOne
    private RendezVous rendezVous;
}
```

### Diagramme des Relations

```
Patient 1----* RendezVous *----1 Medecin
                    |
                    | 1
                    |
                    * 1
              Consultation
```

### Initialisation des Données

Le fichier `data.sql` contient les données initiales :

- 6 patients
- 4 médecins
- 6 rendez-vous
- 3 consultations

---

## API REST

### Endpoints Patient

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/patients` | Récupérer tous les patients |
| GET | `/api/patients/{id}` | Récupérer un patient par ID |
| POST | `/api/patients` | Créer un nouveau patient |
| PUT | `/api/patients/{id}` | Mettre à jour un patient |
| DELETE | `/api/patients/{id}` | Supprimer un patient |

### Endpoints Medecin

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/medecins` | Récupérer tous les médecins |
| GET | `/api/medecins/{id}` | Récupérer un médecin par ID |
| POST | `/api/medecins` | Créer un nouveau médecin |
| PUT | `/api/medecins/{id}` | Mettre à jour un médecin |
| DELETE | `/api/medecins/{id}` | Supprimer un médecin |

### Endpoints RendezVous

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/rendezvous` | Récupérer tous les rendez-vous |
| GET | `/api/rendezvous/{id}` | Récupérer un rendez-vous par ID |
| POST | `/api/rendezvous` | Créer un nouveau rendez-vous |
| PUT | `/api/rendezvous/{id}` | Mettre à jour un rendez-vous |
| DELETE | `/api/rendezvous/{id}` | Supprimer un rendez-vous |

### Endpoints Consultation

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/consultations` | Récupérer toutes les consultations |
| GET | `/api/consultations/{id}` | Récupérer une consultation par ID |
| POST | `/api/consultations` | Créer une nouvelle consultation |
| PUT | `/api/consultations/{id}` | Mettre à jour une consultation |
| DELETE | `/api/consultations/{id}` | Supprimer une consultation |

---

## Tests et Validation

### 1. Récupération de tous les Patients (GET)

![Screenshot 2025-12-23 185405](https://github.com/user-attachments/assets/49cf4ae6-1b34-465b-882a-9b5765ecff4d)

### 2. Création d'un Patient (POST)

![Screenshot 2025-12-23 192700](https://github.com/user-attachments/assets/535b1194-49c5-4692-86a8-1ca07715f9b8)

### 3. Récupération de tous les Médecins (GET)

![Screenshot 2025-12-23 192822](https://github.com/user-attachments/assets/c04c9ca0-7a21-4915-af5f-0a6248bcd212)

### 4. Création d'un Médecin (POST)

![Screenshot](https://github.com/user-attachments/assets/9c170f51-c83b-4701-8304-832ef9edd02f)

### 5. Création d'une Consultation (POST)

![Screenshot 2025-12-23 212852](https://github.com/user-attachments/assets/ad808022-b136-4cf9-b7ea-ca4313edcd6b)

---

## Conclusion

### Réalisations

Ce projet a permis de développer avec succès une application monolithique complète pour la gestion d'un cabinet médical, avec les accomplissements suivants :

- Architecture en couches bien structurée et maintenable
- API REST complète et conforme aux standards RESTful
- Modèle de données robuste avec relations JPA appropriées
- Configuration flexible permettant l'évolution du projet
- Jeu de données de test facilitant la validation fonctionnelle

### Points Forts

- Séparation claire des responsabilités entre les couches
- Validation de la logique métier dans la couche service
- Configuration externalisée pour une meilleure flexibilité
- Documentation complète du code et de l'API
- Base solide pour la transition vers les microservices

