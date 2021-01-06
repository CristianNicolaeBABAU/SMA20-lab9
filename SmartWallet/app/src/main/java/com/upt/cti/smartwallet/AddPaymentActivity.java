package com.upt.cti.smartwallet;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.upt.cti.smartwallet.model.Payment;
import com.upt.cti.smartwallet.model.PaymentType;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddPaymentActivity extends AppCompatActivity {
    private EditText description, cost;
    private Spinner spinner;
    private TextView paymentTime;
    private Button saveButton, deleteButton;
    private Payment payment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fab_layout_2);

        description = findViewById(R.id.paymentDescription);
        cost = findViewById(R.id.paymentCost);
        spinner = findViewById(R.id.paymentSpinner);
        paymentTime = findViewById(R.id.paymentTime);
        saveButton = findViewById(R.id.saveButton);
        deleteButton = findViewById(R.id.deleteButton);

        String[] types = PaymentType.getTypes();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }
    public void clicked(View view) {
        String currentMonth;
        switch (view.getId()) {
            case R.id.saveButton:
                if (payment != null) {
                    //nu stiu cum sa folosesc firebase sa salvez date
                    //save(payment.timestamp);
                } else {
                    //save(AppState.getCurrentTimeDate());
                }
                break;
            case R.id.deleteButton:
                if(payment != null) {
                    //delete(payment.timestamp);
                }
                else
                    Toast.makeText(this, "payment does not exist", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
