package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.content.Context;
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

    //private int backgroundColor;
        public FeatAdapter(Context context , ArrayList<Feat> feats){ //subclass constructor
            super(context,0,feats);

        for(int i =0;i<feats.size();i++){
            checkboxIsCheckedlist.add(new ArrayList<Boolean>());
            for(int j = 0;j<feats.get(i).getComponents().size();j++) {

                checkboxIsCheckedlist.get(i).add(false);
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
            Feat feat = getItem(position);
            final FlexboxLayout maFlexboxLayout = listItemView.findViewById(R.id.flexboxlayout);

            //I guess this is where we're adding default text view to listItemView
            if(feat.isSeparator()==1){ //if separator
                if(((FlexboxLayout) maFlexboxLayout).getChildCount() > 0) { //clear layout in current listviewItem
                    ((FlexboxLayout) maFlexboxLayout).removeAllViews();
                    ((FlexboxLayout) maFlexboxLayout).removeAllViews();
                }

                TextView feattextview = (TextView) listItemView.findViewById(R.id.textview);
                feattextview.setText(feat.getName()+"s");
                feattextview.setTextColor(Color.WHITE);  //text color
                listItemView.setBackgroundColor(Color.BLACK);//backgorund color
                //modifying size of listview Item if its a separator
                listItemView.setLayoutParams(new LinearLayout.LayoutParams((int)(330*parent.getContext().getResources().getDisplayMetrics().density),100));

            }
            else { //then its skill or power
                if(((FlexboxLayout) maFlexboxLayout).getChildCount() > 0) { //clear layout in current listviewItem
                    ((FlexboxLayout) maFlexboxLayout).removeAllViews();
                }
                listItemView.setBackgroundColor(Color.WHITE);
                TextView feattextview = (TextView) listItemView.findViewById(R.id.textview);


                feattextview.setTextColor(Color.parseColor("#808080"));
                listItemView.setLayoutParams(new LinearLayout.LayoutParams((int)(330*parent.getContext().getResources().getDisplayMetrics().density),(int)(119*parent.getContext().getResources().getDisplayMetrics().density)));


                if(feat instanceof Skill) {//if Skill
                    feattextview.setText(feat.getLabel());
                }
                else{// power then
                    Power thispower = (Power)feat;
                    feattextview.setText(thispower.getLabel());

                    for(int i = 0; i<thispower.getComponents().size();i++){ //get components in this feat and put them in listviewItem
                        Log.d("class type",thispower.getComponents().get(i).getClass().toString());

                        if(thispower.getComponents().get(i) instanceof CheckboxComponent){ //if component is checkbox
                            CheckBox macomponentcheckbox = new CheckBox(parent.getContext());
                           // macomponentcheckbox.setGravity(Gravity.CENTER);

                            macomponentcheckbox.setVisibility(View.VISIBLE);
                            macomponentcheckbox.setOnCheckedChangeListener(null);//this line is important --
                            macomponentcheckbox.setFocusable(false);
                            macomponentcheckbox.setChecked(checkboxIsCheckedlist.get(position).get(i));//if it was checked before check it
                                                                                                       //when it comes to screen
                            macomponentcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if(isChecked ){
                                        checkboxIsCheckedlist.get(position).set(maFlexboxLayout.indexOfChild(buttonView), true);
                                    }else{
                                        checkboxIsCheckedlist.get(position).set(maFlexboxLayout.indexOfChild(buttonView), false);
                                    }
                                }
                            });

                            maFlexboxLayout.addView(macomponentcheckbox);
                            Log.d("checkbox","checkbox added to layout!!!");

                        }else{//not a checkbox then
                            TextView macomponenttextview = new TextView(parent.getContext());
                            macomponenttextview.setGravity(Gravity.CENTER);
                            macomponenttextview.setText(thispower.getComponents().get(i).getLabel());
                            maFlexboxLayout.addView(macomponenttextview);
                        }
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