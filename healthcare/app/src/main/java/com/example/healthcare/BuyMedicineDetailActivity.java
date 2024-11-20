package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailActivity extends AppCompatActivity {

    TextView tvPackage ,tvTotalCost;
    EditText edDetails;
    Button btnBack,btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_detail);

        tvPackage=findViewById(R.id.textViewBMDPackageName);
        tvTotalCost=findViewById(R.id.textViewLBMDTotalCost);
        edDetails=findViewById(R.id.editTextBMDMultiLine);
        btnBack=findViewById(R.id.ButtonBMDBack);
        btnAddToCart=findViewById(R.id.ButtonBMDGoToCart);

        edDetails.setKeyListener(null);

        Intent intent=getIntent();
        tvPackage.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost:"+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailActivity.this,BuyMedicineActivity.class));
            }
        });


        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username= sharedPreferences.getString("username","").toString();
                String product = tvPackage.getText().toString();
                float price =Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db= new Database(getApplicationContext(),"healthcare",null,1);
                if (db.CheckCart(username,product)==1){
                    Toast.makeText(BuyMedicineDetailActivity.this, "Product Already Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(BuyMedicineDetailActivity.this, "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailActivity.this, BuyMedicineActivity.class));
                }
            }
        });

    }
}