package com.kh.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button btnPlus, btnMinus, btnMulti, btnDiv,btnRemain,btnErase,restart, exit;
    TextView textView;
    String num1, num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setIcon(R.drawable.iconfinder_51_111141);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        edit1 = (EditText) findViewById(R.id.textView1);
        edit1.setInputType(0);
        edit2 = (EditText) findViewById(R.id.textView2);
        edit2.setInputType(0);
        textView = (TextView) findViewById(R.id.textView3);

        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMulti = (Button) findViewById(R.id.btnMulti);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnRemain = (Button)findViewById(R.id.btnRemain);
        btnErase = (Button)findViewById(R.id.btnErase);
        exit = (Button)findViewById(R.id.exit);
        restart = (Button)findViewById(R.id.restart);
        final Button[] numButtons = new Button[11];
        Integer[] numBtnIDs = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
                R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btn};






        for (int i = 0; i < numButtons.length; i++) {
            numButtons[i] = (Button) findViewById(numBtnIDs[i]);
        }
        for (int i = 0; i < numBtnIDs.length; i++) {
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (edit1.isFocused() == true) {
                        num1 = edit1.getText().toString() + numButtons[index].getText().toString();
                        edit1.setText(num1);
                    } else if (edit2.isFocused() == true) {
                        num2 = edit2.getText().toString() + numButtons[index].getText().toString();
                        edit2.setText(num2);
                    } else {
                        Toast.makeText(getApplicationContext(), "에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        edit1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                edit1.setText("");
            }
        });
        edit2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                edit2.setText("");
            }
        });

        btnErase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (edit1.isFocused() == true) {
                    String number1 = edit1.getText().toString();
                    edit1.setText(number1.substring(0, number1.length()-1));
                } else if (edit2.isFocused() == true) {
                    String number2 = edit2.getText().toString();
                    edit2.setText(number2.substring(0, number2.length()-1));
                }
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!checkNumValue()) {
                    return;
                }

                double n1 = Double.valueOf(num1);
                double n2 = Double.valueOf(num2);
                checkNumValue();
                textView.setText("계산결과: " + String.valueOf(calculator(n1, n2, '+')));
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!checkNumValue()) {
                    return;
                }

                double n1 = Double.valueOf(num1);
                double n2 = Double.valueOf(num2);
                checkNumValue();
                textView.setText("계산결과: " + String.valueOf(calculator(n1, n2, '-')));
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!checkNumValue()) {
                    return;
                }

                double n1 = Double.valueOf(num1);
                double n2 = Double.valueOf(num2);
                checkNumValue();
                textView.setText("계산결과: " + String.valueOf(calculator(n1, n2, '*')));
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!checkNumValue()) {
                    return;
                }

                double n1 = Double.valueOf(num1);
                double n2 = Double.valueOf(num2);
                checkNumValue();
                if(n2 == 0){
                    Toast.makeText(getApplicationContext(),"0으로 나눌 수 없습니다.",Toast.LENGTH_SHORT).show();
                }
                textView.setText("계산결과: " + String.valueOf(calculator(n1, n2, '/')));
            }
        });
        btnRemain.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!checkNumValue()) {
                    return;
                }

                double n1 = Double.valueOf(num1);
                double n2 = Double.valueOf(num2);
                checkNumValue();
                if(n2 == 0){
                    Toast.makeText(getApplicationContext(),"0으로 나눌수없음!",Toast.LENGTH_SHORT).show();
                }
                textView.setText("계산결과: " + String.valueOf(calculator(n1, n2, '%')));
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });


    }
    private double calculator(double n1, double n2, char operator){
        double result =0.0;
        switch(operator){
            case '+': result = n1+n2; break;
            case '-': result = n1-n2; break;
            case '*': result = n1*n2; break;
            case '/': result = n1/n2; break;
            case '%': result = n1%n2; break;
            default: break;
        }
        return result;
    }

    private boolean checkNumValue(){
        boolean result= true;
        if(edit1.getText().toString().trim().length()==0 ){
            Toast.makeText(getApplicationContext(),"숫자1를입력하세요",Toast.LENGTH_SHORT).show();
            edit1.setFocusable(true);
            result = false;
        } if( edit2.getText().toString().trim().length()==0){
            Toast.makeText(getApplicationContext(),"숫자2를입력하세요",Toast.LENGTH_SHORT).show();
            edit2.setFocusable(true);
            result = false;
        }
        return result;
    }
}