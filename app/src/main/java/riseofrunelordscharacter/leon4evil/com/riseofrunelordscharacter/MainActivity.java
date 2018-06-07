package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        //Character builder gets us a list of Game Characters from an XML
        CharacterBuilder maCharacterBuilder = new CharacterBuilder("AllCharacters.xml",this);
        ArrayList<gameCharacter> currentCharacters = new ArrayList<gameCharacter>(maCharacterBuilder.getCharacters());

        //The adapter helps us display our Game character list
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
