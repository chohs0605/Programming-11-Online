public class Main {
    //Code your solution to problem number one here
    static int problemOne(String s){
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // Check if a char is a vowel. If so, increase the answer
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
                answer++;
        }

        return answer;
    }

    //Code you problem number two here
    static int problemTwo(String s){
        int answer = 0;

        // Check if 3 three substring starts with "bob"
        // we don't need to check the last 2 chars
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.substring(i).startsWith("bob"))
                answer++;
        }

        return answer;
    }

    //Code your solution to problem number 3 here
    static String problemThree(String s){
        int sIdx, maxsIdx, maxeIdx;
        int cnt, maxcnt;

        if (s.length() < 2)
            return s;

        sIdx = maxsIdx = maxeIdx = cnt = maxcnt = 0;
        cnt = maxcnt = 1;
        for (int i = 1; i < s.length(); i++) {
            // check if the current char is greater than the previous char, then incease cnt
            if (s.charAt(i - 1) <= s.charAt(i)) {
                cnt++;
            }
            else {
                // If the current char is not greater, check if cnt is over maxcnt. If so
                // reassign maxcnt to cnt and get start and end index of string
                if (cnt > maxcnt) {
                    maxcnt = cnt;
                    maxeIdx = i - 1;
                    maxsIdx = sIdx;
                }
                cnt = 1;
                sIdx = i;
            }
        }

        // check if a longest substring is in end of the string
        if (cnt > maxcnt) {
            maxcnt = cnt;
            maxeIdx = s.length() - 1;
            maxsIdx = sIdx;
        }

        // get substring from maxsIdx to maxeIdx
        s = s.substring(maxsIdx, maxeIdx + 1);

        return s;
    }
    public static void main(String[] args) {
        /*
        Set s to a string and run your method using s as the parameter
        Run your method in a println statement to determine what the output was
        Once you think you have it working try running the tests.
        The tests will put your method through several different Strings to test
        all possible cases.  If you have 100% success then there is no bugs in your methods.
         */
        String s;
    }
}
