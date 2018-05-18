package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

public class StringComponent implements IComponent {

    //SUCK IT DRY BITCH

    String value;
    StringComponent(String givenvalue){
        value = givenvalue;

    }

    //getters
    String getValue(){
        return value;
    }
    //setters
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getLabel() {
        return value;
    }
}
