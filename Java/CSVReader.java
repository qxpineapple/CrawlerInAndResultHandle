package com.atguigu.utils;

import com.atguigu.pojo.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CSVReader {

    public static List<Book> readFile(String bookType,String CSV_path){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(CSV_path));//换成你的文件名
            List<Book> books = new ArrayList<>();

            String line = null;
            Random random = new Random();
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                if(item.length == 7){
                    if(item[0].length()<=25 && item[2].length()<=40 && item[5].length()<=100 && item[5].length()>10 &&item[6]!=null && item[6].length() < 10)
                    {
                        try {
                            Book book = new Book();
                            book.setName(item[0]);
                            book.setPrice(new BigDecimal(item[1]));
                            book.setAuthor(item[2]);
                            book.setPress(item[3]);
                            book.setImgPath(item[6]);
                            book.setTime(item[4]);
                            book.setIntroduction(item[5]);
                            book.setType(bookType);
                            book.setSales(random.nextInt(100));
                            book.setStock(random.nextInt(200));
                            books.add(book);
                        }catch (Exception e){
                            //e.printStackTrace();
                            continue;
                        }
                    }
                }

            }
            return books;
        } catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }
}



