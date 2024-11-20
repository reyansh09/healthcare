package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartLabActivity extends AppCompatActivity {

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    TextView tvTotal;

    Button datePicker,timePicker,btnCheckout,btnBack;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    ListView listView;
    private String[][] packages={};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);

        datePicker=findViewById(R.id.buttonCartDate);
        timePicker=findViewById(R.id.buttonCartTime);
        btnCheckout=findViewById(R.id.ButtonCartCheckout);
        btnBack=findViewById(R.id.ButtonCartBack);
        tvTotal=findViewById(R.id.textViewCartTcost);
        listView=findViewById(R.id.listViewCart);

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartLabActivity.this, LabTestBookActivity.class);
                intent.putExtra("price",tvTotal.getText());
                intent.putExtra("date",datePicker.getText());
                intent.putExtra("time",timePicker.getText());
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences= getSharedPreferences("shared prefs", Context.MODE_PRIVATE);
        String username= sharedPreferences.getString("username","").toString();

        Database db=new Database(getApplicationContext(),"healthcare",null,1);

        float totalAmount=0;
        ArrayList dbData=db.getCartData(username,"lab");
        Toast.makeText(this, ""+dbData, Toast.LENGTH_SHORT).show();

        packages=new String[dbData.size()][];
        for (int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }

        for (int i=0;i<dbData.size();i++){
            String arrData=dbData.get(i).toString();
            String[] strData= arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]="Cost : "+strData[1]+"/-";
            totalAmount=totalAmount+Float.parseFloat(strData[1]);
        }
        tvTotal.setText("Total Cost : "+totalAmount);

        list=new ArrayList();
        for (int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);

        }

        sa =new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        listView.setAdapter(sa);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartLabActivity.this,LabTestActivity.class));
            }
        });

        //DatePicker
        initDatePicker();
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        //TimePicker
        initTimePicker();
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });



    }

    public void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=dayOfMonth+1;
                datePicker.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis()+86400000);
    }

    public void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener= new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timePicker.setText(hourOfDay+":"+minute);
            }
        };

        Calendar calendar= Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR);
        int mins=calendar.get(Calendar.MINUTE);

        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog= new TimePickerDialog(this,style,timeSetListener,hour,mins,true);

    }
}