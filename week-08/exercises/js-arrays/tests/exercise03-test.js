QUnit.module('mergeItemBetweenArrays', () => {
  QUnit.test('returns `[ 1, 2, "a", true ]` when passed the arguments `[ 1, 2 ], "a", [ true ]`', assert => {
    assert.deepEqual(mergeItemBetweenArrays([ 1, 2 ], "a", [ true ]), [ 1, 2, "a", true ]);
  });
  QUnit.test('returns `[ 87, 9 ]` when passed the arguments `[], 87, [ 9 ]`', assert => {
    assert.deepEqual(mergeItemBetweenArrays([], 87, [ 9 ]), [ 87, 9 ]);
  });
  QUnit.test('returns `[ 14, 15, 0 ]` when passed the arguments `[ 14, 15 ], 0, []`', assert => {
    assert.deepEqual(mergeItemBetweenArrays([ 14, 15 ], 0, []), [ 14, 15, 0 ]);
  });
  QUnit.test('returns `[ "red", "blue", "orange", "yellow" ]` when passed the arguments `[ "red", "blue" ], "orange", [ "yellow" ]`', assert => {
    assert.deepEqual(mergeItemBetweenArrays([ "red", "blue" ], "orange", [ "yellow" ]), [ "red", "blue", "orange", "yellow" ]);
  });
  QUnit.test('returns `[ "red", [ "orange" ], "yellow" ]` when passed the arguments `[ "red" ], [ "orange" ], [ "yellow" ]`', assert => {
    assert.deepEqual(mergeItemBetweenArrays([ "red" ], [ "orange" ], [ "yellow" ]), [ "red", [ "orange" ], "yellow" ]);
  });
});
