package com.upt.cti.smartwallet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.upt.cti.smartwallet.model.MonthlyExpenses;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {

    private TextView tStatus;
    private EditText eSearch, eIncome, eExpenses;

    private DatabaseReference databaseReference;
    private ValueEventListener databaseListener;

    private String currentMonth;

    private String preSelectedMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tStatus = findViewById(R.id.tStatus);
        eIncome = findViewById(R.id.eIncome);
        eExpenses = findViewById(R.id.eExpenses);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        SharedPreferences sharedPreferences = getSharedPreferences("lastMonth", MODE_PRIVATE);
        preSelectedMonth = sharedPreferences.getString("month","");

        //aici cumva ar trebui sa ma folosesc de spinner sa afisez lunile
    }

    public void clicked(View view) {
        switch (view.getId()) {
            case R.id.bSearch:
                if (!eSearch.getText().toString().isEmpty()) {
                    // save text to lower case (all our months are stored online in lower case)
                    currentMonth = eSearch.getText().toString().toLowerCase();

                    tStatus.setText("Searching ...");
                    createNewDBListener();
                } else {
                    Toast.makeText(this, "Search field may not be empty", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bUpdate:
                break;
        }
    }

    private void createNewDBListener() {
        // remove previous databaseListener
        if (databaseReference != null && currentMonth != null && databaseListener != null)
            databaseReference.child("calendar").child(currentMonth).removeEventListener(databaseListener);

        databaseListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                MonthlyExpenses monthlyExpense = dataSnapshot.getValue(MonthlyExpenses.class);
                // explicit mapping of month name from entry key
                monthlyExpense.month = dataSnapshot.getKey();

                eIncome.setText(String.valueOf(monthlyExpense.getIncome()));
                eExpenses.setText(String.valueOf(monthlyExpense.getExpenses()));
                tStatus.setText("Found entry for " + currentMonth);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        };

        databaseReference.child("calendar").child(currentMonth).addValueEventListener(databaseListener);
    }

    private void getMonths(final FirebaseCallback callback) {
        final ArrayList<String> firebaseMonths = new ArrayList<String>();
        Query query = databaseReference.child("calendar").orderByValue();


        Spinner spinner = findViewById(R.id.monthSpinner);

    }
    private interface FirebaseCallback {
        void onCallBack(ArrayList<String> firebaseMonths);
    }

}