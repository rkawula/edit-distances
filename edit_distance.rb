# Naive recursive levenshtein edit distance.
def levenshtein(a, b)
  return b.size if a.size == 0
  return a.size if b.size == 0
  return [(levenshtein a, b.chop) + 1,
          (levenshtein a.chop, b) + 1,
          (levenshtein a.chop, b.chop) + (a[-1] == b[-1] ? 0 : 1)].min
end

def memoized_levenshtein(a, b)
  d = []
  (a.size + 1).times do |i|
    d[i] = []
  end
  lookup_levenshtein a, b, d
end

private

def lookup_levenshtein(a, b, d)
  i = a.size
  j = b.size
  return d[i][j] unless d[i][j].nil?
  if i == 0 || j == 0
    d[i][j] = [i, j].min
  else
    actions = [(lookup_levenshtein a, b.chop, d) + 1,
              (lookup_levenshtein a.chop, b, d) + 1,
              (lookup_levenshtein a.chop, b.chop, d) + (a[-1] == b[-1] ? 0 : 1)]
    d[i][j] = actions.min
  end
  return d[i][j]
end