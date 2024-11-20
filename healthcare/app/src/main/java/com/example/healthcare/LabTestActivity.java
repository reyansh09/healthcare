package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages=
    {
        {"Package 1:Full Body CheckUp","","","","999"},
        {"Package 2:Blood Glucose Fasting","","","","299"},
        {"Package 3:COVID-19 Antibody- IgG","","","","899"},
        {"Package 4:Thyroid Check","","","","499"},
        {"Package 5:Immunity Check","","","","999"},
    };

    private String[] package_details=
            {
                 "Full Body CheckUp\n"+
                         "Complete Hemogram\n"+
                         "HbA1c\n"+
                         "Iron studies\n"+
                         "Kidney Function Test\n"+
                         "LDH Lactate Dehydrogenase, Serum\n"+
                         "Lipid Profile\n"+
                         "Liver Function Test",
                    "Blood Glucose Fasting",
                    "COVID-19 Antibody- IgG",
                    "Thyroid Profile-Total (T3, T4, TSH Ultra-sensitive)",
                    "Complete Hemogram\n"+
                    "CRP (C Reactive Protein) Quantitative, Serum\n"+
                            "Iron Studies\n"+
                            "Kidney Function Test\n"+
                            "Liver Function Test\n"+
                            "Lipid Profile\n"+
                    "Vitamin D Total-25 Hydroxy\n"
            };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    Button btnGoToCart,btnBackLT;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnBackLT=findViewById(R.id.ButtonLTBack);
        btnGoToCart=findViewById(R.id.ButtonLTGoToBack);
        listView=findViewById(R.id.listViewLT);

        btnBackLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity2.class));

            }
        });

        list= new ArrayList();
        for (int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost"+packages[i][4]+"/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,R.layout.medicine_cart,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_p,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_q});
        listView.setAdapter(sa);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(LabTestActivity.this, LabTestDetailActivity.class);
                it.putExtra("text1",packages[position][0]);
                it.putExtra("text2",package_details[position]);
                it.putExtra("text3",packages[position][4]);
                startActivity(it);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });
    }
}