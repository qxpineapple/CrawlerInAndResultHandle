import requests
from lxml import etree
from fake_useragent import UserAgent
import re
import csv
import urllib
import urllib.request
import re
import os

TYPE = '医学'
KEY_VALUE = 'ҽѧ'
CSV_PATH = TYPE +'.csv'
IMG_PATH = 'C:\\Users\\Administrator\\Desktop\\图书信息\\'+TYPE+'\\原图'
x = 1
def get_page(key):
     for page in range(1,20):
         url = 'http://search.dangdang.com/?key=%s&act=input&page_index=%s' % (key,page)
         headers = {
             'User-Agent':UserAgent().random
         }
         response = requests.get(url = url,headers = headers)
         parse_page(response)
         print('page %s over!!!' % page)

def parse_page(response):
     tree = etree.HTML(response.text)
     li_list = tree.xpath('//ul[@class="bigimg"]/li')
     # print(len(li_list))  # 测试
     path = IMG_PATH
     if not os.path.isdir(path):
        os.makedirs(path)  # 判断没有此路径则创建
     paths = path + '\\'  # 保存在test路径下
     global x
     flag = 0
     for li in li_list:
         data = []
         try:
             # 获取书的标题,并添加到列表中
             title = li.xpath('./p[@class="name"]/a/@title')[0].strip()
             data.append(title)
            # 获取价格,并添加到列表中
             price = li.xpath('./p[@class="price"]/span[1]/text()')[0]
             pub_price = re.sub('¥','',price).strip()
             data.append(pub_price)
             # 获取作者,并添加到列表中
             author = ''.join(li.xpath('./p[@class="search_book_author"]/span[1]//text()')).strip()
             data.append(author)
             # 获取出版社,并添加到列表中
             press = ''.join(li.xpath('./p[@class="search_book_author"]/span[3]//text()')).strip()
             pub_press = re.sub('/','',press).strip()
             data.append(pub_press)
             # 获取出版时间,并添加到列表中
             time = li.xpath('./p[@class="search_book_author"]/span[2]/text()')[0]
             pub_time = re.sub('/','',time).strip()
             data.append(pub_time)
             # 获取书本的简介,并添加到列表中.由于有些书本没有简介，所以要用try
             commodity_detail = ''
             commodity_detail = li.xpath('./p[@class="detail"]/text()')[0]
             data.append(commodity_detail)
             # 获取书的标题,并添加到列表中
             if(flag != 0):
                imgurl =''.join(li.xpath('./a/img/@data-original'))
             else:
                imgurl =''.join(li.xpath('./a/img/@src'))
                flag = flag + 1
             urllib.request.urlretrieve(imgurl, '{0}{1}.jpg'.format(paths, x))  # 打开imglist,下载图片到本地
             img_path = '' + str(x) + ".jpg"
             data.append(img_path)
             x = x + 1
         except Exception as e:
            pass
         save_data(data)

def save_data(data):
     writer.writerow(data)

def main():
    key = KEY_VALUE   # input('Please input key:')
    get_page(key) 
fp = open(CSV_PATH,'w+',encoding = 'utf-8-sig',newline = '')
writer = csv.writer(fp)
header = ['书名','价格','作者','出版社','出版时间','简介','图片名']
writer.writerow(header)
main()
fp.close()