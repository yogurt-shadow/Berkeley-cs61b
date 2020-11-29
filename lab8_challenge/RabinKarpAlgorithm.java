public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int length = pattern.length();
        if(length > input.length()){
        	return -1;
        }
        RollingString rs2 = new RollingString(pattern, length);
        for(int i = 0; i < input.length() - length; i++){
        	RollingString rs1 = new RollingString(input.substring(i, i + length), length);
        	if(rs1.hashCode() == rs2.hashCode()){
        		if(rs1.equals(pattern)){
        			return i;
        		}
        	}
        }
        return -1;
    }

}
