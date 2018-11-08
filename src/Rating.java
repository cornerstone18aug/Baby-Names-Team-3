package src;

public class Rating {

    private int rating;
    private String nameMan;
    private String nameWoman;

    public Rating(int rating, String nameMan, String nameWoman) {
        this.rating = rating;
        this.nameMan = nameMan;
        this.nameWoman = nameWoman;
    }

  public Rating(int rating, String name) {
        this.rating = rating;
    this.nameWoman = name;
    }


    public int getRating() {
        return rating;
    }

    public String getNameMan() {
        return nameMan;
    }

    public String getNameWoman() {
        return nameWoman;
    }

    @Override
    public String toString() {
        return "Rating{" +
            "rating=" + rating +
            ", nameMan='" + nameMan + '\'' +
            ", nameWoman='" + nameWoman + '\'' +
            '}';
    }
}