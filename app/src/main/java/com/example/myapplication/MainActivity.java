package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtName;
    private RadioGroup theme;
    private Button btnValider;
    private String name;
    private String selectedTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txtName = (TextView) findViewById(R.id.name);
        this.theme = (RadioGroup) findViewById((R.id.theme));
        this.btnValider = (Button) findViewById(R.id.btnValider);

        btnValider.setEnabled(false);
        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btnValider.setEnabled(!editable.toString().isEmpty());
            }
        });

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = txtName.getText().toString();
                selectedTheme = ((RadioButton)findViewById(theme.getCheckedRadioButtonId())).getText().toString();
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("name",name);
                intent.putExtra("selectedTheme",selectedTheme);

                startActivity(intent);

            }
        });
    }


}