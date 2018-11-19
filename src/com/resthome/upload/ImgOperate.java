package com.resthome.upload;
/**
 * 图片工具类， 图片水印，文字水印，缩放，补白等
 */
import java.awt.AlphaComposite; 
import java.awt.Graphics2D; 
import java.awt.Image; 
import java.awt.RenderingHints; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.OutputStream; 
 
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.ImageIcon; 

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
 
/**
 * 图片水印
 */ 
public class ImgOperate {
	    /**
	     * 给图片添加水印
	     * @param iconPath 水印图片路径
	     * @param srcImgPath 源图片路径
	     * @param targerPath 目标图片路径
	     */ 
	    public void markImageByIcon(String iconPath, String srcImgPath, 
	            String targerPath) { 
	        markImageByIcon(iconPath, srcImgPath, targerPath, null); 
	    } 
	    /**
	     * 给图片添加水印、可设置水印图片旋转角度
	     * @param iconPath 水印图片路径
	     * @param srcImgPath 源图片路径
	     * @param targerPath 目标图片路径
	     * @param degree 水印图片旋转角度
	     */ 
	    public void markImageByIcon(String iconPath, String srcImgPath, 
	            String targerPath, Integer degree) { 
	        OutputStream os = null; 
	        try { 
	            Image srcImg = ImageIO.read(new File(srcImgPath)); 
	 
	            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null), 
	                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB); 
	 
	            // 得到画笔对象 
	            // Graphics g= buffImg.getGraphics(); 
	            Graphics2D g = buffImg.createGraphics(); 
	 
	            // 设置对线段的锯齿状边缘处理 
	            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
	                    RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
	 
	            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg 
	                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null); 
	 
	            if (null != degree) { 
	                // 设置水印旋转 
	                g.rotate(Math.toRadians(degree), 
	                        (double) buffImg.getWidth() / 2, (double) buffImg 
	                                .getHeight() / 2); 
	            } 
	            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度 
	            ImageIcon imgIcon = new ImageIcon(iconPath); 
	            System.out.println(iconPath);
	            // 得到Image对象。 
	            Image img = imgIcon.getImage(); 
	            float alpha = 0.8f; // 透明度 
	            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 
	                    alpha)); 
	            // 表示水印图片的位置 
	            g.drawImage(img, srcImg.getHeight(null)-20, srcImg.getWidth(null)-20, null); 
	            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); 
	            g.dispose(); 
	            os = new FileOutputStream(targerPath); 
	 
	            // 生成图片 
	            ImageIO.write(buffImg, "JPG", os); 
	            System.out.println("图片完成添加Icon印章。。。。。。"); 
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } finally { 
	            try { 
	                if (null != os) 
	                    os.close(); 
	            } catch (Exception e) { 
	                e.printStackTrace(); 
	            } 
	        } 
	    } 
	    
	    private static final String PICTRUE_FORMATE_JPG = "jpg";

	    /**
	     * 添加图片水印
	     * @param targetImg 目标图片路径 
	     * @param waterImg  水印图片路径 
	     * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
	     * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
	     * @param d 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	*/
	    public final static void pressImage(String targetImg, String waterImg,double d) {
	            try {
	                File file = new File(targetImg);
	                Image image = ImageIO.read(file);
	                int width = image.getWidth(null);
	                int height = image.getHeight(null);
	                BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	                Graphics2D g = bufferedImage.createGraphics();
	                g.drawImage(image, 0, 0, width, height, null);
	            
	                Image waterImage = ImageIO.read(new File(waterImg));    // 水印文件
	                int width_1 = waterImage.getWidth(null);
	                int height_1 = waterImage.getHeight(null);
	                float aa=(float) d;
	                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, aa));
	                int widthDiff = width - width_1;
	                int heightDiff = height - height_1;
	                g.drawImage(waterImage, widthDiff-5, heightDiff-5, width_1, height_1, null); // 水印文件结束
	                g.dispose();
	                ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	    }

	    /**
	     * 添加文字水印
	     * @param targetImg 目标图片路径 
	     * @param pressText 水印文字 
	     * @param fontName 字体名称 
	     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
	     * @param fontSize 字体大小，单位为像素
	     * @param color 字体颜色
	     * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
	     * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
	     * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	*/
	    public static void pressText(String targetImg, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
	        try {
	            File file = new File(targetImg);
	            
	            Image image = ImageIO.read(file);
	            int width = image.getWidth(null);
	            int height = image.getHeight(null);
	            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            Graphics2D g = bufferedImage.createGraphics();
	            g.drawImage(image, 0, 0, width, height, null);
	            g.setFont(new Font(fontName, fontStyle, fontSize));
	            g.setColor(color);
	            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
	            
	            int width_1 = fontSize * getLength(pressText);
	            int height_1 = fontSize;
	            int widthDiff = width - width_1;
	            int heightDiff = height - height_1;
	            if(x < 0){
	                x = widthDiff / 2;
	            }else if(x > widthDiff){
	                x = widthDiff;
	            }
	            if(y < 0){
	                y = heightDiff / 2;
	            }else if(y > heightDiff){
	                y = heightDiff;
	            }
	            
	            g.drawString(pressText, x, y + height_1);
	            g.dispose();
	            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
	     * @param text
	     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
	*/
	    public static int getLength(String text) {
	        int textLength = text.length();
	        int length = textLength;
	        for (int i = 0; i < textLength; i++) {
	            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
	                length++;
	            }
	        }
	        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
	    }

	    /**
	     * 图片缩放
	     * @param filePath 图片路径
	     * @param height 高度
	     * @param width 宽度
	     * @param bb 比例不对时是否需要补白
	*/
	    public static void resize(String filePath, int height, int width, boolean bb) {
	        try {
	            double ratio = 0; //缩放比例    
	            File f = new File(filePath);   
	            BufferedImage bi = ImageIO.read(f);   
	            Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);   
	            //计算比例   
	            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {   
	                if (bi.getHeight() > bi.getWidth()) {   
	                    ratio = (new Integer(height)).doubleValue() / bi.getHeight();   
	                } else {   
	                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();   
	                }   
	                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);   
	                itemp = op.filter(bi, null);   
	            }   
	            if (bb) {   
	                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);   
	                Graphics2D g = image.createGraphics();   
	                g.setColor(Color.white);   
	                g.fillRect(0, 0, width, height);   
	                if (width == itemp.getWidth(null))   
	                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);   
	                else  
	                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);   
	                g.dispose();   
	                itemp = image;   
	            }
	            ImageIO.write((BufferedImage) itemp, "jpg", f);   
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * 图片复制
	     * @param sourceDir 源文件目录
	     * @param targetDir 目标文件目录
	     */
	    public static void copy(String sourceDir,String targetDir)
	    {
	     try
	     {
	      File file=new File(sourceDir);
	      Image image =ImageIO.read(file); 
	      int width=image.getWidth(null);
	      int height=image.getHeight(null);
	      
	      BufferedImage imageTag=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	      imageTag.getGraphics().drawImage(image,0,0,width,height,null);
	      FileOutputStream out=new FileOutputStream(targetDir);
	      JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(out);
	      encoder.encode(imageTag);
	      out.close();
	     }catch (IOException e) {
	      e.printStackTrace();
	     }
	    }
}
