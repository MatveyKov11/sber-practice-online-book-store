package com.example.book_store;

import com.example.book_store.controllers.AdminController;
import com.example.book_store.entities.*;
import com.example.book_store.data.Genre;
import com.example.book_store.data.Language;
import com.example.book_store.services.BookStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BookStoreApplication implements WebMvcConfigurer {

    private static final Logger log = LoggerFactory.getLogger(BookStoreApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataTester(BookStoreService serv){
        return args -> {
            //admin: login="a#_admin" password="admin"
            serv.saveEmployee(new Employee(0, "e#_ivan", "12345", new UserEntity()));
            serv.saveEmployee(new Employee(0, "e#_danya", "12345", new UserEntity()));
            serv.saveEmployee(new Employee(0, "e#_qqq", "12345", new UserEntity()));

            serv.saveClient(new ClientEntity(0, "client1", "pass1", "Ivan",
                    "Ivanovich", "Ivanov", "email@email.ru",
                    "88005553535", new Date(), 0, 0, null));
            serv.saveClient(new ClientEntity(0, "client2", "pass2", "Ivan",
                    "Ivanovich", "Smirnov", "email@email.ru",
                    "88005553535", new Date(), 0, 0, null));
            serv.saveClient(new ClientEntity(0, "client3", "pass3", "Danya",
                    "Ivanovich", "Dmitriev", "email@email.ru",
                    "88005553535", new Date(), 0, 0, null));

            serv.saveBook(new Book(0, "Медный всадник", 250, 10,
                    "https://cdn1.ozone.ru/s3/multimedia-1/c600/6563281741.jpg", "Пушкин А. С.", Language.RUSSIAN,
                    1833, Genre.CLASSIC, "Была написана в Болдине осенью 1833 года. Поэма не была разрешена Николаем I к печати.",
                    "", 70, 8, false));
            serv.saveBook(new Book(0, "Идиот", 250, 10,
                    "https://cdn1.ozone.ru/s3/multimedia-4/6552320068.jpg", "Достоевский Ф. М.", Language.RUSSIAN,
                    1869, Genre.CLASSIC,
                    "Замысел романа обдумывался писателем во время пребывания за границей — в Германии и Швейцарии.",
                    "", 190, 9, false));
            serv.saveBook(new Book(0, "Тихий Дон", 250, 10,
                    "https://cdn1.ozone.ru/s3/multimedia-1-f/7043067411.jpg", "Шолохов М. А.", Language.RUSSIAN,
                    1928, Genre.CLASSIC,
                    "Тома 1—3-й написаны с 1925 по 1932 год, опубликованы в журнале «Октябрь» в 1928—1932 " +
                            "годах. Том 4-й был написан в 1932 году, закончен в 1940 году.",
                    "", 350, 7, false));
            serv.saveBook(new Book(0, "Понедельник начинается в субботу", 250, 10,
                    "https://omskbook.ru/image/cache/catalog/products/9785170903344/0-700x700.jpeg",
                    "Братья Стругацкие", Language.RUSSIAN,
                    1965, Genre.SCI_FI,
                    "Фантастическая юмористическая повесть братьев Стругацких, " +
                            "одно из наиболее своеобразных воплощений советской утопии 1960-х годов",
                    "", 130, 8, false));

            ClientEntity c1 = serv.getClientEntity("Smirnov");
            ClientEntity c2 = serv.getClientEntity("Ivanov");
            Book b1 = serv.getBook("Медный всадник");
            Book b2 = serv.getBook("Идиот");
            Book b3 = serv.getBook("Тихий Дон");
            Book b4 = serv.getBook("Понедельник начинается в субботу");
            List<Book> lb1 = new ArrayList<>();
            lb1.add(b1);
            lb1.add(b2);
            lb1.add(b3);
            List<Book> lb2 = new ArrayList<>();
            lb2.add(b4);
            serv.newOrder(new BookOrder(0, c1, lb1, new Date(), 750, true));
            serv.newOrder(new BookOrder(0, c2, lb2, new Date(), 250, true));
        };
    }

}
