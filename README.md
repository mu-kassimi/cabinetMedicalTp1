# cabinetMedicalTp1
Rapport TP1 - Application Monolithique : Gestion d'un Cabinet Médical
Master IPS - Systèmes Distribués Basés sur les Microservices
Faculté des Sciences de Rabat
Auteur : Mustapha Kassimi
Date : 23 Décembre 2025

📋 Table des Matières
Introduction

Architecture du Projet

Modèle de Données

Endpoints REST

Tests et Validations

Conclusion

🎯 Introduction
Ce projet correspond au premier TP du module Systèmes Distribués Basés sur les Microservices. L'objectif était de développer une application monolithique Spring Boot pour la gestion d'un cabinet médical. Cette application sert de base pour une future migration vers une architecture microservices.

Objectifs atteints :

✅ Création d'un projet Spring Boot monolithique

✅ Implémentation de l'architecture en couches (Web, Service, Repository, Modèle)

✅ Modélisation des entités métier principales

✅ Exposition des opérations CRUD via API REST

✅ Préparation pour une future découpe en microservices

🏗️ Architecture du Projet
Structure des Packages
text
ma.fsr.tp1.cabinetmedical/
├── CabinetMedicalTp1Application.java    # Classe principale
├── model/                              # Entités JPA
│   ├── Patient.java
│   ├── Medecin.java
│   ├── RendezVous.java
│   └── Consultation.java
├── repository/                         # Interfaces Spring Data JPA
│   ├── PatientRepository.java
│   ├── MedecinRepository.java
│   ├── RendezVousRepository.java
│   └── ConsultationRepository.java
├── service/                           # Couche métier
│   ├── PatientService.java
│   ├── MedecinService.java
│   ├── RendezVousService.java
│   └── ConsultationService.java
└── web/                               # Contrôleurs REST
    ├── PatientController.java
    ├── MedecinController.java
    ├── RendezVousController.java
    └── ConsultationController.java
Technologies Utilisées
Spring Boot 4.0.1 (avec Spring Web, Spring Data JPA)

Base de données H2 (en mémoire)

Lombok pour la réduction du code boilerplate

Java +17

Configuration Spring Boot
properties
# application.properties
spring.application.name=cabinetMedicalTp1
spring.datasource.url=jdbc:h2:mem:cabinetMedicalTp1DB
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
📊 Modèle de Données
Entité Patient
java
@Entity
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private LocalDate dateNaissance;
    private String telephone;
    private String genre; // "M" ou "F"
}
Entité Medecin
java
@Entity
public class Medecin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String specialite;
    private String email;
}
Entité RendezVous
java
@Entity
public class RendezVous {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateRdv;
    private String statut; // "CONFIRME", "EN_ATTENTE", "ANNULE"
    
    @ManyToOne
    private Patient patient;
    
    @ManyToOne
    private Medecin medecin;
}
Entité Consultation
java
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
Données d'Initialisation (data.sql)
sql
-- Insertion des Patients
INSERT INTO patient (nom, date_naissance, telephone, genre) VALUES
                                                                    ( 'Ahmed El Alami', '1985-03-15', '0612345678', 'M'),
                                                                    ( 'Fatima Zahra', '1990-07-22', '0623456789', 'F'),
                                                                    ( 'Yassine Bennani', '1978-11-30', '0634567890', 'M'),
                                                                    ( 'Khadija Mansouri', '1995-05-10', '0645678901', 'F'),
                                                                    ( 'Omar Ait Ali', '1982-09-18', '0656789012', 'M'),
                                                                    ( 'Salma Idrissi', '1988-12-25', '0667890123', 'F');

-- Insertion des Médecins
INSERT INTO medecin (nom, specialite, email) VALUES
                                                     ('Dr. Mehdi Benjelloun', 'Cardiologue', 'mehdi.benjelloun@cabinet.ma'),
                                                     ( 'Dr. Samira Alaoui', 'Pédiatre', 'samira.alaoui@cabinet.ma'),
                                                     ( 'Dr. Karim Tazi', 'Généraliste', 'karim.tazi@cabinet.ma'),
                                                     ( 'Dr. Leila Fassi', 'Dermatologue', 'leila.fassi@cabinet.ma');

-- Insertion des Rendez-vous
INSERT INTO rendez_vous (date_rdv, statut, patient_id, medecin_id) VALUES
                                                                           ('2024-12-24', 'CONFIRME', 1, 1),
                                                                           ( '2024-12-25', 'EN_ATTENTE', 2, 2),
                                                                           ( '2024-12-26', 'CONFIRME', 3, 3),
                                                                           ( '2024-12-27', 'ANNULE', 4, 4),
                                                                           ( '2024-12-28', 'CONFIRME', 5, 1),
                                                                           ( '2024-12-29', 'EN_ATTENTE', 6, 3);

-- Insertion des Consultations
INSERT INTO consultation (date_consultation, rapport, rendez_vous_id) VALUES
                                                                              ( '2024-12-24', 'Consultation cardiologique : Tension artérielle normale. Prescription de médicaments pour le cholestérol.', 1),
                                                                              ( '2024-12-26', 'Consultation générale : Grippe saisonnière. Repos recommandé et traitement symptomatique.', 3),
                                                                              ( '2024-12-28', 'Consultation cardiologique : Suivi post-opératoire. Évolution favorable.', 5);
🧪 Tests et Validations
1. Test GET Patients
Requête :
<img width="1279" height="756" alt="Screenshot 2025-12-23 185405" src="https://github.com/user-attachments/assets/49cf4ae6-1b34-465b-882a-9b5765ecff4d" />

2. Test POST Patient
<img width="1279" height="766" alt="Screenshot 2025-12-23 192700" src="https://github.com/user-attachments/assets/535b1194-49c5-4692-86a8-1ca07715f9b8" />

3. Test GET Médecins
<img width="1251" height="775" alt="Screenshot 2025-12-23 192822" src="https://github.com/user-attachments/assets/c04c9ca0-7a21-4915-af5f-0a6248bcd212" />

4. Test Post Médecins
   
   <img width="1305" height="758" alt="image" src="https://github.com/user-attachments/assets/9c170f51-c83b-4701-8304-832ef9edd02f" />

5. Test Post Consultation
   <img width="1345" height="768" alt="Screenshot 2025-12-23 212852" src="https://github.com/user-attachments/assets/ad808022-b136-4cf9-b7ea-ca4313edcd6b" />


🎓 Conclusion
Objectifs Atteints
✅ Application monolithique Spring Boot fonctionnelle

✅ Architecture en couches bien définie

✅ API REST complète pour les 4 entités principales

✅ Base de données H2 avec données de test

✅ Validation métier dans la couche service

✅ Préparation pour découpage en microservices

Points Forts
Code propre avec séparation des responsabilités

Validation métier intégrée dans les services

API RESTful conforme aux standards

Configuration externalisée pour flexibilité

Tests manuels complets avec Postman

Améliorations Possibles
Ajouter la validation avec annotations (@Valid, @NotNull)

Implémenter la gestion des erreurs avec @ControllerAdvice

Ajouter des tests unitaires et d'intégration

Implémenter la pagination pour les listes

Ajouter l'authentification (Spring Security)
