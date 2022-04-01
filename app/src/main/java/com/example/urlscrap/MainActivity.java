package com.example.urlscrap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText ed1;
    Button button;
    private ArrayList<UrlName> urlList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textview);
        ed1=(EditText)findViewById(R.id.edittext);
        button=(Button)findViewById(R.id.btn);
        recyclerView=(RecyclerView)findViewById(R.id.recycle);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
        Python py = Python.getInstance();

        PyObject pyobj = py.getModule("pythonscript");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = ed1.getText().toString().trim();
                urlList = new ArrayList<>();
                String[] n = pyobj.callAttr("main",url).toJava(String[].class);


            //Toast.makeText(getApplicationContext(),n.toString(),Toast.LENGTH_LONG).show();
                //textView.setText(n.toString());
                for (String in : n){
                    urlList.add(new UrlName(in));
                    //Toast.makeText(getApplicationContext(),urlList.toString(),Toast.LENGTH_LONG).show();
                }
                setAdapter();
            }


        });

        //PyObject obj=pyobj.callAttr("main");

        //textView.setText(obj.toString());

    }
    private void setAdapter() {
        RecycleAdapter recycleAdapter =new RecycleAdapter(urlList);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recycleAdapter);

    }
}