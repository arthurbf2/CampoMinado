package game;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Select the game difficulty\n[1] Easy\n[2] Medium\n[3] Hard");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        Difficulty diff = Difficulty.EASY;
        switch (input) {
            case 1:
                break;
            case 2:
                diff = Difficulty.MEDIUM;
                break;
            case 3:
                diff = Difficulty.HARD;
                break;
            default:
                System.out.println("Invalid input, setting game to easy mode");
        }
        Minesweeper minesweeper = new Minesweeper(diff);
    }
}
