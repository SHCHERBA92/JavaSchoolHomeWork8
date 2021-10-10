import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        JustClass aClass = new JustClass();
//        int res = aClass.myMethod(5);
//        System.out.println(res);

        ClassLoader classLoader = aClass.getClass().getClassLoader();
        Class[] classes = aClass.getClass().getInterfaces();

        FibOrFact myProxy = (FibOrFact) Proxy.newProxyInstance(classLoader,classes, new FibOrFactInvoker(aClass));

        int result = myProxy.myMethod(8);
        int result2 = myProxy.myMethod(3);
        int result3 = myProxy.myMethod(4);
        int result4 = myProxy.myMethod(5);
        System.out.println("result = " + result);

        System.out.println("&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&");

        System.out.println(aClass.getHashMapCash());

        System.out.println("&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&");
        System.out.println("&&&&&&&&&&&&&&&&&&&");

        int result5 = myProxy.myMethod(5);
        System.out.println(result5);

    }
}