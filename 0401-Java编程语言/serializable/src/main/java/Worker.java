import java.io.Serializable;

public class Worker extends People implements Serializable {
    private String name;
    private int age;

    public Worker(Long id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
    }
}
