import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;

import java.io.File;

public class Playground {

    public static void main(String[] args){
        test0();
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
