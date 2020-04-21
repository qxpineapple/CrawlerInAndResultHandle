package com.atguigu.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ChangeImgSize{
    public static void changeSize(int newWidth, int newHeight, String redPath,String savePath) {
        BufferedInputStream in = null;
        try {
        in = new BufferedInputStream(new FileInputStream(redPath));

        //字节流转图片对象
        Image bi = ImageIO.read(in);
        //构建图片流
        BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        //绘制改变尺寸后的图
        tag.getGraphics().drawImage(bi, 0, 0, newWidth, newHeight, null);
        //输出流
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(savePath));
        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        //encoder.encode(tag);
        ImageIO.write(tag, "JPEG", out);
        in.close();
        out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
