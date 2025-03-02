package pdf_extract;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.ws.rs.NotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PdfKeywordSearchForEssay {
    public static void main(String[] args) throws IOException {
        //定义path，pdf所在的文件夹
        String path = "F:\\资料\\书籍\\中医药\\PDF\\文献\\202305\\形神一体 word\\PDF-单栏\\";
        List<String> fileL = getAllFile(path);
        Collections.sort(fileL);
        String outPath = "F:\\资料\\书籍\\中医药\\PDF\\文献\\202305\\形神一体 word\\output.txt";
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath,true)));
        // 读取文件夹内所有的PDF文档
        for(String file : fileL){
            PDDocument doc = PDDocument.load(new File(path + "\\" + file));

            // 创建一个PDF文本提取器
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // 设置关键词
            String[] keywordList = new String[]{"神","魂","魄","意","志","喜","怒","忧","思","悲","恐","惊","愁","智","虑","癫","狂","痫","烦","寐","卧","瞑","忘","癔","眠", "郁"};

            // 循环处理每一页
            for(int k =0; k< keywordList.length; k++) {
                System.out.println("正在处理"+file+"的："+keywordList[k]);
                for (int i = 1; i <= doc.getNumberOfPages(); i++) {
                    // 将当前页转换为文本
                    pdfStripper.setStartPage(i);
                    pdfStripper.setEndPage(i);
                    String pageText = pdfStripper.getText(doc);
                    String[] sentence = pageText.split("[.\u3002]");
                    // 搜索关键词
                    for (int j = 0; j < sentence.length; j++) {
                        if (sentence[j].contains(keywordList[k])) {
                            out.write("essayENTER" + file +"Keyword" + keywordList[k] + "in_sentence" + sentence[j]);
                        }
                    }
                }
            }

            // 关闭文档
            doc.close();
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
