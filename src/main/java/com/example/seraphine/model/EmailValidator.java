package com.example.seraphine.model;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

/**
 * Validate email using regular expression.
 * @author Loc Bui Nhien
 */
@Service
public class EmailValidator implements Predicate<String>
{
    @Override
    public boolean test(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}