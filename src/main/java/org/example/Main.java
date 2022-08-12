package org.example;
import models.DatabaseStorageUserManager;
import models.InMemoryStorageUserManager;
import models.User;
import models.UserManager;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner integerInput = new Scanner(System.in);
        Scanner stringInput = new Scanner(System.in);


        UserManager memoryStorageUserManager = new InMemoryStorageUserManager();

        UserManager databaseStorageUserManager = new DatabaseStorageUserManager();


        int choice1 = 0;

        do {
            System.out.println("enter where to storage the data");
            System.out.println("1.InMemoryStorage");
            System.out.println("2.InDatabaseStorage");
            System.out.println("3.exit");
            System.out.println("enter your choice");
            choice1 = integerInput.nextInt();

            if (choice1 == 1) {
                int choice2 = 0;
                do {
                    System.out.println("enter your choice");
                    System.out.println("1.Adduser");
                    System.out.println("2.delete user");
                    System.out.println("3.list users");
                    System.out.println("4.Update users");
                    System.out.println("5.Search user");
                    System.out.println("6.exit");
                    choice2 = integerInput.nextInt();

                    if (choice2 == 1) {
                        User user = new User();
                        System.out.println("enter user id ");
                        user.setId(integerInput.nextInt());
                        System.out.println("enter user name");
                        user.setName(stringInput.nextLine());
                        System.out.println("enter user gender");
                        user.setGender(stringInput.nextLine());
                        System.out.println("enter user addressId ");
                        user.setAddressId(integerInput.nextInt());
                        System.out.println("enter user date of birth ");
                        user.setDob(stringInput.nextLine());

                        memoryStorageUserManager.insert(user);

                    } else if (choice2 == 2) {
                        User user = new User();
                        System.out.println("enter Id to be deleted");
                        int deleteId = integerInput.nextInt();
//                        user.setId(integerInput.nextInt());
                        memoryStorageUserManager.delete(deleteId);

                    } else if (choice2 == 3) {
                        System.out.println(memoryStorageUserManager.listUsers());

                    } else if (choice2 == 4) {

                        User user = new User();
                        System.out.println("enter user id to be updated ");
                        user.setId(integerInput.nextInt());
                        System.out.println("enter user name");
                        user.setName(stringInput.nextLine());
                        System.out.println("enter user gender");
                        user.setGender(stringInput.nextLine());
                        System.out.println("enter user addressId to be updated ");
                        user.setAddressId(integerInput.nextInt());
                        System.out.println("enter user date of birth to be updated ");
                        user.setDob(stringInput.nextLine());


                        memoryStorageUserManager.update(user);
                        System.out.println("updated successfully");

                    } else if (choice2 == 5) {
                        System.out.println("enter user id to search");
                        int searchId = integerInput.nextInt();
                        System.out.println(memoryStorageUserManager.search(searchId));

                    } else {
                        choice2 = 6;
                    }
                } while (choice2 != 6);
            } else if (choice1 == 2) {
                int choice2 = 0;
                do {
                    User user = new User();
                    System.out.println("1.display user table");
                    System.out.println("2.search from table");
                    System.out.println("3.insert into table");
                    System.out.println("4.update data into table ");
                    System.out.println("5.delete data from table");
                    System.out.println("6.exit");
                    choice2 = integerInput.nextInt();

                    if (choice2 == 1) {
                        new Main().printuser();

                    } else if (choice2 == 2) {

                        System.out.println("enter user id to be searched");
                        int id = integerInput.nextInt();
                        databaseStorageUserManager.search(id);

                    } else if (choice2 == 3) {
                        System.out.println("enter user id to be inserted ");
                        user.setId(integerInput.nextInt());
                        System.out.println("enter user name to be inserted");
                        user.setName(stringInput.nextLine());
                        System.out.println("enter user gender to be inserted");
                        user.setName(stringInput.nextLine());
                        System.out.println("enter addressId to be inserted");
                        user.setAddressId(integerInput.nextInt());
                        System.out.println("enter user dateofbirth to be updated");
                        user.setDob(stringInput.nextLine());

                        databaseStorageUserManager.insert(user);


                    } else if (choice2 == 4) {
                        System.out.println("enter user id to be updated ");
                        user.setId(integerInput.nextInt());
                        System.out.println("enter user name to be updated");
                        user.setName(stringInput.nextLine());
                        System.out.println("enter user gender to be updated");
                        user.setGender(stringInput.nextLine());
                        System.out.println("enter addressid to be updated ");
                        user.setAddressId(integerInput.nextInt());
                        System.out.println("enter user date of birth to be updated");
                        user.setDob(stringInput.nextLine());

                        databaseStorageUserManager.update(user);

                    } else if (choice2 == 5) {
                        System.out.println("enter user id to be deleted");
                        int id = integerInput.nextInt();
                        // user.setId(integerInput.nextInt());

                        databaseStorageUserManager.delete(id);

                    } else {
                        choice2 = 6;
                    }

                } while (choice2 != 6);

            } else {
                choice1 = 3;
            }
        }while (choice1 !=3);


    }
    DatabaseStorageUserManager userManager1 = new DatabaseStorageUserManager();
    public void printuser() {
        List<User> ListOfuser = userManager1.listUsers();
        if (ListOfuser != null) {
            for (User user : ListOfuser) {
                System.out.println(user);
            }
        }
    }
}
