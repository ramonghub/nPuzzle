//nPuzzle - GamePlay2
//Ramon Geessink (10794913)
//(3/4) gameplay - shuffle the tiles

package nl.mprog.projects.npuzzle10794913;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GamePlay2 extends Activity {
String passed_image_id2;
String difficulty;
String old_difficulty;
ImageView imageView;
Bitmap slice[];
Bitmap img_png;
Bitmap blank_bmp;
private ArrayList<Bitmap> tList = new ArrayList<Bitmap>();
private ArrayList<Integer> OldLocations = new ArrayList<Integer>();
int on_pause_check;
int blank_position;
int board_width;
int board_height;
int n;
int width;
int height;
int moves;
int best;
TextView movesView;
TextView bestView;
Intent intentGamePlay2;
Intent wintent;

@Override
public void onCreate(Bundle savedInstanceState) 
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_play2);
//    handle intents
    passed_image_id2 = getIntent().getStringExtra("ID2");
    wintent = new Intent(this, WinActivity.class);    
//	  make views
    movesView=(TextView)findViewById(R.id.textView4);
    bestView=(TextView)findViewById(R.id.textView5);
//	  initiate SharedPreferences
	final Context mContext = getApplicationContext();
	SharedPreferences settings = mContext.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
	
//	  set moves
	if(settings.getInt("resume_moves", 0) != 0){
		settings.getInt("resume_moves", 0);
		movesView.setText("Moves: " + moves);
	}
	else{
		moves = 0;
		movesView.setText("Moves: " + moves);
	}
	
//	  set difficulty
	if(settings.getString("resume_difficulty", "no_diff_passed") != "no_diff_passed"){
		difficulty = settings.getString("resume_difficulty", "no_diff_pass");
	}
	else{
		difficulty = "2";	
	}
	
//	  set best
	if(settings.getInt("resume_best", 0) != 0){
		best = settings.getInt("resume_best", 0);
		if(best != 10000){
			bestView.setText("Best: " + best);
		}
		else{
			bestView.setText("Best: " + moves);
		}
	}
	else{
		bestView.setText("Best: " + moves);
		best = 10000;
	}
	
//  set settings of game by difficulty
	setState(difficulty);
//    get image
    setImage(passed_image_id2);
    
//	  cut bitmaps to set as tiles 
    blank_bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.blank);
//	  define size of tiles
    width = img_png.getWidth() / board_width;
    height = img_png.getHeight() / board_height;
//	  define starting point of cut
    int x = 0;
    int y = 0;
    int c = 0;
//	  loop for cutting img_bmp
    for(int h=0; h<board_height; h++){
    	for(int w=0; w<board_width; w++){
    		if(c<n){
    			Bitmap tile = Bitmap.createBitmap(img_png,x,y,width,height);
	    		tList.add(c, tile);
	    		x += height;
	    		c++;
    		}
    		else{
    			Bitmap tile = Bitmap.createScaledBitmap(blank_bmp,1,1,true);
    			tList.add(n, tile);
    		}
    	}
    	x = 0;
    	y += width;
    }
	    		    
//	  set ImageAdapter as GridView source
    final ImageAdapter adapter = new ImageAdapter(this);
    GridView gridview = (GridView) findViewById(R.id.gridview);
    gridview.setAdapter(adapter);
    gridview.setNumColumns(board_width);
    gridview.invalidateViews();
    
//    copy bitmaps to remember old location (needed for win check)
    slice = new Bitmap[n+1];
    
    for(int i = 0; i<n+1; i++){
    	slice[i] = tList.get(i);
    }
    
//    shuffle tiles
    int j = n-1;
    for(int i = 0; i < (n/2); i++){
    	Bitmap bmp_i = tList.get(i);
    	Bitmap bmp_j = tList.get(j);
    	tList.set(i, bmp_j);
		tList.set(j, bmp_i);
		j -= 1;
    }

//    shuffle one and 2 if number of tiles is uneven
    if((blank_position % 2) == 1){
		Bitmap temp_1 = tList.get(1);
    	Bitmap temp_2 = tList.get(2);
    	tList.set(1, temp_2);
		tList.set(2, temp_1);
	}
        
