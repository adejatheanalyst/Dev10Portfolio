QUnit.module('getFirstVowel', () => {
  QUnit.test('returns "a" as the first vowel in the string "magnificent"', assert => {
    assert.strictEqual(getFirstVowel('magnificent'), 'a');
  });
  QUnit.test('returns "i" as the first vowel in the string "winsome"', assert => {
    assert.strictEqual(getFirstVowel('winsome'), 'i');
  });
  QUnit.test('returns an empty string ("") as the first vowel in the string "xxx"', assert => {
    assert.strictEqual(getFirstVowel('xxx'), '');
  });
  QUnit.test('returns an empty string ("") if no argument is passed', assert => {
    assert.strictEqual(getFirstVowel(), '');
  });
  QUnit.test('is case-insensitive (returns "A" as the first vowel in the string "mAgnificent")', assert => {
    assert.strictEqual(getFirstVowel('mAgnificent'), 'A');
  });
});
