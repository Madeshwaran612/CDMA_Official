package com.madesh.scorer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Timer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView timer,p1,p2,p3,p4,p5,p6 ;
    EditText roomCode,competitionName,EditTextAge6;
    Button start, pause, reset, validate;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;



    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    CollectionReference collectionReference;

    int i = 0;

Spinner typeSpin, ageSpin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        validate = findViewById(R.id.validate);

        roomCode=findViewById(R.id.roomCode);
        competitionName = findViewById(R.id.CompetitionName);
        EditTextAge6 = findViewById(R.id.playerAgeCategory);
        p1 = findViewById(R.id.time1);
        p2 = findViewById(R.id.time2);
        p3 = findViewById(R.id.time3);
        p4 = findViewById(R.id.time4);
        p5 = findViewById(R.id.time5);
        p6 = findViewById(R.id.time6);

        timer = (TextView)findViewById(R.id.tvTimer);
        start = (Button)findViewById(R.id.btStart);
        pause = (Button)findViewById(R.id.btPause);
        reset = (Button)findViewById(R.id.btReset);

        handler = new Handler() ;

        typeSpin = findViewById(R.id.mallakhamb_type);
        ageSpin = findViewById(R.id.ageCatBoys);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.mallakhamb_type, android.R.layout.select_dialog_item);
        adapter2.setDropDownViewResource(android.R.layout.select_dialog_item);
        typeSpin.setAdapter(adapter2);
        typeSpin.setOnItemSelectedListener(this);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roomCode.getText().length()==0){
                    roomCode.setError("Enter Room Code");
                    Toast.makeText(Timer.this, "Room Code is Required", Toast.LENGTH_SHORT).show();

                }else if (competitionName.getText().length()==0){
                    competitionName.setError("Enter Competition Name");
                    Toast.makeText(Timer.this, "Competition name is Required", Toast.LENGTH_SHORT).show();

                }else if (EditTextAge6.getText().length()==0){
                    EditTextAge6.setError("Enter Age Category");
                    Toast.makeText(Timer.this, "Age Category is Required", Toast.LENGTH_SHORT).show();

                }else {
                    collectionReference = fStore.collection(competitionName.getText().toString().trim().toUpperCase())
                            .document(typeSpin.getSelectedItem().toString()).collection(EditTextAge6.getText().toString().toUpperCase().trim())
                            .document(roomCode.getText().toString().toUpperCase().trim()).collection(ageSpin.getSelectedItem().toString());
                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()) {
                                start.setVisibility(View.VISIBLE);
                                reset.setVisibility(View.VISIBLE);
                                pause.setVisibility(View.VISIBLE);
                                timer.setVisibility(View.VISIBLE);

                            } else {
                                Toast.makeText(Timer.this, "Check Room Code", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.setVisibility(View.INVISIBLE);
                collectionReference = fStore.collection(competitionName.getText().toString().trim().toUpperCase())
                        .document(typeSpin.getSelectedItem().toString()).collection(EditTextAge6.getText().toString().toUpperCase().trim())
                        .document(roomCode.getText().toString().toUpperCase().trim()).collection(ageSpin.getSelectedItem().toString());
                collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            if (i > 5) {
                                Toast.makeText(Timer.this, "Entry for 6 players finished", Toast.LENGTH_SHORT).show();
                            } else {

                                StartTime = SystemClock.uptimeMillis();
                                handler.postDelayed(runnable, 0);

                                reset.setEnabled(false);

                                i++;

                            }

                        } else {
                            Toast.makeText(Timer.this, "Check Room Code", Toast.LENGTH_SHORT).show();
                            start.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                collectionReference = fStore.collection(competitionName.getText().toString().trim().toUpperCase())
                        .document(typeSpin.getSelectedItem().toString()).collection(EditTextAge6.getText().toString().toUpperCase().trim())
                        .document(roomCode.getText().toString().toUpperCase().trim()).collection(ageSpin.getSelectedItem().toString());

                TimeBuff += MillisecondTime;

                handler.removeCallbacks(runnable);

                reset.setEnabled(true);

                if (i==1){
                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()) {
                                Toast.makeText(Timer.this, "Check Room Code and update it", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    p1.setText(timer.getText().toString());
                    Map<String,String>timining = new HashMap<>();
                    timining.put("Time1",p1.getText().toString());

                    collectionReference.document("Timer").set(timining);
                }
                if (i==2){

                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()) {
                                Toast.makeText(Timer.this, "Check Room Code and update it", Toast.LENGTH_LONG).show();
                            }                        }
                    });
                    p2.setText(timer.getText().toString());
                    Map<String,String>timining = new HashMap<>();
                    timining.put("Time1",p1.getText().toString());
                    timining.put("Time2",p2.getText().toString());

                    collectionReference.document("Timer").set(timining);
                }

                if (i==3){

                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()) {
                                Toast.makeText(Timer.this, "Check Room Code and update it", Toast.LENGTH_LONG).show();
                            }                        }
                    });
                    p3.setText(timer.getText().toString());
                    Map<String,String>timining = new HashMap<>();
                    timining.put("Time1",p1.getText().toString());
                    timining.put("Time2",p2.getText().toString());
                    timining.put("Time3",p3.getText().toString());

                    collectionReference.document("Timer").set(timining);
                }

                if (i==4){

                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()) {
                                Toast.makeText(Timer.this, "Check Room Code and update it", Toast.LENGTH_LONG).show();
                            }                        }
                    });
                    p4.setText(timer.getText().toString());
                    Map<String,String>timining = new HashMap<>();
                    timining.put("Time1",p1.getText().toString());
                    timining.put("Time2",p2.getText().toString());
                    timining.put("Time3",p3.getText().toString());
                    timining.put("Time4",p4.getText().toString());

                    collectionReference.document("Timer").set(timining);
                }

                if (i==5){

                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()) {
                                Toast.makeText(Timer.this, "Check Room Code and update it", Toast.LENGTH_LONG).show();
                            }                        }
                    });
                    p5.setText(timer.getText().toString());
                    Map<String,String>timining = new HashMap<>();
                    timining.put("Time1",p1.getText().toString());
                    timining.put("Time2",p2.getText().toString());
                    timining.put("Time3",p3.getText().toString());
                    timining.put("Time4",p4.getText().toString());
                    timining.put("Time5",p5.getText().toString());

                    collectionReference.document("Timer").set(timining);
                }

                if (i==6){


                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()) {
                                Toast.makeText(Timer.this, "Check Room Code and update it", Toast.LENGTH_LONG).show();
                            }                        }
                    });
                    p6.setText(timer.getText().toString());
                    Map<String,String>timining = new HashMap<>();
                    timining.put("Time1",p1.getText().toString());
                    timining.put("Time2",p2.getText().toString());
                    timining.put("Time3",p3.getText().toString());
                    timining.put("Time4",p4.getText().toString());
                    timining.put("Time5",p5.getText().toString());
                    timining.put("Time6",p6.getText().toString());
                    collectionReference.document("Timer").set(timining);
                }


                start.setVisibility(View.INVISIBLE);


            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                collectionReference = fStore.collection(competitionName.getText().toString().trim().toUpperCase())
                        .document(typeSpin.getSelectedItem().toString()).collection(EditTextAge6.getText().toString().toUpperCase().trim())
                        .document(roomCode.getText().toString().toUpperCase().trim()).collection(ageSpin.getSelectedItem().toString());


                if (i==1){
                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()) {
                                Toast.makeText(Timer.this, "Check Room Code and update it", Toast.LENGTH_LONG).show();
                            }                        }
                    });
                    p1.setText(timer.getText().toString());
                    Map<String,String> timining = new HashMap<>();
                    timining.put("Time1",p1.getText().toString());

                    collectionReference.document("Timer").set(timining);
                }
                if (i==2){
                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()) {
                                Toast.makeText(Timer.this, "Check Room Code and update it", Toast.LENGTH_LONG).show();
                            }                        }
                    });
                    p2.setText(timer.getText().toString());
                    Map<String,String>timining = new HashMap<>();
                    timining.put("Time1",p1.getText().toString());
                    timining.put("Time2",p2.getText().toString());

                    collectionReference.document("Timer").set(timining);
                }

                if (i==3){
                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()) {
                                Toast.makeText(Timer.this, "Check Room Code and update it", Toast.LENGTH_LONG).show();
                            }                        }
                    });
                    p3.setText(timer.getText().toString());
                    Map<String,String>timining = new HashMap<>();
                    timining.put("Time1",p1.getText().toString());
                    timining.put("Time2",p2.getText().toString());
                    timining.put("Time3",p3.getText().toString());


                    collectionReference.document("Timer").set(timining);
                }

                if (i==4){
                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()) {
                                Toast.makeText(Timer.this, "Check Room Code and update it", Toast.LENGTH_LONG).show();
                            }                        }
                    });
                    p4.setText(timer.getText().toString());
                    Map<String,String>timining = new HashMap<>();
                    timining.put("Time1",p1.getText().toString());
                    timining.put("Time2",p2.getText().toString());
                    timining.put("Time3",p3.getText().toString());
                    timining.put("Time4",p4.getText().toString());


                    collectionReference.document("Timer").set(timining);
                }

                if (i==5){
                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()) {
                                Toast.makeText(Timer.this, "Check Room Code and update it", Toast.LENGTH_LONG).show();
                            }                        }
                    });
                    p5.setText(timer.getText().toString());
                    Map<String,String>timining = new HashMap<>();
                    timining.put("Time1",p1.getText().toString());
                    timining.put("Time2",p2.getText().toString());
                    timining.put("Time3",p3.getText().toString());
                    timining.put("Time4",p4.getText().toString());
                    timining.put("Time5",p5.getText().toString());


                    collectionReference.document("Timer").set(timining);
                }

                if (i==6){
                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if(!documentSnapshot.exists()) {
                                Toast.makeText(Timer.this, "Check Room Code and update it", Toast.LENGTH_LONG).show();
                            }                        }
                    });
                    p6.setText(timer.getText().toString());
                    Map<String,String>timining = new HashMap<>();
                    timining.put("Time1",p1.getText().toString());
                    timining.put("Time2",p2.getText().toString());
                    timining.put("Time3",p3.getText().toString());
                    timining.put("Time4",p1.getText().toString());
                    timining.put("Time5",p2.getText().toString());
                    timining.put("Time6",p3.getText().toString());
                    collectionReference.document("Timer").set(timining);
                }

                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;

                timer.setText("00:00:00");
                start.setVisibility(View.VISIBLE);

            }
        });










    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            timer.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.mallakhamb_type) {
            if (parent.getSelectedItemPosition() == 0) {
                Spinner ageSpin = findViewById(R.id.ageCatBoys);
                ageSpin.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Choose the correct Mallakhamb Type", Toast.LENGTH_LONG).show();

            } else if (parent.getSelectedItemPosition() == 1) {
                Spinner ageSpin = findViewById(R.id.ageCatBoys);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.age_cat_boys, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ageSpin.setAdapter(adapter);
                ageSpin.setOnItemSelectedListener(this);


                ageSpin.setVisibility(View.VISIBLE);



            } else if (parent.getSelectedItemPosition() == 2) {
                Spinner ageSpin = findViewById(R.id.ageCatBoys);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.age_cat_girls, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ageSpin.setAdapter(adapter);
                ageSpin.setOnItemSelectedListener(this);


                ageSpin.setVisibility(View.VISIBLE);




            } else if (parent.getSelectedItemPosition() == 3) {
                Spinner ageSpin = findViewById(R.id.ageCatBoys);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hanging_age, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ageSpin.setAdapter(adapter);
                ageSpin.setOnItemSelectedListener(this);


                ageSpin.setVisibility(View.VISIBLE);


            }
        } else if (parent.getId() == R.id.ageCatBoys) {
            if (parent.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "Choose correct Age Group", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder buid = new AlertDialog.Builder(this);
        buid.setMessage("Are you sure want to exit?");
        buid.setCancelable(true);
        buid.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        buid.setCancelable(true);

        buid.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Timer.this,MainActivity.class));
            }
        });
        AlertDialog alertDialog = buid.create();
        alertDialog.show();
    }
}