package com.example.bookmanagement_njs1701_se172266;

import android.content.Context;
import android.content.Intent;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Menu {

    public static void onCreateOptionsMenu(Context context, android.view.Menu menu) {
        MenuInflater inflater = new MenuInflater(context);
        inflater.inflate(R.menu.menu, menu);
    }

    public static boolean onOptionsItemSelected(Context context, @NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.author:
                Intent authorIntent = new Intent(context, AuthorActivity.class);
                context.startActivity(authorIntent);
                return true;
            case R.id.book:
                Intent bookIntent = new Intent(context, MainActivity.class);
                context.startActivity(bookIntent);
                return true;
            case R.id.logout:
                logout(context);
                return true;
            default:
                return false;
        }
    }

    private static void logout(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        GoogleSignInClient gsc = GoogleSignIn.getClient(context, gso);

        gsc.signOut().addOnCompleteListener(task -> {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        });
    }
}
