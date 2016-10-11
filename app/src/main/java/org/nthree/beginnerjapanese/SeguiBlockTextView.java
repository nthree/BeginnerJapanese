package org.nthree.beginnerjapanese;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Nolan on 9/7/2016.
 */
public class SeguiBlockTextView extends TextView {


        public SeguiBlockTextView(Context context) {
            super(context);
            applyCustomFont(context);
        }

        public SeguiBlockTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
            applyCustomFont(context);
        }

        public SeguiBlockTextView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            applyCustomFont(context);
        }

        private void applyCustomFont(Context context) {
            Typeface customFont = FontCache.getTypeface("segoubold.ttf", context);
            setTypeface(customFont);
        }

    }

