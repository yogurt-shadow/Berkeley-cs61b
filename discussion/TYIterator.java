import java.util.Iterator;

public class TYIterator implements Iterator<OHRequest>{
	public OHRequest queue;

	public TYIterator(OHRequest queue){
		this.queue = queue;
	}

	public boolean hasNext(){
		return queue != null;
	}

	public boolean isGood(String description){
		return description != null && description.length() > 5;
	}

	public OHRequest next(){
		OHRequest result = queue;
		if(queue.next == null){
			queue = null;
			return result;
		}
		queue = queue.next;
		while(!isGood(queue.description) || queue.description.contains("thank u")){
			queue = queue.next;
		}
		return result;
	}
}