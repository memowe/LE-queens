package chess;

public enum Piece {

  King, Queen, Rook, Knight, Bishop, Pawn;

  public char abbr() {
    switch (this) {
      case King:    return 'K';
      case Queen:   return 'Q';
      case Rook:    return 'R';
      case Knight:  return 'K';
      case Bishop:  return 'B';
      case Pawn:    return 'p';
      default:      return 'X';
    }
  }

  public boolean hits(Position pos, Position target) {
    switch (this) {

      case Queen:
        
        // same file/rank
        if (pos.file == target.file || pos.rank == target.rank)
          return true;

        // same diagonal up
        else if (pos.file - pos.rank == target.file - target.rank) {
          return true;
        }

        // same diagonal down
        else if (pos.file + pos.rank == target.file + target.rank) {
          return true;
        }

        // arbitrary position
        return false;

      // other than queen: not yet implemented
      default:
        return false;
    }
  }
}
