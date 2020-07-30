// 方法一
class Solution {
    private List<List<Integer>> res = new LinkedList<>();
    private Deque<Integer> stack = new ArrayDeque<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, 0, used);
        return res;
    }

    public void dfs(int[] nums, int depth, boolean[] used) {
        if(depth==nums.length) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for(int i=0; i<nums.length; ++i) {
            if(!used[i]) {
                // 对于nums中重复的元素，只有前一个用过时，后一个才能参与排列
                if(i>=1 && nums[i-1]==nums[i] && used[i-1]==false)
                    continue;
                stack.addLast(nums[i]);
                used[i]=true;
                dfs(nums, depth+1, used);
                stack.removeLast();
                used[i]=false;
            }
        }
    }
}

// 方法二
class Solution {
    private List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int pos) {
        if(pos==nums.length) {
            List<Integer> list = new LinkedList<>();
            for(int i:nums)
                list.add(i);
            res.add(list);
            return;
        }
        for(int i=pos; i<nums.length; ++i) {
            if(isRepeated(nums, pos, i))
                continue;
            swap(nums, i, pos);
            dfs(nums, pos+1);
            swap(nums, i, pos);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private boolean isRepeated(int[] nums, int start, int end) {
        for(int i=start; i<end; ++i)
            if(nums[i] == nums[end])
                return true;
        return false;
    }
}