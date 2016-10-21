package com.lin.MyAsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import java.net.URLConnection;

public class ImageText extends AppCompatActivity {

    private ImageView mimageView;
    private ProgressBar mprogressBar;
    private static String URL="http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";  //慕课链接

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_text);

        mimageView= (ImageView) findViewById(R.id.image);
        mprogressBar= (ProgressBar) findViewById(R.id.progressbar);

        //传入URL参数
        new MyAsycTask().execute(URL );  //开启AsyncTask

    }

    //创建AsycTask对象，以内部类的方式
    class MyAsycTask extends AsyncTask<String,Void,Bitmap>{//第一个参数 因为要传入URL所以时String,第二个参数放回进度，这里不需要就业Void.第三分放回图片所以用BitMap
                                  // <传入参数，放回进度类型，放回值类型>

        //被调用实行过程
        //1. onPreExecute（）开始实行异步初始化 后台准备
        //2. doInBackground（） 真正开始一步操作处理 （都是在子线程中）
        //3. onPostExecute（）   得到放回的结果  （实行在主线程中，所以可以对UI操作）

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mprogressBar.setVisibility(View.VISIBLE); //设置进度条显示
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {  //放回Bitmap  操作UI设置图像
            super.onPostExecute(bitmap);
            mprogressBar.setVisibility(View.GONE); //设置进度条隐藏
            mimageView.setImageBitmap(bitmap); //设置图片
        }

        @Override
        protected Bitmap doInBackground(String... strings) {//可变长数组  //访问网络，加载图像
            //第一步 从strings数组中取到传递进来的参数-->URL  （从execute中传入的）
            //第二步 通过decodeStream 解析输入流 将URL对应图解析成Bitmap
            //第三步 将bitmap作为返回值
            String url=strings[0]; //得到第一个传进来的URL得到图片地址
            Bitmap bitmap=null;
            URLConnection connection; //用于获取输入流
            InputStream is;//输入流
            try {
                //1.获取连接  2.通过连接得到输入流   3.得到输入流中文件
                connection=new URL(url).openConnection(); //import java.net.URL;   获取网络链接对象
                is=connection.getInputStream();//得到输入流
                BufferedInputStream bis=new BufferedInputStream(is);//包装输入流为缓冲流
                Thread.sleep(3000);//睡眠3秒 有异常
                bitmap= BitmapFactory.decodeStream(bis);  //通过decodeStream 解析输入流 将URL对应图解析成Bitmap

                //关闭流
                is.close();
                bis.close();


            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //将bitmap作为返回值
            return bitmap;
        }


    }
}
