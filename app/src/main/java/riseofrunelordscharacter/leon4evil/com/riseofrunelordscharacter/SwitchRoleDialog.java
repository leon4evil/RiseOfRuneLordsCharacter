package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwitchRoleDialog extends DialogFragment {
    int selected;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context macontext = getContext(); //so get context has to be called when
                                                //fregment is already attached to activity otherewise it
                                                //returns null. Since I need it in inner classes ima save it
        final Activity maactivity= getActivity();

        final String thischaractername = getArguments().getString("character name");
        final String pathtofile = getArguments().getString("associatedfile");

        final List<String> poisonList = new ArrayList<>(); //will hold the roles in string form
        String[] poisonArray;                       //same but in an array instead of list


        try {   //this horrible thing is supposed to get the all files that start with the Characters name

            AssetManager assetmngr = macontext.getAssets();//first get all file names
            String filenamesarray[];
            filenamesarray = assetmngr.list("");//this gets all the file names in the assets folder
                                                //and puts them in the string array
            Pattern p = Pattern.compile(thischaractername+"([A-Za-z_0-9]+)\\.xml$");//this pattern matcher stuff
                                                                                    //will find all files that start with name of character
            Matcher m;
            for(int i = 0;i<filenamesarray.length;i++) {
                Log.d("what is in this list?",String.valueOf(filenamesarray[i].matches("^([A-Za-z_0-9]+)\\.xml$")));
                m = p.matcher(filenamesarray[i]);
                while(m.find()) {
                    poisonList.add(m.group(1));
                    Log.d("what is the name?", m.group(1));
                }
            }
        }catch (FileNotFoundException e) {
            Log.d("Main Activity", "File not found");
        } catch (IOException e) {
            Log.d("Main Activity", "Cannot read File");
        }
        poisonArray = poisonList.toArray(new String[poisonList.size()]); //convert linked list to array


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); //make the dialog!
        builder.setTitle("Select New Role")
                .setItems(poisonArray, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        selected = which;
                        FragmentManager fm2 = getFragmentManager(); //I dont get this Fragment manager stuff
                        DialogFragment seconddialog = new AreyouSureDialog(); //Creating new Dialog fragment
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("filename", thischaractername+poisonList.get(which)+".xml"); //pass data to dialog fragment
                        bundle2.putString("associatedfile",pathtofile);

                         seconddialog.setArguments(bundle2);
                         seconddialog.show(fm2, "areyousure");
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}