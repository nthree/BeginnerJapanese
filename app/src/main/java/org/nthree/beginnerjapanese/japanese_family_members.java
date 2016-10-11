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

public class japanese_family_members extends AppCompatActivity {


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

   // FamilyMembers.add(new FamilyMemberWords("Family Member", "Kazoku", "かぞく"));
    WordBank.add(new Words("Grandfather", "Sofu", "そふ", R.drawable.family_grandfather, R.raw.grandfather));
    WordBank.add(new Words("Grandmother", "Sobo", "そぼ", R.drawable.family_grandmother, R.raw.grandmother));
   // FamilyMembers.add(new Words("Uncle", "Oji", " おじ"));
   // FamilyMembers.add(new Words("Aunt", "Oba", "おば"));
   // FamilyMembers.add(new Words("Parents", "Ryoushin", "りょうしん"));
    WordBank.add(new Words("Father", "Oto-san", "おとう-さん", R.drawable.family_father, R.raw.father));
    WordBank.add(new Words("Mother", "Oka-san", "おかあ-さん", R.drawable.family_mother, R.raw.mother));
    WordBank.add(new Words("Older Brother", "Ni-san", "に-さん", R.drawable.family_older_brother, R.raw.olderbrother));
    WordBank.add(new Words("Younger Brother", "Otouto", "おとうと", R.drawable.family_younger_brother, R.raw.youngerbrother));
    WordBank.add(new Words("Older Sister", "Ane-san", "あね-さん", R.drawable.family_older_sister, R.raw.oldersister));
    WordBank.add(new Words("Younger Sister","Imouto", "いもうと", R.drawable.family_younger_sister, R.raw.youngersister));
   // FamilyMembers.add(new Words("Married Couple", "Fuufu", "ふうふ"));
   // FamilyMembers.add(new Words("Husband", "Shujin", "しゅじん"));
    //FamilyMembers.add(new Words("Wife", "Kanai", " かない"));
    //FamilyMembers.add(new Words("Children", "Kodomo", "こども"));
    WordBank.add(new Words("Son", "Musuko", "むすこ", R.drawable.family_son, R.raw.son));
    WordBank.add(new Words("Daughter", "Musume", "むすめ", R.drawable.family_daughter, R.raw.daughter ));
   // FamilyMembers.add(new Words("Nephew", "Oi", "おい"));
   // FamilyMembers.add(new Words("Niece", "Mei", "めい"));
   // FamilyMembers.add(new Words("Cousin", "Itoko", "いとこ"));


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
                    mMediaPlayer = MediaPlayer.create(japanese_family_members.this, wordBank.getAudioResourceID());

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






