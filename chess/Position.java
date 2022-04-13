package chess;

public class Position {

  public int file;
  public int rank;

  public Position(int file, int rank) {
    this.file = file;
    this.rank = rank;
  }

  public char fileChar() {
    String files = "abcdefghijklmnopqrstuvwxyz";
    return files.charAt(this.file - 1);
  }

  public String toString() {
    return "" + this.fileChar() + rank;
  }

  // Usable equals and hashCode methods are neccessary
  // for Map's with Position as keys.
  @Override
  public boolean equals(Object obj) {
    if (! (obj instanceof Position)) return false;
    Position p = (Position) obj;
    return this.file == p.file && this.rank == p.rank;
  }

  @Override
  public int hashCode() {
    return (int) Math.round(Math.pow(2, this.file) + Math.pow(7, this.rank));
  }
}
