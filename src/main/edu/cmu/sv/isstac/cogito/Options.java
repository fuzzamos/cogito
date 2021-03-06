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

package edu.cmu.sv.isstac.cogito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import edu.cmu.sv.isstac.cogito.cost.CostModel;
import edu.cmu.sv.isstac.cogito.cost.DepthCostModel;
import gov.nasa.jpf.Config;

import static edu.cmu.sv.isstac.cogito.Options.ConfigDescription.create;

/**
 * @author Kasper Luckow
 */
public class Options {
  static class ConfigDescription {
    private final String config;
    private final String description;

    public static ConfigDescription create(String config, String description) {
      return new ConfigDescription(config, description);
    }
    private ConfigDescription(String config, String description) {
      this.config = config;
      this.description = description;
    }

    @Override
    public String toString() {
      return config + ": " + description;
    }
  }
  private static final String CONFIG_PREFIX = "cogito";

  private static final String TRAINING_PREFIX = CONFIG_PREFIX + ".training";

  private static final String PREDICTION_PREFIX = CONFIG_PREFIX + ".prediction";

  private static final String TARGET_ARGS = ".target.args";

  //Exposed
  public static final String EVENT_OBSERVER = ".eventobserver";
  public static final String VISUALIZE = CONFIG_PREFIX + ".visualize";
  public static final boolean DEFAULT_VISUALIZE = false;

  public static final String OUTPUT_RESULTS = CONFIG_PREFIX + ".output";

  public static final String TRAINING_TARGET_ARGS = TRAINING_PREFIX + TARGET_ARGS;
  public static final String PREDICTION_TARGET_ARGS = PREDICTION_PREFIX + TARGET_ARGS;

  public static final String COST_MODEL = CONFIG_PREFIX + ".costmodel";
  public static final String DEFAULT_COST_MODEL = DepthCostModel.class.getName();
  public static final String MEASURED_METHOD = COST_MODEL + ".measuredmethod";

  public static final String CONFIDENCE = PREDICTION_PREFIX + ".confidence";

  //List of options
  private static List<ConfigDescription> configs = new ArrayList<>();
  static {
    configs.add(create(TRAINING_TARGET_ARGS,
        "Range of input sizes over which training data will be collection. Use format from,to"));
    configs.add(create(PREDICTION_TARGET_ARGS,
        "Maximum input size for which " + Cogito.class.getName() +
            " will perform guided search by the trained machine learning model"));
    configs.add(create(CONFIDENCE, "Specifies a threshold ]0.0;1.0] on the posterior probability" +
        "computed for the prediction. If the prediction is less than this value, exploration " +
        "degenerates to exhaustive exploration at this decision. By default no filtering is made"));
    configs.add(create(COST_MODEL,
        "Cost model to use. Must be an implementation of " + CostModel.class.getCanonicalName() +
        ". Default is " + DEFAULT_COST_MODEL));
    configs.add(create(EVENT_OBSERVER,
        "A list of classes that implement " + AnalysisEventObserver.class.getCanonicalName() +
            ". Instances will created and will be notified about the certain event during the " +
            "analysis"));
    configs.add(create(OUTPUT_RESULTS, "Output data (max cost, statistics etc) from the " +
        "exhaustive exploration (phase 1) and the guided search (phase 2) into the directory " +
        "specified by this option."));
    configs.add(create(MEASURED_METHOD, "Simple method name e.g. com.example.method that " +
        "specifies where cost should start accumulating. Does not support overloaded methods. " +
        "Currently only works for " + DepthCostModel.class.getName()));
    configs.add(create(VISUALIZE, "Visualize max costs for all input sizes during guided search." +
        " Will also generate prediction models and plot them. Default is " + DEFAULT_VISUALIZE));
  }

  public static boolean valid(Config config) {
    return config.hasValue(TRAINING_TARGET_ARGS)
        && config.hasValue(PREDICTION_TARGET_ARGS);
  }

  public static void printConfigurations() {
    configs.forEach(System.out::println);
  }
}
