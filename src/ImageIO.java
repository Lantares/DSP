/******************************
	����˵����ͼ���ȡ��

	����޸�ʱ�䣺2005-09-18
	
	���ߣ������� ���ſƼ���ѧ
*******************************/

import java.sql.*;
import java.awt.*;
import java.awt.image.*;

public class ImageIO {
	
	public Database db = new Database();
	ResultSet imrs;
	Object im;
	
	//��ʱ����
	int[] pixels;
	int temp1,temp2;

	public void saveImage(String strSQL) {
		
		//����ͼ��
		db.updateSql(strSQL);
		
	}
	
	public Object readImage(String strSQL) {
		
		//����ͼ��
		try {
			im = imrs.getBinaryStream(strSQL);	
			System.out.println(im);
			return im;	
		}
		catch(Exception e){
			return null;
		}
		
	}
	
	//����
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