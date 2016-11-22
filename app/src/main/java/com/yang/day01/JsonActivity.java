package com.yang.day01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yang.entity.Teacher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 将json与对象和集合之间进行转换
 */
public class JsonActivity extends AppCompatActivity {

    @InjectView(R.id.button5)
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ButterKnife.inject(this);


    }

    //对象转换成json
    public String convertToJsonObject(Teacher teacher) {
        //Teacher对象 --> JSONObject
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", teacher.getName());
            jsonObject.put("age", teacher.getAge());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();  //  JSONObject转换为字符串
    }

    //json装换成对象  {"name":"name","age":20}
    public Teacher convertJsonToTeacher(String jsonTeacher) {
        Teacher teacher = new Teacher();
        try {
            JSONObject jsonObject = new JSONObject(jsonTeacher);
            if (jsonObject != null) {
                String name = jsonObject.getString("name");//取出key=name的value
                teacher.setName(name);
                Integer age = jsonObject.getInt("age");
                teacher.setAge(age);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return teacher;
    }

    //集合转换成json
    public String convertListToJson(List<Teacher> teachers) {
        //JSONObject ,JSONArray ：存放最终的结果
        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        //每个元素：JSONObject
        //遍历集合，取出Teacher对象==》JSONObject
        for (Teacher teacher : teachers) {
            //将每一个Teacher对象转换成json字符串
            String eachJsonTeacher = convertToJsonObject(teacher);
            //json对象添加到json数组
            jsonArray.put(eachJsonTeacher);
        }
        try {
            jsonObject.put("teachers", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    //表示集合的jsonList转换成List<Teacher>
    public List<Teacher> convertJsonToList(String jsonList) {
        List<Teacher> teachers = new ArrayList<Teacher>();

        try {
            //传的json是{}
            JSONObject jsonObject = new JSONObject(jsonList);
            JSONArray jsonArray = jsonObject.getJSONArray("teachers");
            //遍历json数组
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject eachJsonTeacher = jsonArray.getJSONObject(i);
                //json对象转换为Teacher对象
                Teacher teacher = convertJsonToTeacher(eachJsonTeacher.toString());
                teachers.add(teacher);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return teachers;
    }


    //按钮点击事件
    public void parse(View v) {
        switch (v.getId()) {
            case R.id.button1://对象转换成json
                Teacher teacher = new Teacher("张三", 20);
                String jsonTeacher = convertToJsonObject(teacher);
                Toast.makeText(JsonActivity.this, jsonTeacher, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2://json装换成对象  {"name":"name","age":20}
                String jsonTeacher2 = "{\"name\":\"name\",\"age\":20}";
                Teacher teacher2 = convertJsonToTeacher(jsonTeacher2);
                Toast.makeText(JsonActivity.this, teacher2.toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3://集合转换成json
                //创建Teacher集合
                List<Teacher> teachers = new ArrayList<Teacher>();
                teachers.add(new Teacher("name1", 21));
                teachers.add(new Teacher("name2", 22));
                teachers.add(new Teacher("name3", 23));
                String jsonTeachers = convertListToJson(teachers);
                Toast.makeText(JsonActivity.this, jsonTeachers, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button4://json转换成集合
                String jsonList = "{\"teachers\":[{\"name\":\"name1\",\"age\":21},{\"name\":\"name2\",\"age\":22},{\"name\":\"name3\",\"age\":23}]}";

                List<Teacher> teachers2 = convertJsonToList(jsonList);
                //遍历集合
                StringBuilder sb = new StringBuilder();
                for (Teacher teacher3 : teachers2) {
                    sb.append(teacher3.toString());
                }
                Toast.makeText(JsonActivity.this, sb, Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @OnClick(R.id.button5)
    public void onClick() {
        Intent intent = new Intent(this,GsonActivity.class);
        startActivity(intent);
    }


}
