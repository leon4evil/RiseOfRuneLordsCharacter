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
    private List<IComponent> skillComponents= new ArrayList<>();


    //constructor
    public Skill(String givenName,int givenBasedie,int givenAdded) {
        super(givenName);
        name = givenName;
        basedie = givenBasedie;
        added = givenAdded;
    }

    public Skill(String givenName, int givenBasedie, int givenAdded, List<IComponent> givenComponentList){
        super("");
        name=givenName;
        basedie=givenBasedie;
        added = givenAdded;
        skillComponents = new ArrayList<IComponent>(givenComponentList);
    }



    //getters
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLabel() {
        return getName();
    }

    @Override
    public List<IComponent> getComponents(){
        return skillComponents;
    }

    public int getBasedie() {
        return basedie;
    }

    public int getAdded() {
        return added;
    }

    //setters
    public String getName() {
        return name;
    }

    public void setBasedie(int basedie) {
        this.basedie = basedie;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    //others
    public String printFeat(){

        //Log.d("Feat name is: ", skillname);
        String featString = "<feat>\n" +
                "\t\t<skill>\n"+
                "\t\t\t<skillname>"+name+"</skillname>\n";
        //Log.d("Feat Type: ",this.getClass().toString());
        //featString = featString+"Feat Type: "+this.getClass().toString()+"\n";
        //Log.d("components in this feat",String.valueOf(components.size()));
        //featString = featString+"components in this feat"+String.valueOf(components.size())+"\n";
        for (IComponent item:skillComponents) {
            featString = featString+item.printComponent();
        }
        featString=featString+"\t\t</skill>\n </feat>\n";
        return featString;
    }

    @Override
    public void addComponents(List<IComponent> givenComponentList){
        skillComponents = new ArrayList<IComponent>(givenComponentList);
    }

    @Override
    public void addComponent(IComponent sumComponent, int index){
        skillComponents.add(index,sumComponent);
    }

    /////////////////Parceling part/////////////////////////

    //Writing
    @Override
    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out,flags);
        out.writeString(name);
        out.writeInt(basedie);
        out.writeInt(added);

        out.writeInt(skillComponents.size());

        for(int i = 0; skillComponents.size() > i; i++){
            if (skillComponents.get(i) instanceof StringComponent){
                out.writeInt(19); //19 is for 's' number 19th letter in alphabet
                StringComponent se = (StringComponent) skillComponents.get(i);
                out.writeString(se.getDescription());
                out.writeInt(11);

            }
            else{
                CheckboxComponent cc = (CheckboxComponent) skillComponents.get(i);
                out.writeInt(3); //3 is for 'c' number 3rd letter in alphabet
                if(cc.isChecked()==false){
                    out.writeString(skillComponents.get(i).getDescription());
                    out.writeInt(0);
                }
                else{
                    out.writeString(skillComponents.get(i).getDescription());
                    out.writeInt(1);
                }
            }
        }



    }
    //Reading
    public Skill(Parcel in){
        super(in);
        name =  in.readString();
        basedie = in.readInt();
        added = in.readInt();

        int numcomponents = in.readInt();
        int stringorcheckedbox;
        int isChecked;
        int sumRandomint;
        String stupedString;

        for(int i =0;i<numcomponents;i++){
            stringorcheckedbox = in.readInt();
            if(stringorcheckedbox==19){
                StringComponent sc =new StringComponent(in.readString());
                skillComponents.add(sc);
                sumRandomint=in.readInt();
            }
            if(stringorcheckedbox == 3){
                stupedString = in.readString();
                CheckboxComponent cc = new CheckboxComponent();
                cc.setDescription(stupedString);
                isChecked = in.readInt();
                if(isChecked == 0){
                    cc.setChecked(false);
                }
                else{
                    cc.setChecked(true);
                }
                skillComponents.add(cc);
            }
        }



    }

    //others

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