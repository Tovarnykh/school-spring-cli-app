package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.CommandLineInterface;

abstract class MenuItem extends CommandLineInterface {

    protected void chooseOpetion() {
        int choice = readNumber();

        if (choice == 1) {
            addSection();
        } else if (choice == 2) {
            getSection();
        } else if (choice == 3) {
            getAllSection();
        } else if (choice == 4) {
            updateSection();
        } else if (choice == 5) {
            deleteSection();
        }
    }

    abstract void addSection();

    abstract void getSection();

    abstract void getAllSection();

    abstract void updateSection();

    abstract void deleteSection();

}