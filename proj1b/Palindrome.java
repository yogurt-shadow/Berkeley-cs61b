public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> result = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            result.addLast(word.charAt(i));
        }
        return result;
    }

    //helper function used for recursion
    private boolean isPalindrome_helper(Deque<Character> words) {
        Palindrome a = new Palindrome();
        if (words.size() < 2) {
            return true;
        }
        char remove_first = words.removeFirst();
        char remove_last = words.removeLast();
        if (remove_first != remove_last) {
            return false;
        }
        return a.isPalindrome_helper(words);
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
        return isPalindrome_helper(words);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque words = wordToDeque(word);
        while (words.size() >= 2) {
            char remove_last = (Character) words.removeLast();
            char remove_first = (Character) words.removeFirst();
            if (!cc.equalChars(remove_first, remove_last)) {
                return false;
            }
        }
        return true;
    }
}