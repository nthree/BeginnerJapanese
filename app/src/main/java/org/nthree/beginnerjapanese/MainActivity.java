package org.nthree.beginnerjapanese;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView hiraganaActivity = (ImageView) findViewById(R.id.hiragana);
        hiraganaActivity.setOnClickListener(this);

        ImageView numbersActivity = (ImageView) findViewById(R.id.numbers);
        numbersActivity.setOnClickListener(this);

        ImageView familyActivity =  (ImageView) findViewById(R.id.family);
        familyActivity.setOnClickListener(this);

        ImageView colorActivity = (ImageView)findViewById(R.id.colors);
        colorActivity.setOnClickListener(this);

        ImageView timeActivity = (ImageView)findViewById(R.id.time);
        timeActivity.setOnClickListener(this);

        ImageView phrasesActivity = (ImageView)findViewById(R.id.phrases);
        phrasesActivity.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){

        switch (v.getId()) {
            case R.id.hiragana:
                Log.v("MainActivity", "Opening Hiragana Activity");
                Intent HiraganaIntent = new Intent(MainActivity.this, japanese_hiragana.class);
                startActivity(HiraganaIntent);
                break;

            case R.id.numbers:
                Log.v("MainActivity", "Opening Numbers Activity");
                Intent NumbersIntent = new Intent(this, japanese_numbers.class);
                startActivity(NumbersIntent);
                break;


            case R.id.family:
                Log.v("MainActivity", "Opening Family Activity");
                Intent FamilyIntent = new Intent(MainActivity.this, japanese_family_members.class);
                startActivity(FamilyIntent);
                break;


            case R.id.colors:
                Log.v("MainActivity", "Opening Color Activity");
                Intent colorIntent = new Intent(MainActivity.this, japanese_colors.class);
                startActivity(colorIntent);
                break;

            case R.id.time:
                Log.v("MainActivity", "Opening Time Activity");
                Intent timeIntent = new Intent(MainActivity.this, japanese_time.class);
                startActivity(timeIntent);
                break;

            case R.id.phrases:
                Log.v("MainActivity", "Opening Phrases Activity");
                Intent phrasesIntent = new Intent(MainActivity.this, japanese_phrases.class);
                startActivity(phrasesIntent);
                break;
        }
    }




}

