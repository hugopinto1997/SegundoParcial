package com.hugopinto.segundoparcial.Classes;

public class Game {
    private String gamename;
    private String gamedesc;
    private int gameimg;

    public Game(String gamename, String gamedesc, int gameimg) {
        this.gamename = gamename;
        this.gamedesc = gamedesc;
        this.gameimg = gameimg;
    }


    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public String getGamedesc() {
        return gamedesc;
    }

    public void setGamedesc(String gamedesc) {
        this.gamedesc = gamedesc;
    }

    public int getGameimg() {
        return gameimg;
    }

    public void setGameimg(int gameimg) {
        this.gameimg = gameimg;
    }
}
