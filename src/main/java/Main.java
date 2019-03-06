import Generation.Generator;
import satibea.SATIBEA_Problem;
import utils.InitInstanceUtil;

public class Main {

    public static void main(String[] args) throws Exception {
        String str[] = {"src/main/resources/init/uClinux.dimacs", "30000"};
        //SATIBEA_Main.main(str);
        SATIBEA_Problem satibea_problem = InitInstanceUtil.InitIstance();
        Generator generator = new Generator(satibea_problem);
        generator.execute();
    }
}
