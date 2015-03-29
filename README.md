Still on development phase

# Credit Card Lib

Android Credit card library is basicly a bunch of textwatchers for edit texts to enter credit card information. In sample code, you can see how a credit card page has been implemented.

This library provides
  - Credit Card TextWatchers
  - Expiry Date TextWatchers

### Gradle

```groovy
will be added in near future
```
Sample app has a default credit card page
<img src='https://github.com/yekmer/credit_card_lib/blob/master/images/before.png'/>
After entering credit card information default format would be set
<img src='https://github.com/yekmer/credit_card_lib/blob/master/images/after.png'/>
If your card is mastercard, default mastercard format will be set. If you change card type, it will automatically update format
<img src='https://github.com/yekmer/credit_card_lib/blob/master/images/mastercard.png'/>
### Usage

```xml
<com.yekmer.cardlib.CreditCardEditText
        android:id="@+id/credit_card_edit_text"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:lines="1"
        android:maxLines="1"
        android:maxLength="19"
        android:singleLine="true"
        android:hint="@string/payment_credit_card_number_hint"
        android:imeOptions="actionNext"
        android:inputType="phone"/>

<EditText
            android:id="@+id/et_fragment_add_credit_card_expiry"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:lines="1"
            android:maxLines="1"
            android:maxLength="5"
            android:singleLine="true"
            android:hint="@string/payment_expiration_hint"
            android:imeOptions="actionNext"
            android:inputType="datetime"/>
```

```java
        creditCardEditText = (CreditCardEditText) findViewById(R.id.credit_card_edit_text);
        expirationEditText = (EditText) findViewById(R.id.et_fragment_add_credit_card_expiry);

        creditCardEditText.addTextChangedListener(new OtherCardTextWatcher(creditCardEditText));
        expirationEditText.addTextChangedListener(new TwoDigitsCardTextWatcher(expirationEditText));
```
###License
<pre>
Copyright 2015 Yekmer Simsek

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>
