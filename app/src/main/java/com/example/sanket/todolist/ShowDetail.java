package com.example.sanket.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class ShowDetail extends AppCompatActivity {

    private String detail;
    private String title;
    private boolean ispassword;
    private String password;
    private Date lastUpdatedDate;
    private TextView title_view;
    private EditText detail_view;
    private int postion;
    private SwipeDetailAdapter swipeDetailAdapter;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_detail);

        Toolbar toolbar = (Toolbar)findViewById(R.id.show_detail_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        //ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
        ab.setDisplayHomeAsUpEnabled(true);
        //ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(false);
        this.viewPager = (ViewPager)findViewById(R.id.show_detail_viewpager);
        this.swipeDetailAdapter = new SwipeDetailAdapter(this);
        this.viewPager.setAdapter(swipeDetailAdapter);
        Intent intent = getIntent();
        this.postion = intent.getIntExtra("position",0);
       /* this.detail = intent.getStringExtra("detail");
        this.title = intent.getStringExtra("title");*/
        if(TodoListItems.getTodoListItems().get(postion).ispassword() == 1){
            TodoListItems.getTodoListItems().get(postion).setIspassword(3);
        }
        viewPager.setCurrentItem(postion);
       // displayMessage();
    }
/*
    public void displayMessage(){
        title_view.setText(this.title);
        detail_view.setText(this.detail);;
    }*/

    @Override
    public void onResume(){
        super.onResume();
     //   ((TextView)findViewById(R.id.show_detail_title)).setText(TodoListItems.get().getTodoListItems().get(viewPager.getCurrentItem()).getTitle());

      /*  detail_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                detail_view.setEnabled(true);
                detail_view.setFocusable(true);
            }
        });*/
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Log.d("ShowDetail","onBackPressed() enters");
        finish();
        Log.d("ShowDetail","onBackPressed() returns");
    }
    @Override
    public void finish(){
        Log.d("ShowDetail","finish() enters");
        Intent intent = new Intent();
        if(((EditText)viewPager.findViewById(R.id.detail_value)).getText().toString().isEmpty()){
            Toast.makeText(this,"Please Enter some detail",Toast.LENGTH_SHORT).show();
            return;
        }
        //intent.putExtra("title",((TextView)viewPager.findViewById(R.id.show_detail_title)).getText().toString());
        intent.putExtra("detail",((EditText)viewPager.findViewById(R.id.detail_value)).getText().toString());
        setResult(RESULT_OK,intent);
        super.finish();
        Log.d("ShowDetail","finish() returns");
    }

}
