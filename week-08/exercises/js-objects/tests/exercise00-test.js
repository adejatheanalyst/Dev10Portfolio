QUnit.module('makeObject', () => {
  QUnit.test('returns the object `{ name: "Timi", isGrumpy: false, favoriteColors: [ "orange", "lilac" ] }`', assert => {
    assert.deepEqual(makeObject(), {
      name: "Timi",
      isGrumpy: false,
      favoriteColors: ["orange", "lilac"]
    });
  });
});
