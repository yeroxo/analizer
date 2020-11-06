package com.file_analizer.analizer.analizers;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;

//C:\Users\onohite\Desktop\lmvlYhIJaUw.jpg
//

@Component
public class Img_analizer implements FileAnalizer {

    @Override
    public String analize(String method_alias, String file_path) {
        Metadata metadata;
        String answer = "Такого метода несуществует";

        try {
            metadata = ImageMetadataReader.readMetadata(new File(file_path));
        } catch (Exception e) {
            return "Что-то пошло не так";
        }
        if(method_alias.compareTo("1") == 0){
            answer = img_size(metadata);
        }
        if(method_alias.compareTo("2") == 0){
            answer = exif_information(metadata);
        }
        if(method_alias.compareTo("3") == 0){
            answer = full_image_information(metadata);
        }

        return answer;
    }

    public String img_size(Metadata metadata){
        StringBuilder answer = new StringBuilder("Размер изображения: \n" );
        int checker = 0;
        ArrayList<Directory> directories = (ArrayList<Directory>) metadata.getDirectories();
        for (Tag tag : directories.get(0).getTags()){
            if(checker == 2 || checker == 3)
                answer.append(tag.getTagName() + " - " + tag.getDescription() + "\n");
            if(checker > 3)
                break;
            checker++;
        }

        return answer.toString();
    }

    public String exif_information(Metadata metadata){
        StringBuilder answer = new StringBuilder("Информация exif \n" );

        ArrayList<Directory> directories = (ArrayList<Directory>) metadata.getDirectories();
        for (Tag tag : directories.get(2).getTags()){
            answer.append(tag.getTagName() + " : " + tag.getDescription() + "\n");
        }

        return answer.toString();
    }

    public String full_image_information(Metadata metadata){
        StringBuilder answer = new StringBuilder("Информация о изображении: \n" );
        ArrayList<Directory> directories = (ArrayList<Directory>) metadata.getDirectories();
        for (Tag tag : directories.get(0).getTags()){
                answer.append(tag.getTagName() + " - " + tag.getDescription() + "\n");
        }
        return answer.toString();
    }
}
