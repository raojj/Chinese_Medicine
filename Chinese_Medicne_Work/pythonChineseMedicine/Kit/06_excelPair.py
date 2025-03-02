"""
    目的：匹配excel表格中的关键词，匹配到的行写入到新的表格中
    日期：20240617
    作者：Joe
"""
import pandas as pd

# 读取第一个Excel表格并处理关键词
df_keywords = pd.read_excel(r'D:\RJ_WORK\20240617-实用内科学文本提取\keywordList.xlsx')
keywords = df_keywords.iloc[:, 0].tolist()  # 获取第一列的关键词列表

# 读取第二个Excel表格
df_data = pd.read_excel(r'D:\RJ_WORK\20240617-实用内科学文本提取\非实用内科学数据.xlsx')

# 创建一个空的DataFrame来存储匹配结果
columns = ['匹配疾病名'] + df_data.columns.tolist()  # 新表格的列名
df_result = pd.DataFrame(columns=columns)

# 在第二个Excel表格的第一列和第四列进行关键词匹配
for index, row in df_data.iterrows():
    first_col = row.iloc[0]
    fourth_col = row.iloc[3]

    for keyword in keywords:
        kw = keyword.replace('病', '').replace('症', '')
        if kw in str(first_col) or kw in str(fourth_col):
            new_row = [keyword] + row.tolist()
            df_result.loc[len(df_result)] = new_row
        else:
            new_row = [keyword] + ['无', '无', '无', '无', '无']
            df_result.loc[len(df_result)] = new_row

# 将结果写入新的Excel文件
df_result.to_excel(r'D:\RJ_WORK\20240617-实用内科学文本提取\数据-匹配后-2.xlsx', index=False)
