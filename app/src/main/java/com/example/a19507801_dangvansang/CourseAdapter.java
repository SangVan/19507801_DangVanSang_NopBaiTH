package com.example.a19507801_dangvansang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CourseAdapter extends AppCompatActivity {

    private Context context;
    private int Layout;
    private List<Course> todoCourse;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    public CourseAdapter(ActivityCourse activityCourse, int activity_course_item, ArrayList<Course> arrCourse) {
    }

    public void todoCourse(Context context, int layout, List<Course> todoCourse) {
        this.context = context;
        Layout = layout;
        this.todoCourse = todoCourse;
    }


    public int getCount() {
        return todoCourse.size();
    }


    public Object getItem(int i) {
        return null;
    }


    public long getItemId(int i) {
        return 0;
    }


    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(Layout, null);

        TextView txtCourse = view.findViewById(R.id.txtCourse);
        Course item = todoCourse.get(i);
        txtCourse.setText(item.getCourse());



        //Button remove
        ImageButton btnRemove = view.findViewById(R.id.btnXoa);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child(todoCourse.get(i).getId() + "").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("Xoa thanh cong", "");
                            Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("Error !!!", "");
                        }
                    }
                });
            }
        });

        return view;
    }

    public void notifyDataSetChanged() {
    }
}