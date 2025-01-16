CREATE TABLE Client (
    ID_Client INT AUTO_INCREMENT PRIMARY KEY,
    Nom VARCHAR(100) NOT NULL,
    Prenom VARCHAR(100) NOT NULL,
    Adresse VARCHAR(255),
    Email VARCHAR(100),
    Telephone VARCHAR(15)
);

CREATE TABLE Conseiller (
    ID_Conseiller INT AUTO_INCREMENT PRIMARY KEY,
    Nom VARCHAR(100) NOT NULL,
    Prenom VARCHAR(100) NOT NULL,
    Email VARCHAR(100),
    Telephone VARCHAR(15)
);

CREATE TABLE Compagnie (
    ID_Compagnie INT AUTO_INCREMENT PRIMARY KEY,
    Nom VARCHAR(100) NOT NULL,
    Pays_Origine VARCHAR(100) NOT NULL
);

CREATE TABLE Vol (
    ID_Vol INT AUTO_INCREMENT PRIMARY KEY,
    ID_Compagnie INT NOT NULL,
    Lieu_Depart VARCHAR(100) NOT NULL,
    Lieu_Arrivee VARCHAR(100) NOT NULL,
    Date_Depart DATE NOT NULL,
    Heure_Depart TIME NOT NULL,
    Date_Arrivee DATE NOT NULL,
    Heure_Arrivee TIME NOT NULL,
    FOREIGN KEY (ID_Compagnie) REFERENCES Compagnie(ID_Compagnie)
);

CREATE TABLE Reservation (
    ID_Reservation INT AUTO_INCREMENT PRIMARY KEY,
    ID_Client INT NOT NULL,
    ID_Conseiller INT NOT NULL,
    ID_Vol INT NOT NULL,
    Date_Reservation DATE NOT NULL,
    Classe ENUM('economique', 'premiere classe', 'business') NOT NULL,
    Type_Paiement ENUM('CB', 'virement', 'paypal', 'especes') NOT NULL, 
    Statut ENUM('confirme', 'annule') NOT NULL,
    Nom_Reservateur VARCHAR(100),
    Prenom_Reservateur VARCHAR(100),
    Telephone_Reservateur VARCHAR(15),
    FOREIGN KEY (ID_Client) REFERENCES Client(ID_Client),
    FOREIGN KEY (ID_Conseiller) REFERENCES Conseiller(ID_Conseiller),
    FOREIGN KEY (ID_Vol) REFERENCES Vol(ID_Vol)
);





INSERT INTO Client (Nom, Prenom, Adresse, Email, Telephone)
VALUES
('DUPUIS', 'Elise', '17 rue de la Foire, Lille', 'elise.dupuis@exemple.com', '0611111111'),
('LEBLANC', 'Aurelien', '22 boulevard Saint Germain, Paris', 'aurelien.leblanc@exemple.com', '0612121212'),
('FERRARI', 'Isabella', '45 rue des Orangers, Bordeaux', 'isabella.ferrari@exemple.com', '0613131313'),
('MARTIN', 'Leo', '8 avenue de la Liberte, Rennes', 'leo.martin@exemple.com', '0614141414'),
('KIM', 'Yumi', '12 rue des Fleurs, Lyon', 'yumi.kim@exemple.com', '0615151515'),
('BERGER', 'Julian', '9 rue des Roses, Toulouse', 'julian.berger@exemple.com', '0616161616'),
('MANY', 'Sarah', '23 avenue du Parc, Strasbourg', 'sarah.many@exemple.com', '0617171717'),
('LEROY', 'Chloe', '34 rue de la Republique, Montpellier', 'chloe.leroy@exemple.com', '0618181818'),
('AGUERO', 'Samy', '4 avenue des Lilas, Annecy', 'samy.aguero@exemple.com', '0619191919'),
('MORENO', 'Diego', '78 rue du Vieux Port, Nice', 'diego.moreno@exemple.com', '0620202020'),
('CHEVALIER', 'Maxime', '1 rue de la Montagne, Lorient', 'maxime.chevalier@exemple.com', '0621212121'),
('FLEURY', 'Lucie', '49 allee du Mons, Amiens', 'lucie.fleury@exemple.com', '0622222222'),
('LAURENT', 'Henri', '10 boulevard des Etoiles, Clermont-Ferrand', 'henri.laurent@exemple.com', '0623232323');


INSERT INTO Conseiller (Nom, Prenom, Email, Telephone)
VALUES
('CORNET', 'Emma', 'emma.cornet@travelagency.com', '0711223344'),
('DE SULLY', 'Antoine', 'antoine.desully@travelagency.com', '0722334455'),
('MANEL', 'Sarah', 'sarah.manel@travelagency.com', '0733445566'),
('GOFFIN', 'Simon', 'simon.goffin@travelagency.com', '0744556677'),
('DURAND', 'Lucie', 'lucie.durand@travelagency.com', '0755667788');


