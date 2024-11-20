package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailActivity extends AppCompatActivity {

    TextView tvPackage ,tvTotalCost;
    EditText edDetails;
    Button btnBackLTD,btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_detail);

        tvPackage=findViewById(R.id.textViewLTDPackageName);
        tvTotalCost=findViewById(R.id.textViewLTDTotalCost);
        edDetails=findViewById(R.id.editTextLTDMultiLine);
        btnBackLTD=findViewById(R.id.ButtonLTDBack);
        btnAddToCart=findViewById(R.id.ButtonLTDGoToBack);

        edDetails.setKeyListener(null);

        Intent intent=getIntent();
        tvPackage.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost:"+intent.getStringExtra("text3")+"/-");

        btnBackLTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailActivity.this,HomeActivity2.class));
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
                    Toast.makeText(LabTestDetailActivity.this, "Product Already Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(LabTestDetailActivity.this, "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailActivity.this, LabTestActivity.class));
                }
            }
        });
    }
}