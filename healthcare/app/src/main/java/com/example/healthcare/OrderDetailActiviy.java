package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDetailActiviy extends AppCompatActivity {

    private String[][] orders_detail={};
    HashMap<String,String> item;

    ArrayList list;
    SimpleAdapter sa;

    Button btnBack ;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_activiy);

        lst=findViewById(R.id.listViewODetail);
        btnBack = findViewById(R.id.ButtonODetailBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailActiviy.this, HomeActivity2.class));
            }
        });


        Database db=new Database(getApplicationContext(),"healthcare",null,1);
        SharedPreferences sharedPreferences= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username= sharedPreferences.getString("username","").toString();
        ArrayList dbData=db.getOrderData(username);

        orders_detail=new String[dbData.size()][];
        for (int i=0;i<orders_detail.length;i++){
            orders_detail[i]=new String[5];
            String arrData= dbData.get(i).toString();
            String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
            orders_detail[i][0]=strData[0];
            orders_detail[i][1]=strData[1];//+""+strData[3];
            if (strData[7].compareTo("medicine")==0){
                orders_detail[i][3]="Del:"+strData[4];
            }else {
                orders_detail[i][3]="Del:"+strData[4]+" "+strData[5];
            }

            orders_detail[i][2]="Rs."+strData[6];
            orders_detail[i][4]=strData[7];

        }

        list=new ArrayList();
        for (int i=0;i<orders_detail.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",orders_detail[i][0]);
            item.put("line2",orders_detail[i][1]);
            item.put("line3",orders_detail[i][2]);
            item.put("line4",orders_detail[i][3]);
            item.put("line5",orders_detail[i][4]);
            list.add(item);

        }

        sa =new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

    }
}