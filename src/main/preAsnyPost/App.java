package main.preAsnyPost;

import java.util.concurrent.LinkedBlockingQueue;

public class App {
    public static void main(String[] args) {
        AsyncService asyncService = new AsyncService(new LinkedBlockingQueue<>());

        // create a task and sen TODO:
        //asyncService.execute();
    }
}
