;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.3 Fixed-Size Data / Adding Structure / Programming with `posn'
;; ex. 63

#lang htdp/bsl

;; distance-to-0: Posn -> Number
;; Compute the distance of posn to the origin.
(define (distance-to-0 posn)
  (sqrt
   (+ (sqr (posn-x posn))
      (sqr (posn-y posn)))))

(check-expect (distance-to-0 (make-posn 0 5)) 5)
(check-expect (distance-to-0 (make-posn 7 0)) 7)
(check-expect (distance-to-0 (make-posn 3 4)) 5)
(check-expect (distance-to-0 (make-posn 8 6)) 10)
(check-expect (distance-to-0 (make-posn 5 12)) 13)

;; * Expression 1

(distance-to-0 (make-posn 3 4))

(sqrt
 (+ (sqr (posn-x (make-posn 3 4)))
    (sqr (posn-y (make-posn 3 4)))))

(sqrt
 (+ (sqr 3)
    (sqr (posn-y (make-posn 3 4)))))

(sqrt (+ 9 (sqr (posn-y (make-posn 3 4)))))
(sqrt (+ 9 (sqr 4)))
(sqrt (+ 9 16))
(sqrt 25)
5

;; * Expression 2

(distance-to-0 (make-posn 6 (* 2 4)))
(distance-to-0 (make-posn 6 8))
;; [...] same logic as above
10

;; * Expression 3

(+ (distance-to-0 (make-posn 12 5)) 10)

(+ (sqrt
    (+ (sqr (posn-x (make-posn 12 5)))
       (sqr (posn-y (make-posn 12 5)))))
   10)

(+ (sqrt
    (+ (sqr 12)
       (sqr (posn-y (make-posn 12 5)))))
   10)

(+ (sqrt
    (+ 144
       (sqr (posn-y (make-posn 12 5)))))
   10)

(+ (sqrt (+ 144 (sqr 5))) 10)
(+ (sqrt (+ 144 25)) 10)
(+ (sqrt 169) 10)
(+ 13 10)
23
