QUnit.module('areInOrder', () => {
  QUnit.test('returns `true` when passed the arguments `2, 4, 6, 7`', assert => {
    assert.true(areInOrder(2, 4, 6, 7));
  });
  QUnit.test('returns `false` when passed the arguments `4, 1, 1, 8`', assert => {
    assert.false(areInOrder(4, 1, 1, 8));
  });
  QUnit.test('returns `true` when passed the arguments `1, 1, 2, 2`', assert => {
    assert.true(areInOrder(1, 1, 2, 2));
  });
  QUnit.test('returns `true` when passed the arguments `-5, 0, 5, 10`', assert => {
    assert.true(areInOrder(-5, 0, 5, 10));
  });
  QUnit.test('returns `false` when passed the arguments `-5, 0, -5, 0`', assert => {
    assert.false(areInOrder(-5, 0, -5, 0));
  });
});
