package grammar.C_FaceTo_Object.B_Code;

import java.text.DecimalFormat;

public class C28_NumFormat_Num {
    public static void main(String[] args) {
        SingleFormat("###,###.###", 123456.789);
        SingleFormat("00000000.###kg", 123456.789);
        SingleFormat("000000.000", 123.78);
        UseApplyPatternMethodFormat("#.###%", 0.789);
        UseApplyPatternMethodFormat(".##", 123456.789);
        UseApplyPatternMethodFormat("0.00\u2030", 0.789);


    }

    //使用实例化对象设置格式化模式
    static public void SingleFormat(String pattern, double value){
        DecimalFormat myFormat = new DecimalFormat(pattern);
        String output = myFormat.format(value);
        System.out.println(value+" "+pattern+" "+output);
    }

    //使用applyPattern()方法对数字进行格式化
    static public void UseApplyPatternMethodFormat(String pattern, double value){
        DecimalFormat myFormat = new DecimalFormat();
        myFormat.applyPattern(pattern);
        System.out.println(value +" "+ pattern +" "+ myFormat.format(value));
    }
}
