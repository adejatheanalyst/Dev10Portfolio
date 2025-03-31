QUnit.module('padLeftStringArray', () => {
  QUnit.test('returns `[ "\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7one", "two hundred", "\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7fifty" ]` when passed the argument `[ "one", "two hundred", "fifty" ]`', assert => {
    assert.deepEqual(padLeftStringArray([ "one", "two hundred", "fifty" ]), [ "        one", "two hundred", "      fifty" ]);
  });
  QUnit.test('returns `[ "yes", "\u00B7no" ]` when passed the argument `[ "yes", "no" ]`', assert => {
    assert.deepEqual(padLeftStringArray([ "yes", "no" ]), [ "yes", " no" ]);
  });
  QUnit.test('returns `[ "\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7merciful", "\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7cold", "beyond reproach", "\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7brighter", "\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7\u00B7sad" ]` when passed the argument `[ "merciful", "cold", "beyond reproach", "brighter", "sad" ]`', assert => {
    assert.deepEqual(padLeftStringArray([ "merciful", "cold", "beyond reproach", "brighter", "sad" ]), [ "       merciful", "           cold", "beyond reproach", "       brighter", "            sad" ]);
  });
});
