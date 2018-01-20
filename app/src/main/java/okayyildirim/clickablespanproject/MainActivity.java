package okayyildirim.clickablespanproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private TextView txt_tv;
    private String questionText;

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
              }
          });

        txt_tv = (TextView) findViewById(R.id.txt_tv);
        //html Text with img url

        questionText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. <br/><img src=\"http://www.kingglitter.com/wp-content/uploads/2015/10/request-a-sample-547-p.jpg\"/><br/>Lorem ipsum dolor sit amet, consectetur adipiscing elit. <br/><img src=\"http://www.kingglitter.com/wp-content/uploads/2015/10/request-a-sample-547-p.jpg\"/><br/>Lorem ipsum dolor sit amet, consectetur adipiscing elit. <br/><img src=\"http://www.kingglitter.com/wp-content/uploads/2015/10/request-a-sample-547-p.jpg\"/><br/>";

    }
}
