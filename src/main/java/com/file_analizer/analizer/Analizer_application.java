package com.file_analizer.analizer;

import com.file_analizer.analizer.analizers.File_analizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@SpringBootApplication
public class Analizer_application {

	private Map<String, File_analizer> analizers;

	private String path;

	private String file_extention;

	private File_analizer analizer;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		Analizer_application analizer = (Analizer_application) ctx.getBean("Analizer_application");
		System.out.println(analizer.analize());
	}

	public Analizer_application(){}

	public Analizer_application(Map<String, File_analizer> analizers){
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
			analizer = analizers.get(file_extention);
			method_alias = reader.readLine();
		}

		catch(IOException e) {
			System.out.println("Ошибка");
			return null;
		}

		answer = analizer.analize(method_alias, path);
		return answer;
	}
}
