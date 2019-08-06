package tw.org.tcca.app.iotest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private File sdroot,approot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    12);
        }else{
            init();
        }
    }

    private void init(){
        sdroot = Environment.getExternalStorageDirectory();
        Log.v("brad", sdroot.getAbsolutePath());

        approot = new File(sdroot, "Android/data/" + getPackageName());
        if (!approot.exists()) approot.mkdirs();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 12){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                init();
            }else{
                finish();
            }
        }


    }

    public void test1(View view) {
        try {
            FileOutputStream fout =
                    openFileOutput("brad.txt", MODE_PRIVATE);
            fout.write("Hello, World".getBytes());
            fout.flush();
            fout.close();
        }catch (Exception e){
            Log.v("brad", e.toString());
        }
    }
    public void test2(View view) {
        try {
            FileInputStream fin = openFileInput("brad.txt");
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(fin));
            String line = null;
            while ( (line = reader.readLine()) != null){
                Log.v("brad", line);
            }
            fin.close();
        }catch (Exception e){
            Log.v("brad", e.toString());
        }

    }
    public void test3(View view) {
        File file1 = new File(sdroot, "file1.txt");
        try {
            FileOutputStream fout = new FileOutputStream(file1);
            fout.write("Hello, World".getBytes());
            fout.flush();
            fout.close();
            Log.v("brad", "save OK1");
        }catch (Exception e){
            Log.v("brad", e.toString());
        }
    }
    public void test4(View view) {
        File file1 = new File(approot, "file1.txt");
        try {
            FileOutputStream fout = new FileOutputStream(file1);
            fout.write("Hello, World".getBytes());
            fout.flush();
            fout.close();
            Log.v("brad", "save OK2");
        }catch (Exception e){
            Log.v("brad", e.toString());
        }
    }
}
