package com.yang.contentProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.yang.day01.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ContactInfoActivity extends AppCompatActivity {

    @InjectView(R.id.btn_readContactInfo)
    Button btnReadContactInfo;
    @InjectView(R.id.lv)
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        ButterKnife.inject(this);


    }


    @OnClick(R.id.btn_readContactInfo)
    public void onClick() {
        //若没有权限，则需要向用户申请权限：android6.0新特性
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            int requestCode = 1;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, requestCode);
        } else {//若已有权限，则读取联系人信息，显示在ListView上（最好写成一个方法，防止代码冗余，因为下面申请权限的回调里还需要写）
            //获取联系人的URI:ContactsContract.Contacts.CONTENT_URI
            Uri uri = ContactsContract.Contacts.CONTENT_URI;
            //查询uri的两个字段的取值:id和name
            // URI分别为：ContactsContract.Contacts._ID和ContactsContract.Contacts.DISPLAY_NAME（后面参数含义？）
            Cursor cursor = getContentResolver().query(uri, new String[]{ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);

            //创建map集合，用来存放每个用户的姓名和电话号码
            List<Map<String, String>> contactInfo = new ArrayList<Map<String, String>>();
            while (cursor.moveToNext()) {
                //创建map对象，用来存放当前用户的姓名和电话号码
                Map<String, String> map = new HashMap<String, String>();

                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                //根据_id,查找电话字段取值，电话数据库表的URI为：ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                /**
                 * 联系人id: ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                 * 电话： ContactsContract.CommonDataKinds.Phone.NUMBER
                 */
                Cursor cursor1 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);

                //遍历cursor1，取出当前用户所有的电话号码（这里拼凑成一个字符串,多个电话间用空格连接）
                String phoneNum = "";
                while (cursor1.moveToNext()) {
                    phoneNum += cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) + "  ";
                }
                //将联系人姓名和电话号码存放在map里面
                map.put("name", displayName);
                map.put("phoneNum", phoneNum);

                contactInfo.add(map); //将map添加到集合
                cursor1.close();
            }
            cursor.close();
            //将map显示在ListView上面，使用SimpleAdapter
            lv.setAdapter(new SimpleAdapter(this, contactInfo, android.R.layout.simple_expandable_list_item_2,
                    new String[]{"name", "phoneNum"}, new int[]{android.R.id.text1, android.R.id.text2}));

        }
    }


    //申请权限后的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1&&grantResults[0]==PackageManager.PERMISSION_GRANTED){//申请权限成功后，读取联系人信息，显示在ListView上
            //获取联系人的URI:ContactsContract.Contacts.CONTENT_URI
            Uri uri = ContactsContract.Contacts.CONTENT_URI;
            //查询uri的两个字段的取值:id和name
            // URI分别为：ContactsContract.Contacts._ID和ContactsContract.Contacts.DISPLAY_NAME（后面参数含义？）
            Cursor cursor = getContentResolver().query(uri, new String[]{ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);

            //创建map集合，用来存放每个用户的姓名和电话号码
            List<Map<String, String>> contactInfo = new ArrayList<Map<String, String>>();
            while (cursor.moveToNext()) {
                //创建map对象，用来存放当前用户的姓名和电话号码
                Map<String, String> map = new HashMap<String, String>();

                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                //根据_id,查找电话字段取值，电话数据库表的URI为：ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                /**
                 * 联系人id: ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                 * 电话： ContactsContract.CommonDataKinds.Phone.NUMBER
                 */
                Cursor cursor1 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);

                //遍历cursor1，取出当前用户所有的电话号码（这里拼凑成一个字符串,多个电话间用空格连接）
                String phoneNum = "";
                while (cursor1.moveToNext()) {
                    phoneNum += cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)) + "  ";
                }
                //将联系人姓名和电话号码存放在map里面
                map.put("name", displayName);
                map.put("phoneNum", phoneNum);

                contactInfo.add(map); //将map添加到集合
                cursor1.close();
            }
            cursor.close();
            //将map显示在ListView上面，使用SimpleAdapter
            lv.setAdapter(new SimpleAdapter(this, contactInfo, android.R.layout.simple_expandable_list_item_2,
                    new String[]{"name", "phoneNum"}, new int[]{android.R.id.text1, android.R.id.text2}));
        }
    }






}
