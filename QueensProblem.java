import java.util.stream.*;
import chess.*;

public class QueensProblem {

  protected Board board;
  protected boolean solved;

  public QueensProblem(int size) {
    this.board  = new Board(size);
    this.solved = false;
  }
  public QueensProblem() { this(8); }

  public Board getSolution() {
    return this.solved ? this.board : null;
  }

  public boolean solve() {
    return this.solved = this.solveFile(1);
  }

  // Recursive backtracking method
  private boolean solveFile(int file) {
    
    // Done
    if (file > board.size)
      return true;

    // Try all fields in this file
    for (int rank = 1; rank <= this.board.size; rank++) {

      // Try this position
      Position pos = new Position(file, rank);

      // It's a sage place for a piece
      if (board.isSafe(pos)) {
        board.setPiece(pos, Piece.Queen);
        
        // Try to find a solution including this queen
        if (solveFile(file + 1))
          return true;

        // That didn't work. Ctrl-Z
        else
          board.removePiece(pos);
      }
    }

    // No valid rank found
    return false;
  }

  public String reportSolution() {
    if (! this.solved)
      return "Not solved!";
    
    Stream<Position> queenPoss  = board.findPieces(Piece.Queen).stream();
    Stream<String> posNames     = queenPoss.map(p -> p.toString());

    return "Solution for " + board.size + " queens: "
      + posNames.collect(Collectors.joining(", ")) + "\n" + this.board;
  }

  public static void main(String[] args) {
    QueensProblem problem = new QueensProblem(17);
    problem.solve();
    System.out.println(problem.reportSolution());
  }
}
