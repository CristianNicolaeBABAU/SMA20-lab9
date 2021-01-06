package com.upt.cti.smartwallet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upt.cti.smartwallet.adapter.PaymentAdapter;
import com.upt.cti.smartwallet.model.Payment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivityLayout extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private int currentMonth;
    private List<Payment> payments = new ArrayList<>();
    private List<Payment> paymentList = new ArrayList<>();

    private TextView tStatus;
    private Button bPrevious, bNext;
    private FloatingActionButton fabAdd;
    private ListView listPayments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        tStatus = (TextView) findViewById(R.id.tStatus);
        bPrevious = (Button) findViewById(R.id.bPrevious);
        bNext = (Button) findViewById(R.id.bNext);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        listPayments = (ListView) findViewById(R.id.listPayments);
        final PaymentAdapter adapter = new PaymentAdapter(this, R.layout.item_payment, payments);
        listPayments.setAdapter(adapter);

        // setup firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

    }
    private void onPrev(){
        Toast.makeText(this, "Previous pressed", Toast.LENGTH_SHORT).show();
    }

    public void onNext(){
        Toast.makeText(this, "Next pressed", Toast.LENGTH_SHORT).show();
    }

    private interface FirebaseCallback {
        void onCallBack(List<Payment> p);
    }
}
