package A_Singleton_B_Prototype;

public class A {
    private final B b;

    public A(B b) {
        this.b = b;
    }

    public void m1() {
        System.out.println("A class m1 method");
        System.out.println("B Class "+b);
        b.m2();
    }
}
