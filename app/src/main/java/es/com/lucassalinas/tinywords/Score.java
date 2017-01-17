package es.com.lucassalinas.tinywords;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        MySQLiteHelper db = new MySQLiteHelper(this);

        text = (TextView) findViewById(R.id.text);
        String puntuacionTotal = "";

        if(db.getScore(1).getScore() > 0){
            puntuacionTotal += "Max. Level "+ db.getScore(1).getId()+": "+db.getScore(1).getScore();
        }
        text.setText(puntuacionTotal);
    }
}
