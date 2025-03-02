# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""
# 14医书，16注本提取 词对

import shutil
import os
import time
from win32com.client.gencache import EnsureDispatch
from win32com.client import constants

class ShuiWordApp:
#初始化，打开相关app 
    WordApp = EnsureDispatch("Word.Application")
    WordApp.Visible = True # 调试阶段打开
    WordApp.DisplayAlerts = 0 # 警告信息关闭
    
    ExcelApp = EnsureDispatch("Excel.Application")
    ExcelApp.Visible = True
    ExcelApp.DisplayAlerts = 0
    
    workPath = os.getcwd()
    
    def __init__(self):
    
        pass

     # 清理cache
    def removeCache(self):
        fp = "C:/Users/Joe/AppData/Local/Temp/gen_py"
        del_list = os.listdir(fp)
        for f in del_list:
            file_path = os.path.join(fp, f)
            if os.path.isfile(file_path):
                os.remove(file_path)
            elif os.path.isdir(file_path):
                shutil.rmtree(file_path)  
                
    def getKeywords(self):
        keywords = []
        cnt = 2    # int(input("请输入词对个数："))
        xlsPath = os.path.join(self.workPath,'Keywords.xlsx')
        xls = app.ExcelApp.Workbooks.Open(xlsPath, ReadOnly=True)
        row = 1
        for row in range(1, cnt+1):
            lct = 'A' + str(row)
            keyword = xls.Worksheets("Sheet1").Range(lct).Text
            keywords.append(keyword)
        xls.Close(0)
        
        return keywords

    # 若文件存在，则删除keywords里面对应的主题词对
    def isDone(self, finalDir, keywords):
        works = os.listdir(finalDir)
        if len(works)>0:
            for work in works:
                w = work.split('-')
                name = w[0]+'-'+w[1]+'-'+w[2]+'-'+w[3]
                if name in keywords:
                    keywords.remove(name)
        else:
            return 0
        
        return keywords
    
    def mkFinalDir(self, finalDir):
        folder = os.path.exists(finalDir)
        if not folder:
            os.mkdir(finalDir)
            
if __name__=="__main__":
   
    app = ShuiWordApp()
    
    keywords = app.getKeywords()
    print(keywords)
    
    pathFile = app.workPath
    resFileName = '14医书.xlsx'    #input("请输入源文件名：")
    xlsPath = os.path.join(pathFile, resFileName)
    finalDir = os.path.join(pathFile, '词对-' + resFileName.replace('.xlsx', ''))
    app.mkFinalDir(finalDir)
    xls = app.ExcelApp.Workbooks.Open(xlsPath, ReadOnly=True)
    xlsSt = xls.Worksheets("Sheet1").UsedRange
    
    app.isDone(finalDir, keywords)
    
    for keyword in keywords:    
        keywordCuts = keyword.split('-')
        # 主题词对
        # xlsSt.AutoFilter(Field=1, Criteria1='*Keyword'+keywordCuts[2]+'*', Operator=1,
        #                  Criteria2='*'+keywordCuts[3]+'*')

        # 单本书内单主题
        xlsSt.AutoFilter(Field=1, Criteria1='*'+keywordCuts[2]+'*', Operator=1,
                         Criteria2='*'+keywordCuts[3]+'*')
        xlsSt.Select()
        app.ExcelApp.Selection.Copy()
        doc = app.WordApp.Documents.Add()

        time.sleep(3)
        app.WordApp.Selection.PasteSpecial(DataType=2)
        print(str(doc.Content.Paragraphs.Count))
        if doc.Content.Paragraphs.Count == 1:
            with open('error.txt', 'at') as f:
                f.write(keyword+'\n')
                f.close()
            errorName =  keyword + "-词对-无-" + resFileName.replace('xlsx', 'docx')
            errorPath = os.path.join(finalDir, errorName)
            doc.SaveAs2(errorPath)
            doc.Close(0)
            continue
            
        doc.Content.Font.Name = "宋体"
        doc.Content.Font.Size = 12
        doc.Content.ParagraphFormat.LineSpacingRule = 1
        
