package mds.mdstoolkit.tasks;

/**
 * Created by vivekjha on 07/06/16.
 */
public interface ICancelableTask {

    void cancel(); //> cancel task
    boolean isCancelled(); //> is task has been cancelled
    boolean finish(); //> Is task has been finished


}
