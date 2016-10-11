package org.nthree.beginnerjapanese;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class japanese_hiragana extends AppCompatActivity  {

    MediaPlayer SoundFile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiragana);
    }

    private void stopPlaying() {
        if (SoundFile != null) {
            SoundFile.stop();
            SoundFile.release();
            SoundFile = null;
        }
    }

    public void buttonPress(View view) {

        switch (view.getId()) {

            case R.id.A:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.a);
                SoundFile.start();
                break;
            case R.id.I:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.i);
                SoundFile.start();
                break;
            case R.id.U:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.u);
                SoundFile.start();
                break;
            case R.id.E:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.e);
                SoundFile.start();
                break;
            case R.id.O:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.o);
                SoundFile.start();
                break;

            //************** 'K' ******************//
            case R.id.KA:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ka);
                SoundFile.start();
                break;
            case R.id.KI:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ki);
                SoundFile.start();
                break;
            case R.id.KU:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ku);
                SoundFile.start();
                break;
            case R.id.KE:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ke);
                SoundFile.start();
                break;
            case R.id.KO:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ko);
                SoundFile.start();
                break;

            //************** 'S' ******************//
            case R.id.SA:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.sa);
                SoundFile.start();
                break;
            case R.id.SHI:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.shi);
                SoundFile.start();
                break;
            case R.id.SU:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.su);
                SoundFile.start();
                break;
            case R.id.SE:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.se);
                SoundFile.start();
                break;
            case R.id.SO:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.so);
                SoundFile.start();
                break;


            //************** 'T' ******************//
            case R.id.TA:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ta);
                SoundFile.start();
                break;
            case R.id.CHI:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.chi);
                SoundFile.start();
                break;
            case R.id.TSU:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.tsu);
                SoundFile.start();
                break;
            case R.id.TE:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.te);
                SoundFile.start();
                break;
            case R.id.TO:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.to);
                SoundFile.start();
                break;


            //************** 'N' ******************//
            case R.id.NA:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.na);
                SoundFile.start();
                break;
            case R.id.NI:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ni);
                SoundFile.start();
                break;
            case R.id.NU:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.nu);
                SoundFile.start();
                break;
            case R.id.NE:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ne);
                SoundFile.start();
                break;
            case R.id.NO:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.no);
                SoundFile.start();
                break;


            //************** 'H' ******************//
            case R.id.HA:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ha);
                SoundFile.start();
                break;
            case R.id.HI:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.hi);
                SoundFile.start();
                break;
            case R.id.HU:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.fu);
                SoundFile.start();
                break;
            case R.id.HE:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.he);
                SoundFile.start();
                break;
            case R.id.HO:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ho);
                SoundFile.start();
                break;



            //************** 'M' ******************//
            case R.id.MA:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ma);
                SoundFile.start();
                break;
            case R.id.MI:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.mi);
                SoundFile.start();
                break;
            case R.id.MU:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.mu);
                SoundFile.start();
                break;
            case R.id.ME:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.me);
                SoundFile.start();
                break;
            case R.id.MO:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.mo);
                SoundFile.start();
                break;


            //************** 'Y' ******************//
            case R.id.YA:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ya);
                SoundFile.start();
                break;
            case R.id.YU:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.yu);
                SoundFile.start();
                break;
            case R.id.YO:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.yo);
                SoundFile.start();
                break;


            //************** 'R' ******************//
            case R.id.RA:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ra);
                SoundFile.start();
                break;
            case R.id.RI:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ri);
                SoundFile.start();
                break;
            case R.id.RU:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ru);
                SoundFile.start();
                break;
            case R.id.RE:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.re);
                SoundFile.start();
                break;
            case R.id.RO:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.ro);
                SoundFile.start();
                break;

            //************** 'conjunction' ******************//
            case R.id.WA:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.wa);
                SoundFile.start();
                break;
            case R.id.OO:
                stopPlaying();
                SoundFile = MediaPlayer.create(this, R.raw.o);
                SoundFile.start();
                break;

        }
    }
}
