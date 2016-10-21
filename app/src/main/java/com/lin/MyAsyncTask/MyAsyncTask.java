package com.lin.MyAsyncTask;

import android.os.AsyncTask;

/**
 * Created by lin on 2016/10/17.
 */

   //线程池实现多线程异步
public class MyAsyncTask extends AsyncTask<Void ,Void,Void> {


    @Override
    protected Void doInBackground(Void... voids) {
        //必须重写，异步执行后台线程将要完成的任务 真正执行耗时操作
        //publishProgress(); //在此处调用该方法更新进度  会调用onProgressUpdate方法
        return null;
    }
    //ctrl+o 重写方法    执行顺序： onPreExecute   ---> doInBackground --->  onPostExecute

    @Override
    protected void onProgressUpdate(Void... values) {   //doInBackground中调用publishProgress方法更新任务的执行进度后就会触发
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Void aVoid) {   //得到doInBackground返回的值
        super.onPostExecute(aVoid);
    }



    @Override
    protected void onPreExecute() {  //执行后台耗时操作前被调用，通常用户完成一些初始化操作
        super.onPreExecute();
    }


}
