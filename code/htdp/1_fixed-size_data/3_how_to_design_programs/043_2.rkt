;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.3.6 Fixed-Size Data / How to Design Programs / Designing World Programs
;; Exercise 43_2 (variation on ex. 43_1 with a sine wave)

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

;; Functions

;; distance: AnimationState -> Number
;; Compute the distance travelled by the object at a given point in
;; time according to a sine wave.
;; Expectations:
(check-expect (distance 0) 0)
(check-expect (distance 40) 400)
(check-expect (distance 80) 0)
(check-expect (distance 120) 400)
(define (distance animation-state)
  ;; TODO: write explanation / improve implementation
  ;; I am a 100% sure this is not what was asked :rofl:
  (inexact->exact
   (+ (* (- (/ WORLD-WIDTH 2))
         (cos (/ (* pi animation-state CAR-SPEED)
                 WORLD-WIDTH)))
      (/ WORLD-WIDTH 2))))

;; snapshot: AnimationState -> Image
;; Generate a snapshot of the scene at a point in time.
;; Expectations:
(check-expect (snapshot 0) (place-image CAR 0 CAR-Y BACKGROUND))
(check-expect (snapshot 40) (place-image CAR 400 CAR-Y BACKGROUND))
(define (snapshot animation-state)
  (place-image CAR
               ;; Since the car has a cyclic behavior, I use its
               ;; center as a reference here.
               (distance animation-state) CAR-Y
               BACKGROUND))

;; advance-time: AnimationState -> AnimationState
;; Advance the time by one unit.
(check-expect (advance-time 0) 1)
(check-expect (advance-time 50) 51)
(define (advance-time animation-state)
  (add1 animation-state))

;; invalid-state?: AnimationState -> Boolean
;; Check if the animation state is invalid.
(check-expect (invalid-state? 400) #false)
(check-expect (invalid-state? 20) #false)
(check-expect (invalid-state? -1) #true)
(define (invalid-state? animation-state)
  (= animation-state -1))

;; invalidate-state: AnimationState -> AnimationState
;; Return an invalid animation state to trigger the end the program.
(check-expect (invalidate-state 50 "a") -1)
(check-expect (invalidate-state 50 "c") -1)
(define (invalidate-state animation-state key-event)
  -1)

;; Main

;; main: AnimationState -> AnimationState
;; Drive the program from an initial point in time.
(define (main animation-state)
  (big-bang animation-state
    [to-draw snapshot]
    [on-key invalidate-state]
    [on-tick advance-time]
    [stop-when invalid-state?]))

(main 0)

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
