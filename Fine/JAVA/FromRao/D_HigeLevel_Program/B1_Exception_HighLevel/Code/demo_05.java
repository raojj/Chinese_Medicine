package grammar.D_HigeLevel_Program.B1_Exception_HighLevel.Code;

public class demo_05 {
    public static void main(String[] args) {
        try {
            String[] str = new String[]{"1","2"};
            System.out.println(str[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println("啊哦，好像出错了,访问到不能访问的数据了");
        } finally {
            System.out.println("我一定会被执行");
        }
    }
}
class myException extends Exception{
    public myException(String ErrorMessage){
        super(ErrorMessage);
    }
}