package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 4/1/18.
 */

public abstract class Feat implements Parcelable {

    //variables
    String name;

    //private boolean separator = false
    private int separator = 0; //TODO will need to do something here to prevent anything other than true or False
                                //for now 0 will be false 1 will be true

//   public static Feat createFeat(Parcel in){
//
//       return
//
//   }

    //constructor
    public Feat(String givenName, int isseparator){

        name = givenName;
        separator = isseparator;
    }

    public  abstract String getLabel();
    public  abstract List<IComponent> getComponents();


    //Other method
    public String getName(){
        return name;
    }


    public void printFeat(){
        Log.d("type of Feat","Ima generic feat");

    }
    //other methods
    public int isSeparator() {
        return separator;
    }


    /////////////////Parceling part/////////////////////////

    //Writing
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(separator);
    }

    //Reading
    protected Feat(Parcel in){
        name = in.readString();
        separator = in.readInt();
    }

    //Others
    @Override
    public int describeContents() {
        return 0;
    }

//    public static final Parcelable.Creator <Feat> CREATOR = new Parcelable.Creator<Feat>() {
//        // This simply calls our new constructor (typically private) and
//        // passes along the unmarshalled `Parcel`, and then returns the new object!
//        @Override
//        public Feat createFromParcel(Parcel in) {
//            return new Feat(in);
//        }
//        // We just need to copy this and change the type to match our class.
//        @Override
//        public Feat[] newArray(int size) {
//            return new Feat[size];
//        }
//    };

}
