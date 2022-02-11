;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.5.6 Fixed-Size Data / Adding Structure / Programming with Structures
;; ex. 74

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Data definitions

;; A Posn is a structure:
;; (make-posn Number Number)
;; Interpretation: a position in the X,Y plane.

;; * Constants

(define SCENE (empty-scene 500 500))
(define DOT (circle 5 "solid" "red"))

;; * Functions

;; scene+dot: Posn -> Image
;; Add a red spot to the scene at a given position.
(define (scene+dot position)
  (place-image DOT
               (posn-x position) (posn-y position)
               SCENE))

;; x+: Posn -> Posn
;; Increase the x-coordinate of the position by 3.
(define (x+ p)
  (posn-up-x p (+ (posn-x p) 3)))

;; posn-up-x: Posn Number -> Posn
;; Replace the x field of the Posn by the given number.
(define (posn-up-x posn number)
  (make-posn number (posn-y posn)))

;; reset-dot: Posn Number Number MouseEvt -> Posn
;; Return the position of the mouse click, or the current position.
(define (reset-dot posn click-x click-y mouse-event)
  (cond
    [(mouse=? mouse-event "button-down")
     (make-posn click-x click-y)]
    [else posn]))

;; * Tests

;; scene+dot

(check-expect (scene+dot (make-posn 10 20))
              (place-image DOT 10 20 SCENE))
(check-expect (scene+dot (make-posn 88 73))
              (place-image DOT 88 73 SCENE))

;; x+

(check-expect (x+ (make-posn 10 0)) (make-posn 13 0))

;; reset-dot

(check-expect
 (reset-dot (make-posn 10 20) 32 78 "button-down")
 (make-posn 32 78))

(check-expect
 (reset-dot (make-posn 10 20) 32 78 "button-up")
 (make-posn 10 20))

;; * Main

;; main: Posn -> Posn
;; Drive the program with an initial position for the red dot.
(define (main initial-position)
  (big-bang initial-position
    [on-tick x+]
    [on-mouse reset-dot]
    [to-draw scene+dot]))

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
