package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoleExtractor {


    //Gets name with role concatenated to it
    public static String getNameAndRole(String givenCharacterName, String givenRoleFilename){
        String macharactername = givenCharacterName;
        String roleFilename = givenRoleFilename;
        Pattern p = Pattern.compile("([A-Za-z0-9]+)\\.xml$");
        Matcher m = p.matcher(roleFilename);
        m.find();
        String tempcharactername = m.group(1);
        Log.d("whats ma new name>?", tempcharactername);
        p = Pattern.compile(macharactername+"([A-Za-z_0-9]+)");
        m = p.matcher(tempcharactername);
        m.find();
        String secondnamepart = m.group(1);
        String[] macharacternamearray = secondnamepart.split("(?=[A-Z])",5); //not my regex
        for(int i = 0;i<macharacternamearray.length; i++){                  //got it from stackOverflow NawMan
            Log.d("what is in this array", macharacternamearray[i]);
            macharactername= macharactername +" "+macharacternamearray[i];
        }
        return macharactername;
    }

    //Removes role from name if it has one
    public static String getJustName(String givenCharacterName){
        String macleancharactername = givenCharacterName;
        String[] splitthischaractername =givenCharacterName.split("\\s|(?=[A-Z])");
        for(int i = 0 ;i<splitthischaractername.length;i++){
            Log.d("charactername", "\""+macleancharactername+"\""); //show in log
            Log.d("splitstring",splitthischaractername[i]+String.valueOf(i));
        }
        int finder = 0; // magic variable I know but split for sum reason puts an empty string at beginning
        while(splitthischaractername[finder].length()==0){
            finder++;
            Log.d("finder is",String.valueOf(finder));
        }
        macleancharactername = splitthischaractername[finder];
        return macleancharactername;
    }


    //Returns the roles in a String array
    public static String [] getJustRoles(Context givenContext, String givenCharacterName){
        String maCharacterName = givenCharacterName;
        List<String> poisonList = new ArrayList<>(); //will hold the roles in string form
        Context macontext = givenContext;

        try {   //this horrible thing is supposed to get the all files that start with the Characters name

            AssetManager assetmngr = macontext.getAssets();//first get all file names
            String filenamesarray[];
            filenamesarray = assetmngr.list("");//this gets all the file names in the assets folder
            //and puts them in the string array


            Pattern p = Pattern.compile(maCharacterName+"([A-Za-z_0-9]+)\\.xml$");//this pattern matcher stuff
            //will find all files that start with name of character
            Matcher m;
            for(int i = 0;i<filenamesarray.length;i++) {
                Log.d("what is in this list?",String.valueOf(filenamesarray[i].matches("^([A-Za-z_0-9]+)\\.xml$")));
                m = p.matcher(filenamesarray[i]);
                while(m.find()) {
                    poisonList.add(m.group(1));
                    Log.d("what is the name?", m.group(1));
                }
            }
        }catch (FileNotFoundException e) {
            Log.d("Main Activity", "File not found");
        } catch (IOException e) {
            Log.d("Main Activity", "Cannot read File");
        }

        String[] rolesArray = poisonList.toArray(new String[poisonList.size()]); //convert linked list to array
        return rolesArray;
    }


}
