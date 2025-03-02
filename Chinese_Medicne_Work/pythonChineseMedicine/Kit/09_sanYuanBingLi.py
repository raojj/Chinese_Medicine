"""
    目的：三院病历整理
    日期：20240701
"""
import os
import re
from pdfminer.pdfparser import PDFParser
from pdfminer.pdfdocument import PDFDocument
from pdfminer.pdfinterp import PDFResourceManager
from pdfminer.pdfinterp import PDFPageInterpreter
from pdfminer.converter import PDFPageAggregator
from pdfminer.layout import LAParams
from pdfminer.pdfpage import PDFPage
import pandas as pd
from natsort import ns, natsorted

# 跟诊日期
data = '2025年02月23日'
# 处方医生
doctor = '蔡华珠'
# 舌象图暂时为空
tongue_1 = 'none'
tongue_2 = 'none'
figure_1 = 'none'
figure_2 = 'none'
# 病例文件夹
folder = r"E:\门诊病例整理-ING\三院治未病\2025\202502\20250223\20250223-三院治未病"
# excel地址
excel_path = r"E:\门诊病例整理-ING\三院治未病\2025\202502\20250223\20250223-三院门诊病历整理.xlsx"
columns = ['编号', '日期', '患者姓名', '患者性别', '出生日期', '年龄', '主诉', '问诊信息', '脉象', '舌象', '舌象正面图',
           '舌象背面图', '补充图1', '补充图2', '中医疾病', '证候', '方剂名称', '方剂组成', '处方医师']
index = 1
df_result = pd.DataFrame(columns=columns)
file_list = os.listdir(folder)
file_list = natsorted(file_list,alg=ns.PATH)
for filename in file_list:
    path = os.path.join(folder, filename)
    name = ''
    sex = ''
    age = ''
    birth = ''
    pulse = ''
    tongue = ''
    zheng_hou = ''
    disease = ''
    main_complain = ''
    disease_history = ''
    prescription_list = []
    prescription_name = ''
    content_list = []
    prescription_content = ''
    new_row = []
    name = filename.split('.')[1]
    fp = open(path, "rb")
    pdfparser = PDFParser(fp)
    doc = PDFDocument(pdfparser)
    # pdf资源管理器
    resource = PDFResourceManager()
    # 参数分析器
    laparam = LAParams()
    # 页面聚合器
    device = PDFPageAggregator(resource, laparams=laparam)
    # 页面解释器
    interpreter = PDFPageInterpreter(resource, device)

    for page in PDFPage.create_pages(doc):
        # 使用页面解释器读取
        interpreter.process_page(page)

        # 使用聚合器来获取内容
        layout = device.get_result()
        for out in layout:
            # 需要注意的是pdf文件不仅文字，还有图片等内容，
            # 为了避免错误，我们判断是否为文本
            if hasattr(out, 'get_text'):
                # print(out.get_text(), type(out.get_text()))
                if '性别' in out.get_text():
                    sex = out.get_text().split('：')[1].strip('\n')
                    # print(sex.replace('\n', ''))
                if '年龄' in out.get_text():
                    age = out.get_text().split('：')[1].strip('\n')
                    # print(age.replace('\n', ''))
                # if '姓名' in out.get_text():
                #     name = out.get_text().split('：')[1]
                # print(name.replace('\n', ''))
                if '体格检查' in out.get_text():
                    lists = out.get_text().split('体格检查')
                    for i in range(len(
                            lists[0].replace('\n', '').replace('主诉', '\n主诉').replace('现病史', '\n现病史').split(
                                '：'))):
                        if "主  诉" in lists[0].replace('\n', '').replace('主诉', '\n主诉').replace('现病史',
                                                                                                    '\n现病史').split(
                                '：')[i]:
                            main_complain = str(lists[0]).replace('\n', '').replace('主诉', '\n主诉').replace('现病史',
                                                                                                              '\n现病史').split(
                                '：')[i + 1].split('\n')[0].replace('简要病史', '')
                        if "简要病史" in lists[0].replace('\n', '').replace('主诉', '\n主诉').replace('现病史',
                                                                                                  '\n现病史').split(
                            '：')[i] or "现病史" in lists[0].replace('\n', '').replace('主诉', '\n主诉').replace('现病史', '\n现病史').split('：')[i]:
                            disease_history = \
                                str(lists[0]).replace('\n', '').replace('主诉', '\n主诉').replace('现病史',
                                                                                                  '\n现病史').split(
                                    '：')[
                                    i + 1]
                    # print(disease_history)
                    birth = str(lists[1].split('\n')[1])
                    pulse = str(lists[1].split('\n')[2])
                    tongue = str(lists[1].split('\n')[3])
                    # print(main_complain, birth.replace('\n', ''), pulse.replace('\n', ''), tongue.replace('\n', ''))
                if '诊断' in out.get_text() and len(
                        out.get_text().replace('\n', '').replace('    ', ' ').split('诊断：')[1].split(' ')) == 2:
                    disease = \
                        out.get_text().replace('\n', '').replace('    ', ' ').split('诊断：')[1].split(
                            ' ')[0].strip('颈肩综合征').strip('一般性检查，其他的')
                    zheng_hou = \
                        out.get_text().replace('\n', '').replace('    ', ' ').split('诊断：')[1].split(
                            ' ')[1]
                    # print(filename, disease, zheng_hou)
                else:
                    if len(out.get_text().replace('\n', '').replace('    ', ' ').replace('g', 'g ').replace('  ',
                                                                                                            ' ').split(
                        ' ')) == 2 and "时间" not in out.get_text() and disease == '':
                        disease = out.get_text().replace('\n', '').replace('    ', ' ').replace('g', 'g ').replace('  ',
                                                                                                                   ' ').split(
                            ' ')[0]
                        zheng_hou = \
                            out.get_text().replace('\n', '').replace('    ', ' ').replace('g', 'g ').replace('  ',
                                                                                                             ' ').split(
                                ' ')[1]
                        # print(filename, disease, zheng_hou)
                    else:
                        content = out.get_text().replace('\n', '').replace('    ', ' ').replace('g', 'g ').replace('  ',
                                                                                                                   ' ').replace(
                            ']', '] ')
                        if '草药' in content:
                            prescription_name = re.search('[\\[].*?[]]', str(content)).group().strip('[').strip(']')
                            # print(content)
                            # print(prescription_name)
                        if 'g' in content:
                            content_list = content.split(' ')
                            # print(content_list)
                            for item in content_list:
                                if 'g' in item and item[len(item) - 4:len(item)] not in prescription_list:
                                    prescription_list.append(item)
                            prescription_content = ' '.join(prescription_list)
                            # print(prescription_content)
        # 行数据
    new_row.append(index)
    new_row.append(data)
    new_row.append(name)
    new_row.append(sex)
    new_row.append(birth)
    new_row.append(age)
    new_row.append(main_complain)
    new_row.append(disease_history)
    new_row.append(pulse)
    new_row.append(tongue)
    new_row.append(tongue_1)
    new_row.append(tongue_2)
    new_row.append(figure_1)
    new_row.append(figure_2)
    new_row.append(disease)
    new_row.append(zheng_hou)
    new_row.append(prescription_name)
    new_row.append(prescription_content)
    new_row.append(doctor)
    print(new_row)
    df_result.loc[len(df_result)] = new_row
    index += 1
df_result.to_excel(excel_path, header=True, index=False)
