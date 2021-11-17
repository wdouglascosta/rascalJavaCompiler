package core;

public class LexerToken {
    private String val;
    private Integer column;

    public LexerToken(String val, Integer column) {

        this.val = val;
        this.column = column;
    }

    public String getVal() {

        return val;
    }

    public void setVal(String val) {

        this.val = val;
    }

    public Integer getColumn() {

        return column;
    }

    public void setColumn(Integer column) {

        this.column = column;
    }
}
