package sample.tabs;

import javafx.beans.property.SimpleStringProperty;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.File;

//Klasse für die Zeilen der Tabelle
public class MusicFile {

    //Tabellen-Spalten
    private SimpleStringProperty filename = null;
    private SimpleStringProperty title = null;
    private SimpleStringProperty artist = null;
    private SimpleStringProperty album = null;
    private SimpleStringProperty year = null;
    private SimpleStringProperty disk = null;
    private SimpleStringProperty track = null;
    private SimpleStringProperty comment = null;
    private SimpleStringProperty albumartist = null;
    private SimpleStringProperty composer = null;
    private SimpleStringProperty genre = null;

    private String disk_NO = "";
    private String disk_TOTAL = "";
    private String track_NO = "";
    private String track_TOTAL = "";

    private File file = null;

    public MusicFile(File file){
        this.file = file;
        init();
        analyze();
    }

    public MusicFile(){
        init();
    }

    private void init(){
        filename = new SimpleStringProperty("");
        title = new SimpleStringProperty("");
        artist = new SimpleStringProperty("");
        album = new SimpleStringProperty("");
        year = new SimpleStringProperty("");
        disk = new SimpleStringProperty("");
        track = new SimpleStringProperty("");
        comment = new SimpleStringProperty("");
        albumartist = new SimpleStringProperty("");
        composer = new SimpleStringProperty("");
        genre = new SimpleStringProperty("");
    }

    private void analyze(){
        setFilename(file.getName());
        readTags();
    }

    private void readTags(){
        System.out.println(file.getName());
        try{
            AudioFile af = AudioFileIO.read(file);
            Tag tag = af.getTag();

            System.out.println(af);
            System.out.println(tag);

            //Tag auslesen
            setTitle(tag.getFirst(FieldKey.TITLE));
            setArtist(tag.getFirst(FieldKey.ARTIST));
            setAlbum(tag.getFirst(FieldKey.ALBUM));
            setYear(tag.getFirst(FieldKey.YEAR));

            //Disk-Nummer
            disk_NO = tag.getFirst(FieldKey.DISC_NO);
            disk_TOTAL = tag.getFirst(FieldKey.DISC_TOTAL);

            if(disk_TOTAL.isEmpty()){
                setDisk(disk_NO);
            }
            else if(disk_TOTAL.isEmpty() && disk_NO.isEmpty()){
                setDisk("");
            }
            else{
                setDisk(disk_NO + "/" + disk_TOTAL);
            }

            //Track-Nummer
            track_NO = tag.getFirst(FieldKey.TRACK);
            track_TOTAL = tag.getFirst(FieldKey.TRACK_TOTAL);

            if(track_TOTAL.isEmpty()){
                setTrack(track_NO);
            }
            else if(track_TOTAL.isEmpty() && track_NO.isEmpty()){
                setTrack("");
            }
            else{
                setTrack(track_NO + "/" + track_TOTAL);
            }

            setComment(tag.getFirst(FieldKey.COMMENT));
            setAlbumartist(tag.getFirst(FieldKey.ALBUM_ARTIST));
            setComposer(tag.getFirst(FieldKey.COMPOSER));
            setGenre(tag.getFirst(FieldKey.GENRE));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //### GET ###

    public String getFilename(){
        return this.filename.get();
    }

    public String getTitle(){
        return this.title.get();
    }

    public String getArtist(){
        return this.artist.get();
    }

    public String getAlbum(){
        return this.album.get();
    }

    public String getYear(){
        return this.year.get();
    }

    public String getDisk(){
        return this.disk.get();
    }

    public String getTrack(){
        return this.track.get();
    }

    public String getComment(){
        return this.comment.get();
    }

    public String getAlbumartist(){
        return this.albumartist.get();
    }

    public String getComposer(){
        return this.composer.get();
    }

    public String getGenre(){
        return this.genre.get();
    }

    //### SET ###

    public void setFilename(String s){
        this.filename.set(s);
    }

    public void setTitle(String s){
        this.title.set(s);
    }

    public void setArtist(String s){
        this.artist.set(s);
    }

    public void setAlbum(String s){
        this.album.set(s);
    }

    public void setYear(String s){
        this.year.set(s);
    }

    public void setDisk(String s){
        this.disk.set(s);
    }

    public void setTrack(String s){
        this.track.set(s);
    }

    public void setComment(String s){
        this.comment.set(s);
    }

    public void setAlbumartist(String s){
        this.albumartist.set(s);
    }

    public void setComposer(String s){
        this.composer.set(s);
    }

    public void setGenre(String s){
        this.genre.set(s);
    }

    @Override
    public String toString(){
        String s = "";
        s += filename.get() + "#";
        s += title.get() + "#";
        s += artist.get() + "#";
        s += album.get() + "#";
        s += year.get() + "#";
        s += disk.get() + "#";
        s += track.get() + "#";
        s += comment.get() + "#";
        s += albumartist.get() + "#";
        s += composer.get() + "#";
        s += genre.get() + "#";
        return s;
    }

}
