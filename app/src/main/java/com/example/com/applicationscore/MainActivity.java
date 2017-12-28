package com.example.com.applicationscore;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int score1 = 0;
    private int score2 = 0;
    private int nightMode = AppCompatDelegate.getDefaultNightMode();
    private StateListDrawable stateListDrawable;

    private String sport_name, team_name1, team_name2, team_desc1, team_desc2;

    private TextView tv_score1, tv_score2, tvName1, tvName2, tvDesc1, tvDesc2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent data = getIntent();
        sport_name = data.getStringExtra("name");
        team_name1 = data.getStringExtra("team1");
        team_name2 = data.getStringExtra("team2");
        team_desc1 = data.getStringExtra("desc1");
        team_desc2 = data.getStringExtra("desc2");

        getSupportActionBar().setTitle(sport_name + " Score");

        initView();

        if (savedInstanceState != null) {
            score1 = savedInstanceState.getInt("data1");
            score2 = savedInstanceState.getInt("data2");
            tv_score1.setText(String.valueOf(score1));
            tv_score2.setText(String.valueOf(score2));
        }
    }

    private void initView() {
        stateListDrawable = new StateListDrawable();
        tv_score1 = findViewById(R.id.score1);
        tv_score2 = findViewById(R.id.score2);
        tvName1 = findViewById(R.id.tv_team_name1);
        tvName2 = findViewById(R.id.tv_team_name2);
        tvDesc1 = findViewById(R.id.tv_team_desc1);
        tvDesc2 = findViewById(R.id.tv_team_desc2);

        tvName1.setText(team_name1);
        tvName2.setText(team_name2);
        tvDesc1.setText(team_desc1);
        tvDesc2.setText(team_desc2);

        if (tvDesc1 != null || tvDesc2 !=null){
            tvDesc1.setVisibility(View.VISIBLE);
            tvDesc2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
    outState.putInt("data1", score1);
    outState.putInt("data2", score2);

    super.onSaveInstanceState(outState);
    }

    public void increaseScore(View view){

        switch (view.getId()){
            case R.id.increaseTeam1:
                score1++;
                tv_score1.setText(String.valueOf(score1));
                if(nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                break;

            case R.id.increaseTeam2:
                score2++;
                tv_score2.setText(String.valueOf(score2));
                break;
        }
    }

    public void descreaseScore(View view){
        switch (view.getId()){
            case R.id.decreaseTeam1:
                score1--;
                tv_score1.setText(String.valueOf(score1));

                if (score1 <= 0){
                    tv_score1.setText(String.valueOf(score1 = 0));
                }
                break;

            case R.id.decreaseTeam2:
                score2--;
                tv_score2.setText(String.valueOf(score2));

                if (score2 <= 0){
                    tv_score2.setText(String.valueOf(score2 = 0));
                }
                break;
        }
    }

    public void showResults (View view){
        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
        intent.putExtra("score1", String.valueOf(score1));
        intent.putExtra("score2", String.valueOf(score2));
        intent.putExtra("team1", String.valueOf(team_name1));
        intent.putExtra("team2", String.valueOf(team_name2));
        intent.putExtra("desc2", String.valueOf(team_desc2));
        intent.putExtra("desc1", String.valueOf(team_desc2));
        intent.putExtra("name", String.valueOf(sport_name));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.reset_score:
                tv_score1.setText(String.valueOf(score1 = 0));
                tv_score2.setText(String.valueOf(score2 = 0));
                break;

            case R.id.about:
                showAlerDialog();
                break;

            case R.id.exit:
                alertClose();
                break;

            case R.id.night_mode:
                if(nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    onResume();
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    onResume();
                }
                recreate();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void alertClose() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Activity Exit Alert");
        alert.setMessage("Are you sure want to exit ?");
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }

    private void showAlerDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final DialogInterface dialogInterface = builder.create();
        final View dialog = LayoutInflater.from(this).inflate(R.layout.about_dialog, null);
        builder.setView(dialog);
        builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
