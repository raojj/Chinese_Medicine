"""
    目的：爬取小红书的视频和图片
    日期：20240625
"""
import os
import time
from DrissionPage import WebPage
from DrissionPage.common import Actions
import requests

headers = {
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36'
}
# 文件保存目录
folder_pictures = r'E:\资料\书籍\阅读_心理学\心理学视频\XHS\图片'
folder_videos = r'E:\资料\书籍\阅读_心理学\心理学视频\XHS\视频'
# 开启浏览器
wp = WebPage()
ac = Actions(wp)
# 模拟滚动
ac.scroll(delta_y=50)
# wp.get('https://www.xiaohongshu.com')
info = []
index = 0
wp.listen.start(['/api/sns/web/v1/feed'])
for page in range(50):
    # packet = wp.listen.wait()
    for i in range(0, len(wp.eles('xpath://a[@class="cover ld mask"]'))):

        if i < len(wp.eles('xpath://a[@class="cover ld mask"]')):
            temp = wp.eles('xpath://a[@class="cover ld mask"]')[i]
            if temp.attr('href') not in info:
                info.append(temp.attr('href'))
                temp.click()
                index += 1
                time.sleep(1)
                pack = wp.listen.wait()
                url_defaults = []
                url_defaults_video = []
                # 图片链接
                if 'data' in pack.response.body:
                    if 'items' in pack.response.body['data']:
                        if pack.response.body['data']['items']:
                            note_card = pack.response.body['data']['items'][0].get('note_card')
                            if note_card and 'image_list' in note_card:
                                url_defaults = [image.get('url_default') for image in note_card['image_list'] if
                                                'url_default' in image]
                # 视频链接
                if 'data' in pack.response.body:
                    if 'items' in pack.response.body['data']:
                        if pack.response.body['data']['items']:
                            note_card = pack.response.body['data']['items'][0].get('note_card')
                            if note_card and 'video' in note_card:
                                if 'media' in note_card['video']:
                                    url_defaults_video.append(
                                        note_card['video']['media']['stream']['h264'][0].get('master_url'))
                print(f"已经处理了{index}个笔记")
                if len(url_defaults_video) == 0:
                    for url_default in url_defaults:
                        things = requests.get(url_default, headers=headers)
                        name = os.path.basename(url_default)
                        savePath = os.path.join(folder_pictures, name + '.jpg')
                        with open(savePath, 'wb') as f:
                            f.write(things.content)
                        f.close()
                        time.sleep(1)
                else:
                    for url_default in url_defaults_video:
                        things = requests.get(url_default, headers=headers)
                        name = os.path.basename(url_default)
                        savePath = os.path.join(folder_videos, name[0:10] + '.mp4')
                        with open(savePath, 'wb') as f:
                            f.write(things.content)
                        f.close()
                        time.sleep(1)
                # 关闭小窗口
                wp.ele('.buttons engage-bar-style').child('.left').child('.like-wrapper like-active').click()
                time.sleep(1)
                wp.ele('xpath://div[@class="close close-mask-dark"]').click()
                time.sleep(1)
        else:
            wp.ele('.floating-btn-sets').child('.back-top active')
            time.sleep(5)
            wp.refresh()
    ac.scroll(delta_y=5)
