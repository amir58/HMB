package com.gp.hmb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.gp.hmb.adapter.childrenAdapter;
import com.gp.hmb.databinding.ActivityMyChildrenBinding;
import com.gp.hmb.model.Child;

import java.util.ArrayList;
import java.util.List;

public class MyChildrenActivity extends AppCompatActivity {
    ActivityMyChildrenBinding binding;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_children);

        getChildren();
    }

    private String getUserId() {
        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
        return preferences.getString("phone", "");
    }


    private void getChildren() {
        firestore.collection("users").document(getUserId()).collection("children")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        List<Child> children = new ArrayList<>();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {
                            Child child = snapshot.toObject(Child.class);
                            children.add(child);
                        }

                        binding.childrenRv.setAdapter(new childrenAdapter(children));
                    }
                });
    }


    public void openAddChildren(View view) {
        Intent intent = new Intent(this, AddChildrenActivity.class);
        startActivity(intent);

    }


}