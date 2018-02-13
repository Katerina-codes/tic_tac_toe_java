package main.game;

public class HumanPlayer implements Player {

    private final UI ui;
    private final String mark;

    public HumanPlayer(UI ui, String mark) {
        this.ui = ui;
        this.mark = mark;
    }

    public void playMove(Board board) {
        String playerMove = getMove(board);
        board.updateMove(playerMove, this.mark);
    }

    public String getMark() {
        return mark;
    }

    public String getMove(Board board) {
        return ui.getValidMove(board);
    }
}
