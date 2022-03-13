package com.samsung.chatexample.services;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.samsung.chatexample.listeners.MyValueEventListener;
import com.samsung.chatexample.models.domain.UserD;

import java.util.Date;

public class AuthService {
    public static Task<AuthResult> createAccount(
            String email,
            String name,
            String password,
            MyValueEventListener<String> listener
    ) {
        return FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        if (authResult.getUser() != null) {
                            UserD user = new UserD(name, email, new Date().getTime());
                            listener.onValue(DatabaseService.addUser(user));
                        } else {
                            listener.onException(new Exception("No auth user"));
                        }
                    }
                });
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
