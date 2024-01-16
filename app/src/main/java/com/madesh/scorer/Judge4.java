package com.madesh.scorer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Judge4 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    CollectionReference collectionReference;
    DocumentReference documentReference;

    Spinner typeSpin;

    EditText compName,date,RoomCode,
            playerName,
            playerName2,
            playerName3,
            playerName4,
            playerName5,
            playerName6,
            playerNameh1,
            playerNameh2,
            playerNameh3,
            playerNameh4,
            playerNameh5,
            playerNameh6,
            playerNamer1,
            playerNamer2,
            playerNamer3,
            playerNamer4,
            playerNamer5,
            playerNamer6,
            teamName;
    //POLE
    CheckBox originality,originality2,originality3,originality4,originality5,originality6,
            originalityFinal, originalityFinal2,originalityFinal3,originalityFinal4,originalityFinal5,originalityFinal6;
    TableLayout Pole, Pole2, Pole3, Pole4, Pole5, Pole6;
    Button Submit, Submit2, Submit3, Submit4, Submit5, Submit6, exit,
            Confirm, Confirm2, Confirm3, Confirm4, Confirm5, Confirm6,
            next, next2, next3, next4, next5, PolePanel;
    EditText difficulty, execution,  difficultyFinal, executionFinal,
            difficulty2, execution2,  difficultyFinal2, executionFinal2,
            difficulty3, execution3,  difficultyFinal3, executionFinal3,
            difficulty4, execution4,  difficultyFinal4, executionFinal4,
            difficulty5, execution5,  difficultyFinal5, executionFinal5,
            difficulty6, execution6,  difficultyFinal6, executionFinal6;
    RadioGroup FP1, FP2, FP3, FP4, FP5, FP6, FP7, FP8, FP9, FP10, BD1, BD2, BD3, BD4, BD5, BD6, BD7, BD8, BD9, BD10,
            FP12, FP22, FP32, FP42, FP52, FP62, FP72, FP82, FP92, FP102, BD12, BD22, BD32, BD42, BD52, BD62, BD72, BD82, BD92, BD102,
            FP13, FP23, FP33, FP43, FP53, FP63, FP73, FP83, FP93, FP103, BD13, BD23, BD33, BD43, BD53, BD63, BD73, BD83, BD93, BD103,
            FP14, FP24, FP34, FP44, FP54, FP64, FP74, FP84, FP94, FP104, BD14, BD24, BD34, BD44, BD54, BD64, BD74, BD84, BD94, BD104,
            FP15, FP25, FP35, FP45, FP55, FP65, FP75, FP85, FP95, FP105, BD15, BD25, BD35, BD45, BD55, BD65, BD75, BD85, BD95, BD105,
            FP16, FP26, FP36, FP46, FP56, FP66, FP76, FP86, FP96, FP106, BD16, BD26, BD36, BD46, BD56, BD66, BD76, BD86, BD96, BD106;
    RadioButton BY1, BY2, BY3, BY4, BY5, BY6, BY7, BY8, BY9, BY10, BN1, BN2, BN3, BN4, BN5, BN6, BN7, BN8, BN9, BN10, FY1, FY2, FY3, FY4, FY5, FY6, FY7, FY8, FY9, FY10, FN1, FN2, FN3, FN4, FN5, FN6, FN7, FN8, FN9, FN10,
            BY12, BY22, BY32, BY42, BY52, BY62, BY72, BY82, BY92, BY102, BN12, BN22, BN32, BN42, BN52, BN62, BN72, BN82, BN92, BN102, FY12, FY22, FY32, FY42, FY52, FY62, FY72, FY82, FY92, FY102, FN12, FN22, FN32, FN42, FN52, FN62, FN72, FN82, FN92, FN102,
            BY13, BY23, BY33, BY43, BY53, BY63, BY73, BY83, BY93, BY103, BN13, BN23, BN33, BN43, BN53, BN63, BN73, BN83, BN93, BN103, FY13, FY23, FY33, FY43, FY53, FY63, FY73, FY83, FY93, FY103, FN13, FN23, FN33, FN43, FN53, FN63, FN73, FN83, FN93, FN103,
            BY14, BY24, BY34, BY44, BY54, BY64, BY74, BY84, BY94, BY104, BN14, BN24, BN34, BN44, BN54, BN64, BN74, BN84, BN94, BN104, FY14, FY24, FY34, FY44, FY54, FY64, FY74, FY84, FY94, FY104, FN14, FN24, FN34, FN44, FN54, FN64, FN74, FN84, FN94, FN104,
            BY15, BY25, BY35, BY45, BY55, BY65, BY75, BY85, BY95, BY105, BN15, BN25, BN35, BN45, BN55, BN65, BN75, BN85, BN95, BN105, FY15, FY25, FY35, FY45, FY55, FY65, FY75, FY85, FY95, FY105, FN15, FN25, FN35, FN45, FN55, FN65, FN75, FN85, FN95, FN105,
            BY16, BY26, BY36, BY46, BY56, BY66, BY76, BY86, BY96, BY106, BN16, BN26, BN36, BN46, BN56, BN66, BN76, BN86, BN96, BN106, FY16, FY26, FY36, FY46, FY56, FY66, FY76, FY86, FY96, FY106, FN16, FN26, FN36, FN46, FN56, FN66, FN76, FN86, FN96, FN106;
    TextView BDComb, BDComb2, BDComb3, BDComb4, BDComb5, BDComb6,
            FPComb, FPComb2, FPComb3, FPComb4, FPComb5, FPComb6,
            BDTotal, BDTotal2, BDTotal3, BDTotal4, BDTotal5, BDTotal6,
            FPTotal, FPTotal2, FPTotal3, FPTotal4, FPTotal5, FPTotal6;


    //    Hanging
    CheckBox originalityh,originalityh2,originalityh3,originalityh4,originalityh5,originalityh6,originalityFinalh,originalityFinalh2,
            originalityFinalh3,originalityFinalh4,originalityFinalh5,originalityFinalh6;
    TableLayout Hanging, Hanging2, Hanging3, Hanging4, Hanging5, Hanging6;
    Button Submith, Submith2, Submith3, Submith4, Submith5, Submith6,
            Confirmh, Confirmh2, Confirmh3, Confirmh4, Confirmh5, Confirmh6,
            nexth, nexth2, nexth3, nexth4, nexth5,HangingPanel;
    EditText difficultyh, executionh,  difficultyFinalh, executionFinalh,
            difficultyh2, executionh2,  difficultyFinalh2, executionFinalh2,
            difficultyh3, executionh3,  difficultyFinalh3, executionFinalh3,
            difficultyh4, executionh4,  difficultyFinalh4, executionFinalh4,
            difficultyh5, executionh5,  difficultyFinalh5, executionFinalh5,
            difficultyh6, executionh6,  difficultyFinalh6, executionFinalh6;
    RadioGroup FP1h, FP2h, FP3h, FP4h, FP5h, FP6h, FP7h, FP8h, FP9h, FP10h, BD1h, BD2h, BD3h, BD4h, BD5h, BD6h, BD7h, BD8h, BD9h, BD10h,
            FP1h2, FP2h2, FP3h2, FP4h2, FP5h2, FP6h2, FP7h2, FP8h2, FP9h2, FP10h2, BD1h2, BD2h2, BD3h2, BD4h2, BD5h2, BD6h2, BD7h2, BD8h2, BD9h2, BD10h2,
            FP1h3, FP2h3, FP3h3, FP4h3, FP5h3, FP6h3, FP7h3, FP8h3, FP9h3, FP10h3, BD1h3, BD2h3, BD3h3, BD4h3, BD5h3, BD6h3, BD7h3, BD8h3, BD9h3, BD10h3,
            FP1h4, FP2h4, FP3h4, FP4h4, FP5h4, FP6h4, FP7h4, FP8h4, FP9h4, FP10h4, BD1h4, BD2h4, BD3h4, BD4h4, BD5h4, BD6h4, BD7h4, BD8h4, BD9h4, BD10h4,
            FP1h5, FP2h5, FP3h5, FP4h5, FP5h5, FP6h5, FP7h5, FP8h5, FP9h5, FP10h5, BD1h5, BD2h5, BD3h5, BD4h5, BD5h5, BD6h5, BD7h5, BD8h5, BD9h5, BD10h5,
            FP1h6, FP2h6, FP3h6, FP4h6, FP5h6, FP6h6, FP7h6, FP8h6, FP9h6, FP10h6, BD1h6, BD2h6, BD3h6, BD4h6, BD5h6, BD6h6, BD7h6, BD8h6, BD9h6, BD10h6;
    RadioButton BY1h, BY2h, BY3h, BY4h, BY5h, BY6h, BY7h, BY8h, BY9h, BY10h, BN1h, BN2h, BN3h, BN4h, BN5h, BN6h, BN7h, BN8h, BN9h, BN10h, FY1h, FY2h, FY3h, FY4h, FY5h, FY6h, FY7h, FY8h, FY9h, FY10h, FN1h, FN2h, FN3h, FN4h, FN5h, FN6h, FN7h, FN8h, FN9h, FN10h;
    RadioButton BY1h2, BY2h2, BY3h2, BY4h2, BY5h2, BY6h2, BY7h2, BY8h2, BY9h2, BY10h2, BN1h2, BN2h2, BN3h2, BN4h2, BN5h2, BN6h2, BN7h2, BN8h2, BN9h2, BN10h2, FY1h2, FY2h2, FY3h2, FY4h2, FY5h2, FY6h2, FY7h2, FY8h2, FY9h2, FY10h2, FN1h2, FN2h2, FN3h2, FN4h2, FN5h2, FN6h2, FN7h2, FN8h2, FN9h2, FN10h2;
    RadioButton BY1h3, BY2h3, BY3h3, BY4h3, BY5h3, BY6h3, BY7h3, BY8h3, BY9h3, BY10h3, BN1h3, BN2h3, BN3h3, BN4h3, BN5h3, BN6h3, BN7h3, BN8h3, BN9h3, BN10h3, FY1h3, FY2h3, FY3h3, FY4h3, FY5h3, FY6h3, FY7h3, FY8h3, FY9h3, FY10h3, FN1h3, FN2h3, FN3h3, FN4h3, FN5h3, FN6h3, FN7h3, FN8h3, FN9h3, FN10h3;
    RadioButton BY1h4, BY2h4, BY3h4, BY4h4, BY5h4, BY6h4, BY7h4, BY8h4, BY9h4, BY10h4, BN1h4, BN2h4, BN3h4, BN4h4, BN5h4, BN6h4, BN7h4, BN8h4, BN9h4, BN10h4, FY1h4, FY2h4, FY3h4, FY4h4, FY5h4, FY6h4, FY7h4, FY8h4, FY9h4, FY10h4, FN1h4, FN2h4, FN3h4, FN4h4, FN5h4, FN6h4, FN7h4, FN8h4, FN9h4, FN10h4;
    RadioButton BY1h5, BY2h5, BY3h5, BY4h5, BY5h5, BY6h5, BY7h5, BY8h5, BY9h5, BY10h5, BN1h5, BN2h5, BN3h5, BN4h5, BN5h5, BN6h5, BN7h5, BN8h5, BN9h5, BN10h5, FY1h5, FY2h5, FY3h5, FY4h5, FY5h5, FY6h5, FY7h5, FY8h5, FY9h5, FY10h5, FN1h5, FN2h5, FN3h5, FN4h5, FN5h5, FN6h5, FN7h5, FN8h5, FN9h5, FN10h5;
    RadioButton BY1h6, BY2h6, BY3h6, BY4h6, BY5h6, BY6h6, BY7h6, BY8h6, BY9h6, BY10h6, BN1h6, BN2h6, BN3h6, BN4h6, BN5h6, BN6h6, BN7h6, BN8h6, BN9h6, BN10h6, FY1h6, FY2h6, FY3h6, FY4h6, FY5h6, FY6h6, FY7h6, FY8h6, FY9h6, FY10h6, FN1h6, FN2h6, FN3h6, FN4h6, FN5h6, FN6h6, FN7h6, FN8h6, FN9h6, FN10h6;
    TextView BDCombh,BDCombh2,BDCombh3,BDCombh4,BDCombh5,BDCombh6,
            FPCombh,FPCombh2,FPCombh3,FPCombh4,FPCombh5,FPCombh6,
            BDTotalh,BDTotalh2,BDTotalh3,BDTotalh4,BDTotalh5,BDTotalh6,
            FPTotalh,FPTotalh2,FPTotalh3,FPTotalh4,FPTotalh5,FPTotalh6;


    //    Rope
    TableLayout Rope, Rope2, Rope3, Rope4, Rope5, Rope6;
    CheckBox originalityr, originalityr2, originalityr3, originalityr4, originalityr5, originalityr6, originalityFinalr, originalityFinalr2, originalityFinalr3, originalityFinalr4, originalityFinalr5, originalityFinalr6;

    Button Submitr,Submitr2,Submitr3,Submitr4,Submitr5,Submitr6,
            Confirmr,Confirmr2,Confirmr3,Confirmr4,Confirmr5,Confirmr6,
            nextr,nextr2,nextr3,nextr4,nextr5,RopePanel;
    EditText difficultyr, executionr,  difficultyFinalr, executionFinalr;
    EditText difficultyr2, executionr2,  difficultyFinalr2, executionFinalr2;
    EditText difficultyr3, executionr3,  difficultyFinalr3, executionFinalr3;
    EditText difficultyr4, executionr4,  difficultyFinalr4, executionFinalr4;
    EditText difficultyr5, executionr5,  difficultyFinalr5, executionFinalr5;
    EditText difficultyr6, executionr6,  difficultyFinalr6, executionFinalr6;
    RadioGroup FP1r, FP2r, FP3r, FP4r, FP5r, FP6r, FP7r, BD1r, BD2r, BD3r, BD4r, BD5r, BD6r, BD7r;
    RadioGroup FP1r2, FP2r2, FP3r2, FP4r2, FP5r2, FP6r2, FP7r2, BD1r2, BD2r2, BD3r2, BD4r2, BD5r2, BD6r2, BD7r2;
    RadioGroup FP1r3, FP2r3, FP3r3, FP4r3, FP5r3, FP6r3, FP7r3, BD1r3, BD2r3, BD3r3, BD4r3, BD5r3, BD6r3, BD7r3;
    RadioGroup FP1r4, FP2r4, FP3r4, FP4r4, FP5r4, FP6r4, FP7r4, BD1r4, BD2r4, BD3r4, BD4r4, BD5r4, BD6r4, BD7r4;
    RadioGroup FP1r5, FP2r5, FP3r5, FP4r5, FP5r5, FP6r5, FP7r5, BD1r5, BD2r5, BD3r5, BD4r5, BD5r5, BD6r5, BD7r5;
    RadioGroup FP1r6, FP2r6, FP3r6, FP4r6, FP5r6, FP6r6, FP7r6, BD1r6, BD2r6, BD3r6, BD4r6, BD5r6, BD6r6, BD7r6;
    RadioButton BY1r, BY2r, BY3r, BY4r, BY5r, BY6r, BY7r, BN1r, BN2r, BN3r, BN4r, BN5r, BN6r, BN7r, FY1r, FY2r, FY3r, FY4r, FY5r, FY6r, FY7r, FN1r, FN2r, FN3r, FN4r, FN5r, FN6r, FN7r;
    RadioButton BY1r2, BY2r2, BY3r2, BY4r2, BY5r2, BY6r2, BY7r2, BN1r2, BN2r2, BN3r2, BN4r2, BN5r2, BN6r2, BN7r2, FY1r2, FY2r2, FY3r2, FY4r2, FY5r2, FY6r2, FY7r2, FN1r2, FN2r2, FN3r2, FN4r2, FN5r2, FN6r2, FN7r2;
    RadioButton BY1r3, BY2r3, BY3r3, BY4r3, BY5r3, BY6r3, BY7r3, BN1r3, BN2r3, BN3r3, BN4r3, BN5r3, BN6r3, BN7r3, FY1r3, FY2r3, FY3r3, FY4r3, FY5r3, FY6r3, FY7r3, FN1r3, FN2r3, FN3r3, FN4r3, FN5r3, FN6r3, FN7r3;
    RadioButton BY1r4, BY2r4, BY3r4, BY4r4, BY5r4, BY6r4, BY7r4, BN1r4, BN2r4, BN3r4, BN4r4, BN5r4, BN6r4, BN7r4, FY1r4, FY2r4, FY3r4, FY4r4, FY5r4, FY6r4, FY7r4, FN1r4, FN2r4, FN3r4, FN4r4, FN5r4, FN6r4, FN7r4;
    RadioButton BY1r5, BY2r5, BY3r5, BY4r5, BY5r5, BY6r5, BY7r5, BN1r5, BN2r5, BN3r5, BN4r5, BN5r5, BN6r5, BN7r5, FY1r5, FY2r5, FY3r5, FY4r5, FY5r5, FY6r5, FY7r5, FN1r5, FN2r5, FN3r5, FN4r5, FN5r5, FN6r5, FN7r5;
    RadioButton BY1r6, BY2r6, BY3r6, BY4r6, BY5r6, BY6r6, BY7r6, BN1r6, BN2r6, BN3r6, BN4r6, BN5r6, BN6r6, BN7r6, FY1r6, FY2r6, FY3r6, FY4r6, FY5r6, FY6r6, FY7r6, FN1r6, FN2r6, FN3r6, FN4r6, FN5r6, FN6r6, FN7r6;
    TextView BDCombr,BDCombr2,BDCombr3,BDCombr4,BDCombr5,BDCombr6,
            FPCombr,FPCombr2,FPCombr3,FPCombr4,FPCombr5,FPCombr6,
            BDTotalr,BDTotalr2,BDTotalr3,BDTotalr4,BDTotalr5,BDTotalr6,
            FPTotalr,FPTotalr2,FPTotalr3,FPTotalr4,FPTotalr5,FPTotalr6;

    Spinner ageSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.judge_4);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        RoomCode = findViewById(R.id.roomCode);
        compName = findViewById(R.id.CompName);
        date = findViewById(R.id.Date);
        playerName = findViewById(R.id.PlayerName);playerName2 = findViewById(R.id.PlayerName2);playerName3 = findViewById(R.id.PlayerName3);playerName4 = findViewById(R.id.PlayerName4);playerName5 = findViewById(R.id.PlayerName5);
        playerName6 = findViewById(R.id.PlayerName6);playerNameh1 = findViewById(R.id.PlayerNameh1);playerNameh2 = findViewById(R.id.PlayerNameh2);playerNameh3 = findViewById(R.id.PlayerNameh3);
        playerNameh4 = findViewById(R.id.PlayerNameh4);playerNameh5 = findViewById(R.id.PlayerNameh5);playerNameh6 = findViewById(R.id.PlayerNameh6);playerNamer1 = findViewById(R.id.PlayerNamer1);playerNamer2 = findViewById(R.id.PlayerNamer2);
        playerNamer3 = findViewById(R.id.PlayerNamer3);playerNamer4 = findViewById(R.id.PlayerNamer4);playerNamer5 = findViewById(R.id.PlayerNamer5);playerNamer6 = findViewById(R.id.PlayerNamer6);
        teamName = findViewById(R.id.TeamName);


//        POLE Mallakhamb

        PolePanel = findViewById(R.id.polePanel);

        Pole = findViewById(R.id.poleMallakhambScoreSheet);Pole2 = findViewById(R.id.poleMallakhambScoreSheet2);Pole3 = findViewById(R.id.poleMallakhambScoreSheet3);Pole4 = findViewById(R.id.poleMallakhambScoreSheet4);Pole5 = findViewById(R.id.poleMallakhambScoreSheet5);Pole6 = findViewById(R.id.poleMallakhambScoreSheet6);

        next = findViewById(R.id.nextPolePlayer1);next2 = findViewById(R.id.nextPolePlayer2);next3 = findViewById(R.id.nextPolePlayer3);next4 = findViewById(R.id.nextPolePlayer4);next5 = findViewById(R.id.nextPolePlayer5);

        Submit = findViewById(R.id.submit);Submit2 = findViewById(R.id.submit2);Submit3 = findViewById(R.id.submit3);Submit4 = findViewById(R.id.submit4);Submit5 = findViewById(R.id.submit5);Submit6 = findViewById(R.id.submit6);

        Confirm = findViewById(R.id.confirm);Confirm2 = findViewById(R.id.confirm2);Confirm3 = findViewById(R.id.confirm3);Confirm4 = findViewById(R.id.confirm4);Confirm5 = findViewById(R.id.confirm5);Confirm6 = findViewById(R.id.confirm6);

        difficulty = findViewById(R.id.Difficulty);difficulty2 = findViewById(R.id.Difficulty2);difficulty3 = findViewById(R.id.Difficulty3);difficulty4 = findViewById(R.id.Difficulty4);difficulty5 = findViewById(R.id.Difficulty5);difficulty6 = findViewById(R.id.Difficulty6);
        execution = findViewById(R.id.Execution);execution2 = findViewById(R.id.Execution2);execution3 = findViewById(R.id.Execution3);execution4 = findViewById(R.id.Execution4);execution5 = findViewById(R.id.Execution5);execution6 = findViewById(R.id.Execution6);
        originality = findViewById(R.id.Originality);originality2 = findViewById(R.id.Originality2);originality3 = findViewById(R.id.Originality3);originality4 = findViewById(R.id.Originality4);originality5 = findViewById(R.id.Originality5);originality6 = findViewById(R.id.Originality6);
        difficultyFinal = findViewById(R.id.DifficultyFinal);difficultyFinal2 = findViewById(R.id.DifficultyFinal2);difficultyFinal3 = findViewById(R.id.DifficultyFinal3);difficultyFinal4 = findViewById(R.id.DifficultyFinal4);difficultyFinal5 = findViewById(R.id.DifficultyFinal5);difficultyFinal6 = findViewById(R.id.DifficultyFinal6);
        executionFinal = findViewById(R.id.ExecutionFinal);executionFinal2 = findViewById(R.id.ExecutionFinal2);executionFinal3 = findViewById(R.id.ExecutionFinal3);executionFinal4 = findViewById(R.id.ExecutionFinal4);executionFinal5 = findViewById(R.id.ExecutionFinal5);executionFinal6 = findViewById(R.id.ExecutionFinal6);
        originalityFinal = findViewById(R.id.OriginalityFinal);originalityFinal2 = findViewById(R.id.OriginalityFinal2);originalityFinal3 = findViewById(R.id.OriginalityFinal3);originalityFinal4 = findViewById(R.id.OriginalityFinal4);originalityFinal5 = findViewById(R.id.OriginalityFinal5);originalityFinal6 = findViewById(R.id.OriginalityFinal6);

        FP1 = findViewById(R.id.fp1);FP2 = findViewById(R.id.fp2);FP3 = findViewById(R.id.fp3);FP4 = findViewById(R.id.fp4);FP5 = findViewById(R.id.fp5);FP6 = findViewById(R.id.fp6);FP7 = findViewById(R.id.fp7);FP8 = findViewById(R.id.fp8);FP9 = findViewById(R.id.fp9);FP10 = findViewById(R.id.fp10);
        FP12 = findViewById(R.id.fp12);FP22 = findViewById(R.id.fp22);FP32 = findViewById(R.id.fp32);FP42 = findViewById(R.id.fp42);FP52 = findViewById(R.id.fp52);FP62 = findViewById(R.id.fp62);FP72 = findViewById(R.id.fp72);FP82 = findViewById(R.id.fp82);FP92 = findViewById(R.id.fp92);FP102 = findViewById(R.id.fp102);
        FP13 = findViewById(R.id.fp13);FP23 = findViewById(R.id.fp23);FP33 = findViewById(R.id.fp33);FP43 = findViewById(R.id.fp43);FP53 = findViewById(R.id.fp53);FP63 = findViewById(R.id.fp63);FP73 = findViewById(R.id.fp73);FP83 = findViewById(R.id.fp83);FP93 = findViewById(R.id.fp93);FP103 = findViewById(R.id.fp103);
        FP14 = findViewById(R.id.fp14);FP24 = findViewById(R.id.fp24);FP34 = findViewById(R.id.fp34);FP44 = findViewById(R.id.fp44);FP54 = findViewById(R.id.fp54);FP64 = findViewById(R.id.fp64);FP74 = findViewById(R.id.fp74);FP84 = findViewById(R.id.fp84);FP94 = findViewById(R.id.fp94);FP104 = findViewById(R.id.fp104);
        FP15 = findViewById(R.id.fp15);FP25 = findViewById(R.id.fp25);FP35 = findViewById(R.id.fp35);FP45 = findViewById(R.id.fp45);FP55 = findViewById(R.id.fp55);FP65 = findViewById(R.id.fp65);FP75 = findViewById(R.id.fp75);FP85 = findViewById(R.id.fp85);FP95 = findViewById(R.id.fp95);FP105 = findViewById(R.id.fp105);
        FP16 = findViewById(R.id.fp16);FP26 = findViewById(R.id.fp26);FP36 = findViewById(R.id.fp36);FP46 = findViewById(R.id.fp46);FP56 = findViewById(R.id.fp56);FP66 = findViewById(R.id.fp66);FP76 = findViewById(R.id.fp76);FP86 = findViewById(R.id.fp86);FP96 = findViewById(R.id.fp96);FP106 = findViewById(R.id.fp106);

        BD1 = findViewById(R.id.bd1);BD2 = findViewById(R.id.bd2);BD3 = findViewById(R.id.bd3);BD4 = findViewById(R.id.bd4);BD5 = findViewById(R.id.bd5);BD6 = findViewById(R.id.bd6);BD7 = findViewById(R.id.bd7);BD8 = findViewById(R.id.bd8);BD9 = findViewById(R.id.bd9);BD10 = findViewById(R.id.bd10);
        BD12 = findViewById(R.id.bd12);BD22 = findViewById(R.id.bd22);BD32 = findViewById(R.id.bd32);BD42 = findViewById(R.id.bd42);BD52 = findViewById(R.id.bd52);BD62 = findViewById(R.id.bd62);BD72 = findViewById(R.id.bd72);BD82 = findViewById(R.id.bd82);BD92 = findViewById(R.id.bd92);BD102 = findViewById(R.id.bd102);
        BD13 = findViewById(R.id.bd13);BD23 = findViewById(R.id.bd23);BD33 = findViewById(R.id.bd33);BD43 = findViewById(R.id.bd43);BD53 = findViewById(R.id.bd53);BD63 = findViewById(R.id.bd63);BD73 = findViewById(R.id.bd73);BD83 = findViewById(R.id.bd83);BD93 = findViewById(R.id.bd93);BD103 = findViewById(R.id.bd103);
        BD14 = findViewById(R.id.bd14);BD24 = findViewById(R.id.bd24);BD34 = findViewById(R.id.bd34);BD44 = findViewById(R.id.bd44);BD54 = findViewById(R.id.bd54);BD64 = findViewById(R.id.bd64);BD74 = findViewById(R.id.bd74);BD84 = findViewById(R.id.bd84);BD94 = findViewById(R.id.bd94);BD104 = findViewById(R.id.bd104);
        BD15 = findViewById(R.id.bd15);BD25 = findViewById(R.id.bd25);BD35 = findViewById(R.id.bd35);BD45 = findViewById(R.id.bd45);BD55 = findViewById(R.id.bd55);BD65 = findViewById(R.id.bd65);BD75 = findViewById(R.id.bd75);BD85 = findViewById(R.id.bd85);BD95 = findViewById(R.id.bd95);BD105 = findViewById(R.id.bd105);
        BD16 = findViewById(R.id.bd16);BD26 = findViewById(R.id.bd26);BD36 = findViewById(R.id.bd36);BD46 = findViewById(R.id.bd46);BD56 = findViewById(R.id.bd56);BD66 = findViewById(R.id.bd66);BD76 = findViewById(R.id.bd76);BD86 = findViewById(R.id.bd86);BD96 = findViewById(R.id.bd96);BD106 = findViewById(R.id.bd106);

        BY1 = findViewById(R.id.by1);BY2 = findViewById(R.id.by2);BY3 = findViewById(R.id.by3);BY4 = findViewById(R.id.by4);BY5 = findViewById(R.id.by5);BY6 = findViewById(R.id.by6);BY7 = findViewById(R.id.by7);BY8 = findViewById(R.id.by8);BY9 = findViewById(R.id.by9);BY10 = findViewById(R.id.by10);
        BY12 = findViewById(R.id.by12);BY22 = findViewById(R.id.by22);BY32 = findViewById(R.id.by32);BY42 = findViewById(R.id.by42);BY52 = findViewById(R.id.by52);BY62 = findViewById(R.id.by62);BY72 = findViewById(R.id.by72);BY82 = findViewById(R.id.by82);BY92 = findViewById(R.id.by92);BY102 = findViewById(R.id.by102);
        BY13 = findViewById(R.id.by13);BY23 = findViewById(R.id.by23);BY33 = findViewById(R.id.by33);BY43 = findViewById(R.id.by43);BY53 = findViewById(R.id.by53);BY63 = findViewById(R.id.by63);BY73 = findViewById(R.id.by73);BY83 = findViewById(R.id.by83);BY93 = findViewById(R.id.by93);BY103 = findViewById(R.id.by103);
        BY14 = findViewById(R.id.by14);BY24 = findViewById(R.id.by24);BY34 = findViewById(R.id.by34);BY44 = findViewById(R.id.by44);BY54 = findViewById(R.id.by54);BY64 = findViewById(R.id.by64);BY74 = findViewById(R.id.by74);BY84 = findViewById(R.id.by84);BY94 = findViewById(R.id.by94);BY104 = findViewById(R.id.by104);
        BY15 = findViewById(R.id.by15);BY25 = findViewById(R.id.by25);BY35 = findViewById(R.id.by35);BY45 = findViewById(R.id.by45);BY55 = findViewById(R.id.by55);BY65 = findViewById(R.id.by65);BY75 = findViewById(R.id.by75);BY85 = findViewById(R.id.by85);BY95 = findViewById(R.id.by95);BY105 = findViewById(R.id.by105);
        BY16 = findViewById(R.id.by16);BY26 = findViewById(R.id.by26);BY36 = findViewById(R.id.by36);BY46 = findViewById(R.id.by46);BY56 = findViewById(R.id.by56);BY66 = findViewById(R.id.by66);BY76 = findViewById(R.id.by76);BY86 = findViewById(R.id.by86);BY96 = findViewById(R.id.by96);BY106 = findViewById(R.id.by106);

        BN1 = findViewById(R.id.bn1);BN2 = findViewById(R.id.bn2);BN3 = findViewById(R.id.bn3);BN4 = findViewById(R.id.bn4);BN5 = findViewById(R.id.bn5);BN6 = findViewById(R.id.bn6);BN7 = findViewById(R.id.bn7);BN8 = findViewById(R.id.bn8);BN9 = findViewById(R.id.bn9);BN10 = findViewById(R.id.bn10);
        BN12 = findViewById(R.id.bn12);BN22 = findViewById(R.id.bn22);BN32 = findViewById(R.id.bn32);BN42 = findViewById(R.id.bn42);BN52 = findViewById(R.id.bn52);BN62 = findViewById(R.id.bn62);BN72 = findViewById(R.id.bn72);BN82 = findViewById(R.id.bn82);BN92 = findViewById(R.id.bn92);BN102 = findViewById(R.id.bn102);
        BN13 = findViewById(R.id.bn13);BN23 = findViewById(R.id.bn23);BN33 = findViewById(R.id.bn33);BN43 = findViewById(R.id.bn43);BN53 = findViewById(R.id.bn53);BN63 = findViewById(R.id.bn63);BN73 = findViewById(R.id.bn73);BN83 = findViewById(R.id.bn83);BN93 = findViewById(R.id.bn93);BN103 = findViewById(R.id.bn103);
        BN14 = findViewById(R.id.bn14);BN24 = findViewById(R.id.bn24);BN34 = findViewById(R.id.bn34);BN44 = findViewById(R.id.bn44);BN54 = findViewById(R.id.bn54);BN64 = findViewById(R.id.bn64);BN74 = findViewById(R.id.bn74);BN84 = findViewById(R.id.bn84);BN94 = findViewById(R.id.bn94);BN104 = findViewById(R.id.bn104);
        BN15 = findViewById(R.id.bn15);BN25 = findViewById(R.id.bn25);BN35 = findViewById(R.id.bn35);BN45 = findViewById(R.id.bn45);BN55 = findViewById(R.id.bn55);BN65 = findViewById(R.id.bn65);BN75 = findViewById(R.id.bn75);BN85 = findViewById(R.id.bn85);BN95 = findViewById(R.id.bn95);BN105 = findViewById(R.id.bn105);
        BN16 = findViewById(R.id.bn16);BN26 = findViewById(R.id.bn26);BN36 = findViewById(R.id.bn36);BN46 = findViewById(R.id.bn46);BN56 = findViewById(R.id.bn56);BN66 = findViewById(R.id.bn66);BN76 = findViewById(R.id.bn76);BN86 = findViewById(R.id.bn86);BN96 = findViewById(R.id.bn96);BN106 = findViewById(R.id.bn106);

        FY1 = findViewById(R.id.fy1);FY2 = findViewById(R.id.fy2);FY3 = findViewById(R.id.fy3);FY4 = findViewById(R.id.fy4);FY5 = findViewById(R.id.fy5);FY6 = findViewById(R.id.fy6);FY7 = findViewById(R.id.fy7);FY8 = findViewById(R.id.fy8);FY9 = findViewById(R.id.fy9);FY10 = findViewById(R.id.fy10);
        FY12 = findViewById(R.id.fy12);FY22 = findViewById(R.id.fy22);FY32 = findViewById(R.id.fy32);FY42 = findViewById(R.id.fy42);FY52 = findViewById(R.id.fy52);FY62 = findViewById(R.id.fy62);FY72 = findViewById(R.id.fy72);FY82 = findViewById(R.id.fy82);FY92 = findViewById(R.id.fy92);FY102 = findViewById(R.id.fy102);
        FY13 = findViewById(R.id.fy13);FY23 = findViewById(R.id.fy23);FY33 = findViewById(R.id.fy33);FY43 = findViewById(R.id.fy43);FY53 = findViewById(R.id.fy53);FY63 = findViewById(R.id.fy63);FY73 = findViewById(R.id.fy73);FY83 = findViewById(R.id.fy83);FY93 = findViewById(R.id.fy93);FY103 = findViewById(R.id.fy103);
        FY14 = findViewById(R.id.fy14);FY24 = findViewById(R.id.fy24);FY34 = findViewById(R.id.fy34);FY44 = findViewById(R.id.fy44);FY54 = findViewById(R.id.fy54);FY64 = findViewById(R.id.fy64);FY74 = findViewById(R.id.fy74);FY84 = findViewById(R.id.fy84);FY94 = findViewById(R.id.fy94);FY104 = findViewById(R.id.fy104);
        FY15 = findViewById(R.id.fy15);FY25 = findViewById(R.id.fy25);FY35 = findViewById(R.id.fy35);FY45 = findViewById(R.id.fy45);FY55 = findViewById(R.id.fy55);FY65 = findViewById(R.id.fy65);FY75 = findViewById(R.id.fy75);FY85 = findViewById(R.id.fy85);FY95 = findViewById(R.id.fy95);FY105 = findViewById(R.id.fy105);
        FY16 = findViewById(R.id.fy16);FY26 = findViewById(R.id.fy26);FY36 = findViewById(R.id.fy36);FY46 = findViewById(R.id.fy46);FY56 = findViewById(R.id.fy56);FY66 = findViewById(R.id.fy66);FY76 = findViewById(R.id.fy76);FY86 = findViewById(R.id.fy86);FY96 = findViewById(R.id.fy96);FY106 = findViewById(R.id.fy106);

        FN1 = findViewById(R.id.fn1);FN2 = findViewById(R.id.fn2);FN3 = findViewById(R.id.fn3);FN4 = findViewById(R.id.fn4);FN5 = findViewById(R.id.fn5);FN6 = findViewById(R.id.fn6);FN7 = findViewById(R.id.fn7);FN8 = findViewById(R.id.fn8);FN9 = findViewById(R.id.fn9);FN10 = findViewById(R.id.fn10);
        FN12 = findViewById(R.id.fn12);FN22 = findViewById(R.id.fn22);FN32 = findViewById(R.id.fn32);FN42 = findViewById(R.id.fn42);FN52 = findViewById(R.id.fn52);FN62 = findViewById(R.id.fn62);FN72 = findViewById(R.id.fn72);FN82 = findViewById(R.id.fn82);FN92 = findViewById(R.id.fn92);FN102 = findViewById(R.id.fn102);
        FN13 = findViewById(R.id.fn13);FN23 = findViewById(R.id.fn23);FN33 = findViewById(R.id.fn33);FN43 = findViewById(R.id.fn43);FN53 = findViewById(R.id.fn53);FN63 = findViewById(R.id.fn63);FN73 = findViewById(R.id.fn73);FN83 = findViewById(R.id.fn83);FN93 = findViewById(R.id.fn93);FN103 = findViewById(R.id.fn103);
        FN14 = findViewById(R.id.fn14);FN24 = findViewById(R.id.fn24);FN34 = findViewById(R.id.fn34);FN44 = findViewById(R.id.fn44);FN54 = findViewById(R.id.fn54);FN64 = findViewById(R.id.fn64);FN74 = findViewById(R.id.fn74);FN84 = findViewById(R.id.fn84);FN94 = findViewById(R.id.fn94);FN104 = findViewById(R.id.fn104);
        FN15 = findViewById(R.id.fn15);FN25 = findViewById(R.id.fn25);FN35 = findViewById(R.id.fn35);FN45 = findViewById(R.id.fn45);FN55 = findViewById(R.id.fn55);FN65 = findViewById(R.id.fn65);FN75 = findViewById(R.id.fn75);FN85 = findViewById(R.id.fn85);FN95 = findViewById(R.id.fn95);FN105 = findViewById(R.id.fn105);
        FN16 = findViewById(R.id.fn16);FN26 = findViewById(R.id.fn26);FN36 = findViewById(R.id.fn36);FN46 = findViewById(R.id.fn46);FN56 = findViewById(R.id.fn56);FN66 = findViewById(R.id.fn66);FN76 = findViewById(R.id.fn76);FN86 = findViewById(R.id.fn86);FN96 = findViewById(R.id.fn96);FN106 = findViewById(R.id.fn106);

        BDComb = findViewById(R.id.bdcomb);BDComb2 = findViewById(R.id.bdcomb2);BDComb3 = findViewById(R.id.bdcomb3);BDComb4 = findViewById(R.id.bdcomb4);BDComb5 = findViewById(R.id.bdcomb5);BDComb6 = findViewById(R.id.bdcomb6);

        FPComb = findViewById(R.id.fpcomb);FPComb2 = findViewById(R.id.fpcomb2);FPComb3 = findViewById(R.id.fpcomb3);FPComb4 = findViewById(R.id.fpcomb4);FPComb5 = findViewById(R.id.fpcomb5);FPComb6 = findViewById(R.id.fpcomb6);

        BDTotal = findViewById(R.id.bdtotal);BDTotal2 = findViewById(R.id.bdtotal2);BDTotal3 = findViewById(R.id.bdtotal3);BDTotal4 = findViewById(R.id.bdtotal4);BDTotal5 = findViewById(R.id.bdtotal5);BDTotal6 = findViewById(R.id.bdtotal6);

        FPTotal = findViewById(R.id.fptotal);FPTotal2 = findViewById(R.id.fptotal2);FPTotal3 = findViewById(R.id.fptotal3);FPTotal4 = findViewById(R.id.fptotal4);FPTotal5 = findViewById(R.id.fptotal5);FPTotal6 = findViewById(R.id.fptotal6);

