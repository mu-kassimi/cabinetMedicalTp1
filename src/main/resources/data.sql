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