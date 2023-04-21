var fibGenerator = function*() {
    let pre = 0;
    let cur = 1;
    yield pre;
    yield cur;
    while(true) {
        [cur, pre] = [pre + cur, cur];
        yield cur;
    };
};