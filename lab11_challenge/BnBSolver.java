import java.util.ArrayList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    private List<Bear> bears;
    private List<Bed> beds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        // TODO: Fix me.
       Pair<List<Bear>, List<Bed>> solved = quicksort(bears, beds);
       this.bears = solved.first();
       this.beds = solved.second(); 
    }


    /**
     * @param bears unmatched bears
     * @param beds unmatched beds
     * @return Pair<Bear, Bed> matched bear and bed
     * core idea: use bear's pivot to quicksort beds and use bed's pivot to quicksort bears
     */
    private Pair<List<Bear>, List<Bed>> quicksort(List<Bear> bears, List<Bed> beds){
        if(bears.size() <= 1 && beds.size() == bears.size()){
            return new Pair(bears, beds);
        }


        Bear pivot_bear = bears.get(0);
        List<Bed> less_bed = new ArrayList<>();
        List<Bed> equal_bed = new ArrayList<>();
        List<Bed> greater_bed = new ArrayList<>();
        quicksort_bed(beds, pivot_bear, less_bed, equal_bed, greater_bed);

        Bed pivot_bed = equal_bed.get(0);
        List<Bear> less_bear = new ArrayList<>();
        List<Bear> equal_bear = new ArrayList<>();
        List<Bear> greater_bear = new ArrayList<>();
        quicksort_bear(bears, pivot_bed, less_bear, equal_bear, greater_bear);

   
       Pair<List<Bear>, List<Bed>> less_pair = quicksort(less_bear, less_bed);
       Pair<List<Bear>, List<Bed>> greater_pair = quicksort(greater_bear, greater_bed);
       List<Bear> bear_result = connect(less_pair.first(), connect(equal_bear, greater_pair.first()));
       List<Bed> bed_result = connect(less_pair.second(), connect(equal_bed, greater_pair.second()));
       return new Pair(bear_result, bed_result);

    }

    private void quicksort_bed(List<Bed> beds, Bear pivot, List<Bed> less, List<Bed> equal, List<Bed> greater){
        if(beds.size() == 0){
            return;
        }
        for(Bed bed: beds){
            if(bed.compareTo(pivot) > 0){
                greater.add(bed);
            }
            else if(bed.compareTo(pivot) < 0){
                less.add(bed);
            }
            else{
                equal.add(bed);
            }
        }
    }

    private void quicksort_bear(List<Bear> bears, Bed pivot, List<Bear> less, List<Bear> equal, List<Bear> greater){
        if(bears.size() == 0){
            return;
        }
        for(Bear bear: bears){
            if(bear.compareTo(pivot) > 0){
                greater.add(bear);
            }
            else if(bear.compareTo(pivot) < 0){
                less.add(bear);
            }
            else{
                equal.add(bear);
            }
        }
    }

    private <Item> List<Item> connect(List<Item> list1, List<Item> list2){
        for(Item item: list2){
            list1.add(item);
        }
        return list1;
    } 


    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.
        return bears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.
        return beds;
    }
}
