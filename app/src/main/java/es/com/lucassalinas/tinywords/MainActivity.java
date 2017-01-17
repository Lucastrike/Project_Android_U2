package es.com.lucassalinas.tinywords;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static EditText user;
    static EditText password;
    static CheckBox rembrPass;
    static String pass = "";
    static MySQLiteHelper db;
    SQLiteDatabase midb;
    Button start;

    EditText login, passLogin;

    static int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        start = (Button) findViewById(R.id.start);
        db = new MySQLiteHelper(this);
        midb = db.getWritableDatabase();

        login = (EditText) findViewById(R.id.login);
        passLogin = (EditText) findViewById(R.id.passLogin);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.compobarlogin(login.getText().toString(), passLogin.getText().toString())) {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("nameUser", login.getText().toString());
                    startActivity(intent);
                    MainActivity.this.finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogFragment dialogA = new MyDialogFragment();
                dialogA.show(getSupportFragmentManager(), "MyAlert");
            }
        });

    }

    public static class MyDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            //View loginDialog = getLayoutInflater().inflate(R.layout.custom_dialog, null);
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View v = inflater.inflate(R.layout.custom_dialog, null);

            user = (EditText) v.findViewById(R.id.user);
            password = (EditText) v.findViewById(R.id.password);
            //rembrPass = (CheckBox) v.findViewById(R.id.rembrPass);

            /*if (pass != ""){
                password.setText(pass);
            }*/

            builder.setView(v)
                    .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            /*if (rembrPass.isChecked()){
                                pass = password.getText().toString();
                            }*/

                            if (db.compobaruser(user.getText().toString())) {
                                Toast.makeText(getActivity(), "Error, usuario existente!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                db.addUser(new Users(user.getText().toString(), password.getText().toString(), 0));
                            }

                            //SET UP
                            user.setText("");
                            password.setText("");
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

}
