package com.example.book_store.data;

public enum Language {
    RUSSIAN, ENGLISH, GERMAN, NONE;

    @Override
    public String toString(){
        switch (this){
            case RUSSIAN -> {
                return "Русский";
            }
            case ENGLISH -> {
                return "Английский";
            }
            case GERMAN -> {
                return "Немецкий";
            }
            default -> {
                return "нет информации о языке";
            }
        }
    }

    public static Language fromId(int id){
        switch (id){
            case 1: return RUSSIAN;
            case 2: return ENGLISH;
            case 3: return GERMAN;
            default: return NONE;
        }
    }

    public static Language fromString(String str){
        switch (str){
            case "Русский": return RUSSIAN;
            case "Английский": return ENGLISH;
            case "Немецкий": return GERMAN;
            default: return NONE;
        }
    }
}
