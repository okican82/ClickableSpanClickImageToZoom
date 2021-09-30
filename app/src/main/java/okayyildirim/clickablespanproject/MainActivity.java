package okayyildirim.clickablespanproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView txt_tv;
    private String questionText;
    private WebView ww;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = (Button) findViewById(R.id.button);
        ww = (WebView) findViewById(R.id.ww);



        button.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v)
              {
                  System.gc();
                  ww.loadData(questionText, "text/html; charset=utf-8", "UTF-8");

                  Utils.setHtmlText(getApplicationContext(), txt_tv, questionText);

                  txt_tv.setMovementMethod(LinkMovementMethod.getInstance());
                  txt_tv.setHighlightColor(Color.TRANSPARENT);
              }
          });

        txt_tv = (TextView) findViewById(R.id.txt_tv);

        //html Text with img url
        questionText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. <br/> " +
                "<img alt=\"\" src=\"http://test.myenocta.com/mobilemaintain/eep/common_show_picture_cached.aspx?encQS=uQMsFm++hGvvJQvioV4DMyS4z1qn3GJw2LFPzJSQx05nat/0zxCPg0QVtMIQNF7vfyumEdqwNAN3Y8ME+jZLdGqDJejY6mllXT4rtmpz25nFCu6M4padvhysB7aqhH8zknvzQWDtq4+YYD3GGRGQfw==\" " +
                "style=\"width:458px; height:164px\"><br>\n<br>\n " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. <br/> " +
                "<img alt=\"\" src=\"http://test.myenocta.com/mobilemaintain/eep/common_show_picture_cached.aspx?encQS=uQMsFm++hGvvJQvioV4DMyS4z1qn3GJw2LFPzJSQx05nat/0zxCPg0QVtMIQNF7vfyumEdqwNAN3Y8ME+jZLdGqDJejY6mllXT4rtmpz25nFCu6M4padvhysB7aqhH8zknvzQWDtq4+YYD3GGRGQfw==\" " +
                "style=\"width:458px; height:164px\"><br>\n<br>\n "
        ;

    }
}
