QUnit.module('mergeAndRemoveDuplicates', () => {
  QUnit.test('returns `[ 1, 2, 3 ]` when passed the arguments `[ 1, 2 ], [ 2, 3 ]`', assert => {
    assert.deepEqual(mergeAndRemoveDuplicates([ 1, 2 ], [ 2, 3 ]), [ 1, 2, 3 ]);
  });
  QUnit.test('returns `[ 1, 2, 3 ]` when passed the arguments `[ 1, 1, 2 ], [ 2, 2, 3 ]`', assert => {
    assert.deepEqual(mergeAndRemoveDuplicates([ 1, 1, 2 ], [ 2, 2, 3 ]), [ 1, 2, 3 ]);
  });
  QUnit.test('returns `[ "one", 2, true, false, "two" ]` when passed the arguments `[ "one", 2, true ], [ true, false, "two" ]`', assert => {
    assert.deepEqual(mergeAndRemoveDuplicates([ "one", 2, true ], [ true, false, "two" ]), [ "one", 2, true, false, "two" ]);
  });
  QUnit.test('returns `[]` when passed the arguments `[], []`', assert => {
    assert.deepEqual(mergeAndRemoveDuplicates([], []), []);
  });
  QUnit.test('returns `[ "red", "blue" ]` when passed the arguments `[ "red" ], [ "blue" ]`', assert => {
    assert.deepEqual(mergeAndRemoveDuplicates([ "red" ], [ "blue" ]), [ "red", "blue" ]);
  });
  QUnit.test('returns `[ "red", "green", "blue", "yellow" ]` when passed the arguments `[ "red", "green", "blue" ], [ "blue", "yellow" ]`', assert => {
    assert.deepEqual(mergeAndRemoveDuplicates([ "red", "green", "blue" ], [ "blue", "yellow" ]), [ "red", "green", "blue", "yellow" ]);
  });
});
