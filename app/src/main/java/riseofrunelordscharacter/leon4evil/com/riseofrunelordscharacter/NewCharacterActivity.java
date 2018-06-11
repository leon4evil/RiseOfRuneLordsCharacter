package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NewCharacterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character);

        final Context thisActivityContext = this;

        final ArrayList<GameCharacter> currentCharacters;
        CharacterBuilder maCharacterBuilder;


        //Character builder gets us a list of Game Characters from an XML
        maCharacterBuilder = new CharacterBuilder("AllCharacters.xml", this);
        currentCharacters = new ArrayList<GameCharacter>(maCharacterBuilder.getCharacters());


        //The adapter helps us display our Game character list
        GameCharacterAdapter adapter= new GameCharacterAdapter(this,currentCharacters);
        final ListView listview = (ListView) findViewById(R.id.newCharacterList);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterSaver maSaver = new CharacterSaver(currentCharacters.get(i),thisActivityContext);
                maSaver.saveToFile();
            }
        });
    }


}
