[1mdiff --git a/app/src/main/java/com/example/aprivate/custom_sqlite/EditCards.java b/app/src/main/java/com/example/aprivate/custom_sqlite/EditCards.java[m
[1mindex 1b0e472..8ebf1bb 100644[m
[1m--- a/app/src/main/java/com/example/aprivate/custom_sqlite/EditCards.java[m
[1m+++ b/app/src/main/java/com/example/aprivate/custom_sqlite/EditCards.java[m
[36m@@ -38,24 +38,23 @@[m [mpublic class EditCards extends AppCompatActivity {[m
         mAddButton.setOnClickListener(new View.OnClickListener() {[m
             @Override[m
             public void onClick(View v) {[m
[31m-                writeInBd();[m
[31m-                readBd(mSQL);[m
[32m+[m[32m                writeInDb();[m
[32m+[m[32m                readDb(mSQL);[m
             }[m
         });[m
 [m
[31m-        //TODO[m
         mDeleteAllButton = (Button) findViewById(R.id.delete_all);[m
[31m-        mDeleteAllButton.setEnabled(false);[m
         mDeleteAllButton.setOnClickListener(new View.OnClickListener() {[m
             @Override[m
             public void onClick(View v) {[m
                 //deleteDatabase(BaseShema.CardTable.TABLE_NAME);[m
[31m-                deleteAll(mSQL);[m
[32m+[m[32m                deleteTarget(mSQL, BaseShema.Cols.UUID);[m
[32m+[m[32m                //deleteAll(mSQL);[m
             }[m
         });[m
     }[m
 [m
[31m-    void writeInBd(){[m
[32m+[m[32m    void writeInDb(){[m
         mSQL = mDbHelper.getWritableDatabase();[m
 [m
         Cards cards = new Cards();[m
[36m@@ -72,7 +71,7 @@[m [mpublic class EditCards extends AppCompatActivity {[m
         mSQL.insert(BaseShema.CardTable.TABLE_NAME, null, values);[m
     }[m
 [m
[31m-    void readBd(SQLiteDatabase db){[m
[32m+[m[32m    void readDb(SQLiteDatabase db){[m
         db = mDbHelper.getReadableDatabase();[m
 [m
         String projection [] = {[m
[36m@@ -112,8 +111,22 @@[m [mpublic class EditCards extends AppCompatActivity {[m
         }[m
     }[m
 [m
[31m-    private void deleteAll(SQLiteDatabase mSQL) {[m
[31m-        String target = BaseShema.Cols.UUID;[m
[31m-        mSQL.delete(BaseShema.CardTable.TABLE_NAME, target, null);[m
[32m+[m[32m    private void deleteAll(SQLiteDatabase db) {[m
[32m+[m[32m        BaseHelper baseHelper = new BaseHelper(this);[m
[32m+[m[32m        db = baseHelper.getWritableDatabase();[m
[32m+[m[32m        db.delete(BaseShema.CardTable.TABLE_NAME, null, null);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    private void deleteTarget(SQLiteDatabase db, String target){[m
[32m+[m[32m        BaseHelper baseHelper = new BaseHelper(this);[m
[32m+[m[32m        db = baseHelper.getWritableDatabase();[m
[32m+[m[32m        String command = "173aca6d-7e1b-4f7b-9f71-e6fa7e15abd8";[m
[32m+[m[32m        String test = BaseShema.Cols.UUID +[m
[32m+[m[32m                " =" +[m
[32m+[m[32m                "'" +[m
[32m+[m[32m                command +[m
[32m+[m[32m                "'";[m
[32m+[m
[32m+[m[32m        db.delete(BaseShema.CardTable.TABLE_NAME, test, null);[m
     }[m
 }[m
[1mdiff --git a/app/src/main/java/com/example/aprivate/custom_sqlite/MainActivity.java b/app/src/main/java/com/example/aprivate/custom_sqlite/MainActivity.java[m
[1mindex 685b4ec..f15c094 100644[m
[1m--- a/app/src/main/java/com/example/aprivate/custom_sqlite/MainActivity.java[m
[1m+++ b/app/src/main/java/com/example/aprivate/custom_sqlite/MainActivity.java[m
[36m@@ -7,8 +7,6 @@[m [mimport android.view.View;[m
 import android.widget.Button;[m
 [m
 public class MainActivity extends AppCompatActivity {[m
[31m-    private static final String TAG = "MainActivity========= /:";[m
[31m-[m
     private Button mGoToEditButton;[m
 [m
     @Override[m
