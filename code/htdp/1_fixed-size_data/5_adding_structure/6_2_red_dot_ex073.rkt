;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.6 Fixed-Size Data / Adding Structure / Programming with Structures
;; ex. 73

#lang htdp/bsl

;; * Data definitions

;; A Posn is a structure:
;; (make-posn Number Number)
;; Interpretation: a position in the X,Y plane.

;; * Functions

;; posn-up-x: Posn Number -> Posn
;; Replace the x field of the Posn by the given number.
(define (posn-up-x posn number)
  (make-posn number (posn-y posn)))
