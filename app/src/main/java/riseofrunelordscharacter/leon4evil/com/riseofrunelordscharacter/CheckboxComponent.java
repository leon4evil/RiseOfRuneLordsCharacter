package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.util.Log;

public class CheckboxComponent implements IComponent {

    boolean isChecked;
    String description;

    public CheckboxComponent (){
        isChecked = false;
        description = "";

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
    public void printComponent() {
        Log.d("this component is a ","\u25A1");
        if(isChecked==true){
            Log.d("and it is ","checked");
        }
        else {
            Log.d("and it is ","not checked");
        }
    }

//    public String getLabel() {
//        return "\u25A1";
//    }
}
