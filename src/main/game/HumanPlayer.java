package main.game;

public class HumanPlayer implements Player {

    private final UI ui;
    private final Marks mark;

    public HumanPlayer(UI ui, Marks mark) {
        this.ui = ui;
        this.mark = mark;
    }

    public void playMove(Board board) {
        int playerMove = getMove(board);
        board.updateMove(playerMove, this.mark);
    }

    public Marks getMark() {
        return mark;
    }

    public Integer getMove(Board board) {
        String userMove = ui.getValidMove(board);
        return Integer.parseInt(String.valueOf(userMove));
    }
}
