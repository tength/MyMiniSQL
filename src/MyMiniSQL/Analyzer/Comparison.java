package MyMiniSQL.Analyzer;

public enum Comparison {
    bt, //bigger than
    lt, //less than
    eq, //equal
    be, //bigger than or equal
    le, //less than or equal
    ne  //not equal
    ;

    @Override
    public String toString() {
        return getSymbolStr(this);
    }

    private static String getSymbolStr(Comparison comparison){
        switch (comparison){
            case ne:
                return "!=";
            case lt:
                return "<";
            case le:
                return "<=";
            case eq:
                return "=";
            case bt:
                return ">";
            case be:
                return ">=";
            default:
                return "";
        }
    }

    public static Comparison reverse(Comparison toReverse){
        switch (toReverse){
            case be:
                return le;
            case le:
                return be;
            case bt:
                return lt;
            case lt:
                return bt;
            case eq:
                return eq;
            case ne:
                return ne;
            default:
                return toReverse;
        }
    }
}
