package private_classes;

/**
 * Created by remen on 20.11.15.
 */
public class Tupel<T1, T2> {
    // ##########################################
    // vars
    // ##########################################
    private T1 t1;
    private T2 t2;
    // ##########################################
    // methods
    // ##########################################
    private Tupel() {}

    public static <T1, T2> Tupel<T1, T2> of(T1 t1, T2 t2) {
        return new Tupel<>();
    }

    public T1 getT1() { return t1; }
    public T2 getT2() { return t2; }

    public void setT1(T1 t1) { this.t1 = t1; }
    public void setT2(T2 t2) { this.t2 = t2; }
    // ##########################################
    // invisible
    // ##########################################

    // ##########################################
    // alias
    // ##########################################
}
