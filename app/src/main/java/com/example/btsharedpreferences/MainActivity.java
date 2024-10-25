package com.example.btsharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnXacNhan;
    EditText edtUsername, edtPassword;
    CheckBox chkRemember;
    RatingBar ratingBar;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        AnhXa();
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        // lấy giá trị
        edtUsername.setText(sharedPreferences.getString("hoten", ""));
        edtPassword.setText(sharedPreferences.getString("maSV",""));
        chkRemember.setChecked(sharedPreferences.getBoolean("checked", false));
        ratingBar.setRating(sharedPreferences.getFloat("rating", 0));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                float rating = ratingBar.getRating();

                if (username.equals("LeNguyenThanhLinh") && password.equals("22115053122121")) {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    // nếu có check
                    if (chkRemember.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("hoten", username);
                        editor.putString("maSV", password);
                        editor.putBoolean("checked", true);
                        editor.putFloat("rating", rating);
                        editor.commit();
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("hoten");
                        editor.remove("maSV");
                        editor.remove("checked");
                        editor.remove("rating");
                        editor.commit();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void AnhXa() {
        btnXacNhan = findViewById(R.id.buttonXacNhac);
        edtUsername = findViewById(R.id.editTextUsername);
        edtPassword = findViewById(R.id.editTextPassword);
        chkRemember = findViewById(R.id.checkBoxRemember);
        ratingBar = findViewById(R.id.ratingBar);

    }
}