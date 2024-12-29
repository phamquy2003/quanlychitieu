package tlu.cse.android.ht63.quanlychitieu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import tlu.cse.android.ht63.quanlychitieu.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText edtEmail;
    private Button btnSendEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Khởi tạo FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        edtEmail = findViewById(R.id.edtEmail);
        btnSendEmail = findViewById(R.id.btnSendEmail);

        // Đăng ký sự kiện khi người dùng nhấn nút "Send Reset Link"
        btnSendEmail.setOnClickListener(v -> {
            String email = edtEmail.getText().toString();

            if (email.isEmpty()) {
                Toast.makeText(ForgotPasswordActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
            } else {
                // Gửi yêu cầu reset mật khẩu qua Firebase
                sendPasswordResetEmail(email);
            }
        });
    }

    // Hàm gửi email để đặt lại mật khẩu qua Firebase
    private void sendPasswordResetEmail(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPasswordActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                        // Chuyển về màn hình đăng nhập sau khi gửi email thành công
                        startActivity(new Intent(ForgotPasswordActivity.this, Login.class));
                    } else {
                        Toast.makeText(ForgotPasswordActivity.this, "Failed to send reset email", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Hàm quay lại màn hình đăng nhập
    public void onBackToLoginClicked(View view) {
        startActivity(new Intent(ForgotPasswordActivity.this, Login.class));
    }
}
