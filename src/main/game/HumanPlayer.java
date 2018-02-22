package main.game;

public class HumanPlayer implements Player {

    private final UI ui;
    private final Mark mark;

    public HumanPlayer(UI ui, Mark mark) {
        this.ui = ui;
        this.mark = mark;
    }

    public void playMove(Board board) {
        int playerMove = getMove(board);
        board.updateMove(playerMove, this.mark);
    }

    public Mark getMark() {
        return mark;
    }

    public Integer getMove(Board board) {
        String userMove = ui.getValidMove(board);
        return Integer.parseInt(String.valueOf(userMove)) - 1;
    }
}