//        HANGING Mallakhamb

        HangingPanel = findViewById(R.id.hangingPanel);

        Hanging = findViewById(R.id.hangingMallakhambScoreSheet);Hanging2 = findViewById(R.id.hangingMallakhambScoreSheet2);Hanging3 = findViewById(R.id.hangingMallakhambScoreSheet3);Hanging4 = findViewById(R.id.hangingMallakhambScoreSheet4);Hanging5 = findViewById(R.id.hangingMallakhambScoreSheet5);Hanging6 = findViewById(R.id.hangingMallakhambScoreSheet6);

        nexth = findViewById(R.id.nextHangingPlayer1);
        nexth2 = findViewById(R.id.nextHangingPlayer12);
        nexth3 = findViewById(R.id.nextHangingPlayer13);
        nexth4 = findViewById(R.id.nextHangingPlayer14);
        nexth5 = findViewById(R.id.nextHangingPlayer15);

        Submith = findViewById(R.id.submith);Submith2 = findViewById(R.id.submith2);Submith3 = findViewById(R.id.submith3);Submith4 = findViewById(R.id.submith4);Submith5 = findViewById(R.id.submith5);Submith6 = findViewById(R.id.submith6);

        Confirmh = findViewById(R.id.confirmh);Confirmh2 = findViewById(R.id.confirmh2);Confirmh3 = findViewById(R.id.confirmh3);Confirmh4 = findViewById(R.id.confirmh4);Confirmh5 = findViewById(R.id.confirmh5);Confirmh6 = findViewById(R.id.confirmh6);

        difficultyh = findViewById(R.id.Difficultyh);difficultyh2 = findViewById(R.id.Difficultyh2);difficultyh3 = findViewById(R.id.Difficultyh3);difficultyh4 = findViewById(R.id.Difficultyh4);difficultyh5 = findViewById(R.id.Difficultyh5);difficultyh6 = findViewById(R.id.Difficultyh6);

        executionh = findViewById(R.id.Executionh);executionh2 = findViewById(R.id.Executionh2);executionh3 = findViewById(R.id.Executionh3);executionh4 = findViewById(R.id.Executionh4);executionh5 = findViewById(R.id.Executionh5);

        originalityh = findViewById(R.id.Originalityh);originalityh2 = findViewById(R.id.Originalityh2);originalityh3 = findViewById(R.id.Originalityh3);originalityh4 = findViewById(R.id.Originalityh4);originalityh5 = findViewById(R.id.Originalityh5);originalityh6 = findViewById(R.id.Originalityh6);

        difficultyFinalh = findViewById(R.id.DifficultyFinalh);difficultyFinalh2 = findViewById(R.id.DifficultyFinalh2);difficultyFinalh3 = findViewById(R.id.DifficultyFinalh3);difficultyFinalh4 = findViewById(R.id.DifficultyFinalh4);difficultyFinalh5 = findViewById(R.id.DifficultyFinalh5);difficultyFinalh6 = findViewById(R.id.DifficultyFinalh6);

        executionFinalh = findViewById(R.id.ExecutionFinalh);executionFinalh2 = findViewById(R.id.ExecutionFinalh2);executionFinalh3 = findViewById(R.id.ExecutionFinalh3);executionFinalh4 = findViewById(R.id.ExecutionFinalh4);executionFinalh5 = findViewById(R.id.ExecutionFinalh5);executionFinalh6 = findViewById(R.id.ExecutionFinalh6);

        originalityFinalh = findViewById(R.id.OriginalityFinalh);originalityFinalh2 = findViewById(R.id.OriginalityFinalh2);originalityFinalh3 = findViewById(R.id.OriginalityFinalh3);originalityFinalh4 = findViewById(R.id.OriginalityFinalh4);originalityFinalh5 = findViewById(R.id.OriginalityFinalh5);originalityFinalh6 = findViewById(R.id.OriginalityFinalh6);

        FP1h = findViewById(R.id.fp1h);FP2h = findViewById(R.id.fp2h);FP3h = findViewById(R.id.fp3h);FP4h = findViewById(R.id.fp4h);FP5h = findViewById(R.id.fp5h);FP6h = findViewById(R.id.fp6h);FP7h = findViewById(R.id.fp7h);FP8h = findViewById(R.id.fp8h);FP9h = findViewById(R.id.fp9h);FP10h = findViewById(R.id.fp10h);
        FP1h2 = findViewById(R.id.fp1h2);FP2h2 = findViewById(R.id.fp2h2);FP3h2 = findViewById(R.id.fp3h2);FP4h2 = findViewById(R.id.fp4h2);FP5h2 = findViewById(R.id.fp5h2);FP6h2 = findViewById(R.id.fp6h2);FP7h2 = findViewById(R.id.fp7h2);FP8h2 = findViewById(R.id.fp8h2);FP9h2 = findViewById(R.id.fp9h2);FP10h2 = findViewById(R.id.fp10h2);
        FP1h3 = findViewById(R.id.fp1h3);FP2h3 = findViewById(R.id.fp2h3);FP3h3 = findViewById(R.id.fp3h3);FP4h3 = findViewById(R.id.fp4h3);FP5h3 = findViewById(R.id.fp5h3);FP6h3 = findViewById(R.id.fp6h3);FP7h3 = findViewById(R.id.fp7h3);FP8h3 = findViewById(R.id.fp8h3);FP9h3 = findViewById(R.id.fp9h3);FP10h3 = findViewById(R.id.fp10h3);
        FP1h4 = findViewById(R.id.fp1h4);FP2h4 = findViewById(R.id.fp2h4);FP3h4 = findViewById(R.id.fp3h4);FP4h4 = findViewById(R.id.fp4h4);FP5h4 = findViewById(R.id.fp5h4);FP6h4 = findViewById(R.id.fp6h4);FP7h4 = findViewById(R.id.fp7h4);FP8h4 = findViewById(R.id.fp8h4);FP9h4 = findViewById(R.id.fp9h4);FP10h4 = findViewById(R.id.fp10h4);
        FP1h5 = findViewById(R.id.fp1h5);FP2h5 = findViewById(R.id.fp2h5);FP3h5 = findViewById(R.id.fp3h5);FP4h5 = findViewById(R.id.fp4h5);FP5h5 = findViewById(R.id.fp5h5);FP6h5 = findViewById(R.id.fp6h5);FP7h5 = findViewById(R.id.fp7h5);FP8h5 = findViewById(R.id.fp8h5);FP9h5 = findViewById(R.id.fp9h5);FP10h5 = findViewById(R.id.fp10h5);
        FP1h6 = findViewById(R.id.fp1h6);FP2h6 = findViewById(R.id.fp2h6);FP3h6 = findViewById(R.id.fp3h6);FP4h6 = findViewById(R.id.fp4h6);FP5h6 = findViewById(R.id.fp5h6);FP6h6 = findViewById(R.id.fp6h6);FP7h6 = findViewById(R.id.fp7h6);FP8h6 = findViewById(R.id.fp8h6);FP9h6 = findViewById(R.id.fp9h6);FP10h6 = findViewById(R.id.fp10h6);

        BD1h = findViewById(R.id.bd1h);BD2h = findViewById(R.id.bd2h);BD3h = findViewById(R.id.bd3h);BD4h = findViewById(R.id.bd4h);BD5h = findViewById(R.id.bd5h);BD6h = findViewById(R.id.bd6h);BD7h = findViewById(R.id.bd7h);BD8h = findViewById(R.id.bd8h);BD9h = findViewById(R.id.bd9h);BD10h = findViewById(R.id.bd10h);
        BD1h2 = findViewById(R.id.bd1h2);BD2h2 = findViewById(R.id.bd2h2);BD3h2 = findViewById(R.id.bd3h2);BD4h2 = findViewById(R.id.bd4h2);BD5h2 = findViewById(R.id.bd5h2);BD6h2 = findViewById(R.id.bd6h2);BD7h2 = findViewById(R.id.bd7h2);BD8h2 = findViewById(R.id.bd8h2);BD9h2 = findViewById(R.id.bd9h2);BD10h2 = findViewById(R.id.bd10h2);
        BD1h3 = findViewById(R.id.bd1h3);BD2h3 = findViewById(R.id.bd2h3);BD3h3 = findViewById(R.id.bd3h3);BD4h3 = findViewById(R.id.bd4h3);BD5h3 = findViewById(R.id.bd5h3);BD6h3 = findViewById(R.id.bd6h3);BD7h3 = findViewById(R.id.bd7h3);BD8h3 = findViewById(R.id.bd8h3);BD9h3 = findViewById(R.id.bd9h3);BD10h3 = findViewById(R.id.bd10h3);
        BD1h4 = findViewById(R.id.bd1h4);BD2h4 = findViewById(R.id.bd2h4);BD3h4 = findViewById(R.id.bd3h4);BD4h4 = findViewById(R.id.bd4h4);BD5h4 = findViewById(R.id.bd5h4);BD6h4 = findViewById(R.id.bd6h4);BD7h4 = findViewById(R.id.bd7h4);BD8h4 = findViewById(R.id.bd8h4);BD9h4 = findViewById(R.id.bd9h4);BD10h4 = findViewById(R.id.bd10h4);
        BD1h5 = findViewById(R.id.bd1h5);BD2h5 = findViewById(R.id.bd2h5);BD3h5 = findViewById(R.id.bd3h5);BD4h5 = findViewById(R.id.bd4h5);BD5h5 = findViewById(R.id.bd5h5);BD6h5 = findViewById(R.id.bd6h5);BD7h5 = findViewById(R.id.bd7h5);BD8h5 = findViewById(R.id.bd8h5);BD9h5 = findViewById(R.id.bd9h5);BD10h5 = findViewById(R.id.bd10h5);
        BD1h6 = findViewById(R.id.bd1h6);BD2h6 = findViewById(R.id.bd2h6);BD3h6 = findViewById(R.id.bd3h6);BD4h6 = findViewById(R.id.bd4h6);BD5h6 = findViewById(R.id.bd5h6);BD6h6 = findViewById(R.id.bd6h6);BD7h6 = findViewById(R.id.bd7h6);BD8h6 = findViewById(R.id.bd8h6);BD9h6 = findViewById(R.id.bd9h6);BD10h6 = findViewById(R.id.bd10h6);

        BY1h = findViewById(R.id.by1h);BY2h = findViewById(R.id.by2h);BY3h = findViewById(R.id.by3h);BY4h = findViewById(R.id.by4h);BY5h = findViewById(R.id.by5h);BY6h = findViewById(R.id.by6h);BY7h = findViewById(R.id.by7h);BY8h = findViewById(R.id.by8h);BY9h = findViewById(R.id.by9h);BY10h = findViewById(R.id.by10h);
        BY1h2 = findViewById(R.id.by1h2);BY2h2 = findViewById(R.id.by2h2);BY3h2 = findViewById(R.id.by3h2);BY4h2 = findViewById(R.id.by4h2);BY5h2 = findViewById(R.id.by5h2);BY6h2 = findViewById(R.id.by6h2);BY7h2 = findViewById(R.id.by7h2);BY8h2 = findViewById(R.id.by8h2);BY9h2 = findViewById(R.id.by9h2);BY10h2 = findViewById(R.id.by10h2);
        BY1h3 = findViewById(R.id.by1h3);BY2h3 = findViewById(R.id.by2h3);BY3h3 = findViewById(R.id.by3h3);BY4h3 = findViewById(R.id.by4h3);BY5h3 = findViewById(R.id.by5h3);BY6h3 = findViewById(R.id.by6h3);BY7h3 = findViewById(R.id.by7h3);BY8h3 = findViewById(R.id.by8h3);BY9h3 = findViewById(R.id.by9h3);BY10h3 = findViewById(R.id.by10h3);
        BY1h4 = findViewById(R.id.by1h4);BY2h4 = findViewById(R.id.by2h4);BY3h4 = findViewById(R.id.by3h4);BY4h4 = findViewById(R.id.by4h4);BY5h4 = findViewById(R.id.by5h4);BY6h4 = findViewById(R.id.by6h4);BY7h4 = findViewById(R.id.by7h4);BY8h4 = findViewById(R.id.by8h4);BY9h4 = findViewById(R.id.by9h4);BY10h4 = findViewById(R.id.by10h4);
        BY1h5 = findViewById(R.id.by1h5);BY2h5 = findViewById(R.id.by2h5);BY3h5 = findViewById(R.id.by3h5);BY4h5 = findViewById(R.id.by4h5);BY5h5 = findViewById(R.id.by5h5);BY6h5 = findViewById(R.id.by6h5);BY7h5 = findViewById(R.id.by7h5);BY8h5 = findViewById(R.id.by8h5);BY9h5 = findViewById(R.id.by9h5);BY10h5 = findViewById(R.id.by10h5);
        BY1h6 = findViewById(R.id.by1h6);BY2h6 = findViewById(R.id.by2h6);BY3h6 = findViewById(R.id.by3h6);BY4h6 = findViewById(R.id.by4h6);BY5h6 = findViewById(R.id.by5h6);BY6h6 = findViewById(R.id.by6h6);BY7h6 = findViewById(R.id.by7h6);BY8h6 = findViewById(R.id.by8h6);BY9h6 = findViewById(R.id.by9h6);BY10h6 = findViewById(R.id.by10h6);

        BN1h = findViewById(R.id.bn1h);BN2h = findViewById(R.id.bn2h);BN3h = findViewById(R.id.bn3h);BN4h = findViewById(R.id.bn4h);BN5h = findViewById(R.id.bn5h);BN6h = findViewById(R.id.bn6h);BN7h = findViewById(R.id.bn7h);BN8h = findViewById(R.id.bn8h);BN9h = findViewById(R.id.bn9h);BN10h = findViewById(R.id.bn10h);
        BN1h2 = findViewById(R.id.bn1h2);BN2h2 = findViewById(R.id.bn2h2);BN3h2 = findViewById(R.id.bn3h2);BN4h2 = findViewById(R.id.bn4h2);BN5h2 = findViewById(R.id.bn5h2);BN6h2 = findViewById(R.id.bn6h2);BN7h2 = findViewById(R.id.bn7h2);BN8h2 = findViewById(R.id.bn8h2);BN9h2 = findViewById(R.id.bn9h2);BN10h2 = findViewById(R.id.bn10h2);
        BN1h3 = findViewById(R.id.bn1h3);BN2h3 = findViewById(R.id.bn2h3);BN3h3 = findViewById(R.id.bn3h3);BN4h3 = findViewById(R.id.bn4h3);BN5h3 = findViewById(R.id.bn5h3);BN6h3 = findViewById(R.id.bn6h3);BN7h3 = findViewById(R.id.bn7h3);BN8h3 = findViewById(R.id.bn8h3);BN9h3 = findViewById(R.id.bn9h3);BN10h3 = findViewById(R.id.bn10h3);
        BN1h4 = findViewById(R.id.bn1h4);BN2h4 = findViewById(R.id.bn2h4);BN3h4 = findViewById(R.id.bn3h4);BN4h4 = findViewById(R.id.bn4h4);BN5h4 = findViewById(R.id.bn5h4);BN6h4 = findViewById(R.id.bn6h4);BN7h4 = findViewById(R.id.bn7h4);BN8h4 = findViewById(R.id.bn8h4);BN9h4 = findViewById(R.id.bn9h4);BN10h4 = findViewById(R.id.bn10h4);
        BN1h5 = findViewById(R.id.bn1h5);BN2h5 = findViewById(R.id.bn2h5);BN3h5 = findViewById(R.id.bn3h5);BN4h5 = findViewById(R.id.bn4h5);BN5h5 = findViewById(R.id.bn5h5);BN6h5 = findViewById(R.id.bn6h5);BN7h5 = findViewById(R.id.bn7h5);BN8h5 = findViewById(R.id.bn8h5);BN9h5 = findViewById(R.id.bn9h5);BN10h5 = findViewById(R.id.bn10h5);
        BN1h6 = findViewById(R.id.bn1h6);BN2h6 = findViewById(R.id.bn2h6);BN3h6 = findViewById(R.id.bn3h6);BN4h6 = findViewById(R.id.bn4h6);BN5h6 = findViewById(R.id.bn5h6);BN6h6 = findViewById(R.id.bn6h6);BN7h6 = findViewById(R.id.bn7h6);BN8h6 = findViewById(R.id.bn8h6);BN9h6 = findViewById(R.id.bn9h6);BN10h6 = findViewById(R.id.bn10h6);

        FY1h = findViewById(R.id.fy1h);FY2h = findViewById(R.id.fy2h);FY3h = findViewById(R.id.fy3h);FY4h = findViewById(R.id.fy4h);FY5h = findViewById(R.id.fy5h);FY6h = findViewById(R.id.fy6h);FY7h = findViewById(R.id.fy7h);FY8h = findViewById(R.id.fy8h);FY9h = findViewById(R.id.fy9h);FY10h = findViewById(R.id.fy10h);
        FY1h2 = findViewById(R.id.fy1h2);FY2h2 = findViewById(R.id.fy2h2);FY3h2 = findViewById(R.id.fy3h2);FY4h2 = findViewById(R.id.fy4h2);FY5h2 = findViewById(R.id.fy5h2);FY6h2 = findViewById(R.id.fy6h2);FY7h2 = findViewById(R.id.fy7h2);FY8h2 = findViewById(R.id.fy8h2);FY9h2 = findViewById(R.id.fy9h2);FY10h2 = findViewById(R.id.fy10h2);
        FY1h3 = findViewById(R.id.fy1h3);FY2h3 = findViewById(R.id.fy2h3);FY3h3 = findViewById(R.id.fy3h3);FY4h3 = findViewById(R.id.fy4h3);FY5h3 = findViewById(R.id.fy5h3);FY6h3 = findViewById(R.id.fy6h3);FY7h3 = findViewById(R.id.fy7h3);FY8h3 = findViewById(R.id.fy8h3);FY9h3 = findViewById(R.id.fy9h3);FY10h3 = findViewById(R.id.fy10h3);
        FY1h4 = findViewById(R.id.fy1h4);FY2h4 = findViewById(R.id.fy2h4);FY3h4 = findViewById(R.id.fy3h4);FY4h4 = findViewById(R.id.fy4h4);FY5h4 = findViewById(R.id.fy5h4);FY6h4 = findViewById(R.id.fy6h4);FY7h4 = findViewById(R.id.fy7h4);FY8h4 = findViewById(R.id.fy8h4);FY9h4 = findViewById(R.id.fy9h4);FY10h4 = findViewById(R.id.fy10h4);
        FY1h5 = findViewById(R.id.fy1h5);FY2h5 = findViewById(R.id.fy2h5);FY3h5 = findViewById(R.id.fy3h5);FY4h5 = findViewById(R.id.fy4h5);FY5h5 = findViewById(R.id.fy5h5);FY6h5 = findViewById(R.id.fy6h5);FY7h5 = findViewById(R.id.fy7h5);FY8h5 = findViewById(R.id.fy8h5);FY9h5 = findViewById(R.id.fy9h5);FY10h5 = findViewById(R.id.fy10h5);
        FY1h6 = findViewById(R.id.fy1h6);FY2h6 = findViewById(R.id.fy2h6);FY3h6 = findViewById(R.id.fy3h6);FY4h6 = findViewById(R.id.fy4h6);FY5h6 = findViewById(R.id.fy5h6);FY6h6 = findViewById(R.id.fy6h6);FY7h6 = findViewById(R.id.fy7h6);FY8h6 = findViewById(R.id.fy8h6);FY9h6 = findViewById(R.id.fy9h6);FY10h6 = findViewById(R.id.fy10h6);

        FN1h = findViewById(R.id.fn1h);FN2h = findViewById(R.id.fn2h);FN3h = findViewById(R.id.fn3h);FN4h = findViewById(R.id.fn4h);FN5h = findViewById(R.id.fn5h);FN6h = findViewById(R.id.fn6h);FN7h = findViewById(R.id.fn7h);FN8h = findViewById(R.id.fn8h);FN9h = findViewById(R.id.fn9h);FN10h = findViewById(R.id.fn10h);
        FN1h2 = findViewById(R.id.fn1h2);FN2h2 = findViewById(R.id.fn2h2);FN3h2 = findViewById(R.id.fn3h2);FN4h2 = findViewById(R.id.fn4h2);FN5h2 = findViewById(R.id.fn5h2);FN6h2 = findViewById(R.id.fn6h2);FN7h2 = findViewById(R.id.fn7h2);FN8h2 = findViewById(R.id.fn8h2);FN9h2 = findViewById(R.id.fn9h2);FN10h2 = findViewById(R.id.fn10h2);
        FN1h3 = findViewById(R.id.fn1h3);FN2h3 = findViewById(R.id.fn2h3);FN3h3 = findViewById(R.id.fn3h3);FN4h3 = findViewById(R.id.fn4h3);FN5h3 = findViewById(R.id.fn5h3);FN6h3 = findViewById(R.id.fn6h3);FN7h3 = findViewById(R.id.fn7h3);FN8h3 = findViewById(R.id.fn8h3);FN9h3 = findViewById(R.id.fn9h3);FN10h3 = findViewById(R.id.fn10h3);
        FN1h4 = findViewById(R.id.fn1h4);FN2h4 = findViewById(R.id.fn2h4);FN3h4 = findViewById(R.id.fn3h4);FN4h4 = findViewById(R.id.fn4h4);FN5h4 = findViewById(R.id.fn5h4);FN6h4 = findViewById(R.id.fn6h4);FN7h4 = findViewById(R.id.fn7h4);FN8h4 = findViewById(R.id.fn8h4);FN9h4 = findViewById(R.id.fn9h4);FN10h4 = findViewById(R.id.fn10h4);
        FN1h5 = findViewById(R.id.fn1h5);FN2h5 = findViewById(R.id.fn2h5);FN3h5 = findViewById(R.id.fn3h5);FN4h5 = findViewById(R.id.fn4h5);FN5h5 = findViewById(R.id.fn5h5);FN6h5 = findViewById(R.id.fn6h5);FN7h5 = findViewById(R.id.fn7h5);FN8h5 = findViewById(R.id.fn8h5);FN9h5 = findViewById(R.id.fn9h5);FN10h5 = findViewById(R.id.fn10h5);
        FN1h6 = findViewById(R.id.fn1h6);FN2h6 = findViewById(R.id.fn2h6);FN3h6 = findViewById(R.id.fn3h6);FN4h6 = findViewById(R.id.fn4h6);FN5h6 = findViewById(R.id.fn5h6);FN6h6 = findViewById(R.id.fn6h6);FN7h6 = findViewById(R.id.fn7h6);FN8h6 = findViewById(R.id.fn8h6);FN9h6 = findViewById(R.id.fn9h6);FN10h6 = findViewById(R.id.fn10h6);

        BDCombh = findViewById(R.id.bdcombh);BDCombh2 = findViewById(R.id.bdcombh2);BDCombh3 = findViewById(R.id.bdcombh3);BDCombh4 = findViewById(R.id.bdcombh4);BDCombh5 = findViewById(R.id.bdcombh5);BDCombh6 = findViewById(R.id.bdcombh6);

        FPCombh = findViewById(R.id.fpcombh);FPCombh2 = findViewById(R.id.fpcombh2);FPCombh3 = findViewById(R.id.fpcombh3);FPCombh4 = findViewById(R.id.fpcombh4);FPCombh5 = findViewById(R.id.fpcombh5);FPCombh6 = findViewById(R.id.fpcombh6);

        BDTotalh = findViewById(R.id.bdtotalh);BDTotalh2 = findViewById(R.id.bdtotalh2);BDTotalh3 = findViewById(R.id.bdtotalh3);BDTotalh4 = findViewById(R.id.bdtotalh4);BDTotalh5 = findViewById(R.id.bdtotalh5);BDTotalh6 = findViewById(R.id.bdtotalh6);

        FPTotalh = findViewById(R.id.fptotalh);FPTotalh2 = findViewById(R.id.fptotalh2);FPTotalh3 = findViewById(R.id.fptotalh3);FPTotalh4 = findViewById(R.id.fptotalh4);FPTotalh5 = findViewById(R.id.fptotalh5);FPTotalh6 = findViewById(R.id.fptotalh6);

