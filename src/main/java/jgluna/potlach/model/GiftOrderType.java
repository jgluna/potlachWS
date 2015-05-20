package jgluna.potlach.model;

public enum GiftOrderType {

    USER("user.email"),
    TOUCHED("touchesCount"),
    TITLE("title"),
    DATE("creationDate");

    private final String column;

    GiftOrderType(String column) {
        this.column = column;
    }

    public String getColumn() {
        return this.column;
    }

    public static GiftOrderType fromString(String input) {
        if (input != null) {
            for (GiftOrderType orderType : GiftOrderType.values()) {
                if (orderType.getColumn().equalsIgnoreCase(input)) {
                    return orderType;
                }
            }
        }
        return null;
    }
}
