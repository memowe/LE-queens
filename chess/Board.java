package chess;

import java.util.*;
import java.util.stream.*;

public class Board {

  public final int size;
  protected Map<Position, Piece> pieces;

  public Board(int size) {
    this.size   = size;
    this.pieces = new HashMap<Position, Piece>();
  }
  public Board() { this(8); }

  public boolean onBoard(Position p) {
    return this.size >= p.file && p.file >= 1
        && this.size >= p.rank && p.rank >= 1;
  }

  public Piece getPiece(Position pos) {
    return this.pieces.get(pos);
  }

  public boolean hasPiece(Position pos) {
    return this.pieces.containsKey(pos);
  }

  public void setPiece(Position pos, Piece piece) {
    this.pieces.put(pos, piece);
  }

  public void removePiece(Position pos) {
    this.pieces.remove(pos);
  }

  public List<Position> findPieces(Piece piece) {
    return this.pieces.entrySet().stream()
      .filter(e -> e.getValue() == piece)
      .map(e -> e.getKey())
      .collect(Collectors.toList());
  }

  public boolean isSafe(Position target) {

    // Somebody's already there
    if (this.hasPiece(target))
      return false;

    // A piece hits this position
    for (Map.Entry<Position, Piece> e : this.pieces.entrySet()) {
      Position pos  = e.getKey();
      Piece piece   = e.getValue();
      if (piece.hits(pos, target))
        return false;
    }

    // Looks safe
    return true;
  }

  public String toString() {
    String output = "";
    
    for (int rank = this.size; rank >= 1; rank--) {
      for (int file = 1; file <= this.size; file++) {
        Position pos = new Position(file, rank);
        output += this.hasPiece(pos) ? this.getPiece(pos).abbr() : '_';
      }
      output += "\n";
    }

    return output;
  }
}