//        when clicked switch tiles
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {            	
//            	  selected tile is right of blank tile
                if((position + 1 == blank_position) & (((position + 1) % board_width) != 0)){
	                Bitmap temp = tList.get(position);
	            	Bitmap temp_blank = tList.get(position + 1);
	            	tList.set(position, temp_blank);
	            	tList.set(blank_position, temp);
	            	blank_position = position;
	            	moves++;
	        		movesView.setText("Moves: " + moves);
	            	adapter.notifyDataSetChanged();
            		}
//                selected tile is left of blank tile
                else if((position - 1 == blank_position) & (position % board_width !=0)){
                	Bitmap temp = tList.get(position);
	            	Bitmap temp_blank = tList.get(position - 1);
	            	tList.set(position, temp_blank);
	            	tList.set(blank_position, temp);
	            	blank_position = position;
	            	moves++;
	        		movesView.setText("Moves: " + moves);
	            	adapter.notifyDataSetChanged();
                	}
//                selected tile is under blank tile
                else if(position - board_width == blank_position){
                	Bitmap temp = tList.get(position);
	            	Bitmap temp_blank = tList.get(position - board_width);
	            	tList.set(position, temp_blank);
	            	tList.set(blank_position, temp);
	            	blank_position = position;
	            	moves++;
	        		movesView.setText("Moves: " + moves);
	            	adapter.notifyDataSetChanged();
                	}
//                selected tile is above blank tile
                else if(position + board_width == blank_position){
                	Bitmap temp = tList.get(position);
	            	Bitmap temp_blank = tList.get(position + board_width);
	            	tList.set(position, temp_blank);
	            	tList.set(blank_position, temp);
	            	blank_position = position;
	            	moves++;
	        		movesView.setText("Moves: " + moves);
	            	adapter.notifyDataSetChanged();
                	}
//                selected blank tile itself
                else if(position == blank_position){
                    Toast.makeText(GamePlay2.this, "Blank", Toast.LENGTH_SHORT).show();
                	}
//                selected another tile
                else{
                	Toast.makeText(GamePlay2.this, "Imposible", Toast.LENGTH_SHORT).show();
                	}

//                check if game is won
                int win = 0;
                for(int i = 0; i<n ;i++){
                	if(slice[i] == tList.get(i)){
                		win++;
                	}
                }
                
                if(win == n){
            		MediaPlayer mediaPlayer = MediaPlayer.create(mContext, R.raw.sound);
             		mediaPlayer.start();
             		wintent.putExtra("moves", String.valueOf(moves));
             		wintent.putExtra("passed_image", String.valueOf(passed_image_id2));
	             		if(moves < best){
	     					best = moves;
	         			}
             		moves = 0;
             		startActivity(wintent);
             		finish();
            	}
                
            }
        });        
}

//	 image adapter
	public class ImageAdapter extends BaseAdapter {
	    private Context mContext;
	    
	    public ImageAdapter(Context c) {
	        mContext = c;
	    }

	    public int getCount() {
	        return tList.size();
	    }

	    public Object getItem(int position) {
	        return null;
	    }

	    public long getItemId(int position) {
	        return 0;
	    }

//	    create a new ImageView for each item referenced by the Adapter
	    public View getView(int position, View convertView, ViewGroup parent) {
	        ImageView imageView;
//	        if it's not recycled, initialize some attributes
	        if (convertView == null) {  
	        	DisplayMetrics displaymetrics = new DisplayMetrics();
	        	getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
	        	int screen_width = displaymetrics.widthPixels / board_width;	        	
	        	imageView = new ImageView(mContext);
	            imageView.setLayoutParams(new GridView.LayoutParams(screen_width,screen_width));
	            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            imageView.setPadding(2, 2, 2, 2);
	        } else {
	            imageView = (ImageView) convertView;
	        }

	        imageView.setImageBitmap(tList.get(position));
	        return imageView;
	    }

	}
	
//	  create menu on top right
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.game_play2, menu);
		return true;
	}	

//	  set menu items
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		intentGamePlay2 = new Intent(this, GamePlay2.class);
		int id = item.getItemId();
