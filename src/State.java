public class State {
    int processes, resources;
    int[] available;
    int[][] maximum , allocation, need;

    public State(int processes, int resources, int[] available, int[][] maximum, int[][] allocation, int[][] need){
        this.processes = processes;
        this.resources = resources;
        this.available = available;
        this.maximum = maximum;
        this.allocation = allocation;
        this.need = need;
    }

    // compares two arrays return 1 if a <= b for elements of array1 & 2, 0 otherwise
    boolean cmpArrays(int[] array1, int[] array2, int sz){
        for (int i = 0; i < sz; ++i) {
            if (array1[i] > array2[i]) return false;
        }
        return true;
    }
    public boolean isSafe(){
        // initialize work & finish arrays
        int[] work = new int[resources];
        for(int i = 0; i < resources; ++i) work[i] = available[i];
        boolean[] finish = new boolean[processes];

        // search for a process that's not finished and what it needs is less than currently available in work
        // until no such process is found
        boolean found;
        do {
            found = false;
            for (int i = 0; i < processes; ++i){
                if (finish[i] == false && cmpArrays(need[i], work, resources)) {
                    // work = work + allocation[i]: process i can finish so free up its allocated resources
                    finish[i] = true;
                    for (int j = 0; j < resources; ++j) work[j] = work[j] + allocation[i][j];
                    // search for another process after the update
                    found = true;
                    break;
                }
            }
        } while(found);

        // if all processes are finished then it's a safe state, otherwise it's not
        for (int i = 0; i < processes; ++i) if (!finish[i]) return false;
        return true;
    }

    public boolean request(int pid, int[] requestArray) throws Exception {
        // raise error condition, since process has exceeded its maximum claim
        if (!cmpArrays(requestArray, need[pid], resources)) throw new Exception(" process has exceeded its maximum claim");
        // Process i must wait, since resources are not available
        if (!cmpArrays(requestArray, available, resources)) return false;

        // updates
        for (int i = 0; i < resources; ++i) available[i] = available[i] - requestArray[i];
        for (int i = 0; i < resources; ++i) allocation[pid][i] = allocation[pid][i] + requestArray[i];
        for (int i = 0; i < resources; ++i) need[pid][i] = need[pid][i] - requestArray[i];
        // check safety
        if (isSafe()) return true;
        // if not safe revert changes & process must wait
        for (int i = 0; i < resources; ++i) available[i] = available[i] + requestArray[i];
        for (int i = 0; i < resources; ++i) allocation[pid][i] = allocation[pid][i] - requestArray[i];
        for (int i = 0; i < resources; ++i) need[pid][i] = need[pid][i] + requestArray[i];
        return false;
    }

    public boolean release(int pid, int[] releaseArray) throws Exception {
        // raise error condition, since process can't release more than whats allocated for it
        if (!cmpArrays(releaseArray, allocation[pid], resources)) throw new Exception("process can't release more than whats allocated for it");

        // updates
        for (int i = 0; i < resources; ++i) available[i] = available[i] + releaseArray[i];
        for (int i = 0; i < resources; ++i) allocation[pid][i] = allocation[pid][i] - releaseArray[i];
        for (int i = 0; i < resources; ++i) need[pid][i] = need[pid][i] + releaseArray[i];

        return true;
    }
}
