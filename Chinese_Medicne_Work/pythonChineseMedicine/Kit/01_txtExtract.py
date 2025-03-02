import re
import os

# 汉字数字到阿拉伯数字的映射
chinese_to_arabic = {
    '一': 1, '二': 2, '三': 3, '四': 4, '五': 5,
    '六': 6, '七': 7, '八': 8, '九': 9, '十': 10,
    '十一': 11, '十二': 12, '十三': 13, '十四': 14, '十五': 15,
    '十六': 16, '十七': 17, '十八': 18, '十九': 19, '二十': 20, '二十一': 21, '二十二': 22, '二十三': 23, '二十四': 24,
    '二十五': 25, '二十六': 26, '二十七': 27, '二十八': 28, '二十九': 29
}


def chinese_number_to_arabic(chinese_num):
    return chinese_to_arabic.get(chinese_num, chinese_num)


def split_content(content, pattern):
    return re.split(pattern, content)


def extract_sections(content, level):
    sections = []
    pattern = re.compile(rf"(第[(\u4e00-\u9fa5\s)*]+{level}[\u4e00-\u9fa5\s]*\n)")
    splits = split_content(content, pattern)
    headers = re.findall(pattern, content)
    for i in range(1, len(splits)):
        header = headers[i - 1].strip()
        sections.append((header, splits[i].strip()))
    return sections


def main(input_file):
    with open(input_file, 'r', encoding='utf-8') as file:
        content = file.read()

    # 分割“第x篇”
    sections_pian = extract_sections(content, '篇')

    for x, content_pian in sections_pian:
        # 分割“第y章”
        sections_zhang = extract_sections(content_pian, '章')

        for y, content_zhang in sections_zhang:
            # 分割“第z节”
            sections_jie = extract_sections(content_zhang, '节')

            for z, content_jie in sections_jie:
                # 提取节标题
                title_match = re.match(rf"第[^篇章节\n]+节([\u4e00-\u9fa5\s]+)\n", content_jie)
                if title_match:
                    title = title_match.group(1).strip()
                    # 提取汉字数字部分
                    x_num = re.search(r"第([^篇章节]+)篇", x).group(1)
                    y_num = re.search(r"第([^篇章节]+)章", y).group(1)
                    z_num = re.search(r"第([^篇章节]+)节", z).group(1)
                    # 转换为阿拉伯数字
                    x_arabic = chinese_number_to_arabic(x_num)
                    y_arabic = chinese_number_to_arabic(y_num)
                    z_arabic = chinese_number_to_arabic(z_num)
                    file_name = f"{x_arabic}-{y_arabic}-{z_arabic}-{title}.txt"
                    with open(file_name, 'w', encoding='utf-8') as output_file:
                        output_file.write(content_jie)


if __name__ == "__main__":
    input_file = 'D:\\RJ_WORK\\实用内科学文本提取\\实用内科学-十篇后.txt'  # 请将 'input.txt' 替换为您的txt文件路径
    main(input_file)