//			  restart
			if(id == R.id.restart){
				old_difficulty = difficulty;
				intentGamePlay2.putExtra("passed_difficulty", String.valueOf(difficulty));
				intentGamePlay2.putExtra("ID2", String.valueOf(passed_image_id2));
				moves = 0;
				blank_position = n;
			}
			else if(id == R.id.level1)
			{
				old_difficulty = difficulty;
				difficulty = "1";
				intentGamePlay2.putExtra("passed_difficulty", String.valueOf(difficulty));
				intentGamePlay2.putExtra("ID2", String.valueOf(passed_image_id2));
				moves = 0;
				blank_position = n;
			}
			else if(id == R.id.level2)
			{
				old_difficulty = difficulty;
				difficulty = "2";
				intentGamePlay2.putExtra("passed_difficulty", String.valueOf(difficulty));
				intentGamePlay2.putExtra("ID2", String.valueOf(passed_image_id2));
				moves = 0;
				blank_position = n;
			}
			else if(id == R.id.level3)
			{
				old_difficulty = difficulty;
				difficulty = "3";
				intentGamePlay2.putExtra("passed_difficulty", String.valueOf(difficulty));
				intentGamePlay2.putExtra("ID2", String.valueOf(passed_image_id2));
				moves = 0;
				blank_position = n;
			}
		recycleTiles();
		startActivity(intentGamePlay2);
		finish();
		return super.onOptionsItemSelected(item);
	}

//	  when game is resumed
	@Override
	public void onResume(){		
//		  make shared preferences editor
		Context mContext = getApplicationContext();
		SharedPreferences settings = mContext.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
		int on_pause_check = settings.getInt("on_pause_check", 0);
		
//		  if data is saved restore preferences
		if(on_pause_check == 1){
//			  restore difficulty
			old_difficulty = settings.getString("old_difficulty", "no_old_diff");
	        difficulty = settings.getString("resume_difficulty", "no_diff");
//	          restore moves
	        moves = settings.getInt("resume_moves", 0);
//	          restore if game was played        
	        if(moves > 0){
//	        	  restore blank position
	        	blank_position = settings.getInt("resume_blank_position", n+1);
//		          restore tiles
		        for(int i = 0; i<n+1; i++){
		        	OldLocations.add(i, settings.getInt("num" + i, 0));
		        }
		    	for(int i = 0; i<n+1; i++){
			        tList.set(OldLocations.get(i), slice[i]);
			        }
		    	}
	        }
    	super.onResume();
	}
//	  when game is paused/stopped	
	@Override
	public void onPause(){		
//		  make shared preferences editor
	    Context mContext = getApplicationContext();
		SharedPreferences settings = mContext.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
//	      remember difficulty
	    editor.putString("resume_difficulty", difficulty);
	    editor.putString("old_difficulty", old_difficulty);
//        remember moves
	    editor.putInt("resume_moves", moves);
//      remember best
	    editor.putInt("resume_best", best);
//	      remember blank_position
	    editor.putInt("resume_blank_position", blank_position);
//        remember tiles
	    for(int i = 0; i<n+1; i++){
	    	editor.putInt("num" + i, tList.indexOf(slice[i]));
        }
//	      enable onResume
	    editor.putInt("on_pause_check" , 1);
//	      commit the edits!
	    editor.commit();
		
	super.onPause();
	}

//	recycle images in memory
	public void recycleTiles(){
		for(int i = 0; i < tList.size(); i++){
 			tList.get(i).recycle();
		}
	}
	
//	select chosen image
	public void setImage(String passed_image_id){
		String passed_image_id2 = passed_image_id;
		
		    if(passed_image_id2.equals("0")){
		    	img_png = BitmapFactory.decodeResource(this.getResources(), R.drawable.puzzle_0);
		    }
		    else if(passed_image_id2.equals("1")){
		    	img_png = BitmapFactory.decodeResource(this.getResources(), R.drawable.puzzle_1);
		    }
		    else if(passed_image_id2.equals("2")){
		    	img_png = BitmapFactory.decodeResource(this.getResources(), R.drawable.puzzle_2);
		    }
		    else if(passed_image_id2.equals("3")){
		    	img_png = BitmapFactory.decodeResource(this.getResources(), R.drawable.puzzle_3);
		    }
		    else if(passed_image_id2.equals("4")){
		    	img_png = BitmapFactory.decodeResource(this.getResources(), R.drawable.puzzle_4);
		    }
	}
	
	public void setState(String passed_difficulty){
		String difficulty = passed_difficulty;
		
//		  easy
	    if(difficulty.equals("1")){
	    	blank_position = 8;
	    	board_width = 3;
	    	board_height = 3;
	    	n = (board_width * board_height) - 1;
	    }
//	    medium
	    else if(difficulty.equals("2")){
	    	blank_position = 15;
	    	board_width = 4;
	    	board_height = 4;
	    	n = (board_width * board_height) - 1;
		}
//		  hard
	    else if(difficulty.equals("3")){
	    	blank_position = 24;
	    	board_width = 5;
	    	board_height = 5;
	    	n = (board_width * board_height) - 1;
	    }
	}
}