var reduce = function(nums, fn, init) {
    if (nums.length > 0) {
        for (let i = 0; i < nums.length; i++) {
            init = fn(init, nums[i]);
        }
    }

    return init;
};