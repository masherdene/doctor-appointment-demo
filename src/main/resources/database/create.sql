CREATE TABLE IF NOT EXISTS appointment (
    appointmentId INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    appointmentDate TIMESTAMP NOT NULL,
    doctorId INT(11) NULL,
    patientId INT(11) NULL,
    treatmentId INT(11) NULL,
    FOREIGN KEY (doctorId) REFERENCES doctor(doctorId),
    FOREIGN KEY (patientId) REFERENCES patient(patientId),
    FOREIGN KEY (treatmentId) REFERENCES treatment(treatmentId)
);

CREATE TABLE IF NOT EXISTS doctor (
    doctorId INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    doctorName VARCHAR(100) NOT NULL,
    doctorSpecialization VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS patient (
    patientId INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    patientName VARCHAR(100) NOT NULL,
    patientEmail VARCHAR(100) NOT NULL,
    patientCondition VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS treatment (
    treatmentId INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    treatmentName VARCHAR(50) NOT NULL,
    treatmentType VARCHAR(50) NOT NULL
);

