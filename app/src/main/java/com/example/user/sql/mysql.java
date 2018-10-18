package com.example.user.sql;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class mysql extends AppCompatActivity {

    Button obj_btnCloseProductWindw, obj_btnDataPointer01, obj_btnDataPointer02, obj_btnDataPointer03, obj_btnDataPointer04, obj_btnApplication01, obj_btnApplication02, obj_btnApplication03, obj_btnApplication04;
    EditText obj_editGetField01, obj_editGetField02, obj_editGetField03, obj_editGetField04, obj_editGetField05, obj_editGetField06, obj_editGetField07;
    private TextView obj_txtDisplayShowField01;
    private TextView obj_txtDisplayShowField02;
    private TextView obj_txtDisplayShowField03;
    private TextView obj_txtDisplayShowField04;
    private TextView obj_txtDisplayShowField05;
    private TextView obj_txtDisplayShowField06;
    private TextView obj_txtDisplayShowField07;
    String objstr_ed02, objstr_ed03, objstr_ed07,objstr_ed04, objstr_ed05, objstr_ed06;


    int obint_ed04,obint_ed05,obint_ed06;
    Handler handler=null;

    String sql;

    private String dbDriver = "com.mysql.jdbc.Driver";
    private String dbUrl = "jdbc:mysql://localhost:3306/possqlitemaindb";//根据实际情况变化
    private String dbUser = "root";
    private String dbPass = "1234";

    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);//注意是三个参数
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql);
        find();
    }


    public void find() {

        obj_editGetField01 = (EditText) findViewById(R.id.editGetField01);

        obj_editGetField02 = (EditText) findViewById(R.id.editGetField02);

        obj_editGetField03 = (EditText) findViewById(R.id.editGetField03);

        obj_editGetField04 = (EditText) findViewById(R.id.editGetField04);

        obj_editGetField05 = (EditText) findViewById(R.id.editGetField05);
        obj_editGetField06 = (EditText) findViewById(R.id.editGetField06);
        obj_editGetField07 = (EditText) findViewById(R.id.editGetField07);

        objstr_ed02 = obj_editGetField02.getText().toString().trim();
        objstr_ed03 = obj_editGetField03.getText().toString().trim();
        objstr_ed04 =obj_editGetField04.getText().toString().trim();
        objstr_ed05 =obj_editGetField05.getText().toString().trim();
        objstr_ed06 =obj_editGetField06.getText().toString().trim();
        objstr_ed07 = obj_editGetField07.getText().toString().trim();

        obj_btnApplication01 = (Button) findViewById(R.id.btnApplication01);
        obj_btnApplication02 = (Button) findViewById(R.id.btnApplication02);
        obj_btnApplication03 = (Button) findViewById(R.id.btnApplication03);
        //obj_btnApplication04=(Button)findViewById(R.id.btnApplication04);

        obj_btnDataPointer01 = (Button) findViewById(R.id.btnDataPointer01);
        obj_btnDataPointer02 = (Button) findViewById(R.id.btnDataPointer02);
        obj_btnDataPointer03 = (Button) findViewById(R.id.btnDataPointer03);
        obj_btnDataPointer04 = (Button) findViewById(R.id.btnDataPointer04);

        obj_btnCloseProductWindw = (Button) findViewById(R.id.btnCloseProductWindows);

        obj_txtDisplayShowField01 = (TextView) findViewById(R.id.txtDisplayShowField01);
        obj_txtDisplayShowField02 = (TextView) findViewById(R.id.txtDisplayShowField02);
        obj_txtDisplayShowField03 = (TextView) findViewById(R.id.txtDisplayShowField03);
        obj_txtDisplayShowField04 = (TextView) findViewById(R.id.txtDisplayShowField04);
        obj_txtDisplayShowField05 = (TextView) findViewById(R.id.txtDisplayShowField05);
        obj_txtDisplayShowField06 = (TextView) findViewById(R.id.txtDisplayShowField06);
        obj_txtDisplayShowField07 = (TextView) findViewById(R.id.txtDisplayShowField07);
    }
        public void findint(){
            objstr_ed02 = obj_editGetField02.getText().toString().trim();
            objstr_ed03 = obj_editGetField03.getText().toString().trim();
            obint_ed04=Integer.parseInt(obj_editGetField04.getText().toString());
            obint_ed05=Integer.parseInt(obj_editGetField04.getText().toString());
            obint_ed06=Integer.parseInt(obj_editGetField04.getText().toString());
            objstr_ed07 = obj_editGetField07.getText().toString().trim();
        }


    public void mysqlinster(View view)  {
            findint();
        new Thread()
        {
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/possqlitemaindb";
                    Connection conn = DriverManager.getConnection(url, "root", "1234");
                    Statement stmt = conn.createStatement();
                    String sql ="INSERT INTO `posproductmainstore`" +
                "(`ProdltemID`,`ProdltemName`,`ProdltemDesc`,`ProdltemCost`,`ProdltemListP`,`ProdltemPrice`,`ProdltemRemark`)VALUES" +
                "(NULL,'" + objstr_ed02 + "','" + objstr_ed03 + "','" + obint_ed04 +
                "','" + obint_ed05 + "','" + obint_ed06 + "','" + objstr_ed07 + "')";
                   stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close();
                    Log.v("final", "success to connect!");
                }catch(ClassNotFoundException e)
                {
                    Log.v("final", "fail to connect!"+"  "+e.getMessage());
                } catch (SQLException e)
                {
                    Log.v("final", "fail to connect!"+"  "+e.getMessage());
                }
            };
        }.start();
        Toast.makeText(mysql.this, "資料建立成功", Toast.LENGTH_SHORT).show();
    }


    public void no1(View view) {
        find();
        new Thread() {




                @Override
                public void run () {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/possqlitemaindb";
                        Connection conn = DriverManager.getConnection(url, "root", "1234");
                        Statement stmt = conn.createStatement();
                        String sql = "SELECT * FROM `posproductmainstore` WHERE `ProdltemID`=1";//查询表名为“table_test”的所有内容
                        ResultSet rs = stmt.executeQuery(sql);//ResultSet类似Cursor



                        while (rs.next()) {//<code>ResultSet</code>最初指向第一行
                            final String prodltemid=Integer.toString(rs.getInt("ProdltemID"));
                            final String prodltemname=rs.getNString("ProdltemName");
                            final String prodltemdesc=rs.getNString("ProdltemDesc");
                                     final String prodltemcost=Integer.toString(rs.getInt("ProdltemCost"));
                                    final String prodltemlLisp=Integer.toString(rs.getInt("ProdltemListP"));
                                    final String prodltemprice=Integer.toString(rs.getInt("ProdltemPrice"));
                            final String prodltemremark=rs.getNString("ProdltemRemark");
                           // final String name=rs.getString("ProdltemName");
                            obj_editGetField01.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                                @Override public void run() {
                                    obj_editGetField01.setText(prodltemid);//更新textview
                                }
                            });

                            obj_editGetField02.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                                @Override public void run() {
                                    obj_editGetField02.setText(prodltemname);//更新textview
                                }
                            });

                            obj_editGetField03.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                                @Override public void run() {
                                    obj_editGetField03.setText(prodltemdesc);//更新textview
                                }
                            });
                            obj_editGetField04.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                                @Override public void run() {
                                    obj_editGetField04.setText(prodltemcost);//更新textview
                                }
                            });

                            obj_editGetField05.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                                @Override public void run() {
                                    obj_editGetField05.setText(prodltemlLisp);//更新textview
                                }
                            });

                            obj_editGetField06.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                                @Override public void run() {
                                    obj_editGetField06.setText(prodltemprice);//更新textview
                                }
                            });

                            obj_editGetField07.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                                @Override public void run() {
                                    obj_editGetField07.setText(prodltemremark);//更新textview
                                }
                            });
                        }
                        rs.close();
                        stmt.close();
                        conn.close();
                        Log.v("final", "success to connect!");
                    } catch (ClassNotFoundException e) {
                        Log.v("final", "fail to connect!" + "  " + e.getMessage());
                    } catch (SQLException e) {
                        Log.v("final", "fail to connect!" + "  " + e.getMessage());
                    }
                }



        }.start();
    }


    public void atlast(View view) {
        find();
        new Thread() {




            @Override
            public void run () {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/possqlitemaindb";
                    Connection conn = DriverManager.getConnection(url, "root", "1234");
                    Statement stmt = conn.createStatement();
                    String sql = "SELECT * from `posproductmainstore` ORDER BY`ProdltemID`DESC LIMIT 1";//查询表名为“table_test”的所有内容
                    ResultSet rs = stmt.executeQuery(sql);//ResultSet类似Cursor



                    while (rs.next()) {//<code>ResultSet</code>最初指向第一行
                        final String prodltemid=Integer.toString(rs.getInt("ProdltemID"));
                        final String prodltemname=rs.getNString("ProdltemName");
                        final String prodltemdesc=rs.getNString("ProdltemDesc");
                        final String prodltemcost=Integer.toString(rs.getInt("ProdltemCost"));
                        final String prodltemlLisp=Integer.toString(rs.getInt("ProdltemListP"));
                        final String prodltemprice=Integer.toString(rs.getInt("ProdltemPrice"));
                        final String prodltemremark=rs.getNString("ProdltemRemark");
                        // final String name=rs.getString("ProdltemName");
                        obj_editGetField01.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField01.setText(prodltemid);//更新textview
                            }
                        });

                        obj_editGetField02.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField02.setText(prodltemname);//更新textview
                            }
                        });

                        obj_editGetField03.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField03.setText(prodltemdesc);//更新textview
                            }
                        });
                        obj_editGetField04.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField04.setText(prodltemcost);//更新textview
                            }
                        });

                        obj_editGetField05.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField05.setText(prodltemlLisp);//更新textview
                            }
                        });

                        obj_editGetField06.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField06.setText(prodltemprice);//更新textview
                            }
                        });

                        obj_editGetField07.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField07.setText(prodltemremark);//更新textview
                            }
                        });
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                    Log.v("final", "success to connect!");
                } catch (ClassNotFoundException e) {
                    Log.v("final", "fail to connect!" + "  " + e.getMessage());
                } catch (SQLException e) {
                    Log.v("final", "fail to connect!" + "  " + e.getMessage());
                }
            }



        }.start();
    }

    public void select(View view) {



        new Thread() {




            @Override
            public void run () {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/possqlitemaindb";
                    Connection conn = DriverManager.getConnection(url, "root", "1234");
                    Statement stmt = conn.createStatement();
                    String aaa=obj_editGetField01.getText().toString();
                    //int aaa=Integer.parseInt(obj_editGetField01.getText().toString().trim());
                    String sql = "SELECT * FROM `posproductmainstore`WHERE `ProdltemID`="+aaa;//查询表名为“table_test”的所有内容
                    ResultSet rs = stmt.executeQuery(sql);//ResultSet类似Curso



                    while (rs.next()) {//<code>ResultSet</code>最初指向第一行
                        //final String prodltemid=Integer.toString(rs.getInt("ProdltemID"));
                        final String prodltemname=rs.getNString("ProdltemName");
                        final String prodltemdesc=rs.getNString("ProdltemDesc");
                        final String prodltemcost=Integer.toString(rs.getInt("ProdltemCost"));
                        final String prodltemlLisp=Integer.toString(rs.getInt("ProdltemListP"));
                        final String prodltemprice=Integer.toString(rs.getInt("ProdltemPrice"));
                        final String prodltemremark=rs.getNString("ProdltemRemark");
                        // final String name=rs.getString("ProdltemName");
//                        obj_editGetField01.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
//                            @Override public void run() {
//                                obj_editGetField01.setText(prodltemid);//更新textview
//                            }
//                        });

                        obj_editGetField02.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField02.setText(prodltemname);//更新textview
                            }
                        });

                        obj_editGetField03.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField03.setText(prodltemdesc);//更新textview
                            }
                        });
                        obj_editGetField04.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField04.setText(prodltemcost);//更新textview
                            }
                        });

                        obj_editGetField05.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField05.setText(prodltemlLisp);//更新textview
                            }
                        });

                        obj_editGetField06.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField06.setText(prodltemprice);//更新textview
                            }
                        });

                        obj_editGetField07.post(new Runnable() {//子线程post方法更新UI，否则会FALUT抛出
                            @Override public void run() {
                                obj_editGetField07.setText(prodltemremark);//更新textview
                            }
                        });
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                    Log.v("final", "success to connect!");
                } catch (ClassNotFoundException e) {
                    Log.v("final", "fail to connect!" + "  " + e.getMessage());
                } catch (SQLException e) {
                    Log.v("final", "fail to connect!" + "  " + e.getMessage());
                }
            }



        }.start();




    }

    public void clean(View view) {
        obj_editGetField01.setText("");
        obj_editGetField02.setText("");
        obj_editGetField03.setText("");
        obj_editGetField04.setText("");
        obj_editGetField05.setText("");
        obj_editGetField06.setText("");
        obj_editGetField07.setText("");

    }

    public void gohome(View view) {

        Intent firebase=new Intent(mysql.this,MainActivity.class);
        startActivity(firebase);
        finish();
    }

    public void updata(View view) {
        find();
        new Thread()
        {
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/possqlitemaindb";
                    Connection conn = DriverManager.getConnection(url, "root", "1234");
                    Statement stmt = conn.createStatement();
                    String aaa=obj_editGetField01.getText().toString();
                    String sql ="UPDATE `posproductmainstore` SET " +
                            "`ProdltemName`='"+objstr_ed02+
                            "',`ProdltemDesc`='"+ objstr_ed03+
                            "',`ProdltemCost`=" + objstr_ed04+
                            ",`ProdltemListP`=" + objstr_ed05+
                            ",`ProdltemPrice`="+ objstr_ed06+
                            ",`ProdltemRemark`='"+objstr_ed07+
                            "' WHERE `ProdltemID`=" + aaa;

                    stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close();
                    Log.v("final", "success to connect!");
                }catch(ClassNotFoundException e)
                {
                    Log.v("final", "fail to connect!"+"  "+e.getMessage());
                } catch (SQLException e)
                {
                    Log.v("final", "fail to connect!"+"  "+e.getMessage());
                }
            };
        }.start();
        Toast.makeText(mysql.this, "資料更新成功", Toast.LENGTH_SHORT).show();

    }

    public void del(View view) {

        findint();
        new Thread()
        {
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/possqlitemaindb";
                    Connection conn = DriverManager.getConnection(url, "root", "1234");
                    String aaa=obj_editGetField01.getText().toString();
                    Statement stmt = conn.createStatement();
                    String sql ="delete from `posproductmainstore` where `ProdltemID`=" +aaa;

                    stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close();
                    Log.v("final", "success to connect!");
                }catch(ClassNotFoundException e)
                {
                    Log.v("final", "fail to connect!"+"  "+e.getMessage());
                } catch (SQLException e)
                {
                    Log.v("final", "fail to connect!"+"  "+e.getMessage());
                }
            };
        }.start();
        Toast.makeText(mysql.this, "資料建立成功", Toast.LENGTH_SHORT).show();


    }
}