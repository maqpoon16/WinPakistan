package com.win.pakistan.Models;

public class MenuDataclass {
    String menuname;
    int menuImage;

    public MenuDataclass(String menuname, int menuImage) {
        this.menuname = menuname;
        this.menuImage = menuImage;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public int getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }
}
