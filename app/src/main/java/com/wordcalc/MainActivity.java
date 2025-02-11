package com.wordcalc;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loopedlabs.util.debug.DebugLog;

import org.apache.commons.lang3.StringUtils;

public class MainActivity extends AppCompatActivity {

    private EditText etInputNumbers;
    private EditText etWords;
    private String sInputNumber = "";
    private WordNumConverter wordNumConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DebugLog.setDebugMode(BuildConfig.DEBUG);
        DebugLog.logTrace();

        wordNumConverter = new WordNumConverter();

        etInputNumbers = findViewById(R.id.etInputNumbers);
        etWords = findViewById(R.id.etWords);

        findViewById(R.id.btnOne).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnTwo).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnThree).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnFour).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnFive).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnSix).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnSeven).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnEight).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnNine).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnZero).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnSign).setOnClickListener(this::onClickNumbers);

        findViewById(R.id.btnMultiply).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnPlus).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnMinus).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnDivide).setOnClickListener(this::onClickNumbers);

        findViewById(R.id.btnAllClear).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnDel).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnDecimal).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnEquals).setOnClickListener(this::onClickNumbers);

        findViewById(R.id.btnMultiply).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnPlus).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnMinus).setOnClickListener(this::onClickNumbers);
        findViewById(R.id.btnDivide).setOnClickListener(this::onClickNumbers);


        initInputNumber();
    }

    private void onClickNumbers(View view) {
        DebugLog.logTrace();
        int btnId = view.getId();
        if (btnId == R.id.btnAllClear) {
            initInputNumber();
        }else if (btnId == R.id.btnDel) {
            delLastInputNumber();
        }else if (btnId == R.id.btnZero) {
            setInputNumber("0");
        } else if (btnId == R.id.btnOne) {
            setInputNumber("1");
        } else if (btnId == R.id.btnTwo) {
            setInputNumber("2");
        } else if (btnId == R.id.btnThree) {
            setInputNumber("3");
        } else if (btnId == R.id.btnFour) {
            setInputNumber("4");
        } else if (btnId == R.id.btnFive) {
            setInputNumber("5");
        } else if (btnId == R.id.btnSix) {
            setInputNumber("6");
        } else if (btnId == R.id.btnSeven) {
            setInputNumber("7");
        } else if (btnId == R.id.btnEight) {
            setInputNumber("8");
        } else if (btnId == R.id.btnNine) {
            setInputNumber("9");
        } else if (btnId == R.id.btnSign) {
            etInputNumbers.setText("+");
        } else if (btnId == R.id.btnDecimal) {
            etInputNumbers.setText(".");
        } else {
            Log.d("onClick", "Clicked : " + btnId);
            etInputNumbers.setText("?");
        }
    }

    private void initInputNumber() {
        DebugLog.logTrace();
        sInputNumber = "0";
        setInputNumber("0");
    }

    private void setInputNumber(String sKey) {
        DebugLog.logTrace();
        if (StringUtils.containsAny(sKey, "0123456789")) {
            if (sInputNumber.contentEquals("0")) {
                sInputNumber = sKey;
            } else {
                sInputNumber += sKey;
            }
            etInputNumbers.setText(sInputNumber);
            setWordNumber();
        }
    }

    private void delLastInputNumber() {
        DebugLog.logTrace();
            switch (sInputNumber.length()) {
                case 0:
                case 1:
                    sInputNumber = "0";
                    break;
                default:
                    sInputNumber = sInputNumber.substring(0, sInputNumber.length() - 1);
                    break;
            }
            etInputNumbers.setText(sInputNumber);
            setWordNumber();
    }

    private void setWordNumber() {
        etWords.setText(wordNumConverter.number_to_words(sInputNumber));
    }
}