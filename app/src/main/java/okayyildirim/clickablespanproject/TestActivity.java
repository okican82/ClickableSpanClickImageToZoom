package okayyildirim.clickablespanproject;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;


import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;


import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

public class TestActivity extends Activity {


    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView  = (TextView) findViewById(R.id.textview);


        final SpannableString ss = new SpannableString("abc");
        final Drawable d = getResources().getDrawable(R.drawable.sample);


        textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressLint("NewApi")
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                //now we can retrieve the width and height
                int width = textView.getWidth();
                int height = textView.getHeight();


                int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
                int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
                textView.measure(widthMeasureSpec, heightMeasureSpec);
                textView.getMeasuredHeight();

                float r = (float) d.getIntrinsicHeight()/ (float) d.getIntrinsicWidth();
                float f = r*textView.getMeasuredWidth();


                d.setBounds(0, 0, textView.getMeasuredWidth(), Math.round(f));
                //d.setBounds(0, 0, width, height);
                ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
                ss.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(ss);
                //...
                //do whatever you want with them
                //...
                //this is an important step not to keep receiving callbacks:
                //we should remove this listener
                //I use the function to remove it based on the api level!

                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
                    textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                else
                    textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
//*/

        //Display display = getWindowManager().getDefaultDisplay();
        /*
        int width = textView.getLayoutParams().width; //display.getWidth();
        int height = textView.getLayoutParams().height;//display.getHeight();

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        textView.getMeasuredHeight();

        float r = (float) d.getIntrinsicHeight()/ (float) d.getIntrinsicWidth();
        float f = r*textView.getMeasuredWidth();


        d.setBounds(0, 0, textView.getMeasuredWidth(), Math.round(f));

        //*/

        //d.setBounds(0, 0, d.getIntrinsicWidth()/10, d.getIntrinsicHeight()/10);
        //d.setBounds(0, 0, 10, 10);

    }



}
