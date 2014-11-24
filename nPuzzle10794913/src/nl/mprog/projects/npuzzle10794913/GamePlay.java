package nl.mprog.projects.npuzzle10794913;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;


public class GamePlay extends Activity {

	TextView text1;	
	String passedID=null;
	TextView passedView;
	
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_play);
    
    passedID = getIntent().getStringExtra("ID");
    passedView = (TextView)findViewById(R.id.textView2);
    passedView.setText("Selectie = "+passedID);
    text1=(TextView)findViewById(R.id.textView1);

    final Intent intentGamePlay2 = new Intent(this, GamePlay2.class);
    intentGamePlay2.putExtra("ID2", String.valueOf(passedID));

    new CountDownTimer(4000, 1000) {

    	 public void onTick(long millisUntilFinished) {
    	     text1.setText("" + millisUntilFinished / 1000);
    	 }

    	 public void onFinish() {
             startActivity(intentGamePlay2);
    	     finish();
    	 }
    	}
    	.start();             

  }

  }