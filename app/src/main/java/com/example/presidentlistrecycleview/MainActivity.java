package com.example.presidentlistrecycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Presidents App";
    Button btn_addOne;
    Menu menu;

    MyApplication myApplication = (MyApplication) this.getApplication();
    List<President> presidentList;

    private RecyclerView recyclerView;
    private  RecyclerView.Adapter mAdapter;
    private  RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presidentList = myApplication.getPresidentList();

        Log.d(TAG, "onCreate: "+presidentList.toString());

        Toast.makeText(MainActivity.this, "Presidents count " + presidentList.size(), Toast.LENGTH_SHORT).show();

        btn_addOne = findViewById(R.id.btn_addOne);


        btn_addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditOne.class );
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.lv_presidentList);
        recyclerView.setAdapter(new EmptyAdapter());
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecycleViewAdapter(presidentList, MainActivity.this);
        recyclerView.setAdapter(mAdapter);

         }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_aToz :
                // sort a to z
                Collections.sort(presidentList, President.PresidentNameAZComparator);
                Toast.makeText(MainActivity.this, "sort a to z", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return  true;

            case R.id.menu_zToa:
                // sort z to a
                Collections.sort(presidentList, President.PresidentNameZAComparator);
                Toast.makeText(MainActivity.this, "sort z to a", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_dateAscending:
                // sort ascending
                Collections.sort(presidentList, President.PresidentDateAscendingComparator);
                Toast.makeText(MainActivity.this, "sort ascending", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;
            case R.id.menu_dateDescending :
                // sort descending
                Collections.sort(presidentList, President.PresidentDateDecendingComparator);
                Toast.makeText(MainActivity.this, "sort descending", Toast.LENGTH_SHORT).show();
                mAdapter.notifyDataSetChanged();
                return true;

            default:
                return false;
        }
        //return super.onOptionsItemSelected(item);
    }

}