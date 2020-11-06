package com.file_analizer.analizer;

import com.file_analizer.analizer.analizers.FileAnalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Analizer_application implements CommandLineRunner {

	private Map<String, FileAnalizer> analizers;

	private String path;

	private String file_extention;

	private FileAnalizer analizer;

	List<String> ExtTyper = List.of("img_analizer","mp3_analizer","txt_analizer","folder_analizer");


	public static void main(String[] args) {
		SpringApplication.run(Analizer_application.class);
	}

	public Analizer_application(){}

	@Autowired
	public Analizer_application(Map<String, FileAnalizer> analizers){
		this.analizers = analizers;
	}

	public String analize() {
		String method_alias;
		String answer;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Введите путь к файлу");

		try {
			path = reader.readLine();
			File file = new File(path);
			int index = path.indexOf('.');
			if(file.exists()) {
				if(index < 0)
					file_extention="folder";
				else file_extention = path.substring(index + 1);
				System.out.println(file_extention);
			}
			else throw new IOException();

			System.out.println("Введите номер метода");
			if (file_extention.equals("jpg") || file_extention.equals("png")){
				analizer = analizers.get(ExtTyper.get(0));
			}
			else if (file_extention.equals("mp3")){
				analizer = analizers.get(ExtTyper.get(1));
			}
			else if (file_extention.equals("txt")){
				analizer = analizers.get(ExtTyper.get(2));
			}
			else if(file_extention.equals("folder")){
				analizer = analizers.get(ExtTyper.get(3));
			}
			method_alias = reader.readLine();
		}

		catch(IOException e) {
			System.out.println("Ошибка");
			return null;
		}

		answer = analizer.analize(method_alias, path);
		return answer;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(analize());
	}
}
