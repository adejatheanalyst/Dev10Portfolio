QUnit.module('mergeObjects', () => {
  QUnit.test('returns the object `{ a: 1, b: "b", c: true }` when passed the arguments `{ a: 1, b: "b" }, { c: true }`', assert => {
    assert.deepEqual(mergeObjects({ a: 1, b: "b" }, { c: true }), { a: 1, b: "b", c: true });
  });
  QUnit.test('returns the object `{ a: "aye", b: "banana" }` when passed the arguments `{ a: 1 }, { a: "aye", b: "banana" }`', assert => {
    assert.deepEqual(mergeObjects({ a: 1 }, { a: "aye", b: "banana" }), { a: "aye", b: "banana" });
  });
  QUnit.test('returns the object `{ a: 1, b: "b", c: true, d: ["red", "orange"] }` when passed the arguments `{ a: 1, b: "b" }, { c: true, d: ["red", "orange"] }`', assert => {
    assert.deepEqual(mergeObjects({ a: 1, b: "b" }, { c: true, d: ["red", "orange"] }), { a: 1, b: "b", c: true, d: ["red", "orange"] });
  });
});
