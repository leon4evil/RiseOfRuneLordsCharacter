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
     String thischaractername;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context macontext = getContext(); //so get context has to be called when
                                                //fregment is already attached to activity otherewise it
                                                //returns null. Since I need it in inner classes ima save it

         thischaractername = getArguments().getString("character name");
        final String pathtofile = getArguments().getString("associatedfile");


        thischaractername = RoleExtractor.getJustName(thischaractername);//Doing this so user can switch roles again
        final String[] poisonArray = RoleExtractor.getJustRoles(getContext(),thischaractername); //Gets Roles Available for character


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
                        bundle2.putString("filename", thischaractername+poisonArray[which]+".xml"); //pass data to dialog fragment
                        bundle2.putString("associatedfile",pathtofile);

                         seconddialog.setArguments(bundle2);
                         seconddialog.show(fm2, "areyousure");
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }





}