package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO main activity a listview with all characters
        gameCharacter Monk = new gameCharacter("Sajan","Monk");
        gameCharacter Fighter = new gameCharacter("Valeros","Fighter");
        gameCharacter Sorcerer = new gameCharacter("Seoni","Sorcerer");
        gameCharacter Wizard = new gameCharacter("Ezren","Wizard");

        List<IComponent> secondComponetnlist = new ArrayList<>();
        secondComponetnlist.add(new StringComponent("ASSSSSSSS"));
        secondComponetnlist.add(new CheckboxComponent());

        secondComponetnlist.add(new StringComponent("ASSSSSSSS"));


        //Valeros
        Fighter.addFeat(new Skill("Strength",6,2));
        Fighter.addFeat(new Skill("Strength",6,2));
        Fighter.addFeat(new Power("Hand Size",secondComponetnlist));
        Fighter.addFeat(new Skill("Strength",6,2));
        Fighter.addFeat(new Power("Hand Size",secondComponetnlist));
        Fighter.addFeat(new Power("Hand Size",secondComponetnlist));
        Fighter.addFeat(new Skill("Strength",6,2));
        Fighter.addFeat(new Skill("Strength",6,2));

        /*Fighter.addFeat(new Feat("generic feat 1",0));
        Fighter.addFeat(new Feat("generic feat 2",0));
        Fighter.addFeat(new Feat("generic feat 3",0));
        Fighter.addFeat(new Feat("generic feat 4",0));*/
        //Fighter.addFeat(new Power(""));

        //Sajan
        Monk.addFeat(new Skill("Strength",6,2));
        Monk.addFeat(new Skill("Dexterity",10,4));
        Monk.addFeat(new Skill("Constitution",6,2));
        Monk.addFeat(new Skill("Inteligence",6,2));
        Monk.addFeat(new Skill("Wisdom",8,3));
        Monk.addFeat(new Skill("Charisma",6,2));


        Monk.addFeat(new Power("Hand Size",secondComponetnlist));
        Monk.addFeat(new Power("Hand Size",secondComponetnlist));


        List <IComponent>componentlist = new ArrayList<>();
        componentlist.add(new StringComponent("For your combat check, you may your dexterity die("));
        componentlist.add(new CheckboxComponent());
        componentlist.add(new StringComponent("and add the magic trait) ("));
        componentlist.add(new CheckboxComponent());
        componentlist.add(new StringComponent("and the fire trait); you may not play a weapon on the check."));
        Monk.addFeat(new Power(componentlist));


        ArrayList<gameCharacter> currentCharacters = new ArrayList<gameCharacter>();
        currentCharacters.add(Monk);
        currentCharacters.add(Fighter);
        currentCharacters.add(Sorcerer);
        currentCharacters.add(Wizard);

        //TODO need to make these clickable then send daata to feat Activity
        GameCharacterAdapter adapter= new GameCharacterAdapter(this,currentCharacters);
        final ListView listview = (ListView) findViewById(R.id.characterlist);
        listview.setAdapter(adapter);


     //make em clickable
        listview.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id){
                Intent numbersIntent = new Intent(MainActivity.this, FeatActivity.class);
                gameCharacter clickedCharacter = (gameCharacter) listview.getItemAtPosition(position);
                clickedCharacter.printFeats();
                //Log.d("wanna see clckied", clickedCharacter.printFeats());
                numbersIntent.putExtra("clickedCharacter",clickedCharacter);
                startActivity(numbersIntent);
            }
        });

    }


}
