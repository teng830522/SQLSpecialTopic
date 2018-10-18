package com.example.user.sql;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SqliteClientProcess extends SQLiteOpenHelper {

    private static final int vs = 1;
    private static final String DATABASE_NAME = "PosSqliteMainDB.db";
    int i=0;

    public SqliteClientProcess(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);


    }

    public SqliteClientProcess(Context context, String name) {
        this(context, name, null, vs);

        //this(context,name,null,VERSION);

    }

    public SqliteClientProcess(Context context, String name, int version) {
        this(context, name, null, vs);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        CreatePosProductMainStoreTable(db);
        CreatePosPayMoneyTypeTable(db);
        CreateLocalAreaRecordTable(db);
        CreatePosSalesMainRecordTable(db);
        CreatePosSalesDetailRecordTable(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public synchronized void close() {
        super.close();
    }


    private void CreatePosSalesDetailRecordTable(SQLiteDatabase db) {
        String sCreeatePosProductMainStore =
                "CREATE TABLE IF NOT EXISTS PosSalesDetailRecord(" +
                        "PosSalesDetaID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "PosSalesMainID INTEGER" +
                        "ProdltemID INTEGER" +
                        "ProdltemListP INTEGER" +
                        "ProdltemPrice INTEGER" +
                        "ProdltemCose INTEGER" +
                        "ProdltemQty INTEGER" +
                        "PriceTotal INTEGER" +
                        "ltemRemark TEXT)";
        db.execSQL(sCreeatePosProductMainStore.trim());
    }

    private void CreateLocalAreaRecordTable(SQLiteDatabase db) {
        String sCreeatePosProductMainStore =
                "CREATE TABLE IF NOT EXISTS LocalAreaRecord(" +
                        "LocalAreaID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "LocalAreaName TEXT" +
                        "LocalAreaRemark TEXT)";
        db.execSQL(sCreeatePosProductMainStore.trim());
    }

    private void CreatePosSalesMainRecordTable(SQLiteDatabase db) {

        String sCreeatePosProductMainStore =
                "CREATE TABLE IF NOT EXISTS PosSalesMainRecor(" +
                        "PosSaleMainID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "SaleMainCode TEXT," +
                        "SaleMainDate TEXT," +
                        "CustomName TEXT," +
                        "CustLocalArea INTEGER," +
                        "PayTypeID INTEGER," +
                        "CreditCardID TEXT" +
                        "SalesRemark TEXT" +
                        "SalesTotalQty INTEGER" +
                        "SalesTotalCost INTEGER" +
                        "SalesTotalPrice INTEGER)";
        db.execSQL(sCreeatePosProductMainStore.trim());
    }

    private void CreatePosPayMoneyTypeTable(SQLiteDatabase db) {

        String sCreeatePosProductMainStore =
                "CREATE TABLE IF NOT EXISTS PosPayMoneyType(" +
                        "PayTypeID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "PayTypeName TEXT" +
                        "PayTypeRemark TEXT)";
        db.execSQL(sCreeatePosProductMainStore.trim());

    }

    private void CreatePosProductMainStoreTable(SQLiteDatabase db) {

        String sCreeatePosProductMainStore =
                "CREATE TABLE IF NOT EXISTS PosProductMainStore(" +
                        "ProdltemID INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "ProdltemName TEXT," +
                        "ProdltemDesc TEXT," +
                        "ProdltemCost INTEGER," +
                        "ProdltemListP INTEGER," +
                        "ProdltemPrice INTEGER," +
                        "ProdltemRemark TEXT)";
        db.execSQL(sCreeatePosProductMainStore.trim());

    }

    public void InsertUpdateDelPosProductMainStoreTable(
            SQLiteDatabase db, int pProcessMode, int pProdltemID, String pProdltemName, String pProdltemDesc,
            int pProdltemCost, int pProdltemListP, int pProdltemPrice, String pProdltemRemark) {
        String sCreaeSQLCommand = "";

        switch (pProcessMode) {
            case 1:
                sCreaeSQLCommand = "INSTERT INTO PosProductMainStore" +
                        "(ProdltemListP,ProdltemPricemProdltemRemark)VALUES" +
                        "(" + pProdltemName + "," + pProdltemDesc + "," + pProdltemCost + "," + pProdltemListP + "," + pProdltemPrice + "," + pProdltemRemark + ")";

                db.execSQL(sCreaeSQLCommand.trim());
                break;
            case 2:
                sCreaeSQLCommand = "UPDATE PosProductMainStore SET" +
                        "ProdltemName=" + pProdltemName.trim() + "," +
                        "ProdltemDesc=" + pProdltemDesc.trim() + "," +
                        "ProdltemCost=" + pProdltemCost + "," +
                        "ProdltemListP=" + pProdltemListP + "," +
                        "ProdltemPrice=" + pProdltemPrice + "," +
                        "ProdltemRemark=" + pProdltemRemark + "," +
                        "WHERE ProdltemID=" + pProdltemID;
                db.execSQL(sCreaeSQLCommand.trim());
                break;
            case 3:
                sCreaeSQLCommand = "DELETE FROM PosProductMainStore" +
                        "WHERE ProdltemID=" + pProdltemID;
                db.execSQL(sCreaeSQLCommand.trim());
                break;
            default:
                break;
        }
    }

    public void InsertUpdateDelPosPayMoneyTypeTable(
            SQLiteDatabase db, int pProcessMode, int pPayTypeID, String pPayTypeName, String pPayTypeRemark) {
        String sCreaeSQLCommand = "";

        switch (pProcessMode) {
            case 1:
                sCreaeSQLCommand = "INSTERT INTO PosPayMoneyType" +
                        "(PayTypeName,PayTypeRemark)VALUES" +
                        "(" + pPayTypeName + "," + pPayTypeRemark + ")";

                db.execSQL(sCreaeSQLCommand.trim());
                break;
            case 2:
                sCreaeSQLCommand = "UPDATE PosPayMoneyType SET " +
                        "PayTypeName=" + pPayTypeName + "," +
                        "PayTypeRemark=" + pPayTypeRemark + "," +
                        "WHERE PayTypeID=" + pPayTypeID;
                db.execSQL(sCreaeSQLCommand.trim());
                break;
            case 3:
                sCreaeSQLCommand = "DELETE FROM PosPayMoneyType" +
                        "WHERE PayTypeID=" + pPayTypeID;
                db.execSQL(sCreaeSQLCommand.trim());
                break;
            default:
                break;
        }
    }

    public void InsertUpdateDelLocalAreaRecordTable(
            SQLiteDatabase db, int pProcessMode, int pLocalAreaID, String pLocalAreaName, String pLocalAreaRemark) {
        String sCreaeSQLCommand = "";

        switch (pProcessMode) {
            case 1:
                sCreaeSQLCommand = "INSTERT INTO LocalAreaRecord" +
                        "(LocalAreaName,LocalAreaRemark)VALUES" +
                        "(" + pLocalAreaName + "," + pLocalAreaRemark + ")";

                db.execSQL(sCreaeSQLCommand.trim());
                break;
            case 2:
                sCreaeSQLCommand = "UPDATE LocalAreaRecord SET " +
                        "LocalAreaNAME=" + pLocalAreaName + "," +
                        "LocalAreamark=" + pLocalAreaRemark + "," +
                        "WHERE LocalAreaID=" + pLocalAreaID;
                db.execSQL(sCreaeSQLCommand.trim());
                break;
            case 3:
                sCreaeSQLCommand = "DELETE FROM LocalAreaRecord" +
                        "WHERE LocalAreaID=" + pLocalAreaID;
                db.execSQL(sCreaeSQLCommand.trim());
                break;
            default:
                break;
        }
    }


    public Cursor SelectPosProductMainStoreTable(
            SQLiteDatabase db, int pProdltemID, String pProdltemName, String pProdltemDesc
    ) {
        String sCreaeSQLCommand = "";

        if (pProdltemID == -99) {
            sCreaeSQLCommand = "SELECT*FROM PosProductMainStore";
        } else {
            sCreaeSQLCommand = "SELECT*FROM PosProductMainStore" +
                    "WHERE ProdltemID=" + pProdltemID + "And" +
                    "ProdltemName LIKE %" + pProdltemName.trim() + "% AND" +
                    "ProdltemDesc LIKE %" + pProdltemDesc.trim() + "%";

        }
        Cursor obj_QuerySelectResult = db.rawQuery(sCreaeSQLCommand.trim(), null);
        return obj_QuerySelectResult;
    }

    public Cursor SelectPosPayMoneyTypeTable(
            SQLiteDatabase db, int pPayTypeID, String pPayTypeName, String pPayTypeRemark
    ) {
        String sCreaeSQLCommand = "";

        if (pPayTypeID == -99) {
            sCreaeSQLCommand = "SELECT*FROM PosPayMoneyType";
        } else {
            sCreaeSQLCommand = "SELECT*FROM PosPayMoneyType" +
                    "WHERE PayTypeID=" + pPayTypeID + "And" +
                    "PayTypeName LIKE %" + pPayTypeName.trim() + "% AND" +
                    "PayTypeRemark LIKE %" + pPayTypeRemark.trim() + "%";

        }
        Cursor obj_QuerySelectResult = db.rawQuery(sCreaeSQLCommand.trim(), null);
        return obj_QuerySelectResult;
    }


    public Cursor SelectLocalAreaRecordTable(
            SQLiteDatabase db, int pLocalAreaID, String pLocalAreaName, String pLocalAreaRemark
    ) {
        String sCreaeSQLCommand = "";

        if (pLocalAreaID == -99) {
            sCreaeSQLCommand = "SELECT*FROM LocalAreaRecord";
        } else {
            sCreaeSQLCommand = "SELECT*FROM LocalAreaRecord" +
                    "WHERE LocalAreaID=" + pLocalAreaID + "And" +
                    "LocalAreaName LIKE %" + pLocalAreaName.trim() + "% AND" +
                    "LocalAreaRemark LIKE %" + pLocalAreaRemark.trim() + "%";

        }
        Cursor obj_QuerySelectResult = db.rawQuery(sCreaeSQLCommand.trim(), null);
        return obj_QuerySelectResult;
    }

    public Cursor InsertUpdateDelPosProductMainStoreTableCursor(
            SQLiteDatabase db, int pProcessMode, int pProdltemID,
            String pProdltemName, String pProdltemDesc, int pProdltemCost,
            int pProdltemListP, int pProdltemPrice, String pProdltemRemark) {
        String sCreateSQLCommand = "";
        Cursor obj_ReturnValuesCursor;
        switch (pProcessMode) {
            case 1:
                sCreateSQLCommand = "INSERT INTO PosProductMainStore" +
                        "('ProdltemName','ProdltemDesc','ProdltemCost','ProdltemListP','ProdltemPrice','ProdltemRemark')VALUES" +
                        "('" + pProdltemName + "','" + pProdltemDesc + "','" + pProdltemCost +
                        "','" + pProdltemListP + "','" + pProdltemPrice + "','" + pProdltemRemark + "')";


                db.execSQL(sCreateSQLCommand.trim());
                break;


            case 2:
                sCreateSQLCommand = "UPDATE PosProductMainStore SET " +
                        "ProdltemName='" + pProdltemName.trim() + "'," +
                        "ProdltemDesc='" + pProdltemDesc.trim() + "'," +
                        "ProdltemCost=" + pProdltemCost + "," +
                        "ProdltemListP=" + pProdltemListP + "," +
                        "ProdltemPrice=" + pProdltemPrice + "," +
                        "ProdltemRemark='" + pProdltemRemark + "' " +
                        "WHERE ProdltemID=" + pProdltemID;

                db.execSQL(sCreateSQLCommand.trim());
                break;
            case 3:
                sCreateSQLCommand = "DELETE FROM PosProductMainStore" +
                        " WHERE ProdltemID="+ pProdltemID;
                db.execSQL(sCreateSQLCommand.trim());
                break;
            default:
                break;

        }
            obj_ReturnValuesCursor=SelectPosProductMainStoreTable(db,-99,"","");
            return obj_ReturnValuesCursor;
    }


//    public int sqlInsertUpdateDelPosProductMainStoreTableCursor(
//             int pProcessMode, int pProdltemID,
//            String pProdltemName, String pProdltemDesc, int pProdltemCost,
//            int pProdltemListP, int pProdltemPrice, String pProdltemRemark) {
//
//        String sCreateSQLCommand = "";
//        Cursor obj_ReturnValuesCursor;
//        switch (pProcessMode) {
//            case 1:
//                sCreateSQLCommand = "INSERT INTO posproductmainstore" +
//                        "('ProdltemName','ProdltemDesc','ProdltemCost','ProdltemListP','ProdltemPrice','ProdltemRemark')VALUES" +
//                        "('" + pProdltemName + "','" + pProdltemDesc + "','" + pProdltemCost +
//                        "','" + pProdltemListP + "','" + pProdltemPrice + "','" + pProdltemRemark + "')";
//
//
//                Connection cnn = getConn();
//
//                try {
//                    //PreparedStatement preStmt =cnn.prepareStement(sql);
//                    PreparedStatement preStmt = cnn.prepareStatement(sCreateSQLCommand);
//
//                    preStmt.setString(1, pProdltemName);
//                    preStmt.setString(2, pProdltemDesc);
//                    preStmt.setInt(3, pProdltemCost);
//                    preStmt.setInt(4, pProdltemListP);
//                    preStmt.setInt(5, pProdltemPrice);
//                    preStmt.setString(6, pProdltemRemark);
//
//
//
//                    //或者：preStmt.setInt(1,值);
//                    i = preStmt.executeUpdate();
//                    return i;
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                ////返回影响的行数，1为执行成功
//
//
//            //db.execSQL(sCreateSQLCommand.trim());
//            break;
//
//
//            case 2:
//                sCreateSQLCommand = "UPDATE PosProductMainStore SET " +
//                        "ProdltemName='" + pProdltemName.trim() + "'," +
//                        "ProdltemDesc='" + pProdltemDesc.trim() + "'," +
//                        "ProdltemCost=" + pProdltemCost + "," +
//                        "ProdltemListP=" + pProdltemListP + "," +
//                        "ProdltemPrice=" + pProdltemPrice + "," +
//                        "ProdltemRemark='" + pProdltemRemark + "' " +
//                        "WHERE ProdltemID=" + pProdltemID;
//
//
//                break;
//            case 3:
//                sCreateSQLCommand = "DELETE FROM PosProductMainStore" +
//                        " WHERE ProdltemID=" + pProdltemID;
//
//                break;
//            default:
//                break;
//
//        }
////        obj_ReturnValuesCursor = SelectPosProductMainStoreTable(db, -99, "", "");
//
//
//
//    }


    private String dbDriver="com.mysql.jdbc.Driver";
    private String dbUrl="jdbc:mysql://10.0.2.2/possqlitemaindb";//根据实际情况变化
    private String dbUser="root";
    private String dbPass="1234";
    public Connection getConn()
    {
        Connection conn=null;
        try
        {
            Class.forName(dbDriver);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try
        {
            conn = DriverManager.getConnection(dbUrl,dbUser,dbPass);//注意是三个参数
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }
}









