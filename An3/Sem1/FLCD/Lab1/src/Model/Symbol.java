package Model;

/**
 * Created by shobo on 09.11.2014.
 */
public class Symbol implements Comparable<Symbol> {

    private Integer code;
    private String identifier;

    public Symbol(Integer code, String identifier) {
        this.code = code;
        this.identifier = identifier;
    }

    public Integer getCode() {
        return code;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public int compareTo(Symbol symbol) {
        return this.getIdentifier().compareTo(symbol.getIdentifier());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Symbol) {
            return this.identifier.equals(((Symbol) o).identifier);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "code=" + code +
                ", identifier='" + identifier + '\'' +
                '}';
    }

}
