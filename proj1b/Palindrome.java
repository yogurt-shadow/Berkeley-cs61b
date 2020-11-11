public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> result = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    //helper function used for recursion
    private boolean isPalindromehelper(Deque<Character> words) {
        Palindrome a = new Palindrome();
        if (words.size() < 2) {
            return true;
        }
        char removefirst = words.removeFirst();
        char removelast = words.removeLast();
        if (removefirst != removelast) {
            return false;
        }
        return a.isPalindromehelper(words);
    }

    public boolean isPalindrome(String word) {
        /**Deque words = wordToDeque(word);
         while (words.size() >= 2) {
         char remove_first =(Character) words.removeFirst();
         char remove_last =(Character) words.removeLast();
         if (remove_first != remove_last) {
         return false;
         }
         }
         return true;*/
        Deque words = wordToDeque(word);
        return isPalindromehelper(words);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque words = wordToDeque(word);
        while (words.size() >= 2) {
            char removelast = (Character) words.removeLast();
            char removefirst = (Character) words.removeFirst();
            if (!cc.equalChars(removefirst, removelast)) {
                return false;
            }
        }
        return true;
    }
}
