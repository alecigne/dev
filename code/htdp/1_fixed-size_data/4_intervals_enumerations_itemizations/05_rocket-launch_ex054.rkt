;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.5 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Itemizations
;; Rocket Launch - ex. 054

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Constants

(define HEIGHT 300)
(define WIDTH 100)
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
;; state, flying state. In flying state, a non-negative number
;; denotes the distance between the top of the canvas and the bottom
;; of the rocket.

;; * Functions

;; draw: RocketLaunch -> Image
;; Draw the rocket launch in an appropriate state.
(define (draw rocket-launch)
  (cond
    [(string? rocket-launch)
     (place-image ROCKET 10 (- HEIGHT CENTER) BACKGROUND)]
    [(<= -3 rocket-launch -1)
     (place-image (text (number->string rocket-launch) 20 "red")
                  10 (* 3/4 WIDTH)
                  (place-image ROCKET
                               10 (- HEIGHT CENTER)
                               BACKGROUND))]
    [(>= rocket-launch 0)
     (place-image ROCKET 10 (- rocket-launch CENTER) BACKGROUND)]))

;; ex054: it would be incorrect to use (string=? "resting" x) here
;; because it can only test for strings. It would be a contract
;; violation to test for with something else, and the data definition
;; includes non-strings.
;;
;; A completely accurate condition would be:
;; (and (string? rocket-launch)
;;      (string=? rocket-launch "resting"))

;; launch: KeyEvent RocketLaunch -> RocketLaunch
;; Switch the rocket launch to countdown mode.
(define (launch key-event rocket-launch)
  rocket-launch)

;; next-position: RocketLaunch -> RocketLaunch
;; Compute the next position of the rocket.
(define (next-position rocket-launch)
  rocket-launch)

;; * Tests

;; draw-rocket

(check-expect
 (draw "resting")
 (place-image ROCKET 10 (- HEIGHT CENTER) BACKGROUND))

(check-expect
 (draw -2)
 (place-image (text "-2" 20 "red")
              10 (* 3/4 WIDTH)
              (place-image ROCKET 10 (- HEIGHT CENTER) BACKGROUND)))

(check-expect
 (draw 53)
 (place-image ROCKET 10 (- 53 CENTER) BACKGROUND))

(check-expect
 (draw HEIGHT)
 (place-image ROCKET 10 (- HEIGHT CENTER) BACKGROUND))

(check-expect
 (draw 0)
 (place-image ROCKET 10 (- CENTER) BACKGROUND))

;; * Main

;; main: RocketLaunch -> RocketLaunch
;; Drive the rocket launch from an initial rocket state.
(define (main initial-state)
  (big-bang initial-state
    [to-draw draw]
    [on-key launch]
    [on-tick next-position]))

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
