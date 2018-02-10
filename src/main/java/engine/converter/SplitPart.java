package engine.converter;

public enum SplitPart {

    TITLE("%title%"),
    ARTIST("%artist%"),
    ALBUM("%album%"),
    YEAR("%year%"),
    DISK("%disk%"),  //aktuelle Disk nicht Total
    TRACK("%track%"),  //aktuelle Nummer nicht Total
    COMMENT("%comment%"),
    ALBUM_ARTIST("%albumartist%"),
    COMPOSER("%composer%"),
    GENRE("%genre%"),
    ;

    private String s;

    SplitPart(String s){
        this.s = s;
    }

    @Override
    public String toString(){
        return this.s;
    }

    //--- Functions ---

    public static boolean isPart(String s){
        SplitPart[] parts = SplitPart.values();
        for(SplitPart sp : parts){
            if(sp.toString().equals(s)){
                return true;
            }
        }
        return false;
    }

    public static SplitPart getPart(String s){
        SplitPart[] parts = SplitPart.values();
        for(SplitPart sp : parts){
            if(sp.toString().equals(s)){
                return sp;
            }
        }
        return null;
    }

}
