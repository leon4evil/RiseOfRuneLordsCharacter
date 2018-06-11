package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //prep character builder
        CharacterBuilder maCharacterBuilder;
        ArrayList<GameCharacter> currentCharacters = new ArrayList<>();



        //check file path see if there are sum user files already
        File sdCard = this.getExternalFilesDir(null);
        File dir = new File(sdCard.getAbsolutePath()+"/thismofoapp");

        if(dir.exists()) {
            File[] listOfFiles = dir.listFiles();


            for (int i = 0; i < listOfFiles.length; i++) {

                Log.d("What is name of file?", listOfFiles[i].getName());
                maCharacterBuilder = new CharacterBuilder(listOfFiles[i], this);
                currentCharacters.addAll(maCharacterBuilder.getCharacters());
            }
            currentCharacters.add(new NewGameCharacter());

        }else{
            currentCharacters.add(new NewGameCharacter());

        }

        //The adapter helps us display our Game character list
        GameCharacterAdapter adapter= new GameCharacterAdapter(this,currentCharacters);
        final ListView listview = (ListView) findViewById(R.id.characterlist);
        listview.setAdapter(adapter);


        //make em clickable
        listview.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id){
                GameCharacter clickedCharacter = (GameCharacter) listview.getItemAtPosition(position);

                if(clickedCharacter.getCharacterName().equals("New Character")) {
                    Intent newCharacterIntent = new Intent(MainActivity.this,NewCharacterActivity.class);
                    startActivity(newCharacterIntent);
                }else{
                    Intent featIntent = new Intent(MainActivity.this, FeatActivity.class);

                    clickedCharacter.printFeats();
                    //Log.d("wanna see clckied", clickedCharacter.printFeats());
                    featIntent.putExtra("clickedCharacter",clickedCharacter);
                    startActivity(featIntent);
                }
            }
        });

    }
}