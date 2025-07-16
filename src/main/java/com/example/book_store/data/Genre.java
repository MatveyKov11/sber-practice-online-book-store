package com.example.book_store.data;

public enum Genre {
    CLASSIC, SCI_FI, FANTASY, NONFICTION, FICTION, COMICS, MANGA, NONE;

    @Override
    public String toString(){
        switch (this){
            case CLASSIC -> {
                return "Классическая литература";
            }
            case SCI_FI -> {
                return "Научная Фантастика";
            }
            case FANTASY -> {
                return "Фэнтези";
            }
            case NONFICTION -> {
                return "Научно-популярная литература";
            }
            case FICTION -> {
                return "Художественная литература";
            }
            case COMICS -> {
                return "Комикс";
            }
            case MANGA -> {
                return "Манга";
            }
            default -> {
                return "жанра нет";
            }
        }
    }

    public static Genre fromId(int id){
        switch (id){
            case 1: return CLASSIC;
            case 2: return SCI_FI;
            case 3: return FANTASY;
            case 4: return NONFICTION;
            case 5: return FICTION;
            case 6: return COMICS;
            case 7: return MANGA;
            default: return NONE;
        }
    }

    public static Genre fromString(String id){
        switch (id){
            case "Классическая литература": return CLASSIC;
            case "Научная Фантастика": return SCI_FI;
            case "Фэнтези": return FANTASY;
            case "Научно-популярная литература": return NONFICTION;
            case "Художественная литература": return FICTION;
            case "Комикс": return COMICS;
            case "Манга": return MANGA;
            default: return NONE;
        }
    }
}
