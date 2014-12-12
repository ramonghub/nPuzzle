//nPuzzle - ImageSelection
//Ramon Geessink (10794913)
//(1/4) this activity lets the user select an image.

package nl.mprog.projects.npuzzle10794913;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class ImageSelection extends Activity {
	
//	strings of menu
	ListView list;
	String[] web = {
		"HTC One M8",
		"MacBook Pro",
		"Chromecast",
		"Xbox One",
		"iPad Air"
  	} ;
	
//	images of menu
  	Integer[] imageId = {
	    R.drawable.puzzle_0,
	    R.drawable.puzzle_1,
	    R.drawable.puzzle_2,
	    R.drawable.puzzle_3,
	    R.drawable.puzzle_4
    };
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_selection);
    
    final Intent intentGamePlay = new Intent(this, GamePlay.class);
    
//    make list
    CustomList adapter = new
    CustomList(ImageSelection.this, web, imageId);
    list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        
//        on click give id to gameplay
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {                    
                    intentGamePlay.putExtra("image_id", String.valueOf(id));
                    startActivity(intentGamePlay);
                }
            });
	}

}
