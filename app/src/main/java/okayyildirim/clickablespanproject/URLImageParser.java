package okayyildirim.clickablespanproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html;
import android.webkit.URLUtil;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

/**
 * Created by aykutersahin on 01/02/17.
 */

public class URLImageParser implements Html.ImageGetter {

    private Context c;
    private TextView container;
    private boolean isFirstLoad;

    private int multiplexer = 10;
    private int multiplexerForTablet = 1;

    /***
     * Construct the URLImageParser which will execute AsyncTask and refresh the container
     *
     * @param t
     * @param c
     */
    public URLImageParser(TextView t, Context c, boolean isFirstLoad) {
        this.c = c;
        this.container = t;
        this.isFirstLoad = isFirstLoad;
    }

    public Drawable getDrawable(String source) {
        URLDrawable urlDrawable = new URLDrawable();

        ImageGetterAsyncTask asyncTask = new ImageGetterAsyncTask(urlDrawable);
        asyncTask.execute(source);

        return urlDrawable;
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
        URLDrawable urlDrawable;

        public ImageGetterAsyncTask(URLDrawable d) {
            this.urlDrawable = d;
        }

        @Override
        protected Drawable doInBackground(String... params) {
            String source = params[0];
            return fetchDrawable(source);
        }

        @Override
        protected void onPostExecute(Drawable result) {
            if (result != null) {
                // set the correct bound according to the result from HTTP call
                urlDrawable.setBounds(0, 0, 0 + result.getIntrinsicWidth() * multiplexer,0 + result.getIntrinsicHeight() * multiplexer);
                // change the reference of the current drawable to the result
                // from the HTTP call

                // redraw the image by invalidating the container
                URLImageParser.this.container.invalidate();
                if (URLImageParser.this.isFirstLoad)
                    URLImageParser.this.container.setHeight((URLImageParser.this.container.getHeight() + result.getIntrinsicHeight() * multiplexer));
                else if (URLImageParser.this.container.getHeight() < result.getIntrinsicHeight() * multiplexer)
                    URLImageParser.this.container.setHeight((URLImageParser.this.container.getHeight() + result.getIntrinsicHeight() * multiplexer));
                urlDrawable.drawable = result;
                // Pre ICS
                URLImageParser.this.container.setEllipsize(null);
            }
        }


        /***
         * Get the Drawable from URL
         *
         * @param urlString
         * @return
         */
        public Drawable fetchDrawable(String urlString) {
            try {
                InputStream is = fetch(urlString);
                Drawable drawable = Drawable.createFromStream(is, "src");
                drawable.setBounds(0, 0, 0 + drawable.getIntrinsicWidth() * multiplexer,
                        0 + drawable.getIntrinsicHeight() * multiplexer);
                return drawable;
            } catch (Exception e) {
                return null;
            }
        }

        private InputStream fetch(String urlString) throws IOException {
            HttpClient httpClient = HttpClientBuilder.create().build();

            String url = urlString;

            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            return response.getEntity().getContent();
        }
    }
}
