import java.util.Iterator;

public class OHIterator implements Iterator<OHRequest>{
	OHRequest curr;

	public OHIterator(OHRequest queue){
		curr = queue;
	}

	public boolean isGood(String description){
		return description != null && description.length() > 5;
	}

	public boolean hasNext(){
		return curr != null;
	}

	public OHRequest next(){
		OHRequest result = curr;
		if(curr.next == null){
			curr = null;
			return result;
		}
		curr = curr.next;
		while(!isGood(curr.description)){
			curr = curr.next;
		}
		return result;
	}
}