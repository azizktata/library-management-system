package com.example.librarybooks.core.author.web;

import javax.validation.constraints.NotEmpty;

public class AuthorBaseReq {
    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
