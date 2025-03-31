QUnit.module('areInOrder', () => {
  QUnit.test('returns `true` when passed the arguments `2, 4, 6`', assert => {
    assert.true(areInOrder(2, 4, 6));
  });
  QUnit.test('returns `false` when passed the arguments `4, 1, 8`', assert => {
    assert.false(areInOrder(4, 1, 8));
  });
  QUnit.test('returns `true` when passed the arguments `1, 1, 2`', assert => {
    assert.true(areInOrder(1, 1, 2));
  });
  QUnit.test('returns `true` when passed the arguments `-5, 0, 5`', assert => {
    assert.true(areInOrder(-5, 0, 5));
  });
  QUnit.test('returns `false` when passed the arguments `-5, 0, -5`', assert => {
    assert.false(areInOrder(-5, 0, -5));
  });
});

QUnit.module('areContiguous', () => {
  QUnit.test('returns `true` when passed the arguments `1, 2, 3`', assert => {
    assert.true(areContiguous(1, 2, 3));
  });
  QUnit.test('returns `false` when passed the arguments `1, 1, 2`', assert => {
    assert.false(areContiguous(1, 1, 2));
  });
  QUnit.test('returns `true` when passed the arguments `1, 2, 1`', assert => {
    assert.true(areContiguous(1, 2, 1));
  });
  QUnit.test('returns `false` when passed the arguments `1, 5, 7`', assert => {
    assert.false(areContiguous(1, 5, 7));
  });
  QUnit.test('returns `true` when passed the arguments `0, 1, 2`', assert => {
    assert.true(areContiguous(0, 1, 2));
  });
  QUnit.test('returns `true` when passed the arguments `7, 6, 5`', assert => {
    assert.true(areContiguous(7, 6, 5));
  });
  QUnit.test('returns `false` when passed the arguments `7, 5, 6`', assert => {
    assert.false(areContiguous(7, 5, 6));
  });
  QUnit.test('returns `true` when passed the arguments `1, 0, 1`', assert => {
    assert.true(areContiguous(1, 0, 1));
  });
});

QUnit.module('isAscendingContiguous', () => {
  QUnit.test('returns `true` when passed the arguments `3, 4, 5`', assert => {
    assert.true(isAscendingContiguous(3, 4, 5));
  });
  QUnit.test('returns `false` when passed the arguments `-10, 4, 100`', assert => {
    assert.false(isAscendingContiguous(-10, 4, 100));
  });
  QUnit.test('returns `false` when passed the arguments `2, 1, 2`', assert => {
    assert.false(isAscendingContiguous(2, 1, 2));
  });
  QUnit.test('returns `false` when passed the arguments `5, 4, 3`', assert => {
    assert.false(isAscendingContiguous(5, 4, 3));
  });
});
