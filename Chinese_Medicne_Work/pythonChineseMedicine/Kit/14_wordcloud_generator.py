import os

import pandas as pd
from wordcloud import WordCloud
import matplotlib.pyplot as plt


def read_excel_sheet(file_path, sheet_name):
    """读取Excel文件中的指定sheet"""
    df = pd.read_excel(file_path, sheet_name=sheet_name)
    return df


def generate_wordcloud(data, sheet_name, out_path):
    """生成词云并保存"""
    # 将数据转换为字典格式
    word_freq = dict(zip(data.iloc[:, 0], data.iloc[:, 1]))
    output_file = os.path.join(out_path, '词云图' + sheet_name + '.png')
    # 创建词云对象
    wordcloud = WordCloud(
        scale=32,
        width=400,
        height=800,
        max_words=500,
        background_color='white',
        font_path='C:/Windows/fonts/simsun.ttc'  # 支持中文的字体
    ).generate_from_frequencies(word_freq)

    # 显示并保存词云
    plt.figure(figsize=(5, 10))
    plt.imshow(wordcloud, interpolation='bilinear')
    plt.axis('off')
    plt.savefig(output_file, bbox_inches='tight', pad_inches=0.2, dpi=900)
    plt.show()


if __name__ == '__main__':
    # 示例用法
    file_path = "F:\\学业\\周老师\\2024\\202408-DONE\\20240801-博士论文-DONE\\博士毕业论文-20241124\\数据.xlsx"  # 替换为实际Excel文件路径
    sheet_name = '教材-中医疾病'  # 替换为实际sheet名称
    out_path = 'F:/学业/周老师/202501-ING/20250110-AI&博士论文/code'
    # 读取数据
    data = read_excel_sheet(file_path, sheet_name)

    # 生成词云
    generate_wordcloud(data, sheet_name, out_path)
