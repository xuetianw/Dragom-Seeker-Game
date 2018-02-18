package ca.cmpt276.as3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import ca.cmpt276.as3.model.R;

/**
 * This class is responsible to tell the user about the
 * author and the date the app was fully developed and
 * published. It will also tell the user about the req-
 * uired skills to play the game as efficient as possible.
 */
public class HelpActivity extends AppCompatActivity {
    private String TAG = "OrientationDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "Running onCreate()!");  // test
        setContentView(R.layout.activity_help);
        setTextView();
        setBackgroundImage();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, HelpActivity.class);
    }


    @SuppressLint("SetTextI18n")        // ask if necessary
    private void setTextView(){
        TextView myTextView = (TextView) findViewById(R.id.aboutAuthorID);
        myTextView.setMovementMethod(LinkMovementMethod.getInstance());
        myTextView.setText("Dragon Seeker is written by Kourosh Azizi and Fred Wu. " +
                            "This application was made for a school project for CMPT 276 " +
                            "class at Simon Fraser University." + "\n\n\n\n");

        // Course Website Hyperlink:
        TextView courseWebsiteText = (TextView) findViewById(R.id.CourseWebsiteID);
        courseWebsiteText.setClickable(true);
        courseWebsiteText.setMovementMethod(LinkMovementMethod.getInstance());
        String cmpt276Website = "<a href= 'http://www.cs.sfu.ca/CourseCentral/276/bfraser/as" +
                "signments.html'> CMPT 276 Course Website Link</a>";
        courseWebsiteText.setText(Html.fromHtml(cmpt276Website));
        courseWebsiteText.setTextColor(Color.BLACK);
        courseWebsiteText.setBackgroundColor(Color.WHITE);

        // Background Image Hyperlink:
        TextView backgroundImageText = (TextView) findViewById(R.id.backgroundImageTextID);
        backgroundImageText.setClickable(true);
        backgroundImageText.setMovementMethod(LinkMovementMethod.getInstance());
        String backgroundImageLink = "<a href= 'https://www.google.ca/search?rlz=1C1CHBF_enCA7" +
                "75CA775&biw=1366&bih=637&tbm=isch&sa=1&ei=gRCGWpr6J4W8jwPOjobYBA&q=chinese+ne" +
                "w+year+&oq=chinese+new+year+&gs_l=psy-ab.3..0i67k1l3j0j0i67k1l2j0j0i67k1j0l2." +
                "14317.14317.0.14714.1.1.0.0.0.0.114.114.0j1.1.0....0...1c.1.64.psy-ab..0.1.1" +
                "12....0.Lf-VMGNx0RM#imgrc=jRDt2rmBiJdQ3M:'> Background Image Link</a>";
        backgroundImageText.setText(Html.fromHtml(backgroundImageLink));
        backgroundImageText.setTextColor(Color.BLACK);
        backgroundImageText.setBackgroundColor(Color.WHITE);


        // Welcome Screen Animation Hyperlink:
        TextView animationImageText = (TextView) findViewById(R.id.welcomeScreenDragonTextID);
        animationImageText.setClickable(true);
        animationImageText.setMovementMethod(LinkMovementMethod.getInstance());
        String animationImageLink = "<a href= 'https://www.google.ca/search?rlz=1C1CHBF_enCA" +
                "775CA775&biw=1366&bih=637&tbm=isch&sa=1&ei=gRCGWpr6J4W8jwPOjobYBA&q=chinese+new+y" +
                "ear+&oq=chinese+new+year+&gs_l=psy-ab.3..0i67k1l3j0j0i67k1l2j0j0i67k1j0l2.14317." +
                "14317.0.14714.1.1.0.0.0.0.114.114.0j1.1.0....0...1c.1.64.psy-ab..0.1.112....0.Lf-" +
                "VMGNx0RM#imgrc=OWAg4eCVDbH1pM:'> Animation Image Link </a>";
        animationImageText.setText(Html.fromHtml(animationImageLink));
        animationImageText.setTextColor(Color.BLACK);
        animationImageText.setBackgroundColor(Color.WHITE);


        // Board Game's Dragon Hyperlink:
        TextView dragonButtonText = (TextView) findViewById(R.id.dragonButtonTextID);
        dragonButtonText.setClickable(true);
        dragonButtonText.setMovementMethod(LinkMovementMethod.getInstance());
        String dragonButtonImageLink = "<a href= 'https://www.google.ca/search?rlz=1C1CHBF_enCA7" +
                "75CA775&biw=1366&bih=637&tbm=isch&sa=1&ei=wiaGWpaYLsfajwPvg72gDg&q=chinese+drag" +
                "on&oq=chinese+dragon&gs_l=psy-ab.3..0j0i67k1l8j0.17358.20110.0.20347.16.9.0.7.7." +
                "0.182.878.4j4.9.0....0...1c.1.64.psy-ab..0.15.1076.0...40.y7iBEGBvcCg#imgrc=VzhX" +
                "k3Vo3uuqKM:'> Board Game's Dragon Link</a>";
        dragonButtonText.setText(Html.fromHtml(dragonButtonImageLink));
        dragonButtonText.setTextColor(Color.BLACK);
        dragonButtonText.setBackgroundColor(Color.WHITE);


        TextView instructionsText = (TextView) findViewById(R.id.instructionsID);
        instructionsText.setMovementMethod(LinkMovementMethod.getInstance());
        instructionsText.setText("Go to Main Menu and then and click on the options " +
                            "button to choose your favorite board size, and number " +
                            "of dragons that you want to find in the game. Then click on " +
                            "set Set Game button to go to play the game. In the game you " +
                            "need to tap on each cell to perform a scan in order to find " +
                            "the dragons. Keep in mind that each time you scan, there is " +
                            "scan counter that will increase to show how many times you " +
                            "have looked for a dragon. You should try to win the round " +
                            "with least number of scans as possible. Compete and break your " +
                            "records! :)" + "\n\n\n\n");

    }

    private void setBackgroundImage(){
        ImageView myImageView = (ImageView) findViewById(R.id.backgroundImageID);
        myImageView.setImageResource(R.drawable.chinese_new_year1);
    }
}
