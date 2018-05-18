package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter.Feat;

/**
 * Created by leon on 4/3/18.
 */

public class Skill extends Feat {

    private int basedie;
    private int added;
    private String name;


    //constructor
    public Skill(String givenName,int givenBasedie,int givenAdded) {
        super(givenName,0);
        name = givenName;
        basedie = givenBasedie;
        added = givenAdded;
    }



    //Getters
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLabel() {
        return getName();
    }

    //Setters
    public String getName() {
        return name;
    }

    //getters
    public int getBasedie() {
        return basedie;
    }

    public int getAdded() {
        return added;
    }

    //setters
    public void setBasedie(int basedie) {
        this.basedie = basedie;
    }

    public void setAdded(int added) {
        this.added = added;
    }
    @Override
    public void printFeat(){
        Log.d("type of Feat","Ima skill");

    }

    @Override
    public List<IComponent> getComponents(){
        return new ArrayList<IComponent>();

    }

    /////////////////Parceling part/////////////////////////

    //Writing
    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out,flags);
        out.writeString(name);
        out.writeInt(basedie);
        out.writeInt(added);

    }
    //Reading
    public Skill(Parcel in){
        super(in);
        name =  in.readString();
        basedie = in.readInt();
        added = in.readInt();
    }

    //Others

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator <Skill> CREATOR = new Parcelable.Creator<Skill>() {
        public Skill createFromParcel(Parcel in) {
            return new Skill(in);
        }

        public Skill[] newArray(int size) {
            return new Skill[size];
        }
    };

}
