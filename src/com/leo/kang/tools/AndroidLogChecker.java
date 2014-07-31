package com.leo.kang.tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AndroidLogChecker {
    static List<File> fileList;

    public static void main(String[] args) {
	fileList = new ArrayList<File>();
	// 递归显示D:test\\src下所有文件夹及其中文件
	File root = new File("D:test\\src");
	try {
	    getAllFiles(root);
	    for (File file : fileList) {
		readFileByLine(file);
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Get all files
     * @author: yi.kang
     * @date: 2014年7月31日 下午8:05:25
     * @param dir
     * @throws Exception
     */
    final static void getAllFiles(File dir) throws Exception {
	File[] fs = dir.listFiles();
	if (fileList == null) {
	    fileList = new ArrayList<File>();
	}
	String path = "";
	for (int i = 0; i < fs.length; i++) {
	    path = fs[i].getAbsolutePath();
	    if (!fs[i].isDirectory()
		    && path.substring(path.lastIndexOf(".")).contains("java")) {
		fileList.add(fs[i]);
	    }
	    if (fs[i].isDirectory()) {
		try {
		    getAllFiles(fs[i]);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}
    }

    /**
     * Read file line by line
     * @author: yi.kang
     * @date: 2014年7月31日 下午8:06:20
     * @param file
     */
    public static void readFileByLine(File file) {
	try {
	    // read file content from file
	    //StringBuffer sb = new StringBuffer("");
	    String fileName = file.getAbsolutePath();
	    FileReader reader = new FileReader(fileName);
	    BufferedReader br = new BufferedReader(reader);

	    String str = null;

	    int line = 0;
	    
	    while ((str = br.readLine()) != null) {
		line++;
		if (str.contains("Log.i(") 
			|| str.contains("Log.d(")
			|| str.contains("Log.w(") 
			|| str.contains("Log.v(")
			|| str.contains("Log.e(")) {
		    // sb.append(fileName.substring(fileName.lastIndexOf("\\") +
		    // 1)
		    // + "  line " + line + "  " + str.trim() + "/n");

		    System.out.println(file.getAbsolutePath().substring(
			    file.getAbsolutePath().lastIndexOf("\\") + 1)
			    + "  line " + line + "  " + str.trim());
		}
	    }

	    br.close();
	    reader.close();

	    // write string to file
	    //FileWriter writer = new FileWriter("c://test2.txt");
	    //BufferedWriter bw = new BufferedWriter(writer);
	    //bw.write(sb.toString());

	    //bw.close();
	    //writer.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}