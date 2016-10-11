package org.nthree.beginnerjapanese;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Nolan on 8/23/2016.
 */
public class japanese_time extends AppCompatActivity {


    // Handles playback of all the sound files
    private MediaPlayer mMediaPlayer;

    // Handles audio focus when playing a sound file
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {

                    // AUDIOFOCUS_LOSS_TRANSIENT case means we've loss audio focus for a short time.
                    // AUDIOFOCUS_LOSS_TRANSIENT_MAY_DUCK means that the app is allowed to continue
                    // playing the soundfile but at a lower volume.
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK) {

                        // Pause playback and reset the mediaplayer to replay the soundfile again
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // AUDIOFOCUS_LOSS_TRANSIENT_MAY_DUCK means that the app is allowed to continue
                        // playing the soundfile but at a lower volume.
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                        // Stop playback
                        releaseMediaPlayer();

                    }
                }
            };

    private MediaPlayer.OnCompletionListener mCompletetionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Create and setup the link to AudioManager
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Words> WordBank = new ArrayList<Words>();

        WordBank.add(new Words("1 o'clock", "Ichi-ji", "いち-じ", R.drawable.one, R.raw.one_oclock));
        WordBank.add(new Words("2 o'clock", "Ni-ji", "に-じ", R.drawable.two, R.raw.two_oclock));
        WordBank.add(new Words("3 o'clock", "San-ji", "さん-じ", R.drawable.three, R.raw.three_oclock));
        WordBank.add(new Words("4 o'clock", "Yo-ji", "よ-じ", R.drawable.four, R.raw.four_oclock));
        WordBank.add(new Words("5 o'clock", "Go-ji", "ご-じ", R.drawable.five, R.raw.five_oclock));
        WordBank.add(new Words("6 o'clock", "Roku-ji", "ろく-じ", R.drawable.six, R.raw.six_oclock));
        WordBank.add(new Words("7 o'clock", "Shichi-ji", "しち-じ", R.drawable.seven, R.raw.seven_oclock));
        WordBank.add(new Words("8 o'clock", "Hachi-ji", "はち-じ", R.drawable.eight, R.raw.eight_oclock));
        WordBank.add(new Words("9 o'clock", "Ku-ji", "く-じ", R.drawable.nine, R.raw.nine_oclock));
        WordBank.add(new Words("10 o'clock", "Juu-ji", "じゅう-じ", R.drawable.ten, R.raw.ten_oclock));
        WordBank.add(new Words("11 o'clock", "Juuichi-ji", "じゅういち-じ", R.drawable.eleven, R.raw.eleven_oclock));
        WordBank.add(new Words("12 o'clock", "Juuni-ji", "じゅうに-じ", R.drawable.twelve, R.raw.twelve_oclock));
        WordBank.add(new Words("P.M", "Gogo", "ごご", R.raw.pm));
        WordBank.add(new Words("Midnight", "Mayonaka", "真夜中", R.raw.midnight));
        WordBank.add(new Words("A.M", "Gozen", "ごぜん", R.raw.am));
        WordBank.add(new Words("Morning", "Asa", "あさ", R.raw.morning));
        WordBank.add(new Words("Minute", "pun/fun", "分", R.raw.minute));



        WordAdapter adapter = new WordAdapter(this, WordBank);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Creates the position for the list
                Words wordBank = WordBank.get(position);

                // Releases the media player in order to play the next sound file when press
                releaseMediaPlayer();


                // Request audio focus for playback
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now

                    //Players the given sound file of the position of the list.
                    mMediaPlayer = MediaPlayer.create(japanese_time.this, wordBank.getAudioResourceID());

                    //Start the audio file.
                    mMediaPlayer.start();

                    //Setup a listener in order to stop and release the media player in
                    //order to play the next sound file.
                    mMediaPlayer.setOnCompletionListener(mCompletetionListener);
                }
            }
        });

    }

    @Override
    protected  void onStop(){
        super.onStop();
        //When the activity is stopped, release the media player in to be playing more sounds.
        releaseMediaPlayer();
    }


    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;


            // Just in case we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}




