Cabinet Médical - Application Monolithique
Informations du Projet
Programme : Master IPS - Systèmes Distribués Basés sur les Microservices
Institution : Faculté des Sciences de Rabat
Auteur : Mustapha Kassimi
Date : 23 Décembre 2025

Table des Matières

Introduction
Architecture du Projet
Modèle de Données
API REST
Tests et Validation
Conclusion


Introduction
Ce projet représente le premier travail pratique du module Systèmes Distribués Basés sur les Microservices. Il consiste en le développement d'une application monolithique utilisant Spring Boot pour la gestion d'un cabinet médical. Cette application constitue la base d'une future migration vers une architecture microservices.
Objectifs du Projet

Création d'une application Spring Boot monolithique
Implémentation d'une architecture en couches (Web, Service, Repository, Modèle)
Modélisation des entités métier
Exposition d'opérations CRUD via API REST
Préparation à la transition microservices


Architecture du Projet
Structure des Packages
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
Technologies Utilisées
TechnologieVersion/DescriptionSpring Boot4.0.1Spring WebInclusSpring Data JPAInclusBase de donnéesH2 (en mémoire)LombokRéduction du code boilerplateJava17+
Configuration
propertiesspring.application.name=cabinetMedicalTp1
spring.datasource.url=jdbc:h2:mem:cabinetMedicalTp1DB
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true

Modèle de Données
Entité Patient
java@Entity
public class Patient {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private LocalDate dateNaissance;
    private String telephone;
    private String genre; // "M" ou "F"
}
Entité Medecin
java@Entity
public class Medecin {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String specialite;
    private String email;
}
Entité RendezVous
java@Entity
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
Entité Consultation
java@Entity
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
Initialisation des Données
Le fichier data.sql contient les données initiales :

6 patients
4 médecins
6 rendez-vous
3 consultations


API REST
Endpoints Patient
MéthodeEndpointDescriptionGET/api/patientsRécupérer tous les patientsGET/api/patients/{id}Récupérer un patient par IDPOST/api/patientsCréer un nouveau patientPUT/api/patients/{id}Mettre à jour un patientDELETE/api/patients/{id}Supprimer un patient
Endpoints Medecin
MéthodeEndpointDescriptionGET/api/medecinsRécupérer tous les médecinsGET/api/medecins/{id}Récupérer un médecin par IDPOST/api/medecinsCréer un nouveau médecinPUT/api/medecins/{id}Mettre à jour un médecinDELETE/api/medecins/{id}Supprimer un médecin
Endpoints RendezVous
MéthodeEndpointDescriptionGET/api/rendezvousRécupérer tous les rendez-vousGET/api/rendezvous/{id}Récupérer un rendez-vous par IDPOST/api/rendezvousCréer un nouveau rendez-vousPUT/api/rendezvous/{id}Mettre à jour un rendez-vousDELETE/api/rendezvous/{id}Supprimer un rendez-vous
Endpoints Consultation
MéthodeEndpointDescriptionGET/api/consultationsRécupérer toutes les consultationsGET/api/consultations/{id}Récupérer une consultation par IDPOST/api/consultationsCréer une nouvelle consultationPUT/api/consultations/{id}Mettre à jour une consultationDELETE/api/consultations/{id}Supprimer une consultation

Tests et Validation
1. Récupération de tous les Patients (GET)
Show Image
2. Création d'un Patient (POST)
Show Image
3. Récupération de tous les Médecins (GET)
Show Image
4. Création d'un Médecin (POST)
Show Image
5. Création d'une Consultation (POST)
Show Image

Conclusion
Réalisations
Ce projet a permis de développer avec succès une application monolithique complète pour la gestion d'un cabinet médical, avec les accomplissements suivants :

Architecture en couches bien structurée et maintenable
API REST complète et conforme aux standards RESTful
Modèle de données robuste avec relations JPA appropriées
Configuration flexible permettant l'évolution du projet
Jeu de données de test facilitant la validation fonctionnelle

Points Forts

Séparation claire des responsabilités entre les couches
Validation de la logique métier dans la couche service
Configuration externalisée pour une meilleure flexibilité
Documentation complète du code et de l'API
Base solide pour la transition vers les microservices
