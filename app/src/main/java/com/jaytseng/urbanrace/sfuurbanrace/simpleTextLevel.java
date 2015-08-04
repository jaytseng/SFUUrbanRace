package com.jaytseng.urbanrace.sfuurbanrace;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class simpleTextLevel extends AppCompatActivity implements View.OnClickListener {
    TextView puzzleHeading, puzzleSubHeading, puzzleBody;
    EditText puzzleSolution;
    Button hintButton, solveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_text_level);

        puzzleHeading = (TextView)findViewById(R.id.puzzleHeading);
        puzzleSubHeading = (TextView)findViewById(R.id.puzzleSubheading);
        puzzleBody = (TextView)findViewById(R.id.puzzleBody);

        puzzleSolution = (EditText)findViewById(R.id.enterSolutionField);

        hintButton = (Button)findViewById(R.id.hintButton);
        solveButton = (Button) findViewById(R.id.solveButton);
        hintButton.setOnClickListener(this);
        solveButton.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();

        puzzleHeading.setText("Translate to Location - Room Number");
        puzzleSubHeading.setText("電腦\ndeuxième étage\nThe Now Newspaper");
        puzzleBody.setText("Translate the clue above and find the number of the room." +
                "Remember, you must photograph yourself and your team in front of the solution.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_four, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hintButton:
                String url = "http://translate.google.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;
            case R.id.solveButton:
                String enteredSoln = puzzleSolution.getText().toString();
                Toast.makeText(this, "entered solution" + enteredSoln, Toast.LENGTH_LONG).show();
                String solution = "3310";
                if (enteredSoln.equals(solution)){
                    Toast.makeText(this, "Correct", Toast.LENGTH_LONG).show();
                    Intent j = new Intent(this, RaceListHome.class);
                    j.putExtra("Result", "correct");
                    startActivity(j);
                } else {
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
