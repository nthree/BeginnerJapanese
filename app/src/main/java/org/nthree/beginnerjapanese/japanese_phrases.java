package org.nthree.beginnerjapanese;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class japanese_phrases extends AppCompatActivity {

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

        //Creates the array of words of the list
        final ArrayList<Words> WordBank = new ArrayList<Words>();

        WordBank.add(new Words("I", "Watashi", "わたし", R.raw.watashi));
        WordBank.add(new Words("You", "Anata", "あなた", R.raw.you));
        WordBank.add(new Words("He", "Kare", "かれ", R.raw.kare));
        WordBank.add(new Words("She", "kanojo", "かのじょ", R.raw.she));
        WordBank.add(new Words("Here", "Koko", "ここ", R.raw.here));
        WordBank.add(new Words("This", "Kono", "この", R.raw.phrase_this));
        WordBank.add(new Words("That", "Sore", "それ", R.raw.that));
        WordBank.add(new Words("There", "Soko", "そこ", R.raw.there));
        WordBank.add(new Words("Yes", "Hai", "はい", R.raw.yes));
        WordBank.add(new Words("No", "Iie", "いいえ", R.raw.phrase_no));
        WordBank.add(new Words("Good Morning", "Ohayou Gozaimasu", "お早うございます", R.raw.goodmorning));
        WordBank.add(new Words("Good Afternoon", "Konnichiwa", "こんにちは", R.raw.goodafternoon));
        WordBank.add(new Words("Good Evening", "Konbanwa", "こんばんは", R.raw.goodevening));
        WordBank.add(new Words("Welcome!", "Youkoso!", "ようこそ", R.raw.welcome));
        WordBank.add(new Words("Good bye!", "Sayounara!", "さようなら", R.raw.goodbye));
        WordBank.add(new Words("See you later", "Ja matane", "じゃ またね", R.raw.seeyoulater));
        WordBank.add(new Words("See you tomorrow", "Mata ashita", "また  あした", R.raw.seeyoutomorrow));
        WordBank.add(new Words("Pardon me", "Sumimasen", "すみません", R.raw.pardonme));
        WordBank.add(new Words("I am sorry", "Gomennasai", "ごめんなさい", R.raw.iamsorry));
        WordBank.add(new Words("Thanks", "Doumo", "どうも", R.raw.thanks));
        WordBank.add(new Words("Thank you!", "Arigatou Gozaimasu!", "ありがとう  ございます!", R.raw.thankyouverymuch));
        WordBank.add(new Words("You're welcome", "Dou itashimashite", "どう  いたしまして", R.raw.yourewelcome));
        WordBank.add(new Words("How are you?", "Ogenki desu ka", "お元気  です  か?", R.raw.howareyou));
        WordBank.add(new Words("Are you okay?", "Daijoubu desu ka", "大丈夫  です  か?", R.raw.areyouokay));
        WordBank.add(new Words("Your name?", "Onamaewa desu ka?", "おなまえは  です  か?", R.raw.yourname));
        WordBank.add(new Words("My name is", " Watashi no namae wa", "わたし  の  なまえ  は", R.raw.mynameis));


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
                    mMediaPlayer = MediaPlayer.create(japanese_phrases.this, wordBank.getAudioResourceID());

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



