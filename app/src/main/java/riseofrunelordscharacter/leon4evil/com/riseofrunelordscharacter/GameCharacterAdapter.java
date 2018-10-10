package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by leon on 4/11/18.
 */

public class GameCharacterAdapter extends ArrayAdapter<GameCharacter> {

    boolean selectionmode;

    public GameCharacterAdapter(Context context , ArrayList<GameCharacter> gameCharacters,boolean currentmode){ //subclass constructor
        super(context,0,gameCharacters);
        selectionmode = currentmode;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.character_list_view_item, parent, false);
        }
        GameCharacter maGameCharacter = getItem(position);


        //Add ImageView

        String imageFilename = (RoleExtractor.getJustName(maGameCharacter.getCharacterName())).toLowerCase(); //no extension necessary on file name
        Log.d("Image that shoud be",imageFilename);
        int maid = parent.getResources().getIdentifier(imageFilename,"drawable",getContext().getPackageName());
        Log.d("package  is",getContext().getPackageName());
        Log.d("ID is",String.valueOf(maid));

        ImageView gameCharacterImageView = (ImageView) listItemView.findViewById(R.id.photoImageView);
        gameCharacterImageView.setImageResource(maid);

        if(maGameCharacter instanceof NewGameCharacter){
            gameCharacterImageView.setImageResource(R.drawable.new_character);
        }

        TextView characterTextview = (TextView) listItemView.findViewById(R.id.nameTextView);
        characterTextview.setText(maGameCharacter.getCharacterName());

        GameCharacter thisCharacter = (GameCharacter)getItem(position);
        if(thisCharacter.isSelected()){
            if(selectionmode) {
                Log.d("turning","blue");
                listItemView.setBackgroundColor(Color.parseColor("#33007FFF"));
            }
        }
        else{
            if(selectionmode){
                Log.d("turning","white");
                listItemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }
        listItemView.setFocusable(false);
        listItemView.setClickable(false);

        return listItemView;

    }

    public void updateColor(){



    }

    public void updatemode(boolean givenselectionmode){
        this.selectionmode = givenselectionmode;
    }

}
