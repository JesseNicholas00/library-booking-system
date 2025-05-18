CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    isbn VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    borrower_id BIGINT,
    CONSTRAINT fk_borrower FOREIGN KEY (borrower_id) REFERENCES borrowers(id)
);

CREATE INDEX idx_books_isbn ON books(isbn);
CREATE INDEX idx_books_borrower_id ON books(borrower_id);