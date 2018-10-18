package com.example.user.sql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FireBaseData extends AppCompatActivity {
    FirebaseDatabase database;


    Button fbdbtn1,fbdbtn2,fbdbtn3,fbdbtn4;
    EditText fb_editGetField01,fb_editGetField02,fb_editGetField03
            ,fb_editGetField04,fb_editGetField05,fb_editGetField06
            ,fb_editGetField07;




    DatabaseReference mRef;
    int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_data);

        fbdfend();



    }

    public void fbdfend() {
        fbdbtn1=findViewById(R.id.fbdbtn1);
        fbdbtn2=findViewById(R.id.fbdbtn2);
        fbdbtn3=findViewById(R.id.fbdbtn3);
        fbdbtn4=findViewById(R.id.fbdbtn4);

        fb_editGetField01=(EditText) findViewById(R.id.fbeditGetField01);
        fb_editGetField02=(EditText) findViewById(R.id.fbeditGetField02);
        fb_editGetField03=(EditText) findViewById(R.id.fbeditGetField03);
        fb_editGetField04=(EditText) findViewById(R.id.fbeditGetField04);
        fb_editGetField05=(EditText) findViewById(R.id.fbeditGetField05);
        fb_editGetField06=(EditText) findViewById(R.id.fbeditGetField06);
        fb_editGetField07=(EditText)findViewById(R.id.fbeditGetField07);




    }




    //todo 新增
    public void fbdinsert(View view) {



        mRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference no = mRef.child(fb_editGetField01.getText().toString());
        DatabaseReference no2 = no.child("商品名稱");
        no2.setValue(fb_editGetField02.getText().toString());
        DatabaseReference no3 = no.child("商品規格");
        no3.setValue(fb_editGetField03.getText().toString());
        DatabaseReference no4 = no.child("進貨價格");
        no4.setValue(fb_editGetField04.getText().toString());
        DatabaseReference no5 = no.child("商品定價");
        no5.setValue(fb_editGetField05.getText().toString());
        DatabaseReference no6 = no.child("銷貨價格");
        no6.setValue(fb_editGetField06.getText().toString());
        DatabaseReference no7 = no.child("備註");
        no7.setValue(fb_editGetField07.getText().toString());


        Toast.makeText(this, "資料編號" + fb_editGetField01.getText().toString()+"以新增成功", Toast.LENGTH_SHORT).show();

    }
    //todo 修改
    public void fbdupdata(View view) {



        mRef = FirebaseDatabase.getInstance().getReference();


        DatabaseReference no = mRef.child(fb_editGetField01.getText().toString());
        DatabaseReference no2 = no.child("商品名稱");
        no2.setValue(fb_editGetField02.getText().toString());
        DatabaseReference no3 = no.child("商品規格");
        no3.setValue(fb_editGetField03.getText().toString());
        DatabaseReference no4 = no.child("進貨價格");
        no4.setValue(fb_editGetField04.getText().toString());
        DatabaseReference no5 = no.child("商品定價");
        no5.setValue(fb_editGetField05.getText().toString());
        DatabaseReference no6 = no.child("銷貨價格");
        no6.setValue(fb_editGetField06.getText().toString());
        DatabaseReference no7 = no.child("備註");
        no7.setValue(fb_editGetField07.getText().toString());
        Toast.makeText(this, "資料編號" + fb_editGetField01.getText().toString()+"以修改上傳完畢", Toast.LENGTH_SHORT).show();

    }
    //todo 刪除
    public void fbddelete(View view) {
        mRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference no = mRef.child(fb_editGetField01.getText().toString());

        no.setValue(null);
        Toast.makeText(this, "資料編號" + fb_editGetField01.getText().toString()+"以刪除", Toast.LENGTH_SHORT).show();
    }


    //todo 查詢
    public void fbdselect(View view) {

        mRef = FirebaseDatabase.getInstance().getReference();

        String aaa=fb_editGetField01.getText().toString();
        DatabaseReference  fbdb= mRef.child(aaa);


        DatabaseReference fb2 = fbdb.child("商品名稱");
        DatabaseReference fb3 = fbdb.child("商品規格");
        DatabaseReference fb4 = fbdb.child("進貨價格");
        DatabaseReference fb5 = fbdb.child("商品定價");
        DatabaseReference fb6 = fbdb.child("銷貨價格");
        DatabaseReference fb7 = fbdb.child("備註");




        //todo  讀取商品名稱
        fb2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField02.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //todo  讀取商品規格
        fb3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField03.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        //todo  讀取進貨價格
        fb4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField04.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //todo  讀取商品定價
        fb5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField05.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //todo 讀取銷貨價格
        fb6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               String value = dataSnapshot.getValue(String.class);
                fb_editGetField06.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //Todo 讀取備註
        fb7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                fb_editGetField07.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        }

    public void uplist(View view) {
        int sum=Integer.parseInt(fb_editGetField01.getText().toString());
        sum++;


        mRef = FirebaseDatabase.getInstance().getReference();

        //String bbb = Integer.toString(sum);
        DatabaseReference  fbdb= mRef.child(Integer.toString(sum));

        fb_editGetField01.setText(Integer.toString(sum));
        DatabaseReference fb2 = fbdb.child("商品名稱");
        DatabaseReference fb3 = fbdb.child("商品規格");
        DatabaseReference fb4 = fbdb.child("進貨價格");
        DatabaseReference fb5 = fbdb.child("商品定價");
        DatabaseReference fb6 = fbdb.child("銷貨價格");
        DatabaseReference fb7 = fbdb.child("備註");




        //todo  讀取商品名稱
        fb2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField02.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //todo  讀取商品規格
        fb3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField03.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        //todo  讀取進貨價格
        fb4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField04.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //todo  讀取商品定價
        fb5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField05.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //todo 讀取銷貨價格
        fb6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField06.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //Todo 讀取備註
        fb7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                fb_editGetField07.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


    }

    public void downlist(View view) {

        int sum1=Integer.parseInt(fb_editGetField01.getText().toString());
        sum1--;


        mRef = FirebaseDatabase.getInstance().getReference();

        //String bbb = Integer.toString(sum);
        DatabaseReference  fbdb= mRef.child(Integer.toString(sum1));

        fb_editGetField01.setText(Integer.toString(sum1));
        DatabaseReference fb2 = fbdb.child("商品名稱");
        DatabaseReference fb3 = fbdb.child("商品規格");
        DatabaseReference fb4 = fbdb.child("進貨價格");
        DatabaseReference fb5 = fbdb.child("商品定價");
        DatabaseReference fb6 = fbdb.child("銷貨價格");
        DatabaseReference fb7 = fbdb.child("備註");




        //todo  讀取商品名稱
        fb2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField02.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //todo  讀取商品規格
        fb3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField03.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        //todo  讀取進貨價格
        fb4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField04.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //todo  讀取商品定價
        fb5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField05.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //todo 讀取銷貨價格
        fb6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                fb_editGetField06.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //Todo 讀取備註
        fb7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
                fb_editGetField07.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
    public void fbgomain(View view) {

        Intent firebase=new Intent(FireBaseData.this,MainActivity.class);
        startActivity(firebase);
        finish();
    }
}






