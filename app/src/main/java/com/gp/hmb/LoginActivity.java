package com.gp.hmb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.gp.hmb.databinding.ActivityLoginBinding;
import com.gp.hmb.model.User;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

    }

    public void openRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        String id = binding.etId.getText().toString();
        String password = binding.etPassword.getText().toString();


        if (id.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "برجاء ملئ كافة البيانات", Toast.LENGTH_SHORT).show();
            return;
        }

        firestore.collection("users").document(id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                User user = task.getResult().toObject(User.class);

                if (user == null) {
                    Toast.makeText(LoginActivity.this, "الرقم القومي غير موحود", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.equals(user.getPassword())) {
                    saveUserData(user);
                    navigateToMyChildrenActivity();
                    runMyNotificationWorker();

                } else {
                    Toast.makeText(LoginActivity.this, "الرمز السري غير صحيح", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void runMyNotificationWorker() {
        Constraints constraints = new Constraints.Builder()
                .setRequiresDeviceIdle(false)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyNotificationWorker.class, 12, TimeUnit.HOURS)
                .setConstraints(constraints)
                .addTag("MY_WORK_MANAGER_PERIODIC_TAG")
                .build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyNotificationWorker.class)
                .setConstraints(constraints)
                .addTag("MY_WORK_MANAGER_TAG_ONE_TIME")
                .build();

        WorkManager.getInstance(getApplicationContext()).enqueue(oneTimeWorkRequest);

        WorkManager.getInstance(getApplicationContext()).enqueue(periodicWorkRequest);
    }

    private void navigateToMyChildrenActivity() {
        Intent intent = new Intent(LoginActivity.this, MyChildrenActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveUserData(User user) {
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        preferences.edit().putString("phone", user.getId()).apply();
    }
}
