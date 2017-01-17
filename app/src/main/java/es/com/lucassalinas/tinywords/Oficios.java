package es.com.lucassalinas.tinywords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Oficios extends AppCompatActivity {

    ViewFlipper flipper;
    EditText edit;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oficios);

        flipper = (ViewFlipper) findViewById(R.id.flipper);
        edit = (EditText) findViewById(R.id.edit);
        next = (Button) findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (flipper.getDisplayedChild()) {
                    case 0: if (edit.getText().toString().equals("police")){
                        Toast.makeText(getApplicationContext(), "Well done!!!", Toast.LENGTH_SHORT).show();
                        flipper.showNext();
                        edit.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Try again!", Toast.LENGTH_SHORT).show();
                        edit.setText("");
                    }
                        break;
                    case 1: if (edit.getText().toString().equals("plumber")){
                        Toast.makeText(getApplicationContext(), "Well done!!!", Toast.LENGTH_SHORT).show();
                        flipper.showNext();
                        edit.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Try again!", Toast.LENGTH_SHORT).show();
                        edit.setText("");
                    }
                        break;
                    case 2: if (edit.getText().toString().equals("firefighter")){
                        Toast.makeText(getApplicationContext(), "Well done!!!", Toast.LENGTH_SHORT).show();
                        flipper.showNext();
                        edit.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Try again!", Toast.LENGTH_SHORT).show();
                        edit.setText("");
                    }
                        break;
                    case 3: if (edit.getText().toString().equals("worker")){
                        Toast.makeText(getApplicationContext(), "Well done!!!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Oficios.this, Main2Activity.class);
                        startActivity(intent);
                        Oficios.this.finish();
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
