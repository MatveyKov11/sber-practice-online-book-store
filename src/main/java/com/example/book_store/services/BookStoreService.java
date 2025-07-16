package com.example.book_store.services;

import com.example.book_store.controllers.AdminController;
import com.example.book_store.data.*;
import com.example.book_store.entities.*;
import com.example.book_store.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookStoreService {
    private EmployeeRepository employeeRep;
    private BookRepository bookRep;
    private ClientRepository clientRep;
    private BookOrderRepository bookOrderRep;
    private UserRepository userRep;
    private PasswordEncoder encoder;

    private <T> List<T> toList(Iterable<T> iter){
        List<T> list = new ArrayList<>();
        iter.forEach(item -> list.add(item));
        return list;
    }

    @Autowired
    public BookStoreService(EmployeeRepository rep1, BookRepository rep2, ClientRepository rep3,
                            BookOrderRepository rep4, UserRepository rep5, PasswordEncoder encoder){
        this.employeeRep = rep1;
        this.bookRep = rep2;
        this.clientRep = rep3;
        this.bookOrderRep = rep4;
        this.userRep = rep5;
        this.encoder = encoder;
        UserEntity admin = new UserEntity();
        admin.setUsername("a#_admin");
        admin.setPassword(encoder.encode("admin"));
        userRep.save(admin);
    }

    public Client toClient(ClientEntity client){
        LocalDate now = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date = client.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return new Client(client.getId(), client.getLogin(), client.getFirstName() + " " + client.getLastName(),
                client.getEmail(), Period.between(now, date).getYears(), client.getOrdersCnt(), client.getIdLastOrder());
    }

    public List<Client> getClients(AdminController.Filter filter){
        String str = filter.getStr();
        Iterable<ClientEntity> it = clientRep.findAll();
        List<Client> list = new ArrayList<>();
        if(str.equals("")){
            it.forEach(item -> list.add(toClient(item)));
        }else {
            it.forEach(item -> {
                if(item.getLogin().contains(str) || item.getFirstName().contains(str) ||
                        item.getLastName().contains(str)) {
                    list.add(toClient(item));
                }
            });
        }
        return list;
    }

    public Client getClient(int id){
        return toClient(clientRep.findById(Integer.valueOf(id)).orElse(null));
    }

    public ClientEntity getClientEntity(int id){
        return clientRep.findById(Integer.valueOf(id)).orElse(null);
    }

    public ClientEntity getClientEntity(String lastName){
        return clientRep.findByLastName(lastName);
    }

    public ClientEntity getClientEntity(UserEntity user){
        return clientRep.findByUser(user);
    }

    public void saveClient(ClientEntity client){
        UserEntity c = new UserEntity();
        c.setUsername(client.getLogin());
        c.setPassword(encoder.encode(client.getPassword()));
        userRep.save(c);
        client.setUser(userRep.findByUsername(c.getUsername()));
        client.setOrdersCnt(0);
        client.setIdLastOrder(-1);
        clientRep.save(client);
    }

    public int updateClient(ClientEntity clientE){
        ClientEntity c = clientRep.findById(Integer.valueOf(clientE.getId())).orElse(null);
        if(c == null){
            return -1;
        }else {
            userRep.update(c.getUser().getId(), encoder.encode(clientE.getPassword()));
            userRep.updateLogin(c.getUser().getId(), clientE.getLogin());
            clientRep.updateFull(clientE.getId(), clientE.getLogin(), clientE.getPassword(),
                    clientE.getFirstName(), clientE.getMiddleName(), clientE.getLastName(),
                    clientE.getEmail(), clientE.getPhoneNumber(), clientE.getDateOfBirth());
            return 0;
        }
    }

    public int updateClient(Client client){
        clientRep.updateOrder(client.getId(), client.getOrdersCnt(), client.getIdLastOrder());
        return 0;
    }

    public List<Employee> getEmployees(){
        return toList(employeeRep.findAll());
    }

    public void saveEmployee(Employee employee){
        if(!employee.getLogin().startsWith("e#_")){
            employee.setLogin("e#_"+employee.getLogin());
        }
        UserEntity e = new UserEntity();
        e.setUsername(employee.getLogin());
        e.setPassword(encoder.encode(employee.getPassword()));
        userRep.save(e);
        employee.setUser(userRep.findByUsername(e.getUsername()));
        employeeRep.save(employee);
    }

    public void updateEmployee(Employee employee){
        if(!employee.getLogin().startsWith("e#_")){
            employee.setLogin("e#_"+employee.getLogin());
        }
        Employee e = employeeRep.findByLogin(employee.getLogin());
        if(e == null){
            return;
        }
        userRep.update(e.getUser().getId(), encoder.encode(employee.getPassword()));
        employeeRep.update(employee.getLogin(), employee.getPassword());
    }

    public void deleteEmployee(Employee employee){
        if(!employee.getLogin().startsWith("e#_")){
            employee.setLogin("e#_"+employee.getLogin());
        }
        List<Employee> e = employeeRep.findAllByLogin(employee.getLogin());
        if(e.size() == 1){
            employeeRep.deleteByLogin(employee.getLogin());
            userRep.delete(e.getFirst().getUser());
        }
    }

    public void updateAdmin(UserEntity user){
        user.setUsername("a#_admin");
        user.setPassword(encoder.encode(user.getPassword()));
        userRep.update(user.getId(), user.getPassword());
    }

    public List<Book> getBooks(){
        return toList(bookRep.findAll());
    }

    public Book getBook(int id){
        return bookRep.findById(id).orElse(new Book());
    }

    public Book getBook(String title){
        return bookRep.findByTitle(title);
    }

    public void saveBook(Book book){
        if(book.getUrlPicture().equals("")){
            book.setUrlPicture("https://avatars.mds.yandex.net/i?id=569d39c7cebd6489bc2019e681a1844d1fbbc37e-4589723-images-thumbs&n=13");
        }
        if(book.getIsbn().equals("")){
            book.setIsbn("xxx-x-xxxxx-xxx-x");
        }
        if(book.getAuthor().equals("")){
            book.setAuthor("Неизвестный");
        }
        bookRep.save(book);
    }

    public void updateBook(Book book){
        bookRep.update(book.getId(), book.getTitle(), book.getCost(),
                book.getCount(), book.getUrlPicture(), book.getAuthor(),
                book.getLanguage(), book.getYear_(), book.getGenre(),
                book.getDescription(), book.getIsbn(), book.getPageCnt(),
                book.getRate(), book.isRecency());
    }

    public List<BookOrder> getOrders(){
        return toList(bookOrderRep.findAll());
    }

    public List<BookOrder> getOrders(String clientLogin){
        return bookOrderRep.findAllByClient_Login(clientLogin);
    }

    public List<BookOrder> getOrders(int clientId){
        return bookOrderRep.findAllByClient_Id(clientId);
    }

    public List<BookOrder> getOrders(AdminController.Filter filter){
        String str = filter.getStr();
        if(str.equals("")){
            return toList(bookOrderRep.findAll());
        }else {
            return bookOrderRep.findAllByClient_Login(str);
        }
    }

    public List<BookOrder> getOrders(UserEntity user){
        ClientEntity ce = getClientEntity(user);
        if(ce == null){
            return new ArrayList<>();
        }else {
            return bookOrderRep.findAllByClient(ce);
        }
    }

    public BookOrder getOrder(int id){
        return bookOrderRep.findById(id).orElse(null);
    }

    public BookOrder getCart(UserEntity user){
        ClientEntity ce = getClientEntity(user);
        if(ce == null){
            return new BookOrder();
        }else {
            if(ce.getIdLastOrder() != -1){
                return getOrder(ce.getIdLastOrder());
            }else{
                BookOrder bo = new BookOrder();
                bo.setDate(new Date());
                bo.setClient(ce);
                bo.setBooks(new ArrayList<>());
                bo.setDone(false);
                bo.setTotalCost(0);
                bookOrderRep.save(bo);
                bo = bookOrderRep.findByClientAndDone(ce, false);
                clientRep.updateOrder(ce.getId(), ce.getOrdersCnt()+1, bo.getId());
                return bo;
            }
        }
    }

    public void addToCart(UserEntity user, int bookId){
        BookOrder bo = getCart(user);
        Book b = bookRep.findById(bookId).orElse(null);
        if(b != null){
            b.setCount(b.getCount()-1);
            updateBook(b);
            List<Book> bArr = bo.getBooks();
            bArr.add(b);
            //bookOrderRep.updateList(bo.getId(), bArr);
            //bookOrderRep.updateCost(bo.getId(), bo.getTotalCost()+b.getCost());
            bookOrderRep.delete(bo);
            bo.setBooks(bArr);
            bo.setTotalCost(bo.getTotalCost()+b.getCost());
            bo.setId(0);
            bookOrderRep.save(bo);
            ClientEntity ce = getClientEntity(user);
            bo = bookOrderRep.findByClientAndDone(ce, false);
            clientRep.updateOrder(ce.getId(), ce.getOrdersCnt(), bo.getId());
        }
    }

    public void deleteFromCart(UserEntity user, int bookId){
        BookOrder bo = getCart(user);
        Book b = bookRep.findById(bookId).orElse(null);
        if(b != null){
            b.setCount(b.getCount()+1);
            updateBook(b);
            List<Book> bArr = bo.getBooks();
            List<Book> bArrNew = new ArrayList<>();
            bArr.forEach(item ->{
                if(item.getId() != bookId){
                    bArrNew.add(item);
                }
            });
            //bookOrderRep.updateList(bo.getId(), bArr);
            //bookOrderRep.updateCost(bo.getId(), bo.getTotalCost()+b.getCost());
            bookOrderRep.delete(bo);
            bo.setBooks(bArrNew);
            bo.setTotalCost(bo.getTotalCost()-b.getCost());
            bo.setId(0);
            bookOrderRep.save(bo);
            ClientEntity ce = getClientEntity(user);
            bo = bookOrderRep.findByClientAndDone(ce, false);
            clientRep.updateOrder(ce.getId(), ce.getOrdersCnt(), bo.getId());
        }
    }

    public void doneCart(UserEntity user){
        BookOrder bo = getCart(user);
        bookOrderRep.done(bo.getId(), new Date());
        ClientEntity ce = getClientEntity(user);
        clientRep.updateOrder(ce.getId(), ce.getOrdersCnt(), -1);
    }

    public void newOrder(BookOrder bookOrder){
        bookOrderRep.save(bookOrder);
    }
}