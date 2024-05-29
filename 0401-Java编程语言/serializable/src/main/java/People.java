import java.io.Serializable;

//public class People  {  // 不序列化的话，引用的对象也不会被序列化
public class People implements Serializable {
    private Long id;

    public People() {
    }

    public People(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                '}';
    }
}
