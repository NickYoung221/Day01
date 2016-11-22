package com.yang.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/** 自定义一个网络通信所需要的工具类（一些框架内层其实就是这样执行的）
 * Created by yang on 2016/9/21 0021.
 */
public class NetUtil {

    //get方式访问服务器，url-访问路径
    public static void getDataByGet(final String urlStr, final Handler handler){

        //开子线程，获取网络数据
        new Thread(){
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                OutputStream outputStream = null;
                InputStream is = null;
                try {
                    URL url = new URL(urlStr);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");//设置请求方式为get
                    httpURLConnection.setConnectTimeout(5000);//设置链接超时时间
                    httpURLConnection.setReadTimeout(5000);//设置读取数据超时时间
                    //请求头信息
                    if(httpURLConnection.getResponseCode()==200){//响应码为200表示相应成功（404，500）
                        //服务器相应成功
                        //读取服务器返回的数据
                        is = httpURLConnection.getInputStream();//json-->String
                        //字节输入流is转换为字符串
                        String str = convertByteToString(is);

                        //创建消息对象
                        Message message = new Message();
                        message.what = 1;//消息标识：（1个handler可以发无数条信息）
                        message.obj = str;//消息携带数据（message.arg1,arg2: 传int类型数据）

                        //使用handler发信息
                        handler.sendMessage(message);

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    //如果没有获得服务器响应，调用getResponseCode()方法就会能抛出IOException（具体看getResponseCode()方法源代码）
                    //这里可以发送一个message“响应失败！”给主线程，主线程可以将其展现给用户
                    Message message = new Message();
                    message.what = 1;
                    message.obj = "响应失败！-get";

                    handler.sendMessage(message);
                    e.printStackTrace();
                }finally {  //关闭流和连接,要判断下，预防出现空指针异常
                    //                        if(outputStream!=null){
//                            outputStream.close();
//                        }
//                        if(is!=null){
//                            is.close();
//                        }
//                        httpURLConnection.disconnect();

                }

                super.run();
            }
        }.start();

    }

    //post方式访问服务器
    public static void getDataByPost(final String urlStr, final Map<String,Object> paramsSet, final Handler handler){
        //开子线程，获取网络数据
        new Thread(){
            @Override
            public void run() {

                HttpURLConnection httpURLConnection = null;
                OutputStream outputStream = null;
                InputStream is = null;
                try {

                    URL url = new URL(urlStr);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");//设置请求方式为get
                    httpURLConnection.setConnectTimeout(5000);//设置链接超时时间
                    //设置post请求的参数
                    outputStream = httpURLConnection.getOutputStream();
                    //outputStream.write(params.getBytes()); //调用方法传参，写入请求体里面(这里的params前面传的是String)
                    //方式二：获得map参数传来的信息（遍历map,取出key-value,调用Map的entrySet()方法）
                    Set<Map.Entry<String,Object>> set = paramsSet.entrySet();//key-value的集合
                    StringBuilder stringBuilder = new StringBuilder();
                    for(Map.Entry<String,Object> entry :set){
                        stringBuilder.append(entry.getKey()+"="+entry.getValue()+"&");
                    }
                    //去掉最后一个&
                    stringBuilder.setLength(stringBuilder.length()-1);
                    //转换成字符串
                    String params = stringBuilder.toString();
                    //调用方法传参，写入请求体里面
                    outputStream.write(params.getBytes());

                    //请求头信息
                    if(httpURLConnection.getResponseCode()==200){//响应码为200表示相应成功（404，500）
                        //服务器相应成功
                        //读取服务器返回的数据
                        is = httpURLConnection.getInputStream();//json-->String
                        //字节输入流is转换为字符串
                        String str = convertByteToString(is);

                        //创建消息对象
                        Message message = new Message();
                        message.what = 2;//消息标识：（1个handler可以发无数条信息）
                        message.obj = str;//消息携带数据（message.arg1,arg2: 传int类型数据）

                        //使用handler发信息
                        handler.sendMessage(message);

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    //与get方法里面同理，捕捉到IOException，可向主线程发送一条message
                    Message message = new Message();
                    message.what = 2;
                    message.obj = "响应失败！-post";

                    handler.sendMessage(message);
                    e.printStackTrace();
                } finally{  //关闭流和连接，不关的话将会占用内存
                    try {
                        if(outputStream!=null){//这里要判断流是否为null，直接关闭的话，若其为null的话出现空指针异常
                            outputStream.close();
                        }
                        if(is!=null){
                            is.close();
                        }
                        httpURLConnection.disconnect();//关闭连接
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                super.run();
            }
        }.start();

    }


    //将字节流转换成字符串
    public static String convertByteToString(InputStream is){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();//内存中创建字节数组
        int i;
        try {
            while ((i=is.read())!=-1){
                baos.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toString();
    }



}
