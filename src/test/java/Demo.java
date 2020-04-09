public class Demo {
    public static void main(String[] args) {
        //此处语法是Java的匿名子类。匿名子类的格式是就是
        // 方法名（new   构造器）{  调用方法/重写方法 }
        //如果子类构造器中对父类方法进行重写，
        // 那么就调用重写的方法，这就是匿名子类的语法特性与目的。
        // 我们既可以在匿名子类中进行调用父类的方法，
        // 也可以在匿名子类中重写父类的方法以及直接调用父类方法。
        Test t = new Test() {
            @Override
            public void method1() {
                System.out.println("2222222222222");
            }
        };

        Test t2 = new Test() {
            public void method1() {
                super.method3();
                System.out.println("222222222222222");
            }
        };
        t2.method1();
	    }

	}

	class Test {
    public void method1() {
        System.out.println("11111111111111");
    }
    public void method3(){
        System.out.println("333333333333");
 	   }

	}