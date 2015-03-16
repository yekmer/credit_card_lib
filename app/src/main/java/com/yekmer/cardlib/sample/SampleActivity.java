package com.yekmer.cardlib.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.yekmer.cardlib.CreditCardEditText;
import com.yekmer.cardlib.OtherCardTextWatcher;
import com.yekmer.cardlib.TwoDigitsCardTextWatcher;

/**
 * Created by yekmer
 */
public class SampleActivity extends Activity {

    private CreditCardEditText creditCardEditText;
    private EditText expirationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creditCardEditText = (CreditCardEditText) findViewById(R.id.credit_card_edit_text);
        expirationEditText = (EditText) findViewById(R.id.et_fragment_add_credit_card_expiry);

        creditCardEditText.addTextChangedListener(new OtherCardTextWatcher(creditCardEditText));
        expirationEditText.addTextChangedListener(new TwoDigitsCardTextWatcher(expirationEditText));

    }
}
