package jp.techacademy.wakabayashi.kojiro.currancy;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button mButton1,mButton2,mButton3;
    EditText mEdittext;
    TextView mTextView;


    private static Integer rate = 110;
    private int amount = -1; //null alart
    private int currency = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton1 = (Button)findViewById(R.id.button);
        mButton1.setText("通貨選択");
        mButton2 = (Button)findViewById(R.id.button2);
        mButton2.setText("為替計算");
        mButton3 = (Button)findViewById(R.id.button3);
        mButton3.setText("リセット");

        mEdittext = (EditText)findViewById(R.id.editText);
        //mEdittext.setText("金額を入れてください");


        mTextView = (TextView)findViewById(R.id.textView);

        mTextView.setText("計算結果");

       // int defaultItem = 0;



        mButton1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //memo:通貨選択のポップアップ



                    currencyselector();


                }
        });

        mButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //memo:通貨選択のポップアップ

                String str = mEdittext.getText().toString();
                amount = Integer.parseInt(str);

                Log.d("amount", String.valueOf(amount));


                if(amount != -1){
                    int total = -13;
                    switch(currency) {
                        case 0:
                        total = amount * rate;
                            break;
                        case 1:
                        total = amount / rate;

                    }
                    mTextView.setText(String.valueOf(total));

                }


            }
        });
        mButton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //memo:reset

                currency = -1;
                mEdittext.setText("");





                updateUI();
            }
        });

    }

    public void currencyselector() {
        final String[] items = {"from US.Doller to YEN","from YEN to US.Doller"};
        int defaultItem = 0;
        if(currency != -1) {
            defaultItem = currency;
        } else {
            defaultItem = 0;// デフォルトでチェックされているアイテム
        }
        final List<Integer> checkedItems = new ArrayList<>();
        checkedItems.add(defaultItem);
        new AlertDialog.Builder(this)
                .setTitle("Currency Selector")
                .setSingleChoiceItems(items, defaultItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedItems.clear();
                        checkedItems.add(which);
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!checkedItems.isEmpty()) {
                            Log.d("checkedItem:", "" + checkedItems.get(0));

                            currency = checkedItems.get(0);
                            Log.d("選択された通貨:", "" + currency);

                            updateUI();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();

        Log.d("選択された通貨:", "" + currency);

    }

    public void updateUI(){
    switch (currency){
        case -1:
            mButton1.setText("通貨選択");
            mTextView.setText("計算結果");
            break;
        case 0:
            mButton1.setText("input US dollar");
            break;
        case 1:
            mButton1.setText("input Japanese YEN");
            break;
    }
    }
}
