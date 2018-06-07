package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CharacterBuilder {

    private InputStream maStream;
    private InputStreamReader maReader;
    private Context maContext;


    public CharacterBuilder(String XMLFilename,Context givenContext) { //this will build a character from an XML File
        try {
            AssetManager assetmngr = givenContext.getAssets();
            maStream = assetmngr.open(XMLFilename);//*this.openFileInput("sajan.xml");*/
            if (maStream != null) {
                maReader = new InputStreamReader(maStream);
            }
        } catch (FileNotFoundException e) {
            Log.d("Main Activity", "File not found");
        } catch (IOException e) {
            Log.d("Main Activity", "Cannot read File");
        }
    }

    private List<gameCharacter> readXMLfile() {

        String lastTag="";
        String currentString;

        gameCharacter currentGameCharacter = new gameCharacter("","");
        Feat  currentFeat = new Skill("",0,0);
        IComponent currentComponent = new CheckboxComponent();// = new FeatComponent("");


        List<gameCharacter> currentGameCharacterList = new ArrayList<gameCharacter>();
        List<IComponent> currentFeatComponentList = new ArrayList<IComponent>();
        List<Feat> currentFeatList = new ArrayList<Feat>();

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(maReader);
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {
                    Log.d("Main Activity", "Start Document");

                } else if (eventType == XmlPullParser.START_TAG) {//start tag cases we create
                    lastTag = xpp.getName();                      //what we find
                    if(lastTag.equals( "name")){
                        currentGameCharacter = new gameCharacter("","");
                    }
                    if (lastTag.equals("skillname")){
                        currentFeat = new Skill("",0,0);
                    }
                    if(lastTag.equals("powername")){
                        currentFeat = new Power();
                    }
                    if(lastTag.equals("component")){
                        //currentComponent = new FeatComponent("");
                    }
                    if(lastTag.equals("checkbox")){
                        currentComponent = new CheckboxComponent();
                    }
                    if(lastTag.equals("string")){
                        currentComponent = new StringComponent("");
                    }
                    if(lastTag.equals("description")){
                        //currentComponent
                    }

                    Log.d("main Activity", "Start Tag " + lastTag);

                } else if (eventType == XmlPullParser.END_TAG) { //When we're at a closing tag
                    currentString =xpp.getName();
                    lastTag="";
                    if(currentString.equals("name")){ }
                    if(currentString.equals( "component")){
//                         Log.d("the current compo is ",currentComponent.getDescription());
//                         currentFeatComponentList.add(currentComponent);
//                         Log.d("szeOfCureatComponentLst",String.valueOf(currentFeatComponentList.size()));
                    }
                    if(currentString.equals("checkbox") || currentString.equals("string")){
                        currentFeatComponentList.add(currentComponent);
                    }
                    if(currentString.equals("skillname")){}
                    if(currentString.equals( "powername")){}
                    if(currentString.equals( "feat")){
                        currentFeat.addComponents(currentFeatComponentList);
                        currentGameCharacter.addFeat(currentFeat);
                        currentFeatComponentList = new ArrayList<IComponent>();
                    }
                    if(currentString.equals("character")){
                        currentGameCharacterList.add(currentGameCharacter);
                    }
                    Log.d("Main Activity", "End Tag "+currentString);

                } else if (eventType == XmlPullParser.TEXT) { //When we're in between tags
                    currentString = xpp.getText();
                    if(lastTag.equals( "name")){
                        Log.d("assinginng name","name is"+currentString);
                        currentGameCharacter.setCharacterName(currentString);
                    }
                    if (lastTag.equals("skillname")){
                        currentFeat.setName(currentString);
                    }
                    if(lastTag.equals( "powername")){
                        currentFeat.setName(currentString);
                    }
                    if(lastTag.equals( "component")){
                        //currentComponent.setDescription(currentString);
                        //Log.d("this mofo Component is ",currentString);
                    }
//                    if(lastTag.equals("checkbox") || lastTag.equals("string")){
//                        currentComponent.setDescription(currentString);
//                    }
                    if(lastTag.equals( "string")){
                        currentComponent.setDescription(currentString);
                    }
                    if(lastTag.equals( "description")){
                        currentComponent.setDescription(currentString);
                    }
                    if(lastTag.equals( "ischecked")){
                        if(currentString.equals("checked")){
                            ((CheckboxComponent) currentComponent).setChecked(true);
                        }
                        if(currentString.equals("notchecked")){
                            ((CheckboxComponent) currentComponent).setChecked(false);
                        }
                    }


                    Log.d("Main Activity", "Text " + currentString);
                }
                eventType = xpp.next();
            }
            Log.d("Main Activity", "End of document");
        }
        catch (XmlPullParserException e) { //handle possible exceptions
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currentGameCharacterList;
    }
    public List<gameCharacter> getCharacters(){
        List<gameCharacter> characterList;
        characterList = readXMLfile();

        return characterList;
    }
}






