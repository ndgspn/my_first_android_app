package com.nan.myfirstapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtLength, edtWidth, edtHeight;
    private TextView tvResult;
    private Button btnCalculate, btnClear;
    private final static String STATE_HASIL = "state_hasil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLength = findViewById(R.id.edt_length);
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        btnCalculate = findViewById(R.id.btn_calculate);
        btnClear = findViewById(R.id.btn_clear);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_HASIL);
            tvResult.setText(result);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_calculate) {
            String length = edtLength.getText().toString();
            String width = edtWidth.getText().toString();
            String height = edtHeight.getText().toString();
            Boolean isEmptyFields = false;

            if (TextUtils.isEmpty(length)) {
                isEmptyFields = true;
                edtLength.setError("Length is required!");
            }
            if (TextUtils.isEmpty(height)) {
                isEmptyFields = true;
                edtHeight.setError("Height is required!");
            }
            if (TextUtils.isEmpty(width)) {
                isEmptyFields = true;
                edtWidth.setError("Width is required!");
            }

            if (!isEmptyFields) {
                Double l = Double.parseDouble(length);
                Double w = Double.parseDouble(width);
                Double h = Double.parseDouble(height);
                Double volume = l * w * h;
                tvResult.setText(String.valueOf(volume));
            }
        }

        if (v.getId() == R.id.btn_clear) {
            edtLength.setText("");
            edtWidth.setText("");
            edtHeight.setText("");
            tvResult.setText("Hasil");
            edtLength.requestFocus();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_HASIL, tvResult.getText().toString());
        super.onSaveInstanceState(outState);
    }

}
