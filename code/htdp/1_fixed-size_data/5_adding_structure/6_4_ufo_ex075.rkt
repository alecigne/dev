;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.6 Fixed-Size Data / Adding Structure / Programming with Structures
;; ex. 75

#lang htdp/bsl

;; * Data definitions

;; A Posn is a structure:
;; (make-posn Number Number)
;; Interpretation: a position in the X,Y plane.

(define-struct velocity [deltax deltay])
;; A Velocity is a structure:
;; (make-velocity Number Number)
;; Interpretation: a velocity, expressed in pixels per unit of time
;; along the horizontal direction and vertical axis.

(define-struct ufo [position velocity])
;; A Ufo is a structure:
;; (make-ufo Posn Velocity)
;; Interpretation: A Ufo at a given position and travelling at a given
;; velocity.

;; * Functions

;; ufo-move-1: Ufo -> Ufo
;; Compute the next position of a UFO.
(define (ufo-move-1 ufo)
  (make-ufo (next-position (ufo-position ufo)
                           (ufo-velocity ufo))
            (ufo-velocity ufo)))

;; next-position: Posn Velocity -> Posn
;; Compute the next position from a velocity.
(define (next-position position velocity)
  (make-posn (+ (posn-x position) (velocity-deltax velocity))
             (+ (posn-y position) (velocity-deltay velocity))))

;; * Tests

;; ufo-move-1

(check-expect
 (ufo-move-1 (make-ufo (make-posn 22 80)
                       (make-velocity 8 -3)))
 (make-ufo (make-posn 30 77)
           (make-velocity 8 -3)))

(check-expect
 (ufo-move-1 (make-ufo (make-posn 22 80)
                       (make-velocity -5 -3)))
 (make-ufo (make-posn 17 77) (make-velocity -5 -3)))

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
