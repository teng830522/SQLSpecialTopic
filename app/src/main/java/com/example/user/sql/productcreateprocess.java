package com.example.user.sql;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class productcreateprocess extends AppCompatActivity {

    SQLiteDatabase DB_MainPOSSqlite_Database,DB_SaveProcessSQLite_DB;
    Cursor DB_CURSOR_forProductMain_Table;
    SqliteClientProcess POS_DB_SQLITE_COMPONENT;
    Boolean bool_OPEN_DATABASE_SUCCESS=false;
    Button obj_btnCloseProductWindw,obj_btnDataPointer01,obj_btnDataPointer02
            ,obj_btnDataPointer03,obj_btnDataPointer04,obj_btnApplication01
            ,obj_btnApplication02,obj_btnApplication03,obj_btnApplication04;
    EditText obj_editGetField01,obj_editGetField02,obj_editGetField03
            ,obj_editGetField04,obj_editGetField05,obj_editGetField06
            ,obj_editGetField07;
    private TextView obj_txtDisplayShowField01;
    private TextView obj_txtDisplayShowField02;
    private TextView obj_txtDisplayShowField03;
    private TextView obj_txtDisplayShowField04;
    private TextView obj_txtDisplayShowField05;
    private TextView obj_txtDisplayShowField06;
    private TextView obj_txtDisplayShowField07;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productcreateprocess);

        find();

        obj_btnDataPointer();
        obj_btnApplication();

        bool_OPEN_DATABASE();



    }

    private void bool_OPEN_DATABASE() {
        bool_OPEN_DATABASE_SUCCESS=OpenPOSDatabaseAndCursor();
        ApplicationButtonEnaabledControl(0);
        EditTextObjectEnable(false);
        if(!bool_OPEN_DATABASE_SUCCESS){
            AlertDialog.Builder obj_ExitAlertDialog=new AlertDialog.Builder(this);
            obj_ExitAlertDialog.setTitle("資料庫開啟錯誤");
            obj_ExitAlertDialog.setMessage("無法開啟POS資料庫,不能執行產品維護");
            obj_ExitAlertDialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    productcreateprocess.this.finish();
                }
            });
            obj_ExitAlertDialog.show();
        }
    }

    private Boolean OpenPOSDatabaseAndCursor() {
     boolean bool_OpenSqliteDataBase=false;
     try{
         POS_DB_SQLITE_COMPONENT=new SqliteClientProcess(productcreateprocess.this.getApplicationContext(),"possqlitemaindb");

         DB_MainPOSSqlite_Database=POS_DB_SQLITE_COMPONENT.getReadableDatabase();

         DB_SaveProcessSQLite_DB=POS_DB_SQLITE_COMPONENT.getWritableDatabase();

         DB_CURSOR_forProductMain_Table=POS_DB_SQLITE_COMPONENT.SelectPosProductMainStoreTable(DB_MainPOSSqlite_Database,-99,"","");

         if(DB_CURSOR_forProductMain_Table.getCount()>0){
             DB_CURSOR_forProductMain_Table.moveToNext();
             GetCurrentColumnData();
     }
     bool_OpenSqliteDataBase=true;

     }catch (Exception e){
         bool_OpenSqliteDataBase=false;
     }
     return bool_OpenSqliteDataBase;
    }

    private void obj_btnApplication() {
        obj_btnApplication01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int iTextEdit01Values=(obj_editGetField01.getText().toString().trim().equals("")?0:
                        Integer.parseInt(obj_editGetField01.getText().toString().trim()));
                String sTextEdit02Values=obj_editGetField02.getText().toString().trim();
                String sTextEdit03Values=obj_editGetField03.getText().toString().trim();
                int iTextEdit04Values=(obj_editGetField04.getText().toString().trim().equals("")?0:
                        Integer.parseInt(obj_editGetField04.getText().toString().trim()));
                int iTextEdit05Values=(obj_editGetField05.getText().toString().trim().equals("")?0:
                        Integer.parseInt(obj_editGetField05.getText().toString().trim()));
                int iTextEdit06Values=(obj_editGetField06.getText().toString().trim().equals("")?0:
                        Integer.parseInt(obj_editGetField06.getText().toString().trim()));
                String sTextEdit07Values=obj_editGetField07.getText().toString().trim();
            if(obj_btnApplication01.getText().toString().trim().equals("新增")){
                ApplicationButtonEnaabledControl(1);
                ClearColumnsObjectlnfo();
                EditTextObjectEnable(true);
                }else{
                    if(obj_editGetField01.getText().toString().trim().equals("")){
                    //todo 新增產品
                        DB_CURSOR_forProductMain_Table=POS_DB_SQLITE_COMPONENT.
                              InsertUpdateDelPosProductMainStoreTableCursor(DB_SaveProcessSQLite_DB,
                            1,
                             iTextEdit01Values,
                             sTextEdit02Values.trim(),
                             sTextEdit03Values.trim(),
                            iTextEdit04Values,
                            iTextEdit05Values,
                            iTextEdit06Values,
                           sTextEdit07Values.trim());

                    }else{
                        //todo 修改產品
                        DB_CURSOR_forProductMain_Table=POS_DB_SQLITE_COMPONENT.
                              InsertUpdateDelPosProductMainStoreTableCursor(DB_SaveProcessSQLite_DB,
                                        2,
                                      iTextEdit01Values,
                                      sTextEdit02Values.trim(),
                                      sTextEdit03Values.trim(),
                                      iTextEdit04Values,
                                      iTextEdit05Values,
                                      iTextEdit06Values,
                                      sTextEdit07Values.trim());

//                        DB_CURSOR_forProductMain_Table=POS_DB_SQLITE_COMPONENT. InsertUpdateDelPosProductMainStoreTableCursor(
//                                        DB_SaveProcessSQLite_DB,
//                                        2,
//                                        iTextEdit01Values,
//                                        sTextEdit02Values.trim(),
//                                        sTextEdit03Values.trim(),
//                                        iTextEdit04Values,
//                                        iTextEdit05Values,
//                                        iTextEdit06Values,
//                                        sTextEdit07Values.trim());

                    }
                  ApplicationButtonEnaabledControl(0);
                    EditTextObjectEnable(false);
                    if(DB_CURSOR_forProductMain_Table.getCount()>0){
                        DB_CURSOR_forProductMain_Table.moveToFirst();
                        while (!DB_CURSOR_forProductMain_Table.isAfterLast()){
                            if(DB_CURSOR_forProductMain_Table.getString(1).toString().trim().equals(sTextEdit02Values.trim()) &&
                               DB_CURSOR_forProductMain_Table.getString(2).toString().trim().equals(sTextEdit03Values.trim())){
                                GetCurrentColumnData();
                                break;
                            }else{
                                DB_CURSOR_forProductMain_Table.moveToNext();
                            }
                        }
                    }
                }
            }
        });

        obj_btnApplication02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(obj_btnApplication02.getText().toString().trim().equals("修改")) {
                    ApplicationButtonEnaabledControl(1);
                    GetCurrentColumnData();
                    EditTextObjectEnable(true);
                }else{
                    ApplicationButtonEnaabledControl(0);
                    GetCurrentColumnData();
                    EditTextObjectEnable(false);


                }
            }
        });

        obj_btnApplication03.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(DB_CURSOR_forProductMain_Table.getCount()>0){
                    if(!obj_editGetField01.getText().toString().trim().equals("")){
                        AlertDialog.Builder obj_ExitAlertDialogA=new AlertDialog.Builder(productcreateprocess.this);
                        obj_ExitAlertDialogA.setTitle("刪除產品資料");
                        obj_ExitAlertDialogA.setMessage("確定刪除產品："+obj_editGetField02.getText().toString().trim()+"?");
                        obj_ExitAlertDialogA.setPositiveButton("確定刪除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DB_CURSOR_forProductMain_Table=POS_DB_SQLITE_COMPONENT.InsertUpdateDelPosProductMainStoreTableCursor(
                                        DB_SaveProcessSQLite_DB,3,obj_editGetField01.getText().toString().trim().equals("")?0:
                                                Integer.parseInt(obj_editGetField01.getText().toString().trim()),"","",0,0,0,"");
                                                ClearColumnsObjectlnfo();
                                        if(DB_CURSOR_forProductMain_Table.getCount()>0){
                                            DB_CURSOR_forProductMain_Table.moveToNext();
                                        }


                            }
                        });
                        obj_ExitAlertDialogA.setNegativeButton("放棄刪除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                    obj_ExitAlertDialogA.show();
                    }
                }
            }
        });

    }




    private void obj_btnDataPointer() {
        obj_btnDataPointer01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DB_CURSOR_forProductMain_Table.getCount()>0){
                    DB_CURSOR_forProductMain_Table.moveToFirst();
                    GetCurrentColumnData();
                    }}
            });


        obj_btnDataPointer02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DB_CURSOR_forProductMain_Table.getCount()>0){
                   if(!DB_CURSOR_forProductMain_Table.isFirst()){
                       DB_CURSOR_forProductMain_Table.moveToPrevious();
                   } else {
                       DB_CURSOR_forProductMain_Table.moveToFirst();
                   }
                    GetCurrentColumnData();
                }}
                });


        obj_btnDataPointer03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DB_CURSOR_forProductMain_Table.getCount()>0){
                    if(!DB_CURSOR_forProductMain_Table.isLast()){
                        DB_CURSOR_forProductMain_Table.moveToNext();
                    } else{
                        DB_CURSOR_forProductMain_Table.moveToLast();
                    }
                        GetCurrentColumnData();
                }}});

        obj_btnDataPointer04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DB_CURSOR_forProductMain_Table.getCount()>0){
                    DB_CURSOR_forProductMain_Table.moveToLast();
                    GetCurrentColumnData();
                }}});

    }



    private void find() {
        obj_editGetField01=(EditText)findViewById(R.id.editGetField01);
        obj_editGetField01.setEnabled(false);
        obj_editGetField02=(EditText)findViewById(R.id.editGetField02);
        obj_editGetField03=(EditText)findViewById(R.id.editGetField03);
        obj_editGetField04=(EditText)findViewById(R.id.editGetField04);
        obj_editGetField05=(EditText)findViewById(R.id.editGetField05);
        obj_editGetField06=(EditText)findViewById(R.id.editGetField06);
        obj_editGetField07=(EditText)findViewById(R.id.editGetField07);

        obj_btnApplication01=(Button)findViewById(R.id.btnApplication01);
        obj_btnApplication02=(Button)findViewById(R.id.btnApplication02);
        obj_btnApplication03=(Button)findViewById(R.id.btnApplication03);
        //obj_btnApplication04=(Button)findViewById(R.id.btnApplication04);

        obj_btnDataPointer01=(Button)findViewById(R.id.btnDataPointer01);
        obj_btnDataPointer02=(Button)findViewById(R.id.btnDataPointer02);
        obj_btnDataPointer03=(Button)findViewById(R.id.btnDataPointer03);
        obj_btnDataPointer04=(Button)findViewById(R.id.btnDataPointer04);

        obj_btnCloseProductWindw=(Button)findViewById(R.id.btnCloseProductWindows);

        obj_txtDisplayShowField01=(TextView)findViewById(R.id.txtDisplayShowField01);
        obj_txtDisplayShowField02=(TextView)findViewById(R.id.txtDisplayShowField02);
        obj_txtDisplayShowField03=(TextView)findViewById(R.id.txtDisplayShowField03);
        obj_txtDisplayShowField04=(TextView)findViewById(R.id.txtDisplayShowField04);
        obj_txtDisplayShowField05=(TextView)findViewById(R.id.txtDisplayShowField05);
        obj_txtDisplayShowField06=(TextView)findViewById(R.id.txtDisplayShowField06);
        obj_txtDisplayShowField07=(TextView)findViewById(R.id.txtDisplayShowField07);


    }



    private void GetCurrentColumnData() {
        Toast.makeText(this.getApplicationContext(),DB_CURSOR_forProductMain_Table.getString(1)+"-"+
                DB_CURSOR_forProductMain_Table.getString(2), Toast.LENGTH_LONG).show();
        ClearColumnsObjectlnfo();
        obj_editGetField01.setText(String.valueOf(DB_CURSOR_forProductMain_Table.getInt(0)));
        obj_editGetField02.setText(DB_CURSOR_forProductMain_Table.getString(1));
        obj_editGetField03.setText(DB_CURSOR_forProductMain_Table.getString(2));
        obj_editGetField04.setText(String.valueOf(DB_CURSOR_forProductMain_Table.getInt(3)));
        obj_editGetField05.setText(String.valueOf(DB_CURSOR_forProductMain_Table.getInt(4)));
        obj_editGetField06.setText(String.valueOf(DB_CURSOR_forProductMain_Table.getInt(5)));
        obj_editGetField07.setText(DB_CURSOR_forProductMain_Table.getString(6));
        obj_txtDisplayShowField01.setText(String.valueOf(DB_CURSOR_forProductMain_Table.getInt(0)));
        obj_txtDisplayShowField02.setText(DB_CURSOR_forProductMain_Table.getString(1));
        obj_txtDisplayShowField03.setText(DB_CURSOR_forProductMain_Table.getString(2));
        obj_txtDisplayShowField04.setText(String.valueOf(DB_CURSOR_forProductMain_Table.getInt(3)));
        obj_txtDisplayShowField05.setText(String.valueOf(DB_CURSOR_forProductMain_Table.getInt(4)));
        obj_txtDisplayShowField06.setText(String.valueOf(DB_CURSOR_forProductMain_Table.getInt(5)));
        obj_txtDisplayShowField07.setText(DB_CURSOR_forProductMain_Table.getString(6));

    }


    private void ApplicationButtonEnaabledControl(int para_iProgressMode) {
        if(para_iProgressMode ==0){
            obj_btnApplication01.setVisibility(View.VISIBLE);
            obj_btnApplication02.setVisibility(View.VISIBLE);
            obj_btnApplication03.setVisibility(View.VISIBLE);
           // obj_btnApplication04.setVisibility(View.VISIBLE);
            obj_btnApplication01.setText("新增");
            obj_btnApplication02.setText("修改");

        }else{
            obj_btnApplication01.setVisibility(View.VISIBLE);
            obj_btnApplication02.setVisibility(View.VISIBLE);
            obj_btnApplication03.setVisibility(View.INVISIBLE);
           // obj_btnApplication04.setVisibility(View.INVISIBLE);
            obj_btnApplication01.setText("儲存資料");
            obj_btnApplication02.setText("放棄儲存");

        }


    }


    private void EditTextObjectEnable(Boolean para_BoolEditTextEnabled) {
        if(para_BoolEditTextEnabled){
            obj_editGetField01.setVisibility(View.VISIBLE);
            obj_editGetField02.setVisibility(View.VISIBLE);
            obj_editGetField03.setVisibility(View.VISIBLE);
            obj_editGetField04.setVisibility(View.VISIBLE);
            obj_editGetField05.setVisibility(View.VISIBLE);
            obj_editGetField06.setVisibility(View.VISIBLE);
            obj_editGetField07.setVisibility(View.VISIBLE);

            obj_txtDisplayShowField01.setVisibility(View.GONE);
            obj_txtDisplayShowField02.setVisibility(View.GONE);
            obj_txtDisplayShowField03.setVisibility(View.GONE);
            obj_txtDisplayShowField04.setVisibility(View.GONE);
            obj_txtDisplayShowField05.setVisibility(View.GONE);
            obj_txtDisplayShowField06.setVisibility(View.GONE);
            obj_txtDisplayShowField07.setVisibility(View.GONE);

        } else {

            obj_editGetField01.setVisibility(View.GONE);
            obj_editGetField02.setVisibility(View.GONE);
            obj_editGetField03.setVisibility(View.GONE);
            obj_editGetField04.setVisibility(View.GONE);
            obj_editGetField05.setVisibility(View.GONE);
            obj_editGetField06.setVisibility(View.GONE);
            obj_editGetField07.setVisibility(View.GONE);

            obj_txtDisplayShowField01.setVisibility(View.VISIBLE);
            obj_txtDisplayShowField02.setVisibility(View.VISIBLE);
            obj_txtDisplayShowField03.setVisibility(View.VISIBLE);
            obj_txtDisplayShowField04.setVisibility(View.VISIBLE);
            obj_txtDisplayShowField05.setVisibility(View.VISIBLE);
            obj_txtDisplayShowField06.setVisibility(View.VISIBLE);
            obj_txtDisplayShowField07.setVisibility(View.VISIBLE);

        }

    }

    private void ClearColumnsObjectlnfo() {
        obj_editGetField07.setText("");
        obj_editGetField06.setText("");
        obj_editGetField05.setText("");
        obj_editGetField04.setText("");
        obj_editGetField03.setText("");
        obj_editGetField02.setText("");
        obj_editGetField01.setText("");
        obj_txtDisplayShowField07.setText("");
        obj_txtDisplayShowField06.setText("");
        obj_txtDisplayShowField05.setText("");
        obj_txtDisplayShowField04.setText("");
        obj_txtDisplayShowField03.setText("");
        obj_txtDisplayShowField02.setText("");
        obj_txtDisplayShowField01.setText("");

    }

    public void gohome(View view) {
        Intent gohome=new Intent();
        gohome.setClass(productcreateprocess.this,MainActivity.class);
        startActivity(gohome);

    }


    protected void onDestroy() {
        super.onDestroy();
        if(bool_OPEN_DATABASE_SUCCESS){
            DB_CURSOR_forProductMain_Table.close();
            DB_SaveProcessSQLite_DB.close();
            DB_MainPOSSqlite_Database.close();
            bool_OPEN_DATABASE_SUCCESS=false;
            }
    }


//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//          return true;
//        }
//
//    public boolean onOptionsLtemSelected(MenuItem item){
//        int id=item.getItemId();
//
//        if(id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//}
    }
