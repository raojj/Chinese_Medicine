package pdf_extract;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.ws.rs.NotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PdfKeywordSearchJC {
    public static void main(String[] args) throws IOException {
        //定义path，pdf所在的文件夹
        String path = "F:\\资料\\书籍\\中医药\\PDF\\教材\\13-教材\\2-OCR版\\";
        String[] fileL = new String[]{"a.pdf", "b.pdf", "c.pdf", "x.pdf", "e.pdf", "y.pdf", "g.pdf", "h.pdf", "i.pdf", "j.pdf", "k.pdf", "l.pdf", "m.pdf"};
//        "a.pdf", "b.pdf", "c.pdf", "x.pdf", "e.pdf", "y.pdf", "g.pdf", "h.pdf", "i.pdf", "j.pdf", "k.pdf", "l.pdf", "m.pdf"
        String outPath = "F:\\资料\\书籍\\中医药\\PDF\\教材\\output.txt";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath,true)));
        // 读取文件夹内所有的PDF文档
        for(String file : fileL){
            PDDocument document = PDDocument.load(new File(path + "\\" + file));

            // 创建一个PDF文本提取器
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // 设置关键词
            String[] keywordList = new String[]{"脾欲缓，急食甘以缓之"};

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
                                if (50<i) {
                                    out.write("bookENTER1-中医基础理论author郑洪新"+"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-35) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("b")){
                                if (30<i){
                                    out.write("bookENTER2-中医诊断学author李灿东"+"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-35) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("c")) {
                                if (61<i){
                                    out.write("bookENTER3-中药学author钟赣生"+"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-30) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("x")) {
                                if (35<i){
                                    out.write("bookENTER4-方剂学author李冀 连建伟"+"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-9) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("e")) {
                                if (7<i){
                                    out.write("bookENTER5-内经选读author翟双庆"+"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-27) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("y")) {
                                if (17<i){
                                    out.write("bookENTER6-伤寒论选读author王庆国"+"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-23) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("g")) {
                                if (14<i){
                                    out.write("bookENTER7-金匮要略author范永升"+"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-19) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("h")) {
                                if (40<i){
                                    out.write("bookENTER8-温病学author马健"+"Keyword" + (i-16) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-16) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("i")) {
                                if (16<i){
                                    out.write("bookENTER9-中医内科学author张伯礼 吴勉华"+"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-33) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("j")) {
                                if (88<i){
                                    out.write("bookENTER10-中医妇科学author谈勇"+"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-24) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("k")) {
                                if(70<i) {
                                    out.write("bookENTER11-中医儿科学author马融" + "Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
//                                System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-9) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("l")) {
                                if (26<i){
                                    out.write("bookENTER12-中医外科学author陈红风"+"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-39) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("m")) {
                                if (50<i){
                                    out.write("bookENTER13-针灸学author梁繁荣 王华"+"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i+818) + "in_sentence" + sentence[j]);
                                }
                            }
//                            else if (file.contains("n")) {
//                                if (18<i){
//                                    out.write("bookENTER14-医林改错author王清任(清)"+"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
////                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-18) + "in_sentence" + sentence[j]);
//                                }
//                            }
                        }
                    }
                }
            }

            // 关闭文档
            document.close();
            System.out.println("执行结束");
        }
        out.close();
    }
    public static List<String> getAllFile(String path) throws NotFoundException {
        File dir = new File(path);
        List<String> allFileList = new ArrayList<>();
        if (!dir.exists()){
            System.out.println("目录不存在");
        }
        else{
            File[] fileList = dir.listFiles();
            assert fileList != null;
            for (File file : fileList){
                allFileList.add(file.getName());
            }
        }
        return allFileList;
    }
}
