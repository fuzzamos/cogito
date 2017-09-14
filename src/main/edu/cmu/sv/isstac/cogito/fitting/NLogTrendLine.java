/*
 * MIT License
 *
 * Copyright (c) 2017 The ISSTAC Authors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package edu.cmu.sv.isstac.cogito.fitting;

import com.google.common.base.Predicate;

import java.text.DecimalFormat;

/**
 * @author Kasper Luckow
 * TODO: check that the output of getFunction is correct
 */
public class NLogTrendLine extends OLSTrendLine {
  @Override
  protected double[] xVector(double x) {
    return new double[]{1,x, x*Math.log(x)};
  }

  @Override
  protected boolean logY() {
    return false;
  }

  @Override
  public String getFunction() {
    StringBuilder functionSb = new StringBuilder();
    DecimalFormat df = new DecimalFormat("#.00");
    double a = super.coef.getColumn(0)[0];
    double b = super.coef.getColumn(0)[1];
    double c = super.coef.getColumn(0)[2];
    
    if(a != 0.0)
      functionSb.append(df.format(a)).append(" + ");
    if(b != 0.0)
      functionSb.append(df.format(b) + "x").append(" + ");
    if(c != 0.0)    
      functionSb.append(df.format(c) + "x*log(x)");
    return functionSb.toString();
  }

  @Override
  public Predicate<Double> getDomainPredicate() {
    return new Predicate<Double>() {
      @Override
      public boolean apply(Double arg0) {
        return arg0 > 0.0;
      }
    };
  }

  @Override
  public Predicate<Double> getRangePredicate() {
    return new Predicate<Double>() {
      @Override
      public boolean apply(Double arg0) {
        return true;
      }
    };
  }
}
