package grammar.F_JAVA8.B1_Lambda;

import org.testng.annotations.Test;

/**
 * @Description：
 * @Version：V1.0
 * @JDK-Version:1.8
 * @SoftWare:IntelliJ-IDEA-2022.1
 * @Author:Joe
 * @CreateTime:2022/5/261749
 */
public class demo_01 {
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello Lambda");
            }
        };
        r1.run();

        Runnable r2 = () -> System.out.println("hello lambdada");
        r2.run();
    }
}
