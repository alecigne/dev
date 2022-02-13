;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.7 Fixed-Size Data / Adding Structure / The Universe of Data
;; ex. 78

#lang htdp/bsl

;; A LCL is one of:
;; - A 1String such as "a" <= x <= "z"
;; - #false
;; Interpretation: a lowercase letter, from A to Z, or no letter.

(define-struct 3letterword [lcl1 lcl2 lcl3])
;; A 3LetterWord is a structure:
;; (make-3letterword LCL LCL LCL)
;; Interpretation: a 3-letter word, consisting of 3 LCL.
