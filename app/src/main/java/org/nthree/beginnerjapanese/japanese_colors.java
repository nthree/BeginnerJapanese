package org.nthree.beginnerjapanese;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Nolan on 8/23/2016.
 */
public class japanese_colors extends AppCompatActivity{


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

        WordBank.add(new Words("Color", "Iro", "いろ", R.drawable.color, R.raw.color));
        WordBank.add(new Words("Red", "Aka", "あか", R.drawable.red, R.raw.red));
        WordBank.add(new Words("Orange", "Daidai", "だいだいいろ", R.drawable.orange, R.raw.orange));
        WordBank.add(new Words("Yellow", "Ki", "きいろ", R.drawable.yellow, R.raw.yellow));
        WordBank.add(new Words("Green", "Midori", "みどり", R.drawable.green, R.raw.green));
        WordBank.add(new Words("Blue", "Ao", "あお", R.drawable.blue, R.raw.blue));
        WordBank.add(new Words("Purple", "Murasaki", "むらさき", R.drawable.violet, R.raw.purple));
        WordBank.add(new Words("Pink", "Pinku", " ピンク", R.drawable.pink, R.raw.pink));
        WordBank.add(new Words("Brown", "Chairo", "ちゃいろ", R.drawable.brown, R.raw.brown));
        WordBank.add(new Words("Black", "Kuro", "くろ", R.drawable.black, R.raw.black));
        WordBank.add(new Words("Gray", "Haiiro", "はいいろ", R.drawable.grey, R.raw.gray));
        WordBank.add(new Words("White", "Shiro", "しろ", R.drawable.white1, R.raw.white));
        WordBank.add(new Words("Silver", "Gin", "ぎんいろ", R.drawable.silver, R.raw.silver));
        WordBank.add(new Words("Gold", "Kin", "きんいろ", R.drawable.gold, R.raw.gold));

        // Create an {@link FamilyMemberWordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, WordBank);

        //ArrayAdapter<FamilyMemberWords> itemsAdapter =
        //   new ArrayAdapter<FamilyMemberWords>(this, android.R.layout.activity_list_item, FamilyMembers);


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
                    mMediaPlayer = MediaPlayer.create(japanese_colors.this, wordBank.getAudioResourceID());

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