#            doc.Content.Find.ClearFormatting()
#            doc.Content.Find.Replacement.ClearFormatting()
#            app.WordApp.Selection.WholeStory()
#            while app.WordApp.Selection.Find.Execute('ENTER*Keyword', False, False, True, False, False, True, 1,  ):
#                app.WordApp.Selection.Font.Name = "微软亚黑"
#                app.WordApp.Selection.Font.Bold = True
            
        doc.Content.Find.ClearFormatting()
        doc.Content.Find.Replacement.ClearFormatting()    
        
        doc.Content.Find.Execute(FindText='正文^p', ReplaceWith='', Replace=2)
        # 教材需注释这句代码
        doc.Content.Find.Execute(FindText='found_on_page', ReplaceWith='ABC^p页码：', Replace=2)
        # 教材需改成ABC^p句子
        doc.Content.Find.Execute(FindText='in_sentence', ReplaceWith='^p句子：', Replace=2)

        # 设置书名样式
        doc.Content.Find.ClearFormatting()
        doc.Content.Find.Replacement.ClearFormatting()    
        app.WordApp.Selection.WholeStory()
        while app.WordApp.Selection.Find.Execute('ENTER*author', False, False, True,):
            app.WordApp.Selection.Font.Bold = True
            app.WordApp.Selection.Font.Name = "微软雅黑"
            app.WordApp.Selection.Font.Color = 16711680
            
        doc.Content.Find.Execute(FindText='author', ReplaceWith='^p作者：', Replace=2)
            
        doc.Content.Find.ClearFormatting()
        doc.Content.Find.Replacement.ClearFormatting()    
        app.WordApp.Selection.WholeStory()
        cnt = 1
        while app.WordApp.Selection.Find.Execute('ENTER', False, False, True, False, False, True, 1, False, '^p（'+str(cnt)+'）', 1,):
            app.WordApp.Selection.Font.Bold = False
            cnt = cnt + 1
        
        doc.Content.Find.Execute(FindText='Keyword', ReplaceWith='^p关键词：ABC', Replace=2)

        # 设置 “作者：”样式
        doc.Content.Find.ClearFormatting()
        doc.Content.Find.Replacement.ClearFormatting()
        app.WordApp.Selection.WholeStory()
        while app.WordApp.Selection.Find.Execute('作者：', ):
            app.WordApp.Selection.Font.Color = 0

        # 设置 “关键词：”样式
        doc.Content.Find.ClearFormatting()
        doc.Content.Find.Replacement.ClearFormatting()    
        app.WordApp.Selection.WholeStory()
        while app.WordApp.Selection.Find.Execute('关键词：',):
            app.WordApp.Selection.Font.Bold = True
            app.WordApp.Selection.Font.Name = "微软雅黑"

        # 设置 “页码：”样式（教材需注释）
        doc.Content.Find.ClearFormatting()
        doc.Content.Find.Replacement.ClearFormatting()
        app.WordApp.Selection.WholeStory()
        while app.WordApp.Selection.Find.Execute('页码：', ):
            app.WordApp.Selection.Font.Bold = True
            app.WordApp.Selection.Font.Name = "微软雅黑"

        # 设置 “句子：”样式
        doc.Content.Find.ClearFormatting()
        doc.Content.Find.Replacement.ClearFormatting()    
        app.WordApp.Selection.WholeStory()
        while app.WordApp.Selection.Find.Execute('句子：', ):
            app.WordApp.Selection.Font.Bold = True
            app.WordApp.Selection.Font.Name = "微软雅黑"
            
        doc.Content.Find.Execute(FindText='ABC*ABC', MatchWildcards=True, ReplaceWith=keywordCuts[2]+'-'+keywordCuts[3], Replace=2)                

        # 设置第一个关键词的颜色
        doc.Content.Find.ClearFormatting()
        doc.Content.Find.Replacement.ClearFormatting()    
        app.WordApp.Selection.WholeStory()
        while app.WordApp.Selection.Find.Execute(keywordCuts[2]):
            app.WordApp.Selection.Font.Bold = True
            app.WordApp.Selection.Font.Color = 255

        # 设置第二个关键词的颜色
        doc.Content.Find.ClearFormatting()
        doc.Content.Find.Replacement.ClearFormatting()                
        app.WordApp.Selection.WholeStory()
        while app.WordApp.Selection.Find.Execute(keywordCuts[3]):
            app.WordApp.Selection.Font.Bold = True
            app.WordApp.Selection.Font.Color = 255

        # doc.Content.Find.ClearFormatting()
        # doc.Content.Find.Replacement.ClearFormatting()                
        # app.WordApp.Selection.WholeStory()
        # while app.WordApp.Selection.Find.Execute('ABC*ABC', False, False, True, False, False, True, 1, False, keywordCuts[2]+'-'+keywordCuts[3], Replace=1):
        #     app.WordApp.Selection.Font.Bold = True
        #     app.WordApp.Selection.Font.Color = 255
            
        doc.Sections(1).Footers(1).PageNumbers.Add(PageNumberAlignment=1, FirstPage=True)

        outputName = keyword + "-词对-" + resFileName.replace('xlsx', 'docx')
        outputPath = os.path.join(finalDir, outputName)
        
        doc.SaveAs2(outputPath)

        doc.Close()
        print(keyword+"处理完毕")
    xls.Close(0)
    
    app.WordApp.Quit()
    app.ExcelApp.Quit()
    app.removeCache()