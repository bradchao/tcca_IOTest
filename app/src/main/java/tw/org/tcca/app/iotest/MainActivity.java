package tw.org.tcca.app.iotest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }
    public void test4(View view) {
    }
}
