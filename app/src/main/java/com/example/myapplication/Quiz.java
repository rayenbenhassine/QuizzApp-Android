package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

public class Quiz extends AppCompatActivity {
    private TextView theme;
    private TextView question;
    private RadioGroup props;
    private RadioButton prop1;
    private RadioButton prop2;
    private RadioButton prop3;
    private Button valider;

    private String name;
    private String selectedTheme;

    private Vector<SportQuestion> VectSport; //vecteur qui contient des questions en tant que objets
    private Vector<InformatiqueQuestion> vectInformatique; //vecteur qui contient des questions en tant que objets
    private Vector<HistoireQuestion> vectHistoire;
    private Vector<ArtQuestion> vectArt;


    private Vector<String> vectReponse; //vecteur qui contient les reponses de l'utilisateur
    private int score; //variable qui contient le score de l'utilisateur

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        this.theme = (TextView) findViewById(R.id.theme);
        this.question = (TextView) findViewById(R.id.question);
        this.props = (RadioGroup) findViewById(R.id.props);
        this.prop1 = (RadioButton) findViewById(R.id.prop1);
        this.prop2 = (RadioButton) findViewById(R.id.prop2);
        this.prop3 = (RadioButton) findViewById(R.id.prop3);
        this.valider = (Button) findViewById(R.id.valider);

        //instantiation des deux vectors
        this.VectSport = new Vector<>();
        this.vectInformatique = new Vector<>();
        this.vectHistoire = new Vector<>();
        this.vectArt = new Vector<>();

        this.vectReponse = new Vector<>();

        this.score = 0;

        Bundle extras = getIntent().getExtras();
        this.name = extras.getString("name");
        this.selectedTheme = extras.getString("selectedTheme");

