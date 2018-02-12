package sample;

import engine.converter.SplitPart;
import sample.tabs.MusicFile;

import java.util.*;

//Eine Anzahl von mehreren Files mit der Tags Analyse
public class MusicFiles {

    private List<MusicFile> musicFiles = null;

    private HashMap<SplitPart, Set<String>> map = null;

    public MusicFiles(List<MusicFile> musicFiles){
        this.musicFiles = musicFiles;
        init();
    }

    private void init(){
        //musicFiles = new ArrayList<>();
        map = new HashMap();
    }

    public void add(MusicFile mf){
        musicFiles.add(mf);
    }

    public void analyze(){
        SplitPart[] parts = SplitPart.values();

        for(SplitPart p : parts){
            Set<String> set = new HashSet<>();
            for(MusicFile mf : musicFiles){
                if(p == SplitPart.TITLE){
                    set.add(mf.getTitle());
                }
                else if(p == SplitPart.ARTIST){
                    set.add(mf.getArtist());
                }
                else if(p == SplitPart.ALBUM){
                    set.add(mf.getAlbum());
                }
                else if(p == SplitPart.YEAR){
                    set.add(mf.getYear());
                }
                else if(p == SplitPart.DISK){
                    set.add(mf.getDisk());
                }
                else if(p == SplitPart.TRACK){
                    set.add(mf.getTrack());
                }
                else if(p == SplitPart.COMMENT){
                    set.add(mf.getComment());
                }
                else if(p == SplitPart.ALBUM_ARTIST){
                    set.add(mf.getAlbumartist());
                }
                else if(p == SplitPart.COMPOSER){
                    set.add(mf.getComposer());
                }
                else if(p == SplitPart.GENRE){
                    set.add(mf.getGenre());
                }
                else{
                    //...
                }
            }
            map.put(p, set);
        }
    }

    public Set<String> getTagSet(SplitPart sp){
        return map.get(sp);
    }

    public List<MusicFile> getMusicFiles(){
        return musicFiles;
    }

}
