package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

public class CheckboxComponent implements IComponent {

    public CheckboxComponent (){
        isChecked = false;

    }
    public CheckboxComponent(int i){
        if(i==0){
            isChecked = false;
        }
        else{
            isChecked = true;
        }

    }

    boolean isChecked;

    //getters
    public boolean isChecked() {
        return isChecked;
    }

    //setters
    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String getLabel() {
        return "\u25A1";
    }
}
