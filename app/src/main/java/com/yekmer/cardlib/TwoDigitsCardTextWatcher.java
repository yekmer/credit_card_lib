package com.yekmer.cardlib;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @author yekmer
 */
public class TwoDigitsCardTextWatcher implements TextWatcher {

    private static final String INITIAL_MONTH_ADD_ON = "0";
    private static final String DEFAULT_MONTH = "01";
    private static final String SPACE = "/";
    private EditText mEditText;
    private int mLength;

    public TwoDigitsCardTextWatcher(EditText editText) {
        mEditText = editText;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mEditText.setError(null);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mLength = mEditText.getText().length();
    }

    @Override
    public void afterTextChanged(Editable s) {
        int currentLength = mEditText.getText().length();
        boolean ignoreBecauseIsDeleting = false;
        if (mLength > currentLength) {
            ignoreBecauseIsDeleting = true;
        }

        if (ignoreBecauseIsDeleting && s.toString().startsWith(SPACE)) {
            return;
        }

        if (s.length() == 1 && !isNumberChar(String.valueOf(s.charAt(0)))) {
            mEditText.setText(DEFAULT_MONTH + SPACE);
        } else if (s.length() == 1 && !isCharValidMonth(s.charAt(0))) {
            mEditText.setText(INITIAL_MONTH_ADD_ON + String.valueOf(s.charAt(0)) + SPACE);
        } else if (s.length() == 2 && String.valueOf(s.charAt(s.length() - 1)).equals(SPACE)) {
            mEditText.setText(INITIAL_MONTH_ADD_ON + String.valueOf(s));
        } else if (!ignoreBecauseIsDeleting &&
                (s.length() == 2 && !String.valueOf(s.charAt(s.length() - 1)).equals(SPACE))) {
            mEditText.setText(mEditText.getText().toString() + SPACE);
        } else if (s.length() == 3 && !String.valueOf(s.charAt(s.length() - 1)).equals(SPACE) && !ignoreBecauseIsDeleting) {
            s.insert(2, SPACE);
            mEditText.setText(String.valueOf(s));
        } else if (s.length() > 3 && !isCharValidMonth(s.charAt(0))) {
            mEditText.setText(INITIAL_MONTH_ADD_ON + s);
        }

        if (!ignoreBecauseIsDeleting) {
            mEditText.setSelection(mEditText.getText().toString().length());
        }
    }

    private boolean isCharValidMonth(char charFromString) {
        int month = 0;
        if (Character.isDigit(charFromString)) {
            month = Integer.parseInt(String.valueOf(charFromString));
        }
        return month <= 1;
    }

    private boolean isNumberChar(String string) {
        return string.matches(".*\\d.*");
    }
}