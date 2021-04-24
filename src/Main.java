import java.util.ArrayList;
import java.util.List;

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
        int[][] need = new int[processes][resources];
        for (int i = 0; i < processes; ++i) for (int j = 0; j < resources; ++j) need[i][j] = maximum[i][j] - allocation[i][j];

        State state = new State(processes, resources, available, maximum, allocation, need);
        System.out.println(state.isSafe());

        Bank bank = new Bank(state);
        try{
            System.out.println(bank.request(1, new int[]{1, 0, 2}));
            System.out.println(bank.request(4, new int[]{3, 3, 0}));
        }catch(Exception e){
            e.printStackTrace();
        }

        return;
    }
}
