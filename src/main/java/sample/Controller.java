package sample;

import engine.converter.SplitPart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import sample.tabs.MusicFile;
import sample.tabs.TabTable;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private Button b1;

    @FXML
    private MenuItem menu_file_openDir;


    @FXML
    private void b0_click(){
        System.out.println("b0 click!");

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
    private void b1_click(){
        System.out.println("b1 click!");

        try{
            //Ausgewählte Dateien speichern
            for(MusicFile mf : mfs.getMusicFiles()){
                AudioFile af = AudioFileIO.read(mf.getFile());
                Tag tag = af.getTag();

                //Änderungen
                //Title
                String gui_text = cb_title.getEditor().getText();
                String new_text = "";
                if(gui_text.equals("< keep >")){
                    //nichts machen
                }
                else if(gui_text.equals("< remove >")){
                    new_text = "";
                    tag.setField(FieldKey.TITLE, new_text);
                }
                else{
                    new_text = gui_text;
                    tag.setField(FieldKey.TITLE, new_text);
                }

                //Artist
                gui_text = cb_artist.getEditor().getText();
                new_text = "";
                if(gui_text.equals("< keep >")){
                    //nichts machen
                }
                else if(gui_text.equals("< remove >")){
                    new_text = "";
                    tag.setField(FieldKey.ARTIST, new_text);
                }
                else{
                    new_text = gui_text;
                    tag.setField(FieldKey.ARTIST, new_text);
                }

                //Album
                gui_text = cb_album.getEditor().getText();
                new_text = "";
                if(gui_text.equals("< keep >")){
                    //nichts machen
                }
                else if(gui_text.equals("< remove >")){
                    new_text = "";
                    tag.setField(FieldKey.ALBUM, new_text);
                }
                else{
                    new_text = gui_text;
                    tag.setField(FieldKey.ALBUM, new_text);
                }

                //Year
                gui_text = cb_year.getEditor().getText();
                new_text = "";
                if(gui_text.equals("< keep >")){
                    //nichts machen
                }
                else if(gui_text.equals("< remove >")){
                    new_text = "";
                    tag.setField(FieldKey.YEAR, new_text);
                }
                else{
                    new_text = gui_text;
                    tag.setField(FieldKey.YEAR, new_text);
                }

                //Disk
                gui_text = cb_disk.getEditor().getText();
                new_text = "";
                if(gui_text.equals("< keep >")){
                    //nichts machen
                }
                else if(gui_text.equals("< remove >")){
                    new_text = "";
                    tag.setField(FieldKey.DISC_NO, new_text);
                    tag.setField(FieldKey.DISC_TOTAL, new_text);  //Todo geht nicht!
//                    tag.deleteField(FieldKey.DISC_NO);
//                    tag.deleteField(FieldKey.DISC_TOTAL);
//                    tag.addField(FieldKey.DISC_NO, "");
//                    tag.addField(FieldKey.DISC_TOTAL, "");
                }
                else{
                    new_text = gui_text;
                    if(gui_text.contains("/")){
                        String[] split = gui_text.split("/");
                        tag.setField(FieldKey.DISC_NO, split[0]);
                        tag.setField(FieldKey.DISC_TOTAL, split[1]);
                    }
                    else{
                        tag.setField(FieldKey.DISC_NO, new_text);
                        tag.setField(FieldKey.DISC_TOTAL, "");  //Todo geht nicht!
                    }
                }

                //Track
                gui_text = cb_track.getEditor().getText();
                new_text = "";
                if(gui_text.equals("< keep >")){
                    //nichts machen
                }
                else if(gui_text.equals("< remove >")){
                    new_text = "";
                    tag.setField(FieldKey.TRACK, new_text);
                    tag.setField(FieldKey.TRACK_TOTAL, new_text);
                }
                else{
                    new_text = gui_text;
                    if(gui_text.contains("/")){
                        String[] split = gui_text.split("/");
                        tag.setField(FieldKey.TRACK, split[0]);
                        tag.setField(FieldKey.TRACK_TOTAL, split[1]);
                    }
                    else{
                        tag.setField(FieldKey.TRACK, new_text);
                    }
                }

                //Comment
                gui_text = cb_comment.getEditor().getText();
                new_text = "";
                if(gui_text.equals("< keep >")){
                    //nichts machen
                }
                else if(gui_text.equals("< remove >")){
                    new_text = "";
                    tag.setField(FieldKey.COMMENT, new_text);
                }
                else{
                    new_text = gui_text;
                    tag.setField(FieldKey.COMMENT, new_text);
                }

                //Album Artist
                gui_text = cb_albumartist.getEditor().getText();
                new_text = "";
                if(gui_text.equals("< keep >")){
                    //nichts machen
                }
                else if(gui_text.equals("< remove >")){
                    new_text = "";
                    tag.setField(FieldKey.ALBUM_ARTIST, new_text);
                }
                else{
                    new_text = gui_text;
                    tag.setField(FieldKey.ALBUM_ARTIST, new_text);
                }

                //Composer
                gui_text = cb_composer.getEditor().getText();
                new_text = "";
                if(gui_text.equals("< keep >")){
                    //nichts machen
                }
                else if(gui_text.equals("< remove >")){
                    new_text = "";
                    tag.setField(FieldKey.COMPOSER, new_text);
                }
                else{
                    new_text = gui_text;
                    tag.setField(FieldKey.COMPOSER, new_text);
                }

                //Genre
                gui_text = cb_genre.getEditor().getText();
                new_text = "";
                if(gui_text.equals("< keep >")){
                    //nichts machen
                }
                else if(gui_text.equals("< remove >")){
                    new_text = "";
                    tag.setField(FieldKey.GENRE, new_text);
                }
                else{
                    new_text = gui_text;
                    tag.setField(FieldKey.GENRE, new_text);
                }

                //Save
                af.commit();

                //Update
                mf.update();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        //Ansicht aktualisieren, Tabelle
        TabTable tab = (TabTable) tabpane.getSelectionModel().getSelectedItem();
        tab.update();
    }

    private MusicFiles mfs = null;

    //Tags to Gui
    public void setTagInfo(List<MusicFile> musicFiles){
        mfs = new MusicFiles(musicFiles);
        mfs.analyze();
        if(musicFiles.isEmpty()){
            ObservableList olist = FXCollections.observableArrayList();
            //Gui füllen
            cb_title.setItems(olist);
            cb_artist.setItems(olist);
            cb_album.setItems(olist);
            cb_year.setItems(olist);
            cb_disk.setItems(olist);
            cb_track.setItems(olist);
            cb_comment.setItems(olist);
            cb_dir.setItems(olist);
            cb_albumartist.setItems(olist);
            cb_composer.setItems(olist);
            cb_genre.setItems(olist);
        }
        else{
            //Gui füllen
            Set<String> tmp = null;
            //Title
            ObservableList<String> oblist = FXCollections.observableArrayList();
            tmp = mfs.getTagSet(SplitPart.TITLE);
            oblist.addAll(tmp);
            oblist.add(0, "< keep >");
            oblist.add(1, "< remove >");
            for(String s : tmp){
                cb_title.setItems(oblist);
            }
            if(oblist.size() == 3){
                cb_title.getSelectionModel().select(2);
            }
            else{
                cb_title.getSelectionModel().select(0);
            }

            //Artist
            oblist = FXCollections.observableArrayList();
            tmp = mfs.getTagSet(SplitPart.ARTIST);
            oblist.addAll(tmp);
            oblist.add(0, "< keep >");
            oblist.add(1, "< remove >");
            for(String s : tmp){
                cb_artist.setItems(oblist);
            }
            if(oblist.size() == 3){
                cb_artist.getSelectionModel().select(2);
            }
            else{
                cb_artist.getSelectionModel().select(0);
            }


            //Album
            oblist = FXCollections.observableArrayList();
            tmp = mfs.getTagSet(SplitPart.ALBUM);
            oblist.addAll(tmp);
            oblist.add(0, "< keep >");
            oblist.add(1, "< remove >");
            for(String s : tmp){
                cb_album.setItems(oblist);
            }
            if(oblist.size() == 3){
                cb_album.getSelectionModel().select(2);
            }
            else{
                cb_album.getSelectionModel().select(0);
            }

            //Year
            oblist = FXCollections.observableArrayList();
            tmp = mfs.getTagSet(SplitPart.YEAR);
            oblist.addAll(tmp);
            oblist.add(0, "< keep >");
            oblist.add(1, "< remove >");
            for(String s : tmp){
                cb_year.setItems(oblist);
            }
            if(oblist.size() == 3){
                cb_year.getSelectionModel().select(2);
            }
            else{
                cb_year.getSelectionModel().select(0);
            }

            //Disk
            oblist = FXCollections.observableArrayList();
            tmp = mfs.getTagSet(SplitPart.DISK);
            oblist.addAll(tmp);
            oblist.add(0, "< keep >");
            oblist.add(1, "< remove >");
            for(String s : tmp){
                cb_disk.setItems(oblist);
            }
            if(oblist.size() == 3){
                cb_disk.getSelectionModel().select(2);
            }
            else{
                cb_disk.getSelectionModel().select(0);
            }

            //Track
            oblist = FXCollections.observableArrayList();
            tmp = mfs.getTagSet(SplitPart.TRACK);
            oblist.addAll(tmp);
            oblist.add(0, "< keep >");
            oblist.add(1, "< remove >");
            for(String s : tmp){
                cb_track.setItems(oblist);
            }
            if(oblist.size() == 3){
                cb_track.getSelectionModel().select(2);
            }
            else{
                cb_track.getSelectionModel().select(0);
            }

            //Comment
            oblist = FXCollections.observableArrayList();
            tmp = mfs.getTagSet(SplitPart.COMMENT);
            oblist.addAll(tmp);
            oblist.add(0, "< keep >");
            oblist.add(1, "< remove >");
            for(String s : tmp){
                cb_comment.setItems(oblist);
            }
            if(oblist.size() == 3){
                cb_comment.getSelectionModel().select(2);
            }
            else{
                cb_comment.getSelectionModel().select(0);
            }

            //Album Artist
            oblist = FXCollections.observableArrayList();
            tmp = mfs.getTagSet(SplitPart.ALBUM_ARTIST);
            oblist.addAll(tmp);
            oblist.add(0, "< keep >");
            oblist.add(1, "< remove >");
            for(String s : tmp){
                cb_albumartist.setItems(oblist);
            }
            if(oblist.size() == 3){
                cb_albumartist.getSelectionModel().select(2);
            }
            else{
                cb_albumartist.getSelectionModel().select(0);
            }

            //Composer
            oblist = FXCollections.observableArrayList();
            tmp = mfs.getTagSet(SplitPart.COMPOSER);
            oblist.addAll(tmp);
            oblist.add(0, "< keep >");
            oblist.add(1, "< remove >");
            for(String s : tmp){
                cb_composer.setItems(oblist);
            }
            if(oblist.size() == 3){
                cb_composer.getSelectionModel().select(2);
            }
            else{
                cb_composer.getSelectionModel().select(0);
            }

            //Genre
            oblist = FXCollections.observableArrayList();
            tmp = mfs.getTagSet(SplitPart.GENRE);
            oblist.addAll(tmp);
            oblist.add(0, "< keep >");
            oblist.add(1, "< remove >");
            for(String s : tmp){
                cb_genre.setItems(oblist);
            }
            if(oblist.size() == 3){
                cb_genre.getSelectionModel().select(2);
            }
            else{
                cb_genre.getSelectionModel().select(0);
            }
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
