package pdf_extract;

/**
 * @Description：
 * @Version：V1.0
 * @JDK-Version:1.8
 * @SoftWare:IntelliJ-IDEA-2022.1
 * @Author:Joe
 * @CreateTime:2023/4/50013
 */

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class PdfSplitter {
    public static void main(String[] args) throws IOException {
        // 定义要分割的PDF文件路径
        File inputFile = new File("input.pdf");

        // 加载PDF文件
        PDDocument inputDocument = PDDocument.load(inputFile);

        // 创建一个Splitter对象来分割PDF文件
        Splitter splitter = new Splitter();

        // 按页数将输入文件分为两个文件
        splitter.setSplitAtPage(2);

        // 获取分割后的所有文档列表
        java.util.List<PDDocument> outputDocuments = splitter.split(inputDocument);

        // 保存第一部分PDF文件
        outputDocuments.get(0).save("part1.pdf");

        // 保存第二部分PDF文件
        outputDocuments.get(1).save("part2.pdf");

        // 关闭所有文档
        for (PDDocument document : outputDocuments) {
            document.close();
        }
        inputDocument.close();
    }
}

