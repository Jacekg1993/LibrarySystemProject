package com.jacekg;

import classes.*;
import data_access.LibraryWorkerDataAccess;
import user_interface.MenuHelper;
import user_interface.UserMenu;
import user_interface.WorkerMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {

        /*List<LibraryWorker> libraryWorkerList = new ArrayList<LibraryWorker>();

        libraryWorkerList = LibraryWorkerDataAccess.getAllLibraryWorkerByNameSurNameId("Jacek", " ", 0);

        for (LibraryWorker libraryWorker : libraryWorkerList) {
            System.out.println(libraryWorker.getUserData());
        }*/

        MenuHelper.logOnPanel();



        //usuwanie
     /*   for (int i = 12; i < 13; i++) {

            int status = LibraryWorkerDataAccess.deleteLibraryWorker(i);

            if (status > 0) {
                System.out.println("usuniety id: " + i);
            }
        }*/





    }
}
