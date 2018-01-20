package okayyildirim.clickablespanproject;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by okay.yildirim on 5.01.2018.
 */

public class Utils
{

    public static void setHtmlText(final Context context, TextView textView, String htmlText) {
        URLImageParser imageParser = new URLImageParser(textView, context, true);
        Spanned htmlSpanned = Html.fromHtml(htmlText, imageParser, null);

        SpannableString ss = new SpannableString(htmlSpanned);

        ImageSpan[] image_spans = ss.getSpans(0, ss.length(), ImageSpan.class);

        for (ImageSpan span : image_spans) {

            final String image_src = span.getSource();
            final int start = ss.getSpanStart(span);
            final int end = ss.getSpanEnd(span);

            ClickableSpan click_span = new ClickableSpan() {

                @Override
                public void onClick(View widget) {
                    Log.d("clicked","image_src:" + image_src);


                    CustomDialogClass cc = new CustomDialogClass(widget.getContext(),image_src);
                    cc.show();
                }

            };

            ss.setSpan(click_span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        textView.setText(trimSpannedAndConvertToSpannableString(ss));

        textView.setMovementMethod(LinkMovementMethod.getInstance());


    }


    public static SpannableString trimSpannedAndConvertToSpannableString(Spanned spanned) {
        return new SpannableString(Utils.trim(spanned));
    }

    public static CharSequence trim(CharSequence s) {
        int start = 0;
        int end = s.length();
        while (start < end && Character.isWhitespace(s.charAt(start))) {
            start++;
        }

        while (end > start && Character.isWhitespace(s.charAt(end - 1))) {
            end--;
        }

        return s.subSequence(start, end);
    }


    public static void loadLogoImageWithImageURL(Context context, String imagePath, ImageView target)
    {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttpDownloader(context));
        builder.build().load(imagePath).fit().centerInside().into(target);
    }
}
