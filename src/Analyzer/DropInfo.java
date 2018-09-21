package Analyzer;

public class DropInfo {
    public DropType getDropType() {
        return dropType;
    }

    public String getName() {
        return name;
    }

    public enum DropType{
        DropTable, DropIndex;
    }

    private DropType dropType;
    private String name;

    DropInfo(DropType dropType, String name){
        this.dropType = dropType;
        this.name = name;
    }
}
