package main.preAsnyPost;

import java.util.concurrent.Callable;


/**
 * @param <R> Result type
 */
public interface AsyncTask<R> extends Callable<R> {
    void pre();
    void post(R result);
    void onError(Throwable throwable);

    // This is where the computation of task. (background thread).
    @Override
    R call() throws Exception;
}