//        ROPE Mallakhamb

        RopePanel = findViewById(R.id.ropePanel);

        nextr = findViewById(R.id.nextRopePlayer1);
        nextr2 = findViewById(R.id.nextRopePlayer12);
        nextr3 = findViewById(R.id.nextRopePlayer13);
        nextr4 = findViewById(R.id.nextRopePlayer14);
        nextr5 = findViewById(R.id.nextRopePlayer15);

        Rope = findViewById(R.id.ropeMallakhambScoreSheet);Rope2 = findViewById(R.id.ropeMallakhambScoreSheet2);Rope3 = findViewById(R.id.ropeMallakhambScoreSheet3);Rope4 = findViewById(R.id.ropeMallakhambScoreSheet4);Rope5 = findViewById(R.id.ropeMallakhambScoreSheet5);Rope6 = findViewById(R.id.ropeMallakhambScoreSheet6);

        Submitr = findViewById(R.id.submitr);Submitr2 = findViewById(R.id.submitr2);Submitr3 = findViewById(R.id.submitr3);Submitr4 = findViewById(R.id.submitr4);Submitr5 = findViewById(R.id.submitr5);Submitr6 = findViewById(R.id.submitr6);

        Confirmr = findViewById(R.id.confirmr);Confirmr2 = findViewById(R.id.confirmr2);Confirmr3 = findViewById(R.id.confirmr3);Confirmr4 = findViewById(R.id.confirmr4);Confirmr5 = findViewById(R.id.confirmr5);Confirmr6 = findViewById(R.id.confirmr6);

        difficultyr = findViewById(R.id.Difficultyr);difficultyr2 = findViewById(R.id.Difficultyr2);difficultyr3 = findViewById(R.id.Difficultyr3);difficultyr4 = findViewById(R.id.Difficultyr4);difficultyr5 = findViewById(R.id.Difficultyr5);difficultyr6 = findViewById(R.id.Difficultyr6);

        executionr = findViewById(R.id.Executionr);executionr2 = findViewById(R.id.Executionr2);executionr3 = findViewById(R.id.Executionr3);executionr4 = findViewById(R.id.Executionr4);executionr5 = findViewById(R.id.Executionr5);executionr6 = findViewById(R.id.Executionr6);

        originalityr = findViewById(R.id.Originalityr);originalityr2 = findViewById(R.id.Originalityr2);originalityr3 = findViewById(R.id.Originalityr3);originalityr4 = findViewById(R.id.Originalityr4);originalityr5 = findViewById(R.id.Originalityr5);originalityr6 = findViewById(R.id.Originalityr6);

        difficultyFinalr = findViewById(R.id.DifficultyFinalr);difficultyFinalr2 = findViewById(R.id.DifficultyFinalr2);difficultyFinalr3 = findViewById(R.id.DifficultyFinalr3);difficultyFinalr4 = findViewById(R.id.DifficultyFinalr4);difficultyFinalr5 = findViewById(R.id.DifficultyFinalr5);difficultyFinalr6 = findViewById(R.id.DifficultyFinalr6);

        executionFinalr = findViewById(R.id.ExecutionFinalr);executionFinalr2 = findViewById(R.id.ExecutionFinalr2);executionFinalr3 = findViewById(R.id.ExecutionFinalr3);executionFinalr4 = findViewById(R.id.ExecutionFinalr4);executionFinalr5 = findViewById(R.id.ExecutionFinalr5);executionFinalr6 = findViewById(R.id.ExecutionFinalr6);

        originalityFinalr = findViewById(R.id.OriginalityFinalr);originalityFinalr2 = findViewById(R.id.OriginalityFinalr2);originalityFinalr3 = findViewById(R.id.OriginalityFinalr3);originalityFinalr4 = findViewById(R.id.OriginalityFinalr4);originalityFinalr5 = findViewById(R.id.OriginalityFinalr5);originalityFinalr6 = findViewById(R.id.OriginalityFinalr6);



        FP1r = findViewById(R.id.fp1r);FP2r = findViewById(R.id.fp2r);FP3r = findViewById(R.id.fp3r);FP4r = findViewById(R.id.fp4r);FP5r = findViewById(R.id.fp5r);FP6r = findViewById(R.id.fp6r);FP7r = findViewById(R.id.fp7r);
        FP1r2 = findViewById(R.id.fp1r2);FP2r2 = findViewById(R.id.fp2r2);FP3r2 = findViewById(R.id.fp3r2);FP4r2 = findViewById(R.id.fp4r2);FP5r2 = findViewById(R.id.fp5r2);FP6r2 = findViewById(R.id.fp6r2);FP7r2 = findViewById(R.id.fp7r2);
        FP1r3 = findViewById(R.id.fp1r3);FP2r3 = findViewById(R.id.fp2r3);FP3r3 = findViewById(R.id.fp3r3);FP4r3 = findViewById(R.id.fp4r3);FP5r3 = findViewById(R.id.fp5r3);FP6r3 = findViewById(R.id.fp6r3);FP7r3 = findViewById(R.id.fp7r3);
        FP1r4 = findViewById(R.id.fp1r4);FP2r4 = findViewById(R.id.fp2r4);FP3r4 = findViewById(R.id.fp3r4);FP4r4 = findViewById(R.id.fp4r4);FP5r4 = findViewById(R.id.fp5r4);FP6r4 = findViewById(R.id.fp6r4);FP7r4 = findViewById(R.id.fp7r4);
        FP1r5 = findViewById(R.id.fp1r5);FP2r5 = findViewById(R.id.fp2r5);FP3r5 = findViewById(R.id.fp3r5);FP4r5 = findViewById(R.id.fp4r5);FP5r5 = findViewById(R.id.fp5r5);FP6r5 = findViewById(R.id.fp6r5);FP7r5 = findViewById(R.id.fp7r5);
        FP1r6 = findViewById(R.id.fp1r6);FP2r6 = findViewById(R.id.fp2r6);FP3r6 = findViewById(R.id.fp3r6);FP4r6 = findViewById(R.id.fp4r6);FP5r6 = findViewById(R.id.fp5r6);FP6r6 = findViewById(R.id.fp6r6);FP7r6 = findViewById(R.id.fp7r6);

        BD1r = findViewById(R.id.bd1r);BD2r = findViewById(R.id.bd2r);BD3r = findViewById(R.id.bd3r);BD4r = findViewById(R.id.bd4r);BD5r = findViewById(R.id.bd5r);BD6r = findViewById(R.id.bd6r);BD7r = findViewById(R.id.bd7r);
        BD1r2 = findViewById(R.id.bd1r2);BD2r2 = findViewById(R.id.bd2r2);BD3r2 = findViewById(R.id.bd3r2);BD4r2 = findViewById(R.id.bd4r2);BD5r2 = findViewById(R.id.bd5r2);BD6r2 = findViewById(R.id.bd6r2);BD7r2 = findViewById(R.id.bd7r2);
        BD1r3 = findViewById(R.id.bd1r3);BD2r3 = findViewById(R.id.bd2r3);BD3r3 = findViewById(R.id.bd3r3);BD4r3 = findViewById(R.id.bd4r3);BD5r3 = findViewById(R.id.bd5r3);BD6r3 = findViewById(R.id.bd6r3);BD7r3 = findViewById(R.id.bd7r3);
        BD1r4 = findViewById(R.id.bd1r4);BD2r4 = findViewById(R.id.bd2r4);BD3r4 = findViewById(R.id.bd3r4);BD4r4 = findViewById(R.id.bd4r4);BD5r4 = findViewById(R.id.bd5r4);BD6r4 = findViewById(R.id.bd6r4);BD7r4 = findViewById(R.id.bd7r4);
        BD1r5 = findViewById(R.id.bd1r5);BD2r5 = findViewById(R.id.bd2r5);BD3r5 = findViewById(R.id.bd3r5);BD4r5 = findViewById(R.id.bd4r5);BD5r5 = findViewById(R.id.bd5r5);BD6r5 = findViewById(R.id.bd6r5);BD7r5 = findViewById(R.id.bd7r5);
        BD1r6 = findViewById(R.id.bd1r6);BD2r6 = findViewById(R.id.bd2r6);BD3r6 = findViewById(R.id.bd3r6);BD4r6 = findViewById(R.id.bd4r6);BD5r6 = findViewById(R.id.bd5r6);BD6r6 = findViewById(R.id.bd6r6);BD7r6 = findViewById(R.id.bd7r6);

        BY1r = findViewById(R.id.by1r);BY2r = findViewById(R.id.by2r);BY3r = findViewById(R.id.by3r);BY4r = findViewById(R.id.by4r);BY5r = findViewById(R.id.by5r);BY6r = findViewById(R.id.by6r);BY7r = findViewById(R.id.by7r);
        BY1r2 = findViewById(R.id.by1r2);BY2r2 = findViewById(R.id.by2r2);BY3r2 = findViewById(R.id.by3r2);BY4r2 = findViewById(R.id.by4r2);BY5r2 = findViewById(R.id.by5r2);BY6r2 = findViewById(R.id.by6r2);BY7r2 = findViewById(R.id.by7r2);
        BY1r3 = findViewById(R.id.by1r3);BY2r3 = findViewById(R.id.by2r3);BY3r3 = findViewById(R.id.by3r3);BY4r3 = findViewById(R.id.by4r3);BY5r3 = findViewById(R.id.by5r3);BY6r3 = findViewById(R.id.by6r3);BY7r3 = findViewById(R.id.by7r3);
        BY1r4 = findViewById(R.id.by1r4);BY2r4 = findViewById(R.id.by2r4);BY3r4 = findViewById(R.id.by3r4);BY4r4 = findViewById(R.id.by4r4);BY5r4 = findViewById(R.id.by5r4);BY6r4 = findViewById(R.id.by6r4);BY7r4 = findViewById(R.id.by7r4);
        BY1r5 = findViewById(R.id.by1r5);BY2r5 = findViewById(R.id.by2r5);BY3r5 = findViewById(R.id.by3r5);BY4r5 = findViewById(R.id.by4r5);BY5r5 = findViewById(R.id.by5r5);BY6r5 = findViewById(R.id.by6r5);BY7r5 = findViewById(R.id.by7r5);
        BY1r6 = findViewById(R.id.by1r6);BY2r6 = findViewById(R.id.by2r6);BY3r6 = findViewById(R.id.by3r6);BY4r6 = findViewById(R.id.by4r6);BY5r6 = findViewById(R.id.by5r6);BY6r6 = findViewById(R.id.by6r6);BY7r6 = findViewById(R.id.by7r6);

        BN1r = findViewById(R.id.bn1r);BN2r = findViewById(R.id.bn2r);BN3r = findViewById(R.id.bn3r);BN4r = findViewById(R.id.bn4r);BN5r = findViewById(R.id.bn5r);BN6r = findViewById(R.id.bn6r);BN7r = findViewById(R.id.bn7r);
        BN1r2 = findViewById(R.id.bn1r2);BN2r2 = findViewById(R.id.bn2r2);BN3r2 = findViewById(R.id.bn3r2);BN4r2 = findViewById(R.id.bn4r2);BN5r2 = findViewById(R.id.bn5r2);BN6r2 = findViewById(R.id.bn6r2);BN7r2 = findViewById(R.id.bn7r2);
        BN1r3 = findViewById(R.id.bn1r3);BN2r3 = findViewById(R.id.bn2r3);BN3r3 = findViewById(R.id.bn3r3);BN4r3 = findViewById(R.id.bn4r3);BN5r3 = findViewById(R.id.bn5r3);BN6r3 = findViewById(R.id.bn6r3);BN7r3 = findViewById(R.id.bn7r3);
        BN1r4 = findViewById(R.id.bn1r4);BN2r4 = findViewById(R.id.bn2r4);BN3r4 = findViewById(R.id.bn3r4);BN4r4 = findViewById(R.id.bn4r4);BN5r4 = findViewById(R.id.bn5r4);BN6r4 = findViewById(R.id.bn6r4);BN7r4 = findViewById(R.id.bn7r4);
        BN1r5 = findViewById(R.id.bn1r5);BN2r5 = findViewById(R.id.bn2r5);BN3r5 = findViewById(R.id.bn3r5);BN4r5 = findViewById(R.id.bn4r5);BN5r5 = findViewById(R.id.bn5r5);BN6r5 = findViewById(R.id.bn6r5);BN7r5 = findViewById(R.id.bn7r5);
        BN1r6 = findViewById(R.id.bn1r6);BN2r6 = findViewById(R.id.bn2r6);BN3r6 = findViewById(R.id.bn3r6);BN4r6 = findViewById(R.id.bn4r6);BN5r6 = findViewById(R.id.bn5r6);BN6r6 = findViewById(R.id.bn6r6);BN7r6 = findViewById(R.id.bn7r6);

        FY1r = findViewById(R.id.fy1r);FY2r = findViewById(R.id.fy2r);FY3r = findViewById(R.id.fy3r);FY4r = findViewById(R.id.fy4r);FY5r = findViewById(R.id.fy5r);FY6r = findViewById(R.id.fy6r);FY7r = findViewById(R.id.fy7r);
        FY1r2 = findViewById(R.id.fy1r2);FY2r2 = findViewById(R.id.fy2r2);FY3r2 = findViewById(R.id.fy3r2);FY4r2 = findViewById(R.id.fy4r2);FY5r2 = findViewById(R.id.fy5r2);FY6r2 = findViewById(R.id.fy6r2);FY7r2 = findViewById(R.id.fy7r2);
        FY1r3 = findViewById(R.id.fy1r3);FY2r3 = findViewById(R.id.fy2r3);FY3r3 = findViewById(R.id.fy3r3);FY4r3 = findViewById(R.id.fy4r3);FY5r3 = findViewById(R.id.fy5r3);FY6r3 = findViewById(R.id.fy6r3);FY7r3 = findViewById(R.id.fy7r3);
        FY1r4 = findViewById(R.id.fy1r4);FY2r4 = findViewById(R.id.fy2r4);FY3r4 = findViewById(R.id.fy3r4);FY4r4 = findViewById(R.id.fy4r4);FY5r4 = findViewById(R.id.fy5r4);FY6r4 = findViewById(R.id.fy6r4);FY7r4 = findViewById(R.id.fy7r4);
        FY1r5 = findViewById(R.id.fy1r5);FY2r5 = findViewById(R.id.fy2r5);FY3r5 = findViewById(R.id.fy3r5);FY4r5 = findViewById(R.id.fy4r5);FY5r5 = findViewById(R.id.fy5r5);FY6r5 = findViewById(R.id.fy6r5);FY7r5 = findViewById(R.id.fy7r5);
        FY1r6 = findViewById(R.id.fy1r6);FY2r6 = findViewById(R.id.fy2r6);FY3r6 = findViewById(R.id.fy3r6);FY4r6 = findViewById(R.id.fy4r6);FY5r6 = findViewById(R.id.fy5r6);FY6r6 = findViewById(R.id.fy6r6);FY7r6 = findViewById(R.id.fy7r6);

        FN1r = findViewById(R.id.fn1r);FN2r = findViewById(R.id.fn2r);FN3r = findViewById(R.id.fn3r);FN4r = findViewById(R.id.fn4r);FN5r = findViewById(R.id.fn5r);FN6r = findViewById(R.id.fn6r);FN7r = findViewById(R.id.fn7r);
        FN1r2 = findViewById(R.id.fn1r2);FN2r2 = findViewById(R.id.fn2r2);FN3r2 = findViewById(R.id.fn3r2);FN4r2 = findViewById(R.id.fn4r2);FN5r2 = findViewById(R.id.fn5r2);FN6r2 = findViewById(R.id.fn6r2);FN7r2 = findViewById(R.id.fn7r2);
        FN1r3 = findViewById(R.id.fn1r3);FN2r3 = findViewById(R.id.fn2r3);FN3r3 = findViewById(R.id.fn3r3);FN4r3 = findViewById(R.id.fn4r3);FN5r3 = findViewById(R.id.fn5r3);FN6r3 = findViewById(R.id.fn6r3);FN7r3 = findViewById(R.id.fn7r3);
        FN1r4 = findViewById(R.id.fn1r4);FN2r4 = findViewById(R.id.fn2r4);FN3r4 = findViewById(R.id.fn3r4);FN4r4 = findViewById(R.id.fn4r4);FN5r4 = findViewById(R.id.fn5r4);FN6r4 = findViewById(R.id.fn6r4);FN7r4 = findViewById(R.id.fn7r4);
        FN1r5 = findViewById(R.id.fn1r5);FN2r5 = findViewById(R.id.fn2r5);FN3r5 = findViewById(R.id.fn3r5);FN4r5 = findViewById(R.id.fn4r5);FN5r5 = findViewById(R.id.fn5r5);FN6r5 = findViewById(R.id.fn6r5);FN7r5 = findViewById(R.id.fn7r5);
        FN1r6 = findViewById(R.id.fn1r6);FN2r6 = findViewById(R.id.fn2r6);FN3r6 = findViewById(R.id.fn3r6);FN4r6 = findViewById(R.id.fn4r6);FN5r6 = findViewById(R.id.fn5r6);FN6r6 = findViewById(R.id.fn6r6);FN7r6 = findViewById(R.id.fn7r6);



        BDCombr = findViewById(R.id.bdcombr);BDCombr2 = findViewById(R.id.bdcombr2);BDCombr3 = findViewById(R.id.bdcombr3);BDCombr4 = findViewById(R.id.bdcombr4);BDCombr5 = findViewById(R.id.bdcombr5);BDCombr6 = findViewById(R.id.bdcombr6);

        FPCombr2 = findViewById(R.id.fpcombr2);FPCombr3 = findViewById(R.id.fpcombr3);FPCombr4 = findViewById(R.id.fpcombr4);FPCombr5 = findViewById(R.id.fpcombr5);FPCombr6 = findViewById(R.id.fpcombr6);FPCombr = findViewById(R.id.fpcombr);

        BDTotalr = findViewById(R.id.bdtotalr);BDTotalr2 = findViewById(R.id.bdtotalr2);BDTotalr3 = findViewById(R.id.bdtotalr3);BDTotalr4 = findViewById(R.id.bdtotalr4);BDTotalr5 = findViewById(R.id.bdtotalr5);BDTotalr6 = findViewById(R.id.bdtotalr6);

        FPTotalr = findViewById(R.id.fptotalr);FPTotalr2 = findViewById(R.id.fptotalr2);FPTotalr3 = findViewById(R.id.fptotalr3);FPTotalr4 = findViewById(R.id.fptotalr4);FPTotalr5 = findViewById(R.id.fptotalr5);FPTotalr6 = findViewById(R.id.fptotalr6);

        ageSpin= findViewById(R.id.ageCatBoys);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.age_cat_boys, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpin.setAdapter(adapter);
        ageSpin.setOnItemSelectedListener(this);

        typeSpin = findViewById(R.id.mallakhamb_type);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.mallakhamb_type, android.R.layout.select_dialog_item);
        adapter2.setDropDownViewResource(android.R.layout.select_dialog_item);
        typeSpin.setAdapter(adapter2);
        typeSpin.setOnItemSelectedListener(this);
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
                startActivity(new Intent(Judge4.this,MainActivity.class));
            }
        });
        AlertDialog alertDialog = buid.create();
        alertDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.mallakhamb_type) {
            if (parent.getSelectedItemPosition() == 0) {
                Spinner ageSpin = findViewById(R.id.ageCatBoys);
                ageSpin.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Choose the correct Mallakhamb Type", Toast.LENGTH_LONG).show();
                Pole.setVisibility(View.GONE);
                Pole2.setVisibility(View.GONE);
                Pole3.setVisibility(View.GONE);
                Pole4.setVisibility(View.GONE);
                Pole5.setVisibility(View.GONE);
                Pole6.setVisibility(View.GONE);
                Hanging.setVisibility(View.GONE);
                Hanging2.setVisibility(View.GONE);
                Hanging3.setVisibility(View.GONE);
                Hanging4.setVisibility(View.GONE);
                Hanging5.setVisibility(View.GONE);
                Hanging6.setVisibility(View.GONE);
                Rope.setVisibility(View.GONE);
                Rope2.setVisibility(View.GONE);
                Rope3.setVisibility(View.GONE);
                Rope4.setVisibility(View.GONE);
                Rope5.setVisibility(View.GONE);
                Rope6.setVisibility(View.GONE);
            } else if (parent.getSelectedItemPosition() == 1) {
                Spinner ageSpin = findViewById(R.id.ageCatBoys);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.age_cat_boys, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ageSpin.setAdapter(adapter);
                ageSpin.setOnItemSelectedListener(this);

                Pole.setVisibility(View.GONE);
                Pole2.setVisibility(View.GONE);
                Pole3.setVisibility(View.GONE);
                Pole4.setVisibility(View.GONE);
                Pole5.setVisibility(View.GONE);
                Pole6.setVisibility(View.GONE);
                Hanging.setVisibility(View.GONE);
                Hanging2.setVisibility(View.GONE);
                Hanging3.setVisibility(View.GONE);
                Hanging4.setVisibility(View.GONE);
                Hanging5.setVisibility(View.GONE);
                Hanging6.setVisibility(View.GONE);
                Rope.setVisibility(View.GONE);
                Rope2.setVisibility(View.GONE);
                Rope3.setVisibility(View.GONE);
                Rope4.setVisibility(View.GONE);
                Rope5.setVisibility(View.GONE);
                Rope6.setVisibility(View.GONE);

                ageSpin.setVisibility(View.VISIBLE);
                PolePanel.setVisibility(View.VISIBLE);


            } else if (parent.getSelectedItemPosition() == 2) {
                Spinner ageSpin = findViewById(R.id.ageCatBoys);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.age_cat_girls, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ageSpin.setAdapter(adapter);
                ageSpin.setOnItemSelectedListener(this);

                Pole.setVisibility(View.GONE);
                Pole2.setVisibility(View.GONE);
                Pole3.setVisibility(View.GONE);
                Pole4.setVisibility(View.GONE);
                Pole5.setVisibility(View.GONE);
                Pole6.setVisibility(View.GONE);
                Hanging.setVisibility(View.GONE);
                Hanging2.setVisibility(View.GONE);
                Hanging3.setVisibility(View.GONE);
                Hanging4.setVisibility(View.GONE);
                Hanging5.setVisibility(View.GONE);
                Hanging6.setVisibility(View.GONE);
                Rope.setVisibility(View.GONE);
                Rope2.setVisibility(View.GONE);
                Rope3.setVisibility(View.GONE);
                Rope4.setVisibility(View.GONE);
                Rope5.setVisibility(View.GONE);
                Rope6.setVisibility(View.GONE);

                ageSpin.setVisibility(View.VISIBLE);
                RopePanel.setVisibility(View.VISIBLE);



            } else if (parent.getSelectedItemPosition() == 3) {
                Spinner ageSpin = findViewById(R.id.ageCatBoys);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hanging_age, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ageSpin.setAdapter(adapter);
                ageSpin.setOnItemSelectedListener(this);

                Pole.setVisibility(View.GONE);
                Pole2.setVisibility(View.GONE);
                Pole3.setVisibility(View.GONE);
                Pole4.setVisibility(View.GONE);
                Pole5.setVisibility(View.GONE);
                Pole6.setVisibility(View.GONE);
                Hanging.setVisibility(View.GONE);
                Hanging2.setVisibility(View.GONE);
                Hanging3.setVisibility(View.GONE);
                Hanging4.setVisibility(View.GONE);
                Hanging5.setVisibility(View.GONE);
                Hanging6.setVisibility(View.GONE);
                Rope.setVisibility(View.GONE);
                Rope2.setVisibility(View.GONE);
                Rope3.setVisibility(View.GONE);
                Rope4.setVisibility(View.GONE);
                Rope5.setVisibility(View.GONE);
                Rope6.setVisibility(View.GONE);

                ageSpin.setVisibility(View.VISIBLE);
                HangingPanel.setVisibility(View.VISIBLE);

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

    public void rbClick(View v) {


        float t;
        switch (v.getId()) {
//POLE MALAKHAMB
            case R.id.bn1:
                if (!BY1.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb.setText(l.subtract(m).toString());
                    BY1.setEnabled(true);
                }
                BN1.setEnabled(false);
                break;
            case R.id.bn6:
                if (!BY6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb.setText(l.subtract(m).toString());
                    BY6.setEnabled(true);
                }
                BN6.setEnabled(false);
                break;
            case R.id.bn7:
                if (!BY7.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb.setText(l.subtract(m).toString());
                    BY7.setEnabled(true);
                }
                BN7.setEnabled(false);
                break;
            case R.id.bn10:
                if (!BY10.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb.setText(l.subtract(m).toString());
                    BY10.setEnabled(true);
                }
                BN10.setEnabled(false);
                break;
            case R.id.bn2:
                if (!BY2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb.setText(l.subtract(m).toString());
                    BY2.setEnabled(true);
                }
                BN2.setEnabled(false);
                break;
            case R.id.bn3:
                if (!BY3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb.setText(l.subtract(m).toString());
                    BY3.setEnabled(true);
                }
                BN3.setEnabled(false);
                break;
            case R.id.bn4:
                if (!BY4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb.setText(l.subtract(m).toString());
                    BY4.setEnabled(true);
                }
                BN4.setEnabled(false);
                break;
            case R.id.bn5:
                if (!BY5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb.setText(l.subtract(m).toString());
                    BY5.setEnabled(true);
                }
                BN5.setEnabled(false);
                break;
            case R.id.bn8:
                if (!BY8.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb.setText(l.subtract(m).toString());
                    BY8.setEnabled(true);
                }
                BN8.setEnabled(false);
                break;
            case R.id.bn9:
                if (!BY9.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb.setText(l.subtract(m).toString());
                    BY9.setEnabled(true);
                }
                BN9.setEnabled(false);
                break;

            case R.id.fn1:
                if (!FY1.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb.setText(l.subtract(m).toString());
                    FY1.setEnabled(true);
                }
                FN1.setEnabled(false);
                break;
            case R.id.fn6:
                if (!FY6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb.setText(l.subtract(m).toString());
                    FY6.setEnabled(true);
                }
                FN6.setEnabled(false);
                break;
            case R.id.fn7:
                if (!FY7.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb.setText(l.subtract(m).toString());
                    FY7.setEnabled(true);
                }
                FN7.setEnabled(false);
                break;
            case R.id.fn10:
                if (!FY10.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb.setText(l.subtract(m).toString());
                    FY10.setEnabled(true);
                }
                FN10.setEnabled(false);
                break;
            case R.id.fn2:
                if (!FY2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb.setText(l.subtract(m).toString());
                    FY2.setEnabled(true);
                }
                FN2.setEnabled(false);
                break;
            case R.id.fn3:
                if (!FY3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb.setText(l.subtract(m).toString());
                    FY3.setEnabled(true);
                }
                FN3.setEnabled(false);
                break;
            case R.id.fn4:
                if (!FY4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb.setText(l.subtract(m).toString());
                    FY4.setEnabled(true);
                }
                FN4.setEnabled(false);
                break;
            case R.id.fn5:
                if (!FY5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb.setText(l.subtract(m).toString());
                    FY5.setEnabled(true);
                }
                FN5.setEnabled(false);
                break;
            case R.id.fn8:
                if (!FY8.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb.setText(l.subtract(m).toString());
                    FY8.setEnabled(true);
                }
                FN8.setEnabled(false);
                break;
            case R.id.fn9:
                if (!FY9.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb.setText(l.subtract(m).toString());
                    FY9.setEnabled(true);
                }
                FN9.setEnabled(false);
                break;

            case R.id.by1:
                if (BY1.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb.setText(l.add(m).toString());
                    BN1.setEnabled(true);
                }
                BY1.setEnabled(false);
                break;
//
            case R.id.by6:
                if (BY6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb.setText(l.add(m).toString());
                    BN6.setEnabled(true);
                }
                BY6.setEnabled(false);
                break;
            case R.id.by7:
                if (BY7.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb.setText(l.add(m).toString());
                    BN7.setEnabled(true);
                }
                BY7.setEnabled(false);
                break;
            case R.id.by10:
                if (BY10.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb.setText(l.add(m).toString());
                    BN10.setEnabled(true);
                }
                BY10.setEnabled(false);
                break;
            case R.id.by2:
                if (BY2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb.setText(l.add(m).toString());
                    BN2.setEnabled(true);
                }
                BY2.setEnabled(false);
                break;
            case R.id.by3:
                if (BY3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb.setText(l.add(m).toString());
                    BN3.setEnabled(true);
                }
                BY3.setEnabled(false);
                break;
            case R.id.by4:
                if (BY4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb.setText(l.add(m).toString());
                    BN4.setEnabled(true);
                }
                BY4.setEnabled(false);
                break;
            case R.id.by5:
                if (BY5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb.setText(l.add(m).toString());
                    BN5.setEnabled(true);
                }
                BY5.setEnabled(false);
                break;
            case R.id.by8:
                if (BY8.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb.setText(l.add(m).toString());
                    BN8.setEnabled(true);
                }
                BY8.setEnabled(false);
                break;
            case R.id.by9:
                if (BY9.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb.setText(l.add(m).toString());
                    BN9.setEnabled(true);
                }
                BY9.setEnabled(false);
                break;

            case R.id.fy1:
                if (FY1.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb.setText(l.add(m).toString());
                    FN1.setEnabled(true);
                }
                FY1.setEnabled(false);
                break;
            case R.id.fy6:
                if (FY6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb.setText(l.add(m).toString());
                    FN6.setEnabled(true);
                }
                FY6.setEnabled(false);
                break;
            case R.id.fy7:
                if (FY7.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb.setText(l.add(m).toString());
                    FN7.setEnabled(true);
                }
                FY7.setEnabled(false);
                break;
            case R.id.fy10:
                if (FY10.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb.setText(l.add(m).toString());
                    FN10.setEnabled(true);
                }
                FY10.setEnabled(false);
                break;
            case R.id.fy2:
                if (FY2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb.setText(l.add(m).toString());
                    FN2.setEnabled(true);
                }
                FY2.setEnabled(false);
                break;
            case R.id.fy3:
                if (FY3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb.setText(l.add(m).toString());
                    FN3.setEnabled(true);
                }
                FY3.setEnabled(false);
                break;
            case R.id.fy4:
                if (FY4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb.setText(l.add(m).toString());
                    FN4.setEnabled(true);
                }
                FY4.setEnabled(false);
                break;
            case R.id.fy5:
                if (FY5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb.setText(l.add(m).toString());
                    FN5.setEnabled(true);
                }
                FY5.setEnabled(false);
                break;
            case R.id.fy8:
                if (FY8.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb.setText(l.add(m).toString());
                    FN8.setEnabled(true);
                }
                FY8.setEnabled(false);
                break;
            case R.id.fy9:
                if (FY9.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb.setText(l.add(m).toString());
                    FN9.setEnabled(true);
                }
                FY9.setEnabled(false);
                break;
// player 2
            case R.id.bn12:
                if (!BY12.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb2.setText(l.subtract(m).toString());
                    BY12.setEnabled(true);
                }
                BN12.setEnabled(false);
                break;
            case R.id.bn62:
                if (!BY62.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb2.setText(l.subtract(m).toString());
                    BY62.setEnabled(true);
                }
                BN62.setEnabled(false);
                break;
            case R.id.bn72:
                if (!BY72.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb2.setText(l.subtract(m).toString());
                    BY72.setEnabled(true);
                }
                BN72.setEnabled(false);
                break;
            case R.id.bn102:
                if (!BY102.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb2.setText(l.subtract(m).toString());
                    BY102.setEnabled(true);
                }
                BN102.setEnabled(false);
                break;
            case R.id.bn22:
                if (!BY22.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb2.setText(l.subtract(m).toString());
                    BY22.setEnabled(true);
                }
                BN22.setEnabled(false);
                break;
            case R.id.bn32:
                if (!BY32.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb2.setText(l.subtract(m).toString());
                    BY32.setEnabled(true);
                }
                BN32.setEnabled(false);
                break;
            case R.id.bn42:
                if (!BY42.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb2.setText(l.subtract(m).toString());
                    BY42.setEnabled(true);
                }
                BN42.setEnabled(false);
                break;
            case R.id.bn52:
                if (!BY52.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb2.setText(l.subtract(m).toString());
                    BY52.setEnabled(true);
                }
                BN52.setEnabled(false);
                break;
            case R.id.bn82:
                if (!BY82.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb2.setText(l.subtract(m).toString());
                    BY82.setEnabled(true);
                }
                BN82.setEnabled(false);
                break;
            case R.id.bn92:
                if (!BY92.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb2.setText(l.subtract(m).toString());
                    BY92.setEnabled(true);
                }
                BN92.setEnabled(false);
                break;

            case R.id.fn12:
                if (!FY12.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb2.setText(l.subtract(m).toString());
                    FY12.setEnabled(true);
                }
                FN12.setEnabled(false);
                break;
            case R.id.fn62:
                if (!FY62.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb2.setText(l.subtract(m).toString());
                    FY62.setEnabled(true);
                }
                FN62.setEnabled(false);
                break;
            case R.id.fn72:
                if (!FY72.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb2.setText(l.subtract(m).toString());
                    FY72.setEnabled(true);
                }
                FN72.setEnabled(false);
                break;
            case R.id.fn102:
                if (!FY102.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb2.setText(l.subtract(m).toString());
                    FY102.setEnabled(true);
                }
                FN102.setEnabled(false);
                break;
            case R.id.fn22:
                if (!FY22.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb2.setText(l.subtract(m).toString());
                    FY22.setEnabled(true);
                }
                FN22.setEnabled(false);
                break;
            case R.id.fn32:
                if (!FY32.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb2.setText(l.subtract(m).toString());
                    FY32.setEnabled(true);
                }
                FN32.setEnabled(false);
                break;
            case R.id.fn42:
                if (!FY42.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb2.setText(l.subtract(m).toString());
                    FY42.setEnabled(true);
                }
                FN42.setEnabled(false);
                break;
            case R.id.fn52:
                if (!FY52.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb2.setText(l.subtract(m).toString());
                    FY52.setEnabled(true);
                }
                FN52.setEnabled(false);
                break;
            case R.id.fn82:
                if (!FY82.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb2.setText(l.subtract(m).toString());
                    FY82.setEnabled(true);
                }
                FN82.setEnabled(false);
                break;
            case R.id.fn92:
                if (!FY92.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb2.setText(l.subtract(m).toString());
                    FY92.setEnabled(true);
                }
                FN92.setEnabled(false);
                break;

            case R.id.by12:
                if (BY12.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb2.setText(l.add(m).toString());
                    BN12.setEnabled(true);
                }
                BY12.setEnabled(false);
                break;
//
            case R.id.by62:
                if (BY62.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb2.setText(l.add(m).toString());
                    BN62.setEnabled(true);
                }
                BY62.setEnabled(false);
                break;
            case R.id.by72:
                if (BY72.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb2.setText(l.add(m).toString());
                    BN72.setEnabled(true);
                }
                BY72.setEnabled(false);
                break;
            case R.id.by102:
                if (BY102.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb2.setText(l.add(m).toString());
                    BN102.setEnabled(true);
                }
                BY102.setEnabled(false);
                break;
            case R.id.by22:
                if (BY22.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb2.setText(l.add(m).toString());
                    BN22.setEnabled(true);
                }
                BY22.setEnabled(false);
                break;
            case R.id.by32:
                if (BY32.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb2.setText(l.add(m).toString());
                    BN32.setEnabled(true);
                }
                BY32.setEnabled(false);
                break;
            case R.id.by42:
                if (BY42.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb2.setText(l.add(m).toString());
                    BN42.setEnabled(true);
                }
                BY42.setEnabled(false);
                break;
            case R.id.by52:
                if (BY52.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb2.setText(l.add(m).toString());
                    BN52.setEnabled(true);
                }
                BY52.setEnabled(false);
                break;
            case R.id.by82:
                if (BY82.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb2.setText(l.add(m).toString());
                    BN82.setEnabled(true);
                }
                BY82.setEnabled(false);
                break;
            case R.id.by92:
                if (BY92.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb2.setText(l.add(m).toString());
                    BN92.setEnabled(true);
                }
                BY92.setEnabled(false);
                break;

            case R.id.fy12:
                if (FY12.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb2.setText(l.add(m).toString());
                    FN12.setEnabled(true);
                }
                FY12.setEnabled(false);
                break;
            case R.id.fy62:
                if (FY62.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb2.setText(l.add(m).toString());
                    FN62.setEnabled(true);
                }
                FY62.setEnabled(false);
                break;
            case R.id.fy72:
                if (FY72.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb2.setText(l.add(m).toString());
                    FN72.setEnabled(true);
                }
                FY72.setEnabled(false);
                break;
            case R.id.fy102:
                if (FY102.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb2.setText(l.add(m).toString());
                    FN102.setEnabled(true);
                }
                FY102.setEnabled(false);
                break;
            case R.id.fy22:
                if (FY22.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb2.setText(l.add(m).toString());
                    FN22.setEnabled(true);
                }
                FY22.setEnabled(false);
                break;
            case R.id.fy32:
                if (FY32.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb2.setText(l.add(m).toString());
                    FN32.setEnabled(true);
                }
                FY32.setEnabled(false);
                break;
            case R.id.fy42:
                if (FY42.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb2.setText(l.add(m).toString());
                    FN42.setEnabled(true);
                }
                FY42.setEnabled(false);
                break;
            case R.id.fy52:
                if (FY52.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb2.setText(l.add(m).toString());
                    FN52.setEnabled(true);
                }
                FY52.setEnabled(false);
                break;
            case R.id.fy82:
                if (FY82.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb2.setText(l.add(m).toString());
                    FN82.setEnabled(true);
                }
                FY82.setEnabled(false);
                break;
            case R.id.fy92:
                if (FY92.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb2.setText(l.add(m).toString());
                    FN92.setEnabled(true);
                }
                FY92.setEnabled(false);
                break;

            //player 3

            case R.id.bn13:
                if (!BY13.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb3.setText(l.subtract(m).toString());
                    BY13.setEnabled(true);
                }
                BN13.setEnabled(false);
                break;
            case R.id.bn63:
                if (!BY63.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb3.setText(l.subtract(m).toString());
                    BY63.setEnabled(true);
                }
                BN63.setEnabled(false);
                break;
            case R.id.bn73:
                if (!BY73.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb3.setText(l.subtract(m).toString());
                    BY73.setEnabled(true);
                }
                BN73.setEnabled(false);
                break;
            case R.id.bn103:
                if (!BY103.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb3.setText(l.subtract(m).toString());
                    BY103.setEnabled(true);
                }
                BN103.setEnabled(false);
                break;
            case R.id.bn23:
                if (!BY23.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb3.setText(l.subtract(m).toString());
                    BY23.setEnabled(true);
                }
                BN23.setEnabled(false);
                break;
            case R.id.bn33:
                if (!BY33.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb3.setText(l.subtract(m).toString());
                    BY33.setEnabled(true);
                }
                BN33.setEnabled(false);
                break;
            case R.id.bn43:
                if (!BY43.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb3.setText(l.subtract(m).toString());
                    BY43.setEnabled(true);
                }
                BN43.setEnabled(false);
                break;
            case R.id.bn53:
                if (!BY53.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb3.setText(l.subtract(m).toString());
                    BY53.setEnabled(true);
                }
                BN53.setEnabled(false);
                break;
            case R.id.bn83:
                if (!BY83.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb3.setText(l.subtract(m).toString());
                    BY83.setEnabled(true);
                }
                BN83.setEnabled(false);
                break;
            case R.id.bn93:
                if (!BY93.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb3.setText(l.subtract(m).toString());
                    BY93.setEnabled(true);
                }
                BN93.setEnabled(false);
                break;

            case R.id.fn13:
                if (!FY13.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb3.setText(l.subtract(m).toString());
                    FY13.setEnabled(true);
                }
                FN13.setEnabled(false);
                break;
            case R.id.fn63:
                if (!FY63.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb3.setText(l.subtract(m).toString());
                    FY63.setEnabled(true);
                }
                FN63.setEnabled(false);
                break;
            case R.id.fn73:
                if (!FY73.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb3.setText(l.subtract(m).toString());
                    FY73.setEnabled(true);
                }
                FN73.setEnabled(false);
                break;
            case R.id.fn103:
                if (!FY103.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb3.setText(l.subtract(m).toString());
                    FY103.setEnabled(true);
                }
                FN103.setEnabled(false);
                break;
            case R.id.fn23:
                if (!FY23.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb3.setText(l.subtract(m).toString());
                    FY23.setEnabled(true);
                }
                FN23.setEnabled(false);
                break;
            case R.id.fn33:
                if (!FY33.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb3.setText(l.subtract(m).toString());
                    FY33.setEnabled(true);
                }
                FN33.setEnabled(false);
                break;
            case R.id.fn43:
                if (!FY43.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb3.setText(l.subtract(m).toString());
                    FY43.setEnabled(true);
                }
                FN43.setEnabled(false);
                break;
            case R.id.fn53:
                if (!FY53.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb3.setText(l.subtract(m).toString());
                    FY53.setEnabled(true);
                }
                FN53.setEnabled(false);
                break;
            case R.id.fn83:
                if (!FY83.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb3.setText(l.subtract(m).toString());
                    FY83.setEnabled(true);
                }
                FN83.setEnabled(false);
                break;
            case R.id.fn93:
                if (!FY93.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb3.setText(l.subtract(m).toString());
                    FY93.setEnabled(true);
                }
                FN93.setEnabled(false);
                break;

            case R.id.by13:
                if (BY13.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb3.setText(l.add(m).toString());
                    BN13.setEnabled(true);
                }
                BY13.setEnabled(false);
                break;
//
            case R.id.by63:
                if (BY63.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb3.setText(l.add(m).toString());
                    BN63.setEnabled(true);
                }
                BY63.setEnabled(false);
                break;
            case R.id.by73:
                if (BY73.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb3.setText(l.add(m).toString());
                    BN73.setEnabled(true);
                }
                BY73.setEnabled(false);
                break;
            case R.id.by103:
                if (BY103.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb3.setText(l.add(m).toString());
                    BN103.setEnabled(true);
                }
                BY103.setEnabled(false);
                break;
            case R.id.by23:
                if (BY23.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb3.setText(l.add(m).toString());
                    BN23.setEnabled(true);
                }
                BY23.setEnabled(false);
                break;
            case R.id.by33:
                if (BY33.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb3.setText(l.add(m).toString());
                    BN33.setEnabled(true);
                }
                BY33.setEnabled(false);
                break;
            case R.id.by43:
                if (BY43.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb3.setText(l.add(m).toString());
                    BN43.setEnabled(true);
                }
                BY43.setEnabled(false);
                break;
            case R.id.by53:
                if (BY53.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb3.setText(l.add(m).toString());
                    BN53.setEnabled(true);
                }
                BY53.setEnabled(false);
                break;
            case R.id.by83:
                if (BY83.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb3.setText(l.add(m).toString());
                    BN83.setEnabled(true);
                }
                BY83.setEnabled(false);
                break;
            case R.id.by93:
                if (BY93.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb3.setText(l.add(m).toString());
                    BN93.setEnabled(true);
                }
                BY93.setEnabled(false);
                break;

            case R.id.fy13:
                if (FY13.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb3.setText(l.add(m).toString());
                    FN13.setEnabled(true);
                }
                FY13.setEnabled(false);
                break;
            case R.id.fy63:
                if (FY63.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb3.setText(l.add(m).toString());
                    FN63.setEnabled(true);
                }
                FY63.setEnabled(false);
                break;
            case R.id.fy73:
                if (FY73.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb3.setText(l.add(m).toString());
                    FN73.setEnabled(true);
                }
                FY73.setEnabled(false);
                break;
            case R.id.fy103:
                if (FY103.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb3.setText(l.add(m).toString());
                    FN103.setEnabled(true);
                }
                FY103.setEnabled(false);
                break;
            case R.id.fy23:
                if (FY23.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb3.setText(l.add(m).toString());
                    FN23.setEnabled(true);
                }
                FY23.setEnabled(false);
                break;
            case R.id.fy33:
                if (FY33.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb3.setText(l.add(m).toString());
                    FN33.setEnabled(true);
                }
                FY33.setEnabled(false);
                break;
            case R.id.fy43:
                if (FY43.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb3.setText(l.add(m).toString());
                    FN43.setEnabled(true);
                }
                FY43.setEnabled(false);
                break;
            case R.id.fy53:
                if (FY53.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb3.setText(l.add(m).toString());
                    FN53.setEnabled(true);
                }
                FY53.setEnabled(false);
                break;
            case R.id.fy83:
                if (FY83.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb3.setText(l.add(m).toString());
                    FN83.setEnabled(true);
                }
                FY83.setEnabled(false);
                break;
            case R.id.fy93:
                if (FY93.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb3.setText(l.add(m).toString());
                    FN93.setEnabled(true);
                }
                FY93.setEnabled(false);
                break;


//player 4
            case R.id.bn14:
                if (!BY14.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb4.setText(l.subtract(m).toString());
                    BY14.setEnabled(true);
                }
                BN14.setEnabled(false);
                break;
            case R.id.bn64:
                if (!BY64.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb4.setText(l.subtract(m).toString());
                    BY64.setEnabled(true);
                }
                BN64.setEnabled(false);
                break;
            case R.id.bn74:
                if (!BY74.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb4.setText(l.subtract(m).toString());
                    BY74.setEnabled(true);
                }
                BN74.setEnabled(false);
                break;
            case R.id.bn104:
                if (!BY104.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb4.setText(l.subtract(m).toString());
                    BY104.setEnabled(true);
                }
                BN104.setEnabled(false);
                break;
            case R.id.bn24:
                if (!BY24.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb4.setText(l.subtract(m).toString());
                    BY24.setEnabled(true);
                }
                BN24.setEnabled(false);
                break;
            case R.id.bn34:
                if (!BY34.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb4.setText(l.subtract(m).toString());
                    BY34.setEnabled(true);
                }
                BN34.setEnabled(false);
                break;
            case R.id.bn44:
                if (!BY44.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb4.setText(l.subtract(m).toString());
                    BY44.setEnabled(true);
                }
                BN44.setEnabled(false);
                break;
            case R.id.bn54:
                if (!BY54.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb4.setText(l.subtract(m).toString());
                    BY54.setEnabled(true);
                }
                BN54.setEnabled(false);
                break;
            case R.id.bn84:
                if (!BY84.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb4.setText(l.subtract(m).toString());
                    BY84.setEnabled(true);
                }
                BN84.setEnabled(false);
                break;
            case R.id.bn94:
                if (!BY94.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb4.setText(l.subtract(m).toString());
                    BY94.setEnabled(true);
                }
                BN94.setEnabled(false);
                break;

            case R.id.fn14:
                if (!FY14.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb4.setText(l.subtract(m).toString());
                    FY14.setEnabled(true);
                }
                FN14.setEnabled(false);
                break;
            case R.id.fn64:
                if (!FY64.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb4.setText(l.subtract(m).toString());
                    FY64.setEnabled(true);
                }
                FN64.setEnabled(false);
                break;
            case R.id.fn74:
                if (!FY74.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb4.setText(l.subtract(m).toString());
                    FY74.setEnabled(true);
                }
                FN74.setEnabled(false);
                break;
            case R.id.fn104:
                if (!FY104.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb4.setText(l.subtract(m).toString());
                    FY104.setEnabled(true);
                }
                FN104.setEnabled(false);
                break;
            case R.id.fn24:
                if (!FY24.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb4.setText(l.subtract(m).toString());
                    FY24.setEnabled(true);
                }
                FN24.setEnabled(false);
                break;
            case R.id.fn34:
                if (!FY34.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb4.setText(l.subtract(m).toString());
                    FY34.setEnabled(true);
                }
                FN34.setEnabled(false);
                break;
            case R.id.fn44:
                if (!FY44.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb4.setText(l.subtract(m).toString());
                    FY44.setEnabled(true);
                }
                FN44.setEnabled(false);
                break;
            case R.id.fn54:
                if (!FY54.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb4.setText(l.subtract(m).toString());
                    FY54.setEnabled(true);
                }
                FN54.setEnabled(false);
                break;
            case R.id.fn84:
                if (!FY84.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb4.setText(l.subtract(m).toString());
                    FY84.setEnabled(true);
                }
                FN84.setEnabled(false);
                break;
            case R.id.fn94:
                if (!FY94.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb4.setText(l.subtract(m).toString());
                    FY94.setEnabled(true);
                }
                FN94.setEnabled(false);
                break;

            case R.id.by14:
                if (BY14.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb4.setText(l.add(m).toString());
                    BN14.setEnabled(true);
                }
                BY14.setEnabled(false);
                break;
//
            case R.id.by64:
                if (BY64.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb4.setText(l.add(m).toString());
                    BN64.setEnabled(true);
                }
                BY64.setEnabled(false);
                break;
            case R.id.by74:
                if (BY74.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb4.setText(l.add(m).toString());
                    BN74.setEnabled(true);
                }
                BY74.setEnabled(false);
                break;
            case R.id.by104:
                if (BY104.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb4.setText(l.add(m).toString());
                    BN104.setEnabled(true);
                }
                BY104.setEnabled(false);
                break;
            case R.id.by24:
                if (BY24.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb4.setText(l.add(m).toString());
                    BN24.setEnabled(true);
                }
                BY24.setEnabled(false);
                break;
            case R.id.by34:
                if (BY34.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb4.setText(l.add(m).toString());
                    BN34.setEnabled(true);
                }
                BY34.setEnabled(false);
                break;
            case R.id.by44:
                if (BY44.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb4.setText(l.add(m).toString());
                    BN44.setEnabled(true);
                }
                BY44.setEnabled(false);
                break;
            case R.id.by54:
                if (BY54.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb4.setText(l.add(m).toString());
                    BN54.setEnabled(true);
                }
                BY54.setEnabled(false);
                break;
            case R.id.by84:
                if (BY84.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb4.setText(l.add(m).toString());
                    BN84.setEnabled(true);
                }
                BY84.setEnabled(false);
                break;
            case R.id.by94:
                if (BY94.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb4.setText(l.add(m).toString());
                    BN94.setEnabled(true);
                }
                BY94.setEnabled(false);
                break;

            case R.id.fy14:
                if (FY14.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb4.setText(l.add(m).toString());
                    FN14.setEnabled(true);
                }
                FY14.setEnabled(false);
                break;
            case R.id.fy64:
                if (FY64.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb4.setText(l.add(m).toString());
                    FN64.setEnabled(true);
                }
                FY64.setEnabled(false);
                break;
            case R.id.fy74:
                if (FY74.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb4.setText(l.add(m).toString());
                    FN74.setEnabled(true);
                }
                FY74.setEnabled(false);
                break;
            case R.id.fy104:
                if (FY104.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb4.setText(l.add(m).toString());
                    FN104.setEnabled(true);
                }
                FY104.setEnabled(false);
                break;
            case R.id.fy24:
                if (FY24.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb4.setText(l.add(m).toString());
                    FN24.setEnabled(true);
                }
                FY24.setEnabled(false);
                break;
            case R.id.fy34:
                if (FY34.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb4.setText(l.add(m).toString());
                    FN34.setEnabled(true);
                }
                FY34.setEnabled(false);
                break;
            case R.id.fy44:
                if (FY44.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb4.setText(l.add(m).toString());
                    FN44.setEnabled(true);
                }
                FY44.setEnabled(false);
                break;
            case R.id.fy54:
                if (FY54.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb4.setText(l.add(m).toString());
                    FN54.setEnabled(true);
                }
                FY54.setEnabled(false);
                break;
            case R.id.fy84:
                if (FY84.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb4.setText(l.add(m).toString());
                    FN84.setEnabled(true);
                }
                FY84.setEnabled(false);
                break;
            case R.id.fy94:
                if (FY94.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb4.setText(l.add(m).toString());
                    FN94.setEnabled(true);
                }
                FY94.setEnabled(false);
                break;

//player 5
            case R.id.bn15:
                if (!BY15.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb5.setText(l.subtract(m).toString());
                    BY15.setEnabled(true);
                }
                BN15.setEnabled(false);
                break;
            case R.id.bn65:
                if (!BY65.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb5.setText(l.subtract(m).toString());
                    BY65.setEnabled(true);
                }
                BN65.setEnabled(false);
                break;
            case R.id.bn75:
                if (!BY75.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb5.setText(l.subtract(m).toString());
                    BY75.setEnabled(true);
                }
                BN75.setEnabled(false);
                break;
            case R.id.bn105:
                if (!BY105.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb5.setText(l.subtract(m).toString());
                    BY105.setEnabled(true);
                }
                BN105.setEnabled(false);
                break;
            case R.id.bn25:
                if (!BY25.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb5.setText(l.subtract(m).toString());
                    BY25.setEnabled(true);
                }
                BN25.setEnabled(false);
                break;
            case R.id.bn35:
                if (!BY35.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb5.setText(l.subtract(m).toString());
                    BY35.setEnabled(true);
                }
                BN35.setEnabled(false);
                break;
            case R.id.bn45:
                if (!BY45.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb5.setText(l.subtract(m).toString());
                    BY45.setEnabled(true);
                }
                BN45.setEnabled(false);
                break;
            case R.id.bn55:
                if (!BY55.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb5.setText(l.subtract(m).toString());
                    BY55.setEnabled(true);
                }
                BN55.setEnabled(false);
                break;
            case R.id.bn85:
                if (!BY85.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb5.setText(l.subtract(m).toString());
                    BY85.setEnabled(true);
                }
                BN85.setEnabled(false);
                break;
            case R.id.bn95:
                if (!BY95.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb5.setText(l.subtract(m).toString());
                    BY95.setEnabled(true);
                }
                BN95.setEnabled(false);
                break;

            case R.id.fn15:
                if (!FY15.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb5.setText(l.subtract(m).toString());
                    FY15.setEnabled(true);
                }
                FN15.setEnabled(false);
                break;
            case R.id.fn65:
                if (!FY65.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb5.setText(l.subtract(m).toString());
                    FY65.setEnabled(true);
                }
                FN65.setEnabled(false);
                break;
            case R.id.fn75:
                if (!FY75.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb5.setText(l.subtract(m).toString());
                    FY75.setEnabled(true);
                }
                FN75.setEnabled(false);
                break;
            case R.id.fn105:
                if (!FY105.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb5.setText(l.subtract(m).toString());
                    FY105.setEnabled(true);
                }
                FN105.setEnabled(false);
                break;
            case R.id.fn25:
                if (!FY25.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb5.setText(l.subtract(m).toString());
                    FY25.setEnabled(true);
                }
                FN25.setEnabled(false);
                break;
            case R.id.fn35:
                if (!FY35.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb5.setText(l.subtract(m).toString());
                    FY35.setEnabled(true);
                }
                FN35.setEnabled(false);
                break;
            case R.id.fn45:
                if (!FY45.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb5.setText(l.subtract(m).toString());
                    FY45.setEnabled(true);
                }
                FN45.setEnabled(false);
                break;
            case R.id.fn55:
                if (!FY55.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb5.setText(l.subtract(m).toString());
                    FY55.setEnabled(true);
                }
                FN55.setEnabled(false);
                break;
            case R.id.fn85:
                if (!FY85.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb5.setText(l.subtract(m).toString());
                    FY85.setEnabled(true);
                }
                FN85.setEnabled(false);
                break;
            case R.id.fn95:
                if (!FY95.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb5.setText(l.subtract(m).toString());
                    FY95.setEnabled(true);
                }
                FN95.setEnabled(false);
                break;

            case R.id.by15:
                if (BY15.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb5.setText(l.add(m).toString());
                    BN15.setEnabled(true);
                }
                BY15.setEnabled(false);
                break;
//
            case R.id.by65:
                if (BY65.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb5.setText(l.add(m).toString());
                    BN65.setEnabled(true);
                }
                BY65.setEnabled(false);
                break;
            case R.id.by75:
                if (BY75.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb5.setText(l.add(m).toString());
                    BN75.setEnabled(true);
                }
                BY75.setEnabled(false);
                break;
            case R.id.by105:
                if (BY105.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb5.setText(l.add(m).toString());
                    BN105.setEnabled(true);
                }
                BY105.setEnabled(false);
                break;
            case R.id.by25:
                if (BY25.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb5.setText(l.add(m).toString());
                    BN25.setEnabled(true);
                }
                BY25.setEnabled(false);
                break;
            case R.id.by35:
                if (BY35.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb5.setText(l.add(m).toString());
                    BN35.setEnabled(true);
                }
                BY35.setEnabled(false);
                break;
            case R.id.by45:
                if (BY45.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb5.setText(l.add(m).toString());
                    BN45.setEnabled(true);
                }
                BY45.setEnabled(false);
                break;
            case R.id.by55:
                if (BY55.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb5.setText(l.add(m).toString());
                    BN55.setEnabled(true);
                }
                BY55.setEnabled(false);
                break;
            case R.id.by85:
                if (BY85.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb5.setText(l.add(m).toString());
                    BN85.setEnabled(true);
                }
                BY85.setEnabled(false);
                break;
            case R.id.by95:
                if (BY95.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb5.setText(l.add(m).toString());
                    BN95.setEnabled(true);
                }
                BY95.setEnabled(false);
                break;

            case R.id.fy15:
                if (FY15.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb5.setText(l.add(m).toString());
                    FN15.setEnabled(true);
                }
                FY15.setEnabled(false);
                break;
            case R.id.fy65:
                if (FY65.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb5.setText(l.add(m).toString());
                    FN65.setEnabled(true);
                }
                FY65.setEnabled(false);
                break;
            case R.id.fy75:
                if (FY75.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb5.setText(l.add(m).toString());
                    FN75.setEnabled(true);
                }
                FY75.setEnabled(false);
                break;
            case R.id.fy105:
                if (FY105.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb5.setText(l.add(m).toString());
                    FN105.setEnabled(true);
                }
                FY105.setEnabled(false);
                break;
            case R.id.fy25:
                if (FY25.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb5.setText(l.add(m).toString());
                    FN25.setEnabled(true);
                }
                FY25.setEnabled(false);
                break;
            case R.id.fy35:
                if (FY35.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb5.setText(l.add(m).toString());
                    FN35.setEnabled(true);
                }
                FY35.setEnabled(false);
                break;
            case R.id.fy45:
                if (FY45.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb5.setText(l.add(m).toString());
                    FN45.setEnabled(true);
                }
                FY45.setEnabled(false);
                break;
            case R.id.fy55:
                if (FY55.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb5.setText(l.add(m).toString());
                    FN55.setEnabled(true);
                }
                FY55.setEnabled(false);
                break;
            case R.id.fy85:
                if (FY85.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb5.setText(l.add(m).toString());
                    FN85.setEnabled(true);
                }
                FY85.setEnabled(false);
                break;
            case R.id.fy95:
                if (FY95.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb5.setText(l.add(m).toString());
                    FN95.setEnabled(true);
                }
                FY95.setEnabled(false);
                break;

//player 6
            case R.id.bn16:
                if (!BY16.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb6.setText(l.subtract(m).toString());
                    BY16.setEnabled(true);
                }
                BN16.setEnabled(false);
                break;
            case R.id.bn66:
                if (!BY66.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb6.setText(l.subtract(m).toString());
                    BY66.setEnabled(true);
                }
                BN66.setEnabled(false);
                break;
            case R.id.bn76:
                if (!BY76.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb6.setText(l.subtract(m).toString());
                    BY76.setEnabled(true);
                }
                BN76.setEnabled(false);
                break;
            case R.id.bn106:
                if (!BY106.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb6.setText(l.subtract(m).toString());
                    BY106.setEnabled(true);
                }
                BN106.setEnabled(false);
                break;
            case R.id.bn26:
                if (!BY26.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb6.setText(l.subtract(m).toString());
                    BY26.setEnabled(true);
                }
                BN26.setEnabled(false);
                break;
            case R.id.bn36:
                if (!BY36.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb6.setText(l.subtract(m).toString());
                    BY36.setEnabled(true);
                }
                BN36.setEnabled(false);
                break;
            case R.id.bn46:
                if (!BY46.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb6.setText(l.subtract(m).toString());
                    BY46.setEnabled(true);
                }
                BN46.setEnabled(false);
                break;
            case R.id.bn56:
                if (!BY56.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb6.setText(l.subtract(m).toString());
                    BY56.setEnabled(true);
                }
                BN56.setEnabled(false);
                break;
            case R.id.bn86:
                if (!BY86.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb6.setText(l.subtract(m).toString());
                    BY86.setEnabled(true);
                }
                BN86.setEnabled(false);
                break;
            case R.id.bn96:
                if (!BY96.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb6.setText(l.subtract(m).toString());
                    BY96.setEnabled(true);
                }
                BN96.setEnabled(false);
                break;

            case R.id.fn16:
                if (!FY16.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb6.setText(l.subtract(m).toString());
                    FY16.setEnabled(true);
                }
                FN16.setEnabled(false);
                break;
            case R.id.fn66:
                if (!FY66.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb6.setText(l.subtract(m).toString());
                    FY66.setEnabled(true);
                }
                FN66.setEnabled(false);
                break;
            case R.id.fn76:
                if (!FY76.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb6.setText(l.subtract(m).toString());
                    FY76.setEnabled(true);
                }
                FN76.setEnabled(false);
                break;
            case R.id.fn106:
                if (!FY106.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb6.setText(l.subtract(m).toString());
                    FY106.setEnabled(true);
                }
                FN106.setEnabled(false);
                break;
            case R.id.fn26:
                if (!FY26.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb6.setText(l.subtract(m).toString());
                    FY26.setEnabled(true);
                }
                FN26.setEnabled(false);
                break;
            case R.id.fn36:
                if (!FY36.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb6.setText(l.subtract(m).toString());
                    FY36.setEnabled(true);
                }
                FN36.setEnabled(false);
                break;
            case R.id.fn46:
                if (!FY46.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb6.setText(l.subtract(m).toString());
                    FY46.setEnabled(true);
                }
                FN46.setEnabled(false);
                break;
            case R.id.fn56:
                if (!FY56.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb6.setText(l.subtract(m).toString());
                    FY56.setEnabled(true);
                }
                FN56.setEnabled(false);
                break;
            case R.id.fn86:
                if (!FY86.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb6.setText(l.subtract(m).toString());
                    FY86.setEnabled(true);
                }
                FN86.setEnabled(false);
                break;
            case R.id.fn96:
                if (!FY96.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb6.setText(l.subtract(m).toString());
                    FY96.setEnabled(true);
                }
                FN96.setEnabled(false);
                break;

            case R.id.by16:
                if (BY16.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb6.setText(l.add(m).toString());
                    BN16.setEnabled(true);
                }
                BY16.setEnabled(false);
                break;
//
            case R.id.by66:
                if (BY66.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb6.setText(l.add(m).toString());
                    BN66.setEnabled(true);
                }
                BY66.setEnabled(false);
                break;
            case R.id.by76:
                if (BY76.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb6.setText(l.add(m).toString());
                    BN76.setEnabled(true);
                }
                BY76.setEnabled(false);
                break;
            case R.id.by106:
                if (BY106.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDComb6.setText(l.add(m).toString());
                    BN106.setEnabled(true);
                }
                BY106.setEnabled(false);
                break;
            case R.id.by26:
                if (BY26.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb6.setText(l.add(m).toString());
                    BN26.setEnabled(true);
                }
                BY26.setEnabled(false);
                break;
            case R.id.by36:
                if (BY36.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb6.setText(l.add(m).toString());
                    BN36.setEnabled(true);
                }
                BY36.setEnabled(false);
                break;
            case R.id.by46:
                if (BY46.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb6.setText(l.add(m).toString());
                    BN46.setEnabled(true);
                }
                BY46.setEnabled(false);
                break;
            case R.id.by56:
                if (BY56.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb6.setText(l.add(m).toString());
                    BN56.setEnabled(true);
                }
                BY56.setEnabled(false);
                break;
            case R.id.by86:
                if (BY86.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb6.setText(l.add(m).toString());
                    BN86.setEnabled(true);
                }
                BY86.setEnabled(false);
                break;
            case R.id.by96:
                if (BY96.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDComb6.setText(l.add(m).toString());
                    BN96.setEnabled(true);
                }
                BY96.setEnabled(false);
                break;

            case R.id.fy16:
                if (FY16.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb6.setText(l.add(m).toString());
                    FN16.setEnabled(true);
                }
                FY16.setEnabled(false);
                break;
            case R.id.fy66:
                if (FY66.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb6.setText(l.add(m).toString());
                    FN66.setEnabled(true);
                }
                FY66.setEnabled(false);
                break;
            case R.id.fy76:
                if (FY76.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb6.setText(l.add(m).toString());
                    FN76.setEnabled(true);
                }
                FY76.setEnabled(false);
                break;
            case R.id.fy106:
                if (FY106.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPComb6.setText(l.add(m).toString());
                    FN106.setEnabled(true);
                }
                FY106.setEnabled(false);
                break;
            case R.id.fy26:
                if (FY26.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb6.setText(l.add(m).toString());
                    FN26.setEnabled(true);
                }
                FY26.setEnabled(false);
                break;
            case R.id.fy36:
                if (FY36.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb6.setText(l.add(m).toString());
                    FN36.setEnabled(true);
                }
                FY36.setEnabled(false);
                break;
            case R.id.fy46:
                if (FY46.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb6.setText(l.add(m).toString());
                    FN46.setEnabled(true);
                }
                FY46.setEnabled(false);
                break;
            case R.id.fy56:
                if (FY56.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb6.setText(l.add(m).toString());
                    FN56.setEnabled(true);
                }
                FY56.setEnabled(false);
                break;
            case R.id.fy86:
                if (FY86.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb6.setText(l.add(m).toString());
                    FN86.setEnabled(true);
                }
                FY86.setEnabled(false);
                break;
            case R.id.fy96:
                if (FY96.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPComb6.setText(l.add(m).toString());
                    FN96.setEnabled(true);
                }
                FY96.setEnabled(false);
                break;


//                HANGING MALLAKHAMB

            case R.id.bn1h:
                if (!BY1h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh.setText(l.subtract(m).toString());
                    BY1h.setEnabled(true);
                }
                BN1h.setEnabled(false);
                break;
            case R.id.bn6h:
                if (!BY6h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh.setText(l.subtract(m).toString());
                    BY6h.setEnabled(true);
                }
                BN6h.setEnabled(false);
                break;
            case R.id.bn7h:
                if (!BY7h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh.setText(l.subtract(m).toString());
                    BY7h.setEnabled(true);
                }
                BN7h.setEnabled(false);
                break;
            case R.id.bn10h:
                if (!BY10h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh.setText(l.subtract(m).toString());
                    BY10h.setEnabled(true);
                }
                BN10h.setEnabled(false);
                break;
            case R.id.bn2h:
                if (!BY2h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh.setText(l.subtract(m).toString());
                    BY2h.setEnabled(true);
                }
                BN2h.setEnabled(false);
                break;
            case R.id.bn3h:
                if (!BY3h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh.setText(l.subtract(m).toString());
                    BY3h.setEnabled(true);
                }
                BN3h.setEnabled(false);
                break;
            case R.id.bn4h:
                if (!BY4h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh.setText(l.subtract(m).toString());
                    BY4h.setEnabled(true);
                }
                BN4h.setEnabled(false);
                break;
            case R.id.bn5h:
                if (!BY5h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh.setText(l.subtract(m).toString());
                    BY5h.setEnabled(true);
                }
                BN5h.setEnabled(false);
                break;
            case R.id.bn8h:
                if (!BY8h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh.setText(l.subtract(m).toString());
                    BY8h.setEnabled(true);
                }
                BN8h.setEnabled(false);
                break;
            case R.id.bn9h:
                if (!BY9h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh.setText(l.subtract(m).toString());
                    BY9h.setEnabled(true);
                }
                BN9h.setEnabled(false);
                break;

            case R.id.fn1h:
                if (!FY1h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh.setText(l.subtract(m).toString());
                    FY1h.setEnabled(true);
                }
                FN1h.setEnabled(false);
                break;
            case R.id.fn6h:
                if (!FY6h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh.setText(l.subtract(m).toString());
                    FY6h.setEnabled(true);
                }
                FN6h.setEnabled(false);
                break;
            case R.id.fn7h:
                if (!FY7h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh.setText(l.subtract(m).toString());
                    FY7h.setEnabled(true);
                }
                FN7h.setEnabled(false);
                break;
            case R.id.fn10h:
                if (!FY10h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh.setText(l.subtract(m).toString());
                    FY10h.setEnabled(true);
                }
                FN10h.setEnabled(false);
                break;
            case R.id.fn2h:
                if (!FY2h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh.setText(l.subtract(m).toString());
                    FY2h.setEnabled(true);
                }
                FN2h.setEnabled(false);
                break;
            case R.id.fn3h:
                if (!FY3h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh.setText(l.subtract(m).toString());
                    FY3h.setEnabled(true);
                }
                FN3h.setEnabled(false);
                break;
            case R.id.fn4h:
                if (!FY4h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh.setText(l.subtract(m).toString());
                    FY4h.setEnabled(true);
                }
                FN4h.setEnabled(false);
                break;
            case R.id.fn5h:
                if (!FY5h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh.setText(l.subtract(m).toString());
                    FY5h.setEnabled(true);
                }
                FN5h.setEnabled(false);
                break;
            case R.id.fn8h:
                if (!FY8h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh.setText(l.subtract(m).toString());
                    FY8h.setEnabled(true);
                }
                FN8h.setEnabled(false);
                break;
            case R.id.fn9h:
                if (!FY9h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh.setText(l.subtract(m).toString());
                    FY9h.setEnabled(true);
                }
                FN9h.setEnabled(false);
                break;

            case R.id.by1h:
                if (BY1h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh.setText(l.add(m).toString());
                    BN1h.setEnabled(true);
                }
                BY1h.setEnabled(false);
                break;
//
            case R.id.by6h:
                if (BY6h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh.setText(l.add(m).toString());
                    BN6h.setEnabled(true);
                }
                BY6h.setEnabled(false);
                break;
            case R.id.by7h:
                if (BY7h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh.setText(l.add(m).toString());
                    BN7h.setEnabled(true);
                }
                BY7h.setEnabled(false);
                break;
            case R.id.by10h:
                if (BY10h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh.setText(l.add(m).toString());
                    BN10h.setEnabled(true);
                }
                BY10h.setEnabled(false);
                break;
            case R.id.by2h:
                if (BY2h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh.setText(l.add(m).toString());
                    BN2h.setEnabled(true);
                }
                BY2h.setEnabled(false);
                break;
            case R.id.by3h:
                if (BY3h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh.setText(l.add(m).toString());
                    BN3h.setEnabled(true);
                }
                BY3h.setEnabled(false);
                break;
            case R.id.by4h:
                if (BY4h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh.setText(l.add(m).toString());
                    BN4h.setEnabled(true);
                }
                BY4h.setEnabled(false);
                break;
            case R.id.by5h:
                if (BY5h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh.setText(l.add(m).toString());
                    BN5h.setEnabled(true);
                }
                BY5h.setEnabled(false);
                break;
            case R.id.by8h:
                if (BY8h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh.setText(l.add(m).toString());
                    BN8h.setEnabled(true);
                }
                BY8h.setEnabled(false);
                break;
            case R.id.by9h:
                if (BY9h.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh.setText(l.add(m).toString());
                    BN9h.setEnabled(true);
                }
                BY9h.setEnabled(false);
                break;

            case R.id.fy1h:
                if (FY1h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh.setText(l.add(m).toString());
                    FN1h.setEnabled(true);
                }
                FY1h.setEnabled(false);
                break;
            case R.id.fy6h:
                if (FY6h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh.setText(l.add(m).toString());
                    FN6h.setEnabled(true);
                }
                FY6h.setEnabled(false);
                break;
            case R.id.fy7h:
                if (FY7h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh.setText(l.add(m).toString());
                    FN7h.setEnabled(true);
                }
                FY7h.setEnabled(false);
                break;
            case R.id.fy10h:
                if (FY10h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh.setText(l.add(m).toString());
                    FN10h.setEnabled(true);
                }
                FY10h.setEnabled(false);
                break;
            case R.id.fy2h:
                if (FY2h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh.setText(l.add(m).toString());
                    FN2h.setEnabled(true);
                }
                FY2h.setEnabled(false);
                break;
            case R.id.fy3h:
                if (FY3h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh.setText(l.add(m).toString());
                    FN3h.setEnabled(true);
                }
                FY3h.setEnabled(false);
                break;
            case R.id.fy4h:
                if (FY4h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh.setText(l.add(m).toString());
                    FN4h.setEnabled(true);
                }
                FY4h.setEnabled(false);
                break;
            case R.id.fy5h:
                if (FY5h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh.setText(l.add(m).toString());
                    FN5h.setEnabled(true);
                }
                FY5h.setEnabled(false);
                break;
            case R.id.fy8h:
                if (FY8h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh.setText(l.add(m).toString());
                    FN8h.setEnabled(true);
                }
                FY8h.setEnabled(false);
                break;
            case R.id.fy9h:
                if (FY9h.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh.setText(l.add(m).toString());
                    FN9h.setEnabled(true);
                }
                FY9h.setEnabled(false);
                break;

//player 2
            case R.id.bn1h2:
                if (!BY1h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh2.setText(l.subtract(m).toString());
                    BY1h2.setEnabled(true);
                }
                BN1h2.setEnabled(false);
                break;
            case R.id.bn6h2:
                if (!BY6h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh2.setText(l.subtract(m).toString());
                    BY6h2.setEnabled(true);
                }
                BN6h2.setEnabled(false);
                break;
            case R.id.bn7h2:
                if (!BY7h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh2.setText(l.subtract(m).toString());
                    BY7h2.setEnabled(true);
                }
                BN7h2.setEnabled(false);
                break;
            case R.id.bn10h2:
                if (!BY10h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh2.setText(l.subtract(m).toString());
                    BY10h2.setEnabled(true);
                }
                BN10h2.setEnabled(false);
                break;
            case R.id.bn2h2:
                if (!BY2h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh2.setText(l.subtract(m).toString());
                    BY2h2.setEnabled(true);
                }
                BN2h2.setEnabled(false);
                break;
            case R.id.bn3h2:
                if (!BY3h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh2.setText(l.subtract(m).toString());
                    BY3h2.setEnabled(true);
                }
                BN3h2.setEnabled(false);
                break;
            case R.id.bn4h2:
                if (!BY4h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh2.setText(l.subtract(m).toString());
                    BY4h2.setEnabled(true);
                }
                BN4h2.setEnabled(false);
                break;
            case R.id.bn5h2:
                if (!BY5h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh2.setText(l.subtract(m).toString());
                    BY5h2.setEnabled(true);
                }
                BN5h2.setEnabled(false);
                break;
            case R.id.bn8h2:
                if (!BY8h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh2.setText(l.subtract(m).toString());
                    BY8h2.setEnabled(true);
                }
                BN8h2.setEnabled(false);
                break;
            case R.id.bn9h2:
                if (!BY9h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh2.setText(l.subtract(m).toString());
                    BY9h2.setEnabled(true);
                }
                BN9h2.setEnabled(false);
                break;

            case R.id.fn1h2:
                if (!FY1h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh2.setText(l.subtract(m).toString());
                    FY1h2.setEnabled(true);
                }
                FN1h2.setEnabled(false);
                break;
            case R.id.fn6h2:
                if (!FY6h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh2.setText(l.subtract(m).toString());
                    FY6h2.setEnabled(true);
                }
                FN6h2.setEnabled(false);
                break;
            case R.id.fn7h2:
                if (!FY7h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh2.setText(l.subtract(m).toString());
                    FY7h2.setEnabled(true);
                }
                FN7h2.setEnabled(false);
                break;
            case R.id.fn10h2:
                if (!FY10h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh2.setText(l.subtract(m).toString());
                    FY10h2.setEnabled(true);
                }
                FN10h2.setEnabled(false);
                break;
            case R.id.fn2h2:
                if (!FY2h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh2.setText(l.subtract(m).toString());
                    FY2h2.setEnabled(true);
                }
                FN2h2.setEnabled(false);
                break;
            case R.id.fn3h2:
                if (!FY3h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh2.setText(l.subtract(m).toString());
                    FY3h2.setEnabled(true);
                }
                FN3h2.setEnabled(false);
                break;
            case R.id.fn4h2:
                if (!FY4h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh2.setText(l.subtract(m).toString());
                    FY4h2.setEnabled(true);
                }
                FN4h2.setEnabled(false);
                break;
            case R.id.fn5h2:
                if (!FY5h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh2.setText(l.subtract(m).toString());
                    FY5h2.setEnabled(true);
                }
                FN5h2.setEnabled(false);
                break;
            case R.id.fn8h2:
                if (!FY8h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh2.setText(l.subtract(m).toString());
                    FY8h2.setEnabled(true);
                }
                FN8h2.setEnabled(false);
                break;
            case R.id.fn9h2:
                if (!FY9h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh2.setText(l.subtract(m).toString());
                    FY9h2.setEnabled(true);
                }
                FN9h2.setEnabled(false);
                break;

            case R.id.by1h2:
                if (BY1h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh2.setText(l.add(m).toString());
                    BN1h2.setEnabled(true);
                }
                BY1h2.setEnabled(false);
                break;
//
            case R.id.by6h2:
                if (BY6h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh2.setText(l.add(m).toString());
                    BN6h2.setEnabled(true);
                }
                BY6h2.setEnabled(false);
                break;
            case R.id.by7h2:
                if (BY7h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh2.setText(l.add(m).toString());
                    BN7h2.setEnabled(true);
                }
                BY7h2.setEnabled(false);
                break;
            case R.id.by10h2:
                if (BY10h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh2.setText(l.add(m).toString());
                    BN10h2.setEnabled(true);
                }
                BY10h2.setEnabled(false);
                break;
            case R.id.by2h2:
                if (BY2h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh2.setText(l.add(m).toString());
                    BN2h2.setEnabled(true);
                }
                BY2h2.setEnabled(false);
                break;
            case R.id.by3h2:
                if (BY3h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh2.setText(l.add(m).toString());
                    BN3h2.setEnabled(true);
                }
                BY3h2.setEnabled(false);
                break;
            case R.id.by4h2:
                if (BY4h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh2.setText(l.add(m).toString());
                    BN4h2.setEnabled(true);
                }
                BY4h2.setEnabled(false);
                break;
            case R.id.by5h2:
                if (BY5h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh2.setText(l.add(m).toString());
                    BN5h2.setEnabled(true);
                }
                BY5h2.setEnabled(false);
                break;
            case R.id.by8h2:
                if (BY8h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh2.setText(l.add(m).toString());
                    BN8h2.setEnabled(true);
                }
                BY8h2.setEnabled(false);
                break;
            case R.id.by9h2:
                if (BY9h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh2.setText(l.add(m).toString());
                    BN9h2.setEnabled(true);
                }
                BY9h2.setEnabled(false);
                break;

            case R.id.fy1h2:
                if (FY1h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh2.setText(l.add(m).toString());
                    FN1h2.setEnabled(true);
                }
                FY1h2.setEnabled(false);
                break;
            case R.id.fy6h2:
                if (FY6h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh2.setText(l.add(m).toString());
                    FN6h2.setEnabled(true);
                }
                FY6h2.setEnabled(false);
                break;
            case R.id.fy7h2:
                if (FY7h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh2.setText(l.add(m).toString());
                    FN7h2.setEnabled(true);
                }
                FY7h2.setEnabled(false);
                break;
            case R.id.fy10h2:
                if (FY10h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh2.setText(l.add(m).toString());
                    FN10h2.setEnabled(true);
                }
                FY10h2.setEnabled(false);
                break;
            case R.id.fy2h2:
                if (FY2h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh2.setText(l.add(m).toString());
                    FN2h2.setEnabled(true);
                }
                FY2h2.setEnabled(false);
                break;
            case R.id.fy3h2:
                if (FY3h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh2.setText(l.add(m).toString());
                    FN3h2.setEnabled(true);
                }
                FY3h2.setEnabled(false);
                break;
            case R.id.fy4h2:
                if (FY4h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh2.setText(l.add(m).toString());
                    FN4h2.setEnabled(true);
                }
                FY4h2.setEnabled(false);
                break;
            case R.id.fy5h2:
                if (FY5h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh2.setText(l.add(m).toString());
                    FN5h2.setEnabled(true);
                }
                FY5h2.setEnabled(false);
                break;
            case R.id.fy8h2:
                if (FY8h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh2.setText(l.add(m).toString());
                    FN8h2.setEnabled(true);
                }
                FY8h2.setEnabled(false);
                break;
            case R.id.fy9h2:
                if (FY9h2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh2.setText(l.add(m).toString());
                    FN9h2.setEnabled(true);
                }
                FY9h2.setEnabled(false);
                break;

//player 3
            case R.id.bn1h3:
                if (!BY1h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh3.setText(l.subtract(m).toString());
                    BY1h3.setEnabled(true);
                }
                BN1h3.setEnabled(false);
                break;
            case R.id.bn6h3:
                if (!BY6h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh3.setText(l.subtract(m).toString());
                    BY6h3.setEnabled(true);
                }
                BN6h3.setEnabled(false);
                break;
            case R.id.bn7h3:
                if (!BY7h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh3.setText(l.subtract(m).toString());
                    BY7h3.setEnabled(true);
                }
                BN7h3.setEnabled(false);
                break;
            case R.id.bn10h3:
                if (!BY10h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh3.setText(l.subtract(m).toString());
                    BY10h3.setEnabled(true);
                }
                BN10h3.setEnabled(false);
                break;
            case R.id.bn2h3:
                if (!BY2h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh3.setText(l.subtract(m).toString());
                    BY2h3.setEnabled(true);
                }
                BN2h3.setEnabled(false);
                break;
            case R.id.bn3h3:
                if (!BY3h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh3.setText(l.subtract(m).toString());
                    BY3h3.setEnabled(true);
                }
                BN3h3.setEnabled(false);
                break;
            case R.id.bn4h3:
                if (!BY4h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh3.setText(l.subtract(m).toString());
                    BY4h3.setEnabled(true);
                }
                BN4h3.setEnabled(false);
                break;
            case R.id.bn5h3:
                if (!BY5h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh3.setText(l.subtract(m).toString());
                    BY5h3.setEnabled(true);
                }
                BN5h3.setEnabled(false);
                break;
            case R.id.bn8h3:
                if (!BY8h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh3.setText(l.subtract(m).toString());
                    BY8h3.setEnabled(true);
                }
                BN8h3.setEnabled(false);
                break;
            case R.id.bn9h3:
                if (!BY9h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh3.setText(l.subtract(m).toString());
                    BY9h3.setEnabled(true);
                }
                BN9h3.setEnabled(false);
                break;

            case R.id.fn1h3:
                if (!FY1h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh3.setText(l.subtract(m).toString());
                    FY1h3.setEnabled(true);
                }
                FN1h3.setEnabled(false);
                break;
            case R.id.fn6h3:
                if (!FY6h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh3.setText(l.subtract(m).toString());
                    FY6h3.setEnabled(true);
                }
                FN6h3.setEnabled(false);
                break;
            case R.id.fn7h3:
                if (!FY7h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh3.setText(l.subtract(m).toString());
                    FY7h3.setEnabled(true);
                }
                FN7h3.setEnabled(false);
                break;
            case R.id.fn10h3:
                if (!FY10h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh3.setText(l.subtract(m).toString());
                    FY10h3.setEnabled(true);
                }
                FN10h3.setEnabled(false);
                break;
            case R.id.fn2h3:
                if (!FY2h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh3.setText(l.subtract(m).toString());
                    FY2h3.setEnabled(true);
                }
                FN2h3.setEnabled(false);
                break;
            case R.id.fn3h3:
                if (!FY3h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh3.setText(l.subtract(m).toString());
                    FY3h3.setEnabled(true);
                }
                FN3h3.setEnabled(false);
                break;
            case R.id.fn4h3:
                if (!FY4h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh3.setText(l.subtract(m).toString());
                    FY4h3.setEnabled(true);
                }
                FN4h3.setEnabled(false);
                break;
            case R.id.fn5h3:
                if (!FY5h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh3.setText(l.subtract(m).toString());
                    FY5h3.setEnabled(true);
                }
                FN5h3.setEnabled(false);
                break;
            case R.id.fn8h3:
                if (!FY8h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh3.setText(l.subtract(m).toString());
                    FY8h3.setEnabled(true);
                }
                FN8h3.setEnabled(false);
                break;
            case R.id.fn9h3:
                if (!FY9h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh3.setText(l.subtract(m).toString());
                    FY9h3.setEnabled(true);
                }
                FN9h3.setEnabled(false);
                break;

            case R.id.by1h3:
                if (BY1h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh3.setText(l.add(m).toString());
                    BN1h3.setEnabled(true);
                }
                BY1h3.setEnabled(false);
                break;
//
            case R.id.by6h3:
                if (BY6h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh3.setText(l.add(m).toString());
                    BN6h3.setEnabled(true);
                }
                BY6h3.setEnabled(false);
                break;
            case R.id.by7h3:
                if (BY7h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh3.setText(l.add(m).toString());
                    BN7h3.setEnabled(true);
                }
                BY7h3.setEnabled(false);
                break;
            case R.id.by10h3:
                if (BY10h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh3.setText(l.add(m).toString());
                    BN10h3.setEnabled(true);
                }
                BY10h3.setEnabled(false);
                break;
            case R.id.by2h3:
                if (BY2h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh3.setText(l.add(m).toString());
                    BN2h3.setEnabled(true);
                }
                BY2h3.setEnabled(false);
                break;
            case R.id.by3h3:
                if (BY3h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh3.setText(l.add(m).toString());
                    BN3h3.setEnabled(true);
                }
                BY3h3.setEnabled(false);
                break;
            case R.id.by4h3:
                if (BY4h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh3.setText(l.add(m).toString());
                    BN4h3.setEnabled(true);
                }
                BY4h3.setEnabled(false);
                break;
            case R.id.by5h3:
                if (BY5h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh3.setText(l.add(m).toString());
                    BN5h3.setEnabled(true);
                }
                BY5h3.setEnabled(false);
                break;
            case R.id.by8h3:
                if (BY8h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh3.setText(l.add(m).toString());
                    BN8h3.setEnabled(true);
                }
                BY8h3.setEnabled(false);
                break;
            case R.id.by9h3:
                if (BY9h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh3.setText(l.add(m).toString());
                    BN9h3.setEnabled(true);
                }
                BY9h3.setEnabled(false);
                break;

            case R.id.fy1h3:
                if (FY1h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh3.setText(l.add(m).toString());
                    FN1h3.setEnabled(true);
                }
                FY1h3.setEnabled(false);
                break;
            case R.id.fy6h3:
                if (FY6h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh3.setText(l.add(m).toString());
                    FN6h3.setEnabled(true);
                }
                FY6h3.setEnabled(false);
                break;
            case R.id.fy7h3:
                if (FY7h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh3.setText(l.add(m).toString());
                    FN7h3.setEnabled(true);
                }
                FY7h3.setEnabled(false);
                break;
            case R.id.fy10h3:
                if (FY10h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh3.setText(l.add(m).toString());
                    FN10h3.setEnabled(true);
                }
                FY10h3.setEnabled(false);
                break;
            case R.id.fy2h3:
                if (FY2h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh3.setText(l.add(m).toString());
                    FN2h3.setEnabled(true);
                }
                FY2h3.setEnabled(false);
                break;
            case R.id.fy3h3:
                if (FY3h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh3.setText(l.add(m).toString());
                    FN3h3.setEnabled(true);
                }
                FY3h3.setEnabled(false);
                break;
            case R.id.fy4h3:
                if (FY4h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh3.setText(l.add(m).toString());
                    FN4h3.setEnabled(true);
                }
                FY4h3.setEnabled(false);
                break;
            case R.id.fy5h3:
                if (FY5h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh3.setText(l.add(m).toString());
                    FN5h3.setEnabled(true);
                }
                FY5h3.setEnabled(false);
                break;
            case R.id.fy8h3:
                if (FY8h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh3.setText(l.add(m).toString());
                    FN8h3.setEnabled(true);
                }
                FY8h3.setEnabled(false);
                break;
            case R.id.fy9h3:
                if (FY9h3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh3.setText(l.add(m).toString());
                    FN9h3.setEnabled(true);
                }
                FY9h3.setEnabled(false);
                break;

//player 4
            case R.id.bn1h4:
                if (!BY1h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh4.setText(l.subtract(m).toString());
                    BY1h4.setEnabled(true);
                }
                BN1h4.setEnabled(false);
                break;
            case R.id.bn6h4:
                if (!BY6h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh4.setText(l.subtract(m).toString());
                    BY6h4.setEnabled(true);
                }
                BN6h4.setEnabled(false);
                break;
            case R.id.bn7h4:
                if (!BY7h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh4.setText(l.subtract(m).toString());
                    BY7h4.setEnabled(true);
                }
                BN7h4.setEnabled(false);
                break;
            case R.id.bn10h4:
                if (!BY10h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh4.setText(l.subtract(m).toString());
                    BY10h4.setEnabled(true);
                }
                BN10h4.setEnabled(false);
                break;
            case R.id.bn2h4:
                if (!BY2h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh4.setText(l.subtract(m).toString());
                    BY2h4.setEnabled(true);
                }
                BN2h4.setEnabled(false);
                break;
            case R.id.bn3h4:
                if (!BY3h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh4.setText(l.subtract(m).toString());
                    BY3h4.setEnabled(true);
                }
                BN3h4.setEnabled(false);
                break;
            case R.id.bn4h4:
                if (!BY4h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh4.setText(l.subtract(m).toString());
                    BY4h4.setEnabled(true);
                }
                BN4h4.setEnabled(false);
                break;
            case R.id.bn5h4:
                if (!BY5h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh4.setText(l.subtract(m).toString());
                    BY5h4.setEnabled(true);
                }
                BN5h4.setEnabled(false);
                break;
            case R.id.bn8h4:
                if (!BY8h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh4.setText(l.subtract(m).toString());
                    BY8h4.setEnabled(true);
                }
                BN8h4.setEnabled(false);
                break;
            case R.id.bn9h4:
                if (!BY9h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh4.setText(l.subtract(m).toString());
                    BY9h4.setEnabled(true);
                }
                BN9h4.setEnabled(false);
                break;

            case R.id.fn1h4:
                if (!FY1h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh4.setText(l.subtract(m).toString());
                    FY1h4.setEnabled(true);
                }
                FN1h4.setEnabled(false);
                break;
            case R.id.fn6h4:
                if (!FY6h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh4.setText(l.subtract(m).toString());
                    FY6h4.setEnabled(true);
                }
                FN6h4.setEnabled(false);
                break;
            case R.id.fn7h4:
                if (!FY7h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh4.setText(l.subtract(m).toString());
                    FY7h4.setEnabled(true);
                }
                FN7h4.setEnabled(false);
                break;
            case R.id.fn10h4:
                if (!FY10h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh4.setText(l.subtract(m).toString());
                    FY10h4.setEnabled(true);
                }
                FN10h4.setEnabled(false);
                break;
            case R.id.fn2h4:
                if (!FY2h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh4.setText(l.subtract(m).toString());
                    FY2h4.setEnabled(true);
                }
                FN2h4.setEnabled(false);
                break;
            case R.id.fn3h4:
                if (!FY3h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh4.setText(l.subtract(m).toString());
                    FY3h4.setEnabled(true);
                }
                FN3h4.setEnabled(false);
                break;
            case R.id.fn4h4:
                if (!FY4h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh4.setText(l.subtract(m).toString());
                    FY4h4.setEnabled(true);
                }
                FN4h4.setEnabled(false);
                break;
            case R.id.fn5h4:
                if (!FY5h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh4.setText(l.subtract(m).toString());
                    FY5h4.setEnabled(true);
                }
                FN5h4.setEnabled(false);
                break;
            case R.id.fn8h4:
                if (!FY8h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh4.setText(l.subtract(m).toString());
                    FY8h4.setEnabled(true);
                }
                FN8h4.setEnabled(false);
                break;
            case R.id.fn9h4:
                if (!FY9h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh4.setText(l.subtract(m).toString());
                    FY9h4.setEnabled(true);
                }
                FN9h4.setEnabled(false);
                break;

            case R.id.by1h4:
                if (BY1h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh4.setText(l.add(m).toString());
                    BN1h4.setEnabled(true);
                }
                BY1h4.setEnabled(false);
                break;
//
            case R.id.by6h4:
                if (BY6h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh4.setText(l.add(m).toString());
                    BN6h4.setEnabled(true);
                }
                BY6h4.setEnabled(false);
                break;
            case R.id.by7h4:
                if (BY7h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh4.setText(l.add(m).toString());
                    BN7h4.setEnabled(true);
                }
                BY7h4.setEnabled(false);
                break;
            case R.id.by10h4:
                if (BY10h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh4.setText(l.add(m).toString());
                    BN10h4.setEnabled(true);
                }
                BY10h4.setEnabled(false);
                break;
            case R.id.by2h4:
                if (BY2h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh4.setText(l.add(m).toString());
                    BN2h4.setEnabled(true);
                }
                BY2h4.setEnabled(false);
                break;
            case R.id.by3h4:
                if (BY3h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh4.setText(l.add(m).toString());
                    BN3h4.setEnabled(true);
                }
                BY3h4.setEnabled(false);
                break;
            case R.id.by4h4:
                if (BY4h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh4.setText(l.add(m).toString());
                    BN4h4.setEnabled(true);
                }
                BY4h4.setEnabled(false);
                break;
            case R.id.by5h4:
                if (BY5h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh4.setText(l.add(m).toString());
                    BN5h4.setEnabled(true);
                }
                BY5h4.setEnabled(false);
                break;
            case R.id.by8h4:
                if (BY8h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh4.setText(l.add(m).toString());
                    BN8h4.setEnabled(true);
                }
                BY8h4.setEnabled(false);
                break;
            case R.id.by9h4:
                if (BY9h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh4.setText(l.add(m).toString());
                    BN9h4.setEnabled(true);
                }
                BY9h4.setEnabled(false);
                break;

            case R.id.fy1h4:
                if (FY1h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh4.setText(l.add(m).toString());
                    FN1h4.setEnabled(true);
                }
                FY1h4.setEnabled(false);
                break;
            case R.id.fy6h4:
                if (FY6h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh4.setText(l.add(m).toString());
                    FN6h4.setEnabled(true);
                }
                FY6h4.setEnabled(false);
                break;
            case R.id.fy7h4:
                if (FY7h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh4.setText(l.add(m).toString());
                    FN7h4.setEnabled(true);
                }
                FY7h4.setEnabled(false);
                break;
            case R.id.fy10h4:
                if (FY10h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh4.setText(l.add(m).toString());
                    FN10h4.setEnabled(true);
                }
                FY10h4.setEnabled(false);
                break;
            case R.id.fy2h4:
                if (FY2h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh4.setText(l.add(m).toString());
                    FN2h4.setEnabled(true);
                }
                FY2h4.setEnabled(false);
                break;
            case R.id.fy3h4:
                if (FY3h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh4.setText(l.add(m).toString());
                    FN3h4.setEnabled(true);
                }
                FY3h4.setEnabled(false);
                break;
            case R.id.fy4h4:
                if (FY4h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh4.setText(l.add(m).toString());
                    FN4h4.setEnabled(true);
                }
                FY4h4.setEnabled(false);
                break;
            case R.id.fy5h4:
                if (FY5h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh4.setText(l.add(m).toString());
                    FN5h4.setEnabled(true);
                }
                FY5h4.setEnabled(false);
                break;
            case R.id.fy8h4:
                if (FY8h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh4.setText(l.add(m).toString());
                    FN8h4.setEnabled(true);
                }
                FY8h4.setEnabled(false);
                break;
            case R.id.fy9h4:
                if (FY9h4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh4.setText(l.add(m).toString());
                    FN9h4.setEnabled(true);
                }
                FY9h4.setEnabled(false);
                break;

//player 5
            case R.id.bn1h5:
                if (!BY1h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh5.setText(l.subtract(m).toString());
                    BY1h5.setEnabled(true);
                }
                BN1h5.setEnabled(false);
                break;
            case R.id.bn6h5:
                if (!BY6h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh5.setText(l.subtract(m).toString());
                    BY6h5.setEnabled(true);
                }
                BN6h5.setEnabled(false);
                break;
            case R.id.bn7h5:
                if (!BY7h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh5.setText(l.subtract(m).toString());
                    BY7h5.setEnabled(true);
                }
                BN7h5.setEnabled(false);
                break;
            case R.id.bn10h5:
                if (!BY10h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh5.setText(l.subtract(m).toString());
                    BY10h5.setEnabled(true);
                }
                BN10h5.setEnabled(false);
                break;
            case R.id.bn2h5:
                if (!BY2h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh5.setText(l.subtract(m).toString());
                    BY2h5.setEnabled(true);
                }
                BN2h5.setEnabled(false);
                break;
            case R.id.bn3h5:
                if (!BY3h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh5.setText(l.subtract(m).toString());
                    BY3h5.setEnabled(true);
                }
                BN3h5.setEnabled(false);
                break;
            case R.id.bn4h5:
                if (!BY4h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh5.setText(l.subtract(m).toString());
                    BY4h5.setEnabled(true);
                }
                BN4h5.setEnabled(false);
                break;
            case R.id.bn5h5:
                if (!BY5h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh5.setText(l.subtract(m).toString());
                    BY5h5.setEnabled(true);
                }
                BN5h5.setEnabled(false);
                break;
            case R.id.bn8h5:
                if (!BY8h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh5.setText(l.subtract(m).toString());
                    BY8h5.setEnabled(true);
                }
                BN8h5.setEnabled(false);
                break;
            case R.id.bn9h5:
                if (!BY9h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh5.setText(l.subtract(m).toString());
                    BY9h5.setEnabled(true);
                }
                BN9h5.setEnabled(false);
                break;

            case R.id.fn1h5:
                if (!FY1h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh5.setText(l.subtract(m).toString());
                    FY1h5.setEnabled(true);
                }
                FN1h5.setEnabled(false);
                break;
            case R.id.fn6h5:
                if (!FY6h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh5.setText(l.subtract(m).toString());
                    FY6h5.setEnabled(true);
                }
                FN6h5.setEnabled(false);
                break;
            case R.id.fn7h5:
                if (!FY7h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh5.setText(l.subtract(m).toString());
                    FY7h5.setEnabled(true);
                }
                FN7h5.setEnabled(false);
                break;
            case R.id.fn10h5:
                if (!FY10h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh5.setText(l.subtract(m).toString());
                    FY10h5.setEnabled(true);
                }
                FN10h5.setEnabled(false);
                break;
            case R.id.fn2h5:
                if (!FY2h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh5.setText(l.subtract(m).toString());
                    FY2h5.setEnabled(true);
                }
                FN2h5.setEnabled(false);
                break;
            case R.id.fn3h5:
                if (!FY3h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh5.setText(l.subtract(m).toString());
                    FY3h5.setEnabled(true);
                }
                FN3h5.setEnabled(false);
                break;
            case R.id.fn4h5:
                if (!FY4h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh5.setText(l.subtract(m).toString());
                    FY4h5.setEnabled(true);
                }
                FN4h5.setEnabled(false);
                break;
            case R.id.fn5h5:
                if (!FY5h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh5.setText(l.subtract(m).toString());
                    FY5h5.setEnabled(true);
                }
                FN5h5.setEnabled(false);
                break;
            case R.id.fn8h5:
                if (!FY8h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh5.setText(l.subtract(m).toString());
                    FY8h5.setEnabled(true);
                }
                FN8h5.setEnabled(false);
                break;
            case R.id.fn9h5:
                if (!FY9h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh5.setText(l.subtract(m).toString());
                    FY9h5.setEnabled(true);
                }
                FN9h5.setEnabled(false);
                break;

            case R.id.by1h5:
                if (BY1h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh5.setText(l.add(m).toString());
                    BN1h5.setEnabled(true);
                }
                BY1h5.setEnabled(false);
                break;
//
            case R.id.by6h5:
                if (BY6h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh5.setText(l.add(m).toString());
                    BN6h5.setEnabled(true);
                }
                BY6h5.setEnabled(false);
                break;
            case R.id.by7h5:
                if (BY7h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh5.setText(l.add(m).toString());
                    BN7h5.setEnabled(true);
                }
                BY7h5.setEnabled(false);
                break;
            case R.id.by10h5:
                if (BY10h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh5.setText(l.add(m).toString());
                    BN10h5.setEnabled(true);
                }
                BY10h5.setEnabled(false);
                break;
            case R.id.by2h5:
                if (BY2h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh5.setText(l.add(m).toString());
                    BN2h5.setEnabled(true);
                }
                BY2h5.setEnabled(false);
                break;
            case R.id.by3h5:
                if (BY3h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh5.setText(l.add(m).toString());
                    BN3h5.setEnabled(true);
                }
                BY3h5.setEnabled(false);
                break;
            case R.id.by4h5:
                if (BY4h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh5.setText(l.add(m).toString());
                    BN4h5.setEnabled(true);
                }
                BY4h5.setEnabled(false);
                break;
            case R.id.by5h5:
                if (BY5h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh5.setText(l.add(m).toString());
                    BN5h5.setEnabled(true);
                }
                BY5h5.setEnabled(false);
                break;
            case R.id.by8h5:
                if (BY8h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh5.setText(l.add(m).toString());
                    BN8h5.setEnabled(true);
                }
                BY8h5.setEnabled(false);
                break;
            case R.id.by9h5:
                if (BY9h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh5.setText(l.add(m).toString());
                    BN9h5.setEnabled(true);
                }
                BY9h5.setEnabled(false);
                break;

            case R.id.fy1h5:
                if (FY1h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh5.setText(l.add(m).toString());
                    FN1h5.setEnabled(true);
                }
                FY1h5.setEnabled(false);
                break;
            case R.id.fy6h5:
                if (FY6h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh5.setText(l.add(m).toString());
                    FN6h5.setEnabled(true);
                }
                FY6h5.setEnabled(false);
                break;
            case R.id.fy7h5:
                if (FY7h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh5.setText(l.add(m).toString());
                    FN7h5.setEnabled(true);
                }
                FY7h5.setEnabled(false);
                break;
            case R.id.fy10h5:
                if (FY10h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh5.setText(l.add(m).toString());
                    FN10h5.setEnabled(true);
                }
                FY10h5.setEnabled(false);
                break;
            case R.id.fy2h5:
                if (FY2h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh5.setText(l.add(m).toString());
                    FN2h5.setEnabled(true);
                }
                FY2h5.setEnabled(false);
                break;
            case R.id.fy3h5:
                if (FY3h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh5.setText(l.add(m).toString());
                    FN3h5.setEnabled(true);
                }
                FY3h5.setEnabled(false);
                break;
            case R.id.fy4h5:
                if (FY4h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh5.setText(l.add(m).toString());
                    FN4h5.setEnabled(true);
                }
                FY4h5.setEnabled(false);
                break;
            case R.id.fy5h5:
                if (FY5h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh5.setText(l.add(m).toString());
                    FN5h5.setEnabled(true);
                }
                FY5h5.setEnabled(false);
                break;
            case R.id.fy8h5:
                if (FY8h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh5.setText(l.add(m).toString());
                    FN8h5.setEnabled(true);
                }
                FY8h5.setEnabled(false);
                break;
            case R.id.fy9h5:
                if (FY9h5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh5.setText(l.add(m).toString());
                    FN9h5.setEnabled(true);
                }
                FY9h5.setEnabled(false);
                break;

//player 6
            case R.id.bn1h6:
                if (!BY1h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh6.setText(l.subtract(m).toString());
                    BY1h6.setEnabled(true);
                }
                BN1h6.setEnabled(false);
                break;
            case R.id.bn6h6:
                if (!BY6h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh6.setText(l.subtract(m).toString());
                    BY6h6.setEnabled(true);
                }
                BN6h6.setEnabled(false);
                break;
            case R.id.bn7h6:
                if (!BY7h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh6.setText(l.subtract(m).toString());
                    BY7h6.setEnabled(true);
                }
                BN7h6.setEnabled(false);
                break;
            case R.id.bn10h6:
                if (!BY10h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh6.setText(l.subtract(m).toString());
                    BY10h6.setEnabled(true);
                }
                BN10h6.setEnabled(false);
                break;
            case R.id.bn2h6:
                if (!BY2h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh6.setText(l.subtract(m).toString());
                    BY2h6.setEnabled(true);
                }
                BN2h6.setEnabled(false);
                break;
            case R.id.bn3h6:
                if (!BY3h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh6.setText(l.subtract(m).toString());
                    BY3h6.setEnabled(true);
                }
                BN3h6.setEnabled(false);
                break;
            case R.id.bn4h6:
                if (!BY4h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh6.setText(l.subtract(m).toString());
                    BY4h6.setEnabled(true);
                }
                BN4h6.setEnabled(false);
                break;
            case R.id.bn5h6:
                if (!BY5h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh6.setText(l.subtract(m).toString());
                    BY5h6.setEnabled(true);
                }
                BN5h6.setEnabled(false);
                break;
            case R.id.bn8h6:
                if (!BY8h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh6.setText(l.subtract(m).toString());
                    BY8h6.setEnabled(true);
                }
                BN8h6.setEnabled(false);
                break;
            case R.id.bn9h6:
                if (!BY9h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh6.setText(l.subtract(m).toString());
                    BY9h6.setEnabled(true);
                }
                BN9h6.setEnabled(false);
                break;

            case R.id.fn1h6:
                if (!FY1h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh6.setText(l.subtract(m).toString());
                    FY1h6.setEnabled(true);
                }
                FN1h6.setEnabled(false);
                break;
            case R.id.fn6h6:
                if (!FY6h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh6.setText(l.subtract(m).toString());
                    FY6h6.setEnabled(true);
                }
                FN6h6.setEnabled(false);
                break;
            case R.id.fn7h6:
                if (!FY7h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh6.setText(l.subtract(m).toString());
                    FY7h6.setEnabled(true);
                }
                FN7h6.setEnabled(false);
                break;
            case R.id.fn10h6:
                if (!FY10h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh6.setText(l.subtract(m).toString());
                    FY10h6.setEnabled(true);
                }
                FN10h6.setEnabled(false);
                break;
            case R.id.fn2h6:
                if (!FY2h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh6.setText(l.subtract(m).toString());
                    FY2h6.setEnabled(true);
                }
                FN2h6.setEnabled(false);
                break;
            case R.id.fn3h6:
                if (!FY3h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh6.setText(l.subtract(m).toString());
                    FY3h6.setEnabled(true);
                }
                FN3h6.setEnabled(false);
                break;
            case R.id.fn4h6:
                if (!FY4h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh6.setText(l.subtract(m).toString());
                    FY4h6.setEnabled(true);
                }
                FN4h6.setEnabled(false);
                break;
            case R.id.fn5h6:
                if (!FY5h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh6.setText(l.subtract(m).toString());
                    FY5h6.setEnabled(true);
                }
                FN5h6.setEnabled(false);
                break;
            case R.id.fn8h6:
                if (!FY8h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh6.setText(l.subtract(m).toString());
                    FY8h6.setEnabled(true);
                }
                FN8h6.setEnabled(false);
                break;
            case R.id.fn9h6:
                if (!FY9h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh6.setText(l.subtract(m).toString());
                    FY9h6.setEnabled(true);
                }
                FN9h6.setEnabled(false);
                break;

            case R.id.by1h6:
                if (BY1h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh6.setText(l.add(m).toString());
                    BN1h6.setEnabled(true);
                }
                BY1h6.setEnabled(false);
                break;
//
            case R.id.by6h6:
                if (BY6h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh6.setText(l.add(m).toString());
                    BN6h6.setEnabled(true);
                }
                BY6h6.setEnabled(false);
                break;
            case R.id.by7h6:
                if (BY7h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh6.setText(l.add(m).toString());
                    BN7h6.setEnabled(true);
                }
                BY7h6.setEnabled(false);
                break;
            case R.id.by10h6:
                if (BY10h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    BDCombh6.setText(l.add(m).toString());
                    BN10h6.setEnabled(true);
                }
                BY10h6.setEnabled(false);
                break;
            case R.id.by2h6:
                if (BY2h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh6.setText(l.add(m).toString());
                    BN2h6.setEnabled(true);
                }
                BY2h6.setEnabled(false);
                break;
            case R.id.by3h6:
                if (BY3h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh6.setText(l.add(m).toString());
                    BN3h6.setEnabled(true);
                }
                BY3h6.setEnabled(false);
                break;
            case R.id.by4h6:
                if (BY4h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh6.setText(l.add(m).toString());
                    BN4h6.setEnabled(true);
                }
                BY4h6.setEnabled(false);
                break;
            case R.id.by5h6:
                if (BY5h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh6.setText(l.add(m).toString());
                    BN5h6.setEnabled(true);
                }
                BY5h6.setEnabled(false);
                break;
            case R.id.by8h6:
                if (BY8h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh6.setText(l.add(m).toString());
                    BN8h6.setEnabled(true);
                }
                BY8h6.setEnabled(false);
                break;
            case R.id.by9h6:
                if (BY9h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    BDCombh6.setText(l.add(m).toString());
                    BN9h6.setEnabled(true);
                }
                BY9h6.setEnabled(false);
                break;

            case R.id.fy1h6:
                if (FY1h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh6.setText(l.add(m).toString());
                    FN1h6.setEnabled(true);
                }
                FY1h6.setEnabled(false);
                break;
            case R.id.fy6h6:
                if (FY6h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh6.setText(l.add(m).toString());
                    FN6h6.setEnabled(true);
                }
                FY6h6.setEnabled(false);
                break;
            case R.id.fy7h6:
                if (FY7h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh6.setText(l.add(m).toString());
                    FN7h6.setEnabled(true);
                }
                FY7h6.setEnabled(false);
                break;
            case R.id.fy10h6:
                if (FY10h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.1");
                    FPCombh6.setText(l.add(m).toString());
                    FN10h6.setEnabled(true);
                }
                FY10h6.setEnabled(false);
                break;
            case R.id.fy2h6:
                if (FY2h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh6.setText(l.add(m).toString());
                    FN2h6.setEnabled(true);
                }
                FY2h6.setEnabled(false);
                break;
            case R.id.fy3h6:
                if (FY3h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh6.setText(l.add(m).toString());
                    FN3h6.setEnabled(true);
                }
                FY3h6.setEnabled(false);
                break;
            case R.id.fy4h6:
                if (FY4h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh6.setText(l.add(m).toString());
                    FN4h6.setEnabled(true);
                }
                FY4h6.setEnabled(false);
                break;
            case R.id.fy5h6:
                if (FY5h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh6.setText(l.add(m).toString());
                    FN5h6.setEnabled(true);
                }
                FY5h6.setEnabled(false);
                break;
            case R.id.fy8h6:
                if (FY8h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh6.setText(l.add(m).toString());
                    FN8h6.setEnabled(true);
                }
                FY8h6.setEnabled(false);
                break;
            case R.id.fy9h6:
                if (FY9h6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal m = new BigDecimal("0.2");
                    FPCombh6.setText(l.add(m).toString());
                    FN9h6.setEnabled(true);
                }
                FY9h6.setEnabled(false);
                break;




//ROPE MALLAKHAMB

            case R.id.bn1r:
                if (!BY1r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr.setText(l.subtract(u).toString());
                    BY1r.setEnabled(true);
                }
                BN1r.setEnabled(false);
                break;
            case R.id.bn6r:
                if (!BY6r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr.setText(l.subtract(u).toString());
                    BY6r.setEnabled(true);
                }
                BN6r.setEnabled(false);
                break;
            case R.id.bn7r:
                if (!BY7r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr.setText(l.subtract(u).toString());
                    BY7r.setEnabled(true);
                }
                BN7r.setEnabled(false);
                break;

            case R.id.bn2r:
                if (!BY2r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr.setText(l.subtract(u).toString());
                    BY2r.setEnabled(true);
                }
                BN2r.setEnabled(false);
                break;
            case R.id.bn3r:
                if (!BY3r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr.setText(l.subtract(u).toString());
                    BY3r.setEnabled(true);
                }
                BN3r.setEnabled(false);
                break;
            case R.id.bn4r:
                if (!BY4r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr.setText(l.subtract(u).toString());
                    BY4r.setEnabled(true);
                }
                BN4r.setEnabled(false);
                break;
            case R.id.bn5r:
                if (!BY5r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr.setText(l.subtract(u).toString());
                    BY5r.setEnabled(true);
                }
                BN5r.setEnabled(false);
                break;

            case R.id.fn1r:
                if (!FY1r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr.setText(l.subtract(u).toString());
                    FY1r.setEnabled(true);
                }
                FN1r.setEnabled(false);
                break;
            case R.id.fn6r:
                if (!FY6r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr.setText(l.subtract(u).toString());
                    FY6r.setEnabled(true);
                }
                FN6r.setEnabled(false);
                break;
            case R.id.fn7r:
                if (!FY7r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr.setText(l.subtract(u).toString());
                    FY7r.setEnabled(true);
                }
                FN7r.setEnabled(false);
                break;

            case R.id.fn2r:
                if (!FY2r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr.setText(l.subtract(u).toString());
                    FY2r.setEnabled(true);
                }
                FN2r.setEnabled(false);
                break;
            case R.id.fn3r:
                if (!FY3r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr.setText(l.subtract(u).toString());
                    FY3r.setEnabled(true);
                }
                FN3r.setEnabled(false);
                break;
            case R.id.fn4r:
                if (!FY4r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr.setText(l.subtract(u).toString());
                    FY4r.setEnabled(true);
                }
                FN4r.setEnabled(false);
                break;
            case R.id.fn5r:
                if (!FY5r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr.setText(l.subtract(u).toString());
                    FY5r.setEnabled(true);
                }
                FN5r.setEnabled(false);
                break;

            case R.id.by1r:
                if (BY1r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr.setText(l.add(u).toString());
                    BN1r.setEnabled(true);
                }
                BY1r.setEnabled(false);
                break;
            case R.id.by6r:
                if (BY6r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr.setText(l.add(u).toString());
                    BN6r.setEnabled(true);
                }
                BY6r.setEnabled(false);
                break;
            case R.id.by7r:
                if (BY7r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr.setText(l.add(u).toString());
                    BN7r.setEnabled(true);
                }
                BY7r.setEnabled(false);
                break;

            case R.id.by2r:
                if (BY2r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr.setText(l.add(u).toString());
                    BN2r.setEnabled(true);
                }
                BY2r.setEnabled(false);
                break;
            case R.id.by3r:
                if (BY3r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr.setText(l.add(u).toString());
                    BN3r.setEnabled(true);
                }
                BY3r.setEnabled(false);
                break;
            case R.id.by4r:
                if (BY4r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr.setText(l.add(u).toString());
                    BN4r.setEnabled(true);
                }
                BY4r.setEnabled(false);
                break;
            case R.id.by5r:
                if (BY5r.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr.setText(l.add(u).toString());
                    BN5r.setEnabled(true);
                }
                BY5r.setEnabled(false);
                break;

            case R.id.fy1r:
                if (FY1r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr.setText(l.add(u).toString());
                    FN1r.setEnabled(true);
                }
                FY1r.setEnabled(false);
                break;
            case R.id.fy6r:
                if (FY6r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr.setText(l.add(u).toString());
                    FN6r.setEnabled(true);
                }
                FY6r.setEnabled(false);
                break;
            case R.id.fy7r:
                if (FY7r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr.setText(l.add(u).toString());
                    FN7r.setEnabled(true);
                }
                FY7r.setEnabled(false);
                break;

            case R.id.fy2r:
                if (FY2r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr.setText(l.add(u).toString());
                    FN2r.setEnabled(true);
                }
                FY2r.setEnabled(false);
                break;
            case R.id.fy3r:
                if (FY3r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr.setText(l.add(u).toString());
                    FN3r.setEnabled(true);
                }
                FY3r.setEnabled(false);
                break;
            case R.id.fy4r:
                if (FY4r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr.setText(l.add(u).toString());
                    FN4r.setEnabled(true);
                }
                FY4r.setEnabled(false);
                break;
            case R.id.fy5r:
                if (FY5r.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr.setText(l.add(u).toString());
                    FN5r.setEnabled(true);
                }
                FY5r.setEnabled(false);
                break;

            case R.id.bn1r2:
                if (!BY1r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr2.setText(l.subtract(u).toString());
                    BY1r2.setEnabled(true);
                }
                BN1r2.setEnabled(false);
                break;
            case R.id.bn6r2:
                if (!BY6r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr2.setText(l.subtract(u).toString());
                    BY6r2.setEnabled(true);
                }
                BN6r2.setEnabled(false);
                break;
            case R.id.bn7r2:
                if (!BY7r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr2.setText(l.subtract(u).toString());
                    BY7r2.setEnabled(true);
                }
                BN7r2.setEnabled(false);
                break;

            case R.id.bn2r2:
                if (!BY2r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr2.setText(l.subtract(u).toString());
                    BY2r2.setEnabled(true);
                }
                BN2r2.setEnabled(false);
                break;
            case R.id.bn3r2:
                if (!BY3r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr2.setText(l.subtract(u).toString());
                    BY3r2.setEnabled(true);
                }
                BN3r2.setEnabled(false);
                break;
            case R.id.bn4r2:
                if (!BY4r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr2.setText(l.subtract(u).toString());
                    BY4r2.setEnabled(true);
                }
                BN4r2.setEnabled(false);
                break;
            case R.id.bn5r2:
                if (!BY5r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr2.setText(l.subtract(u).toString());
                    BY5r2.setEnabled(true);
                }
                BN5r2.setEnabled(false);
                break;

            case R.id.fn1r2:
                if (!FY1r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr2.setText(l.subtract(u).toString());
                    FY1r2.setEnabled(true);
                }
                FN1r2.setEnabled(false);
                break;
            case R.id.fn6r2:
                if (!FY6r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr2.setText(l.subtract(u).toString());
                    FY6r2.setEnabled(true);
                }
                FN6r2.setEnabled(false);
                break;
            case R.id.fn7r2:
                if (!FY7r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr2.setText(l.subtract(u).toString());
                    FY7r2.setEnabled(true);
                }
                FN7r2.setEnabled(false);
                break;

            case R.id.fn2r2:
                if (!FY2r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr2.setText(l.subtract(u).toString());
                    FY2r2.setEnabled(true);
                }
                FN2r2.setEnabled(false);
                break;
            case R.id.fn3r2:
                if (!FY3r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr2.setText(l.subtract(u).toString());
                    FY3r2.setEnabled(true);
                }
                FN3r2.setEnabled(false);
                break;
            case R.id.fn4r2:
                if (!FY4r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr2.setText(l.subtract(u).toString());
                    FY4r2.setEnabled(true);
                }
                FN4r2.setEnabled(false);
                break;
            case R.id.fn5r2:
                if (!FY5r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr2.setText(l.subtract(u).toString());
                    FY5r2.setEnabled(true);
                }
                FN5r2.setEnabled(false);
                break;

            case R.id.by1r2:
                if (BY1r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr2.setText(l.add(u).toString());
                    BN1r2.setEnabled(true);
                }
                BY1r2.setEnabled(false);
                break;
            case R.id.by6r2:
                if (BY6r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr2.setText(l.add(u).toString());
                    BN6r2.setEnabled(true);
                }
                BY6r2.setEnabled(false);
                break;
            case R.id.by7r2:
                if (BY7r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr2.setText(l.add(u).toString());
                    BN7r2.setEnabled(true);
                }
                BY7r2.setEnabled(false);
                break;

            case R.id.by2r2:
                if (BY2r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr2.setText(l.add(u).toString());
                    BN2r2.setEnabled(true);
                }
                BY2r2.setEnabled(false);
                break;
            case R.id.by3r2:
                if (BY3r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr2.setText(l.add(u).toString());
                    BN3r2.setEnabled(true);
                }
                BY3r2.setEnabled(false);
                break;
            case R.id.by4r2:
                if (BY4r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr2.setText(l.add(u).toString());
                    BN4r2.setEnabled(true);
                }
                BY4r2.setEnabled(false);
                break;
            case R.id.by5r2:
                if (BY5r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr2.setText(l.add(u).toString());
                    BN5r2.setEnabled(true);
                }
                BY5r2.setEnabled(false);
                break;

            case R.id.fy1r2:
                if (FY1r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr2.setText(l.add(u).toString());
                    FN1r2.setEnabled(true);
                }
                FY1r2.setEnabled(false);
                break;
            case R.id.fy6r2:
                if (FY6r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr2.setText(l.add(u).toString());
                    FN6r2.setEnabled(true);
                }
                FY6r2.setEnabled(false);
                break;
            case R.id.fy7r2:
                if (FY7r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr2.setText(l.add(u).toString());
                    FN7r2.setEnabled(true);
                }
                FY7r2.setEnabled(false);
                break;

            case R.id.fy2r2:
                if (FY2r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr2.setText(l.add(u).toString());
                    FN2r2.setEnabled(true);
                }
                FY2r2.setEnabled(false);
                break;
            case R.id.fy3r2:
                if (FY3r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr2.setText(l.add(u).toString());
                    FN3r2.setEnabled(true);
                }
                FY3r2.setEnabled(false);
                break;
            case R.id.fy4r2:
                if (FY4r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr2.setText(l.add(u).toString());
                    FN4r2.setEnabled(true);
                }
                FY4r2.setEnabled(false);
                break;
            case R.id.fy5r2:
                if (FY5r2.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr2.setText(l.add(u).toString());
                    FN5r2.setEnabled(true);
                }
                FY5r2.setEnabled(false);
                break;

            case R.id.bn1r3:
                if (!BY1r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr3.setText(l.subtract(u).toString());
                    BY1r3.setEnabled(true);
                }
                BN1r3.setEnabled(false);
                break;
            case R.id.bn6r3:
                if (!BY6r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr3.setText(l.subtract(u).toString());
                    BY6r3.setEnabled(true);
                }
                BN6r3.setEnabled(false);
                break;
            case R.id.bn7r3:
                if (!BY7r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr3.setText(l.subtract(u).toString());
                    BY7r3.setEnabled(true);
                }
                BN7r3.setEnabled(false);
                break;

            case R.id.bn2r3:
                if (!BY2r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr3.setText(l.subtract(u).toString());
                    BY2r3.setEnabled(true);
                }
                BN2r3.setEnabled(false);
                break;
            case R.id.bn3r3:
                if (!BY3r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr3.setText(l.subtract(u).toString());
                    BY3r3.setEnabled(true);
                }
                BN3r3.setEnabled(false);
                break;
            case R.id.bn4r3:
                if (!BY4r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr3.setText(l.subtract(u).toString());
                    BY4r3.setEnabled(true);
                }
                BN4r3.setEnabled(false);
                break;
            case R.id.bn5r3:
                if (!BY5r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr3.setText(l.subtract(u).toString());
                    BY5r3.setEnabled(true);
                }
                BN5r3.setEnabled(false);
                break;

            case R.id.fn1r3:
                if (!FY1r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr3.setText(l.subtract(u).toString());
                    FY1r3.setEnabled(true);
                }
                FN1r3.setEnabled(false);
                break;
            case R.id.fn6r3:
                if (!FY6r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr3.setText(l.subtract(u).toString());
                    FY6r3.setEnabled(true);
                }
                FN6r3.setEnabled(false);
                break;
            case R.id.fn7r3:
                if (!FY7r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr3.setText(l.subtract(u).toString());
                    FY7r3.setEnabled(true);
                }
                FN7r3.setEnabled(false);
                break;

            case R.id.fn2r3:
                if (!FY2r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr3.setText(l.subtract(u).toString());
                    FY2r3.setEnabled(true);
                }
                FN2r3.setEnabled(false);
                break;
            case R.id.fn3r3:
                if (!FY3r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr3.setText(l.subtract(u).toString());
                    FY3r3.setEnabled(true);
                }
                FN3r3.setEnabled(false);
                break;
            case R.id.fn4r3:
                if (!FY4r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr3.setText(l.subtract(u).toString());
                    FY4r3.setEnabled(true);
                }
                FN4r3.setEnabled(false);
                break;
            case R.id.fn5r3:
                if (!FY5r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr3.setText(l.subtract(u).toString());
                    FY5r3.setEnabled(true);
                }
                FN5r3.setEnabled(false);
                break;

            case R.id.by1r3:
                if (BY1r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr3.setText(l.add(u).toString());
                    BN1r3.setEnabled(true);
                }
                BY1r3.setEnabled(false);
                break;
            case R.id.by6r3:
                if (BY6r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr3.setText(l.add(u).toString());
                    BN6r3.setEnabled(true);
                }
                BY6r3.setEnabled(false);
                break;
            case R.id.by7r3:
                if (BY7r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr3.setText(l.add(u).toString());
                    BN7r3.setEnabled(true);
                }
                BY7r3.setEnabled(false);
                break;

            case R.id.by2r3:
                if (BY2r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr3.setText(l.add(u).toString());
                    BN2r3.setEnabled(true);
                }
                BY2r3.setEnabled(false);
                break;
            case R.id.by3r3:
                if (BY3r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr3.setText(l.add(u).toString());
                    BN3r3.setEnabled(true);
                }
                BY3r3.setEnabled(false);
                break;
            case R.id.by4r3:
                if (BY4r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr3.setText(l.add(u).toString());
                    BN4r3.setEnabled(true);
                }
                BY4r3.setEnabled(false);
                break;
            case R.id.by5r3:
                if (BY5r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr3.setText(l.add(u).toString());
                    BN5r3.setEnabled(true);
                }
                BY5r3.setEnabled(false);
                break;

            case R.id.fy1r3:
                if (FY1r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr3.setText(l.add(u).toString());
                    FN1r3.setEnabled(true);
                }
                FY1r3.setEnabled(false);
                break;
            case R.id.fy6r3:
                if (FY6r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr3.setText(l.add(u).toString());
                    FN6r3.setEnabled(true);
                }
                FY6r3.setEnabled(false);
                break;
            case R.id.fy7r3:
                if (FY7r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr3.setText(l.add(u).toString());
                    FN7r3.setEnabled(true);
                }
                FY7r3.setEnabled(false);
                break;

            case R.id.fy2r3:
                if (FY2r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr3.setText(l.add(u).toString());
                    FN2r3.setEnabled(true);
                }
                FY2r3.setEnabled(false);
                break;
            case R.id.fy3r3:
                if (FY3r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr3.setText(l.add(u).toString());
                    FN3r3.setEnabled(true);
                }
                FY3r3.setEnabled(false);
                break;
            case R.id.fy4r3:
                if (FY4r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr3.setText(l.add(u).toString());
                    FN4r3.setEnabled(true);
                }
                FY4r3.setEnabled(false);
                break;
            case R.id.fy5r3:
                if (FY5r3.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr3.setText(l.add(u).toString());
                    FN5r3.setEnabled(true);
                }
                FY5r3.setEnabled(false);
                break;

            case R.id.bn1r4:
                if (!BY1r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr4.setText(l.subtract(u).toString());
                    BY1r4.setEnabled(true);
                }
                BN1r4.setEnabled(false);
                break;
            case R.id.bn6r4:
                if (!BY6r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr4.setText(l.subtract(u).toString());
                    BY6r4.setEnabled(true);
                }
                BN6r4.setEnabled(false);
                break;
            case R.id.bn7r4:
                if (!BY7r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr4.setText(l.subtract(u).toString());
                    BY7r4.setEnabled(true);
                }
                BN7r4.setEnabled(false);
                break;

            case R.id.bn2r4:
                if (!BY2r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr4.setText(l.subtract(u).toString());
                    BY2r4.setEnabled(true);
                }
                BN2r4.setEnabled(false);
                break;
            case R.id.bn3r4:
                if (!BY3r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr4.setText(l.subtract(u).toString());
                    BY3r4.setEnabled(true);
                }
                BN3r4.setEnabled(false);
                break;
            case R.id.bn4r4:
                if (!BY4r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr4.setText(l.subtract(u).toString());
                    BY4r4.setEnabled(true);
                }
                BN4r4.setEnabled(false);
                break;
            case R.id.bn5r4:
                if (!BY5r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr4.setText(l.subtract(u).toString());
                    BY5r4.setEnabled(true);
                }
                BN5r4.setEnabled(false);
                break;

            case R.id.fn1r4:
                if (!FY1r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr4.setText(l.subtract(u).toString());
                    FY1r4.setEnabled(true);
                }
                FN1r4.setEnabled(false);
                break;
            case R.id.fn6r4:
                if (!FY6r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr4.setText(l.subtract(u).toString());
                    FY6r4.setEnabled(true);
                }
                FN6r4.setEnabled(false);
                break;
            case R.id.fn7r4:
                if (!FY7r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr4.setText(l.subtract(u).toString());
                    FY7r4.setEnabled(true);
                }
                FN7r4.setEnabled(false);
                break;

            case R.id.fn2r4:
                if (!FY2r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr4.setText(l.subtract(u).toString());
                    FY2r4.setEnabled(true);
                }
                FN2r4.setEnabled(false);
                break;
            case R.id.fn3r4:
                if (!FY3r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr4.setText(l.subtract(u).toString());
                    FY3r4.setEnabled(true);
                }
                FN3r4.setEnabled(false);
                break;
            case R.id.fn4r4:
                if (!FY4r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr4.setText(l.subtract(u).toString());
                    FY4r4.setEnabled(true);
                }
                FN4r4.setEnabled(false);
                break;
            case R.id.fn5r4:
                if (!FY5r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr4.setText(l.subtract(u).toString());
                    FY5r4.setEnabled(true);
                }
                FN5r4.setEnabled(false);
                break;

            case R.id.by1r4:
                if (BY1r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr4.setText(l.add(u).toString());
                    BN1r4.setEnabled(true);
                }
                BY1r4.setEnabled(false);
                break;
            case R.id.by6r4:
                if (BY6r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr4.setText(l.add(u).toString());
                    BN6r4.setEnabled(true);
                }
                BY6r4.setEnabled(false);
                break;
            case R.id.by7r4:
                if (BY7r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr4.setText(l.add(u).toString());
                    BN7r4.setEnabled(true);
                }
                BY7r4.setEnabled(false);
                break;

            case R.id.by2r4:
                if (BY2r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr4.setText(l.add(u).toString());
                    BN2r4.setEnabled(true);
                }
                BY2r4.setEnabled(false);
                break;
            case R.id.by3r4:
                if (BY3r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr4.setText(l.add(u).toString());
                    BN3r4.setEnabled(true);
                }
                BY3r4.setEnabled(false);
                break;
            case R.id.by4r4:
                if (BY4r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr4.setText(l.add(u).toString());
                    BN4r4.setEnabled(true);
                }
                BY4r4.setEnabled(false);
                break;
            case R.id.by5r4:
                if (BY5r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr4.setText(l.add(u).toString());
                    BN5r4.setEnabled(true);
                }
                BY5r4.setEnabled(false);
                break;

            case R.id.fy1r4:
                if (FY1r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr4.setText(l.add(u).toString());
                    FN1r4.setEnabled(true);
                }
                FY1r4.setEnabled(false);
                break;
            case R.id.fy6r4:
                if (FY6r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr4.setText(l.add(u).toString());
                    FN6r4.setEnabled(true);
                }
                FY6r4.setEnabled(false);
                break;
            case R.id.fy7r4:
                if (FY7r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr4.setText(l.add(u).toString());
                    FN7r4.setEnabled(true);
                }
                FY7r4.setEnabled(false);
                break;

            case R.id.fy2r4:
                if (FY2r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr4.setText(l.add(u).toString());
                    FN2r4.setEnabled(true);
                }
                FY2r4.setEnabled(false);
                break;
            case R.id.fy3r4:
                if (FY3r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr4.setText(l.add(u).toString());
                    FN3r4.setEnabled(true);
                }
                FY3r4.setEnabled(false);
                break;
            case R.id.fy4r4:
                if (FY4r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr4.setText(l.add(u).toString());
                    FN4r4.setEnabled(true);
                }
                FY4r4.setEnabled(false);
                break;
            case R.id.fy5r4:
                if (FY5r4.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr4.setText(l.add(u).toString());
                    FN5r4.setEnabled(true);
                }
                FY5r4.setEnabled(false);
                break;

            case R.id.bn1r5:
                if (!BY1r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr5.setText(l.subtract(u).toString());
                    BY1r5.setEnabled(true);
                }
                BN1r5.setEnabled(false);
                break;
            case R.id.bn6r5:
                if (!BY6r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr5.setText(l.subtract(u).toString());
                    BY6r5.setEnabled(true);
                }
                BN6r5.setEnabled(false);
                break;
            case R.id.bn7r5:
                if (!BY7r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr5.setText(l.subtract(u).toString());
                    BY7r5.setEnabled(true);
                }
                BN7r5.setEnabled(false);
                break;

            case R.id.bn2r5:
                if (!BY2r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr5.setText(l.subtract(u).toString());
                    BY2r5.setEnabled(true);
                }
                BN2r5.setEnabled(false);
                break;
            case R.id.bn3r5:
                if (!BY3r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr5.setText(l.subtract(u).toString());
                    BY3r5.setEnabled(true);
                }
                BN3r5.setEnabled(false);
                break;
            case R.id.bn4r5:
                if (!BY4r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr5.setText(l.subtract(u).toString());
                    BY4r5.setEnabled(true);
                }
                BN4r5.setEnabled(false);
                break;
            case R.id.bn5r5:
                if (!BY5r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr5.setText(l.subtract(u).toString());
                    BY5r5.setEnabled(true);
                }
                BN5r5.setEnabled(false);
                break;

            case R.id.fn1r5:
                if (!FY1r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr5.setText(l.subtract(u).toString());
                    FY1r5.setEnabled(true);
                }
                FN1r5.setEnabled(false);
                break;
            case R.id.fn6r5:
                if (!FY6r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr5.setText(l.subtract(u).toString());
                    FY6r5.setEnabled(true);
                }
                FN6r5.setEnabled(false);
                break;
            case R.id.fn7r5:
                if (!FY7r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr5.setText(l.subtract(u).toString());
                    FY7r5.setEnabled(true);
                }
                FN7r5.setEnabled(false);
                break;

            case R.id.fn2r5:
                if (!FY2r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr5.setText(l.subtract(u).toString());
                    FY2r5.setEnabled(true);
                }
                FN2r5.setEnabled(false);
                break;
            case R.id.fn3r5:
                if (!FY3r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr5.setText(l.subtract(u).toString());
                    FY3r5.setEnabled(true);
                }
                FN3r5.setEnabled(false);
                break;
            case R.id.fn4r5:
                if (!FY4r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr5.setText(l.subtract(u).toString());
                    FY4r5.setEnabled(true);
                }
                FN4r5.setEnabled(false);
                break;
            case R.id.fn5r5:
                if (!FY5r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr5.setText(l.subtract(u).toString());
                    FY5r5.setEnabled(true);
                }
                FN5r5.setEnabled(false);
                break;

            case R.id.by1r5:
                if (BY1r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr5.setText(l.add(u).toString());
                    BN1r5.setEnabled(true);
                }
                BY1r5.setEnabled(false);
                break;
            case R.id.by6r5:
                if (BY6r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr5.setText(l.add(u).toString());
                    BN6r5.setEnabled(true);
                }
                BY6r5.setEnabled(false);
                break;
            case R.id.by7r5:
                if (BY7r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr5.setText(l.add(u).toString());
                    BN7r5.setEnabled(true);
                }
                BY7r5.setEnabled(false);
                break;

            case R.id.by2r5:
                if (BY2r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr5.setText(l.add(u).toString());
                    BN2r5.setEnabled(true);
                }
                BY2r5.setEnabled(false);
                break;
            case R.id.by3r5:
                if (BY3r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr5.setText(l.add(u).toString());
                    BN3r5.setEnabled(true);
                }
                BY3r5.setEnabled(false);
                break;
            case R.id.by4r5:
                if (BY4r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr5.setText(l.add(u).toString());
                    BN4r5.setEnabled(true);
                }
                BY4r5.setEnabled(false);
                break;
            case R.id.by5r5:
                if (BY5r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr5.setText(l.add(u).toString());
                    BN5r5.setEnabled(true);
                }
                BY5r5.setEnabled(false);
                break;

            case R.id.fy1r5:
                if (FY1r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr5.setText(l.add(u).toString());
                    FN1r5.setEnabled(true);
                }
                FY1r5.setEnabled(false);
                break;
            case R.id.fy6r5:
                if (FY6r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr5.setText(l.add(u).toString());
                    FN6r5.setEnabled(true);
                }
                FY6r5.setEnabled(false);
                break;
            case R.id.fy7r5:
                if (FY7r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr5.setText(l.add(u).toString());
                    FN7r5.setEnabled(true);
                }
                FY7r5.setEnabled(false);
                break;

            case R.id.fy2r5:
                if (FY2r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr5.setText(l.add(u).toString());
                    FN2r5.setEnabled(true);
                }
                FY2r5.setEnabled(false);
                break;
            case R.id.fy3r5:
                if (FY3r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr5.setText(l.add(u).toString());
                    FN3r5.setEnabled(true);
                }
                FY3r5.setEnabled(false);
                break;
            case R.id.fy4r5:
                if (FY4r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr5.setText(l.add(u).toString());
                    FN4r5.setEnabled(true);
                }
                FY4r5.setEnabled(false);
                break;
            case R.id.fy5r5:
                if (FY5r5.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr5.setText(l.add(u).toString());
                    FN5r5.setEnabled(true);
                }
                FY5r5.setEnabled(false);
                break;

            case R.id.bn1r6:
                if (!BY1r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr6.setText(l.subtract(u).toString());
                    BY1r6.setEnabled(true);
                }
                BN1r6.setEnabled(false);
                break;
            case R.id.bn6r6:
                if (!BY6r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr6.setText(l.subtract(u).toString());
                    BY6r6.setEnabled(true);
                }
                BN6r6.setEnabled(false);
                break;
            case R.id.bn7r6:
                if (!BY7r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr6.setText(l.subtract(u).toString());
                    BY7r6.setEnabled(true);
                }
                BN7r6.setEnabled(false);
                break;

            case R.id.bn2r6:
                if (!BY2r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr6.setText(l.subtract(u).toString());
                    BY2r6.setEnabled(true);
                }
                BN2r6.setEnabled(false);
                break;
            case R.id.bn3r6:
                if (!BY3r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr6.setText(l.subtract(u).toString());
                    BY3r6.setEnabled(true);
                }
                BN3r6.setEnabled(false);
                break;
            case R.id.bn4r6:
                if (!BY4r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr6.setText(l.subtract(u).toString());
                    BY4r6.setEnabled(true);
                }
                BN4r6.setEnabled(false);
                break;
            case R.id.bn5r6:
                if (!BY5r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr6.setText(l.subtract(u).toString());
                    BY5r6.setEnabled(true);
                }
                BN5r6.setEnabled(false);
                break;

            case R.id.fn1r6:
                if (!FY1r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr6.setText(l.subtract(u).toString());
                    FY1r6.setEnabled(true);
                }
                FN1r6.setEnabled(false);
                break;
            case R.id.fn6r6:
                if (!FY6r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr6.setText(l.subtract(u).toString());
                    FY6r6.setEnabled(true);
                }
                FN6r6.setEnabled(false);
                break;
            case R.id.fn7r6:
                if (!FY7r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr6.setText(l.subtract(u).toString());
                    FY7r6.setEnabled(true);
                }
                FN7r6.setEnabled(false);
                break;

            case R.id.fn2r6:
                if (!FY2r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr6.setText(l.subtract(u).toString());
                    FY2r6.setEnabled(true);
                }
                FN2r6.setEnabled(false);
                break;
            case R.id.fn3r6:
                if (!FY3r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr6.setText(l.subtract(u).toString());
                    FY3r6.setEnabled(true);
                }
                FN3r6.setEnabled(false);
                break;
            case R.id.fn4r6:
                if (!FY4r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr6.setText(l.subtract(u).toString());
                    FY4r6.setEnabled(true);
                }
                FN4r6.setEnabled(false);
                break;
            case R.id.fn5r6:
                if (!FY5r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr6.setText(l.subtract(u).toString());
                    FY5r6.setEnabled(true);
                }
                FN5r6.setEnabled(false);
                break;

            case R.id.by1r6:
                if (BY1r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr6.setText(l.add(u).toString());
                    BN1r6.setEnabled(true);
                }
                BY1r6.setEnabled(false);
                break;
            case R.id.by6r6:
                if (BY6r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr6.setText(l.add(u).toString());
                    BN6r6.setEnabled(true);
                }
                BY6r6.setEnabled(false);
                break;
            case R.id.by7r6:
                if (BY7r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr6.setText(l.add(u).toString());
                    BN7r6.setEnabled(true);
                }
                BY7r6.setEnabled(false);
                break;

            case R.id.by2r6:
                if (BY2r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr6.setText(l.add(u).toString());
                    BN2r6.setEnabled(true);
                }
                BY2r6.setEnabled(false);
                break;
            case R.id.by3r6:
                if (BY3r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    BDCombr6.setText(l.add(u).toString());
                    BN3r6.setEnabled(true);
                }
                BY3r6.setEnabled(false);
                break;
            case R.id.by4r6:
                if (BY4r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr6.setText(l.add(u).toString());
                    BN4r6.setEnabled(true);
                }
                BY4r6.setEnabled(false);
                break;
            case R.id.by5r6:
                if (BY5r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    BDCombr6.setText(l.add(u).toString());
                    BN5r6.setEnabled(true);
                }
                BY5r6.setEnabled(false);
                break;

            case R.id.fy1r6:
                if (FY1r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr6.setText(l.add(u).toString());
                    FN1r6.setEnabled(true);
                }
                FY1r6.setEnabled(false);
                break;
            case R.id.fy6r6:
                if (FY6r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr6.setText(l.add(u).toString());
                    FN6r6.setEnabled(true);
                }
                FY6r6.setEnabled(false);
                break;
            case R.id.fy7r6:
                if (FY7r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr6.setText(l.add(u).toString());
                    FN7r6.setEnabled(true);
                }
                FY7r6.setEnabled(false);
                break;

            case R.id.fy2r6:
                if (FY2r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr6.setText(l.add(u).toString());
                    FN2r6.setEnabled(true);
                }
                FY2r6.setEnabled(false);
                break;
            case R.id.fy3r6:
                if (FY3r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.3");
                    FPCombr6.setText(l.add(u).toString());
                    FN3r6.setEnabled(true);
                }
                FY3r6.setEnabled(false);
                break;
            case R.id.fy4r6:
                if (FY4r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr6.setText(l.add(u).toString());
                    FN4r6.setEnabled(true);
                }
                FY4r6.setEnabled(false);
                break;
            case R.id.fy5r6:
                if (FY5r6.isEnabled()) {
                    BigDecimal l = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal u = new BigDecimal("0.2");
                    FPCombr6.setText(l.add(u).toString());
                    FN5r6.setEnabled(true);
                }
                FY5r6.setEnabled(false);
                break;

        }
    }

    public void buttonClick(View v) {

        collectionReference = fStore.collection(compName.getText().toString().trim().toUpperCase())
                .document(typeSpin.getSelectedItem().toString()).collection(teamName.getText().toString().toUpperCase().trim())
                .document(RoomCode.getText().toString().toUpperCase().trim()).collection(ageSpin.getSelectedItem().toString());

        switch (v.getId()) {


            case R.id.submit:
                if (difficulty.getText().length() == 0 ||
                        execution.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1.setVisibility(View.VISIBLE);
                    FP2.setVisibility(View.VISIBLE);
                    FP3.setVisibility(View.VISIBLE);
                    FP4.setVisibility(View.VISIBLE);
                    FP5.setVisibility(View.VISIBLE);
                    FP6.setVisibility(View.VISIBLE);
                    FP7.setVisibility(View.VISIBLE);
                    FP8.setVisibility(View.VISIBLE);
                    FP9.setVisibility(View.VISIBLE);
                    FP10.setVisibility(View.VISIBLE);
                    FPComb.setVisibility(View.VISIBLE);
                    difficultyFinal.setVisibility(View.VISIBLE);
                    executionFinal.setVisibility(View.VISIBLE);
                    originalityFinal.setVisibility(View.VISIBLE);
                    FPTotal.setVisibility(View.VISIBLE);
                    Confirm.setVisibility(View.VISIBLE);

                    BD1.setVisibility(View.INVISIBLE);
                    BD2.setVisibility(View.INVISIBLE);
                    BD3.setVisibility(View.INVISIBLE);
                    BD4.setVisibility(View.INVISIBLE);
                    BD5.setVisibility(View.INVISIBLE);
                    BD6.setVisibility(View.INVISIBLE);
                    BD7.setVisibility(View.INVISIBLE);
                    BD8.setVisibility(View.INVISIBLE);
                    BD9.setVisibility(View.INVISIBLE);
                    BD10.setVisibility(View.INVISIBLE);

                    difficulty.setEnabled(false);
                    execution.setEnabled(false);
                    originality.setEnabled(false);
                    BDTotal.setEnabled(false);
                    Submit.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDComb.getText().toString());
                    BigDecimal b = new BigDecimal(difficulty.getText().toString());
                    BigDecimal c = new BigDecimal(execution.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originality.isChecked()){
                        BDTotal.setText(a.add(b.add(c.add(d))).toString());}else{BDTotal.setText(a.add(b.add(c)).toString());}



                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", BDTotal.getText().toString());
                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirm:
                if ( difficultyFinal.getText().length() == 0 ||
                        executionFinal.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPComb.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinal.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinal.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");
                    next.setVisibility(View.VISIBLE);
                    if (originalityFinal.isChecked()) {
                        FPTotal.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotal.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotal.getText().toString());
                    collectionReference.document("Judge 4").set(user);

                }
                break;

            case R.id.submit2:
                if ( difficulty2.getText().length() == 0 ||
                        execution2.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP12.setVisibility(View.VISIBLE);
                    FP22.setVisibility(View.VISIBLE);
                    FP32.setVisibility(View.VISIBLE);
                    FP42.setVisibility(View.VISIBLE);
                    FP52.setVisibility(View.VISIBLE);
                    FP62.setVisibility(View.VISIBLE);
                    FP72.setVisibility(View.VISIBLE);
                    FP82.setVisibility(View.VISIBLE);
                    FP92.setVisibility(View.VISIBLE);
                    FP102.setVisibility(View.VISIBLE);
                    FPComb2.setVisibility(View.VISIBLE);
                    difficultyFinal2.setVisibility(View.VISIBLE);
                    executionFinal2.setVisibility(View.VISIBLE);
                    originalityFinal2.setVisibility(View.VISIBLE);
                    FPTotal2.setVisibility(View.VISIBLE);
                    Confirm2.setVisibility(View.VISIBLE);

                    BD12.setVisibility(View.INVISIBLE);
                    BD22.setVisibility(View.INVISIBLE);
                    BD32.setVisibility(View.INVISIBLE);
                    BD42.setVisibility(View.INVISIBLE);
                    BD52.setVisibility(View.INVISIBLE);
                    BD62.setVisibility(View.INVISIBLE);
                    BD72.setVisibility(View.INVISIBLE);
                    BD82.setVisibility(View.INVISIBLE);
                    BD92.setVisibility(View.INVISIBLE);
                    BD102.setVisibility(View.INVISIBLE);

                    difficulty2.setEnabled(false);
                    execution2.setEnabled(false);
                    originality2.setEnabled(false);
                    BDTotal2.setEnabled(false);
                    Submit2.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDComb2.getText().toString());
                    BigDecimal b = new BigDecimal(difficulty2.getText().toString());
                    BigDecimal c = new BigDecimal(execution2.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originality2.isChecked()){
                        BDTotal2.setText(a.add(b.add(c.add(d))).toString());}else{BDTotal2.setText(a.add(b.add(c)).toString());}


                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotal.getText().toString());
                    user.put("Total2", BDTotal2.getText().toString());
                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirm2:
                if ( difficultyFinal2.getText().length() == 0 ||
                        executionFinal2.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPComb2.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinal2.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinal2.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");

                    next2.setVisibility(View.VISIBLE);
                    if (originalityFinal2.isChecked()) {
                        FPTotal2.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotal2.setText(a1.add(b1.add(c1)).toString());
                    }


                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotal.getText().toString());
                    user.put("Total2", FPTotal2.getText().toString());
                    collectionReference.document("Judge 4").set(user);

                }
                break;

            case R.id.submit3:
                if ( difficulty3.getText().length() == 0 ||
                        execution3.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP13.setVisibility(View.VISIBLE);
                    FP23.setVisibility(View.VISIBLE);
                    FP33.setVisibility(View.VISIBLE);
                    FP43.setVisibility(View.VISIBLE);
                    FP53.setVisibility(View.VISIBLE);
                    FP63.setVisibility(View.VISIBLE);
                    FP73.setVisibility(View.VISIBLE);
                    FP83.setVisibility(View.VISIBLE);
                    FP93.setVisibility(View.VISIBLE);
                    FP103.setVisibility(View.VISIBLE);
                    FPComb3.setVisibility(View.VISIBLE);
                    difficultyFinal3.setVisibility(View.VISIBLE);
                    executionFinal3.setVisibility(View.VISIBLE);
                    originalityFinal3.setVisibility(View.VISIBLE);
                    FPTotal3.setVisibility(View.VISIBLE);
                    Confirm3.setVisibility(View.VISIBLE);

                    BD13.setVisibility(View.INVISIBLE);
                    BD23.setVisibility(View.INVISIBLE);
                    BD33.setVisibility(View.INVISIBLE);
                    BD43.setVisibility(View.INVISIBLE);
                    BD53.setVisibility(View.INVISIBLE);
                    BD63.setVisibility(View.INVISIBLE);
                    BD73.setVisibility(View.INVISIBLE);
                    BD83.setVisibility(View.INVISIBLE);
                    BD93.setVisibility(View.INVISIBLE);
                    BD103.setVisibility(View.INVISIBLE);

                    difficulty3.setEnabled(false);
                    execution3.setEnabled(false);
                    originality3.setEnabled(false);
                    BDTotal3.setEnabled(false);
                    Submit3.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDComb3.getText().toString());
                    BigDecimal b = new BigDecimal(difficulty3.getText().toString());
                    BigDecimal c = new BigDecimal(execution3.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originality3.isChecked()){
                        BDTotal3.setText(a.add(b.add(c.add(d))).toString());}else{BDTotal3.setText(a.add(b.add(c)).toString());}


                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotal.getText().toString());
                    user.put("Total2", FPTotal2.getText().toString());
                    user.put("Total3", BDTotal3.getText().toString());
                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirm3:
                if ( difficultyFinal3.getText().length() == 0 ||
                        executionFinal3.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPComb3.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinal3.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinal3.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");

                    next3.setVisibility(View.VISIBLE);
                    if (originalityFinal3.isChecked()) {
                        FPTotal3.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotal3.setText(a1.add(b1.add(c1)).toString());
                    }


                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotal.getText().toString());
                    user.put("Total2", FPTotal2.getText().toString());
                    user.put("Total3", FPTotal3.getText().toString());
                    collectionReference.document("Judge 4").set(user);

                }
                break;

            case R.id.submit4:
                if ( difficulty4.getText().length() == 0 ||
                        execution4.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP14.setVisibility(View.VISIBLE);
                    FP24.setVisibility(View.VISIBLE);
                    FP34.setVisibility(View.VISIBLE);
                    FP44.setVisibility(View.VISIBLE);
                    FP54.setVisibility(View.VISIBLE);
                    FP64.setVisibility(View.VISIBLE);
                    FP74.setVisibility(View.VISIBLE);
                    FP84.setVisibility(View.VISIBLE);
                    FP94.setVisibility(View.VISIBLE);
                    FP104.setVisibility(View.VISIBLE);
                    FPComb4.setVisibility(View.VISIBLE);
                    difficultyFinal4.setVisibility(View.VISIBLE);
                    executionFinal4.setVisibility(View.VISIBLE);
                    originalityFinal4.setVisibility(View.VISIBLE);
                    FPTotal4.setVisibility(View.VISIBLE);
                    Confirm4.setVisibility(View.VISIBLE);

                    BD14.setVisibility(View.INVISIBLE);
                    BD24.setVisibility(View.INVISIBLE);
                    BD34.setVisibility(View.INVISIBLE);
                    BD44.setVisibility(View.INVISIBLE);
                    BD54.setVisibility(View.INVISIBLE);
                    BD64.setVisibility(View.INVISIBLE);
                    BD74.setVisibility(View.INVISIBLE);
                    BD84.setVisibility(View.INVISIBLE);
                    BD94.setVisibility(View.INVISIBLE);
                    BD104.setVisibility(View.INVISIBLE);

                    difficulty4.setEnabled(false);
                    execution4.setEnabled(false);
                    originality4.setEnabled(false);
                    BDTotal4.setEnabled(false);
                    Submit4.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDComb4.getText().toString());
                    BigDecimal b = new BigDecimal(difficulty4.getText().toString());
                    BigDecimal c = new BigDecimal(execution4.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originality4.isChecked()){
                        BDTotal4.setText(a.add(b.add(c.add(d))).toString());}else{BDTotal4.setText(a.add(b.add(c)).toString());}


                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotal.getText().toString());
                    user.put("Total2", FPTotal2.getText().toString());
                    user.put("Total3", FPTotal3.getText().toString());
                    user.put("Total4", BDTotal4.getText().toString());
                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirm4:
                if (difficultyFinal4.getText().length() == 0 ||
                        executionFinal4.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPComb4.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinal4.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinal4.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");
                    next4.setVisibility(View.VISIBLE);
                    if (originalityFinal4.isChecked()) {
                        FPTotal4.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotal4.setText(a1.add(b1.add(c1)).toString());
                    }


                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotal.getText().toString());
                    user.put("Total2", FPTotal2.getText().toString());
                    user.put("Total3", FPTotal3.getText().toString());
                    user.put("Total4", FPTotal4.getText().toString());
                    collectionReference.document("Judge 4").set(user);


                }
                break;

            case R.id.submit5:
                if ( difficulty5.getText().length() == 0 ||
                        execution5.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP15.setVisibility(View.VISIBLE);
                    FP25.setVisibility(View.VISIBLE);
                    FP35.setVisibility(View.VISIBLE);
                    FP45.setVisibility(View.VISIBLE);
                    FP55.setVisibility(View.VISIBLE);
                    FP65.setVisibility(View.VISIBLE);
                    FP75.setVisibility(View.VISIBLE);
                    FP85.setVisibility(View.VISIBLE);
                    FP95.setVisibility(View.VISIBLE);
                    FP105.setVisibility(View.VISIBLE);
                    FPComb5.setVisibility(View.VISIBLE);
                    difficultyFinal5.setVisibility(View.VISIBLE);
                    executionFinal5.setVisibility(View.VISIBLE);
                    originalityFinal5.setVisibility(View.VISIBLE);
                    FPTotal5.setVisibility(View.VISIBLE);
                    Confirm5.setVisibility(View.VISIBLE);

                    BD15.setVisibility(View.INVISIBLE);
                    BD25.setVisibility(View.INVISIBLE);
                    BD35.setVisibility(View.INVISIBLE);
                    BD45.setVisibility(View.INVISIBLE);
                    BD55.setVisibility(View.INVISIBLE);
                    BD65.setVisibility(View.INVISIBLE);
                    BD75.setVisibility(View.INVISIBLE);
                    BD85.setVisibility(View.INVISIBLE);
                    BD95.setVisibility(View.INVISIBLE);
                    BD105.setVisibility(View.INVISIBLE);

                    difficulty5.setEnabled(false);
                    execution5.setEnabled(false);
                    originality5.setEnabled(false);
                    BDTotal5.setEnabled(false);
                    Submit5.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDComb5.getText().toString());
                    BigDecimal b = new BigDecimal(difficulty5.getText().toString());
                    BigDecimal c = new BigDecimal(execution5.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originality5.isChecked()){
                        BDTotal5.setText(a.add(b.add(c.add(d))).toString());}else{BDTotal5.setText(a.add(b.add(c)).toString());}


                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotal.getText().toString());
                    user.put("Total2", FPTotal2.getText().toString());
                    user.put("Total3", FPTotal3.getText().toString());
                    user.put("Total4", FPTotal4.getText().toString());
                    user.put("Total5", BDTotal5.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirm5:
                if ( difficultyFinal5.getText().length() == 0 ||
                        executionFinal5.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPComb5.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinal5.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinal5.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");
                    next5.setVisibility(View.VISIBLE);
                    if (originalityFinal5.isChecked()) {
                        FPTotal5.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotal5.setText(a1.add(b1.add(c1)).toString());
                    }


                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotal.getText().toString());
                    user.put("Total2", FPTotal2.getText().toString());
                    user.put("Total3", FPTotal3.getText().toString());
                    user.put("Total4", FPTotal4.getText().toString());
                    user.put("Total5", FPTotal5.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;

            case R.id.submit6:
                if ( difficulty6.getText().length() == 0 ||
                        execution6.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP16.setVisibility(View.VISIBLE);
                    FP26.setVisibility(View.VISIBLE);
                    FP36.setVisibility(View.VISIBLE);
                    FP46.setVisibility(View.VISIBLE);
                    FP56.setVisibility(View.VISIBLE);
                    FP66.setVisibility(View.VISIBLE);
                    FP76.setVisibility(View.VISIBLE);
                    FP86.setVisibility(View.VISIBLE);
                    FP96.setVisibility(View.VISIBLE);
                    FP106.setVisibility(View.VISIBLE);
                    FPComb6.setVisibility(View.VISIBLE);
                    difficultyFinal6.setVisibility(View.VISIBLE);
                    executionFinal6.setVisibility(View.VISIBLE);
                    originalityFinal6.setVisibility(View.VISIBLE);
                    FPTotal6.setVisibility(View.VISIBLE);
                    Confirm6.setVisibility(View.VISIBLE);

                    BD16.setVisibility(View.INVISIBLE);
                    BD26.setVisibility(View.INVISIBLE);
                    BD36.setVisibility(View.INVISIBLE);
                    BD46.setVisibility(View.INVISIBLE);
                    BD56.setVisibility(View.INVISIBLE);
                    BD66.setVisibility(View.INVISIBLE);
                    BD76.setVisibility(View.INVISIBLE);
                    BD86.setVisibility(View.INVISIBLE);
                    BD96.setVisibility(View.INVISIBLE);
                    BD106.setVisibility(View.INVISIBLE);

                    difficulty6.setEnabled(false);
                    execution6.setEnabled(false);
                    originality6.setEnabled(false);
                    BDTotal6.setEnabled(false);
                    Submit6.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDComb6.getText().toString());
                    BigDecimal b = new BigDecimal(difficulty6.getText().toString());
                    BigDecimal c = new BigDecimal(execution6.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originality6.isChecked()){
                        BDTotal6.setText(a.add(b.add(c.add(d))).toString());}else{BDTotal6.setText(a.add(b.add(c)).toString());}


                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotal.getText().toString());
                    user.put("Total2", FPTotal2.getText().toString());
                    user.put("Total3", FPTotal3.getText().toString());
                    user.put("Total4", FPTotal4.getText().toString());
                    user.put("Total5", FPTotal5.getText().toString());
                    user.put("Total6", BDTotal6.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirm6:
                if ( difficultyFinal6.getText().length() == 0 ||
                        executionFinal6.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPComb6.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinal6.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinal6.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");

                    if (originalityFinal6.isChecked()) {
                        FPTotal6.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotal6.setText(a1.add(b1.add(c1)).toString());
                    }


                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotal.getText().toString());
                    user.put("Total2", FPTotal2.getText().toString());
                    user.put("Total3", FPTotal3.getText().toString());
                    user.put("Total4", FPTotal4.getText().toString());
                    user.put("Total5", FPTotal5.getText().toString());
                    user.put("Total6", FPTotal6.getText().toString());

                    collectionReference.document("Judge 4").set(user);


                }
                break;




//HANGING MALLAKHAMB

            case R.id.submith:
                if ( difficultyh.getText().length() == 0 ||
                        executionh.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1h.setVisibility(View.VISIBLE);
                    FP2h.setVisibility(View.VISIBLE);
                    FP3h.setVisibility(View.VISIBLE);
                    FP4h.setVisibility(View.VISIBLE);
                    FP5h.setVisibility(View.VISIBLE);
                    FP6h.setVisibility(View.VISIBLE);
                    FP7h.setVisibility(View.VISIBLE);
                    FP8h.setVisibility(View.VISIBLE);
                    FP9h.setVisibility(View.VISIBLE);
                    FP10h.setVisibility(View.VISIBLE);
                    FPCombh.setVisibility(View.VISIBLE);
                    difficultyFinalh.setVisibility(View.VISIBLE);
                    executionFinalh.setVisibility(View.VISIBLE);
                    originalityFinalh.setVisibility(View.VISIBLE);
                    FPTotalh.setVisibility(View.VISIBLE);
                    Confirmh.setVisibility(View.VISIBLE);

                    BD1h.setVisibility(View.INVISIBLE);
                    BD2h.setVisibility(View.INVISIBLE);
                    BD3h.setVisibility(View.INVISIBLE);
                    BD4h.setVisibility(View.INVISIBLE);
                    BD5h.setVisibility(View.INVISIBLE);
                    BD6h.setVisibility(View.INVISIBLE);
                    BD7h.setVisibility(View.INVISIBLE);
                    BD8h.setVisibility(View.INVISIBLE);
                    BD9h.setVisibility(View.INVISIBLE);
                    BD10h.setVisibility(View.INVISIBLE);

                    difficultyh.setEnabled(false);
                    executionh.setEnabled(false);
                    originalityh.setEnabled(false);
                    BDTotalh.setEnabled(false);
                    Submith.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDCombh.getText().toString());
                    BigDecimal b = new BigDecimal(difficultyh.getText().toString());
                    BigDecimal c = new BigDecimal(executionh.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originalityh.isChecked()){
                        BDTotalh.setText(a.add(b.add(c.add(d))).toString());}else{BDTotalh.setText(a.add(b.add(c)).toString());}

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", BDTotalh.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }

                break;
            case R.id.confirmh:
                if ( difficultyFinalh.getText().length() == 0 ||
                        executionFinalh.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPCombh.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinalh.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinalh.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");

                    nexth.setVisibility(View.VISIBLE);

                    if (originalityFinalh.isChecked()) {
                        FPTotalh.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotalh.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalh.getText().toString());

                    collectionReference.document("Judge 4").set(user);
                }
                break;

            case R.id.submith2:
                if (difficultyh2.getText().length() == 0 ||
                        executionh2.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1h2.setVisibility(View.VISIBLE);
                    FP2h2.setVisibility(View.VISIBLE);
                    FP3h2.setVisibility(View.VISIBLE);
                    FP4h2.setVisibility(View.VISIBLE);
                    FP5h2.setVisibility(View.VISIBLE);
                    FP6h2.setVisibility(View.VISIBLE);
                    FP7h2.setVisibility(View.VISIBLE);
                    FP8h2.setVisibility(View.VISIBLE);
                    FP9h2.setVisibility(View.VISIBLE);
                    FP10h2.setVisibility(View.VISIBLE);
                    FPCombh2.setVisibility(View.VISIBLE);
                    difficultyFinalh2.setVisibility(View.VISIBLE);
                    executionFinalh2.setVisibility(View.VISIBLE);
                    originalityFinalh2.setVisibility(View.VISIBLE);
                    FPTotalh2.setVisibility(View.VISIBLE);
                    Confirmh2.setVisibility(View.VISIBLE);

                    BD1h2.setVisibility(View.INVISIBLE);
                    BD2h2.setVisibility(View.INVISIBLE);
                    BD3h2.setVisibility(View.INVISIBLE);
                    BD4h2.setVisibility(View.INVISIBLE);
                    BD5h2.setVisibility(View.INVISIBLE);
                    BD6h2.setVisibility(View.INVISIBLE);
                    BD7h2.setVisibility(View.INVISIBLE);
                    BD8h2.setVisibility(View.INVISIBLE);
                    BD9h2.setVisibility(View.INVISIBLE);
                    BD10h2.setVisibility(View.INVISIBLE);

                    difficultyh2.setEnabled(false);
                    executionh2.setEnabled(false);
                    originalityh2.setEnabled(false);
                    BDTotalh2.setEnabled(false);
                    Submith2.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDCombh2.getText().toString());
                    BigDecimal b = new BigDecimal(difficultyh2.getText().toString());
                    BigDecimal c = new BigDecimal(executionh2.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originalityh2.isChecked()){
                        BDTotalh2.setText(a.add(b.add(c.add(d))).toString());}else{BDTotalh2.setText(a.add(b.add(c)).toString());}

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalh.getText().toString());
                    user.put("Total2", BDTotalh2.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirmh2:
                if ( difficultyFinalh2.getText().length() == 0 ||
                        executionFinalh2.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPCombh2.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinalh2.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinalh2.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");
                    nexth2.setVisibility(View.VISIBLE);
                    if (originalityFinalh2.isChecked()) {
                        FPTotalh2.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotalh2.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalh.getText().toString());
                    user.put("Total2", FPTotalh2.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;

            case R.id.submith3:
                if (difficultyh3.getText().length() == 0 ||
                        executionh3.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1h3.setVisibility(View.VISIBLE);
                    FP2h3.setVisibility(View.VISIBLE);
                    FP3h3.setVisibility(View.VISIBLE);
                    FP4h3.setVisibility(View.VISIBLE);
                    FP5h3.setVisibility(View.VISIBLE);
                    FP6h3.setVisibility(View.VISIBLE);
                    FP7h3.setVisibility(View.VISIBLE);
                    FP8h3.setVisibility(View.VISIBLE);
                    FP9h3.setVisibility(View.VISIBLE);
                    FP10h3.setVisibility(View.VISIBLE);
                    FPCombh3.setVisibility(View.VISIBLE);
                    difficultyFinalh3.setVisibility(View.VISIBLE);
                    executionFinalh3.setVisibility(View.VISIBLE);
                    originalityFinalh3.setVisibility(View.VISIBLE);
                    FPTotalh3.setVisibility(View.VISIBLE);
                    Confirmh3.setVisibility(View.VISIBLE);

                    BD1h3.setVisibility(View.INVISIBLE);
                    BD2h3.setVisibility(View.INVISIBLE);
                    BD3h3.setVisibility(View.INVISIBLE);
                    BD4h3.setVisibility(View.INVISIBLE);
                    BD5h3.setVisibility(View.INVISIBLE);
                    BD6h3.setVisibility(View.INVISIBLE);
                    BD7h3.setVisibility(View.INVISIBLE);
                    BD8h3.setVisibility(View.INVISIBLE);
                    BD9h3.setVisibility(View.INVISIBLE);
                    BD10h3.setVisibility(View.INVISIBLE);

                    difficultyh3.setEnabled(false);
                    executionh3.setEnabled(false);
                    originalityh3.setEnabled(false);
                    BDTotalh3.setEnabled(false);
                    Submith3.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDCombh3.getText().toString());
                    BigDecimal b = new BigDecimal(difficultyh3.getText().toString());
                    BigDecimal c = new BigDecimal(executionh3.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originalityh3.isChecked()){
                        BDTotalh3.setText(a.add(b.add(c.add(d))).toString());}else{BDTotalh3.setText(a.add(b.add(c)).toString());}

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalh.getText().toString());
                    user.put("Total2", FPTotalh2.getText().toString());
                    user.put("Total3", BDTotalh3.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirmh3:
                if ( difficultyFinalh3.getText().length() == 0 ||
                        executionFinalh3.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPCombh3.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinalh3.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinalh3.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");
                    nexth3.setVisibility(View.VISIBLE);
                    if (originalityFinalh3.isChecked()) {
                        FPTotalh3.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotalh3.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalh.getText().toString());
                    user.put("Total2", FPTotalh2.getText().toString());
                    user.put("Total3", FPTotalh3.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;

            case R.id.submith4:
                if (difficultyh4.getText().length() == 0 ||
                        executionh4.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1h4.setVisibility(View.VISIBLE);
                    FP2h4.setVisibility(View.VISIBLE);
                    FP3h4.setVisibility(View.VISIBLE);
                    FP4h4.setVisibility(View.VISIBLE);
                    FP5h4.setVisibility(View.VISIBLE);
                    FP6h4.setVisibility(View.VISIBLE);
                    FP7h4.setVisibility(View.VISIBLE);
                    FP8h4.setVisibility(View.VISIBLE);
                    FP9h4.setVisibility(View.VISIBLE);
                    FP10h4.setVisibility(View.VISIBLE);
                    FPCombh4.setVisibility(View.VISIBLE);
                    difficultyFinalh4.setVisibility(View.VISIBLE);
                    executionFinalh4.setVisibility(View.VISIBLE);
                    originalityFinalh4.setVisibility(View.VISIBLE);
                    FPTotalh4.setVisibility(View.VISIBLE);
                    Confirmh4.setVisibility(View.VISIBLE);

                    BD1h4.setVisibility(View.INVISIBLE);
                    BD2h4.setVisibility(View.INVISIBLE);
                    BD3h4.setVisibility(View.INVISIBLE);
                    BD4h4.setVisibility(View.INVISIBLE);
                    BD5h4.setVisibility(View.INVISIBLE);
                    BD6h4.setVisibility(View.INVISIBLE);
                    BD7h4.setVisibility(View.INVISIBLE);
                    BD8h4.setVisibility(View.INVISIBLE);
                    BD9h4.setVisibility(View.INVISIBLE);
                    BD10h4.setVisibility(View.INVISIBLE);

                    difficultyh4.setEnabled(false);
                    executionh4.setEnabled(false);
                    originalityh4.setEnabled(false);
                    BDTotalh4.setEnabled(false);
                    Submith4.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDCombh4.getText().toString());
                    BigDecimal b = new BigDecimal(difficultyh4.getText().toString());
                    BigDecimal c = new BigDecimal(executionh4.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originalityh4.isChecked()){
                        BDTotalh4.setText(a.add(b.add(c.add(d))).toString());}else{BDTotalh4.setText(a.add(b.add(c)).toString());}

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalh.getText().toString());
                    user.put("Total2", FPTotalh2.getText().toString());
                    user.put("Total3", FPTotalh3.getText().toString());
                    user.put("Total4", BDTotalh4.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirmh4:
                if ( difficultyFinalh4.getText().length() == 0 ||
                        executionFinalh4.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPCombh4.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinalh4.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinalh4.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");
                    nexth4.setVisibility(View.VISIBLE);
                    if (originalityFinalh4.isChecked()) {
                        FPTotalh4.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotalh4.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalh.getText().toString());
                    user.put("Total2", FPTotalh2.getText().toString());
                    user.put("Total3", FPTotalh3.getText().toString());
                    user.put("Total4", FPTotalh4.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;

            case R.id.submith5:
                if (difficultyh5.getText().length() == 0 ||
                        executionh5.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1h5.setVisibility(View.VISIBLE);
                    FP2h5.setVisibility(View.VISIBLE);
                    FP3h5.setVisibility(View.VISIBLE);
                    FP4h5.setVisibility(View.VISIBLE);
                    FP5h5.setVisibility(View.VISIBLE);
                    FP6h5.setVisibility(View.VISIBLE);
                    FP7h5.setVisibility(View.VISIBLE);
                    FP8h5.setVisibility(View.VISIBLE);
                    FP9h5.setVisibility(View.VISIBLE);
                    FP10h5.setVisibility(View.VISIBLE);
                    FPCombh5.setVisibility(View.VISIBLE);
                    difficultyFinalh5.setVisibility(View.VISIBLE);
                    executionFinalh5.setVisibility(View.VISIBLE);
                    originalityFinalh5.setVisibility(View.VISIBLE);
                    FPTotalh5.setVisibility(View.VISIBLE);
                    Confirmh5.setVisibility(View.VISIBLE);

                    BD1h5.setVisibility(View.INVISIBLE);
                    BD2h5.setVisibility(View.INVISIBLE);
                    BD3h5.setVisibility(View.INVISIBLE);
                    BD4h5.setVisibility(View.INVISIBLE);
                    BD5h5.setVisibility(View.INVISIBLE);
                    BD6h5.setVisibility(View.INVISIBLE);
                    BD7h5.setVisibility(View.INVISIBLE);
                    BD8h5.setVisibility(View.INVISIBLE);
                    BD9h5.setVisibility(View.INVISIBLE);
                    BD10h5.setVisibility(View.INVISIBLE);

                    difficultyh5.setEnabled(false);
                    executionh5.setEnabled(false);
                    originalityh5.setEnabled(false);
                    BDTotalh5.setEnabled(false);
                    Submith5.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDCombh5.getText().toString());
                    BigDecimal b = new BigDecimal(difficultyh5.getText().toString());
                    BigDecimal c = new BigDecimal(executionh5.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originalityh5.isChecked()){
                        BDTotalh5.setText(a.add(b.add(c.add(d))).toString());}else{BDTotalh5.setText(a.add(b.add(c)).toString());}

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalh.getText().toString());
                    user.put("Total2", FPTotalh2.getText().toString());
                    user.put("Total3", FPTotalh3.getText().toString());
                    user.put("Total4", FPTotalh4.getText().toString());
                    user.put("Total5", BDTotalh5.getText().toString());

                    collectionReference.document("Judge 4").set(user);
                }
                break;
            case R.id.confirmh5:
                if ( difficultyFinalh5.getText().length() == 0 ||
                        executionFinalh5.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPCombh5.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinalh5.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinalh5.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");
                    nexth5.setVisibility(View.VISIBLE);
                    if (originalityFinalh5.isChecked()) {
                        FPTotalh5.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotalh5.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalh.getText().toString());
                    user.put("Total2", FPTotalh2.getText().toString());
                    user.put("Total3", FPTotalh3.getText().toString());
                    user.put("Total4", FPTotalh4.getText().toString());
                    user.put("Total5", FPTotalh5.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;

            case R.id.submith6:
                if (difficultyh6.getText().length() == 0 ||
                        executionh6.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1h6.setVisibility(View.VISIBLE);
                    FP2h6.setVisibility(View.VISIBLE);
                    FP3h6.setVisibility(View.VISIBLE);
                    FP4h6.setVisibility(View.VISIBLE);
                    FP5h6.setVisibility(View.VISIBLE);
                    FP6h6.setVisibility(View.VISIBLE);
                    FP7h6.setVisibility(View.VISIBLE);
                    FP8h6.setVisibility(View.VISIBLE);
                    FP9h6.setVisibility(View.VISIBLE);
                    FP10h6.setVisibility(View.VISIBLE);
                    FPCombh6.setVisibility(View.VISIBLE);
                    difficultyFinalh6.setVisibility(View.VISIBLE);
                    executionFinalh6.setVisibility(View.VISIBLE);
                    originalityFinalh6.setVisibility(View.VISIBLE);
                    FPTotalh6.setVisibility(View.VISIBLE);
                    Confirmh6.setVisibility(View.VISIBLE);

                    BD1h6.setVisibility(View.INVISIBLE);
                    BD2h6.setVisibility(View.INVISIBLE);
                    BD3h6.setVisibility(View.INVISIBLE);
                    BD4h6.setVisibility(View.INVISIBLE);
                    BD5h6.setVisibility(View.INVISIBLE);
                    BD6h6.setVisibility(View.INVISIBLE);
                    BD7h6.setVisibility(View.INVISIBLE);
                    BD8h6.setVisibility(View.INVISIBLE);
                    BD9h6.setVisibility(View.INVISIBLE);
                    BD10h6.setVisibility(View.INVISIBLE);

                    difficultyh6.setEnabled(false);
                    executionh6.setEnabled(false);
                    originalityh6.setEnabled(false);
                    BDTotalh6.setEnabled(false);
                    Submith6.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDCombh6.getText().toString());
                    BigDecimal b = new BigDecimal(difficultyh6.getText().toString());
                    BigDecimal c = new BigDecimal(executionh6.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originalityh6.isChecked()){
                        BDTotalh6.setText(a.add(b.add(c.add(d))).toString());}else{BDTotalh6.setText(a.add(b.add(c)).toString());}

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalh.getText().toString());
                    user.put("Total2", FPTotalh2.getText().toString());
                    user.put("Total3", FPTotalh3.getText().toString());
                    user.put("Total4", FPTotalh4.getText().toString());
                    user.put("Total5", FPTotalh5.getText().toString());
                    user.put("Total6", BDTotalh6.getText().toString());

                    collectionReference.document("Judge 4").set(user);
                }
                break;
            case R.id.confirmh6:
                if ( difficultyFinalh6.getText().length() == 0 ||
                        executionFinalh6.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPCombh6.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinalh6.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinalh6.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");

                    if (originalityFinalh6.isChecked()) {
                        FPTotalh6.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotalh6.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalh.getText().toString());
                    user.put("Total2", FPTotalh2.getText().toString());
                    user.put("Total3", FPTotalh3.getText().toString());
                    user.put("Total4", FPTotalh4.getText().toString());
                    user.put("Total5", FPTotalh5.getText().toString());
                    user.put("Total6", FPTotalh6.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;








//                ROPE MALLAKHAMB


            case R.id.submitr:
                if (difficultyr.getText().length() == 0 ||
                        executionr.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1r.setVisibility(View.VISIBLE);
                    FP2r.setVisibility(View.VISIBLE);
                    FP3r.setVisibility(View.VISIBLE);
                    FP4r.setVisibility(View.VISIBLE);
                    FP5r.setVisibility(View.VISIBLE);
                    FP6r.setVisibility(View.VISIBLE);
                    FP7r.setVisibility(View.VISIBLE);
                    FPCombr.setVisibility(View.VISIBLE);
                    difficultyFinalr.setVisibility(View.VISIBLE);
                    executionFinalr.setVisibility(View.VISIBLE);
                    originalityFinalr.setVisibility(View.VISIBLE);
                    FPTotalr.setVisibility(View.VISIBLE);
                    Confirmr.setVisibility(View.VISIBLE);

                    BD1r.setVisibility(View.INVISIBLE);
                    BD2r.setVisibility(View.INVISIBLE);
                    BD3r.setVisibility(View.INVISIBLE);
                    BD4r.setVisibility(View.INVISIBLE);
                    BD5r.setVisibility(View.INVISIBLE);
                    BD6r.setVisibility(View.INVISIBLE);
                    BD7r.setVisibility(View.INVISIBLE);

                    difficultyr.setEnabled(false);
                    executionr.setEnabled(false);
                    originalityr.setEnabled(false);
                    BDTotalr.setEnabled(false);
                    Submitr.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDCombr.getText().toString());
                    BigDecimal b = new BigDecimal(difficultyr.getText().toString());
                    BigDecimal c = new BigDecimal(executionr.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originalityr.isChecked()){
                        BDTotalr.setText(a.add(b.add(c.add(d))).toString());}else{BDTotalr.setText(a.add(b.add(c)).toString());}

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", BDTotalr.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirmr:
                if ( difficultyFinalr.getText().length() == 0 ||
                        executionFinalr.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPCombr.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinalr.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinalr.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");
                    nextr.setVisibility(View.VISIBLE);
                    if (originalityFinalr.isChecked()) {
                        FPTotalr.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotalr.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalr.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;

            case R.id.submitr2:
                if (difficultyr2.getText().length() == 0 ||
                        executionr2.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1r2.setVisibility(View.VISIBLE);
                    FP2r2.setVisibility(View.VISIBLE);
                    FP3r2.setVisibility(View.VISIBLE);
                    FP4r2.setVisibility(View.VISIBLE);
                    FP5r2.setVisibility(View.VISIBLE);
                    FP6r2.setVisibility(View.VISIBLE);
                    FP7r2.setVisibility(View.VISIBLE);
                    FPCombr2.setVisibility(View.VISIBLE);
                    difficultyFinalr2.setVisibility(View.VISIBLE);
                    executionFinalr2.setVisibility(View.VISIBLE);
                    originalityFinalr2.setVisibility(View.VISIBLE);
                    FPTotalr2.setVisibility(View.VISIBLE);
                    Confirmr2.setVisibility(View.VISIBLE);

                    BD1r2.setVisibility(View.INVISIBLE);
                    BD2r2.setVisibility(View.INVISIBLE);
                    BD3r2.setVisibility(View.INVISIBLE);
                    BD4r2.setVisibility(View.INVISIBLE);
                    BD5r2.setVisibility(View.INVISIBLE);
                    BD6r2.setVisibility(View.INVISIBLE);
                    BD7r2.setVisibility(View.INVISIBLE);

                    difficultyr2.setEnabled(false);
                    executionr2.setEnabled(false);
                    originalityr2.setEnabled(false);
                    BDTotalr2.setEnabled(false);
                    Submitr2.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDCombr2.getText().toString());
                    BigDecimal b = new BigDecimal(difficultyr2.getText().toString());
                    BigDecimal c = new BigDecimal(executionr2.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originalityr2.isChecked()){
                        BDTotalr2.setText(a.add(b.add(c.add(d))).toString());}else{BDTotalr2.setText(a.add(b.add(c)).toString());}

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalr.getText().toString());
                    user.put("Total2", BDTotalr2.getText().toString());

                    collectionReference.document("Judge 4").set(user);
                }
                break;
            case R.id.confirmr2:
                if ( difficultyFinalr2.getText().length() == 0 ||
                        executionFinalr2.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPCombr2.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinalr2.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinalr2.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");
                    nextr2.setVisibility(View.VISIBLE);
                    if (originalityFinalr2.isChecked()) {
                        FPTotalr2.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotalr2.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalr.getText().toString());
                    user.put("Total2", FPTotalr2.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;

            case R.id.submitr3:
                if (difficultyr3.getText().length() == 0 ||
                        executionr3.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1r3.setVisibility(View.VISIBLE);
                    FP2r3.setVisibility(View.VISIBLE);
                    FP3r3.setVisibility(View.VISIBLE);
                    FP4r3.setVisibility(View.VISIBLE);
                    FP5r3.setVisibility(View.VISIBLE);
                    FP6r3.setVisibility(View.VISIBLE);
                    FP7r3.setVisibility(View.VISIBLE);
                    FPCombr3.setVisibility(View.VISIBLE);
                    difficultyFinalr3.setVisibility(View.VISIBLE);
                    executionFinalr3.setVisibility(View.VISIBLE);
                    originalityFinalr3.setVisibility(View.VISIBLE);
                    FPTotalr3.setVisibility(View.VISIBLE);
                    Confirmr3.setVisibility(View.VISIBLE);

                    BD1r3.setVisibility(View.INVISIBLE);
                    BD2r3.setVisibility(View.INVISIBLE);
                    BD3r3.setVisibility(View.INVISIBLE);
                    BD4r3.setVisibility(View.INVISIBLE);
                    BD5r3.setVisibility(View.INVISIBLE);
                    BD6r3.setVisibility(View.INVISIBLE);
                    BD7r3.setVisibility(View.INVISIBLE);

                    difficultyr3.setEnabled(false);
                    executionr3.setEnabled(false);
                    originalityr3.setEnabled(false);
                    BDTotalr3.setEnabled(false);
                    Submitr3.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDCombr3.getText().toString());
                    BigDecimal b = new BigDecimal(difficultyr3.getText().toString());
                    BigDecimal c = new BigDecimal(executionr3.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originalityr3.isChecked()){
                        BDTotalr3.setText(a.add(b.add(c.add(d))).toString());}else{BDTotalr3.setText(a.add(b.add(c)).toString());}

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalr.getText().toString());
                    user.put("Total2", FPTotalr2.getText().toString());
                    user.put("Total3", BDTotalr3.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirmr3:
                if ( difficultyFinalr3.getText().length() == 0 ||
                        executionFinalr3.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPCombr3.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinalr3.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinalr3.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");
                    nextr3.setVisibility(View.VISIBLE);
                    if (originalityFinalr3.isChecked()) {
                        FPTotalr3.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotalr3.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalr.getText().toString());
                    user.put("Total2", FPTotalr2.getText().toString());
                    user.put("Total3", FPTotalr3.getText().toString());

                    collectionReference.document("Judge 4").set(user);
                }
                break;

            case R.id.submitr4:
                if (difficultyr4.getText().length() == 0 ||
                        executionr4.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1r4.setVisibility(View.VISIBLE);
                    FP2r4.setVisibility(View.VISIBLE);
                    FP3r4.setVisibility(View.VISIBLE);
                    FP4r4.setVisibility(View.VISIBLE);
                    FP5r4.setVisibility(View.VISIBLE);
                    FP6r4.setVisibility(View.VISIBLE);
                    FP7r4.setVisibility(View.VISIBLE);
                    FPCombr4.setVisibility(View.VISIBLE);
                    difficultyFinalr4.setVisibility(View.VISIBLE);
                    executionFinalr4.setVisibility(View.VISIBLE);
                    originalityFinalr4.setVisibility(View.VISIBLE);
                    FPTotalr4.setVisibility(View.VISIBLE);
                    Confirmr4.setVisibility(View.VISIBLE);

                    BD1r4.setVisibility(View.INVISIBLE);
                    BD2r4.setVisibility(View.INVISIBLE);
                    BD3r4.setVisibility(View.INVISIBLE);
                    BD4r4.setVisibility(View.INVISIBLE);
                    BD5r4.setVisibility(View.INVISIBLE);
                    BD6r4.setVisibility(View.INVISIBLE);
                    BD7r4.setVisibility(View.INVISIBLE);

                    difficultyr4.setEnabled(false);
                    executionr4.setEnabled(false);
                    originalityr4.setEnabled(false);
                    BDTotalr4.setEnabled(false);
                    Submitr4.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDCombr4.getText().toString());
                    BigDecimal b = new BigDecimal(difficultyr4.getText().toString());
                    BigDecimal c = new BigDecimal(executionr4.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originalityr4.isChecked()){
                        BDTotalr4.setText(a.add(b.add(c.add(d))).toString());}else{BDTotalr4.setText(a.add(b.add(c)).toString());}

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalr.getText().toString());
                    user.put("Total2", FPTotalr2.getText().toString());
                    user.put("Total3", FPTotalr3.getText().toString());
                    user.put("Total4", BDTotalr4.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirmr4:
                if ( difficultyFinalr4.getText().length() == 0 ||
                        executionFinalr4.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPCombr4.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinalr4.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinalr4.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");
                    nextr4.setVisibility(View.VISIBLE);
                    if (originalityFinalr4.isChecked()) {
                        FPTotalr4.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotalr4.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalr.getText().toString());
                    user.put("Total2", FPTotalr2.getText().toString());
                    user.put("Total3", FPTotalr3.getText().toString());
                    user.put("Total4", FPTotalr4.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;

            case R.id.submitr5:
                if (difficultyr5.getText().length() == 0 ||
                        executionr5.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1r5.setVisibility(View.VISIBLE);
                    FP2r5.setVisibility(View.VISIBLE);
                    FP3r5.setVisibility(View.VISIBLE);
                    FP4r5.setVisibility(View.VISIBLE);
                    FP5r5.setVisibility(View.VISIBLE);
                    FP6r5.setVisibility(View.VISIBLE);
                    FP7r5.setVisibility(View.VISIBLE);
                    FPCombr5.setVisibility(View.VISIBLE);
                    difficultyFinalr5.setVisibility(View.VISIBLE);
                    executionFinalr5.setVisibility(View.VISIBLE);
                    originalityFinalr5.setVisibility(View.VISIBLE);
                    FPTotalr5.setVisibility(View.VISIBLE);
                    Confirmr5.setVisibility(View.VISIBLE);

                    BD1r5.setVisibility(View.INVISIBLE);
                    BD2r5.setVisibility(View.INVISIBLE);
                    BD3r5.setVisibility(View.INVISIBLE);
                    BD4r5.setVisibility(View.INVISIBLE);
                    BD5r5.setVisibility(View.INVISIBLE);
                    BD6r5.setVisibility(View.INVISIBLE);
                    BD7r5.setVisibility(View.INVISIBLE);

                    difficultyr5.setEnabled(false);
                    executionr5.setEnabled(false);
                    originalityr5.setEnabled(false);
                    BDTotalr5.setEnabled(false);
                    Submitr5.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDCombr5.getText().toString());
                    BigDecimal b = new BigDecimal(difficultyr5.getText().toString());
                    BigDecimal c = new BigDecimal(executionr5.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originalityr5.isChecked()){
                        BDTotalr5.setText(a.add(b.add(c.add(d))).toString());}else{BDTotalr5.setText(a.add(b.add(c)).toString());}

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalr.getText().toString());
                    user.put("Total2", FPTotalr2.getText().toString());
                    user.put("Total3", FPTotalr3.getText().toString());
                    user.put("Total4", FPTotalr4.getText().toString());
                    user.put("Total5", BDTotalr5.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirmr5:
                if ( difficultyFinalr5.getText().length() == 0 ||
                        executionFinalr5.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPCombr5.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinalr5.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinalr5.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");
                    nextr5.setVisibility(View.VISIBLE);
                    if (originalityFinalr5.isChecked()) {
                        FPTotalr5.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotalr5.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalr.getText().toString());
                    user.put("Total2", FPTotalr2.getText().toString());
                    user.put("Total3", FPTotalr3.getText().toString());
                    user.put("Total4", FPTotalr4.getText().toString());
                    user.put("Total5", FPTotalr5.getText().toString());

                    collectionReference.document("Judge 4").set(user);
                }
                break;

            case R.id.submitr6:
                if (difficultyr6.getText().length() == 0 ||
                        executionr6.getText().length() == 0 ) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    FP1r6.setVisibility(View.VISIBLE);
                    FP2r6.setVisibility(View.VISIBLE);
                    FP3r6.setVisibility(View.VISIBLE);
                    FP4r6.setVisibility(View.VISIBLE);
                    FP5r6.setVisibility(View.VISIBLE);
                    FP6r6.setVisibility(View.VISIBLE);
                    FP7r6.setVisibility(View.VISIBLE);
                    FPCombr6.setVisibility(View.VISIBLE);
                    difficultyFinalr6.setVisibility(View.VISIBLE);
                    executionFinalr6.setVisibility(View.VISIBLE);
                    originalityFinalr6.setVisibility(View.VISIBLE);
                    FPTotalr6.setVisibility(View.VISIBLE);
                    Confirmr6.setVisibility(View.VISIBLE);

                    BD1r6.setVisibility(View.INVISIBLE);
                    BD2r6.setVisibility(View.INVISIBLE);
                    BD3r6.setVisibility(View.INVISIBLE);
                    BD4r6.setVisibility(View.INVISIBLE);
                    BD5r6.setVisibility(View.INVISIBLE);
                    BD6r6.setVisibility(View.INVISIBLE);
                    BD7r6.setVisibility(View.INVISIBLE);

                    difficultyr6.setEnabled(false);
                    executionr6.setEnabled(false);
                    originalityr6.setEnabled(false);
                    BDTotalr6.setEnabled(false);
                    Submitr6.setEnabled(false);

                    BigDecimal a = new BigDecimal(BDCombr6.getText().toString());
                    BigDecimal b = new BigDecimal(difficultyr6.getText().toString());
                    BigDecimal c = new BigDecimal(executionr6.getText().toString());
                    BigDecimal d = new BigDecimal("0.2");

                    if (originalityr6.isChecked()){
                        BDTotalr6.setText(a.add(b.add(c.add(d))).toString());}else{BDTotalr6.setText(a.add(b.add(c)).toString());}

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalr.getText().toString());
                    user.put("Total2", FPTotalr2.getText().toString());
                    user.put("Total3", FPTotalr3.getText().toString());
                    user.put("Total4", FPTotalr4.getText().toString());
                    user.put("Total5", FPTotalr5.getText().toString());
                    user.put("Total6", BDTotalr6.getText().toString());

                    collectionReference.document("Judge 4").set(user);

                }
                break;
            case R.id.confirmr6:
                if ( difficultyFinalr6.getText().length() == 0 ||
                        executionFinalr6.getText().length() == 0) {
                    Toast.makeText(this, "Enter the Marks", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal a1 = new BigDecimal(FPCombr6.getText().toString());
                    BigDecimal b1 = new BigDecimal(difficultyFinalr6.getText().toString());
                    BigDecimal c1 = new BigDecimal(executionFinalr6.getText().toString());
                    BigDecimal d1 = new BigDecimal("0.2");

                    if (originalityFinalr6.isChecked()) {
                        FPTotalr6.setText(a1.add(b1.add(c1.add(d1))).toString());
                    }else {
                        FPTotalr6.setText(a1.add(b1.add(c1)).toString());
                    }

                    Map<String, String> user = new HashMap<>();
                    user.put("Total1", FPTotalr.getText().toString());
                    user.put("Total2", FPTotalr2.getText().toString());
                    user.put("Total3", FPTotalr3.getText().toString());
                    user.put("Total4", FPTotalr4.getText().toString());
                    user.put("Total5", FPTotalr5.getText().toString());
                    user.put("Total6", FPTotalr6.getText().toString());

                    collectionReference.document("Judge 4").set(user);
                }
                break;
        }
    }


    public void nextPlayer(View view) {

        final AlertDialog.Builder buid = new AlertDialog.Builder(this);
        buid.setMessage("Continue to next player?");
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
                switch (view.getId()) {
                    case R.id.nextPolePlayer1:
                        Pole.setVisibility(View.GONE);
                        Pole2.setVisibility(View.VISIBLE);
                        playerName.setVisibility(View.GONE);
                        playerName2.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextPolePlayer2:
                        Pole2.setVisibility(View.GONE);
                        Pole3.setVisibility(View.VISIBLE);
                        playerName2.setVisibility(View.GONE);
                        playerName3.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextPolePlayer3:
                        Pole3.setVisibility(View.GONE);
                        Pole4.setVisibility(View.VISIBLE);
                        playerName3.setVisibility(View.GONE);
                        playerName4.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextPolePlayer4:
                        Pole4.setVisibility(View.GONE);
                        Pole5.setVisibility(View.VISIBLE);
                        playerName4.setVisibility(View.GONE);
                        playerName5.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextPolePlayer5:
                        Pole5.setVisibility(View.GONE);
                        Pole6.setVisibility(View.VISIBLE);
                        playerName5.setVisibility(View.GONE);
                        playerName6.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextHangingPlayer1:
                        Hanging.setVisibility(View.GONE);
                        Hanging2.setVisibility(View.VISIBLE);
                        playerNameh1.setVisibility(View.GONE);
                        playerNameh2.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextHangingPlayer12:
                        Hanging2.setVisibility(View.GONE);
                        Hanging3.setVisibility(View.VISIBLE);
                        playerNameh2.setVisibility(View.GONE);
                        playerNameh3.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextHangingPlayer13:
                        Hanging3.setVisibility(View.GONE);
                        Hanging4.setVisibility(View.VISIBLE);
                        playerNameh3.setVisibility(View.GONE);
                        playerNameh4.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextHangingPlayer14:
                        Hanging4.setVisibility(View.GONE);
                        Hanging5.setVisibility(View.VISIBLE);
                        playerNameh4.setVisibility(View.GONE);
                        playerNameh5.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextHangingPlayer15:
                        Hanging5.setVisibility(View.GONE);
                        Hanging6.setVisibility(View.VISIBLE);
                        playerNameh5.setVisibility(View.GONE);
                        playerNameh6.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextRopePlayer1:
                        Rope.setVisibility(View.GONE);
                        Rope2.setVisibility(View.VISIBLE);
                        playerNamer1.setVisibility(View.GONE);
                        playerNamer2.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextRopePlayer12:
                        Rope2.setVisibility(View.GONE);
                        Rope3.setVisibility(View.VISIBLE);
                        playerNamer2.setVisibility(View.GONE);
                        playerNamer3.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextRopePlayer13:
                        Rope3.setVisibility(View.GONE);
                        Rope4.setVisibility(View.VISIBLE);
                        playerNamer3.setVisibility(View.GONE);
                        playerNamer4.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextRopePlayer14:
                        Rope4.setVisibility(View.GONE);
                        Rope5.setVisibility(View.VISIBLE);
                        playerNamer4.setVisibility(View.GONE);
                        playerNamer5.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nextRopePlayer15:
                        Rope5.setVisibility(View.GONE);
                        Rope6.setVisibility(View.VISIBLE);
                        playerNamer5.setVisibility(View.GONE);
                        playerNamer6.setVisibility(View.VISIBLE);
                        break;



                }

            }
        });
        AlertDialog alertDialog = buid.create();
        alertDialog.show();


    }

    public void setPanel(View v){

        collectionReference = fStore.collection(compName.getText().toString().trim().toUpperCase())
                .document(typeSpin.getSelectedItem().toString()).collection(teamName.getText().toString().toUpperCase().trim())
                .document(RoomCode.getText().toString().toUpperCase().trim()).collection(ageSpin.getSelectedItem().toString());

        switch (v.getId()){
            case R.id.polePanel:

                if (compName.getText().length()!=0 && teamName.getText().length()!=0&&
                        date.getText().length()!=0 && ageSpin.getSelectedItemPosition()!=0 ){

                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Pole2.setVisibility(View.GONE);
                            Pole3.setVisibility(View.GONE);
                            Pole4.setVisibility(View.GONE);
                            Pole5.setVisibility(View.GONE);
                            Pole6.setVisibility(View.GONE);
                            Hanging.setVisibility(View.GONE);
                            Hanging2.setVisibility(View.GONE);
                            Hanging3.setVisibility(View.GONE);
                            Hanging4.setVisibility(View.GONE);
                            Hanging5.setVisibility(View.GONE);
                            Hanging6.setVisibility(View.GONE);
                            Rope.setVisibility(View.GONE);
                            Rope2.setVisibility(View.GONE);
                            Rope3.setVisibility(View.GONE);
                            Rope4.setVisibility(View.GONE);
                            Rope5.setVisibility(View.GONE);
                            Rope6.setVisibility(View.GONE);
                            Pole.setVisibility(View.VISIBLE);
                            playerName.setVisibility(View.VISIBLE);
                            playerNamer1.setVisibility(View.GONE);
                            playerNameh1.setVisibility(View.GONE);
                            RopePanel.setVisibility(View.GONE);
                            HangingPanel.setVisibility(View.GONE);
                            PolePanel.setVisibility(View.GONE);
                            compName.setEnabled(false);
                            teamName.setEnabled(false);
                            RoomCode.setEnabled(false);
                            ageSpin.setEnabled(false);
                            typeSpin.setEnabled(false);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Judge4.this, "Check the Details", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    Toast.makeText(this, "Enter the Details", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.hangingPanel:

                if (compName.getText().length()!=0 && teamName.getText().length()!=0&&
                        date.getText().length()!=0 && ageSpin.getSelectedItemPosition()!=0 ){

                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Pole2.setVisibility(View.GONE);
                            Pole3.setVisibility(View.GONE);
                            Pole4.setVisibility(View.GONE);
                            Pole5.setVisibility(View.GONE);
                            Pole6.setVisibility(View.GONE);
                            Hanging.setVisibility(View.VISIBLE);
                            Hanging2.setVisibility(View.GONE);
                            Hanging3.setVisibility(View.GONE);
                            Hanging4.setVisibility(View.GONE);
                            Hanging5.setVisibility(View.GONE);
                            Hanging6.setVisibility(View.GONE);
                            Rope.setVisibility(View.GONE);
                            Rope2.setVisibility(View.GONE);
                            Rope3.setVisibility(View.GONE);
                            Rope4.setVisibility(View.GONE);
                            Rope5.setVisibility(View.GONE);
                            Rope6.setVisibility(View.GONE);
                            Pole.setVisibility(View.GONE);
                            playerName.setVisibility(View.GONE);
                            playerNamer1.setVisibility(View.GONE);
                            playerNameh1.setVisibility(View.VISIBLE);
                            RopePanel.setVisibility(View.GONE);
                            HangingPanel.setVisibility(View.GONE);
                            PolePanel.setVisibility(View.GONE);
                            compName.setEnabled(false);
                            teamName.setEnabled(false);
                            RoomCode.setEnabled(false);
                            ageSpin.setEnabled(false);
                            typeSpin.setEnabled(false);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Judge4.this, "Check the Details", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    Toast.makeText(this, "Enter the Details", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.ropePanel:

                if (compName.getText().length()!=0 && teamName.getText().length()!=0&&
                        date.getText().length()!=0 && ageSpin.getSelectedItemPosition()!=0 ){

                    collectionReference.document("Chief Judge").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            Pole2.setVisibility(View.GONE);
                            Pole3.setVisibility(View.GONE);
                            Pole4.setVisibility(View.GONE);
                            Pole5.setVisibility(View.GONE);
                            Pole6.setVisibility(View.GONE);
                            Hanging.setVisibility(View.VISIBLE);
                            Hanging2.setVisibility(View.GONE);
                            Hanging3.setVisibility(View.GONE);
                            Hanging4.setVisibility(View.GONE);
                            Hanging5.setVisibility(View.GONE);
                            Hanging6.setVisibility(View.GONE);
                            Rope.setVisibility(View.GONE);
                            Rope2.setVisibility(View.GONE);
                            Rope3.setVisibility(View.GONE);
                            Rope4.setVisibility(View.GONE);
                            Rope5.setVisibility(View.GONE);
                            Rope6.setVisibility(View.GONE);
                            Pole.setVisibility(View.GONE);
                            playerName.setVisibility(View.GONE);
                            playerNamer1.setVisibility(View.VISIBLE);
                            playerNameh1.setVisibility(View.GONE);
                            RopePanel.setVisibility(View.GONE);
                            HangingPanel.setVisibility(View.GONE);
                            PolePanel.setVisibility(View.GONE);
                            compName.setEnabled(false);
                            teamName.setEnabled(false);
                            RoomCode.setEnabled(false);
                            ageSpin.setEnabled(false);
                            typeSpin.setEnabled(false);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Judge4.this, "Check the Details", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    Toast.makeText(this, "Enter the Details", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}