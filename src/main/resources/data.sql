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

INSERT INTO books VALUES(default, 'The Shining', 1977, 'Doubleday');                  --1
INSERT INTO books VALUES(default, 'Pet Sematary', 1983, 'Doubleday');                 --2
INSERT INTO books VALUES(default, 'It', 1986, 'Viking');                              --3
INSERT INTO books VALUES(default, 'Misery', 1987, 'Doubleday');                       --4
INSERT INTO books VALUES(default, 'The Green Mile', 1996, 'Signet Books');            --5
INSERT INTO books VALUES(default, '11/22/63', 2011, 'Scribner');                      --6
INSERT INTO books VALUES(default, 'Unfinished Portrait', 1934, 'Doubleday');          --7
INSERT INTO books VALUES(default, 'Murder Is Easy', 1939, 'Dodd, Mead & Co');         --8
INSERT INTO books VALUES(default, 'The Burden', 1956, 'Dell Publishing');             --9
INSERT INTO books VALUES(default, 'Passenger to Frankfurt', 1970, 'Dodd, Mead & Co'); --10
INSERT INTO books VALUES(default, 'Some Book', 1970, 'Some Publisher');               --11

INSERT INTO authors VALUES(default, 'Stephen', 'King', '21.09.1947', 'male');
INSERT INTO authors VALUES(default, 'Agatha', 'Christie', '15.09.1890', 'female');
INSERT INTO authors VALUES(default, 'First', 'Author', '29.02.1964', 'undefined');
INSERT INTO authors VALUES(default, 'Second', 'Author', '09.05.1945', 'undefined');
INSERT INTO authors VALUES(default, 'Third', 'Author', '11.09.2001', 'undefined');

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
INSERT INTO book_author VALUES(11, 5);