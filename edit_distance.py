# Naive recursive implementation.
def levenshtein(a, b):
	if len(a) == 0:
		return len(b)
	if len(b) == 0:
		return len(a)
	return min([levenshtein(a, b[:-1]) + 1,
			levenshtein(a[:-1], b) + 1,
			levenshtein(a[:-1], b[:-1]) + (a[-1] != b[-1])])

# Memoized recursive implementation.
def memoized_levenshtein(a, b):
	i, j = len(a), len(b)
	d = [[-1 for x in range(j + 1)] for y in range(i + 1)]
	return lookup_levenshtein(d, a, b)

def lookup_levenshtein(d, a, b):
	i, j = len(a), len(b)
	if d[i][j] != -1:
		return d[i][j]
	if i == 0:
		d[i][j] = j
	elif j == 0:
		d[i][j] = i
	else:
		substitution = a[-1] != b[-1]
		insert = lookup_levenshtein(d, a, b[:-1]) + 1
		delete = lookup_levenshtein(d, a[:-1], b) + 1
		substitute = lookup_levenshtein(d, a[:-1], b[:-1]) + substitution
		d[i][j] = min([insert, delete, substitute])
	return d[i][j]

a = "abcddd"
b = "abdce"
print a + ", " + b + ": Cost is " + str(memoized_levenshtein(a, b)) + "."