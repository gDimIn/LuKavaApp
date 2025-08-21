package handler;

public enum EnumSchedule {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10");

    private final String commadKey;

    EnumSchedule(String commadKey){
        this.commadKey = commadKey;
    }

    public String getCommadKey(){
        return commadKey;
    }
}
