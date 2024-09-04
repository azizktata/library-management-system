package com.example.librarybooks.core.account;

import com.example.librarybooks.core.librarian.Librarian;
import com.example.librarybooks.core.member.Member;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Long accountId;
    private String password;
    private String name;
    private String email;
    private String phone;

    public Account() {
    }

    @Transient
    public String getDiscriminatorValue() {
        if (this instanceof Member) {
            return "Member";
        } else if (this instanceof Librarian) {
            return "Librarian";
        }
        return "Unknown";
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

}