INSERT INTO Compagnie (ID_Compagnie, Nom, Pays_Origine)
VALUES
(1, 'Emirates', 'Emirats Arabes Unis'),
(2, 'Qatar Airways', 'Qatar'),
(3, 'Swiss International AirLines', 'Suisse'),
(4, 'Royal Air Maroc', 'Maroc'),
(5, 'Air France', 'France'),
(6, 'British Airways', 'Royaume-Uni'),
(7, 'Lufthansa', 'Allemagne'),
(8, 'Delta Airlines', 'États-Unis'),
(9, 'Ryanair', 'Irlande'),
(10, 'Turkish Airlines', 'Turquie'),
(11, 'Cathay Pacific', 'Hong Kong'),
(12, 'All Nippon Airways', 'Japon'),
(13, 'Qantas', 'Australie'),
(14, 'Malaysia Airlines', 'Malaisie'),
(15, 'KLM', 'Pays-Bas'),
(16, 'Iberia', 'Espagne'),
(17, 'Austrian Airlines', 'Autriche'),
(18, 'IndiGo', 'Inde'),
(19, 'South African Airways', 'Afrique du Sud'),
(20, 'Singapore Airlines', 'Singapour');



INSERT INTO Vol (ID_Compagnie, Lieu_Depart, Lieu_Arrivee, Date_Depart, Heure_Depart, Date_Arrivee, Heure_Arrivee)
VALUES
(1, 'Dubai', 'Paris', '2025-01-20', '14:00:00', '2025-01-20', '18:00:00'),
(2, 'Doha', 'Londres', '2025-01-21', '15:30:00', '2025-01-21', '19:30:00'),
(3, 'Zurich', 'Charleroi', '2025-02-08', '11:00:00', '2025-02-08', '12:40:00'),
(4, 'Casablanca', 'Berne', '2025-01-25', '10:00:00', '2025-01-25', '12:55:00'),
(5, 'Paris', 'Tokyo', '2025-01-24', '22:30:00', '2025-01-26', '17:00:00'),
(6, 'Londres', 'Los Angeles', '2025-01-23', '21:00:00', '2025-01-24', '05:30:00'),
(7, 'Francfort', 'Tokyo', '2025-01-26', '13:00:00', '2025-01-27', '01:00:00'),
(8, 'Atlanta', 'Toronto', '2025-01-27', '09:00:00', '2025-01-27', '10:30:00'),
(9, 'Dublin', 'Bruxelles', '2025-01-28', '08:00:00', '2025-01-28', '09:45:00'),
(10, 'Istanbul', 'Rome', '2025-01-29', '12:00:00', '2025-01-29', '13:30:00'),
(11, 'Hong Kong', 'San Francisco', '2025-01-30', '16:00:00', '2025-01-31', '08:00:00'),
(12, 'Tokyo', 'Los Angeles', '2025-01-31', '19:00:00', '2025-01-31', '11:00:00'),
(13, 'Sydney', 'Auckland', '2025-02-01', '09:00:00', '2025-02-01', '10:30:00'),
(14, 'Kuala Lumpur', 'Bali', '2025-02-02', '11:00:00', '2025-02-02', '12:00:00'),
(15, 'Amsterdam', 'Bordeaux', '2025-02-03', '16:30:00', '2025-02-03', '18:30:00'),
(16, 'Madrid', 'Lisbonne', '2025-02-04', '10:00:00', '2025-02-04', '11:00:00'),
(17, 'Vienne', 'Varsovie', '2025-02-05', '14:00:00', '2025-02-05', '15:30:00'),
(18, 'New Delhi', 'Mumbai', '2025-02-06', '09:00:00', '2025-02-06', '10:30:00'),
(19, 'Johannesburg', 'Le Cap', '2025-02-07', '11:00:00', '2025-02-07', '12:00:00'),
(20, 'Singapour', 'New York', '2025-01-22', '23:30:00', '2025-01-23', '17:00:00');


INSERT INTO Reservation (ID_Client, ID_Conseiller, ID_Vol, Date_Reservation, Classe, Type_Paiement, Statut, Nom_Reservateur, Prenom_Reservateur, Telephone_Reservateur)
VALUES
(1, 4, 1, '2025-01-10', 'economique', 'CB', 'confirme', 'DUPUIS', 'Elise', '0611111111'),
(2, 5, 3, '2025-01-11', 'business', 'virement', 'confirme', 'LEBLANC', 'Aurelien', '0612121212'),
(3, 1, 5, '2025-01-12', 'premiere classe', 'paypal', 'annule', 'FERRARI', 'Isabella', '0613131313'),
(4, 3, 7, '2025-01-13', 'economique', 'especes', 'confirme', 'MARTIN', 'Leo', '0614141414'),
(5, 2, 9, '2025-01-14', 'business', 'CB', 'confirme', 'KIM', 'Yumi', '0615151515');