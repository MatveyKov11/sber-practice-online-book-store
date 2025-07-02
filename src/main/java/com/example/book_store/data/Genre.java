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
}
