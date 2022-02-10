;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.4 Fixed-Size Data / Adding Structure / Defining Structure Types
;; Bouncing balls - ex. 68

#lang htdp/bsl

(define-struct velocity [deltax deltay])
(define-struct ball [posn velocity])

(define ball1 (make-ball (make-posn 30 40) (make-velocity -10 5)))

;; Flat representation
(define-struct ballf [x y deltax deltay])
(define ball2 (make-ballf 30 40 -10 5))

(check-expect (equal? (posn-x (ball-posn ball1))
                      (ballf-x ball2))
              #true)

(check-expect (equal? (posn-y (ball-posn ball1))
                      (ballf-y ball2))
              #true)

(check-expect (equal? (velocity-deltax (ball-velocity ball1))
                      (ballf-deltax ball2))
              #true)

(check-expect (equal? (velocity-deltay (ball-velocity ball1))
                      (ballf-deltay ball2))
              #true)
