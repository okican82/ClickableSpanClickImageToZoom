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

        button.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v)
              {
                  System.gc();
                  Utils.setHtmlText(getApplicationContext(), txt_tv, questionText);

                  txt_tv.setMovementMethod(LinkMovementMethod.getInstance());
                  txt_tv.setHighlightColor(Color.TRANSPARENT);

                  //ww.loadData(questionText, "text/html; charset=utf-8", "UTF-8");

              }
          });

        txt_tv = (TextView) findViewById(R.id.txt_tv);



        ww = findViewById(R.id.ww);

        //html Text with img url
        //questionText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. <br/><img src=\"https://asia.olympus-imaging.com/content/000107506.jpg\"/><br/>Lorem ipsum dolor sit amet, consectetur adipiscing elit. <br/><img src=\"https://asia.olympus-imaging.com/content/000107506.jpg\"/><br/>Lorem ipsum dolor sit amet, consectetur adipiscing elit. <br/><img src=\"https://asia.olympus-imaging.com/content/000107506.jpg\"/><br/>";


        questionText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. <br/> <img alt=\"\" src=\"http://test.myenocta.com/mobilemaintain/eep/common_show_picture_cached.aspx?encQS=uQMsFm++hGvvJQvioV4DMyS4z1qn3GJw2LFPzJSQx05nat/0zxCPg0QVtMIQNF7vfyumEdqwNAN3Y8ME+jZLdGqDJejY6mllXT4rtmpz25nFCu6M4padvhysB7aqhH8zknvzQWDtq4+YYD3GGRGQfw==\" style=\"width:458px; height:164px\"><br>\n<br>\n ";

    }
}
