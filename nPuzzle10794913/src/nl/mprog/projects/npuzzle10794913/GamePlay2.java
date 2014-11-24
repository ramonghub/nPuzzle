package nl.mprog.projects.npuzzle10794913;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class GamePlay2 extends Activity {
String passedID2=null;
TextView passedView2;
	
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_play2);
    
    passedID2 = getIntent().getStringExtra("ID2");
    passedView2 = (TextView)findViewById(R.id.textView3);
    passedView2.setText("Selectie = "+passedID2);}
}