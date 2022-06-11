package com.johnny.transactiondemo.util;

import com.johnny.transactiondemo.model.Author;

public class Helper {
    public static String getAuthorFullName(Author author) {
        return author.getFirstName() + " " + author.getLastName();
    }
}
