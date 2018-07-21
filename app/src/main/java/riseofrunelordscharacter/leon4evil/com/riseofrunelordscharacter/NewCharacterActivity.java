package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewCharacterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_character);

        //Getting toolbar interface going
        Toolbar maToolbar = (Toolbar) findViewById(R.id.newcharactertoolbar);
        maToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        maToolbar.setTitle("Select new Character");

        final Context thisActivityContext = this;

        final ArrayList<GameCharacter> currentCharacters;
        CharacterBuilder maCharacterBuilder;


        //Character builder gets us a list of Game Characters from an XML
        maCharacterBuilder = new CharacterBuilder("AllCharacters.xml", this);
        currentCharacters = new ArrayList<GameCharacter>(maCharacterBuilder.getCharacters());


        //The adapter helps us display our Game character list
        GameCharacterAdapter adapter= new GameCharacterAdapter(this,currentCharacters,false);
        final ListView listview = (ListView) findViewById(R.id.newCharacterList);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CharacterSaver maSaver = new CharacterSaver(currentCharacters.get(i),thisActivityContext);
                maSaver.saveToFile();


                Toast toast = Toast.makeText(getApplicationContext(), "New "+currentCharacters.get(i).getCharacterName()+" created.", Toast.LENGTH_SHORT);
                toast.show();

                Intent MainActivityIntent = new Intent(NewCharacterActivity.this,MainActivity.class);
                startActivity(MainActivityIntent);
                finish();
            }
        });
    }

}