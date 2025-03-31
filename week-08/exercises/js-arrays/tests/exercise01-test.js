QUnit.module('removeEveryOther', () => {
  QUnit.test('returns `[ 1, 3, 5 ]` when passed the argument `[ 1, 2, 3, 4, 5 ]`', assert => {
    assert.deepEqual(removeEveryOther([ 1, 2, 3, 4, 5 ]), [ 1, 3, 5 ]);
  });
  QUnit.test('returns `[ "a", "c" ]` when passed the argument `[ "a", "b", "c" ]`', assert => {
    assert.deepEqual(removeEveryOther([ "a", "b", "c" ]), [ "a", "c" ]);
  });
  QUnit.test('returns `[]` when passed the argument `[]`', assert => {
    assert.deepEqual(removeEveryOther([]), []);
  });
  QUnit.test('returns `[ 22 ]` when passed the argument `[ 22 ]`', assert => {
    assert.deepEqual(removeEveryOther([ 22 ]), [ 22 ]);
  });
  QUnit.test('returns `[ 22, 23, 24, 25 ]` when passed the argument `[ 22, 0, 23, 0, 24, 0, 25 ]`', assert => {
    assert.deepEqual(removeEveryOther([ 22, 0, 23, 0, 24, 0, 25 ]), [ 22, 23, 24, 25 ]);
  });
  QUnit.test('returns `[ 0, 0, 0, 0 ]` when passed the argument `[ 0, 22, 0, 23, 0, 24, 0, 25 ]`', assert => {
    assert.deepEqual(removeEveryOther([ 0, 22, 0, 23, 0, 24, 0, 25 ]), [ 0, 0, 0, 0 ]);
  });
});
