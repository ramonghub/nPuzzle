package nl.mprog.projects.npuzzle10794913;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class ImageSelection extends Activity {
	
	ListView list;
  String[] web = {
    "Google Plus",
      "Twitter",
      "Windows",
      "Bing",
      "Itunes",
      "Wordpress",
      "Drupal"
  } ;
  Integer[] imageId = {
      R.drawable.star3,
      R.drawable.star3,
      R.drawable.star3,
      R.drawable.star3,
      R.drawable.star3,
      R.drawable.star3,
      R.drawable.star3
  };
    
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_selection);
    
    final Intent intentGamePlay = new Intent(this, GamePlay.class);
    
    CustomList adapter = new
        CustomList(ImageSelection.this, web, imageId);
    list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    Toast.makeText(ImageSelection.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                    
                    intentGamePlay.putExtra("ID", String.valueOf(id));
                    startActivity(intentGamePlay);
                }
            });
  }
}
