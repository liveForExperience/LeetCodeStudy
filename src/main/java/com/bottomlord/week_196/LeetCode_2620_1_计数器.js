var createCounter = function(n) {
    let num = n;
    return function() {
        const r = num;
        num = r + 1;
        return r;
    };
};