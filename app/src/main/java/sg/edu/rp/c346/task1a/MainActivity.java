package sg.edu.rp.c346.task1a;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etname, etage;
    Spinner spClass;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etage = findViewById(R.id.editText2);
        etname = findViewById(R.id.editText);
        spClass = findViewById(R.id.spinner);
        btnSave = findViewById(R.id.button);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT);
                toast.show();
                Integer spinner = spClass.getSelectedItemPosition();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putInt("spinner", spinner);
                prefEdit.commit();

            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();

        //step1a: Get the user input from the EditText and store it in a variable
        String name = etname.getText().toString();
        Integer age = Integer.parseInt(etage.getText().toString());
        //Step 1b: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 1c: Obtain an instance of the SharedPreferences Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        //Step 1d: Add the key value pair
        prefEdit.putString("name", name);
        prefEdit.putInt("age", age);
        //Step 1e: Call commit() method to save the changes into the SharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 2b: Retrieve the saved data with the key "greeting" from the SharedPreference object
        String msg = prefs.getString("name", "");
        Integer age = prefs.getInt("age",0);
        Integer position = prefs.getInt("spinner", 0);
        etname.setText(msg);
        if (age == 0){
            etage.setText("");
        }
        else{
            etage.setText(String.valueOf(age));
        }
        spClass.setSelection(position);
    }

}
