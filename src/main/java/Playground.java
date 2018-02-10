import engine.converter.SplitPart;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Playground {

    public static void main(String[] args){
//        test0();
        test1();
    }

    //Parse die Maske zu einer Liste mit einzelnen Elementen
    private static List<String> mascToList(String masc){
        List<String> splitParts = new ArrayList<>();
        for(SplitPart p : SplitPart.values()){ splitParts.add(p.toString()); }

        List<String> mascParts = new ArrayList<>();
        mascParts.add(masc);

        for(int i=0; i<mascParts.size(); i++){
            String mascPart = mascParts.get(i);
            for(int j=0; j<splitParts.size(); j++){
                String splitPart = splitParts.get(j);
                if(mascPart.equals(splitPart)){
                    continue;
                }
                else if(mascPart.contains(splitPart)){
                    //System.out.println("FOUND -> " + splitPart + " in Part " + i);

                    String[] tmp = mascPart.split(splitPart);
                    //alten Part löschen
                    mascParts.remove(i);

                    // neue Parts einfügen
                    // Fallunterscheidung
                    if(tmp.length == 1){  //_X
                        mascParts.add(i, tmp[0]);
                        mascParts.add(i+1, splitPart);
                    }
                    else if(tmp.length == 2){  //X_ or _X_
                        if(tmp[0].isEmpty()){  //X_
                            mascParts.add(i, splitPart);
                            mascParts.add(i+1, tmp[1]);
                        }
                        else{  //_X_
                            mascParts.add(i, tmp[0]);
                            mascParts.add(i+1, splitPart);
                            mascParts.add(i+2, tmp[1]);
                        }
                    }

                    i = -1;
                    j = 0;
                    //System.out.println("--> " + mascParts);
                    break;
                }
            }
        }
        return mascParts;
    }

    private static void test1(){
        String fileName = "Elvis Presley - Jailhouse Rock - 1952";
        String masc = "%artist% - %title% - %year%";

        System.out.println("FileName: " + fileName);
        System.out.println("Maske: " + masc);

        //Maske umwandeln
        List<String> mascParts = mascToList(masc);
        System.out.println(mascParts);

        //Maske -> Regex
        String regex = "";
        for(String s : mascParts){
            if(SplitPart.getPart(s) != null){
                regex += "(.*)";
            }
            else{
                regex += s;
            }
        }

        //String erkennen
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(fileName);

        if(m.find()){
            System.out.println("GroupCount: " + m.groupCount());
            for(int i=0; i<=m.groupCount(); i++){
                System.out.println("GroupIndex: " + i + " -> " + m.group(i));
            }
        }

        //Daten für Tags auslesen
        HashMap<SplitPart, String> tagData = new HashMap<>();

        int i=1;
        for(String s : mascParts){
            SplitPart sp = SplitPart.getPart(s);
            if(sp != null){
                tagData.put(sp, m.group(i));
                i++;
                sp = null;
            }
        }

        System.out.println("Map: " + tagData);
    }

    private static void test0(){
        System.out.println("Hello World!");

        try{
            File f = new File("/home/ansch190/Musik/Auto/").listFiles()[0];

            AudioFile af = AudioFileIO.read(f);
            Tag tag = af.getTag();

            System.out.println(af);
            System.out.println(tag);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
