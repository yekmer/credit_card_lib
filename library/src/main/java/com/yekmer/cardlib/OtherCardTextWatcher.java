package com.yekmer.cardlib;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/**
 * @author alessandro.balocco
 */
public class OtherCardTextWatcher extends CreditCardBaseTextWatcher implements TextWatcher {

    public OtherCardTextWatcher(CreditCardEditText editText) {
        mEditText = editText;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mEditText.setError(null);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void afterTextChanged(Editable s) {

        if(!checkIsCorrectTextWatcher(s, this)) {
            return;
        }

        boolean isDeleteMode = !mIsCopyPasted && mPreviousText.length() > s.length();

        if(isDeleteMode && !mIsChangedInside) {
            // Remove spacing char at the end
            if (s.length() > 0 && (s.length() % 5) == 0) {
                final char c = s.charAt(s.length() - 1);
                if (SPACE == c) {
                    mIsChangedInside = true;
                    s.delete(s.length() - 1, s.length());
                }
            }
            //remove char between
            String[] cardNumberParts = TextUtils.split(s.toString(), String.valueOf(SPACE));
            for (int i = 0; i < cardNumberParts.length; i++) {
                String cardPart = cardNumberParts[i];
                if (cardPart.length() > 4) {
                    int removedCharIndex = (i * 5) + 3;
                    mIsChangedInside = true;
                    s.delete(removedCharIndex, removedCharIndex + 1);
                }
            }
        } else {
            mIsChangedInside = false;
        }
        // Insert char where needed.
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (((i + 1) % 5) == 0) {
                if (Character.isDigit(c) && TextUtils.split(s.toString(), String.valueOf(SPACE)).length <= 4) {
                    mIsChangedInside = true;
                    s.insert(i, String.valueOf(SPACE));
                }
            } else if(!Character.isDigit(c)) {
                mIsChangedInside = true;
                s.delete(i, (i + 1));
            }
        }
        mPreviousText = s.toString();
    }

    @Override
    public boolean isCreditCardValid() {
        return mEditText.getText().toString().length() == 19;
    }

}
