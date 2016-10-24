public class Solution{
	/*
	1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
	2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
	3, If p.charAt(j) == '*': 
   here are two sub conditions:
               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
               2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
	*/

	public boolean isMatching(String s, String p){
		if (s == null || p == null) return false;
    	
    	boolean[][] dp = new boolean[s.length()+1][p.length()+1];
    	dp[0][0] = true;
    	for (int i = 0; i < p.length(); i++) {
        	if (p.charAt(i) == '*' && dp[0][i-1]){
        		dp[0][i+1] = true;
        	}
    	}
    	for (int i = 0 ; i < s.length(); i++) {
        	for (int j = 0; j < p.length(); j++) {
            	if (p.charAt(j) == '.') {
                	dp[i+1][j+1] = dp[i][j];
            	}
            	if (p.charAt(j) == s.charAt(i)) {
                	dp[i+1][j+1] = dp[i][j];
            	}
            	if (p.charAt(j) == '*') {
                	if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                    	dp[i+1][j+1] = dp[i+1][j-1];
                	} else {
                    	dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                	}
            	}
        	}
    	}
    	return dp[s.length()][p.length()];
	}

	public List<String> fullJustify(String[] words, int L) {
        
        List<String> list = new LinkedList<String>();
        for (int i = 0, w; i < words.length; i = w) {
            int len = -1;
            for (w = i; w < words.length && len + words[w].length() + 1 <= L; w++) {
                len += words[w].length() + 1;
            }
            
            StringBuilder strBuilder = new StringBuilder(words[i]);
            int space = 1, extra = 0;
            if (w != i + 1 && w != words.length) {
                space = (L - len) / (w - i - 1) + 1;
                extra = (L - len) % (w - i - 1);
            }
            for (int j = i + 1; j < w; j++) {
                for (int s = space; s > 0; s--) strBuilder.append(' ');
                if (extra-- > 0) strBuilder.append(' ');
                strBuilder.append(words[j]);
            }
            int strLen = L - strBuilder.length();
            while (strLen-- > 0) strBuilder.append(' ');
            list.add(strBuilder.toString());
        }
        
        return list;
    }

    public int reverseBits(int n) {
        for (int i = 0; i < 16; i++) {
		    n = swapBits(n, i, 32 - i - 1);
	    }
	    return n;
    }
 
    public int swapBits(int n, int i, int j) {
	    int a = (n >> i) & 1;
	    int b = (n >> j) & 1;
 
	    if ((a ^ b) != 0) {
		    return n ^= (1 << i) | (1 << j);
	    }
	    return n;
    }

    public int rob(int[] nums) {
        //f(i) = Max{f(i- 1), f(i - 2) + num[i]}
        int len = nums.length;
        
        
        int prev = 0, next = 0, ans = 0;
        for(int i = 0; i < len; i++){
            ans = Math.max(next, prev + nums[i]);
            prev = next;
            next = ans;
        }
        return ans;
        
        /*int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < len; i++) dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        return dp[len - 1];*/
    }

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        String result="";
        if(words==null || words.length==0) return result;
        for(String s: words){
            for(char c: s.toCharArray()){
                degree.put(c,0);
            }
        }
        for(int i = 0; i < words.length-1; i++){
            String cur = words[i], next = words[i+1];
            int length = Math.min(cur.length(), next.length());
            for(int j=0; j<length; j++){
                char c1=cur.charAt(j), c2=next.charAt(j);
                if(c1!=c2){
                    Set<Character> set=new HashSet<Character>();
                    if(map.containsKey(c1)) set=map.get(c1);
                    if(!set.contains(c2)){
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2)+1);
                    }
                    break;
                }
            }
        }
    
        Queue<Character> q=new LinkedList<Character>();
        for(char c: degree.keySet()){
            if(degree.get(c)==0) q.add(c);
        }
        while(!q.isEmpty()){
            char c=q.remove();
            result += c;
            if(map.containsKey(c)){
                for(char c2: map.get(c)){
                    degree.put(c2,degree.get(c2)-1);
                    if(degree.get(c2)==0) q.add(c2);
                }
            }
        }
        if(result.length()!=degree.size()) return "";
        return result;
    }

    public boolean containsDuplicate(int[] nums) {
    	Set<Integer> set = new HashSet<>();
    	for(int i : nums){
    		if(set.contains(i)) return true;
    		else set.add(i);
    	}
    	return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> tree = new TreeSet<>();
		for(int i = 0; i < nums.length; i++){
			Integer floor = tree.floor(nums[i] + t);
			Integer ceiling = tree.ceiling(nums[i] - t);
			if((floor != null && floor >= nums[i]) || (ceiling != null && ceiling <= nums[i])) return true;
			tree.add(nums[i]);
			if(i >= k) tree.remove(nums[i - k]);
		}
		
		return false;
    }

    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int result = 0;
        for(int i = 1; i <= matrix.length; i++){
            for(int j = 1; j <= matrix[0].length; j++){
                if(matrix[i-1][j-1] == '1'){
                    int min = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]); 
                    dp[i][j] = (int)Math.pow((int)Math.sqrt(min) + 1, 2);
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result;
    }

    public int calculate(String s) {
        int sum = 0, d = 0;
        int flag = 1;
        char op = '+';
        
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ' ') continue;
            if(Character.isDigit(c)){
                d = d * 10 + (c - '0');
            }
            else{
                if(op == '+') stack.push(d);
                else if(op == '-') stack.push(-d);
                else if(op == '*' || op == '/'){
                    int pop = stack.pop();
                    int push = op == '*' ? pop * d : pop / d;
                    stack.push(push);
                }
                op = c;
                d = 0;
            }
        }
        
        if(op == '+') stack.push(d);
                else if(op == '-') stack.push(-d);
                else if(op == '*' || op == '/'){
                    int pop = stack.pop();
                    int push = op == '*' ? pop * d : pop / d;
                    stack.push(push);
                }
        
        for(int i : stack) sum += i;
        return sum;
    }
}

public class Vector2D implements Iterator<Integer> {

    private Iterator<List<Integer>> i;
    private Iterator<Integer> j;
    
    public Vector2D(List<List<Integer>> vec2d) {
        i = vec2d.iterator();
    }

    @Override
    public Integer next() {
        hasNext();
        return j.next();
    }

    @Override
    public boolean hasNext() {
         while ((j == null || !j.hasNext()) && i.hasNext())
            j = i.next().iterator();
        return j != null && j.hasNext();
    }
}
