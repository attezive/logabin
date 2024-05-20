package com.example.logabin.utils;

public class Coordinate {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Coordinate coordinate){
        return x == coordinate.x && y == coordinate.y;
    }

    public Coordinate sum(int a){
        return sum(a, a);
    }

    public Coordinate sum(Coordinate coordinate){
        return sum(coordinate.x, coordinate.y);
    }

    public Coordinate sum(int x, int y){
        return new Coordinate(this.x+x, this.y+y);
    }

    public double getDistance(Coordinate coordinate){
        return Math.sqrt(Math.pow(x- coordinate.x, 2)+Math.pow(y- coordinate.y, 2));
    }

    @Override
    public String toString() {
        return x+":"+y;
    }
}
