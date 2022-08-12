package models;

import java.util.List;

public interface UserManager {

    List<User> insert(User user);

    List<User> listUsers() ;

     void update(User user);

     void delete(int id);

    User search(int id);
}
