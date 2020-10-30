package com.venkata.ds.graphtheory.gridproblems;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Problem Statement: Dungeon has a size RxC and you start at cell S and end at cell E.
 * you cannot pass through a cell with rock and is indicated with # and empty cell is represents with.
 *           - - - - - - -
 *          |S|.|.|#|.|.|.|
 *           - - - - - - -
 *          |.|#|.|.|.|#|.|
 *           - - - - - - -
 *          |.|#|.|.|.|.|.|
 *           - - - - - - -
 *          |.|.|#|#|.|.|.|
 *           - - - - - - -
 *          |#|.|#|E|.|#|.|
 *           - - - - - - -
 *
 * */

public class DungeonProblem {

    // Constants for the direction
    private  final String S = "S";
    private  final String M = ".";
    private  final String R = "#";
    private  final String E = "E";


    // variable to calculate number of moves before reaching E
    private int moves = 0;
    private int steps_left_before_move = 1;
    private int steps_in_next_pass = 0;

    // Size of row and colomn
    private int rowSize = 5;
    private int colSize = 7;

    // Actual Dungeon to solve
    private String [][] dungeon = {
            {S, M, M, R, M, M, M},
            {M, R, M, M, M, R, M},
            {M, R, M, M, M, M, M},
            {M, M, R, R, M, M, M},
            {R, M, R, E, M, R, M}
    };

    // directions to calculate
    private int[] rd = {-1, 1, 0, 0};
    private int[] cd = {0, 0, -1, 1};

    // queues to track next cell to visit
    private Queue<Integer> rq = new LinkedList<>();
    private Queue<Integer> cq = new LinkedList<>();

    // to keep track of visited nodes so that we don't visit them again
    private Set<String> visited = new HashSet<>();

    public boolean isSolved = false;

    // Method solve the maze
    public int solveMaze(int sr, int sc) {
        visited.add(sr+""+sc);
        rq.offer(sr);
        cq.offer(sc);
        //System.out.println("ROOT Node: "+(sr+""+sc));
        while (!rq.isEmpty()){
            int cr = rq.poll();
            int cc = cq.poll();
            System.out.println("Current Node: ["+cr+"]["+cc+"]:"+ dungeon[cr][cc]);
            System.out.println("Current Queue Size: "+rq.size());
            if( dungeon[cr][cc].equals(E)) {
                isSolved = true;
                return moves;
            }

            addNeighbors(cr, cc);
            steps_left_before_move--;
            if(steps_left_before_move == 0){
                moves++;
                steps_left_before_move = steps_in_next_pass;
                steps_in_next_pass =0;
            }

        }

        return moves;
    }

    private void addNeighbors(int cr, int cc) {

        for (int i=0; i < 4; i++){

            int nxtRow = cr+rd[i];
            int nxtCol = cc+cd[i];

            System.out.println("dungeon["+nxtRow+"]["+nxtCol+"]");
            // Skipping if the neighbor is out of bounds or if it is already visited or there is a rock
            if((nxtRow <0 || nxtRow >= rowSize) ||
                    (nxtCol <0 || nxtCol >= colSize ) ||
                    visited.contains(nxtRow+""+nxtCol) ||
                    dungeon[nxtRow][nxtCol] == R)
                continue;

            System.out.println("Adding to visited:"+nxtRow+""+nxtCol);
            visited.add(nxtRow+""+nxtCol);
            steps_in_next_pass++;
            rq.offer(nxtRow);
            cq.offer(nxtCol);

        }

    }


    public static void main(String[] args) {
        DungeonProblem problem = new DungeonProblem();
        System.out.println("total steps to solve: "+problem.solveMaze(0, 0));
        System.out.println("Solved: "+problem.isSolved);
    }

}
