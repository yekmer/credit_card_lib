package com.yekmer.cardlib;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;

/**
 * Created by yekmer
 *
 * Sample credit card data
 * @see <a href="https://stripe.com/docs/testing">https://stripe.com/docs/testing</a>
 *
 * Algorithm to detect credit card data
 * @see <a href="https://stackoverflow.com/questions/72768/how-do-you-detect-credit-card-type-based-on-number">https://stackoverflow.com/questions/72768/how-do-you-detect-credit-card-type-based-on-number</a>
 */

public abstract class CreditCardBaseTextWatcher {
    protected final char SPACE = ' ';
    protected CreditCardEditText mEditText;
    protected String mPreviousText = "";
    protected boolean mIsChangedInside = false;
    protected boolean mIsCopyPasted = false;
    protected final int OTHERS_MAX_LENGTH = 19;
    protected final int AMERICAN_EXPRESS_MAX_LENGTH = 17;
    protected final String AMERICAN_EXPRESS_PREFIX_1 = "34";
    protected final String AMERICAN_EXPRESS_PREFIX_2 = "37";
    protected enum CARD_TYPE { AMERICAN_EXPRESS, OTHERS }

    protected void insertSpace(Editable editable, int index, char ch) {
        if (Character.isDigit(ch) && TextUtils.split(editable.toString(), String.valueOf(SPACE)).length <= 3) {
            mIsChangedInside = true;
            editable.insert(index, String.valueOf(SPACE));
        }
    }

    protected CARD_TYPE getNewCardType(String cardNumber) {
        if(cardNumber.startsWith(AMERICAN_EXPRESS_PREFIX_1) || cardNumber.startsWith(AMERICAN_EXPRESS_PREFIX_2)) {
            return CARD_TYPE.AMERICAN_EXPRESS;
        } else {
            return CARD_TYPE.OTHERS;
        }
    }

    protected boolean checkIsCorrectTextWatcher(Editable editable, TextWatcher textWatcher) {
        CARD_TYPE currentCardType = getCurrentCardType();
        CARD_TYPE newCardType = getNewCardType(editable.toString());

        if(newCardType == currentCardType) {
            return true;
        }

        if(newCardType == CARD_TYPE.OTHERS) {
            mEditText.removeTextChangedListener(textWatcher);
            mEditText.addTextChangedListener(new OtherCardTextWatcher(mEditText));
            mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(OTHERS_MAX_LENGTH)});
            setCursorPosition();
        } else if(newCardType == CARD_TYPE.AMERICAN_EXPRESS) {
            mEditText.removeTextChangedListener(textWatcher);
            mEditText.addTextChangedListener(new AmericanExpressTextWatcher(mEditText));
            mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(AMERICAN_EXPRESS_MAX_LENGTH)});
            setCursorPosition();
        }
        return false;
    }

    private CARD_TYPE getCurrentCardType() {
        if(this instanceof AmericanExpressTextWatcher) {
            return CARD_TYPE.AMERICAN_EXPRESS;
        } else if(this instanceof OtherCardTextWatcher) {
            return CARD_TYPE.OTHERS;
        }
        return CARD_TYPE.OTHERS;
    }

    private void setCursorPosition() {
        int selectionStart = mEditText.getSelectionStart();
        int selectionEnd = mEditText.getSelectionEnd();
        //trigger new textwatcher formatting old text
        mEditText.setText(mEditText.getText());
        mEditText.setSelection(selectionStart, selectionEnd);
    }

    public void setIsCopyPasted(boolean isCopyPasted) {
        mIsCopyPasted = isCopyPasted;
    }

    public abstract boolean isCreditCardValid();
}
