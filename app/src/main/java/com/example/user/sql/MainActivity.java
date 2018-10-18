package com.example.user.sql;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private GridView gridView;
    private int[] image = {
            R.drawable.rich, R.drawable.notes, R.drawable.users,
    };
    private String[] imgText = {
            "Sqlite管理", "MySql管理", "firebase管理", "個人介紹"
    };

    ImageView xxx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SqliteClientProcess obj_POSDBCreate=new SqliteClientProcess(this.getApplicationContext(),"PosSqliteMainDB");
        SQLiteDatabase obj_PosDatabase=obj_POSDBCreate.getReadableDatabase();
        obj_PosDatabase.close();
        obj_POSDBCreate.close();
        Toast.makeText(MainActivity.this, "資料庫已建立", Toast.LENGTH_SHORT).show();




        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("image", image[i]);
            item.put("text", imgText[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                items, R.layout.grid_item, new String[]{"image", "text"},
                new int[]{R.id.image, R.id.text});
        gridView = (GridView) findViewById(R.id.main_page_gridview);
        gridView.setNumColumns(3);
        gridView.setAdapter(adapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "你選擇了" + imgText[position], Toast.LENGTH_SHORT).show();
      switch (imgText[position]) {
             case  "Sqlite管理":
                 Intent objlntentPage=new Intent();
                 objlntentPage.setClass(MainActivity.this,productcreateprocess.class);
                 startActivity(objlntentPage);
                 finish();
                 break;
             case "firebase管理":
                 Intent firebase=new Intent(MainActivity.this,FireBaseData.class);
                 startActivity(firebase);
                 finish();
                break;
              case "個人介紹":
                  Intent webemail=new Intent(MainActivity.this,email.class);
                  startActivity(webemail);
                  finish();
             break;
             case "MySql管理":
                 Intent mysql=new Intent(MainActivity.this,mysql.class);
                 startActivity(mysql);
                 finish();
             break;
//             case :
//             break;
//             case :
//             break;
//             case :
//             break;
//             case :
//             break;
//
             default:
                 System.out.println("1111111111");
        }



            }
        });


    }
}