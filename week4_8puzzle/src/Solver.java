import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Solver {
    private List<Board> solution = new ArrayList<>();
    private boolean solved;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("The initial board is null");
        MinPQ<SearchNode> priorityNodes = new MinPQ<>(new SearchNodeComparator());
        MinPQ<SearchNode> priorityNodesTwin = new MinPQ<>(new SearchNodeComparator());
        priorityNodes.insert(new SearchNode(initial, 0, null));
        priorityNodesTwin.insert(new SearchNode(initial.twin(), 0, null));

        SearchNode node;
        while (!priorityNodes.min().getBoard().isGoal() && !priorityNodesTwin.min().getBoard()
                                                                             .isGoal()) {
            node = priorityNodes.delMin();
            // StdOut.println(node.getBoard());
            for (Board neighbor : node.getBoard().neighbors()) {
                if (isExistedSolution(node, neighbor))
                    // System.out.println(neighbor);
                    priorityNodes.insert(new SearchNode(neighbor, node.getMoves() + 1, node));
            }

            SearchNode nodeTwin = priorityNodesTwin.delMin();
            for (Board neighbor : nodeTwin.getBoard().neighbors()) {
                if (isExistedSolution(nodeTwin, neighbor))
                    // System.out.println("@"+neighbor);
                    priorityNodesTwin.insert(new SearchNode(neighbor, nodeTwin.getMoves() + 1, nodeTwin));
            }


        }

        node = priorityNodes.delMin();
        solved = node.getBoard().isGoal();

        // solution.add(node.getBoard());
        while (node != null) {
            solution.add(0, node.getBoard());
            node = node.getPrevNode();
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solved;
    }

    // min number of moves to solve initial board
    public int moves() {
        int moves = -1;
        if (isSolvable()) {
            moves = solution.size() - 1;
        }
        return moves;
    }

    // sequence of boards in the shortest solution
    public Iterable<Board> solution() {
        Iterable<Board> iterable = null;
        if (isSolvable()) {
            iterable = SolutionIterator::new;
        }
        return iterable;
    }


    private boolean isExistedSolution(SearchNode node, Board board) {
        // SearchNode prevNode = node.prevNode;
        // while (prevNode != null) {
        //     if (prevNode.getBoard().equals(boar4d)) return true;
        //     prevNode = prevNode.getPrevNode();
        // }
        // return false;
        if (node.getPrevNode() != null) {
            return !node.getPrevNode().getBoard().equals(board);
        }
        return true;

    }

    private class SearchNode {
        private Board board;
        private int moves;
        private SearchNode prevNode;
        private int priority;

        public SearchNode(Board board, int moves, SearchNode prevNode) {
            this.board = board;
            this.moves = moves;
            this.prevNode = prevNode;
            this.priority = moves + board.manhattan();
        }

        public Board getBoard() {
            return board;
        }

        public int getMoves() {
            return moves;
        }

        public SearchNode getPrevNode() {
            return prevNode;
        }

        public int getPriority() {
            return priority;
        }
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {

        public int compare(SearchNode o1, SearchNode o2) {
            return o1.getPriority() - o2.getPriority();
        }
    }

    private class SolutionIterator implements Iterator<Board> {
        private int index = 0;

        public boolean hasNext() {
            return index < solution.size();
        }

        public Board next() {
            if (hasNext()) return solution.get(index++);
            else throw new NoSuchElementException();
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}