package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

public class Score extends AppCompatActivity {

    private TextView txtName;
    private TextView txtScore;
    private TextView txtMention;
    private Button btnTryAgain;

    private TextView question1;
    private TextView reponseUtilisateur1;
    private TextView reponseCorrecte1;

    private TextView question2;
    private TextView reponseUtilisateur2;
    private TextView reponseCorrecte2;

    private TextView question3;
    private TextView reponseUtilisateur3;
    private TextView reponseCorrecte3;

    private TextView question4;
    private TextView reponseUtilisateur4;
    private TextView reponseCorrecte4;

    private TextView question5;
    private TextView reponseUtilisateur5;
    private TextView reponseCorrecte5;




    private String name;
    private int score;
    private ArrayList<String> reponsesUtilisateur;
    private ArrayList<String> reponsesCorrecte;
    private ArrayList<String> questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        this.txtName = (TextView) findViewById(R.id.txtName);
        this.txtScore = (TextView) findViewById(R.id.txtScore);
        this.txtMention = (TextView) findViewById(R.id.txtMention);
        this.btnTryAgain = (Button) findViewById(R.id.btnTryAgain);

        this.question1 = (TextView) findViewById(R.id.question1);
        this.question2 = (TextView) findViewById(R.id.question2);
        this.question3 = (TextView) findViewById(R.id.question3);
        this.question4 = (TextView) findViewById(R.id.question4);
        this.question5 = (TextView) findViewById(R.id.question5);


        this.reponseUtilisateur1 = (TextView) findViewById(R.id.reponseUtilisateur1);
        this.reponseUtilisateur2 = (TextView) findViewById(R.id.reponseUtilisateur2);
        this.reponseUtilisateur3 = (TextView) findViewById(R.id.reponseUtilisateur3);
        this.reponseUtilisateur4 = (TextView) findViewById(R.id.reponseUtilisateur4);
        this.reponseUtilisateur5 = (TextView) findViewById(R.id.reponseUtilisateur5);

        this.reponseCorrecte1 = (TextView) findViewById(R.id.reponseCorrecte1);
        this.reponseCorrecte2 = (TextView) findViewById(R.id.reponseCorrecte2);
        this.reponseCorrecte3 = (TextView) findViewById(R.id.reponseCorrecte3);
        this.reponseCorrecte4 = (TextView) findViewById(R.id.reponseCorrecte4);
        this.reponseCorrecte5 = (TextView) findViewById(R.id.reponseCorrecte5);


        Bundle extras = getIntent().getExtras();

        this.reponsesUtilisateur = (ArrayList<String>) extras.get("reponsesUtilisateur");
        this.reponsesCorrecte = (ArrayList<String>) extras.get("reponsesCorrecte");
        this.questions = (ArrayList<String>) extras.get("questions");




        this.question1.setText("Question 1 : " + questions.get(0));
        this.question2.setText("Question 2 : " + questions.get(1));
        this.question3.setText("Question 3 : " + questions.get(2));
        this.question4.setText("Question 4 : " + questions.get(3));
        this.question5.setText("Question 5 : " + questions.get(4));

        this.reponseUtilisateur1.setText("Votre réponse : " + reponsesUtilisateur.get(0));
        if(!this.reponsesUtilisateur.get(0).equals(this.reponsesCorrecte.get(0))){
            this.reponseUtilisateur1.setTextColor(Color.rgb(255,0,0));
        }
        else{
            this.reponseUtilisateur1.setTextColor(Color.rgb(0,255,0));
        }

        this.reponseUtilisateur2.setText("Votre réponse : " + reponsesUtilisateur.get(1));
        if(!this.reponsesUtilisateur.get(1).equals(this.reponsesCorrecte.get(1))){
            this.reponseUtilisateur2.setTextColor(Color.rgb(255,0,0));
        }
        else{
            this.reponseUtilisateur2.setTextColor(Color.rgb(0,255,0));
        }
        this.reponseUtilisateur3.setText("Votre réponse : " + reponsesUtilisateur.get(2));
        if(!this.reponsesUtilisateur.get(2).equals(this.reponsesCorrecte.get(2))){
            this.reponseUtilisateur3.setTextColor(Color.rgb(255,0,0));
        }
        else{
            this.reponseUtilisateur3.setTextColor(Color.rgb(0,255,0));
        }

        this.reponseUtilisateur4.setText("Votre réponse : " + reponsesUtilisateur.get(3));
        if(!this.reponsesUtilisateur.get(3).equals(this.reponsesCorrecte.get(3))){
            this.reponseUtilisateur4.setTextColor(Color.rgb(255,0,0));
        }
        else{
            this.reponseUtilisateur4.setTextColor(Color.rgb(0,255,0));
        }

        this.reponseUtilisateur5.setText("Votre réponse : " + reponsesUtilisateur.get(4));
        if(!this.reponsesUtilisateur.get(4).equals(this.reponsesCorrecte.get(4))){
            this.reponseUtilisateur5.setTextColor(Color.rgb(255,0,0));
        }
        else{
            this.reponseUtilisateur5.setTextColor(Color.rgb(0,255,0));
        }

        this.reponseCorrecte1.setText("Réponse correcte : " + reponsesCorrecte.get(0));
        this.reponseCorrecte2.setText("Réponse correcte : " + reponsesCorrecte.get(1));
        this.reponseCorrecte3.setText("Réponse correcte : " + reponsesCorrecte.get(2));
        this.reponseCorrecte4.setText("Réponse correcte : " + reponsesCorrecte.get(3));
        this.reponseCorrecte5.setText("Réponse correcte : " + reponsesCorrecte.get(4));


        this.name = extras.getString("name");
        this.score = extras.getInt("score");
        this.txtName.setText("Joueur : " + this.name.substring(0,1).toUpperCase() + this.name.substring(1).toLowerCase());
        this.txtScore.setText(Integer.toString(this.score) + " / 10");
        if(score == 0){
            this.txtMention.setText("Aucune reponse correcte");
        }
        else if(score >= 1 && score <= 4){
            this.txtMention.setText("Passable");
        }
        else if(score >= 5 && score <= 7){
            this.txtMention.setText("Bien");
        }
        else{
            this.txtMention.setText("Excellent !");
        }

        this.btnTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });

    }
}