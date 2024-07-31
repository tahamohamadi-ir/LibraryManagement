CREATE TABLE Person (
                        id INT PRIMARY KEY,
                        firstName VARCHAR(50),
                        lastName VARCHAR(50),
                        gender VARCHAR(10),
                        birthDate DATE,
                        nationalities VARCHAR(50)
);
CREATE TABLE Member (
                        id INT PRIMARY KEY,
                        person_id INT,
                        phoneNumber VARCHAR(20),
                        address VARCHAR(100),
                        membershipStatus VARCHAR(20),
                        email VARCHAR(50),
                        registerDate DATE,
                        nationalCode VARCHAR(20),
                        fatherName VARCHAR(50),
                        FOREIGN KEY (person_id) REFERENCES Person(id)
);
CREATE TABLE Author (
                        id INT PRIMARY KEY,
                        person_id INT,
                        FOREIGN KEY (person_id) REFERENCES Person(id)
);

CREATE TABLE Translator (
                            id INT PRIMARY KEY,
                            person_id INT,
                            FOREIGN KEY (person_id) REFERENCES Person(id)
);

CREATE TABLE Book (
                      id INT PRIMARY KEY,
                      title VARCHAR(100),
                      bookNumber INT,
                      isbn VARCHAR(20),
                      publisher VARCHAR(50),
                      publicationDate DATE,
                      publicationYear INT,
                      pages INT,
                      language VARCHAR(20),
                      description TEXT
);

CREATE TABLE BorrowList (
                            id INT PRIMARY KEY,
                            book_id INT,
                            member_id INT,
                            borrowDate TIMESTAMP,
                            returnDate TIMESTAMP,
                            dueDays INT,
                            isReturned BOOLEAN,
                            isExtended BOOLEAN,
                            extendCount INT,
                            penalty DECIMAL(10, 2),
                            FOREIGN KEY (book_id) REFERENCES Book(id),
                            FOREIGN KEY (member_id) REFERENCES Member(id)
);
INSERT INTO Person (id, firstName, lastName, gender, birthDate, nationalities) VALUES
                                                                                   (1, 'John', 'Doe', 'MALE', '1985-06-15', 'AMERICAN'),
                                                                                   (2, 'Jane', 'Smith', 'FEMALE', '1990-07-22', 'CANADIAN'),
                                                                                   (3, 'Alice', 'Johnson', 'FEMALE', '1978-03-05', 'ENGLISH'),
                                                                                   (4, 'Bob', 'Brown', 'MALE', '1982-11-19', 'AUSTRALIAN'),
                                                                                   (5, 'Charlie', 'Davis', 'MALE', '1995-02-10', 'ITALIAN'),
                                                                                   (6, 'Diana', 'Wilson', 'FEMALE', '1988-04-25', 'FRENCH'),
                                                                                   (7, 'Edward', 'Miller', 'MALE', '1972-08-30', 'GERMAN'),
                                                                                   (8, 'Fiona', 'Clark', 'FEMALE', '1983-12-17', 'SPANISH'),
                                                                                   (9, 'George', 'Lewis', 'MALE', '1992-01-02', 'RUSSIAN'),
                                                                                   (10, 'Hannah', 'Walker', 'FEMALE', '1986-05-14', 'ARGENTINIAN');

INSERT INTO Member (id, person_id, phoneNumber, address, membershipStatus, email, registerDate, nationalCode, fatherName) VALUES
                                                                                                                              (1, 1, '1234567890', '123 Elm St', 'ACTIVE', 'john.doe@example.com', '2022-01-01', '1111111111', 'Michael Doe'),
                                                                                                                              (2, 2, '2345678901', '456 Maple Ave', 'INACTIVE', 'jane.smith@example.com', '2022-02-15', '2222222222', 'David Smith'),
                                                                                                                              (3, 3, '3456789012', '789 Oak Dr', 'SUSPENDED', 'alice.johnson@example.com', '2022-03-20', '3333333333', 'Robert Johnson'),
                                                                                                                              (4, 4, '4567890123', '101 Pine Ln', 'ACTIVE', 'bob.brown@example.com', '2022-04-10', '4444444444', 'James Brown'),
                                                                                                                              (5, 5, '5678901234', '202 Cedar Ct', 'INACTIVE', 'charlie.davis@example.com', '2022-05-05', '5555555555', 'Richard Davis'),
                                                                                                                              (6, 6, '6789012345', '303 Birch St', 'ACTIVE', 'diana.wilson@example.com', '2022-06-25', '6666666666', 'Thomas Wilson'),
                                                                                                                              (7, 7, '7890123456', '404 Spruce Ave', 'SUSPENDED', 'edward.miller@example.com', '2022-07-19', '7777777777', 'Joseph Miller'),
                                                                                                                              (8, 8, '8901234567', '505 Aspen Way', 'ACTIVE', 'fiona.clark@example.com', '2022-08-13', '8888888888', 'Patrick Clark'),
                                                                                                                              (9, 9, '9012345678', '606 Redwood Rd', 'INACTIVE', 'george.lewis@example.com', '2022-09-02', '9999999999', 'Charles Lewis'),
                                                                                                                              (10, 10, '0123456789', '707 Cypress Ln', 'ACTIVE', 'hannah.walker@example.com', '2022-10-11', '0000000000', 'Stephen Walker');

