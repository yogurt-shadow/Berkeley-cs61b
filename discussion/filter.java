public class filter implements Predicate<String>{

	public boolean test(String x){
		return x.length() > 3;
	}
}