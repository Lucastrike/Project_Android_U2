package es.com.lucassalinas.tinywords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Comida extends AppCompatActivity {

    ViewFlipper flipper;
    EditText edit;
    Button next;

    private MySQLiteHelper db;
    private int myScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida);

        flipper = (ViewFlipper) findViewById(R.id.flipper);
        edit = (EditText) findViewById(R.id.edit);
        next = (Button) findViewById(R.id.next);

        db = new MySQLiteHelper(this);
        myScore = 0;

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (flipper.getDisplayedChild()) {
                    case 0: if (edit.getText().toString().equals("pizza")){
                        Toast.makeText(getApplicationContext(), "Well done!!!", Toast.LENGTH_SHORT).show();
                        flipper.showNext();
                        edit.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Try again!", Toast.LENGTH_SHORT).show();
                        edit.setText("");
                    }
                        break;
                    case 1: if (edit.getText().toString().equals("meet")){
                        Toast.makeText(getApplicationContext(), "Well done!!!", Toast.LENGTH_SHORT).show();
                        flipper.showNext();
                        edit.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Try again!", Toast.LENGTH_SHORT).show();
                        edit.setText("");
                    }
                        break;
                    case 2: if (edit.getText().toString().equals("fish")){
                        Toast.makeText(getApplicationContext(), "Well done!!!", Toast.LENGTH_SHORT).show();
                        flipper.showNext();
                        edit.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Try again!", Toast.LENGTH_SHORT).show();
                        edit.setText("");
                    }
                        break;
                    case 3: if (edit.getText().toString().equals("hamburger")){
                        Toast.makeText(getApplicationContext(), "Well done!!!", Toast.LENGTH_SHORT).show();
                        myScore = 50;
                        if (myScore >= db.getScore(1).getScore()) {
                            db.updateScore(new Users(null, null, myScore));
                        }
                        Intent intent = new Intent(Comida.this, Main2Activity.class);
                        startActivity(intent);
                        Comida.this.finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Try again!", Toast.LENGTH_SHORT).show();
                        edit.setText("");
                    }
                        break;
                }
            }
        });

    }
}
