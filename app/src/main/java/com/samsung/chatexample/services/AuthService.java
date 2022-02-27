package com.samsung.chatexample.services;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthService {
    public static Task<AuthResult> createAccount(String email, String name, String password) {
        return FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password);
    }

    public static Task<AuthResult> logIn(String email, String password) {
        return FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password);
    }

    public static void logOut() {
        FirebaseAuth.getInstance().signOut();
    }

    public static boolean logInSilently() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }
}
