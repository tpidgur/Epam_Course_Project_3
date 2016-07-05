package generated;

import java.util.Comparator;

import generated.Plane.Model;

/**
 * defines the comparison rule for models of plane according to their price
 * Created by Pidhurska Tetiana on 03.07.2016.
 */
public class PlanePriceComparator implements Comparator {
    /**
     * comparison based on price
     *
     * @param o1 - first Model to be compared with
     * @param o2 -second Model to be compared with
     * @return a negative integer, zero, or a positive integer as the
     * first argument is more than, equal to, or less than the
     * second.
     */
    @Override
    public int compare(Object o1, Object o2) {
        Model model1 = (Model) o1;
        Model model2 = (Model) o2;
        if (model1.getPrice().getValue() > model2.getPrice().getValue()) return -1;
        else if (model1.getPrice().getValue() < model2.getPrice().getValue()) return 1;
        else return 0;
    }
}
