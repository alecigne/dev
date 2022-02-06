;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.3.6 Fixed-Size Data / How to Design Programs / Designing World Programs
;; Exercise 43-1

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; Physical constants

(define WORLD-WIDTH 400)
(define WORLD-HEIGHT 100)

(define WHEEL-RADIUS 10)
(define WHEEL-DISTANCE (* WHEEL-RADIUS 3))
(define WHEEL-COLOR "black")

(define CAR-LENGTH (* WHEEL-RADIUS 8))
(define CAR-HEIGHT (/ CAR-LENGTH 4))
(define CAR-COLOR "red")
(define CAR-SPEED 10)                    ; pixels per tick

;; Graphical constants

(define WHEEL (circle WHEEL-RADIUS "solid" WHEEL-COLOR))
(define SPACE (rectangle WHEEL-DISTANCE 0 "solid" "white"))
(define FRAME (beside WHEEL SPACE WHEEL))
(define CAR-BODY (rectangle CAR-LENGTH CAR-HEIGHT "solid" CAR-COLOR))

(define CAR
  (underlay/align/offset "middle" "bottom"
                         CAR-BODY
                         0 (/ CAR-HEIGHT 2)
                         FRAME))

(define CAR-Y (- WORLD-HEIGHT (/ (image-height CAR) 2)))

(define TREE
  (overlay/xy (circle 20 "solid" "green")
              16 18
              (rectangle 8 50 "solid" "brown")))

(define BACKGROUND (place-image
                    TREE
                    200 (- WORLD-HEIGHT (/ (image-height TREE) 2))
                    (empty-scene WORLD-WIDTH WORLD-HEIGHT)))

;; Data definitions

;; An AnimationState is a Number.
;; Interpretation: the number of clock ticks since the animation
;; started.

;; A Speed is a Number.
;; Interpretation: the speed at which the object travels.

;; Functions

;; distance: AnimationState -> Number
;; Compute the distance travelled by the object at a given point in
;; time.
;; Expectations:
(check-expect (distance 100) 1000)
(check-expect (distance 0) 0)
(define (distance animation-state)
  (* CAR-SPEED animation-state))

;; snapshot: AnimationState -> Image
;; Generate a snapshot of the scene at a point in time.
;; Expectations:
(check-expect (snapshot 0) (place-image CAR -40 CAR-Y BACKGROUND))
(check-expect (snapshot 50) (place-image CAR 460 CAR-Y BACKGROUND))
(check-expect (snapshot 200) (place-image CAR 1960 CAR-Y BACKGROUND))
(define (snapshot animation-state)
  (place-image CAR
               (- (distance animation-state) (/ CAR-LENGTH 2)) CAR-Y
               BACKGROUND))

;; advance-time: AnimationState -> AnimationState
;; Advance the time by one unit.
(check-expect (advance-time 0) 1)
(check-expect (advance-time 50) 51)
(define (advance-time animation-state)
  (add1 animation-state))

;; end?: AnimationState -> Boolean
;; Check if the maximum distance has been reached at this point in
;; time.
(check-expect (end? 100) #true)
(check-expect (end? 20) #false)
(define (end? animation-state)
  (>= (distance animation-state) WORLD-WIDTH))

;; Main

;; main: AnimationState -> AnimationState
;; Drive the program from an initial point in time.
(define (main animation-state)
  (big-bang animation-state
    [to-draw snapshot]
    [on-tick advance-time]
    [stop-when end?]))

(main 0)

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
