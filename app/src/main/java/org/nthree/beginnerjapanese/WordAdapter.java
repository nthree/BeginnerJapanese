package org.nthree.beginnerjapanese;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nolan on 8/20/2016.
 */
public class WordAdapter extends ArrayAdapter<Words> {

    public WordAdapter(Activity context, ArrayList<Words> Words)
    {
        super(context, 0, Words);
    }



    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_items, parent, false);
        }

    /** Link to the Word class for the position of the list */
    Words currentWord = getItem(position);

    /** Set texts to the the given currentWord for each view */
    TextView englishTextView = (TextView) listItemView.findViewById(R.id.english_text_view);
    englishTextView.setText(currentWord.getDefaultTranslation());

    TextView japaneseTextView = (TextView) listItemView.findViewById(R.id.hiragana_english_text_view);
    japaneseTextView.setText(currentWord.getJapanesekTranslation());

    TextView hiraganaTextView = (TextView) listItemView.findViewById(R.id.hiragana_japanese_text_view);
    hiraganaTextView.setText(currentWord.getHiraganaTranslation());

    /** Check to see if the imageview is needed. Set image invisible if its available and not invisible if no images given */
    ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);

        if(currentWord.hasImages()) {
            imageView.setImageResource(currentWord.getImageResourceID());
            imageView.setVisibility(View.VISIBLE);
        }
        else
            imageView.setVisibility(View.GONE);







    return listItemView;
    }

}
