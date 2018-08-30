package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.List;

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
                                Log.d("what I clicked",roleFilename);
                               CharacterBuilder mabuilder = new CharacterBuilder(roleFilename, getContext());
                             List<GameCharacter> macharacters = mabuilder.getCharacters();

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
