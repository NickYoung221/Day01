package com.yang.day01;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yang.interesting.InterestingActivity;

public class ButtonsActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_A;
    Button btn_B;
    Button btn_C;
    Button btn_toDialogActivity;
    TextView tv_click;
    Button btn_Notification;
    Button btn_cancel;
    Button btn_PopupWindow;
    Button btn_Toolbar;
    Button btn_DrawerLayout;
    Button btn_firstFragment;
    Button btn_dyncFragment;
    Button btn_toFragmentsActivity;
    Button btn_sdCard;
    Button btn_toBtns2;
    Button to_interesting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);

        btn_A = (Button) findViewById(R.id.btn_A);
        btn_B = (Button) findViewById(R.id.btn_B);
        btn_C = (Button) findViewById(R.id.btn_C);
        btn_toDialogActivity = (Button) findViewById(R.id.btn_toDialogActivity);
        tv_click = (TextView) findViewById(R.id.tv_click);
        btn_Notification = (Button) findViewById(R.id.btn_Notification);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_PopupWindow = (Button) findViewById(R.id.btn_PopupWindow);
        btn_Toolbar = (Button) findViewById(R.id.btn_Toolbar);
        btn_DrawerLayout = (Button) findViewById(R.id.btn_DrawerLayout);
        btn_firstFragment = (Button) findViewById(R.id.btn_firstFragment);
        btn_dyncFragment = (Button) findViewById(R.id.btn_dyncFragment);
        btn_toFragmentsActivity = (Button) findViewById(R.id.btn_toFragmentsActivity);
        btn_sdCard = (Button) findViewById(R.id.btn_sdCard);
        btn_toBtns2 = (Button) findViewById(R.id.btn_toBtns2);
        to_interesting = (Button) findViewById(R.id.to_interesting);

        btn_A.setOnClickListener(this);
        btn_B.setOnClickListener(this);
        btn_C.setOnClickListener(this);
        btn_toDialogActivity.setOnClickListener(this);
        tv_click.setOnClickListener(this);
        btn_Notification.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_PopupWindow.setOnClickListener(this);
        btn_Toolbar.setOnClickListener(this);
        btn_DrawerLayout.setOnClickListener(this);
        btn_firstFragment.setOnClickListener(this);
        btn_dyncFragment.setOnClickListener(this);
        btn_toFragmentsActivity.setOnClickListener(this);
        btn_sdCard.setOnClickListener(this);
        btn_toBtns2.setOnClickListener(this);
        to_interesting.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){   //根据控件的id来判断
            case R.id.btn_A:
                tv_click.setText("A按钮被点击");
                break;
            case R.id.btn_B:
                tv_click.setText("B按钮被点击");
                break;
            case R.id.btn_C:
                tv_click.setText("C按钮被点击");
                break;
            case R.id.btn_toDialogActivity:
                Intent intent1 = new Intent(ButtonsActivity.this,DialogActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_Notification:   //弹出通知
                //NotificationManager对象     getSystemService:获取系统服务  （activity,broadcast,service）
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                //点击通知内容后，跳转到LoginActivity中  //怎么设置点击通知后取消弹出的通知？？？
                Intent intent = new Intent(this,LoginActivity.class);
                //创建PendingIntent对象    里面参数含义？？？
                PendingIntent pendingIntent = PendingIntent.getActivity(this,1,intent,0);
                //创建Notification对象

                //setContentIntent:点击content,intent是什么？？（PendingIntent）   设置图标好像没有用??
                Notification notification = new Notification.Builder(this).setSmallIcon(R.mipmap.mima)
                        .setContentTitle("中奖通知").setContentText("恭喜你中奖了！").setContentIntent(pendingIntent)
                        .build();     // @TargetApi(Build.VERSION_CODES.JELLY_BEAN)  ??
                //notify:id:如果两个通知id相同，后面会覆盖前面的
                notificationManager.notify(1,notification);

                break;
            case R.id.btn_cancel:    //取消弹出的通知
                //NotificationManager对象
                NotificationManager notificationManager2 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                //参数：通知id
                notificationManager2.cancel(1);
                break;
            case R.id.btn_PopupWindow:  //点击到PopupWindowActivity
                Intent intent3 = new Intent(this,MyPopupWindowActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_Toolbar:   //点击到ToolbarActivity
                Intent intent2 = new Intent(this,ToolbarActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_DrawerLayout:
                Intent intent4 = new Intent(this,DrawerLayoutActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_firstFragment:
                Intent intent5 = new Intent(this,FirstFragmentActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_dyncFragment:
                Intent intent6 = new Intent(this,DyncFragmentActivity.class);
                startActivity(intent6);
                break;
            case R.id.btn_toFragmentsActivity:
                Intent intent7 = new Intent(this,FragmentsActivity.class);
                startActivity(intent7);
                break;
            case R.id.btn_sdCard: //跳转到进行sdCard操作的页面
                Intent intent8 = new Intent(this,SdCardActivity.class);
                startActivity(intent8);
                break;
            case R.id.btn_toBtns2:
                Intent intent9 = new Intent(this,Buttons2Activity.class);
                startActivity(intent9);
                break;
            case R.id.to_interesting:
                Intent intent10 = new Intent(this, InterestingActivity.class);
                startActivity(intent10);
                break;
        }
        if(v==tv_click){    //也可以根据控件的对象来判断
            tv_click.setText("貌似点到了不该点的东西");
        }
    }
}
