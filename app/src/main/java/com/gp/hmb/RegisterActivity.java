package com.gp.hmb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.gp.hmb.databinding.ActivityRegisterBinding;
import com.gp.hmb.model.User;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

    }

    public void register(View view) {
        String motherName = binding.etMotherName.getText().toString();
        String id = binding.etId.getText().toString();
        String password = binding.etPassword.getText().toString();
        String address = binding.erAddress.getText().toString();
        String phoneNumber = binding.etPhoneNumber.getText().toString();
        String nationality = binding.etNationality.getText().toString();

        if (motherName.isEmpty() || id.isEmpty() || password.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() || nationality.isEmpty()) {
            Toast.makeText(this, "برجاء ملئ كافة البيانات", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(motherName, id, password, address, phoneNumber, nationality);

        firestore.collection("users").document(id).set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                            saveUserData(user);
                            navigateToMyChildren();

                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveUserData(User user){
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        preferences.edit().putString("phone", user.getPhoneNumber()).apply();
    }

    private void navigateToMyChildren() {
        Intent intent = new Intent(this, MyChildrenActivity.class);
        startActivity(intent);
    }

}