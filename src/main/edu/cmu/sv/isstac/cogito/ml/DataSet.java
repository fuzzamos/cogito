/*
 * MIT License
 *
 * Copyright (c) 2017 Carnegie Mellon University.
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

package edu.cmu.sv.isstac.cogito.ml;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Kasper Luckow
 */
public class DataSet {
  private Collection<Instance> instances = new ArrayList<>();

  public void add(Instance instance) {
    this.instances.add(instance);
  }

  public double[][] getXs() {
    double[][] xs = new double[instances.size()][];
    int i = 0;
    for(Instance instance : instances) {
      xs[i++] = instance.getX();
    }
    return xs;
  }

  public int[] getYs() {
    int[] ys = new int[instances.size()];
    int i = 0;
    for(Instance instance : instances) {
      ys[i++] = instance.getY();
    }
    return ys;
  }

}
