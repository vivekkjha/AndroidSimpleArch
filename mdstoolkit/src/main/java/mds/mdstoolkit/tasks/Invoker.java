package mds.mdstoolkit.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;

/**
 * Created by vivekjha on 07/06/16.
 * Invoker class to run some funcitons that are two heavy for Main thread
 */
public class Invoker
{
    private static ProgressDialog currentProgressDialog;

    /**
     * This method will be called when default progress dialog needs to be shown on the UI
     * @param task  task to do
     * @param context Activity context required
     * @param inputs inputs values
     * @param <Input> generic inputs
     * @param <Output> generic out put
     * @return cancelable task
     */
    public static <Input, Output> ICancelableTask run(final BGTask<Input, Output> task, Context context, Input... inputs) {
        final Activity activity = (Activity) context;
        ProgressWatcher watcher = new ProgressWatcher(task, activity);
        final ICancelableTask cancelableTask = run(task, watcher, inputs);
        watcher.setCancelableTask(cancelableTask);
        return cancelableTask;
    }

    /**
     * Progress bar for automatics progress dialog
     */
    private static class ProgressWatcher implements IProgressWatcher
    {
        BGTask<?, ?> task;
        Activity activity;
        ICancelableTask mICancelableTask;

        public ProgressWatcher(BGTask<?,?> task, Activity activity)
        {
            this.task = task;
            this.activity = activity;
        }

        public void setCancelableTask(ICancelableTask mICancelableTask) {
            this.mICancelableTask = mICancelableTask;
        }

        @Override
        public void onTaskBegins() {
            dissmisDialog();

            if (activity == null) return;

            DialogInterface.OnCancelListener onCancelListener = new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    if (task.shouldCancel()) {
                        if (mICancelableTask != null && !mICancelableTask.isCancelled()) {
                            mICancelableTask.cancel();
                        }
                    }
                    dissmisDialog();
                }
            };
            String description = task.getDescription() == null ? "Loading..." : task.getDescription(); // TODO: 26/08/16 Change loading to local string
            if (activity.isFinishing()) return;
            try {
                if (description != null && description.length() == 0) return;

                currentProgressDialog = ProgressDialog.show(activity, null, description, true, task.shouldCancel(), onCancelListener);
            } catch (Throwable e) {
            }
        }

        @Override
        public void onTaskEnds() {
            dissmisDialog();
        }

        @Override
        public void onInfo(String msg) {

        }
    }

    /**
     * Dismiss current progress dialog
     */
    private static void dissmisDialog() {
        if (currentProgressDialog != null){
            try {
                currentProgressDialog.dismiss();
            } catch (Exception e) {
            }
            currentProgressDialog = null;
        }
    }

    /**
     * Invoke async task to be run in parallel and on different thread, with progress watcher attached
     * @param mTask Object of Task with generic params
     * @param mProgressWatcher progress watcher to track progress and show loader
     * @param inputs inputs from calling activity
     * @param <Input> Generic Input params
     * @param <Output> Generic Output params
     * @return Cancellable task
     */
    public static <Input,Output> ICancelableTask run(final BGTask<Input,Output> mTask, final IProgressWatcher mProgressWatcher,
                                                     Input... inputs)
    {
        final AsyncTask<Input,Void,Output> mAsyncTask = new AsyncTask<Input, Void, Output>() {

            private Exception exception = null;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if(mProgressWatcher!=null)
                    mProgressWatcher.onTaskBegins();
            }


            @Override
            protected Output doInBackground(Input... params) {
                try {
                    if(!mTask.isTaskCancelled()) {
                       return mTask.performOperation(params);
                    }
                    else return null;
                }
                catch (Exception e)
                {
                    exception = e;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Output output) {
                super.onPostExecute(output);
                if(mProgressWatcher!=null)
                    mProgressWatcher.onTaskEnds();

                if(mTask.isTaskCancelled()) return;
                if(exception!= null)
                {
                    mTask.handleErrorInUI(exception);
                    exception.printStackTrace();
                    exception = null;
                }
                else
                    mTask.updateUI(output);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                if(mProgressWatcher!=null)
                    mProgressWatcher.onTaskEnds();
                mTask.onTaskCancelled();
            }
        };

        ICancelableTask mCancelableTask = new ICancelableTask() {
            @Override
            public void cancel() {
                mAsyncTask.cancel(true);
            }

            @Override
            public boolean isCancelled() {
                return mAsyncTask.isCancelled();
            }

            @Override
            public boolean finish() {
                return mAsyncTask.getStatus() == AsyncTask.Status.FINISHED;
            }
        };
        execute(mAsyncTask,inputs);
        return mCancelableTask;
    }

    /**
     * Execute async task in parallel
     * @param task Task
     * @param params input params
     * @param <Input> input generics
     */
    private static <Input> void execute (AsyncTask<Input, ?, ?> task, Input... params) {
        if (Build.VERSION.SDK_INT < 11) {
            task.execute(params);
        } else {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        }
    }
}
