package mds.mdstoolkit.tasks;

/**
 * Created by vivekjha on 07/06/16.
 */
public interface IProgressWatcher {

    void onTaskBegins();
    void onTaskEnds();
    void onInfo(String msg);
}
