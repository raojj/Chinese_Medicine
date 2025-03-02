"""
    目的：拆分病案
    日期：20240623
"""
import os
import re
import pandas as pd

# 导入依赖库
from docx import Document


# 读取word文档
def read_Docx(file_name):
    doc = Document(file_name)
    list_of_paragraphs = []
    for paragraph in doc.paragraphs:
        if "预览" in paragraph.text or "免责" in paragraph.text or "扫码" in paragraph.text:
            continue
        list_of_paragraphs.append(paragraph.text)
    return list_of_paragraphs


if __name__ == '__main__':
    # list_docx = read_Docx(r'D:\RJ_WORKFILE\Chinese_Medicine\周老师\20240623-张喜奎病案整理\张喜奎公众号-1-1933\张喜奎杏林耕读传薪录1颊肿医案一则.docx')
    # print(list_docx[1:len(list_docx)-1])
    # print(re.findall(r'传薪录(\d*)', list_docx[0]))
    # print(re.findall(r'——(.*)医案', list_docx[0]))
    folder = r'E:\工作\Chinese_Medicine\周老师\20240623-张喜奎病案整理\张喜奎公众号-1-1933'
    columns = ['ID', '疾病名', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
               'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'
               , 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a']
    df_result = pd.DataFrame(columns=columns)
    count = len(os.listdir(folder))
    index = 0
    for filename in os.listdir(folder):
        file_path = os.path.join(folder, filename)
        list_docx = read_Docx(file_path)
        columns = ['ID', '疾病名', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
                   'a','a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
                   'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a',
                   'a', 'a']
        id = re.findall(r'传薪录(\d*)', filename)
        name = re.findall(r'传薪录\d*(.*)医案一则', filename)
        if len(id) != 0:
            columns[0] = id[0]
        else:
            columns[0] = filename
        if len(name) != 0:
            columns[1] = name[0]
        else:
            columns[1] = filename
        for i in range(1,len(list_docx)):
            columns[i+1] = list_docx[i]
            if "按语" in list_docx[i]:
                columns[len(columns)-1] = str(list_docx[i:len(list_docx)])
                break
        df_result.loc[len(df_result)] = columns
        print(f"已处理{index}个文件，还剩下{len(os.listdir(folder))-index}个文件")
        index += 1
    df_result.to_excel(r"E:\工作\Chinese_Medicine\周老师\20240623-张喜奎病案整理\按语.xlsx")
