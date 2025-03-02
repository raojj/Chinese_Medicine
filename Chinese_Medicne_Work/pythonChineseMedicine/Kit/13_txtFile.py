import os


def split_text_by_title(input_file, output_folder):
    # 创建输出文件夹（如果不存在）
    if not os.path.exists(output_folder):
        os.makedirs(output_folder)

    with open(input_file, 'r', encoding='utf-8') as file:
        lines = file.read().splitlines()
    dif = 0
    n = 0
    while n < len(lines):
        if '8.综合医书类' in lines[n]:  # 判断是否包含 > 符号
            title = lines[n].strip()  # 去掉首尾空白字符作为标题
            content = []
            j = 1
            while n + j < len(lines) and '8.综合医书类' not in lines[n + j]:  # 直到下一个包含 > 的行
                content.append(lines[n + j])
                j += 1

            if content:
                # 将标题中的非法字符替换为下划线，避免文件名错误
                safe_title = "".join(c if c.isalnum() or c in (' ', '_') else '_' for c in title)
                if len(safe_title) > 100:
                    safe_title = safe_title[:100] + '...' + str(dif)
                    dif += 1
                output_file = os.path.join(output_folder, f"{safe_title}.txt")  # 输出到指定文件夹
                with open(output_file, 'w', encoding='utf-8') as outfile:
                    outfile.write('\n'.join(content))

            n += j  # 跳到下一个标题行
        else:
            n += 1  # 继续查找


if __name__ == "__main__":
    input_file = "E:/资料/书籍/中医药/中华医典/UTF-8版/综合医书类.txt"  # 替换为你的文件名
    output_dir = 'E:/资料/书籍/中医药/中华医典/切割后文件/8.综合医书类'  # 替换为你想保存文件的目录
    split_text_by_title(input_file, output_dir)
