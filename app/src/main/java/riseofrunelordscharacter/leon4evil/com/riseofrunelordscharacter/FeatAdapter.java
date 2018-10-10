package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by leon on 4/4/18.
 */

public class FeatAdapter extends ArrayAdapter<Feat> {

    private ArrayList <ArrayList <Boolean>> checkboxIsCheckedlist = new ArrayList<ArrayList<Boolean>>();
    GameCharacter workingcharacter;
    //ArrayList<Feat> working
    //private int backgroundColor;
    public FeatAdapter(Context context , ArrayList<Feat> feats, GameCharacter givenCharacter){ //subclass constructor
        super(context,0,feats);
        workingcharacter= givenCharacter;

        for(int i =0;i<feats.size();i++){
            checkboxIsCheckedlist.add(new ArrayList<Boolean>());
            for(int j = 0;j<feats.get(i).getComponents().size();j++) {
                if(feats.get(i).getComponents().get(j) instanceof CheckboxComponent) {
                    if(((CheckboxComponent) feats.get(i).getComponents().get(j)).isChecked()){
                        checkboxIsCheckedlist.get(i).add(true);
                    }else{
                        checkboxIsCheckedlist.get(i).add(false);
                    }
                }else{
                    checkboxIsCheckedlist.get(i).add(false);
                }
            }
        }
        Log.d("fucking with you","HAHA everything is false");
        Log.d("this is the2DArraylist", checkboxIsCheckedlist.toString());
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_view_item, parent, false);
        }

        final Feat feat = getItem(position);
        final FlexboxLayout maFlexboxLayout = listItemView.findViewById(R.id.flexboxlayout);

        //I guess this is where we're adding default text view to listItemView
        if(feat instanceof Separator){ //if separator
            if(((FlexboxLayout) maFlexboxLayout).getChildCount() > 0) { //clear layout in current listviewItem
                ((FlexboxLayout) maFlexboxLayout).removeAllViews();
                ((FlexboxLayout) maFlexboxLayout).removeAllViews();
            }

            TextView feattextview = (TextView) listItemView.findViewById(R.id.textview);
            feattextview.setText(feat.getName()+"s");
            feattextview.setTextColor(Color.WHITE);  //text color
            feattextview.setTextSize(20);
            listItemView.setBackgroundColor(Color.BLACK);//background color
            //modifying size of listview Item if its a separator
            //listItemView.setLayoutParams(new LinearLayout.LayoutParams((int)(330*parent.getContext().getResources().getDisplayMetrics().density),100));
            listItemView.setLayoutParams(new LinearLayout.LayoutParams((int)(parent.getWidth()),100));

        }
        else { //then its skill or power
            if(((FlexboxLayout) maFlexboxLayout).getChildCount() > 0) { //clear layout in current listviewItem
                ((FlexboxLayout) maFlexboxLayout).removeAllViews();
            }
            listItemView.setBackgroundColor(Color.parseColor("#380474"));
            TextView feattextview = (TextView) listItemView.findViewById(R.id.textview);


            feattextview.setTextColor(Color.WHITE);
            feattextview.setTextSize(17);
            listItemView.setLayoutParams(new LinearLayout.LayoutParams((int)(parent.getWidth()),(int)(155*parent.getContext().getResources().getDisplayMetrics().density)));


            //show feat and its components
            feattextview.setText(feat.getLabel());
            for(int i = 0; i<feat.getComponents().size();i++){ //get components in this feat and put them in listviewItem
                Log.d("class type",feat.getComponents().get(i).getClass().toString());

                if(feat.getComponents().get(i) instanceof CheckboxComponent){ //if component is checkbox
                    CheckBox macomponentcheckbox = new CheckBox(parent.getContext());
                    // macomponentcheckbox.setGravity(Gravity.CENTER);

                    macomponentcheckbox.setVisibility(View.VISIBLE);
                    macomponentcheckbox.setOnCheckedChangeListener(null);//this line is important
                    macomponentcheckbox.setFocusable(false);
                    macomponentcheckbox.setText(feat.getComponents().get(i).getDescription()); //if there is a description for the checkbox
                    macomponentcheckbox.setChecked(checkboxIsCheckedlist.get(position).get(i));//if it was checked before check it
                    macomponentcheckbox.setButtonTintList(parent.getContext().getResources().getColorStateList(R.color.checkbox_colors));
                    macomponentcheckbox.setTextColor(Color.WHITE);
                    //when it comes to screen
                    macomponentcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked ){
                                checkboxIsCheckedlist.get(position).set(maFlexboxLayout.indexOfChild(buttonView), true);
                                //this actually changes data the adapter is working with
                                CheckboxComponent thischeckbox = (CheckboxComponent) feat.getComponents().get(maFlexboxLayout.indexOfChild(buttonView));
                                thischeckbox.setChecked(true);
                                //this tested see if we could change data on fly
                                //feat.getComponents().get(maFlexboxLayout.indexOfChild(buttonView)).setDescription("ASSSSSSS");
                                //workingcharacter.setCharacterName("ASSSS");
                                //notifyDataSetChanged();
                                //((Activity)getContext()).finish();


                                //remove(feat); //dont need this just a test

                            }else{
                                checkboxIsCheckedlist.get(position).set(maFlexboxLayout.indexOfChild(buttonView), false);
                                //this actually changes data the adapter is working with
                                CheckboxComponent thischeckbox = (CheckboxComponent) feat.getComponents().get(maFlexboxLayout.indexOfChild(buttonView));
                                thischeckbox.setChecked(false);
                            }
                        }
                    });
                    //macomponentcheckbox.setMaxHeight(100);
                    maFlexboxLayout.addView(macomponentcheckbox);
                    Log.d("checkbox","checkbox added to layout!!!");

                }else{//not a checkbox then
                    TextView macomponenttextview = new TextView(parent.getContext());
                    macomponenttextview.setTextColor(Color.WHITE);
                    macomponenttextview.setGravity(Gravity.CENTER);
                    //macomponenttextview.setMaxHeight(100);
                    macomponenttextview.setText(feat.getComponents().get(i).getDescription());
                    macomponenttextview.setPadding(7,0,7,0);
                    maFlexboxLayout.addView(macomponenttextview);
                }
            }

        }

            /*//set the theme color for the list item
            View textContainer =listItemView.findViewById(R.id.text_container);
            //Find the color that the resource ip maps to
            int color = ContextCompat.getColor(getContext(),backgroundColor);
            //Set the background of the text container view
            textContainer.setBackgroundColor(color);*/

        return listItemView;
    }
}