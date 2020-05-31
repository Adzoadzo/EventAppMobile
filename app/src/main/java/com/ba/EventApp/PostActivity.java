package com.ba.EventApp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.Calendar;
import java.util.HashMap;

public class PostActivity extends AppCompatActivity {

    StorageReference storageRef;
    ImageView close;
    TextView post;
    EditText description,time,people,mDisplayDate, enterLocation;
    Spinner typeSpinner;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        close = findViewById(R.id.close);
        enterLocation = findViewById(R.id.enterLocation);
        post = findViewById(R.id.post);
        description = findViewById(R.id.description);
        people = findViewById(R.id.people);
        time = findViewById(R.id.time);
        typeSpinner = findViewById(R.id.spinerType);
        mDisplayDate = findViewById(R.id.pickDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        PostActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        storageRef = FirebaseStorage.getInstance().getReference("posts");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostActivity.this, MainActivity.class));
                finish();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadPost();
            }
        });
    }

        private void uploadPost(){
            final ProgressDialog pd = new ProgressDialog(this);
            pd.setMessage("Posting");
            pd.show();

            String eventdesc = description.getText().toString();
            String eventtype = typeSpinner.getSelectedItem().toString();
            String eventpeople = people.getText().toString();
            String eventtime = time.getText().toString();
            String eventdate = mDisplayDate.getText().toString();
            String eventlocaion = enterLocation.getText().toString();


            if (!TextUtils.isEmpty(eventdesc) || !TextUtils.isEmpty(eventtype) || !TextUtils.isEmpty(eventpeople)
                    || !TextUtils.isEmpty(eventtime) || !TextUtils.isEmpty(eventdate) || !TextUtils.isEmpty(eventlocaion)) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");

                String postid = reference.push().getKey();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("postid", postid);
                hashMap.put("description", eventdesc);
                hashMap.put("time", eventtime);
                hashMap.put("people", eventpeople);
                hashMap.put("type", eventtype);
                hashMap.put("date", eventdate);
                hashMap.put("location", eventlocaion);
                hashMap.put("publisher", FirebaseAuth.getInstance().getCurrentUser().getUid());

                reference.child(postid).setValue(hashMap);

                pd.dismiss();

                startActivity(new Intent(PostActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "No fields can be empty!", Toast.LENGTH_SHORT).show();
            }


        }
}
