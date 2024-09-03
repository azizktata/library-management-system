package com.example.librarybooks.core.booklending;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class LendedBookId implements Serializable {

        @Column(name = "account_id")
        private Long accountId;

        @Column(name = "book_id")
        private String bookId;

        public LendedBookId(Long accountId, String bookId) {
            this.accountId = accountId;
            this.bookId = bookId;
        }

        public LendedBookId() {
        }

        public Long getAccountId() {
            return accountId;
        }

        public void setAccountId(Long accountId) {
            this.accountId = accountId;
        }

        public String getBookId() {
            return bookId;
        }

        public void setBookId(String bookId) {
            this.bookId = bookId;
        }
}
