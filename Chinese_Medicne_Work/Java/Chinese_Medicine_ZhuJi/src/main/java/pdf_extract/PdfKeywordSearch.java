package pdf_extract;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.ws.rs.NotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PdfKeywordSearch {
    public static void main(String[] args) throws IOException {
        //定义path，pdf所在的文件夹
        String path = "E:\\资料\\书籍\\中医药\\PDF\\202304\\PDF";
        String[] fileL = new String[]{"a.pdf", "b.pdf","c.pdf", "x.pdf", "e.pdf", "y.pdf", "g.pdf", "h.pdf", "i.pdf", "j.pdf", "k.pdf", "l.pdf", "m.pdf", "n.pdf"};
        //输出文件的位置
        String outPath = "E:\\资料\\书籍\\中医药\\PDF\\202304\\output.txt";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath,true)));
        // 读取文件夹内所有的PDF文档
        for(String file : fileL){
            PDDocument document = PDDocument.load(new File(path + "\\" + file));

            // 创建一个PDF文本提取器
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // 设置关键词
            String[] keywordList = new String[]{"脑", "髓"};
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
                                if (35<i && i<223) {
                                    out.write("bookENTER1-伤寒论author张仲景(汉)"+"Keyword" + keywordList[k] + "found_on_page" + (i-35) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-35) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("b")){
                                if (35<i && i<139){
                                    out.write("bookENTER2-金匮要略author张仲景(汉)"+"Keyword" + keywordList[k] + "found_on_page" + (i-35) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-35) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("c")) {
                                if (30<i && i<75){
                                    out.write("bookENTER3-素问玄机原病式author刘完素(金)"+"Keyword" + keywordList[k] + "found_on_page" + (i-30) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-30) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("x")) {
                                if (22<i){
                                    out.write("bookENTER4-儒门事亲author张子和(金)"+"Keyword" + keywordList[k] + "found_on_page" + (i-22) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-9) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("e")) {
                                if (27<i && i<125){
                                    out.write("bookENTER5-脾胃论author李东垣(金)"+"Keyword" + keywordList[k] + "found_on_page" + (i-27) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-27) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("y")) {
                                if (23<i && i<373){
                                    out.write("bookENTER6-丹溪心法author朱震亨(元)"+"Keyword" + keywordList[k] + "found_on_page" + (i-23) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-23) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("g")) {
                                if (19<i && i<74){
                                    out.write("bookENTER7-格致余论author朱震亨(元)"+"Keyword" + keywordList[k] + "found_on_page" + (i-19) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-19) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("h")) {
                                if (16<i){
                                    out.write("bookENTER8-温病学进阶三书：温病合编·温热经纬·温热论笺正author石寿棠、王士雄(清)"+"Keyword" + keywordList[k] + "found_on_page" + (i-16) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-16) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("i")) {
                                if (33<i){
                                    out.write("bookENTER9-温疫论author吴有性(明)"+"Keyword" + keywordList[k] + "found_on_page" + (i-33) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-33) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("j")) {
                                if (38<i){
                                    out.write("bookENTER10-温病条辨author吴塘(清)"+"Keyword" + keywordList[k] + "found_on_page" + (i-24) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-24) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("k")) {
                                out.write("bookENTER11-温热论·湿热论author薛雪(清)、叶桂(清)"+"Keyword" + keywordList[k] + "found_on_page" + (i-9) + "in_sentence" + sentence[j]);
//                                System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-9) + "in_sentence" + sentence[j]);
                            }
                            else if (file.contains("l")) {
                                if (41<i && i<872){
                                    out.write("bookENTER12-景岳全书 上author张介宾(明)"+"Keyword" + keywordList[k] + "found_on_page" + (i-39) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-39) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("m")) {
                                if (17<i){
                                    out.write("bookENTER13-景岳全书 下author张介宾(明)"+"Keyword" + keywordList[k] + "found_on_page" + (i+818) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i+818) + "in_sentence" + sentence[j]);
                                }
                            }
                            else if (file.contains("n")) {
                                if (18<i){
                                    out.write("bookENTER14-医林改错author王清任(清)"+"Keyword" + keywordList[k] + "found_on_page" + (i-18) + "in_sentence" + sentence[j]);
//                                    System.out.println("book" + file.getName()+"Keyword" + keywordList[k] + "found_on_page" + (i-18) + "in_sentence" + sentence[j]);
                                }
                            }
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
