public class Week2Question2 {
    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return "";
        }

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder revWord = new StringBuilder(words[i]);
            result.append(revWord.reverse().toString());

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        String output = result.toString();
        System.out.println(output);
        return output;
    }

    public static void main(String[] args) {
        reverseEachWord("hello club");
    }
}

