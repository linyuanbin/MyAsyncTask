package com.lin.MyAsyncTask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class ProgressBartext extends AppCompatActivity {
    private ProgressBar mprogressBar;
    private MyAsyncTask mtask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bartext);

        mprogressBar= (ProgressBar) findViewById(R.id.progressbar);
        mtask=new MyAsyncTask();
        mtask.execute();//使用AsyncTask必须在UI中启动异步处理机制 ，但只能写一次，若写两次 mtask.execute();会报运行时异常

    }

    @Override
    protected void onPause() {  //设置线程生命周期与Activity同步 不然进入一个线程时只有前面一个线程运行结束开会开始新的线程
        if(mtask!=null&&mtask.getStatus()==AsyncTask.Status.RUNNING){
            //mtask只会将线程标记为cancel状态作为后面方法判断线程状态，而不会真正去停止线程，在Java中的无法直接停止一个线程的
            mtask.cancel(true);
        }
        super.onPause();
    }

    class MyAsyncTask extends AsyncTask<Void,Integer,Void>{  //无传入参数，无返回值，有进度

        //除了doInBackground在子线程中实行，其余方法都在主线程中进行，也就是只有doInBackground（）方法中不能去改变UI
        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=0;i<100;i++){  //虚拟进度
                if(isCancelled()){ //如果线程为cancel状态就结束
                    break;
                }
                publishProgress(i);  //更新进度

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if(isCancelled()){
                return;
            }
            mprogressBar.setProgress(values[0]); //设置进度 来自publishProgress的传递值
            super.onProgressUpdate(values);
        }
    }


}
