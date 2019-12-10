import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int [] res  = new int[2];  //创建一个res数组长度为2
        if(nums == null || nums.length <= 1) return res;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0;i < nums.length;i++){
            int num = nums[i]; //先拿到当前这个数
            int val = target - num; //另外一个数值是target-当前这个数
            if(map.containsKey(val)){ //如果找到这个数
                res[0] = i; //返回第一个索引
                res[1] = map.get(val); //返回第二个索引
                return res; //返回结果
            }else map.put(num,i); //没有找到就把当前数字放进hashmap中
        }
        return res;
    }
}
