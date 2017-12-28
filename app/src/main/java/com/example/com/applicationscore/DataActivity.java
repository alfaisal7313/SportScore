package com.example.com.applicationscore;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;

public class DataActivity extends AppCompatActivity {

    private MaterialEditText sport_name, team_name1, team_name2, team_desc1, team_desc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        sport_name = findViewById(R.id.name_sport);
        team_name1 = findViewById(R.id.team_name1);
        team_name2 = findViewById(R.id.team_name2);
        team_desc1 = findViewById(R.id.team_desc1);
        team_desc2 = findViewById(R.id.team_desc2);
    }

    public void goActivity(View view){

        if (TextUtils.isEmpty(sport_name.getText())){
            sport_name.setError("Please enter sport name");
        }else if (TextUtils.isEmpty(team_name1.getText())){
            team_name1.setError("Please enter team name 1");
        } else if (TextUtils.isEmpty(team_name2.getText())){
            team_name2.setError("Please enter team name 2");
        } else {
            String sName, nTeam1, nTeam2, tDesc1, tDesc2;
            sName = sport_name.getText().toString();
            nTeam1 = team_name1.getText().toString();
            nTeam2 = team_name2.getText().toString();
            tDesc1 = team_desc1.getText().toString();
            tDesc2 = team_desc2.getText().toString();

            Intent intent = new Intent(DataActivity.this, MainActivity.class);
            intent.putExtra("name", sName);
            intent.putExtra("team1", nTeam1);
            intent.putExtra("team2", nTeam2);
            intent.putExtra("desc1", tDesc1);
            intent.putExtra("desc2", tDesc2);
            startActivity(intent);
            finish();
        }
    }

}
