package com.leo.kang.tools;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
	    showAllFiles(root);
	    for (File file : fileList) {
		// System.out.println(file.getAbsolutePath());
		readFileByLine(file);
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    final static void showAllFiles(File dir) throws Exception {
	File[] fs = dir.listFiles();
	if (fileList == null) {
	    fileList = new ArrayList<File>();
	}
	String path = "";
	for (int i = 0; i < fs.length; i++) {
	    path = fs[i].getAbsolutePath();
	    // System.out.println(path);
	    if (!fs[i].isDirectory()
		    && path.substring(path.lastIndexOf(".")).contains("java")) {
		fileList.add(fs[i]);
	    }
	    // System.out.println(fs[i].getAbsolutePath());
	    if (fs[i].isDirectory()) {
		try {
		    showAllFiles(fs[i]);
		} catch (Exception e) {
		}
	    }
	}
    }

    public static void readFileByLine(File file) {
	try {
	    // read file content from file
	    StringBuffer sb = new StringBuffer("");

	    FileReader reader = new FileReader(file.getAbsoluteFile());
	    BufferedReader br = new BufferedReader(reader);

	    String str = null;

	    int line = 0;
	    while ((str = br.readLine()) != null) {
		line++;
		if (str.contains("Log.i(") || str.contains("Log.d(")
			|| str.contains("Log.w(") || str.contains("Log.v(")
			|| str.contains("Log.e(")) {
		    sb.append(file.getAbsolutePath().substring(
			    file.getAbsolutePath().lastIndexOf("\\") + 1)
			    + "  line " + line + "  " + str.trim() + "/n");

		    System.out.println(file.getAbsolutePath().substring(
			    file.getAbsolutePath().lastIndexOf("\\") + 1)
			    + "  line " + line + "  " + str.trim());
		}
	    }

	    br.close();
	    reader.close();

	    // write string to file
	    FileWriter writer = new FileWriter("c://test2.txt");
	    BufferedWriter bw = new BufferedWriter(writer);
	    bw.write(sb.toString());

	    bw.close();
	    writer.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}