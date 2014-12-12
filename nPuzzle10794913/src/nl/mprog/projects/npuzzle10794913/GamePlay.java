package nl.mprog.projects.npuzzle10794913;
//nPuzzle - GamePlay
//Ramon Geessink (10794913)
//(2/4) gameplay - previews the selected image

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

public class GamePlay extends Activity {
	TextView textView;	
	String passed_image_id;
	ImageView imageView;
	String difficulty = "2";
	
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_play);
    
    textView=(TextView)findViewById(R.id.textView1);

//    get passed id
    passed_image_id = getIntent().getStringExtra("image_id");
    imageView = (ImageView) findViewById(R.id.imageView1);

//    set image as passed id
    if(passed_image_id.equals("0")){
    	imageView.setImageResource(R.drawable.puzzle_0);
    }
    else if(passed_image_id.equals("1")){
    	imageView.setImageResource(R.drawable.puzzle_1);
    }
    else if(passed_image_id.equals("2")){
    	imageView.setImageResource(R.drawable.puzzle_2);
    }
    else if(passed_image_id.equals("3")){
    	imageView.setImageResource(R.drawable.puzzle_3);
    }
    else if(passed_image_id.equals("4")){
    	imageView.setImageResource(R.drawable.puzzle_4);
    }
    
//    give id to GamePlay2
    final Intent intentGamePlay2 = new Intent(this, GamePlay2.class);
	intentGamePlay2.putExtra("ID2", String.valueOf(passed_image_id));
    
    new CountDownTimer(4000, 0001) {
    	 public void onTick(long millisUntilFinished) {
    	     textView.setText("" + millisUntilFinished / 1000);
    	 }
    	 public void onFinish() {
    		 textView.setText("0");
    		 startActivity(intentGamePlay2);
    	     finish();
    	 }
    	}
    	.start();             
  }
  }