package okayyildirim.clickablespanproject;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;



/**
 * Created by okay.yildirim on 8.01.2018.
 */

public class CustomDialogClass extends Dialog {

    private String imageURL;
    private Context context;

    ImageView full_image;



    public CustomDialogClass(Context _context, String _imageURL) {
        super(_context, R.style.FullScreenDialogTheme);// to remove margin of dialog box
        context = _context;
        this.imageURL = _imageURL;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.full_screen_image);
        full_image = (ImageView) findViewById(R.id.full_image);


        String ImageURL = String.valueOf(imageURL);

        Utils.loadLogoImageWithImageURL(context,ImageURL,full_image);

        full_image.setOnTouchListener(new Touch());



    }

}

