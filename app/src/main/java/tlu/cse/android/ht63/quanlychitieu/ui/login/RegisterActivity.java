package tlu.cse.android.ht63.quanlychitieu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tlu.cse.android.ht63.quanlychitieu.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword, edtConfirmPassword;
    private Button btnRegister;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize views
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        // Register button click event
        btnRegister.setOnClickListener(v -> {
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            String confirmPassword = edtConfirmPassword.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                registerUser(email, password);
            }
        });
    }

    private void registerUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registration successful
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                        // Redirect to login screen
                        startActivity(new Intent(RegisterActivity.this, Login.class));
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(RegisterActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
