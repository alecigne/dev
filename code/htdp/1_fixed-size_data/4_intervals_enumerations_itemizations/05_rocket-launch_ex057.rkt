;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.5 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Itemizations
;; Rocket Launch - ex. 57

;; Recall that the word “height” forced us to choose one of two
;; possible interpretations. Now that you have solved the exercises in
;; this section, solve them again using the first interpretation of
;; the word. Compare and contrast the solutions.

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Constants

(define HEIGHT 300)
(define WIDTH 100)
(define SCENE-CENTER (/ WIDTH 2))
(define YDELTA 3)

(define BACKGROUND (empty-scene WIDTH HEIGHT))
(define ROCKET (bitmap "files/rocket.png"))
(define CENTER (/ (image-height ROCKET) 2))

;; * Data definitions

;; A RocketLaunch is one of:
;; – "resting"
;; - A Number between -3 and -1
;; – A NonNegativeNumber
;; Interpretation:
;; A rocket launch has three possible states: resting state, countdown
;; state, flying state. In flying state, a non-negative number denotes
;; the altitude - distance between the ground and the bottom of the
;; rocket.

;; * Functions

;; draw: RocketLaunch -> Image
;; Draw the rocket launch in an appropriate state.
(define (draw rocket-launch)
  (cond
    [(string? rocket-launch) (position-rocket 0)]
    [(<= -3 rocket-launch -1)
     (place-image (text (number->string rocket-launch) 20 "red")
                  10 (* 3/4 WIDTH)
                  (position-rocket 0))]
    [(>= rocket-launch 0) (position-rocket rocket-launch)]))

;; position-rocket: Number -> Image
;; Position the rocket on the scene.
(define (position-rocket position)
  (place-image ROCKET
               SCENE-CENTER
               (- HEIGHT position CENTER) BACKGROUND))

;; launch: KeyEvent RocketLaunch -> RocketLaunch
;; Switch the rocket launch to countdown mode.
(define (launch rocket-launch key-event)
  (cond
    [(string? rocket-launch)
     (if (string=? key-event " ") -3 rocket-launch)]
    [(<= -3 rocket-launch -1) rocket-launch]
    [(>= rocket-launch 0) rocket-launch]))

;; next-position: RocketLaunch -> RocketLaunch
;; ; Raises the rocket by YDELTA, if it is already moving.
(define (next-position rocket-launch)
  (cond
    [(string? rocket-launch) rocket-launch]
    [(<= -3 rocket-launch -1)
     (if (= rocket-launch -1)
         0
         (add1 rocket-launch))]
    [(>= rocket-launch 0) (+ rocket-launch YDELTA)]))

;; * Main

;; main: RocketLaunch -> RocketLaunch
;; Drive the rocket launch from an initial rocket state.
(define (main initial-state)
  (big-bang initial-state
    [to-draw draw]
    [on-key launch]
    [on-tick next-position 1]))

;; * Tests

;; draw-rocket

(check-expect
 (draw "resting")
 (place-image ROCKET SCENE-CENTER (- HEIGHT CENTER) BACKGROUND))

(check-expect
 (draw -2)
 (place-image (text "-2" 20 "red")
              10 (* 3/4 WIDTH)
              (place-image ROCKET
                           SCENE-CENTER (- HEIGHT CENTER)
                           BACKGROUND)))

(check-expect
 (draw 53)
 (place-image ROCKET SCENE-CENTER (- HEIGHT 53 CENTER) BACKGROUND))

(check-expect
 (draw HEIGHT)
 (place-image ROCKET SCENE-CENTER (- CENTER) BACKGROUND))

(check-expect
 (draw 0)
 (place-image ROCKET SCENE-CENTER (- HEIGHT CENTER) BACKGROUND))

;; position-rocket

(check-expect
 (position-rocket 50)
 (place-image ROCKET SCENE-CENTER (- HEIGHT 50 CENTER) BACKGROUND))

;; launch

(check-expect (launch "resting" " ") -3)
(check-expect (launch "resting" "a") "resting")
(check-expect (launch -3 " ") -3)
(check-expect (launch -1 " ") -1)
(check-expect (launch 33 " ") 33)
(check-expect (launch 33 "a") 33)

;; next-position

(check-expect (next-position "resting") "resting")
(check-expect (next-position -3) -2)
(check-expect (next-position -2) -1)
(check-expect (next-position -1) 0)
(check-expect (next-position 10) (+ 10 YDELTA))
(check-expect (next-position 22) (+ 22 YDELTA))

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
