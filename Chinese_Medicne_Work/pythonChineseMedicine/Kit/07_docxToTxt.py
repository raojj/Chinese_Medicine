import os
from docx import Document


def docx_to_txt(docx_path, txt_path):
    try:
        doc = Document(docx_path)
        full_text = []

        for para in doc.paragraphs:
            # 将段落添加到列表中，可以添加一些格式化信息，如缩进
            full_text.append(para.text + '\n\n')

        # 将所有文本写入到txt文件
        with open(txt_path, "w", encoding="utf-8") as text_file:
            text_file.writelines(full_text)
    except Exception as e:
        print(f"An error occurred: {e}")


def convert_folder_to_txt(folder_path):
    # 遍历文件夹中的所有文件
    for filename in os.listdir(folder_path):
        if filename.endswith('.docx'):
            docx_file = os.path.join(folder_path, filename)
            txt_file = os.path.join(folder_path, filename[:-5] + '.txt')
            docx_to_txt(docx_file, txt_file)
            print(f"Converted {filename} to {txt_file}")


# 指定文件夹路径
folder_path = 'path_to_your_folder'
convert_folder_to_txt(folder_path)