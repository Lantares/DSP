/******************************************
	程序说明：通用数字信号出处理平台主程序

	最后修改时间：2005-09-18
	
	作者：梁延研 澳门科技大学
******************************************/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class DSP extends Frame {
	
	//定义菜单项
	MenuBar menu_bar;
	Menu fileMenu,createMenu,transMenu,aboutMenu;
	MenuItem menu_import_1d_Data,menu_import_2d_Data,menu_import_3d_Data,menu_Exit;
	MenuItem menu_create_1d_Data,menu_create_2d_Data,menu_create_3d_Data,menu_show_Data;
	MenuItem menu_trans_DFT,menu_trans_DCT,menu_trans_WHT,menu_trans_SLT,menu_trans_UST,menu_trans_VST,menu_trans_DWT,menu_trans_CED;
	MenuItem menu_Help,menu_About;		
	
	//存储变量
	//普通数据
	double[] pdata;
	int il,iw,ih;
	
	//图像数据
	Image im;
	int[] pixels;
	
	//数据装载完成标记	
	boolean flagLoad = false;
	//数据类型(维数)标记
	int flagDimension = 0;
	//是否图像
	boolean flagIm = false;
	
	Create1dData cd1 = new Create1dData();

	public DSP() {
		
		//主菜单条
		menu_bar = new MenuBar();
		fileMenu = new Menu("File");
		createMenu = new Menu("Create Data");
		transMenu = new Menu("Transform");
		aboutMenu = new Menu("About");
		
		//读入数据菜单
		menu_import_1d_Data = new MenuItem("Import 1d Data");
		menu_import_2d_Data = new MenuItem("Import 2d Data");
		menu_import_3d_Data = new MenuItem("Import 3d Data");
		menu_Exit = new MenuItem("Exit");
		fileMenu.add(menu_import_1d_Data);
		fileMenu.add(menu_import_2d_Data);
		fileMenu.add(menu_import_3d_Data);
		fileMenu.add(menu_Exit);
		
		//创建数据菜单
		menu_create_1d_Data = new MenuItem("Create 1d Data");
		menu_create_2d_Data = new MenuItem("Create 2d Data");
		menu_create_3d_Data = new MenuItem("Create 3d Data");
		menu_show_Data = new MenuItem("Show Data");
		createMenu.add(menu_create_1d_Data);
		createMenu.add(menu_create_2d_Data);
		createMenu.add(menu_create_3d_Data);
		createMenu.add(menu_show_Data);
		
		//数据变换菜单
		menu_trans_DFT = new MenuItem("Fourier Transform");
		menu_trans_DCT = new MenuItem("Cosine Transform");
		menu_trans_WHT = new MenuItem("Walsh Transform");
		menu_trans_SLT = new MenuItem("Slant Transform");
		menu_trans_UST = new MenuItem("U-system Transform");
		menu_trans_VST = new MenuItem("V-system Transform");
		menu_trans_DWT = new MenuItem("Wavelet Transform");
		menu_trans_CED = new MenuItem("Candy Transform");
		transMenu.add(menu_trans_DFT);
		transMenu.add(menu_trans_DCT);
		transMenu.add(menu_trans_WHT);
		transMenu.add(menu_trans_SLT);
		transMenu.add(menu_trans_UST);
		transMenu.add(menu_trans_VST);
		transMenu.add(menu_trans_DWT);
		transMenu.add(menu_trans_CED);
		
		//帮助菜单
		menu_Help = new MenuItem("Help");
		menu_About = new MenuItem("About us");
		menu_About.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent ae) {
	  			JOptionPane.showMessageDialog(null,"Universal Digital Signal Process System\nEdition：1.0\nCopyright：梁延研 Macao University of Science and Technology","About",JOptionPane.NO_OPTION);
	  		}
	  	});	
		aboutMenu.add(menu_Help);
		aboutMenu.add(menu_About);
		
		//生成菜单项
		menu_bar.add(fileMenu);
		menu_bar.add(createMenu);
		menu_bar.add(transMenu);
		menu_bar.add(aboutMenu);	
		this.setMenuBar(menu_bar);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Quit();
			}
		});
		
		menu_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quit();
			}
		});

		menu_import_1d_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Load(1);
			}
		}); 
		
		menu_import_2d_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Load(2);
			}
		});
		
		menu_import_3d_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Load(3);
			}
		});
		
		menu_create_1d_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateData(1);
			}
		});
		
		menu_create_2d_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateData(2);
			}
		});
		
		menu_create_3d_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateData(3);
			}
		});	
		
		menu_show_Data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowData();
			}
		});	
		
		menu_trans_DFT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transform(1);
			}
		});
		
		menu_trans_DCT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transform(2);
			}
		});
		
		menu_trans_WHT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transform(3);
			}
		});
		
		menu_trans_SLT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transform(4);
			}
		});		
		
		menu_trans_UST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transform(5);
			}
		});
		
		menu_trans_VST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transform(6);
			}
		});
		
		menu_trans_DWT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transform(7);
			}
		});
		menu_trans_CED.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transform(8);
			}
		});
		
	}
	
	//装载信号数据
	public void Load(int dimension) {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int result = fileChooser.showOpenDialog(this);
		
		if(result == JFileChooser.CANCEL_OPTION) {
			return ;
		}
		
		File filename = fileChooser.getSelectedFile();
		filename.canRead();
		
		String fn = filename.getName();
		int index = fn.lastIndexOf('.');
		
		//文件名为空
		if (filename == null || fn.equals("")) {
			JOptionPane.showMessageDialog(fileChooser,"Invalid File Name",
			"Invalid File Name", JOptionPane.ERROR_MESSAGE);
		}
		// 表示文件名称不为".xxx"与"xxx."之类型
		else if(index > 0 && index < filename.length() - 1) {
			
			//得到文件后缀名
			String extension = fn.substring(index + 1).toLowerCase();
			String opfpath = filename.getPath();
				
			//读入一维数据
			if(dimension == 1) {
				
				try {
					int i = 0;	
					String j = "";
					il = 0;				
					FileInputStream fis = new FileInputStream(opfpath);
					DataInputStream dis = new DataInputStream(fis);
					while(true) {
						j = dis.readLine();
						i = Integer.parseInt(j);
						//获取数据长度il
						il++;
					}	
				}
				catch (Exception e) {
				}
				
				try {
					double i = 0;
					String j = "";
					pdata = new double[il];
					int k = 0;
					FileInputStream fis = new FileInputStream(opfpath);
					DataInputStream dis = new DataInputStream(fis);
					while(true) {
						j = dis.readLine();
						i = Double.parseDouble(j);
						pdata[k] = i;
						k++;
					}
					
				}	
				catch (Exception e) {
				}
				 		
				flagLoad = true;
				flagDimension = 1;
				repaint();
			
			}
			
			//读入二维数据
			else if(dimension == 2){
				
				//读入图像
				if(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("gif") || extension.equals("png")) {
					
					try {
										
						//利用MediaTracker跟踪图像的加载
						MediaTracker tracker = new MediaTracker(this);
						im = Toolkit.getDefaultToolkit().getImage(opfpath);
						tracker.addImage(im,0);
					
						//等待图像的完全加载
						try {
							tracker.waitForID(0);
				 		}
				 		catch(InterruptedException e2) { 
				 			e2.printStackTrace();
				 		}
				 		
				 		//获取图像的宽度iw和高度ih
						iw = im.getWidth(this);
						ih = im.getHeight(this);
						
						//获取图像的象素pixels
						pixels = new int[iw*ih];
						try	{
							PixelGrabber pg = new PixelGrabber(im,0,0,iw,ih,pixels,0,iw);
						pg.grabPixels();
						}
						catch (InterruptedException e3) {
							e3.printStackTrace();
						}
						
						flagLoad = true;
						flagDimension = 2;
						flagIm = true;
						repaint();
					}		
					catch (Exception e) {
					}
				}
				
				//读入其它二维数据
				else {
					
				}
			}
			
			//读入三维数据
			else if(dimension == 3) {
				
			}	
		}
	}
	
	//退出
	public void Quit() {
		
		//System.exit(0);
		JOptionPane op = new JOptionPane();
		int exit = op.showConfirmDialog(this,"Are you sure to exit?","Exit",JOptionPane.YES_NO_OPTION);
		
		if(exit == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		else {
		}
		
	}
	
	//生成数据
	public void CreateData(int dimension) {
		
		if(dimension == 1) {	
			cd1.show();
			flagDimension = 1;
			flagLoad = false;	
		}
		else {
		}
		
	}
	
	//显示生成数据
	public void ShowData() {
		
		if(flagDimension == 1) {		
			pdata = cd1.CreateData();
			il = cd1.il;
			flagLoad = true;
			repaint();
		}
		else {
		}
		
	}
	
	//数据变换
	public void Transform(int task) {
		
		if(flagLoad){
			
			//进行一维数据变换
			if(flagDimension == 1) {
					
				if (task == 1) {
					DataTransform datatrans = new DataTransform();
					datatrans.DFTTransform(pdata,il,flagDimension);
					datatrans.setTitle("Frequency Domain Image of Fourier Transform");
					datatrans.show();
				}	
				else if (task == 2) {
					DataTransform datatrans = new DataTransform();
					datatrans.DCTTransform(pdata,il,flagDimension);
					datatrans.setTitle("Frequency Domain Image of Cosine Transform");
					datatrans.show();
				}
				else if (task == 3) {
					DataTransform datatrans = new DataTransform();
					datatrans.WHTTransform(pdata,il,flagDimension);
					datatrans.setTitle("Frequency Domain Image of Walsh Transform");
					datatrans.show();
				}
				else if (task == 4) {
					DataTransform datatrans = new DataTransform();
					datatrans.SLTTransform(pdata,il,flagDimension);
					datatrans.setTitle("Frequency Domain Image of Slant Transform");
					datatrans.show();
				}
				else if (task == 5) {
					DataTransform datatrans = new DataTransform();
					datatrans.USTTransform(pdata,il,flagDimension);
					datatrans.setTitle("Frequency Domain Image of U-system Transform");
					datatrans.show();
				}
				else if (task == 6) {
					DataTransform datatrans = new DataTransform();
					datatrans.VSTTransform(pdata,il,flagDimension);
					datatrans.setTitle("Frequency Domain Image of V-system Transform");
					datatrans.show();
				}
				else if (task == 7) {
					DataTransform datatrans = new DataTransform();
					datatrans.DWTTransform(pdata,il,flagDimension);
					datatrans.setTitle("Frequency Domain Image of Wavelet Transform");
					datatrans.show();
				}					
			}

			//进行二维数据变换
			else if(flagDimension == 2) {
				
				//进行图像数据变换
				if(flagIm = true) {
					
					if (task == 1) {
						ImageTransform imagetrans = new ImageTransform();
						imagetrans.DFTTransform(pixels,iw,ih);
						imagetrans.setTitle("Frequency Domain Image of Fourier Transform");
						imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
						imagetrans.show();
					}	
					else if (task == 2) {
						ImageTransform imagetrans = new ImageTransform();
						imagetrans.DCTTransform(pixels,iw,ih);
						imagetrans.setTitle("Frequency Domain Image of Cosine Transform");
						imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
						imagetrans.show();
					}
					else if (task == 3) {
						ImageTransform imagetrans = new ImageTransform();
						imagetrans.WHTTransform(pixels,iw,ih);
						imagetrans.setTitle("Frequency Domain Image of Walsh Transform");
						imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
						imagetrans.show();
					}
					else if (task == 4) {
						ImageTransform imagetrans = new ImageTransform();
						imagetrans.SLTTransform(pixels,iw,ih);
						imagetrans.setTitle("Frequency Domain Image of Slant Transform");
						imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
						imagetrans.show();
					}
					else if (task == 5) {
						ImageTransform imagetrans = new ImageTransform();
						imagetrans.USTTransform(pixels,iw,ih);
						imagetrans.setTitle("Frequency Domain Image of U-system Transform");
						imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
						imagetrans.show();
					}
					else if (task == 6) {
						ImageTransform imagetrans = new ImageTransform();
						imagetrans.VSTTransform(pixels,iw,ih);
						imagetrans.setTitle("Frequency Domain Image of V-system Transform");
						imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
						imagetrans.show();
					}
					else if (task == 7) {
						ImageTransform imagetrans = new ImageTransform();
						imagetrans.DWTTransform(pixels,iw,ih);
						imagetrans.setTitle("Frequency Domain Image of Wavelet Transform");
						imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
						imagetrans.show();
					}	
					else if (task == 8) {					
//						CannyEdgeDetector detector = new CannyEdgeDetector();
//						detector.setLowThreshold(0.5f);
//						detector.setHighThreshold(1f);
//						//apply it to an image
//						detector.setSourceImage(convertImageToBuffer());
//						detector.process();
//						BufferedImage edges = detector.getEdgesImage();
						
						
						slider s = new slider(im);
						s.show();
						
//						ImageTransform imagetrans = new ImageTransform();
//						imagetrans.CEDTransform(im);
//						imagetrans.setTitle("Frequency Domain Image of Canny Transform");
//						imagetrans.setSize(imagetrans.w+50,imagetrans.h+100);
//						imagetrans.show();
						
					}			
				}
					
			}
			
			else if(flagDimension == 3) {
				
			}	
	
		}
		else {
			JOptionPane.showMessageDialog(null,"Please Import Data!",
		                 "Alert",JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	public void paint(Graphics g) {
		
		setBackground(Color.lightGray);

		//画一维数据图
		if(flagDimension == 1) {
			
			int[] iltemp = new int[il];
			int[] temppdata = new int[il];
			double temp1,temp2,temp3;
			int xline;
			
			if(flagLoad) {
				
				temp1 = 0;
				temp2 = 0;
				temp3 = 0;
				
				for(int i = 0; i < il; i++)
				{
					temp1 = pdata[i];
					if(temp1 > temp2){ 
						temp2 = temp1; 
					}
					
					if(temp1 < temp3){ 
						temp3 = temp1; 
					}	
				}
					
				//xline = 70+(int)((temp2*280)/(temp2-temp3));				
				xline = 360;
				
				for(int i = 0; i < il; i++)
				{
					//temppdata[i] = (int)((pdata[i]*280)/(temp2-temp3));
					temppdata[i] = (int)(((pdata[i]-temp3)*280)/(temp2-temp3));
					iltemp[i] = i*600/il;
				}
				
				//画出水平和垂直的轴
				g.drawLine(45,xline,645,xline);
				g.drawLine(45,70,45,360);
				
				//画出横轴坐标
				g.drawString("0",45,xline+15);
				
				String x1 = il/4+"";
				g.drawString(x1,193,xline+15);
				
				String x2 = il*2/4+"";
				g.drawString(x2,343,xline+15);
				
				String x3 = il*3/4+"";
				g.drawString(x3,493,xline+15);
				
				String x4 = il+"";
				g.drawString(x4,643,xline+15);
				
				/*
				//画出纵轴坐标
				int s1 = (int)temp2/4;
				String y1 = s1+"";
				g.drawString(y1,25,xline+6-(int)((s1*280)/(temp2-temp3)));
				
				int s2 = (int)temp2*2/4;
				String y2 = s2+"";
				g.drawString(y2,25,xline+6-(int)((s2*280)/(temp2-temp3)));
				
				int s3 = (int)temp2*3/4;
				String y3 = s3+"";
				g.drawString(y3,25,xline+6-(int)((s3*280)/(temp2-temp3)));
				
				int s4 = (int)temp2;
				String y4 = s4+"";
				g.drawString(y4,25,xline+6-(int)((s4*280)/(temp2-temp3))); 
				
				//支持负数
				int t1 = (int)temp3/4;
				String z1 = t1+"";
				g.drawString(z1,25,xline+6-(int)((t1*280)/(temp2-temp3)));
				
				int t2 = (int)temp3*2/4;
				String z2 = t2+"";
				g.drawString(z2,25,xline+6-(int)((t2*280)/(temp2-temp3)));
				
				int t3 = (int)temp3*3/4;
				String z3 = t3+"";
				g.drawString(z3,25,xline+6-(int)((t3*280)/(temp2-temp3)));
				
				int t4 = (int)temp3;
				String z4 = t4+"";
				g.drawString(z4,25,xline+6-(int)((t4*280)/(temp2-temp3))); 
				*/
				
				//画出纵轴坐标
				double s1 = 0.25;
				String y1 = s1+"";
				g.drawString(y1,20,xline+6-70);
				
				double s2 = 0.50;
				String y2 = s2+"";
				g.drawString(y2,20,xline+6-140);
				
				double s3 = 0.75;
				String y3 = s3+"";
				g.drawString(y3,20,xline+6-210);
				
				double s4 = 1.00;
				String y4 = s4+"";
				g.drawString(y4,20,xline+6-280); 
											
				for(int i = 1; i < il; i++)
				{
					g.drawLine(45+iltemp[i-1],xline-temppdata[i-1],45+iltemp[i],xline-temppdata[i]);
				}
				
				if(temppdata[0] > 0) {
					g.drawLine(42,xline-temppdata[0],45,xline+3-temppdata[0]);
					g.drawLine(45,xline+3-temppdata[0],48,xline-temppdata[0]);
					g.drawLine(48,xline-temppdata[0],45,xline-3-temppdata[0]);
					g.drawLine(45,xline-3-temppdata[0],42,xline-temppdata[0]);
				}
				
				for(int i = 0; i < il; i++)
				{
					g.drawLine(45+iltemp[i],xline,45+iltemp[i],xline-temppdata[i]);
				}
				
			}	
			
		}
		
		//画二维数据图
		else if(flagDimension == 2) {
			
			//画图像
			if(flagIm = true) {			
				if(flagLoad) {
					g.drawImage(im,25,70,this);
				}		
			}
			//画二维数据图
			else {
			}			
		}
		
		else if(flagDimension == 3) {
			
		}	

	}
	


	
	public static void main(String[] args) {

		DSP dsp = new DSP();
		dsp.setTitle("Universal Digital Signal Process System");
		dsp.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
					(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		dsp.setVisible(true);
		dsp.show();
	}
	
}