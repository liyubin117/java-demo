package com.serial;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.pubtest.Student;

public class FileMgr {

	public static void main(String[] args) throws Exception {
		//属性文件（一个键值对代表一个属性，键值间由=或:分隔，注释以#或!开头，但不能直接处理中文，在配置文件中，所有非ASCII字符需要使用Unicode编码）处理
		Properties prop=new Properties();
		prop.load(new FileInputStream("file"+File.separator+"config.properties"));
		System.out.println("端口："+prop.getProperty("db.port", "8888"));
		System.out.println("描述："+prop.getProperty("db.desc"));
		
		//csv文件（逗号分隔值）
		//使用分号;作为分隔符，"作为引号符，使用N/A表示null对象，忽略字段之间的空白
		//读取
		CSVFormat format = CSVFormat.newFormat(';')
		        .withQuote('"').withNullString("N/A")
		        .withIgnoreSurroundingSpaces(true);
		Reader reader = new FileReader("file"+File.separator+"student.csv");
		try{
		    for(CSVRecord record : format.parse(reader)){
		        int fieldNum = record.size();
		        for(int i=0; i<fieldNum; i++){
		            System.out.print(record.get(i)+" ");
		        }
		        System.out.println();
		    }
		}finally{
		    reader.close();
		}
		//写入
		CSVPrinter out = new CSVPrinter(new FileWriter("file"+File.separator+"out.csv"),
		        CSVFormat.DEFAULT);
		out.printRecord("老马", 18, "看电影,看书,听音乐");
		out.printRecord("小马", 16, "乐高;赛车;");
		out.close();
		
		//Excel文件
		List<Student> ls=Arrays.asList(
				new Student("李宇彬",26,9999),
				new Student("叶兴萍",24,8888),
				new Student("李",10,999)
				);
		String file="file"+File.separator+"students.xls";
		//写入
		saveAsExcel(ls,file);
		//读取
		System.out.println(Arrays.toString(readAsExcel(file).toArray()));
		
		//HTML文件
		//解析出其中的文章和链接
		//通过文件解析
		Document doc = Jsoup.parse(new File("file"+File.separator+"articles.html"), "UTF-8");
		//通过url解析
		String url = "http://www.cnblogs.com/swiftma/p/5631311.html";
		doc = Jsoup.connect(url).get();
		Elements elements = doc.select("#cnblogs_post_body p a");
		for(Element e : elements){
		    String title = e.text();
		    String href = e.attr("href");
		    System.out.println(title+", "+href);
		}
	}

	//将List对象保存为excel文件
	public static void saveAsExcel(List<Student> list,String file) throws IOException {
		//xls对象
	    Workbook wb = new HSSFWorkbook();
	    //xlsx对象
	    //Workbook wb = new XSSFWorkbook();
	    //sheet页对象
	    Sheet sheet = wb.createSheet();
	    for (int i = 0; i < list.size(); i++) {
	        Student student = list.get(i);
	        //行对象
	        Row row = sheet.createRow(i);
	        //字段对象
	        row.createCell(0).setCellValue(student.getName());
	        row.createCell(1).setCellValue(student.getAge());
	        row.createCell(2).setCellValue(student.getScore());
	    }
	    OutputStream out = new FileOutputStream(file);
	    wb.write(out);
	    out.close();
	    wb.close();
	}
	
	//将excel文件解析为List对象
	public static List<Student> readAsExcel(String file) throws Exception  {
	    Workbook wb = WorkbookFactory.create(new File(file));
	    List<Student> list = new ArrayList<Student>();
	    for(Sheet sheet : wb){
	        for(Row row : sheet){
	            String name = row.getCell(0).getStringCellValue();
	            int age = (int)row.getCell(1).getNumericCellValue();
	            double score = row.getCell(2).getNumericCellValue();
	            list.add(new Student(name, age, score));
	        }
	    }    
	    wb.close();
	    return list;
	}

}
