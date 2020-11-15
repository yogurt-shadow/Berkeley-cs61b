import java.util.Iterator;

public class OfficeHoursQueue implements Iterable<OHRequest>{
	public OHRequest queue;

	public OfficeHoursQueue(OHRequest queue){
		this.queue = queue;
	}

	public Iterator<OHRequest> iterator(){
		return new TYIterator(queue);
	}

	public static void main(String[] args){
		OHRequest s1 = new OHRequest("Failing my test for get", "Pam", null);
		OHRequest s2 = new OHRequest("conceptual: what is dynamic method selection", "Michael", s1);
		OHRequest s3 = new OHRequest("git: what does checkout do.", "Jim", s2);
		OHRequest s4 = new OHRequest("help", "Dwight", s3);
		OHRequest s5 = new OHRequest("I really thank u very much", "Dwi22ght", s4);
		OHRequest s6 = new OHRequest("debugging get(i)", "Creed", s5);

		OfficeHoursQueue office_queue = new OfficeHoursQueue(s6);

		for(OHRequest student : office_queue){
			System.out.println(student.name);
		}
	}
}