package com.projet.event_manager.config;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Pattern;

public class SecurityConfig {
    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password,hashedPassword);
    }

    public static boolean checkEMail(String email){
        Pattern email_pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,3}$");
        return email_pattern.matcher(email).matches();
    }
}
