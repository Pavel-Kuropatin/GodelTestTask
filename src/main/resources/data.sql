DROP TABLE IF EXISTS books, authors, book_author;

CREATE TABLE books(
    id SERIAL PRIMARY KEY UNIQUE,
    book_name varchar(255) NOT NULL UNIQUE,
    year_of_publication integer NOT NULL,
    publisher varchar(255) NOT NULL
);

CREATE TABLE authors(
    id SERIAL PRIMARY KEY UNIQUE,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    birth_date varchar(10),
    sex varchar(9) NOT NULL
);

CREATE TABLE book_author(
    book_id int NOT NULL,
    author_id int NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books (id) ON DELETE CASCADE,
    FOREIGN KEY (author_id) REFERENCES authors (id)
    );

INSERT INTO books VALUES(default, 'The Shining', 1977, 'Doubleday');
INSERT INTO books VALUES(default, 'Pet Sematary', 1983, 'Doubleday');
INSERT INTO books VALUES(default, 'It', 1986, 'Viking');
INSERT INTO books VALUES(default, 'Misery', 1987, 'Doubleday');
INSERT INTO books VALUES(default, 'The Green Mile', 1996, 'Signet Books');
INSERT INTO books VALUES(default, '11/22/63', 2011, 'Scribner');
INSERT INTO books VALUES(default, 'Unfinished Portrait', 1934, 'Doubleday');
INSERT INTO books VALUES(default, 'Murder Is Easy', 1939, 'Dodd, Mead & Co');
INSERT INTO books VALUES(default, 'The Burden', 1956, 'Dell Publishing');
INSERT INTO books VALUES(default, 'Passenger to Frankfurt', 1970, 'Dodd, Mead & Co');
INSERT INTO books VALUES(default, 'Head First HTML5 Programming', 2011, 'OReilly Media, Inc.');
INSERT INTO books VALUES(default, 'Head First HTML and CSS, 2nd Edition', 2012, 'OReilly Media, Inc.');
INSERT INTO books VALUES(default, 'Head First Java, 2nd Edition', 2005, 'OReilly Media, Inc.');
INSERT INTO books VALUES(default, 'Head First Design Patterns', 2015, 'OReilly Media, Inc.');
INSERT INTO books VALUES(default, 'Clean Code : A Handbook of Agile Software Craftsmanship', 2019, 'Pearson Education');

INSERT INTO authors VALUES(default, 'Stephen', 'King', '21.09.1947', 'male');
INSERT INTO authors VALUES(default, 'Agatha', 'Christie', '15.09.1890', 'female');
INSERT INTO authors VALUES(default, 'Eric', 'Freeman', '01.01.1970', 'male');
INSERT INTO authors VALUES(default, 'Elisabeth', 'Robson', '01.01.1970', 'female');
INSERT INTO authors VALUES(default, 'Robert', 'Martin', '05.11.1952', 'male');

INSERT INTO book_author VALUES(1, 1);
INSERT INTO book_author VALUES(2, 1);
INSERT INTO book_author VALUES(3, 1);
INSERT INTO book_author VALUES(4, 1);
INSERT INTO book_author VALUES(5, 1);
INSERT INTO book_author VALUES(6, 1);
INSERT INTO book_author VALUES(7, 2);
INSERT INTO book_author VALUES(8, 2);
INSERT INTO book_author VALUES(9, 2);
INSERT INTO book_author VALUES(10, 2);
INSERT INTO book_author VALUES(11, 3);
INSERT INTO book_author VALUES(11, 4);
INSERT INTO book_author VALUES(12, 3);
INSERT INTO book_author VALUES(12, 4);
INSERT INTO book_author VALUES(13, 3);
INSERT INTO book_author VALUES(13, 4);
INSERT INTO book_author VALUES(14, 3);
INSERT INTO book_author VALUES(14, 4);
INSERT INTO book_author VALUES(15, 5);