package com.jaytseng.urbanrace.sfuurbanrace;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class RaceListHome extends ActionBarActivity implements View.OnClickListener{

    Button compassButton, levelButton, gMapsButton, puzzleListButton, gameOneButton, gameTwoButton, gameThreeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_race_list_home);

        //initializing buttons
        compassButton = (Button)findViewById(R.id.compassButton);
        compassButton.setOnClickListener(this);

        levelButton = (Button)findViewById(R.id.levelButton);
        levelButton.setOnClickListener(this);

        gMapsButton = (Button)findViewById(R.id.gMapsButton);
        gMapsButton.setOnClickListener(this);

        puzzleListButton = (Button)findViewById(R.id.puzzleListButton);
        puzzleListButton.setOnClickListener(this);

        gameOneButton = (Button)findViewById(R.id.gameOneButton);
        gameOneButton.setOnClickListener(this);

        gameTwoButton = (Button)findViewById(R.id.gameTwoButton);
        gameTwoButton.setOnClickListener(this);

        gameThreeButton = (Button)findViewById(R.id.gameThreeButton);
        gameThreeButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_race_list_home, menu);
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
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.compassButton:
                intent.setClass(this, compass.class);
                startActivity(intent);
                break;
            case R.id.levelButton:
                intent.setClass(this, acceleroLevel.class);
                startActivity(intent);
                break;
            case R.id.gMapsButton:
                intent.setClass(this, gMaps.class);
                startActivity(intent);
                break;
            case R.id.gameOneButton:
                intent.setClass(this, Rules.class);
                startActivity(intent);
                break;
            case R.id.gameTwoButton:
                intent.setClass(this, levelTwo.class);
                startActivity(intent);
                break;
            case R.id.gameThreeButton:
                intent.setClass(this, raceList.class);
                startActivity(intent);
                break;
        }


    }
}
