import pandas as pd

# 原始数据文件地址
dataPath = r'E:\工作\Chinese_Medicine\周老师\202409-ING\20240918-信息提取\原始数据.xlsx'
resultPath = r'E:\工作\Chinese_Medicine\周老师\202409-ING\20240918-信息提取\20241005证型症征.xlsx'
# 单元格name
columns = ['编码', '证型', '同义词', '解释', '症征', '英文名', '注']
# 创建结果表格
df_result = pd.DataFrame(columns=columns)
df_data = pd.read_excel(dataPath)
# 2059
for index in range(0,2059):
    result = []
    tar = 0
    id = ''
    name = ''
    similarName = ''
    explanation = ''
    syndrome = ''
    nameEnglish = ''
    mention = ''
    message = df_data.iloc[index,0]
    messageList = message.split('TARGEINONE')
    id = messageList[0]
    name = messageList[1].split('  ')[0]
    nameEnglish = messageList[1].split('  ')[1]
    if "注：" in messageList[len(messageList)-1]:
        mention = messageList[len(messageList)-1]
        if "临床以" in messageList[len(messageList)-2]:
            syndrome = messageList[len(messageList)-2]
            explanation = messageList[len(messageList)-3]
            tar = len(messageList)-3
        else:
            syndrome = "none"
            explanation = messageList[len(messageList)-2]
            tar = len(messageList)-2
    elif "临床以" in messageList[len(messageList)-1]:
        syndrome = messageList[len(messageList)-1]
        explanation = messageList[len(messageList)-2]
        mention = "none"
        tar = len(messageList)-2
    else:
        mention = "none"
        syndrome = "none"
        explanation = messageList[len(messageList)-1]
        tar = len(messageList)-1
    if tar == 2:
        similarName = "none"
    else:
        similarName = ';'.join(messageList[2:tar])
    result.append(id)
    result.append(name)
    result.append(similarName)
    result.append(explanation)
    result.append(syndrome)
    result.append(nameEnglish)
    result.append(mention)
    print(result)
    df_result.loc[len(df_result)] = result
df_result.to_excel(resultPath, header=True, index=False)
