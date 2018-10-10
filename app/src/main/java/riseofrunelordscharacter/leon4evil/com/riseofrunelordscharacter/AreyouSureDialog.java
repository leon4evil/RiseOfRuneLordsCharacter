package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AreyouSureDialog extends DialogFragment {
    String roleFilename;
    String pathtofile;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        roleFilename = getArguments().getString("filename");
        pathtofile = getArguments().getString("associatedfile");

        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
                        builder.setTitle("are you sure?").setPositiveButton("HAHAHA", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //user clicks Yes
                                //Get data so we can create new character with assigned role
                                Log.d("what I clicked","\""+roleFilename+"\"");
                                CharacterBuilder mabuilder = new CharacterBuilder(roleFilename, getContext());
                                List<GameCharacter> macharacters = mabuilder.getCharacters();
                                GameCharacter macharacter = macharacters.get(0);
                                String macharactername = RoleExtractor.getNameAndRole(macharacter.getCharacterName(),roleFilename);//so we get name with role
                                macharacter.setCharacterName(macharactername);

                                //Create Intent so we can go to activity
                                Intent featIntent = new Intent(getActivity(), FeatActivity.class);
                                featIntent.putExtra("clickedCharacter", macharacters.get(0));
                                featIntent.putExtra("associatedfile",pathtofile);
                                getActivity().finish();
                                Log.d("whats my path to file?",pathtofile);
                                startActivity(featIntent);
                                }
                        }  ).setNegativeButton("Better Not", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
      return builder.create();
    }



}
