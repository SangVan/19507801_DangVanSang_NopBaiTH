package com.example.a19507801_dangvansang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityCourse extends AppCompatActivity {

    ListView lvCoure;
    ArrayList<Course> arrCou;
    CourseAdapter adapter;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        initUI();

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editName = findViewById(R.id.editName);
                int length = arrCou.size();

                Course course = new Course(editName.getText().toString(), 0);
                course.setId(length);

                String pathOject = String.valueOf(course.getId());

                myRef.child(pathOject).setValue(course).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ActivityCourse.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                        loadDataFromFirebase();
                    }
                });
            }
        });
    }

    private void initUI() {
        lvCoure = findViewById(R.id.lvMain);
        arrCou = new ArrayList<>();

        loadDataFromFirebase();

        adapter = new CourseAdapter(this, R.layout.activity_course_item, arrCou);
        lvCoure.setAdapter((ListAdapter) adapter);
    }

    private void loadDataFromFirebase () {
        // Load data to arrTodo/ Async
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Course> listCou = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Course co = data.getValue(Course.class);
                    listCou.add(co);
                }
                arrCou.clear();
                arrCou.addAll(listCou);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}