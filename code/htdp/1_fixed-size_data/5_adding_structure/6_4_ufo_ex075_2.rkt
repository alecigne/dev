;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.6 Fixed-Size Data / Adding Structure / Programming with Structures
;; ex. 75 - bonus: a complete world program

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

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

;; * Constants

(define SCENE
  (place-image/align (bitmap "files/sky.jpeg")
                     0 0 "left" "top"
                     (empty-scene 800 500)))

(define UFO (bitmap "files/ufo.png"))

;; * Functions

;; draw-ufo: Ufo -> Image
;; Draw a UFO at its coordinates.
(define (draw-ufo ufo)
  (place-image UFO
               (posn-x (ufo-position ufo))
               (posn-y (ufo-position ufo))
               SCENE))

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

;; draw-ufo

(check-expect
 (draw-ufo (make-ufo (make-posn 10 10)
                     (make-velocity 8 -3)))
 (place-image UFO 10 10 SCENE))

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

;; * Main

;; invasion: Ufo -> Ufo
;; Drive the program starting from a Ufo.
(define (invasion ufo)
  (big-bang ufo
    [to-draw draw-ufo]
    [on-tick ufo-move-1]))

(invasion (make-ufo (make-posn 0 10)
                    (make-velocity 8 3)))

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
