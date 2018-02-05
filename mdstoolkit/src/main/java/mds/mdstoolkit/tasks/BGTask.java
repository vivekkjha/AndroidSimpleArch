package mds.mdstoolkit.tasks;

/**
 * Created by vivekjha on 07/06/16.
 */
public abstract class BGTask<Input, Output> {

    private boolean mTaskCancel = false;
    /**
     * In the implementation , put operational code- work that needs to be done on background thread
     * @param inputs  input params (Generic)
     * @return Outout generic params
     * @throws Exception exception
     */
    public abstract Output performOperation(Input... inputs) throws Exception;

    /**
     * Update UI ,needs to be called after background operation completes
     * @param output generic output
     */
    public abstract void updateUI(Output output);

    protected void onTaskCancelled()
    {
        if(shouldCancel())
            mTaskCancel = true;
    }

    public String getDescription() {
        return null;
    }

    public boolean handleErrorInUI(Exception t) {
        return false;
    }


    public boolean isTaskCancelled()
    {
        return mTaskCancel;
    }

    /**
     * If we Task can be Cancelled , override this method if task can be cancelled
     * @return true, if cancelled false otherwise
     */
    protected boolean shouldCancel()
    {
        return false;
    }


}
