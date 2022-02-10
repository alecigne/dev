;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.3 Fixed-Size Data / Adding Structure / Programming with `posn'
;; ex. 64

#lang htdp/bsl

;; manhattan-distance: Posn -> Number
;; Measure the Manhattan distance of the given posn to the origin.
(define (manhattan-distance posn)
  (+ (posn-x posn)
     (posn-y posn)))

(check-expect (manhattan-distance (make-posn 0 0)) 0)
(check-expect (manhattan-distance (make-posn 0 5)) 5)
(check-expect (manhattan-distance (make-posn 2 0)) 2)
(check-expect (manhattan-distance (make-posn 4 3)) 7)