INSERT INTO Author (id, person_id) VALUES
                                       (1, 1),
                                       (2, 2),
                                       (3, 3),
                                       (4, 4),
                                       (5, 5),
                                       (6, 6),
                                       (7, 7),
                                       (8, 8),
                                       (9, 9),
                                       (10, 10);

INSERT INTO Translator (id, person_id) VALUES
                                           (1, 6),
                                           (2, 7),
                                           (3, 8),
                                           (4, 9),
                                           (5, 10),
                                           (6, 1),
                                           (7, 2),
                                           (8, 3),
                                           (9, 4),
                                           (10, 5);

INSERT INTO Book (id, title, bookNumber, isbn, publisher, publicationDate, publicationYear, pages, language, description) VALUES
                                                                                                                              (1, 'The Great Book', 1001, '978-3-16-148410-0', 'Publisher A', '2020-01-01', 2020, 300, 'English', 'A great book on great things.'),
                                                                                                                              (2, 'Another Story', 1002, '978-1-23-456789-7', 'Publisher B', '2021-02-15', 2021, 250, 'French', 'A captivating story.'),
                                                                                                                              (3, 'The Science Book', 1003, '978-0-12-345678-9', 'Publisher C', '2019-03-30', 2019, 400, 'German', 'Exploring the world of science.'),
                                                                                                                              (4, 'Mystery Novel', 1004, '978-3-16-148419-3', 'Publisher D', '2018-04-10', 2018, 320, 'Spanish', 'A thrilling mystery novel.'),
                                                                                                                              (5, 'History of the World', 1005, '978-1-84-456789-0', 'Publisher E', '2017-05-20', 2017, 500, 'Italian', 'A comprehensive history book.'),
                                                                                                                              (6, 'Programming 101', 1006, '978-3-16-148423-0', 'Publisher F', '2022-06-11', 2022, 600, 'English', 'An introduction to programming.'),
                                                                                                                              (7, 'Philosophy Thoughts', 1007, '978-1-23-456783-6', 'Publisher G', '2016-07-01', 2016, 350, 'French', 'Delving into philosophical ideas.'),
                                                                                                                              (8, 'Travel Guide', 1008, '978-0-12-345672-5', 'Publisher H', '2015-08-15', 2015, 280, 'German', 'A guide to travel the world.'),
                                                                                                                              (9, 'Cooking Recipes', 1009, '978-3-16-148429-2', 'Publisher I', '2014-09-09', 2014, 180, 'Spanish', 'Delicious recipes for every meal.'),
                                                                                                                              (10, 'Art and Culture', 1010, '978-1-84-456785-2', 'Publisher J', '2013-10-30', 2013, 220, 'Italian', 'Exploring art and culture.');

INSERT INTO BorrowList (id, book_id, member_id, borrowDate, returnDate, dueDays, isReturned, isExtended, extendCount, penalty) VALUES
                                                                                                                                   (1, 1, 1, '2023-01-01 00:00:00', '2023-01-15 00:00:00', 14, true, false, 0, 0),
                                                                                                                                   (2, 2, 2, '2023-02-01 00:00:00', '2023-02-15 00:00:00', 14, true, false, 0, 0),
                                                                                                                                   (3, 3, 3, '2023-03-01 00:00:00', '2023-03-15 00:00:00', 14, false, true, 1, 50),
                                                                                                                                   (4, 4, 4, '2023-04-01 00:00:00', '2023-04-15 00:00:00', 14, true, false, 0, 0),
                                                                                                                                   (5, 5, 5, '2023-05-01 00:00:00', '2023-05-15 00:00:00', 14, true, false, 0, 0),
                                                                                                                                   (6, 6, 6, '2023-06-01 00:00:00', '2023-06-15 00:00:00', 14, false, true, 2, 100),
                                                                                                                                   (7, 7, 7, '2023-07-01 00:00:00', '2023-07-15 00:00:00', 14, true, false, 0, 0),
                                                                                                                                   (8, 8, 8, '2023-08-01 00:00:00', '2023-08-15 00:00:00', 14, true, false, 0, 0),
                                                                                                                                   (9, 9, 9, '2023-09-01 00:00:00', '2023-09-15 00:00:00', 14, false, false, 0, 150),
                                                                                                                                   (10, 10, 10, '2023-10-01 00:00:00', '2023-10-15 00:00:00', 14, true, false, 0, 0);