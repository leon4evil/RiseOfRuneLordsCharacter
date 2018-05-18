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

        maCharacter.printFeats();

        maCharacter.orderFeats();
        //maCharacter.printFeats();


        /*gameCharacter Warrior = new gameCharacter("Bill","Warrior");
        Warrior.addFeat(new Skill("Strength",6,2));
        Warrior.addFeat(new Skill("Strength",6,2));
        Warrior.addFeat(new Power("Hand Size","im just awesome like that"));
        Warrior.addFeat(new Skill("Strength",6,2));
        Warrior.addFeat(new Power("Hand Size","im just awesome like that"));
        Warrior.addFeat(new Power("Hand Size","im just awesome like that"));
        Warrior.addFeat(new Skill("Strength",6,2));
        Warrior.addFeat(new Skill("Strength",6,2));
        Log.d("separate","HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHA");
        Warrior.printFeats();
        Warrior.orderFeats();
        Log.d("separate","HAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHAHA");
        Warrior.printFeats();*/


        FeatAdapter adapter= new FeatAdapter(this,maCharacter.getCharacterFeats());
        ListView listview = (ListView) findViewById(R.id.featlist);
        listview.setAdapter(adapter);


    }
    //TODO save state stuff so activity doesnt restart on rotate
}
