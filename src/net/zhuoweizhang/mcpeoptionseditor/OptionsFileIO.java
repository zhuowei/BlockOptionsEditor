package net.zhuoweizhang.mcpeoptionseditor;

import java.io.*;

import java.util.*;

import android.os.Environment;

public class OptionsFileIO {

	public static Map<String, String> readOptionsFile() throws IOException {
		File propFile = new File(Environment.getExternalStorageDirectory(), "games/com.mojang/minecraftpe/options.txt");
		return readOptionsFile(propFile);
	}

	public static Map<String, String> readOptionsFile(File propFile) throws IOException {
		FileInputStream instr = new FileInputStream(propFile);
		Scanner scan = new Scanner(instr);
		Map<String, String> map = new HashMap<String, String>();
		while(scan.hasNext()) {
			String line = scan.nextLine();
			String[] lineSplit = line.split(":");
			map.put(lineSplit[0], lineSplit[1]);
		}
		scan.close();
		return map;
	}

	public static void writeOptionsFile(Map<String, String> map) throws IOException {
		File propFile = new File(Environment.getExternalStorageDirectory(), "games/com.mojang/minecraftpe/options.txt");
		writeOptionsFile(map, propFile);
	}

	public static void writeOptionsFile(Map<String, String> map, File propFile) throws IOException {
		PrintWriter outwriter = new PrintWriter(propFile);
		for (Map.Entry<String, String> e : map.entrySet()) {
			outwriter.printf("%s:%s\n", e.getKey(), e.getValue());
		}
		outwriter.close();
	}
}
