#!/usr/bin/env python3
# -*- coding: utf-8 -*-
# ^ That makes it possible to write unicode stuff ＬＩＫＥ　ＴＨＩＳ in code :)

def widen_chr(c):
    if 'A' <= c.upper() <= 'Z':
        return chr(ord('Ａ') + ord(c) - ord('A'))
    elif c == ' ':
        return '　'
    else:
        return c

def widen(s):
    return ''.join(widen_chr(c) for c in s)
    
from optparse import OptionParser

desc = "Widen some text. For example '%prog Hello World' will print "\
    "'Ｈｅｌｌｏ　Ｗｏｒｌｄ'"
parser = OptionParser(description=desc)
options, args = parser.parse_args()

wide_args = (widen(arg) for arg in args)
print(*wide_args, sep='　')
