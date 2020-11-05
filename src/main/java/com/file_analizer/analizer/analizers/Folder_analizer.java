package com.file_analizer.analizer.analizers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Folder_analizer implements File_analizer {

    @Override
    public String analize(String method_alias, String file_path) {
        String answer = "Такого метода несуществует";
        File directory = new File(file_path);

        if(method_alias.compareTo("1") == 0){
            answer = file_from_folder_output(directory);
        }
        if(method_alias.compareTo("2") == 0){
            answer = "Размер файлов в директории: " + file_size_of_folder(directory) + "bytes";
        }
        if(method_alias.compareTo("3") == 0){
            answer = folder_analize(directory);
        }

        return answer;
    }

    public String file_from_folder_output(File directory){
        StringBuilder answer = new StringBuilder("Файлы в каталоге \n");

        if (directory.isDirectory()){
            for (File file : directory.listFiles()) {
                if(file.isFile()){
                    answer.append(file.getName() + "\n");
                }
            }
        }
        else{
            return "На вход подана не директория";
        }

        return answer.toString();
    }

    public String file_size_of_folder(File directory){
        int size_in_bytes = 0;

        if (directory.isDirectory()){
            for (File file : directory.listFiles()) {
                if(file.isFile()){
                    size_in_bytes += file.length();
                }
            }
        }
        else{
            return "На вход подана не директория";
        }

        return Integer.toString(size_in_bytes);
    }

    public String folder_analize(File directory){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder("Файлы в каталоге \n");

        System.out.println("Введите расширение файла");
        String extension;
        try {
            extension = reader.readLine();
        } catch (IOException e) {
            return "Что-то пошло не так";
        }
        if (directory.isDirectory()){
            for (File file : directory.listFiles()) {
                if(file.isFile()){
                    String name = file.getName();
                    int index = name.indexOf('.');
                    if(extension.compareTo(name.substring(index + 1)) == 0){
                        answer.append(file.getName() + "\n");
                    }
                }
            }
        }
        else{
            return "На вход подана не директория";
        }

        return answer.toString();
    }
}
