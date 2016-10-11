package org.nthree.beginnerjapanese;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class japanese_numbers extends AppCompatActivity {

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

        WordBank.add(new Words("One", "Ichi", "いち", R.drawable.number1, R.raw.ichi));
        WordBank.add(new Words("Two", "Ni", "に", R.drawable.number2, R.raw.ni));
        WordBank.add(new Words("Three", "San", "さん", R.drawable.number3, R.raw.san));
        WordBank.add(new Words("Four", "Shi/Yon", "し／よん", R.drawable.number4, R.raw.shi));
        WordBank.add(new Words("Five", "Go", "ご", R.drawable.number5, R.raw.go));
        WordBank.add(new Words("Six", "Roku", "ろく", R.drawable.number6, R.raw.roku));
        WordBank.add(new Words("Seven", "Shichi/Nana", "しち／なな", R.drawable.number7, R.raw.nana));
        WordBank.add(new Words("Eight","Hachi", "はち", R.drawable.number8, R.raw.hachi));
        WordBank.add(new Words("Nine", "Kyu", "きゅう", R.drawable.number9, R.raw.kyu));
        WordBank.add(new Words("Ten", "Juu", "じゅう", R.drawable.number10, R.raw.ju ));
        WordBank.add(new Words("Eleven", "Juu-Ichi", "じゅう-いち", R.drawable.number11, R.raw.juu_ichi));
        WordBank.add(new Words("Twelve", "Juu-Ni", "じゅう-に", R.drawable.number12, R.raw.juu_ni));
        WordBank.add(new Words("Thirteen", "Juu-San", "じゅう-さん", R.drawable.number13, R.raw.juu_san));
        WordBank.add(new Words("Fourteen", "Juu-Shi/Yon", "じゅう-し／よん", R.drawable.number14, R.raw.juu_yon));
        WordBank.add(new Words("Fifteen", "Juu-Go", "じゅう-ご", R.drawable.number15, R.raw.juu_go));
        WordBank.add(new Words("Sixteen", "Juu-Roku", "じゅう-ろく", R.drawable.number16,  R.raw.juu_roku));
        WordBank.add(new Words("Seventeen", "Juu-Shichi/Nana", "じゅう-しち／なな", R.drawable.number17, R.raw.juu_nana));
        WordBank.add(new Words("Eighteen","Juu-Hachi", "じゅう-はち", R.drawable.number18, R.raw.juu_hachi));
        WordBank.add(new Words("Nineteen", "Juu-Kyu", "じゅう-きゅう", R.drawable.number19, R.raw.juu_kyu));
        WordBank.add(new Words("Twenty", "Ni-Juu", "に-じゅう", R.drawable.number20, R.raw.ni_juu ));
        WordBank.add(new Words("Thirty", "San-Juu", "さん-じゅう",   R.raw.san_juu ));
        WordBank.add(new Words("Fourty", "Shi/Yon-Juu", "し／よん-じゅう", R.raw.yon_juu));
        WordBank.add(new Words("Fifty", "Go-Juu", "ご-じゅう",  R.raw.go_juu ));
        WordBank.add(new Words("Sixty", "Roku-Juu", "ろく-じゅう",  R.raw.roku_juu ));
        WordBank.add(new Words("Seventy", "Nana-Juu", "しち／なな-じゅう", R.raw.nana_juu ));
        WordBank.add(new Words("Eighty", "Hachi-Juu", "はち-じゅう", R.raw.hachi_juu ));
        WordBank.add(new Words("Ninety", "Kyu-Juu", "きゅう-じゅう",  R.raw.kyu_juu ));
        WordBank.add(new Words("Hundred", "Hyaku", "びゃく",  R.raw.hyaku ));


        // Create an {@link FamilyMemberWordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, WordBank);


        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);


        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
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
                    mMediaPlayer = MediaPlayer.create(japanese_numbers.this, wordBank.getAudioResourceID());

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
    public void onStop(){
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
