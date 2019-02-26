package Generation;

import satibea.SATIBEA_Problem;

public class Generator {

    SATIBEA_Problem satibea_problem;

    public Generator(SATIBEA_Problem satibea_problem) {
        this.satibea_problem = satibea_problem;
    }

    public SATIBEA_Problem getSatibea_problem() {
        return satibea_problem;
    }

    public void setSatibea_problem(SATIBEA_Problem satibea_problem) {
        this.satibea_problem = satibea_problem;
    }


}
