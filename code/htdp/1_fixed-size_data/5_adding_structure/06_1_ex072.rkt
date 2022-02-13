;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.6 Fixed-Size Data / Adding Structure / Programming with Structures
;; ex. 72

#lang htdp/bsl

(define-struct phone [area number])
;; A Phone is a structure:
;; (make-phone Number String)
;; Interpretation: a phone number, with its area.

(define-struct phone# [area switch num])
;; A Phone# is a structure:
;; (make-phone# Number Number Number)
;; Interpretation: a phone number, with its area number and switch
;; number.
;; area is the area code -> [100, 999]
;; switch is the switch  -> [100, 999]
;; number is the number  -> [0001, 999]
