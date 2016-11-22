package com.yang.day01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yang.entity.Student;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Gson解析
 * 使用Gson插件可以更方便的进行Json与对象，集合之间的转换
 * 这里解析的同样是activity_json这个布局，与JsonActivity共用一个布局文件
 */
public class GsonActivity extends AppCompatActivity {

    @InjectView(R.id.button1)
    Button button1;
    @InjectView(R.id.button2)
    Button button2;
    @InjectView(R.id.button3)
    Button button3;
    @InjectView(R.id.button4)
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1://对象转换成json
                com.yang.entity.Student student = new Student("张三",1,21);

                Gson gson = new Gson();
                String jsonStu = gson.toJson(student);
                Log.i("Gson", "onClick: "+jsonStu);
                Toast.makeText(GsonActivity.this, jsonStu, Toast.LENGTH_SHORT).show();

                break;
            case R.id.button2://json转换成对象
                String jsonStu2 = "{\"stuAge\":21,\"stuId\":1,\"stuName\":\"张三\"}";

                Gson gson3 = new Gson();
                //Student student3 = gson3.fromJson(jsonStu2,Student.class);
                //也可以使用这种方式进行解析,指定一个type，这种方式更为常用
                Type type = new TypeToken<Student>(){}.getType();
                Student student3 = gson3.fromJson(jsonStu2,type);
                Toast.makeText(GsonActivity.this, student3.toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3://集合转换为json
                List<Student> students = new ArrayList<Student>();
                students.add(new Student("张三",1,21));
                students.add(new Student("李四",2,22));
                students.add(new Student("王五",3,23));

                Gson gson2 = new Gson();
                String jsonStus = gson2.toJson(students);
                Log.i("Gson", "onClick: "+jsonStus);
                Toast.makeText(GsonActivity.this, jsonStus, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4://json转换成集合 [{"stuAge":21,"stuId":1,"stuName":"张三"},{"stuAge":22,"stuId":2,"stuName":"李四"},{"stuAge":23,"stuId":3,"stuName":"王五"}]
//                String jsonStus2 = "[{\"stuAge\":21,\"stuId\":1,\"stuName\":\"张三\"},{\"stuAge\":22,\"stuId\":2,\"stuName\":\"李四\"},{\"stuAge\":23,\"stuId\":3,\"stuName\":\"王五\"}]";
//                Gson gson4 = new Gson();
//                //List<Student>:指定要解析成什么类型
//                Type type2 = new TypeToken<List<Student>>(){}.getType();
//                List<Student> students2 = gson4.fromJson(jsonStus2,type2);//只能解析json数组
//
//                StringBuilder sb = new StringBuilder();
//                for(Student stu:students2){
//                    sb.append(stu.toString());
//                }
//                Toast.makeText(GsonActivity.this, sb, Toast.LENGTH_SHORT).show();

                //上面情况是json是一个数组，下面这种情况json是一个map，map的value是一个数组（有可能数组里面还会嵌套数组，例如学生还有一个班级属性class{classId=1,className="三年二班"}，同样也是可以解析出来的），
                String jsonStus2 = "{\"students\":[{\"stuAge\":21,\"stuId\":1,\"stuName\":\"张三\"},{\"stuAge\":22,\"stuId\":2,\"stuName\":\"李四\"},{\"stuAge\":23,\"stuId\":3,\"stuName\":\"王五\"}]}";
                Gson gson4 = new Gson();
                //要解析成的类型是一个map(key为：students，value为：学生对象的集合)
                Type type2 = new TypeToken<Map<String,List<Student>>>(){}.getType();
                Map<String,List<Student>> stusMap = gson4.fromJson(jsonStus2,type2);
                List<Student> students2 = stusMap.get("students");

                StringBuilder sb = new StringBuilder();
                for(Student stu:students2){
                    sb.append(stu.toString());
                }
                Toast.makeText(GsonActivity.this, sb, Toast.LENGTH_SHORT).show();


                //  Json里面是日期格式该怎么处理？？？
//                Gson gson5 = new GsonBuilder().enableComplexMapKeySerialization()
//                        .setPrettyPrinting().disableHtmlEscaping()
//                        .setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                break;
        }
    }
}
