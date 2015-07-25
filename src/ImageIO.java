/******************************
	程序说明：图像存取类

	最后修改时间：2005-09-18
	
	作者：梁延研 澳门科技大学
*******************************/

import java.sql.*;
import java.awt.*;
import java.awt.image.*;

public class ImageIO {
	
	public Database db = new Database();
	ResultSet imrs;
	Object im;
	
	//临时变量
	int[] pixels;
	int temp1,temp2;

	public void saveImage(String strSQL) {
		
		//保存图像
		db.updateSql(strSQL);
		
	}
	
	public Object readImage(String strSQL) {
		
		//读出图像
		try {
			im = imrs.getBinaryStream(strSQL);	
			System.out.println(im);
			return im;	
		}
		catch(Exception e){
			return null;
		}
		
	}
	
	//测试
	/*
	public static void main(String[] args) {
		
		int[] image = new int[25];
		int w,h,count;
		w = 5;
		h = 5;
		count = 0;
		for(int i = 0; i<h; i++)
		{
			for(int j = 0; j<w; j++)
			{
				image[i*w+j] = count;
				count++;
				System.out.println(image[i*w+j]);
			}
		}
		
		String tab = "select * from image";
		String sql = "insert into image values('1.jpg',";
		
		for(int i = 0; i<h; i++)
		{
			for(int j = 0; j<w; j++)
			{
				sql = sql+image[i*w+j];
			}
		}
		sql = sql+")";
		System.out.println(sql);
		
		ImageIO test = new ImageIO();
		test.db.connection();
		
		test.saveImage(sql);	
		
		try {
			test.imrs = test.db.getResult(tab);	
			while(test.imrs.next()) {
				test.readImage("image");
			}	
		}
		catch(Exception e){
			System.out.println(e);
		}
	
		test.db.closeConnection();
	
	}
	*/
}