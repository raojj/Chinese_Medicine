package grammar.C_FaceTo_Object.B_Code;

public class C29_Math_MathCompute {
    public static void main(String[] args) {
        //Math类的常量
        System.out.println(Math.PI);
        System.out.println(Math.E);
        //Math类三角函数的方法
        System.out.println(Math.sin(Math.PI/6));
        System.out.println(Math.cos(Math.PI/6));
        System.out.println(Math.tan(Math.PI/6));
        System.out.println(Math.asin(Math.PI/6));
        System.out.println(Math.acos(Math.PI/6));
        System.out.println(Math.atan(Math.PI/6));
        //Math类指数相关的函数
        System.out.println(Math.exp(2)); //获取e的a次方
        System.out.println(Math.log(Math.E)); //获取自然对数，即lna
        System.out.println(Math.log10(10)); //获取log以10为底a的对数
        System.out.println(Math.sqrt(4)); //获取a的平方根
        System.out.println(Math.cbrt(8)); //获取a的立方根
        System.out.println(Math.pow(2,2)); //获取a的b次方
    }
}
