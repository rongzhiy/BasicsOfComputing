import java.io.Serializable;

public class Combo implements Serializable {
    private int id;
    private People people;

    public Combo(int id, People people) {
        this.id = id;
        this.people = people;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Combo{" +
                "id=" + id +
                ", people=" + people +
                '}';
    }
}
