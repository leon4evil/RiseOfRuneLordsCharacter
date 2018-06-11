package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class FeatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feat);

        Bundle data = getIntent().getExtras();
        final GameCharacter maCharacter = getIntent().getParcelableExtra("clickedCharacter");
        Log.d("character name",maCharacter.getCharacterName());

        //Always order feats before handing to adapter(orders and adds separators)
        maCharacter.orderFeats();



        //Feat Adapter Helps us display feats
        FeatAdapter adapter= new FeatAdapter(this,maCharacter.getCharacterFeats());
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
}
