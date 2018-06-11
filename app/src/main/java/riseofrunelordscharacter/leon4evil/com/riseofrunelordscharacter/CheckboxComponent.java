package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.util.Log;

public class CheckboxComponent implements IComponent {

    boolean isChecked;
    String description;

    public CheckboxComponent (){
        isChecked = false;
        description = "";

    }
    public CheckboxComponent(String givenDescription){
        description = givenDescription;
    }

    public CheckboxComponent(int i){
        if(i==0){
            isChecked = false;
        }
        else{
            isChecked = true;
        }
        description="";
    }


    //getters
    public boolean isChecked() {
        return isChecked;
    }

    //setters
    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String givenDescription) {
        description = givenDescription;
    }



    @Override
    public String printComponent(){
        String componentString = "\t\t\t\t\t<component>\n"+
                "\t\t\t\t\t\t<checkbox>\n"+
                "\t\t\t\t\t\t\t<ischecked>";

        if(isChecked==true){
            //Log.d("Checkbox Component","is checked");
            componentString = componentString +"checked";
        }
        else {
            //Log.d("Checkbox Component", "is not checked");
            componentString = componentString +"notchecked";

        }
        componentString = componentString +"</ischecked>\n\t\t\t\t\t\t<description>"+description+"</description>\n\t\t\t\t\t\t\t</checkbox>\n\t\t\t\t\t\t</component>\n";
        return componentString;
    }
//    @Override
//    public void printComponent() {
//        Log.d("this component is a ","\u25A1");
//        if(isChecked==true){
//            Log.d("and it is ","checked");
//        }
//        else {
//            Log.d("and it is ","not checked");
//        }
//    }

//    public String getLabel() {
//        return "\u25A1";
//    }
}
