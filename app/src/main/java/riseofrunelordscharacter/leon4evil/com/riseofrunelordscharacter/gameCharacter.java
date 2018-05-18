package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by leon on 4/3/18.
 */

public class gameCharacter implements Parcelable {

    private String characterName;
    private String characterClass;
    private ArrayList<Feat> characterFeats = new ArrayList<Feat>();

    //constructor
    public gameCharacter(String givenCharacterName, String givenCharacterClass) {
        characterName = givenCharacterName;
        characterClass = givenCharacterClass;
    }

    //getters
    public String getCharacterName() {
        return characterName;
    }
    public String getCharacterClass() {
        return characterClass;
    }
    public ArrayList<Feat> getCharacterFeats() {
        return characterFeats;
    }

    //setters
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    //other methods
    public void addFeat(Feat givenFeat) {
        characterFeats.add(givenFeat);
    }

    public void printFeats() { //Prints feats to log
        for (int x = 0; x < characterFeats.size(); x++) {
            characterFeats.get(x).printFeat();
        }
    }

    public void orderFeats() {//orders Feats
        removeSeparators();
        int j;
        for (int i = 1; i < characterFeats.size(); i++) {
                //insertSort kinda more like bubble sort
                //but makes code check every time, makes things inefficient
                j = i;
                //This while here is comparing the first character of the object type name (P for power and S for skill for example)
                while (j > 0 && characterFeats.get(j).getClass().toString().charAt(70) > characterFeats.get(j - 1).getClass().toString().charAt(70)) {

                    Log.d("SWAP",characterFeats.get(j).getClass().toString());
                    Collections.swap(characterFeats, j - 1, j);
                    j = j - 1;
                }
        }
        addSeparators();
    }

    //this method puts separators on the character feats
    //hugely inefficient this hole method could be avoided if we organi¡ze feats when we
    // add them as opposed to later
    private void addSeparators(){
        //make sure there is no separators already
        removeSeparators();

        //here im getting the object type of first item in character feats so I can add
        //a separator
        if(characterFeats.size()>0) {//make sure there are feats
            String[] diviedupString = characterFeats.get(0).getClass().toString().split("[.]");
            Log.d("type of object", diviedupString[diviedupString.length - 1]);

            // create and add generic feat at beginning of character feats
            //to be first separator
            Feat separator1 = new Separator(diviedupString[diviedupString.length - 1], 1);
            characterFeats.add(0, separator1);

            //and when we find a different type of object
            //add an extra separator
            String[] seconddiviedupString;
            Feat aSeparator;
            int currentfeatlistsize = characterFeats.size();
            int mostrecentseparator = 1;
            for (int x = 2; currentfeatlistsize >= x; x++) {
                if (characterFeats.get(mostrecentseparator).getClass() != characterFeats.get(x).getClass()) {
                    seconddiviedupString = characterFeats.get(x).getClass().toString().split("[.]");
                    aSeparator = new Separator(seconddiviedupString[seconddiviedupString.length - 1], 1);
                    characterFeats.add(x, aSeparator);
                    mostrecentseparator = x + 1;
                    x++;
                }

            }

        }
    }
    private void removeSeparators(){
        for(int x = 0;x<characterFeats.size();x++){
            if(characterFeats.get(x).isSeparator()==1) {
                characterFeats.remove(x);
            }
        }
    }

    /////////////////Parceling part/////////////////////////

    //Writing
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(characterName);
        out.writeString(characterClass);
       //out.writeTypedList(characterFeats);
        out.writeList(characterFeats);
    }

    //Reading
    public gameCharacter(Parcel in){
        characterName = in.readString();
        characterClass = in.readString();
        in.readList(characterFeats,Feat.class.getClassLoader());

    }

    //Others
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public gameCharacter createFromParcel(Parcel in) {
            return new gameCharacter(in);
        }

        public gameCharacter[] newArray(int size) {
            return new gameCharacter[size];
        }
    };

}