        switch(selectedTheme){
            case "Sport" : {
                QuizSport(); //traiter le quiz Sport
                break;
            }
            case "Histoire" : {
                QuizHistoire();
                break;
            }
            case "Informatique" : {
                QuizInformatique();
                break;
            }
            case "Arts" : {
                QuizArt();
                break;
            }

        }
    }



    /*---------------------------------------------------------Sport-------------------------------------------------------------*/
    public void QuizSport(){
        this.theme.setText("Theme : Sport");
        initVectorSport();

        afficherQuestionSport(this.VectSport.get(0));
        this.valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                traitQuestionSport(VectSport.get(0).getReponse(),props.getCheckedRadioButtonId());

                afficherQuestionSport(VectSport.get(1));
                valider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        traitQuestionSport(VectSport.get(1).getReponse(),props.getCheckedRadioButtonId());

                        afficherQuestionSport(VectSport.get(2));
                        valider.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                traitQuestionSport(VectSport.get(2).getReponse(),props.getCheckedRadioButtonId());

                                afficherQuestionSport(VectSport.get(3));
                                valider.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        traitQuestionSport(VectSport.get(3).getReponse(),props.getCheckedRadioButtonId());

                                        afficherQuestionSport(VectSport.get(4));
                                        valider.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                traitQuestionSport(VectSport.get(4).getReponse(),props.getCheckedRadioButtonId());

                                                Intent intent = new Intent(getApplicationContext(), Score.class);
                                                intent.putExtra("score", score);
                                                intent.putExtra("name", name);

                                                ArrayList<String> reponsesUtilisateur = new ArrayList<String>();
                                                for(String reponse : vectReponse){
                                                    reponsesUtilisateur.add(reponse);
                                                }
                                                intent.putExtra("reponsesUtilisateur", reponsesUtilisateur);

                                                ArrayList<String> reponsesCorrecte = new ArrayList<>();
                                                ArrayList<String> questions = new ArrayList<>();
                                                for(SportQuestion q : VectSport){
                                                    questions.add(q.getQuestion());
                                                    reponsesCorrecte.add(q.getReponse());
                                                }
                                                intent.putExtra("questions", questions);
                                                intent.putExtra("reponsesCorrecte", reponsesCorrecte);
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }

    public void traitQuestionSport(String reponseCorrecte, int selectedReponseId){
        String reponse = ((RadioButton)findViewById(selectedReponseId)).getText().toString();

        //1) ajouter reponse utilisateur dans le Vector reponseUtilisateur
        vectReponse.add(reponse);
        //2) MAJ du score
        calculScore(reponseCorrecte,reponse);
        //3) reset activity
        if(!reponseCorrecte.equals(VectSport.get(VectSport.size()-1).getReponse()))
            resetActivity();
    }

    public void calculScore(String reponseCorrect, String reponseUtilisateur){
        if(reponseUtilisateur.equals(reponseCorrect)){
            this.score = this.score + 2;
        }
    }

    public void initVectorSport(){
        SportQuestion q1 = new SportQuestion("Qui est Zlatan Ibrahimovic ?", "Un joueur de football suédois", "Un joueur de football serbe", "Un joueur de football croate", "Un joueur de football suédois");
        SportQuestion q2 = new SportQuestion("Quelle est la périodicité des jeux Olympiques d’été ?", "Tous les deux ans", "Tous les trois ans", "Tous les quatre ans", "Tous les quatre ans");
        SportQuestion q3 = new SportQuestion("Quel nom porte un terrain de tennis ?", "La surface", "La terre battue", "Le court", "Le court");
        SportQuestion q4 = new SportQuestion("Où auront lieu les jeux Olympiques d’été en 2016 ?", "Rio de Janeiro", "Doha", "Chicago", "Rio de Janeiro");
        SportQuestion q5 = new SportQuestion("Lors du Tour de France cycliste, qui est récompensé d’un maillot blanc à pois rouges ?", "Le meilleur jeune coureur", "Le meilleur sprinteur", "Le meilleur grimpeur", "Le meilleur grimpeur");
        this.VectSport.add(q1);
        this.VectSport.add(q2);
        this.VectSport.add(q3);
        this.VectSport.add(q4);
        this.VectSport.add(q5);
    }

    public void afficherQuestionSport(SportQuestion q){
        this.question.setText(q.getQuestion());
        this.prop1.setText(q.getProp1());
        this.prop2.setText(q.getProp2());
        this.prop3.setText(q.getProp3());

    }
    public void resetActivity(){
        this.question.setText("");
        this.prop1.setText("");
        this.prop2.setText("");
        this.prop3.setText("");
    }

    /*---------------------------------------------------------Informatique-------------------------------------------------------------*/
    public void QuizInformatique(){
        this.theme.setText("Theme : Informatique");
        initVectorInformatique();

        afficherQuestionInformatique(this.vectInformatique.get(0));
        this.valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                traitQuestionInformatique(vectInformatique.get(0).getReponse(),props.getCheckedRadioButtonId());

                afficherQuestionInformatique(vectInformatique.get(1));
                valider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        traitQuestionInformatique(vectInformatique.get(1).getReponse(),props.getCheckedRadioButtonId());

                        afficherQuestionInformatique(vectInformatique.get(2));
                        valider.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                traitQuestionInformatique(vectInformatique.get(2).getReponse(),props.getCheckedRadioButtonId());

                                afficherQuestionInformatique(vectInformatique.get(3));
                                valider.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        traitQuestionInformatique(vectInformatique.get(3).getReponse(),props.getCheckedRadioButtonId());

                                        afficherQuestionInformatique(vectInformatique.get(4));
                                        valider.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                traitQuestionInformatique(vectInformatique.get(4).getReponse(),props.getCheckedRadioButtonId());

                                                Intent intent = new Intent(getApplicationContext(), Score.class);
                                                intent.putExtra("score", score);
                                                intent.putExtra("name", name);

                                                ArrayList<String> reponsesUtilisateur = new ArrayList<String>();
                                                for(String reponse : vectReponse){
                                                    reponsesUtilisateur.add(reponse);
                                                }
                                                intent.putExtra("reponsesUtilisateur", reponsesUtilisateur);

                                                ArrayList<String> reponsesCorrecte = new ArrayList<>();
                                                ArrayList<String> questions = new ArrayList<>();
                                                for(InformatiqueQuestion q : vectInformatique){
                                                    questions.add(q.getQuestion());
                                                    reponsesCorrecte.add(q.getReponse());
                                                }
                                                intent.putExtra("questions", questions);
                                                intent.putExtra("reponsesCorrecte", reponsesCorrecte);




                                                startActivity(intent);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

    }

    public void traitQuestionInformatique(String reponseCorrecte, int selectedReponseId){
        String reponse = ((RadioButton)findViewById(selectedReponseId)).getText().toString();

        //1) ajouter reponse utilisateur dans le Vector reponseUtilisateur
        vectReponse.add(reponse);
        //2) MAJ du score
        calculScore(reponseCorrecte,reponse);
        //3) reset activity
        if(!reponseCorrecte.equals(vectInformatique.get(vectInformatique.size()-1).getReponse()))
            resetActivity();
    }



    public void initVectorInformatique(){
        InformatiqueQuestion q1 = new InformatiqueQuestion("Android c'est :", "Un système d'exploitation", "Un virus", "Un logiciel", "Un système d'exploitation");
        InformatiqueQuestion q2 = new InformatiqueQuestion("Quel noyau utilise Android ?", "IOS", "Dalvik", "Linux", "Linux");
        InformatiqueQuestion q3 = new InformatiqueQuestion("Quel est le nom de la machine virtuelle d'Android ?", "Java", "KitKat", "Dalvik", "Dalvik");
        InformatiqueQuestion q4 = new InformatiqueQuestion("Comment s'appelle le petit personnage du logo d'Android ?", "Bugdroid", "Android", "Bastien", "Bugdroid");
        InformatiqueQuestion q5 = new InformatiqueQuestion("Quelle est la dernière version d'Android (en mai 2015) ?", "4.9", "4.4.9", "5.1.1 (Lollipop)", "5.1.1 (Lollipop)");
        this.vectInformatique.add(q1);
        this.vectInformatique.add(q2);
        this.vectInformatique.add(q3);
        this.vectInformatique.add(q4);
        this.vectInformatique.add(q5);

    }

    public void afficherQuestionInformatique(InformatiqueQuestion q){
        this.question.setText(q.getQuestion());
        this.prop1.setText(q.getProp1());
        this.prop2.setText(q.getProp2());
        this.prop3.setText(q.getProp3());

    }



    /*---------------------------------------------------------Histoire-------------------------------------------------------------*/
    public void QuizHistoire(){
        this.theme.setText("Theme : Histoire");
        initVectorHistoire();

        afficherQuestionHistoire(this.vectHistoire.get(0));
        Log.d("test","test");
        this.valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                traitQuestionHistoire(vectHistoire.get(0).getReponse(),props.getCheckedRadioButtonId());

                afficherQuestionHistoire(vectHistoire.get(1));
                valider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        traitQuestionHistoire(vectHistoire.get(1).getReponse(),props.getCheckedRadioButtonId());

                        afficherQuestionHistoire(vectHistoire.get(2));
                        valider.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                traitQuestionHistoire(vectHistoire.get(2).getReponse(),props.getCheckedRadioButtonId());

                                afficherQuestionHistoire(vectHistoire.get(3));
                                valider.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        traitQuestionHistoire(vectHistoire.get(3).getReponse(),props.getCheckedRadioButtonId());

                                        afficherQuestionHistoire(vectHistoire.get(4));
                                        valider.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                traitQuestionHistoire(vectHistoire.get(4).getReponse(),props.getCheckedRadioButtonId());

                                                Intent intent = new Intent(getApplicationContext(), Score.class);
                                                intent.putExtra("score", score);
                                                intent.putExtra("name", name);

                                                ArrayList<String> reponsesUtilisateur = new ArrayList<String>();
                                                for(String reponse : vectReponse){
                                                    reponsesUtilisateur.add(reponse);
                                                }
                                                intent.putExtra("reponsesUtilisateur", reponsesUtilisateur);

                                                ArrayList<String> reponsesCorrecte = new ArrayList<>();
                                                ArrayList<String> questions = new ArrayList<>();
                                                for(HistoireQuestion q : vectHistoire){
                                                    questions.add(q.getQuestion());
                                                    reponsesCorrecte.add(q.getReponse());
                                                }
                                                intent.putExtra("questions", questions);

                                                intent.putExtra("reponsesCorrecte", reponsesCorrecte);
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

    }

    public void traitQuestionHistoire(String reponseCorrecte, int selectedReponseId){
        String reponse = ((RadioButton)findViewById(selectedReponseId)).getText().toString();

        //1) ajouter reponse utilisateur dans le Vector reponseUtilisateur
        vectReponse.add(reponse);
        //2) MAJ du score
        calculScore(reponseCorrecte,reponse);
        //3) reset activity
        if(!reponseCorrecte.equals(vectHistoire.get(vectHistoire.size()-1).getReponse()))
            resetActivity();
    }



    public void initVectorHistoire(){
        HistoireQuestion q1 = new HistoireQuestion("Quel événement marque la fin de la Préhistoire et le début de l’Antiquité ?", "La sédentarisation de l’Homme", "La première monnaie d’échange", "L’apparition de l’écriture", "L’apparition de l’écriture");
        HistoireQuestion q2 = new HistoireQuestion("Quel est le premier peuple à obtenir le droit de vote des citoyens ?", "Les Romains", "Les Athéniens", "Les Scandinaves", "Les Athéniens");
        HistoireQuestion q3 = new HistoireQuestion("Qui est l’ennemi de la France pendant la guerre de Cent ans ?", "L’Angleterre", "L’Espagne", "Les Huns", "L’Angleterre");
        HistoireQuestion q4 = new HistoireQuestion("Qui découvre en premier que la Terre tourne autour du soleil ?", "Copernic", "Galilée", "Newton", "Copernic");
        HistoireQuestion q5 = new HistoireQuestion("En quelle année a été détruit le mur de Berlin ?", "1978", "1982", "1989", "1989");
        this.vectHistoire.add(q1);
        this.vectHistoire.add(q2);
        this.vectHistoire.add(q3);
        this.vectHistoire.add(q4);
        this.vectHistoire.add(q5);

    }

    public void afficherQuestionHistoire(HistoireQuestion q){
        this.question.setText(q.getQuestion());
        this.prop1.setText(q.getProp1());
        this.prop2.setText(q.getProp2());
        this.prop3.setText(q.getProp3());

    }

    /*---------------------------------------------------------Art-------------------------------------------------------------*/
    public void QuizArt(){
        this.theme.setText("Theme : Art");
        initVectorArt();

        afficherQuestionArt(this.vectArt.get(0));
        Log.d("test","test");
        this.valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                traitQuestionArt(vectArt.get(0).getReponse(),props.getCheckedRadioButtonId());

                afficherQuestionArt(vectArt.get(1));
                valider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        traitQuestionArt(vectArt.get(1).getReponse(),props.getCheckedRadioButtonId());

                        afficherQuestionArt(vectArt.get(2));
                        valider.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                traitQuestionArt(vectArt.get(2).getReponse(),props.getCheckedRadioButtonId());

                                afficherQuestionArt(vectArt.get(3));
                                valider.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        traitQuestionArt(vectArt.get(3).getReponse(),props.getCheckedRadioButtonId());

                                        afficherQuestionArt(vectArt.get(4));
                                        valider.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                traitQuestionArt(vectArt.get(4).getReponse(),props.getCheckedRadioButtonId());

                                                Intent intent = new Intent(getApplicationContext(), Score.class);
                                                intent.putExtra("score", score);
                                                intent.putExtra("name", name);
                                                ArrayList<String> reponsesUtilisateur = new ArrayList<String>();
                                                for(String reponse : vectReponse){
                                                    reponsesUtilisateur.add(reponse);
                                                }
                                                intent.putExtra("reponsesUtilisateur", reponsesUtilisateur);

                                                ArrayList<String> reponsesCorrecte = new ArrayList<>();
                                                ArrayList<String> questions = new ArrayList<>();
                                                for(ArtQuestion q : vectArt){
                                                    questions.add(q.getQuestion());
                                                    reponsesCorrecte.add(q.getReponse());
                                                }
                                                intent.putExtra("questions", questions);
                                                intent.putExtra("reponsesCorrecte", reponsesCorrecte);

                                                startActivity(intent);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });

    }

    public void traitQuestionArt(String reponseCorrecte, int selectedReponseId){
        String reponse = ((RadioButton)findViewById(selectedReponseId)).getText().toString();

        //1) ajouter reponse utilisateur dans le Vector reponseUtilisateur
        vectReponse.add(reponse);
        //2) MAJ du score
        calculScore(reponseCorrecte,reponse);
        //3) reset activity
        if(!reponseCorrecte.equals(vectArt.get(vectArt.size()-1).getReponse()))
            resetActivity();
    }



    public void initVectorArt(){
        ArtQuestion q1 = new ArtQuestion("Quel est le titre du second album studio de Stromae, sortie en 2013 ?", "Formidable", "Racine carrée", "Pourquoi pas moi", "Racine carrée");
        ArtQuestion q2 = new ArtQuestion("De quel pays, les Beatles sont-ils originaires ?", "États-Unis", "Allemagne", "Angleterre", "Angleterre");
        ArtQuestion q3 = new ArtQuestion("Qui c'est qui a peint la Joconde ?", "Leonardo da Vinci", "Vincent van Gogh", "Salvador Dalí", "Leonardo da Vinci");
        ArtQuestion q4 = new ArtQuestion("Quel est le métier de Frida Kahlo ?", "Ecrivain", "Peintre", "Musicien", "Peintre");
        ArtQuestion q5 = new ArtQuestion("Quand a été écrit Les Misérables ?", "1848", "1845", "1844", "1848");
        this.vectArt.add(q1);
        this.vectArt.add(q2);
        this.vectArt.add(q3);
        this.vectArt.add(q4);
        this.vectArt.add(q5);

    }

    public void afficherQuestionArt(ArtQuestion q){
        this.question.setText(q.getQuestion());
        this.prop1.setText(q.getProp1());
        this.prop2.setText(q.getProp2());
        this.prop3.setText(q.getProp3());

    }

}