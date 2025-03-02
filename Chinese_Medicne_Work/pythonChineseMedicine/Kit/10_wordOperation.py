"""
    目的：操作word
"""
import win32com.client
import shutil
import os


def clearCache():
    fp = 'C:/Users/Joe/AppData/Local/Temp/gen_py'
    del_list = os.listdir(fp)
    for f in del_list:
        file_path = os.path.join(fp, f)
        if os.path.isfile(file_path):
            os.remove(file_path)
        elif os.path.isdir(file_path):
            shutil.rmtree(file_path)


if __name__ == '__main__':
    word_app = win32com.client.Dispatch("Word.Application")
    word_app.Visible = False
    word_app.DisplayAlerts = False
    # 设置文件夹路径和宏名称
    folder_path = r"D:\RJ_WORKFILE\Chinese_Medicine\周老师\20240623-张喜奎病案整理\张喜奎公众号-1-1933"
    macro_name = "test"
    # 批量运行宏
    count = len(os.listdir(folder_path))
    i = 0
    for filename in os.listdir(folder_path):
        if filename.endswith(".docx") or filename.endswith(".doc"):
            count -= 1
            i += 1
            doc_path = os.path.join(folder_path, filename)
            doc = word_app.Documents.Open(doc_path)
            # 运行宏
            word_app.Run(macro_name)
            # 保存并关闭文档
            doc.Close(SaveChanges=True)
            print(f"已经处理了{i}个文件，还剩下{count}个文件")
    word_app.Quit()
    clearCache()
