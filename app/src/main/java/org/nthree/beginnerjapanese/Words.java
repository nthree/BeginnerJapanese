package org.nthree.beginnerjapanese;

/**
 * Created by Nolan on 8/19/2016.
 */
public class Words {

    /**
     * Default translation for the word
     */
    private String mDefaultTranslation;

    /**
     * Japanese translation for the word
     */
    private String mJapaneseTranslation;

    /**
     * Japanese Hiragana Form
     */
    private String mHiraganaTranslation;

    private int mAudioResourceId;

    /**
     * Resource ID for the word
     */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation  is English
     * @param japaneseTranslation is the Japanese language
     * @param hiraganaTranslation is the Hiragana language
     */
    public Words(String defaultTranslation, String japaneseTranslation, String hiraganaTranslation, int audio) {
        mDefaultTranslation = defaultTranslation;
        mJapaneseTranslation = japaneseTranslation;
        mHiraganaTranslation = hiraganaTranslation;
        mAudioResourceId = audio;
    }

    /**
     * An an extension in case for images
     */
    public Words(String defaultTranslation, String japaneseTranslation, String hiraganaTranslation, int images, int audio) {
        mDefaultTranslation = defaultTranslation;
        mJapaneseTranslation = japaneseTranslation;
        mHiraganaTranslation = hiraganaTranslation;
        mImageResourceId = images;
        mAudioResourceId = audio;
    }

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Japanese translation of the word.
     */
    public String getJapanesekTranslation() {
        return mJapaneseTranslation;
    }

    /**
     * Get the Hiragana translation of the word.
     */
    public String getHiraganaTranslation() {
        return mHiraganaTranslation;
    }

    /**
     * Get the images
     */
    public int getImageResourceID() {
        return mImageResourceId;
    }

    public boolean hasImages() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }


    public int getAudioResourceID() {
        return mAudioResourceId;
    }

}

