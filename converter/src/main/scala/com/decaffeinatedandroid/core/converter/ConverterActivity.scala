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
import android.util.Log
import android.view.View
import android.widget._

class ConverterActivity extends Activity {
  /** Log tag for this activity. */
  val TAG = "Converter"

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main)
  }

  /** Handles a click of the 'Convert!' button from the UI. */
  def convert(view: View) {
    val tempValue = getTempValue
    val scaleGroup = findViewById(R.id.scale_group).asInstanceOf[RadioGroup]
    scaleGroup.getCheckedRadioButtonId match {
      case R.id.celsius    => convertFromCelsius(tempValue)
      case R.id.fahrenheit => convertFromFahrenheit(tempValue)
      case R.id.kelvin     => convertFromKelvin(tempValue)
    }
  }

  /** Gets the temperature value from the entry box in the user interface.
    * For now, all erroneous values are simply turned to zero. */
  def getTempValue: Double = {
    val editText = findViewById(R.id.input_temp).asInstanceOf[EditText]
    val input = editText.getText.toString
    try {
      input.toDouble
    } catch {
      case _ => 0.0
    }
  }

  /** Handles conversion from a temperature in Celsius. */
  def convertFromCelsius(celsius: Double) {
    Log.v(TAG, "Converting from Celsius " + celsius)
    val fahrenheit = (celsius * 9 / 5) + 32
    val kelvin = celsius + 273.15
    showConversion(formatCelsius(celsius), formatFahrenheit(fahrenheit),
      formatKelvin(kelvin))
  }

  /** Handles conversion from a temperature in Fahrenheit. */
  def convertFromFahrenheit(fahrenheit: Double) {
    Log.v(TAG, "Converting from Fahrenheit " + fahrenheit)
    val celsius = (fahrenheit - 32) * 5 / 9
    val kelvin = (fahrenheit + 459.67) * 5 / 9
    showConversion(formatFahrenheit(fahrenheit), formatCelsius(celsius),
      formatKelvin(kelvin))
  }

  /** Handles conversion from a temperature in Kelvin. */
  def convertFromKelvin(kelvin: Double) {
    Log.v(TAG, "Converting from Kelvin: " + kelvin)
    val celsius = kelvin - 273.15
    val fahrenheit = (kelvin * 9 / 5) - 459.67
    showConversion(formatKelvin(kelvin), formatCelsius(celsius),
      formatFahrenheit(fahrenheit))
  }

  /** Formats a temperature in Celsius to a suitable string. */
  def formatCelsius(temp: Double) = {
    formatTemp(temp, R.string.celsius_format)
  }

  /** Formats a temperature in Fahrenheit to a suitable string. */
  def formatFahrenheit(temp: Double) = {
    formatTemp(temp, R.string.fahrenheit_format)
  }

  /** Formats a temperature in Kelvin to a suitable string. */
  def formatKelvin(temp: Double) = {
    formatTemp(temp, R.string.kelvin_format)
  }

  /** Formats the temperature given the format. */
  def formatTemp(temp: Double, formatId: Int) = {
    getString(formatId, temp.asInstanceOf[AnyRef])
  }

  /** Shows the conversion result to the user. */
  def showConversion(from: String, to1: String, to2: String) {
    val resultView = findViewById(R.id.result).asInstanceOf[TextView]
    val resultString = getString(R.string.result, from, to1, to2);
    resultView.setText(resultString)
  }
}
