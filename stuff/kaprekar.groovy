// Brute force test for Kaprekar "magic" result for every 4-digit-integer.
(1000..9999).each { n ->
    assert allDigitsEqual(n) || kaprekar(n) == 6174
}
 
// kaprekar(n) should be 6174 for every 4-digit-integer n except 1111, 2222, ... 9999
def kaprekar(n) {
    def k = maxPermutation(n) - minPermutation(n)
    k == n ? k : kaprekar(k)
}
 
// minPermutation(2011) == 112
def minPermutation(n) {
    n.toString().toList().sort().join().toInteger()
}
 
// maxPermutation(2011) == 2110
def maxPermutation(n) {
    n.toString().toList().sort().join().reverse().padRight(4, "0").toInteger()
}
 
def allDigitsEqual(n) {
    def s = n as String
    s.every{ it == s[0] }
}
