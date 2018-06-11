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
    public void setDescription(String givenDescription) {
        this.description = givenDescription;
    }

    @Override
    public String printComponent(){
        //Log.d("Component type",this.getClass().toString());
        //Log.d("String Component ",description);
        String componentString= "\t\t\t<component>\n"+
                "\t\t\t\t\t<string>"+description+"</string>\n"+
                "\t\t\t</component>\n";
        return componentString;
    }
}
