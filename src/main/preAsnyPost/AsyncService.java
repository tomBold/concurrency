package main.preAsnyPost;

import java.util.concurrent.*;

public class AsyncService {
    private ExecutorService service;

    public AsyncService(BlockingQueue<Runnable> workQueue) {
        service = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, workQueue);
    }

    public <T> void execute(final AsyncTask<T> task) {
        try {
            task.pre();
        } catch (Exception e) {
            task.onError(e);
            return;
        }

        service.submit(new FutureTask<T>(task) {
            @Override
            protected void done() {
                super.done();
                try {
                    // Do the post action on the task result
                    task.post(get());
                } catch (InterruptedException e) {
                    // should not occur
                } catch (ExecutionException e) {
                    task.onError(e.getCause());
                }
            }
        });
    }
}