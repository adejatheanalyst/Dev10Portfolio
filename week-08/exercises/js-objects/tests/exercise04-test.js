QUnit.module('extractNames', () => {
  QUnit.test('returns `[ "a", "b", "c" ]` when passed the argument `[ { a: 1, name: "a", c: true }, { name: "b" }, { firstName: "first", name: "c" } ]`', assert => {
    assert.deepEqual(extractNames([ { a: 1, name: "a", c: true }, { name: "b" }, { firstName: "first", name: "c" } ]), [ "a", "b", "c" ]);
  });
  QUnit.test('returns `[ "a", "c" ]` when passed the argument `[ { a: 1, name: "a", c: true }, { firstName: "b" }, { firstName: "first", name: "c" } ]`', assert => {
    assert.deepEqual(extractNames([ { a: 1, name: "a", c: true }, { firstName: "b" }, { firstName: "first", name: "c" } ]), [ "a", "c" ]);
  });
  QUnit.test('returns `[]` when passed the argument `[]`', assert => {
    assert.deepEqual(extractNames([]), []);
  });
  QUnit.test('returns `[ "name" ]` when passed the argument `[ { firstName: "first", lastName: "last", name: "name" } ]`', assert => {
    assert.deepEqual(extractNames([ { firstName: "first", lastName: "last", name: "name" } ]), [ "name" ]);
  });
});
