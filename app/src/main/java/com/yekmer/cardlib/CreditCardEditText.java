package com.yekmer.cardlib;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by yekmer
 */
public class CreditCardEditText extends EditText {

    private CreditCardBaseTextWatcher mTextWatcher;

    public CreditCardEditText(Context context) {
        super(context);
    }

    public CreditCardEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CreditCardEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CreditCardBaseTextWatcher getTextWatcher() {
        return mTextWatcher;
    }

    public void setTextWatcher(CreditCardBaseTextWatcher textWatcher) {
        this.mTextWatcher = textWatcher;
    }

    public void setCopyPastedText(CharSequence text) {
        mTextWatcher.setIsCopyPasted(true);
        setText(text);
        mTextWatcher.setIsCopyPasted(false);
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        super.addTextChangedListener(watcher);
        if(watcher instanceof CreditCardBaseTextWatcher) {
            CreditCardBaseTextWatcher creditCardBaseTextWatcher = (CreditCardBaseTextWatcher) watcher;
            setTextWatcher(creditCardBaseTextWatcher);
        }
    }
}
