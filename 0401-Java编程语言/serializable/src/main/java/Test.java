import java.io.*;
import java.util.Iterator;
import java.util.concurrent.Callable;

public class Test {
    public static void main(String[] args) throws Exception {
        testSerizableWorker();
        testSerializableCombo();
//         结果发现反序列化读出的值都是一样的。
//         明当对象第一次序列化成功后，后续这个对象属性即使有修改，也不会对后面的序列化造成成影响。
        sameObjectRepeatedSerialization();


    }

    /**
     * <h2>子类实现序列化, 父类不实现序列化</h2>
     * */
    private static void testSerizableWorker() throws Exception {

        File file = new File("G:\\Java开发\\Java\\serializable\\src\\test/tmp/worker_10.java_");
        if (!file.exists()) {
            // 1，先得到文件的上级目录，并创建上级目录
            file.getParentFile().mkdirs();
            try {
                // 2，再创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Worker p = new Worker(10L, "lcy", 18);

        // 创建一个输出流
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file)
        );
        // 输出可序列化对象
        oos.writeObject(p);
        // 关闭输出流
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object newWorker = ois.readObject(); // 父类没有序列化的时候，需要调用父类的无参数构造方法
        ois.close();
        System.out.println(newWorker);
    }


    private static void testSerializableCombo() throws Exception {

        File file = new File("G:\\Java开发\\Java\\serializable\\src\\test/tmp/combo_10.java_");
        if (!file.exists()) {
            // 1，先得到文件的上级目录，并创建上级目录
            file.getParentFile().mkdirs();
            try {
                // 2，再创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Combo p = new Combo(1, new People(10L));

        // 创建一个输出流
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file)
        );
        // 输出可序列化对象
        oos.writeObject(p);
        // 关闭输出流
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object newCombo = ois.readObject();
        ois.close();
        System.out.println(newCombo);
    }

    /**
     * <h2>同一个对象多次序列化的问题, 坑</h2>
     * */
    private static void sameObjectRepeatedSerialization() throws Exception {

        File file = new File("G:\\Java开发\\Java\\serializable\\src\\testtmp/peopele_more.java_");
        if (!file.exists()) {
            // 1，先得到文件的上级目录，并创建上级目录
            file.getParentFile().mkdirs();
            try {
                // 2，再创建文件
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        People p = new People(10L);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        // 未序列化，先修改属性
        p.setId(11L);
        oos.writeObject(p);
        // 序列化一次后，再次修改属性
        p.setId(15L);
        oos.writeObject(p);
        // 序列化两次后，再次修改属性
        p.setId(20L);
        oos.writeObject(p);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object people1 = ois.readObject();
        Object people2 = ois.readObject();
        Object people3 = ois.readObject();
        ois.close();

        System.out.println(((People) people1).getId());
        System.out.println(((People) people2).getId());
        System.out.println(((People) people3).getId());
    }



}
