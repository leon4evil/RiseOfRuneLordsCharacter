package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

public class StringComponent implements IComponent {

    private String description;

    public StringComponent(String givenvalue){
        description = givenvalue;
    }

    //getters
    @Override
    public String getDescription(){
        return description;
    }
    //setters
    @Override
    public void setDescription(String givenDescrition) {
        this.description = description;
    }

    @Override
    public void printComponent(){

    }

}
