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

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages={
            { "Uprise-D3 1000IU Capsule","","","","50"},
            { "HealthVit Chromium Piconlinate 200mg Capsule","","","","305"},
            { "Vitamin B Complex Capsules","","","","448"},
            { "Inlife Vitamin E wheat germ Oil Capsule ","","","","538"},
            { "Dolo 650 Tablet","","","","40"},
            { "Crocin 650 Advanced Tablet","","","","50"},
            { "Strepsils Medicated Lozenges for Sore Throat","","","","40"},
            { "Tata Img Capsule+Vitamin D3","","","","30"},
            { "Feronia-XT Tablet","","","","130"}
    };

    private String[] package_details={
        "Building and keeping bones & teeth Strong\n"+
        "Reducing Fatigue/stress and muscular pain\n"+
                "Boosting immunity and increasing resistance against infection",
            "Chromium is an essential trace mineral that plays an important role in helping insulin regulation\n"+
                    "provide relief from vitamin B deficiencies\n"+
                    "Help in formation of red blood cell",
            "It promote health as well as skin benefit\n"+
                    "It help to reduce skin blemish and pigmentation\n"+
                    "It acts as a safeguard the skin from the harsh UVA amd UVB sun rays\n" ,
                    "It help to relieve pain and fever by blocking the release of certain chemical message",
            "Help to relieves the fever and help to bring down the high temperature\n" +
                            "Suitable for people with a heart condition or high blood pressure",
            "Relief the symptoms of a bacterial throat infection soothes the recovery process\n" +
                    "Provide a warm and comforting feeling during sore throat",
            "Reduce the risk of calcium deficiency ,Rickets and Osteoporosis \n" +
                    "Promote nobility and flexibility of joints",
            "Help in Reduce deficiency due to chronic blood loss or low intake of iron",
            "Help in Reduce deficiency due to chronic blood loss or low intake of iron",
            "Help in Reduce deficiency due to chronic blood loss or low intake of iron",

    };

    HashMap<String,String > item;
    ArrayList list;
    SimpleAdapter sa;
    ListView listView;
    Button btnBack,btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        listView=findViewById(R.id.listViewBM);
        btnBack=findViewById(R.id.ButtonBMBack);
        btnGoToCart=findViewById(R.id.ButtonBMGoToCart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity2.class));
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicinesActivity.class));
            }
        });


        list = new ArrayList();
        for(int i =0; i<packages.length;i++){

            item = new HashMap<String, String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5", "Total Fees:"+packages[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.medicine_cart,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_p,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_q}
        );
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it =new Intent(BuyMedicineActivity.this,BuyMedicineDetailActivity.class);
                it.putExtra("text1",packages[position][0]);
                it.putExtra("text2",package_details[position]);
                it.putExtra("text3",packages[position][4]);
                startActivity(it);

            }
        });
    }
}