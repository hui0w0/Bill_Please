package sg.edu.rp.c346.id21016163.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amt;
    EditText pax;
    ToggleButton svs;
    ToggleButton gst;
    EditText discount;
    RadioGroup pay;
    RadioButton cash;
    RadioButton paynow;
    Button split;
    Button reset;
    TextView TtlBill;
    TextView EachPays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amt = findViewById(R.id.editTextAmtDecimal);
        pax = findViewById(R.id.editTextPax);
        svs = findViewById(R.id.toggleButtonNoSvs);
        gst = findViewById(R.id.toggleButtonGST);
        discount = findViewById(R.id.editTextDiscount);
        pay = findViewById(R.id.radioGroup);
        cash = findViewById(R.id.radioButtonCash);
        paynow = findViewById(R.id.radioButtonPayNow);
        split = findViewById(R.id.buttonspit);
        reset = findViewById(R.id.buttonreset);
        TtlBill = findViewById(R.id.textViewTtlBill);
        EachPays = findViewById(R.id.textViewEachPays);


        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amt.getText().toString().trim().length() != 0 && pax.getText().toString().trim().length() != 0) {
                    double originAmt = Double.parseDouble(amt.getText().toString());
                    double newAmt = 0.0;
                    if (svs.isChecked() == true && gst.isChecked() == false) {
                        newAmt = originAmt * 1.1;
                    } else if (svs.isChecked() == false && gst.isChecked() == true) {
                        newAmt = originAmt * 1.07;
                    } else if (svs.isChecked() == true && gst.isChecked() == true) {
                        newAmt = originAmt * 1.17;
                    } else {
                        newAmt = originAmt;
                    }

                    if (discount.getText().toString().trim().length()!=0){
                        newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }

                    String mode = " in cash";
                    if(pay.getCheckedRadioButtonId()==R.id.radioButtonPayNow){
                        mode = " via PayNow to 912345678";
                    }

                    TtlBill.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(pax.getText().toString());
                    if(numPerson!=1){
                        EachPays.setText("Each Pays: $" + (newAmt/numPerson) + mode);
                    }
                    else {
                        EachPays.setText("Each pays: $" + newAmt + mode);
                    }
                }}
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amt.setText(null);
                pax.setText(null);
                discount.setText(null);
            }
        });

    }}
