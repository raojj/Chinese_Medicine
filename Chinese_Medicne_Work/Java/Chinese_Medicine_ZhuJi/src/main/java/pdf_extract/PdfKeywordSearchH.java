package pdf_extract;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.*;


public class PdfKeywordSearchH {
    public static void main(String[] args) throws IOException {
        //定义path，pdf所在的文件夹
        String path = "E:\\资料\\书籍\\中医药\\PDF\\黄帝内经注释\\17注本";
        String outPath = "E:\\资料\\书籍\\中医药\\PDF\\黄帝内经注释\\output.txt";
        String[] fileL = new String[]{"a.pdf", "b.pdf", "c.pdf", "x.pdf", "e.pdf", "y.pdf", "g.pdf", "h.pdf", "i.pdf", "j.pdf", "k.pdf", "l.pdf", "m.pdf", "n.pdf", "o.pdf", "z.pdf", "q.pdf"};
        //"a.pdf", "b.pdf", "c.pdf", "x.pdf", "e.pdf", "y.pdf", "g.pdf", "h.pdf", "i.pdf", "j.pdf", "k.pdf", "l.pdf", "m.pdf", "n.pdf", "o.pdf", "z.pdf", "q.pdf"
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath,true)));
        // 读取文件夹内所有的PDF文档
        for(String file : fileL){
            PDDocument document = PDDocument.load(new File(path + "\\" + file));

            // 创建一个PDF文本提取器
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // 设置关键词
            String[] keywordList = new String[]{"脑", "髓"};
//                    {"神","魂","魄","意","志","喜","怒","忧","思","悲","恐","惊","愁","智","虑","癫","狂","痫","烦","寐","卧","瞑","忘","癔","眠", "郁", "心","肝","脾","肺","肾","心包", "胃","大肠","小肠",
//                    "三焦","膀胱","胆","脑","髓","骨","脉","女子胞","精","气", "阳气", "阴气", "元气","营气","卫气","宗气","血","津","液","津液","肢","支","筋","骨","肉","皮", "舌","目","口","耳","鼻","太阳",
//                    "阳明","少阴","太阴","厥阴","营","上焦","中焦","下焦","形","少阳","语" ,"言" ,"声" ,"音" ,"闷" ,"任脉" , "督脉" ,"冲脉" , "带脉" , "阴维脉" , "阳维脉" , "阴跷脉" , "阳跷脉" , "四肢","卫", "气", "营","血",
//                    "手太阴肺经", "手太阴心经", "手厥阴心包经", "手阳明大肠经", "手少阳三焦经", "足太阴脾经", "足少阴肾经", "足厥阴肝经", "足太阳膀胱经", "足阳明胃经", "足少阴胆经"};
            // 循环处理每一页
            for(int k =0; k< keywordList.length; k++) {
                System.out.println("正在处理"+file+"的："+keywordList[k]);
                for (int i = 1; i <= document.getNumberOfPages(); i++) {
                    // 将当前页转换为文本
                    pdfStripper.setStartPage(i);
                    pdfStripper.setEndPage(i);
                    String pageText = pdfStripper.getText(document);
                    String[] sentence = pageText.split(String.valueOf('。'));
                    // 搜索关键词
                    for (int j = 0; j < sentence.length; j++) {
                        if (sentence[j].contains(keywordList[k])) {
                            if (file.contains("a")){
                                if (19<i){
                                    out.write("bookENTER1-1 黄帝内经太素author杨上善（隋）"+"Keyword" + keywordList[k] + "found_on_page" + (i-19) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-19) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("b")){
                                if (17<i ){
                                    out.write("bookENTER2-2 重广补注黄帝内经素问author王冰（唐）"+"Keyword" + keywordList[k] + "found_on_page" + (i-17) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-15) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("c")) {
                                if (19<i){
                                    out.write("bookENTER3-3 读素问钞author滑寿（元）"+"Keyword" + keywordList[k] + "found_on_page" + (i-18) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-18) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("x")) {
                                if (4<i){
                                    out.write("bookENTER4-4-1黄帝内经素问注证发微author马莳（明）"+"Keyword" + keywordList[k] + "found_on_page" + (i-4) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-4) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("e")) {
                                if (4<i){
                                    out.write("bookENTER5-4-2 黄帝内经灵枢注证发微author马莳（明）"+"Keyword" + keywordList[k] + "found_on_page" + (i-4) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-4) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("y")) {
                                if (18<i){
                                    out.write("bookENTER6-5 黄帝内经素问吴注author吴昆（明）"+"Keyword" + keywordList[k] + "found_on_page" + (i-18) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-18) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("g")) {
                                if (26<i){
                                    out.write("bookENTER7-6类经 上author张介宾（明）"+"Keyword" + keywordList[k] + "found_on_page" + (i-26) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-23) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("h")) {
                                if (2<i){
                                    out.write("bookENTER8-6 类经 下author张介宾（明）"+"Keyword" + keywordList[k] + "found_on_page" + (i+498) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-21) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("i")) {
                                if (21<i){
                                    out.write("bookENTER9-7 内经知要author李中梓(明)"+"Keyword" + keywordList[k] + "found_on_page" + (i-21) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-24) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("j")) {
                                if (24<i){
                                    out.write("bookENTER10-8 素问灵枢类纂约注author汪昂 （明）"+"Keyword" + keywordList[k] + "found_on_page" + (i-24) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-14) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("k")) {
                                if (15<i){
                                    out.write("bookENTER11-9-1 黄帝内经集注author张志聪（清）"+"Keyword" + keywordList[k] + "found_on_page" + (i-15) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-14) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("l")) {
                                if (0<i){
                                    out.write("bookENTER12-9-2 黄帝内经灵枢集注author张志聪（清）"+"Keyword" + keywordList[k] + "found_on_page" + (i-0) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-4) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("m")) {
                                if (4<i){
                                    out.write("bookENTER13-10 黄帝内经素问直解author高士宗（清）"+"Keyword" + keywordList[k] + "found_on_page" + (i-4) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-2) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("n")) {
                                if (2<i){
                                    out.write("bookENTER14-11-1 素问经注节解author姚止庵（清）"+"Keyword" + keywordList[k] + "found_on_page" + (i-2) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-13) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("o")) {
                                if (31<i){
                                    out.write("bookENTER15-12 素问悬解 灵枢悬解author黄元御（清）" +"Keyword" + keywordList[k] + "found_on_page" + (i-27) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-27) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("z")) {
                                if (31<i){
                                    out.write("bookENTER16-13 素问绍识 灵枢识 难经疏证author丹波元简(日)"+"Keyword" + keywordList[k] + "found_on_page" + (i-27) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i+3) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("q")){
                                if (22<i){
                                    out.write("bookENTER17-14 灵素节要浅注author陈修园（清）"+"Keyword" + keywordList[k] + "found_on_page" + (i-22) + "in_sentence" + sentence[j]);
//                                    System.out.println("bookENTER" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i+3) + "in_sentence" + sentence[j]);
                                }
                            }
                        }
                    }
                }
            }

            // 关闭文档
            document.close();
            System.out.println("运行结束");
        }
        out.close();
    }
}
