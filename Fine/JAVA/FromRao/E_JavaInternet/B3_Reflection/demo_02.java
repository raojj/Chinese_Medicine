package grammar.E_JavaInternet.B3_Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description：代理
 * @Version：V1.0
 * @JDK-Version:1.8
 * @SoftWare:IntelliJ-IDEA-2022.1
 * @Author:Joe
 * @CreateTime:2022/5/261639
 */

//静态代理
interface ClothFactory{
    void produceCloth();
}

//代理类
class ProxyClothFactory implements ClothFactory{
    private ClothFactory factory;

    public ProxyClothFactory(ClothFactory factory){
        this.factory =factory;
    }
    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");
        factory.produceCloth();
        System.out.println("代理工厂做一些收尾工作");
    }
}

//被代理类
class NikeClothFactory implements ClothFactory{
    @Override
    public void produceCloth() {
        System.out.println("Nike工厂生产了一批运动服");
    }
}

//public class demo_02 {
//    public static void main(String[] args) {
//        //创建被代理类对象
//        NikeClothFactory nike = new NikeClothFactory();
//        //创建代理类对象
//        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nike);
//
//        proxyClothFactory.produceCloth();
//    }
//}

/*
要想实现动态代理，需要解决的问题？
问题1：如何根据被代理类，动态的造一个代理类
问题2：当通过代理类的对象调用方法时，如何动态的去调用被代理中的同名方法
 */
interface Human{
    String getBelief();
    void eat(String food);
}

//被代理类
class SuperMan implements Human{

    @Override
    public String getBelief() {
        return "I believe I can fly";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃"+food);
    }
}

class MyInvocationHandler implements InvocationHandler{

    private Object obj;//需要使用被代理类的对象进行赋值
    public void bind(Object obj){
        this.obj =obj;
    }
    //当我们通过代理类的对象调用方法a式，就会自动的调用如下的方法：invoke()
    // 将被代理类要执行的方法a的功能就声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method：即为代理类对象调用的方法，，此方法也就作为了被代理类对象要调用的方法
        //obj：被代理类的对象
        Object value = method.invoke(obj, args);
        //上述方法的返回值就作为当前类中的invoke()的返回值
        return value;
    }
}

class ProxyFactory{
    public  static Object getProxyInstance(Object obj){
        MyInvocationHandler handler =new MyInvocationHandler();
        handler.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),handler);
    }
}

public class demo_02 {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        //代理类的对象
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        System.out.println(proxyInstance.getBelief());
        proxyInstance.eat("香蕉");
        System.out.println("***************************");
        NikeClothFactory nike = new NikeClothFactory();
        ClothFactory proxyInstanceNike = (ClothFactory) ProxyFactory.getProxyInstance(nike);
        proxyInstanceNike.produceCloth();
    }
}