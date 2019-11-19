public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        int len = word.length();
        Deque<Character> charDeque = new LinkedListDeque<Character>();
        for (int i = 0; i < len; i++) {
            charDeque.addLast(word.charAt(i));
        }
        return charDeque;
    }

    private boolean helpIsPalindrome(Deque<Character> queue) {
        if (queue.size() <= 1) {
            return true;
        }
        if (queue.removeFirst() == queue.removeLast()) {
            return helpIsPalindrome(queue);
        }
        return false;
    }

    private boolean helpIsPalindromeOffByOne(Deque<Character> queue, CharacterComparator cc) {
        if (queue.size() <= 1) {
            return true;
        }
        if (cc.equalChars(queue.removeFirst(), queue.removeLast())) {
            return helpIsPalindromeOffByOne(queue, cc);
        }
        return false;
    }

    public boolean isPalindrome(String word) {
       Deque<Character> charDeque = wordToDeque(word);
       return helpIsPalindrome(charDeque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> charDeque = wordToDeque(word);
        return helpIsPalindromeOffByOne(charDeque, cc);
    }
}
