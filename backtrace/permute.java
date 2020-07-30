// 方法一
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> list = new LinkedList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0, used);
        return res;
    }
    
    private void dfs(int[] nums, int pos, boolean[] used) {
        if(pos==nums.length) {
            res.add(new LinkedList<>(list));
            return;
        }
        for(int i=0; i<nums.length; ++i) {
            if(!used[i]) {
                used[i]=true;
                list.addLast(nums[i]);
                dfs(nums, pos+1, used);
                used[i]=false;
                list.removeLast();
            }
        }
    }
}


// 方法二
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        dfs(nums, 0);
        return res;
    }
    
    private void dfs(int[] nums, int pos) {
        if(pos==nums.length-1) {
            List<Integer> list = new LinkedList<>();
            for(int i:nums)
                list.add(i);
            res.add(list);
            return;
        }
        for(int i=pos; i<nums.length; ++i) {
            swap(nums, pos, i);
            dfs(nums, pos+1);
            swap(nums, pos, i);
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}