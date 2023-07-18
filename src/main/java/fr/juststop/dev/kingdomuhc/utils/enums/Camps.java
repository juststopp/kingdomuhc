package fr.juststop.dev.kingdomuhc.utils.enums;

public enum Camps {

    QIN("Qin", Colors.GREEN),
    ZHAO("Zhao", Colors.RED),
    CHU("Chu", Colors.YELLOW),
    HAN("Han", Colors.ORANGE),
    WEI("Wei", Colors.PURPLE),
    QI("Qi", Colors.PINK),
    AI("Ai", Colors.LIGHT_BLUE);

    private final String name;
    private final Colors color;

    Camps(String name, Colors color) {
        this.name = name;
        this.color = color;
    }

    public String getName() { return getColor().getChatColor() + name; }
    public Colors getColor() { return color; }
}
