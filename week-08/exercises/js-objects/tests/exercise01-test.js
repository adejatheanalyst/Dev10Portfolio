QUnit.module('makeObject', () => {
  QUnit.test('returns the object `{ title: "", firstName: "Merilee", lastName: "Sheldrick" }` when passed the arguments `"", "Merilee", "Sheldrick"`', assert => {
    assert.deepEqual(makeObject("", "Merilee", "Sheldrick"), { title: "", firstName: "Merilee", lastName: "Sheldrick" });
  });
  QUnit.test('returns the object `{ title: "Mr.", firstName: "Bale", lastName: "Packmann" }` when passed the arguments `"Mr.", "Bale", "Packmann"`', assert => {
    assert.deepEqual(makeObject("Mr.", "Bale", "Packmann"), { title: "Mr.", firstName: "Bale", lastName: "Packmann" });
  });
  QUnit.test('returns the object `{ title: "Dr.", firstName: "Letisha", lastName: "Pursey" }` when passed the arguments `"Dr.", "Letisha", "Pursey"`', assert => {
    assert.deepEqual(makeObject("Dr.", "Letisha", "Pursey"), { title: "Dr.", firstName: "Letisha", lastName: "Pursey" });
  });
});
