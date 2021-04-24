// responsible for taking commands and calling state methods to update as needed
// also responsible for adding/removing process and resources and making a new appropriate state
public class Bank {
    State state;

    public Bank(){
        int processes = 0, resources = 0;
        int[] available = new int[resources];
        int[][] maximum = new int[processes][resources];
        int[][] allocation = new int[processes][resources];
        int[][] need = new int[processes][resources];

        state = new State(processes, resources, available, maximum, allocation, need);
    }

    public Bank(int processes, int resources, int[] available, int[][] maximum, int[][] allocation, int[][] need){
        State state = new State(processes, resources, available, maximum, allocation, need);
    }

    public Bank(State state){
        this.state = state;
    }

    // parses the command & handles exceptions
    public boolean parse(String cmd){
        return true;
    }

    // adds a process by making a new state with a new entry, new process allocation should start with zero thus need = maximum
    public boolean addProcess(int[] maximum){
        return true;
    }

    // removes a process and frees up its allocated resources
    public boolean removeProcess(int pid){
        return true;
    }

    public boolean request(int pid, int[] requestArray) throws Exception {
        return state.request(pid, requestArray);
    }

    public boolean release(int pid, int[] releaseArray) throws Exception {
        return state.release(pid, releaseArray);
    }
}
