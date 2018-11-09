package com.example.mike.week3exam.infected_room_problem;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;

public class Room  {

    public final boolean isInfected;
    public boolean visited = false;
    public static Integer OUTBREAK_THRESHOLD = 5;

    public Room(boolean infected){
        isInfected = infected;
    }

    public static boolean isOutbreak(Room[][] floor) {     //Your logic here }
        return new Room(false)._isOutbreak(floor);
    }

    private boolean _isOutbreak(Room[][] floor){

        LinkedList<Coordinate> infectedRooms = new LinkedList<>();

        for (int i = 0; i < floor.length; i++) {
            for (int i1 = 0; i1 < floor[i].length; i1++) {
                if ( floor[i][i1].isInfected ){
                    infectedRooms.add( new Coordinate(i, i1) );
                }
            }
        }

        LinkedList<OutbreakGroup> connectedInfectedRooms = new LinkedList<>();

        for (Coordinate infectedRoom : infectedRooms) {

            // Check if its connected to any of the rooms
            Boolean connected = false;
            for ( OutbreakGroup outbreakGroup : connectedInfectedRooms ){
                if (outbreakGroup.connectedTo( infectedRoom )){
                    connected = true;
                    outbreakGroup.add( infectedRoom );
                    if ( outbreakGroup.size() >= OUTBREAK_THRESHOLD ){
                        return true;
                    }
                    break;
                }
            }

            if ( !connected ){
                // Create new outbreak group
                connectedInfectedRooms.add( new OutbreakGroup(infectedRoom) );
            }
        }

        return false;
    }

    private class Coordinate{
        Integer x,y;
        public Coordinate(int x,int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals(@Nullable Object obj) {

            if ( obj!=null && obj.getClass().getSimpleName().equals("Coordinate")){
                Coordinate c = (Coordinate) obj;
                if ( this.x == c.x && this.y == c.y ){
                    return true;
                }
            }

            return super.equals(obj);
        }
    }

    private class OutbreakGroup{
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        OutbreakGroup(Coordinate c){
            this.coordinates.add( c );
        }

        public void add( Coordinate c ){
            coordinates.add(c);
        }

        public boolean connectedTo( Coordinate c ){
            Coordinate check_px = new Coordinate( c.x+1, c.y );
            Coordinate check_mx = new Coordinate( c.x-1, c.y );
            Coordinate check_py = new Coordinate( c.x, c.y+1 );
            Coordinate check_my = new Coordinate( c.x, c.y-1 );

            if ( coordinates.contains( check_px ) || coordinates.contains( check_mx ) ||
                    coordinates.contains( check_py ) || coordinates.contains( check_my )){
                return true;
            }
            return false;
        }

        public boolean has( Coordinate c ){
            if ( coordinates.contains( c ) ){
                return true;
            }else{
                return false;
            }
        }

        public int size(){
            return coordinates.size();
        }

    }

    public static void printFloor( Room[][] floor ){

        for (int i = 0; i < floor.length; i++) {

            for (int i1 = 0; i1 < floor[i].length; i1++) {
                if ( floor[i][i1].isInfected ){
                    System.out.print( "X " );
                }else{
                    System.out.print( "O " );
                }
            }
            System.out.print("\n");
        }

    }
}

class main {
    public static void main(String[] args) {

        Room[][] testCase1 = new Room[][] {  {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(true), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) } };
        Room[][] testCase2 = new Room[][] { {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(true), new Room(true), new Room(true), new Room(true), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },{new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) } };
        Room[][] testCase3 = new Room[][] { {new Room(true), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(true), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(true), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(true), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) } };

        Room.printFloor( testCase1 );
        System.out.println( Room.isOutbreak( testCase1 ) );

        Room.printFloor( testCase2 );
        System.out.println( Room.isOutbreak( testCase2 ) );

        Room.printFloor( testCase3 );
        System.out.println( Room.isOutbreak( testCase3 ) );
    }
}
