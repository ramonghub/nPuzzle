//nPuzzle - GamePlay
//Ramon Geessink (10794913)
//(4/4) shows when game is won

package nl.mprog.projects.npuzzle10794913;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WinActivity extends Activity {
String congratulate;
TextView conView1;
String congratulate2;
TextView conView2;
String moves;
String image_id3;
ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_win);

		moves = getIntent().getStringExtra("moves");
		image_id3 = getIntent().getStringExtra("passed_image");
		
	    imageView = (ImageView) findViewById(R.id.imageView2);
	    if(image_id3.equals("0")){
	    	imageView.setImageResource(R.drawable.puzzle_0);
	    }
	    else if(image_id3.equals("1")){
	    	imageView.setImageResource(R.drawable.puzzle_1);
	    }
	    else if(image_id3.equals("2")){
	    	imageView.setImageResource(R.drawable.puzzle_2);
	    }
	    else if(image_id3.equals("3")){
	    	imageView.setImageResource(R.drawable.puzzle_3);
	    }
	    else if(image_id3.equals("4")){
	    	imageView.setImageResource(R.drawable.puzzle_4);
	    }
		
		conView1=(TextView)findViewById(R.id.textView5);
		congratulate = ("CONGRATULATIONS!");
		conView1.setText(congratulate);
		
		conView2=(TextView)findViewById(R.id.textView);
		congratulate2 = ("You solved the puzzle in " + moves + " moves!");
		conView2.setText(congratulate2);
	}
}
