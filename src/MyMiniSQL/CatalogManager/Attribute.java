package MyMiniSQL.CatalogManager;

public class Attribute {
    private String name;
    private DataType type;
    private int length;
    private boolean isUnique = false;

    private static int FloatSize = Float.SIZE / 8;
    private static int IntSize = Integer.SIZE / 8;
    private static int CharSize = Character.SIZE / 8;

    private Attribute(String name, DataType type, int length, boolean isUnique){
        this.name = name;
        this.type = type;
        this.length = length;
        this.isUnique = isUnique;
    }

    public static Attribute getFloatAttribute(String name, boolean isUnique){
        return new Attribute(name, DataType.Float, FloatSize, isUnique);
    }

    public static Attribute getIntegerAttribute(String name, boolean isUnique){
        return new Attribute(name, DataType.Int, IntSize, isUnique);
    }

    public static Attribute getCharArrayAttribute(String name, boolean isUnique, int number){
        return new Attribute(name, DataType.CharArray, CharSize * number, isUnique);
    }

    public int getLength() {
        return length;
    }

    public boolean isUnique() {
        return isUnique;
    }

    public DataType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
