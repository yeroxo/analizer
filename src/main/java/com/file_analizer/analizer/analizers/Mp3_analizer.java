package com.file_analizer.analizer.analizers;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import org.springframework.stereotype.Component;

@Component
public class Mp3_analizer implements FileAnalizer {

    @Override
    public String analize(String method_alias, String file_path) {
        String answer = "Такого метода несуществует";
        Mp3File mp3file;
        try {
            mp3file = new Mp3File(file_path);
        }
        catch(Exception e){
            return "Что-то пошло не так";
        }

        if(method_alias.compareTo("1") == 0){
            answer = "Название трека: " + get_name_tag_of_audio(mp3file);
        }
        if(method_alias.compareTo("2") == 0){
            answer = "Длительность трека: " + get_length_of_audio(mp3file);
        }
        if(method_alias.compareTo("3") == 0){
            answer = get_information_of_audio(mp3file);
        }

        return answer;
    }

    public String get_name_tag_of_audio(Mp3File mp3file){
        if (mp3file.hasId3v1Tag()) {
            ID3v1 id3v1Tag = mp3file.getId3v1Tag();
            return id3v1Tag.getTitle();
        }
        if (mp3file.hasId3v2Tag()){
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            return id3v2Tag.getTrack();
        }
        return "Не верный формат mp3 файла";
    }

    public String get_length_of_audio(Mp3File mp3file){
        return Long.toString(mp3file.getLengthInSeconds());
    }

    public String get_information_of_audio(Mp3File mp3file){
        StringBuilder answer = new StringBuilder("Информация о треке: \n");
        if (mp3file.hasId3v1Tag()) {
            ID3v1 id3v1Tag = mp3file.getId3v1Tag();
            answer.append("Альбом: " + id3v1Tag.getAlbum() + "\n");
            answer.append("Артист: " + id3v1Tag.getArtist() + "\n");
            answer.append("Год: " + id3v1Tag.getYear() + "\n");
            answer.append("Комментарий: " + id3v1Tag.getComment() + "\n");
            return answer.toString();
        }
        if (mp3file.hasId3v2Tag()){
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            answer.append("Альбом: " + id3v2Tag.getAlbum() + "\n");
            answer.append("Артист: " + id3v2Tag.getArtist() + "\n");
            answer.append("Год: " + id3v2Tag.getYear() + "\n");
            answer.append("Комментарий: " + id3v2Tag.getComment() + "\n");
            return answer.toString();
        }
        return "Не верный формат mp3 файла";
    }
}
