package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 4/3/18.
 */

public class Power extends Feat {

    private List<IComponent> powerComponents = new ArrayList<>();

    public Power(String giveName, List<IComponent> givePowerComponents){
        super(giveName,0);
        powerComponents = givePowerComponents;

    }

    public Power(List<IComponent> givenPowerComponents){
        super(null,0);
        powerComponents = givenPowerComponents;
    }
    @Override
    public  List<IComponent> getComponents(){
        return powerComponents;

    }

    @Override
    public String getLabel() {
//             return getDescription();
//        String label = "";
//        for (IComponent c :  powerComponents) {
//            label+=c.getLabel();
//
//        }
//        return label;
        return getName();
    }

    //other methods
    @Override
    public void printFeat(){
        Log.d("type of Feat","Ima power");
    }

    /////////////////Parceling part/////////////////////////


    //Writing
    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out,flags);
        out.writeString(name);
        out.writeInt(powerComponents.size());

            for(int i = 0; powerComponents.size() > i; i++){
                  if (powerComponents.get(i) instanceof StringComponent){
                     out.writeInt(19); //19 is for 's' number 19 letter in alphabet
                     StringComponent se = (StringComponent) powerComponents.get(i);
                     out.writeString(se.getValue());
                     out.writeInt(11);

                 }
                 else{
                     CheckboxComponent cc = (CheckboxComponent) powerComponents.get(i);
                     out.writeInt(3); //3 is for 'c' number 3 letter in alphabet
                     if(cc.isChecked()==false){
                         out.writeString("");
                         out.writeInt(0);
                     }
                     else{
                         out.writeString("");
                         out.writeInt(1);
                     }
                 }
            }
    }

    //Reading
    protected Power(Parcel in){
        super(in);
        String name = in.readString();
        int numcomponents = in.readInt();
        int stringorcheckedbox;
        int isChecked;
        int sumRandomint;
        String stupedString;

        for(int i =0;i<numcomponents;i++){
            stringorcheckedbox = in.readInt();
            if(stringorcheckedbox==19){
                StringComponent sc =new StringComponent(in.readString());
                powerComponents.add(sc);
                sumRandomint=in.readInt();
            }
            if(stringorcheckedbox == 3){
                stupedString = in.readString();
                CheckboxComponent cc = new CheckboxComponent();
                isChecked = in.readInt();
                if(isChecked == 0){
                    cc.setChecked(false);
                }
                else{
                    cc.setChecked(true);
                }
                powerComponents.add(cc);
            }

        }

    }

    //Others
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator <Power> CREATOR = new Parcelable.Creator<Power>() {
        public Power createFromParcel(Parcel in) {
            return new Power(in);
        }

        public Power[] newArray(int size) {
            return new Power[size];
        }
    };
}
