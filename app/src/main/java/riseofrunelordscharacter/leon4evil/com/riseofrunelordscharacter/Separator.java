package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Separator extends Feat {
    public Separator(String givenName) {
        super(givenName);
    }

    protected Separator(Parcel in){
        super(in);

        //name = in.readString();
        //separator = in.readInt();
    }


    @Override
    public List<IComponent> getComponents(){
        return new ArrayList<IComponent>();

    }
    public void addComponents(List<IComponent> componentList){}
    public void setName(String givenName){}



    @Override
    public String getLabel() {
        return "LUIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!";
    }

        public static final Parcelable.Creator <Feat> CREATOR = new Parcelable.Creator<Feat>() {
        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Feat createFromParcel(Parcel in) {
            return new Separator(in);
        }
        // We just need to copy this and change the type to match our class.
        @Override
        public Feat[] newArray(int size) {
            return new Feat[size];
        }
    };
}
