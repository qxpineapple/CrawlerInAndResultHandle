package com.atguigu.utils;

import com.atguigu.pojo.Book;

import java.util.List;

public class AddBookUtils {
    public static final String BOOK_TYPE = "医学";
    public static final String CSV_PATH = "C:\\Users\\Administrator\\source\\repos\\PythonApplication1\\PythonApplication1\\"+BOOK_TYPE+".csv";
    public static final String IMG_PATH = "C:\\Users\\Administrator\\Desktop\\图书信息\\"+BOOK_TYPE+"\\原图\\";
    public static final String SAVE_IMG_PATH = "C:\\Users\\Administrator\\Desktop\\图书信息\\"+BOOK_TYPE+"\\";

    public static void main(String[] args) {

        //Excel读取方法
        /*
        // 设定Excel文件所在路径
        String excelFileName = "C:\\Users\\Administrator\\Desktop\\自动录入\\1.xlsx";
        // 读取Excel文件内容
        */
        String csv_path = "C:\\Users\\Administrator\\Desktop\\自动录入\\1.csv";
        List<Book> readResult = CSVReader.readFile(BOOK_TYPE,CSV_PATH);
        for (Book book: readResult) {
            String imgPath = IMG_PATH + book.getImgPath();
            String savePath = SAVE_IMG_PATH + book.getName() + ".jpg";
            book.setImgPath("img/"+book.getName()+".jpg");
            ChangeImgSize.changeSize(450,450,imgPath,savePath);
            String output ="bookDao.addBook(new Book(null,\""+book.getName()+"\", \""+book.getAuthor()+"\", new BigDecimal(\""+book.getPrice()+
                            "\"),"+book.getSales()+","+book.getStock()+",\""+book.getImgPath()+
                            "\",\""+book.getType()+"\",\""+book.getPress()+"\",\""+book.getTime()+"\",\""+book.getIntroduction()+"\"));";
            System.out.println(output);
        }
        System.out.println(readResult.size());
        // todo 进行业务操作

        //CSVReader.readFile();
    }
}
