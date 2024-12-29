package tlu.cse.android.ht63.quanlychitieu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import tlu.cse.android.ht63.quanlychitieu.MainActivity;
import tlu.cse.android.ht63.quanlychitieu.R;

public class Login extends AppCompatActivity {

    private EditText edtEmail, edtPassword;
    private Button btnLogin;
    private TextView btnRegister, forgotPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize views
        edtEmail = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.register);
        forgotPassword = findViewById(R.id.forgotPassword);

        // Login button click event
        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(Login.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(email, password);
            }
        });

        // Redirect to Register screen
        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(Login.this, RegisterActivity.class));
        });

        // Forgot Password click event
        forgotPassword.setOnClickListener(v -> {
            Toast.makeText(Login.this, "Forgot Password Clicked", Toast.LENGTH_SHORT).show();
            // Implement your Forgot Password logic here
        });
    }

    private void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Login successful
                        Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();
                    } else {
                        // If login fails, display a message to the user.
                        Toast.makeText(Login.this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
