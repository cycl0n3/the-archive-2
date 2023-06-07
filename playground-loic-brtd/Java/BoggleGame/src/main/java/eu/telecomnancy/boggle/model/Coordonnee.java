package eu.telecomnancy.boggle.model;

public class Coordonnee {

  private int lig;
  private int col;

  public Coordonnee(int lig, int col) {
    this.lig = lig;
    this.col = col;
  }

  public int getLig() {
    return lig;
  }

  public int getCol() {
    return col;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + col;
    result = prime * result + lig;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Coordonnee other = (Coordonnee) obj;
    if (col != other.col)
      return false;
    if (lig != other.lig)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Coordonnee [lig=" + lig + ", col=" + col + "]";
  }

}
