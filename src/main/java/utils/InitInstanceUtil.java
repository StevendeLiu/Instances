package utils;

import satibea.SATIBEA_Problem;

public class InitInstanceUtil {
    public static SATIBEA_Problem InitIstance() {
        String fm = "src/main/resources/init/uClinux.dimacs";
        String augment = fm + ".augment";
        String dead = fm + ".dead";
        String mandatory = fm + ".mandatory";
        String seed = fm + ".richseed";

        SATIBEA_Problem p = null;
        try {
            p = new SATIBEA_Problem(fm, augment, mandatory, dead, seed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
}
