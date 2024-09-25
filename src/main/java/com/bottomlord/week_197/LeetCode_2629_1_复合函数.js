var compose = function(functions) {
	return function(x) {
        if (functions.length == 0 ) {
            return x;
        }

        for (let i = functions.length - 1; i >= 0; i--) {
            x = functions[i](x);
        }

        return x;
    }
};