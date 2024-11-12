package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import android.widget.AdapterView.OnItemClickListener;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Menu menu;
    ArrayList<GameCharacter> currentCharacters;
    boolean selectionmode = false;
    GameCharacterAdapter adapter;
    List <File> listOfFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting toolbar interface going
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.getMenu().setGroupVisible(R.id.on_select,true);

        //Prep character builder
        CharacterBuilder maCharacterBuilder;
        currentCharacters = new ArrayList<>();

        //Check file path see if there are sum user files already
        File sdCard = this.getExternalFilesDir(null);
        File dir = new File(sdCard.getAbsolutePath()+"/thismofoapp");

        if(dir.exists()) {
            listOfFiles = new LinkedList<File>(Arrays.asList(dir.listFiles()));

            for (int i = 0; i < listOfFiles.size(); i++) {
                Log.d("What is name of file?", listOfFiles.get(i).getName());
                maCharacterBuilder = new CharacterBuilder(listOfFiles.get(i), this);
                currentCharacters.addAll(maCharacterBuilder.getCharacters());
            }
            currentCharacters.add(new NewGameCharacter());
        }else{
            currentCharacters.add(new NewGameCharacter());

        }

        //The adapter helps us display our Game character list
        adapter= new GameCharacterAdapter(this,currentCharacters,selectionmode);
        final ListView listview = (ListView) findViewById(R.id.characterlist);
        listview.setAdapter(adapter);


        //GestureDetector madetector = new GestureDetector(this,GestureDetector.OnGestureListener);


        //make em clickable
        listview.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id){
                GameCharacter clickedCharacter = (GameCharacter) listview.getItemAtPosition(position);
                if(selectionmode){

                    if(!clickedCharacter.isSelected()&& !(clickedCharacter instanceof NewGameCharacter)){
                        clickedCharacter.setSelected(true);
                    }
                    else{
                        clickedCharacter.setSelected(false);
                    }
                    for(int i =0;i<currentCharacters.size();i++){
                        if(currentCharacters.get(i).isSelected()){
                            menu.getItem(0).setVisible(true);
                            selectionmode = true;
                            break;
                        }
                        else{
                            //selectionmode =false;
                            //clickedCharacter.setSelected(false);
                            //adapter.updatemode(selectionmode);
                            menu.getItem(0).setVisible(false);
                            selectionmode =false;
                            setTitle("Rise Of Runelords");

                            //break;
                        }
                    }
                    adapter.notifyDataSetChanged();
                    //return; //dirty

                }
                //if(!selectionmode) {
                else{
                    if (clickedCharacter.getCharacterName().equals("New Character")) {
                        Intent newCharacterIntent = new Intent(MainActivity.this, NewCharacterActivity.class);
                        startActivity(newCharacterIntent);
                        finish();


                    } else {
                        Intent featIntent = new Intent(MainActivity.this, FeatActivity.class);
                        clickedCharacter.printFeats();
                        //Log.d("wanna see clckied", clickedCharacter.printFeats());
                        featIntent.putExtra("clickedCharacter", clickedCharacter);
                        featIntent.putExtra("associatedfile",listOfFiles.get(position).toString());
                        startActivity(featIntent);
                        finish();
                    }
                }
            }

        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                GameCharacter clickedCharacter = (GameCharacter) listview.getItemAtPosition(position);
                if(!selectionmode && !(clickedCharacter instanceof NewGameCharacter)) {
                    setTitle("Select");
                    clickedCharacter.setSelected(true);
                    selectionmode = true;
                    menu.getItem(0).setVisible(true);
                    adapter.updatemode(selectionmode);
                    adapter.notifyDataSetChanged();
                }
                return true;
            }
        });

    }
    @Override //make menu show
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        this.menu=menu;
        menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.action_favorite){
                Toast.makeText(getApplicationContext(),"Deleted", Toast.LENGTH_SHORT).show();
                Log.d("the error is","ASSS");
                int currentsize = currentCharacters.size()-1;
                for (int i=currentsize;i>=0;i--) {
                    Log.d("is selected?",String.valueOf(currentCharacters.get(i).isSelected()));
                    if(currentCharacters.get(i).isSelected()){
                        currentCharacters.remove(i);
                        listOfFiles.get(i).delete();
                        listOfFiles.remove(i);
                    }
                }
                setTitle("Rise Of Runelords");
                selectionmode = false;

                adapter.notifyDataSetChanged();
                this.menu.getItem(0).setVisible(false);
                return true;
        }

        else if(id == R.id.item1) {
            if (currentCharacters.size() > 1) {
                Toast.makeText(getApplicationContext(), "Select", Toast.LENGTH_SHORT).show();
                setTitle("Select");
                selectionmode = true;
                adapter.updatemode(selectionmode);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "Nothing to select.", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        else if (id == R.id.item2) {
            if (currentCharacters.size() > 1) {
                Toast.makeText(getApplicationContext(), "Select all", Toast.LENGTH_SHORT).show();
                setTitle("Select");
                selectionmode = true;
                for (int i = 0; i + 1 < currentCharacters.size(); i++) {
                    currentCharacters.get(i).setSelected(true);
                }
                adapter.updatemode(selectionmode);
                adapter.notifyDataSetChanged();
                this.menu.getItem(0).setVisible(true);
            } else {
                Toast.makeText(getApplicationContext(), "Nothing to select.", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }

    }

    //if user hits back key
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // action when back is pressed and selection mode is true
            if(selectionmode) {
                selectionmode = false;
                for(int i =0;i<currentCharacters.size();i++) {
                    currentCharacters.get(i).setSelected(false);
                }
                //adapter.updatemode(selectionmode);
                adapter.notifyDataSetChanged();
                setTitle("Rise Of Runelords ");
                this.menu.getItem(0).setVisible(false);
            }
            else{ //when not on selection mode just go back
                onBackPressed();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}