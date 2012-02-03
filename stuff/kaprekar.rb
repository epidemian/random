def kaprekar? n
  all_same_digits? n or kaprekar n == 6174
end

def all_same_digits? n
  n.to_s.chars.to_a.uniq.size == 1
end

# kaprekar n should be 6174 for every 4-digit-integer n except 1111, 2222, ... 9999
def kaprekar n
  k = max_permutation(n) - min_permutation(n)
  if k == n then k else kaprekar k end
end

# min_permutation 2012 == 122
def min_permutation n
  n.to_s.chars.sort.join.to_i
end
 
# max_permutation 2012 == 2210
def max_permutation n
  min_permutation(n).to_s.reverse.ljust(4, '0').to_i
end

# Brute force test for Kaprekar "magic" result for every 4-digit-integer.
(1000..9999).each do |n|
  kaprekar? n or raise "#{n} does not satisfy Kaprekar property :(" 
end
