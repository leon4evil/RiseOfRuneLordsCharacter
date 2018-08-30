package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.List;

/**
 * Created by leon on 4/1/18.
 */

public abstract class Feat implements Parcelable {

    //variables
    protected String name;

    //constructor
    public Feat(String givenName){

        name = givenName;
    }

    public abstract String getLabel();
    public abstract List<IComponent> getComponents();
    public abstract void addComponents(List<IComponent> componentList);
    public abstract void addComponent(IComponent sumComponent, int index);
    public abstract void setName(String givenName);

    //Other method
    public String getName(){
        return name;
    }


    public abstract String printFeat();

    /////////////////Parceling part/////////////////////////

    //Writing
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
    }

    //Reading
    protected Feat(Parcel in){
        name = in.readString();
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
