QUnit.module('makeFunction', () => {
  QUnit.test('returns a function', assert => {
    assert.strictEqual(typeof makeFunction(), 'function');
  });
});
