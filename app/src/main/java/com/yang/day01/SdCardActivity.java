package com.yang.day01;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class SdCardActivity extends AppCompatActivity implements View.OnClickListener{

    int requestCode_write = 1;  //请求码(请求写入)
    int requestCode_read = 2;  //请求码(请求读出)

    EditText et;
    Button btn_writeToSdCard;  //写入SdCard
    Button btn_readFromSdCard; //从sd卡中读取数据
    Button btn_writeImageToSdCard;//向sd卡写图片
    Button btn_readImageFromSdCard;//从sd卡读取图片
    ImageView iv_fromSdCard; //将从sd卡读取的图片显示在ImageView上面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sd_card);

        et = (EditText) findViewById(R.id.et);
        btn_writeToSdCard = (Button) findViewById(R.id.btn_writeToSdCard);
        btn_readFromSdCard = (Button) findViewById(R.id.btn_readFromSdCard);
        btn_writeImageToSdCard = (Button) findViewById(R.id.btn_writeImageToSdCard);
        btn_readImageFromSdCard = (Button) findViewById(R.id.btn_readImageFromSdCard);
        iv_fromSdCard = (ImageView) findViewById(R.id.iv_fromSdCard);

        btn_writeToSdCard.setOnClickListener(this);
        btn_readFromSdCard.setOnClickListener(this);
        btn_writeImageToSdCard.setOnClickListener(this);
        btn_readImageFromSdCard.setOnClickListener(this);




    }

    //写入sd卡
    public void writeToSdCard(){
        //写入数据
        //1、判断sd卡是否可用
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //说明sk卡可用
            //2、获取sd卡路径
            File sdFile = Environment.getExternalStorageDirectory();
            //获得app的文件夹的路径
            File appFile = new File(sdFile+"/xiaoyuanershou");
            if(!appFile.exists()){//如果文件夹不存在，则创建该目录
                appFile.mkdirs();  //如果目录不存在，创建该目录
            }
            //3、获得文件的路径，在sd卡根目录下面app文件夹下的a.txt文件里面
            File path = new File(appFile,"a.txt");
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(path,true);//参数为true表示可以append,即不会覆盖以前写入的东西
                //4、写入数据,先获得EditText里用户写入的数据，转换为字符串，再转换为字节数组
                //字符串转换成字节数组: getBytes()
                fileOutputStream.write(et.getText().toString().getBytes());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(fileOutputStream!=null){
                        fileOutputStream.flush();//刷新，强制将缓冲区的内容写入文件
                        fileOutputStream.close();//关闭流
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }
    }

    //从sd卡读取数据
    public void readFromSdCard(){
        //1、判断sd卡是否可用
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //sd卡可用
            //2、获取sd卡路径
            File sdFile = Environment.getExternalStorageDirectory();
            //获得文件夹路径
            File appFile = new File(sdFile+"/xiaoyuanershou");

            //3、获得文件的完整路径，在sd卡根目录下面app文件夹下的的a.txt文件里面
            File path = new File(appFile,"a.txt");
            FileInputStream fileInputStream = null;
            BufferedReader bufferedReader = null;
            try {
                fileInputStream = new FileInputStream(path);
                //4、读取数据,并把读取的字符append给StringBuilder
//                StringBuilder sb = new StringBuilder();
//                int n = -1;
//                byte[] bt = new byte[100];
//                while((n=fileInputStream.read(bt)) != -1){
//                    String str = new String(bt);  // 这样读写中文就不会出现乱码（原理？？）
//                    sb.append(str);
//                }
                //也可以采用BufferedReader读取数据,可以一次读取一行的数据
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                String str;
                StringBuilder sb = new StringBuilder();
                //读一行数据
                while ((str=bufferedReader.readLine())!=null){
                    sb.append(str);
                }

                Toast.makeText(SdCardActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                    try {
                        if(bufferedReader!=null) {
                            bufferedReader.close();
                        }
                        if(fileInputStream!=null){
                            fileInputStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

            }

        }
    }

    //参数三：授权结果，和参数二对应
    @Override//回调方法
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //如果授权成功,写入sd卡
        if(requestCode==requestCode_write&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            writeToSdCard();
        }else if(requestCode==requestCode_read&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
            readFromSdCard();   //如果授权成功，从sd卡读取出数据
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_writeToSdCard:
                //判断是否是6.0以上的手机，（6.0新特性：需要权限的时候将会弹出提示框让用户选择是否授予权限）
                if (Build.VERSION.SDK_INT >= 23){
                    //1、检查是否有权限
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            !=PackageManager.PERMISSION_GRANTED){
                        //2、申请权限：参数二：权限的数组，参数三：请求码
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},requestCode_write);


                    }else {  //授权过了
                        writeToSdCard();
                    }

                }else { // 不是android6.0以上版本
                    writeToSdCard();
                }

                break;
            case R.id.btn_readFromSdCard:
                if(Build.VERSION.SDK_INT>=23){//跟写入sd卡同理，实际上若在写入sd卡时已经授权，这里就不用再申请授权
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            !=PackageManager.PERMISSION_GRANTED){
                        //申请权限
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},requestCode_read);
                    }else{
                        readFromSdCard();
                    }
                }else{
                    readFromSdCard();
                }
                break;

            case R.id.btn_writeImageToSdCard://往sd卡写入图片(是不是应该另开一个线程去执行该操作？？？)
                //因为写入图片资源需要一定时间，因此需要开启一个子线程执行操作，这样就不影响主线程
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        InputStream is = null;
                        FileOutputStream fos = null;
                        try {
                            //1、读取assets目录下的文件
                            is = getAssets().open("tower.png");
                            //2、判断sd卡是否存在
                            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                                //3、获取sd卡的目录
                                File sdFile = Environment.getExternalStorageDirectory();
                                //4、获取想要存储的文件夹的路径
                                File imageFile = new File(sdFile+"/xiaoyuanershou/image");
                                if(!imageFile.exists()){//如果文件夹不存在，则创建该目录
                                    imageFile.mkdirs();  //如果目录不存在，创建该目录
                                }
                                //5、获得文件的完整路径，创建--》tower.png
                                File f = new File(imageFile,"tower.png");
                                //6、将图片写入文件里面
                                fos = new FileOutputStream(f);
                                int i;
                                while((i=is.read())!=-1){
                                    fos.write(i);
                                }

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                if(fos!=null){
                                    fos.flush();//刷新，强制将缓冲区里面的内容写到文件
                                    fos.close();
                                }
                                if(is!=null){
                                    is.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }.start();
                break;
            case R.id.btn_readImageFromSdCard: //从sd卡读取图片,显示在ImageView上面
                FileInputStream fis = null;
                //判断sd卡是否可用
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    //获取需要读取的图片的路径
                    File sdFile = Environment.getExternalStorageDirectory();
                            File f = new File(sdFile+"/xiaoyuanershou/image/tower.png");
                            try {
                                if(f.exists()){
                                    fis = new FileInputStream(f);
                                    //BitmapFactory: inputStream转换成->bitmap
                                    Bitmap bitmap = BitmapFactory.decodeStream(fis);
                                    //将bitmap展现在ImageView上面
                                    iv_fromSdCard.setImageBitmap(bitmap);
                                } else { //若读取不到，则给用户显示一个读取失败的照片
                            iv_fromSdCard.setImageResource(R.mipmap.head);
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }finally {
                        try {
                            if(fis!=null){
                                fis.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                }


                break;

        }

    }




}
