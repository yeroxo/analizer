package com.file_analizer.analizer.analizers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Component
public class Txt_analizer implements FileAnalizer {
    @Override
    public String analize(String method_alias, String file_path) {
        String answer = "Такого метода несуществует";
        StringBuilder text_content = new StringBuilder();
        Scanner scanner;
        int row_count = 0;

        try {
            File file = new File(file_path);
            scanner = new Scanner(file);
        }
        catch (FileNotFoundException e){ return "Файл не найден";}

        while (scanner.hasNextLine()){
            text_content.append(scanner.nextLine());
            row_count ++;
        }

        if(method_alias.compareTo("1") == 0){
            answer = "Количество строк: " + row_count;
        }
        if(method_alias.compareTo("2") == 0){
            answer = symbol_frequency(text_content.toString());
        }
        if(method_alias.compareTo("3") == 0){
            answer = "Частотность фразы: " + fraze_frequency(text_content.toString());
        }

        return answer;
    }

    public String symbol_frequency(String line){
        StringBuilder answer = new StringBuilder();

        Map<Character,Integer> frequencies = new HashMap<>();
        for (char ch : line.toCharArray())
            frequencies.put(ch, frequencies.getOrDefault(ch, 0) + 1);

        for (Map.Entry<Character, Integer> entry: frequencies.entrySet())
            answer.append("Символ: " + entry.getKey() + " Вхождения: " + entry.getValue() + "\n");
        return answer.toString();
    }

    public String fraze_frequency(String line){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите фразу");
        String fraze;
        try {
            fraze = reader.readLine();
        } catch (IOException e) {
            return "Что-то пошло не так";
        }
        int occurrence = StringUtils.countMatches(line, fraze);
        return Integer.toString(occurrence);
    }
}
