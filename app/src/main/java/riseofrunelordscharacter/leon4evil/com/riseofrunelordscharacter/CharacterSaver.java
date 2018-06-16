package riseofrunelordscharacter.leon4evil.com.riseofrunelordscharacter;


import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CharacterSaver {
    private GameCharacter workingCharacter;
    private String workingName;
    private List<Feat> workingFeats;
    private List<Feat> workingCompontnens;
    private Context maContext;

    public CharacterSaver(GameCharacter givencharacter, Context givenContext){

        workingCharacter = givencharacter;
        workingName = givencharacter.getCharacterName();
        workingFeats = givencharacter.getCharacterFeats();
        maContext = givenContext;
    }

    public void saveToFile() {
        try {
            //for when file already exist
            int numberOfRepeatedFiles = 2;
            //File sdCard = Environment.getExternalStorageDirectory(); //get location of external storage
            //Not Really sd card. Internal accessible storage in device
            File sdCard = maContext.getExternalFilesDir(null);
            //may have to change this to internal
            File dir = new File(sdCard.getAbsolutePath()+"/thismofoapp");
            Log.d("What is the Path?",dir.getAbsolutePath());
            if(dir.exists()){ // check if directory exists
                Log.d("Does Dir exist?","Directory already Exists!!");
            }else{
                Log.d("Does Dir exist?","Does not. Creating now");
                //Files.createDirectory(dir.toPath()); used this to debug but requires API 26 or higher
                dir.mkdirs();
            }

            File newFile = new File(dir, workingName+".xml");
            File repeatednewFile;

            //see if file already exists. if it does create a different one
            while(newFile.exists()) {//TODO make checking for files more efficient.
                                    //using garbage collector too much
                                    //TODO limit how many files can be created
                                    //this app could fill up entire device

                Log.d("Does File Exist?", "File Already Exists!");

                repeatednewFile = new File(dir, workingName + String.valueOf(numberOfRepeatedFiles) + ".xml");
                //newFile.renameTo(repeatednewFile);
                newFile = repeatednewFile;
                numberOfRepeatedFiles++;

            }
                Log.d("Does File Exist?","Does not. Creating now");
                try{
                    Log.d("creating file",newFile.getName());
                    newFile.createNewFile();
                }
                catch (IOException e){
                    e.printStackTrace();
                }

            //write out file
            FileOutputStream maFileOutputStream = new FileOutputStream(newFile);
            Log.d("Where is ma Directory? " ,String.valueOf(maContext.getFilesDir()));
            String content = workingCharacter.printGameCharacter ();

            maFileOutputStream.write(content.getBytes());
            Log.d("Saving to file","ass");
            maFileOutputStream.close();

            //OutputStreamWriter maWriter = new OutputStreamWriter(maContext.openFileOutput("savedCharacter.xml",Context.MODE_APPEND));
            //OutputStreamWriter maWriter = new OutputStreamWriter(maFileOutputStream);
            //maWriter.write("My ass");
            //maWriter.close();

        }
        catch (FileNotFoundException e){
            Log.d("Could not find File! ","NOOOOOOOOOOO!");
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("CANNOT WRITE","NOOOOOOOOOOOOO!");
            e.printStackTrace();
        }

    }
}