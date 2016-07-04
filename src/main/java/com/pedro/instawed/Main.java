package com.pedro.instawed;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {

	private static String data = "";
	private final static String uri = "https://www.instagram.com/explore/tags/snow/";
	private static final String imagesPath = new File("src/main/resources/images/").getAbsolutePath();
	private static final String filePath = new File("src/main/resources/files/").getAbsolutePath();
	
	public static void main(String[] args) {
		
		Document document;
		JSONObject jsonObj;
		JSONArray jsonAr;
		
		try {
			document = Jsoup.connect(uri).get();
			
			System.out.println("Connecting and fetching html...");
			Elements el = document.getElementsByTag("script");
			System.out.println("Closing connection");
			
			System.out.println("Element data");
			data = el.get(6).data().split(" = ")[1];
			jsonObj = new JSONObject(data);
			jsonObj = jsonObj.getJSONObject("entry_data");
			jsonAr = jsonObj.getJSONArray("TagPage");
			jsonObj = jsonAr.getJSONObject(0);
			jsonObj = jsonObj.getJSONObject("tag");
			jsonObj = jsonObj.getJSONObject("media");
			jsonAr = jsonObj.getJSONArray("nodes");
			jsonObj = jsonAr.getJSONObject(0);
			System.out.println("code: " + jsonObj.get("code") + " \nsrc: " + jsonObj.get("display_src"));
			
			System.out.println("Download photo");
			URL url = new URL("https://igcdn-photos-b-a.akamaihd.net/hphotos-ak-xaf1/t51.2885-15/e35/13129353_1789718211314833_190502334_n.jpg?ig_cache_key=MTI0NzQ1MTg1MTY2MTE1OTA5Mw%3D%3D.2");
			System.out.println(imagesPath);
			List<String> ids = loadIds();
			saveImage(url, imagesPath + "/teste.jpg");
			System.out.println("File: " + "teste.jpg" + " saved!");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException j) {
			j.printStackTrace();
		}
	}

	private static void saveImage(URL url, String fileName){
		InputStream in;
		try {
			in = new BufferedInputStream(url.openStream());
			OutputStream out = new BufferedOutputStream(new FileOutputStream(fileName));

			for ( int i; (i = in.read()) != -1; ) {
				out.write(i);
			}

			in.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static List<String> loadIds(){
		
		String line = null;
		List<String> list = new ArrayList<String>();
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(filePath + "/images-ids.txt"));
			while ((line = input.readLine()) != null){
				list.add(line);
			}
			input.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
