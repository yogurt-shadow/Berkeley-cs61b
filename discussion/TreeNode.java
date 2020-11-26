public class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;

	/**  this is a wrong answer */
	public static boolean buggyIsBST(TreeNode T){
	if(T == null){
		return true;
	}
	else if(T.left != null && T.left.val > T.val){
		return false;
	}
	else if(T.right != null && T.right.val < T.val){
		return false;
	}
	return buggyIsBST(T.left) && buggyIsBST(T.right);
}

/**  the right answer here */
public static boolean isBST(TreeNode T){
	return isBSThelper(T, Integer.MIN_VALUE, Integer.MAX_VALUE);
}

private static boolean isBSThelper(TreeNode T, int min, int max){
	if(T == null){
		return true;
	}
	if(T.val <= min || T.val >= max){
		return false;
	}
	else{
		return isBSThelper(T.left, min, T.val) && isBSThelper(T.right, T.val, max);
	}
}

public static void main(String[] args){
	TreeNode t = new TreeNode();
	t.val = 10;
	t.left = new TreeNode();
	t.left.val = 8;
	t.right = new TreeNode();
	t.right.val = 20;
	t.left.left = new TreeNode();
	t.left.left.val = 4;
	t.left.right = new TreeNode();
	t.left.right.val = 100;
	/**
	       10
	      /  \
	     8   20
  	    / \  
  	   4  100
  	   */
  	   System.out.println(buggyIsBST(t));
  	   System.out.println(isBST(t));
}
}