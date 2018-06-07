package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class FeatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feat);

        Bundle data = getIntent().getExtras();
        gameCharacter maCharacter = getIntent().getParcelableExtra("clickedCharacter");
        Log.d("character name",maCharacter.getCharacterName());

        //Always order feats before handing to adapter(orders and adds separators)
        maCharacter.orderFeats();


        //Feat Adapter Helps us display feats
        FeatAdapter adapter= new FeatAdapter(this,maCharacter.getCharacterFeats());
        ListView listview = (ListView) findViewById(R.id.featlist);
        listview.setAdapter(adapter);


    }
    //TODO save state stuff so activity doesnt restart on rotate
}
