import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int processes = 5, resources = 3;
        int[] available = {3, 3, 2};
        int[][] maximum = {
                {7, 5, 3},
                {3, 2, 2},
                {9, 0, 2},
                {2, 2, 2},
                {4, 3, 3}};
        int[][] allocation = {
                {0, 1, 0},
                {2, 0, 0},
                {3, 0, 2},
                {2, 1, 1},
                {0, 0, 2}};

            State state = new State(processes, resources, available, maximum, allocation);
            state.isSafe();
            Bank bank = new Bank(state);

            Scanner sc = new Scanner(System.in);
//            System.out.println(bank.request(1, new int[]{1, 0, 2}));
//            System.out.println(bank.request(4, new int[]{3, 3, 0}));

            String input = sc.nextLine();

            while (!input.equals("Quit")){
                try{
                    bank.parse(input);
                }catch(Exception e){
                    System.out.println("Deny");
                    System.out.println(e.getMessage());
                }
                    input = sc.nextLine();
            }


        return;
    }
}
