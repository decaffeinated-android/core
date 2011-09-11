/*
 * Copyright © 2011 Daniel Solano Gómez
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Decaffeinated Android nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS “AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL DANIEL SOLANO GÓMEZ
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.decaffeinatedandroid.core.converter

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import android.widget._

import ViewGroup.LayoutParams._

class ConverterActivity extends Activity {
  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)

    val layout = new LinearLayout(this)
    layout.setOrientation(LinearLayout.VERTICAL)

    val defaultLayoutParams = new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)

    val entry = new EditText(this)
    layout.addView(entry, new LinearLayout.LayoutParams(defaultLayoutParams))

    val radioGroup = new RadioGroup(this)
    radioGroup.setOrientation(LinearLayout.VERTICAL)

    val celsius = new RadioButton(this)
    celsius.setText("Celsius")
    radioGroup.addView(celsius, defaultLayoutParams)
    radioGroup.check(celsius.getId)

    val fahrenheit = new RadioButton(this)
    fahrenheit.setText("Fahrenheit")
    radioGroup.addView(fahrenheit, defaultLayoutParams)

    val kelvin = new RadioButton(this)
    kelvin.setText("Kelvin")
    radioGroup.addView(kelvin, defaultLayoutParams)

    layout.addView(radioGroup, defaultLayoutParams)

    val convertButton = new Button(this)
    convertButton.setText("Convert!")
    layout.addView(convertButton, defaultLayoutParams)

    setContentView(layout)
  }
}
