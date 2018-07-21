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

        //TODO add image view with character pic
        /* //Add ImageView
            ImageView miwokimageview = (ImageView) listItemView.findViewById(R.id.image);*/


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
