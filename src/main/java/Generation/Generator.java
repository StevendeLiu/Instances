package Generation;

import jmetal.core.Algorithm;
import jmetal.core.SolutionSet;
import satibea.SATIBEA_Problem;
import satibea.SATIBEA_SettingsIBEA;
import utils.InitInstanceUtil;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.min;

public class Generator {

    SATIBEA_Problem satibea_problem;

    final double mutationRate = 0.5;
    final String time = "30000";
    final int maxSize = 10;
    final int maxIterationSize = 20;

    Random r = new java.util.Random();

    Hashtable<Double, SATIBEA_Problem> hashtable = new Hashtable<Double, SATIBEA_Problem>();

    public Generator(SATIBEA_Problem satibea_problem) {
        this.satibea_problem = satibea_problem;
    }


    private SATIBEA_Problem mutation(SATIBEA_Problem satibea_problem) {
        double[] cost = satibea_problem.getCost();
        for (int i = 0; i < cost.length; i++) {
            double tmp = r.nextDouble();
            if (tmp < 1 && tmp >= 0.75) {
                cost[i] *= 1.05;
            } else if (tmp < 0.75 && tmp >= mutationRate) {
                cost[i] /= 1.05;
            }
        }
        satibea_problem.setCost(cost);

        int[] defects = satibea_problem.getDefects();
        for (int i = 0; i < defects.length; i++) {
            double tmp = r.nextDouble();
            if (tmp < 1 && tmp >= 0.75) {
                defects[i] *= 1.05;
            } else if (tmp < 0.75 && tmp >= mutationRate) {
                defects[i] /= 1.05;
            }
        }
        satibea_problem.setDefects(defects);

        boolean[] used_before = satibea_problem.getUsed_before();
        for (int i = 0; i < cost.length; i++) {
            double tmp = r.nextDouble();
            if (tmp >= mutationRate) {
                used_before[i] = !used_before[i];
            }
        }
        satibea_problem.setUsed_before(used_before);

        return satibea_problem;
    }

    private void init() {
        SATIBEA_Problem satibea_problem = InitInstanceUtil.InitIstance();
        satibea_problem = mutation(satibea_problem);
        hashtable.put(evaluation(satibea_problem), satibea_problem);
    }

    private Double evaluation(SATIBEA_Problem satibea_problem) {
        double value = 0;
        try {
            Algorithm a = new SATIBEA_SettingsIBEA(satibea_problem)
                    .configureSATIBEA(Integer.parseInt(time), satibea_problem.getFm(), ((SATIBEA_Problem) satibea_problem).getNumFeatures(),
                            ((SATIBEA_Problem) satibea_problem).getConstraints());
            SolutionSet pop = a.execute();
            double[][] results = pop.writeObjectivesToMatrix();
            value = results[0][4];
            for (int i = 1; i < pop.size(); i++) {
                value = min(value, results[0][4]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private SATIBEA_Problem crossover(SATIBEA_Problem satibea_problem1, SATIBEA_Problem satibea_problem2) {
        SATIBEA_Problem satibea_problem = satibea_problem1.deepClone();

        int randomCost = r.nextInt(satibea_problem.getCost().length);
        double cost[] = satibea_problem.getCost();
        double cost2[] = satibea_problem2.getCost();
        for (int i = randomCost; i < cost.length; i++) {
            cost[i] = cost2[i];
        }

        int randomDefects = r.nextInt(satibea_problem.getDefects().length);
        int defects[] = satibea_problem.getDefects();
        int defects2[] = satibea_problem2.getDefects();
        for (int i = randomDefects; i < defects.length; i++) {
            defects[i] = defects2[i];
        }

        int randomUsedBefore = r.nextInt(satibea_problem.getUsed_before().length);
        boolean used_before[] = satibea_problem.getUsed_before();
        boolean used_before2[] = satibea_problem2.getUsed_before();
        for (int i = randomCost; i < used_before.length; i++) {
            used_before[i] = used_before2[i];
        }

        return satibea_problem;
    }

    SATIBEA_Problem select() {
        int n = r.nextInt(hashtable.size());
        int i = 0;
        for (Map.Entry<Double, SATIBEA_Problem> entry : hashtable.entrySet()) {
            if (i == n) {
                return entry.getValue();
            } else {
                i++;
            }
        }
        System.out.println("select error!");
        return null;
    }

    void persist() {

    }

    public void execute() {
        init();
        for (int i = 0; i < maxIterationSize; i++) {
            SATIBEA_Problem satibea_problem1 = select();
            SATIBEA_Problem satibea_problem2 = select();
            SATIBEA_Problem satibea_problem = crossover(satibea_problem1, satibea_problem2);
            mutation(satibea_problem);
            hashtable.put(evaluation(satibea_problem), satibea_problem);
            if (hashtable.size() > maxSize) {
                Double wrostInstance = Collections.max(hashtable.keySet());
                hashtable.remove(wrostInstance);
            }
            persist();
        }
    }

}
