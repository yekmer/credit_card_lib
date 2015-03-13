package com.yekmer.cardlib;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by yekmer
 */
public class SampleActivity extends Activity {

    private CreditCardEditText creditCardEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creditCardEditText = (CreditCardEditText) findViewById(R.id.credit_card_edit_text);

        creditCardEditText.addTextChangedListener(new OtherCardTextWatcher(creditCardEditText));

    }
}
