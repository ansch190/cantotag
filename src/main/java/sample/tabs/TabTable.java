package sample.tabs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabTable extends Tab {

    //### TABLE ###
    private List<TableColumn> tableColumns = null; //Spalten

    private TableView tv = null;  //Tabelle

    private ScrollPane scrollPane = null;

    //### DATA ###

    private File directory = null;  //Anfangsverzeichnis

    private List<File> files = null;

    //private List<MusicFile> musicFiles = null;

    public TabTable(File directory){
        this.directory = directory;
        this.setText(this.directory.getName());

        init();
    }

    public TabTable(){
        init();
    }

    //init Tab
    private void init(){
        initFiles();

        //initScrollPane();
        initTableView();
    }

    //Dateien rekursiv einlesen
    private void initFiles(){
        List<File> files = new ArrayList<>();

        File[] tmp = directory.listFiles();

        files.addAll(Arrays.asList(tmp));

        File f = null;
        for(int i=0; i<files.size(); i++){
            f = files.get(i);
            //System.out.println("Dir: " + f.getName());
            if(f.isDirectory()){
                files.remove(i);
                tmp = f.listFiles();
                files.addAll(Arrays.asList(tmp));
                i=-1;
            }
        }

        //Filter Files
        this.files = new ArrayList<>();
        List<String> endings = Arrays.asList(".mp1",".mp2",".mp3",".ogg",".wma",".wav",".flac",".aac",".opus",".mka");

        for(int i=0; i<files.size(); i++){
            f = files.get(i);
            for(String end : endings){
                if(f.getName().endsWith(end)){
                    this.files.add(f);
                    break;
                }
            }
        }
    }

    //init ScrollPane
    private void initScrollPane(){
        scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(tv);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }

    private void initTableViewColumns(){
        //Spalten
        tableColumns = new ArrayList<>();

        //Filename
        TableColumn tc = new TableColumn("Filename");
        tc.setCellValueFactory(new PropertyValueFactory<MusicFile,String>("filename"));
        tableColumns.add(tc);

        //Title
        tc = new TableColumn("Title");
        tc.setCellValueFactory(new PropertyValueFactory<MusicFile,String>("title"));
        tableColumns.add(tc);

        //Artist
        tc = new TableColumn("Artist");
        tc.setCellValueFactory(new PropertyValueFactory<MusicFile,String>("artist"));
        tableColumns.add(tc);

        //Album
        tc = new TableColumn("Album");
        tc.setCellValueFactory(new PropertyValueFactory<MusicFile,String>("album"));
        tableColumns.add(tc);

        //Year
        tc = new TableColumn("Year");
        tc.setCellValueFactory(new PropertyValueFactory<MusicFile,String>("year"));
        tableColumns.add(tc);

        //Disk
        tc = new TableColumn("Disk");
        tc.setCellValueFactory(new PropertyValueFactory<MusicFile,String>("disk"));
        tableColumns.add(tc);

        //Track
        tc = new TableColumn("Track");
        tc.setCellValueFactory(new PropertyValueFactory<MusicFile,String>("track"));
        tableColumns.add(tc);

        //Comment
        tc = new TableColumn("Comment");
        tc.setCellValueFactory(new PropertyValueFactory<MusicFile,String>("comment"));
        tableColumns.add(tc);

        //Albumartist
        tc = new TableColumn("Albumartist");
        tc.setCellValueFactory(new PropertyValueFactory<MusicFile,String>("albumartist"));
        tableColumns.add(tc);

        //Composer
        tc = new TableColumn("Composer");
        tc.setCellValueFactory(new PropertyValueFactory<MusicFile,String>("composer"));
        tableColumns.add(tc);

        //Genre
        tc = new TableColumn("Genre");
        tc.setCellValueFactory(new PropertyValueFactory<MusicFile,String>("genre"));
        tableColumns.add(tc);

        tv.getColumns().addAll(tableColumns);
    }

    //init TableView
    private void initTableView(){
        tv = new TableView();
        tv.setEditable(true);
        tv.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        initTableViewColumns();

        HBox hbox = new HBox();
        hbox.getChildren().add(tv);
        hbox.setHgrow(tv, Priority.ALWAYS);
        this.setContent(hbox);

//        initScrollPane();
//
//        HBox hbox = new HBox();
//        hbox.getChildren().add(scrollPane);
//        hbox.setHgrow(scrollPane, Priority.ALWAYS);
//        this.setContent(hbox);
    }

    private ObservableList<MusicFile> musicFiles = null;

//    public void addData(){
//        musicFiles = FXCollections.observableArrayList();
//
//        MusicFile mf0 = new MusicFile();
//        System.out.println(mf0);
//        musicFiles.add(mf0);
//
//        tv.setItems(musicFiles);
//    }

    public void addData2(){
        musicFiles = FXCollections.observableArrayList();

        for(File f : files){
            musicFiles.add(new MusicFile(f));
        }

        tv.setItems(musicFiles);
    }

}
