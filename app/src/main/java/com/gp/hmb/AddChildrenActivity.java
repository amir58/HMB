package com.gp.hmb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.gp.hmb.databinding.ActivityAddChildrenBinding;
import com.gp.hmb.model.Child;
import com.gp.hmb.model.User;

import java.util.Calendar;

public class AddChildrenActivity extends AppCompatActivity {
    ActivityAddChildrenBinding binding;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    String[] types = {"ذكر", "انثي"};
    String[] bloods = {"A-", "A+ ", "B-", "B+", "AB-", "AB+", "O-", "O+"};

    String kind = "";
    String bloodType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_children);

        ArrayAdapter<String> adapterTypes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, types);
        binding.etKind.setAdapter(adapterTypes);
        binding.etKind.setOnItemClickListener((parent, view, position, id) -> {
            kind = types[position];
        });

        ArrayAdapter<String> adapterBlood = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bloods);
        binding.etBloodType.setAdapter(adapterBlood);
        binding.etBloodType.setOnItemClickListener((parent, view, position, id) -> {
            bloodType = bloods[position];
        });

        binding.etDateOfBirth.setFocusable(false);

        binding.etDateOfBirth.setOnClickListener(v -> showDatePicker());

    }

    private void showDatePicker() {

        final Calendar newCalendar = Calendar.getInstance();
        final DatePickerDialog startTime = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                binding.etDateOfBirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        startTime.show();
    }

    private String getUserId() {
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        return preferences.getString("phone", "");
    }

    public void addChild(View view) {
        String childName = binding.etChildName.getText().toString().trim();
        String fatherName = binding.etFatherName.getText().toString().trim();
        String dateOfBirth = binding.etDateOfBirth.getText().toString().trim();
        String tall = binding.etTall.getText().toString().trim();
        String weight = binding.etWeight.getText().toString().trim();

        if (childName.isEmpty() || fatherName.isEmpty() || dateOfBirth.isEmpty() || kind.isEmpty() || bloodType.isEmpty() || tall.isEmpty() || weight.isEmpty()) {
            Toast.makeText(this, "برجاء ملئ كافة البيانات", Toast.LENGTH_SHORT).show();
            return;
        }

        Child child = new Child(childName, fatherName, dateOfBirth, kind, bloodType, tall, weight);

        firestore.collection("users").document(getUserId()).collection("children").document().set(child)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddChildrenActivity.this, "تم اضافة الطفل", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            String errorMessage = task.getException().getLocalizedMessage();
                            Toast.makeText(AddChildrenActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}