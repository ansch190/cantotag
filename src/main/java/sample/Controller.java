package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import sample.tabs.TabTable;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    //GUI-ELEMENTS

    @FXML
    private ComboBox cb_title;
    @FXML
    private ComboBox cb_artist;
    @FXML
    private ComboBox cb_album;
    @FXML
    private ComboBox cb_year;
    @FXML
    private ComboBox cb_disk;
    @FXML
    private ComboBox cb_track;
    @FXML
    private ComboBox cb_comment;
    @FXML
    private ComboBox cb_dir;
    @FXML
    private ComboBox cb_albumartist;
    @FXML
    private ComboBox cb_composer;
    @FXML
    private ComboBox cb_genre;

    @FXML
    private TabPane tabpane;

    @FXML
    private Button b0;

    @FXML
    private MenuItem menu_file_openDir;


    @FXML
    private void b0_click(){
        System.out.println("click!");

        //Datei einlesen
        File f = new File("/home/ansch190/Musik/Bravo Hits 99/CD1/01. P!nk - What About Us.mp3");

        try{
            AudioFile af = AudioFileIO.read(f);
            Tag tag = af.getTag();

            System.out.println(af);
            System.out.println(tag);

            //Tag auslesen
            String title = tag.getFirst(FieldKey.TITLE);
            String artist = tag.getFirst(FieldKey.ARTIST);
            String album = tag.getFirst(FieldKey.ALBUM);
            String year = tag.getFirst(FieldKey.YEAR);
            String disk = tag.getFirst(FieldKey.DISC_NO);
            String disk_total = tag.getFirst(FieldKey.DISC_TOTAL);
            String track = tag.getFirst(FieldKey.TRACK);
            String track_total = tag.getFirst(FieldKey.TRACK_TOTAL);
            String comment = tag.getFirst(FieldKey.COMMENT);
            String albumartist = tag.getFirst(FieldKey.ALBUM_ARTIST);
            String composer = tag.getFirst(FieldKey.COMPOSER);
            String genre = tag.getFirst(FieldKey.GENRE);

            System.out.println(disk);
            System.out.println(disk_total);
            System.out.println(track);
            System.out.println(track_total);

            //Gui füllen
            cb_title.getEditor().setText(title);
            cb_artist.getEditor().setText(artist);
            cb_album.getEditor().setText(album);
            cb_year.getEditor().setText(year);
            cb_disk.getEditor().setText(disk + "/" + disk_total);
            cb_track.getEditor().setText(track + "/" + track_total);
            cb_comment.getEditor().setText(comment);
            cb_dir.getEditor().setText(f.getPath());
            cb_albumartist.getEditor().setText(albumartist);
            cb_composer.getEditor().setText(composer);
            cb_genre.getEditor().setText(genre);

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    private void openDir(){
        System.out.println("File -> open Dir");

        JFileChooser fc = new JFileChooser();

        File startDir = new File(System.getProperty("user.home"));

        fc.setDialogTitle("Open Directory");
        fc.setCurrentDirectory(startDir);
        fc.setDragEnabled(true);
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setMultiSelectionEnabled(false);

        int val = fc.showOpenDialog(null);

        if(val == JFileChooser.APPROVE_OPTION){
            File f = fc.getSelectedFile();
            System.out.println(f);

            //erstelle neues Tab
            createNewTab(f);

            //

        } else {
            System.out.println("Open Directory - CANCEL");
        }
    }

    //### Ausführbarer Code - Später Auslagern ###

    private List<Tab> tabList = null;

    //initialisiere wichtige Dinge
    private void initController(){
        //Tabliste
        tabList = new ArrayList<>();
    }

    //erstelle neues Tab
    private void createNewTab(File f){
        TabTable t = new TabTable(f);

        t.addData2();

        tabpane.getTabs().add(t);
    }

}
