import os
import random
import time
from DrissionPage import WebPage
from DrissionPage.common import Actions
import requests

# 新建网页
ks = WebPage()
# ks.get('https://www.kuaishou.com/profile/3xn65sbfzt7x9yq')
headers = {
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36'
}
folder_videos = r'E:\资料\书籍\阅读_心理学\心理学视频\KS\视频'
ac = Actions(ks)
# test = ks.ele('.video-card video-item vertical')
# test.click()
# src = ks.ele('.player-video').link
# video = requests.get(src, headers=headers)
# name = os.path.basename(src)
# savePath = os.path.join(folder_videos, name[10:20] + '.mp4')
# with open(savePath, 'wb') as f:
#     f.write(video.content)
# f.close()
# time.sleep(1)
# print(src)
#
# ks.ele('.interactive-bar-item').click()
# ks.ele('.close-page').click()
# ac.scroll(delta_y=500)
info = []
for page in range(50):
    for i in range(0, len(ks.eles('.video-card video-item vertical'))):
        if i < len(ks.eles('.video-card video-item vertical'))-1:
            print(i)
            temp = ks.eles('.video-card video-item vertical')[i]
            if temp not in info:
                info.append(temp)
                temp.click()
                src = ks.ele('.player-video').link
                # 取消点赞
                ks.ele('.interactive-bar-item').click()
                time.sleep(2)
                # 关闭页面
                ks.ele('.close-page').click()
                video = requests.get(src, headers=headers)
                name = os.path.basename(src)
                savePath = os.path.join(folder_videos, name[10:25] + '.mp4')
                with open(savePath, 'wb') as f:
                    f.write(video.content)
                f.close()
                x = random.randint(2,8)
                time.sleep(x)
            else:
                continue

    # ks.refresh()
    # time.sleep(3)
    # ks.ele('粉丝').click()
    # time.sleep(2)
    # ks.ele('作品').click()
    # time.sleep(2)
    # ks.ele('赞').click()
    # time.sleep(7)
    ac.scroll(delta_y=500)
