package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.io.File;

public class FeatActivity extends AppCompatActivity {
    GameCharacter maCharacter;
    Menu menu;
    File thischaracterfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feat);

        //Gathering data from previeous activity
        Bundle data = getIntent().getExtras();
        maCharacter = getIntent().getParcelableExtra("clickedCharacter");
        String pathtofile = data.getString("associatedfile");
        thischaracterfile = new File(pathtofile);
        //thischaracterfile.delete();

        Log.d("character name",maCharacter.getCharacterName());

        //Getting toolbar interface going
        Toolbar maToolbar = (Toolbar) findViewById(R.id.feat_toolbar);
        maToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        maToolbar.setTitle(maCharacter.getCharacterName());

        setSupportActionBar(maToolbar);
        maToolbar.getMenu().setGroupVisible(R.id.on_feat_select,true);


        //Always order feats before handing to adapter(orders and adds separators)
        maCharacter.orderFeats();



        //Feat Adapter Helps us display feats
        FeatAdapter adapter= new FeatAdapter(this,maCharacter.getCharacterFeats(),maCharacter);
        ListView listview = (ListView) findViewById(R.id.featlist);
        listview.setAdapter(adapter);

        //ListView mofolayout  = (ListView) findViewById(R.id.featlist);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3)
            {
                featsInCharacter(maCharacter);
            }
        });




    }
    //TODO save state stuff so activity doesnt restart on rotate

    public void featsInCharacter(GameCharacter givenCharacter) {
        Log.d("ON FEAT ACTIVITY","TRIPLE TRIPLE TRIPLE ASSSSSSSSSSSSSSSSSSSSS");
        Log.d("Feats in this mofo!!",String.valueOf(givenCharacter.getCharacterFeats().size()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_feat,menu);
        this.menu=menu;
        //menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.save:
                Toast.makeText(getApplicationContext(), "Saved Character", Toast.LENGTH_SHORT).show();
                thischaracterfile.delete();
                CharacterSaver maSaver = new CharacterSaver(maCharacter, this);
                maSaver.saveToFile();
                finish();
                startActivity(getIntent());
                return true;

            case R.id.addfeats:

                Toast.makeText(getApplicationContext(), "Not Implemented yet", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event)  {
            if (keyCode == android.view.KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

                Intent featIntent = new Intent(FeatActivity.this, MainActivity.class);
                startActivity(featIntent);
                finish();
            }

            return super.onKeyDown(keyCode, event);
    }

}
