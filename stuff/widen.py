#!/usr/bin/env python
# -*- coding: utf-8 -*-
# ^ That makes it possible to write unicode stuff ＬＩＫＥ　ＴＨＩＳ in code :)

def widen_chr(c):
    if 'A' <= c.upper() <= 'Z':
        return unichr(ord(u'Ａ') + ord(c) - ord('A'))
    elif c == ' ':
        return u'　'
    else:
        return c

def widen(s):
    return ''.join(widen_chr(c) for c in s)
    

import argparse

epilog = u'''
Usage example:
$ %(prog)s Hello World
Ｈｅｌｌｏ　Ｗｏｒｌｄ
'''.encode('utf-8') 
# ^ encode call needed as argparse does not support unicode strings.

parser = argparse.ArgumentParser(description='Widen some text.', epilog=epilog,
    formatter_class=argparse.RawDescriptionHelpFormatter)
parser.add_argument('strings', metavar='TEXT', type=str, 
    nargs='+', help='text to widen')

args = parser.parse_args()
wide_str = u'　'.join(widen(s) for s in args.strings)
print(wide_str)
