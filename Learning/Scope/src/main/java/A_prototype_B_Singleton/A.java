package A_prototype_B_Singleton;



public class A {
public B b;
	
	public A(B b) {
		this.b=b;
	}

	public void m1() {
		System.out.println("A class m1 method....");
		System.out.println("B class "+b);
		b.m2();
	}
}
