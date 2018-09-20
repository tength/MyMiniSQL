package CatalogManager;

public class Attribute {
    private String name;
    private DataType type;
    private int length;
    private boolean isPrimary = false;

    public Attribute(String name, DataType type, int length, boolean isPrimary){
        this.name = name;
        this.type = type;
        this.length = length;
        this.isPrimary = isPrimary;
    }

    public int getLength() {
        return length;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public DataType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
