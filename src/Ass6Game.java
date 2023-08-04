import GameMain.GameFlow;
import GameMain.LevelInformation;
import GameMain.Levels.DirectHit;
import GameMain.Levels.FinalFour;
import GameMain.Levels.Green3;
import GameMain.Levels.WideEasy;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 * The class represent the game.
 */
public class Ass6Game {

    /**
     * the method initialize and run the game.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        List<LevelInformation> list1 = new ArrayList<>();
        List<LevelInformation> list2 = new ArrayList<>();
        GameFlow gameFlow = new GameFlow();
        //check if every argument is a legal and enters to the levels array.
        for (String s : args) {
            try {
                if (Integer.parseInt(s) >= 1 && Integer.parseInt(s) <= 4) {
                    //for the first level
                    if (Integer.parseInt(s) == 1) {
                        list2.add(new DirectHit());
                    }
                    //for the second level
                    if (Integer.parseInt(s) == 2) {
                        list2.add(new WideEasy());
                    }
                    //for the third level
                    if (Integer.parseInt(s) == 3) {
                        list2.add(new Green3());
                    }
                    //for the fourth level
                    if (Integer.parseInt(s) == 4) {
                        list2.add(new FinalFour());
                    }
                }
            } catch (Exception ignored) {
            }
        }
        // if we didn't get any level - run the level cy the usual order
        if (list2.isEmpty()) {
            list1.add(new DirectHit());
            list1.add(new WideEasy());
            list1.add(new Green3());
            list1.add(new FinalFour());
            gameFlow.runLevels(list1);
        } else {
            gameFlow.runLevels(list2);
        }
    }

}
