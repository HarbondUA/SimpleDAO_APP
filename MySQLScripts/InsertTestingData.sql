-- Екземпляр 1
INSERT INTO PrintedMaterial (page_count, publisher, title) 
VALUES (400, 'HarperCollins', 'Brave New World');
INSERT INTO Book (id, author, genre) 
VALUES (LAST_INSERT_ID(), 'Aldous Huxley', 'Science Fiction');

-- Екземпляр 2
INSERT INTO PrintedMaterial (page_count, publisher, title) 
VALUES (250, 'Simon & Schuster', 'The Great Gatsby');
INSERT INTO Book (id, author, genre) 
VALUES (LAST_INSERT_ID(), 'F. Scott Fitzgerald', 'Classic');

-- Екземпляр 3
INSERT INTO PrintedMaterial (page_count, publisher, title) 
VALUES (320, 'Random House', 'To Kill a Mockingbird');
INSERT INTO Book (id, author, genre) 
VALUES (LAST_INSERT_ID(), 'Harper Lee', 'Classic');

-- Екземпляр 4
INSERT INTO PrintedMaterial (page_count, publisher, title) 
VALUES (280, 'Vintage', 'The Catcher in the Rye');
INSERT INTO Book (id, author, genre) 
VALUES (LAST_INSERT_ID(), 'J.D. Salinger', 'Classic');

-- Екземпляр 5
INSERT INTO PrintedMaterial (page_count, publisher, title) 
VALUES (350, 'Penguin Books', '1984');
INSERT INTO Book (id, author, genre) 
VALUES (LAST_INSERT_ID(), 'George Orwell', 'Dystopian');

-- Екземпляр 6
INSERT INTO printedmaterial (page_count, publisher, title) 
VALUES (120, 'Nature Publishing Group', 'Scientific Discoveries');

INSERT INTO magazine (id, issueNumber, genre) 
VALUES (LAST_INSERT_ID(), 15, 'Science');

INSERT INTO sciencemagazine (id, scientificField, peerReviewed) 
VALUES (LAST_INSERT_ID(), 'Astronomy', 1);

-- Екземпляр 7
INSERT INTO printedmaterial (page_count, publisher, title) 
VALUES (80, 'Elsevier', 'Physics Today');

INSERT INTO magazine (id, issueNumber, genre) 
VALUES (LAST_INSERT_ID(), 8, 'Physics');

INSERT INTO sciencemagazine (id, scientificField, peerReviewed) 
VALUES (LAST_INSERT_ID(), 'Quantum Mechanics', 1);

-- Екземпляр 8
INSERT INTO printedmaterial (page_count, publisher, title) 
VALUES (200, 'Springer', 'Modern Biology');

INSERT INTO magazine (id, issueNumber, genre) 
VALUES (LAST_INSERT_ID(), 25, 'Biology');

INSERT INTO sciencemagazine (id, scientificField, peerReviewed) 
VALUES (LAST_INSERT_ID(), 'Genetics', 1);

-- Екземпляр 9
INSERT INTO printedmaterial (page_count, publisher, title) 
VALUES (150, 'Oxford University Press', 'Mathematics Monthly');

INSERT INTO magazine (id, issueNumber, genre) 
VALUES (LAST_INSERT_ID(), 12, 'Mathematics');

INSERT INTO sciencemagazine (id, scientificField, peerReviewed) 
VALUES (LAST_INSERT_ID(), 'Algebra', 0);

-- Екземпляр 10
INSERT INTO printedmaterial (page_count, publisher, title) 
VALUES (95, 'Cambridge University Press', 'Chemistry World');

INSERT INTO magazine (id, issueNumber, genre) 
VALUES (LAST_INSERT_ID(), 10, 'Chemistry');

INSERT INTO sciencemagazine (id, scientificField, peerReviewed) 
VALUES (LAST_INSERT_ID(), 'Organic Chemistry', 1);
