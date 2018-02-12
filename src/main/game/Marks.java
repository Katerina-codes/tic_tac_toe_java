package main.game;

public enum Marks {
    X("X"),
    O("O");

    private final String mark;

    Marks(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}
