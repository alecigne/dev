;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.3 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Enumerations
;; Exercise 51

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Constants

(define SCENE-WIDTH 200)
(define SCENE-HEIGHT 100)
(define SCENE (empty-scene SCENE-WIDTH SCENE-HEIGHT))
(define STATE-DURATION 2)

;; * Data definitions

;; A TrafficLight is one of the following Strings:
;; - "red"
;; - "yellow"
;; - "green"
;; Interpretation: the three strings represent the three possible
;; states that a traffic light may assume.

;; * Functions

;; draw-scene: TrafficLight -> Image
;; Draw a scene with a traffic-light in the appropriate state.
(check-expect (draw-scene "red")
              (overlay/align "middle" "middle"
                             (draw-light "red") SCENE))
(define (draw-scene traffic-light)
  (overlay/align "middle" "middle"
                 (draw-light traffic-light)
                 SCENE))

;; draw-light: TrafficLight -> Image
;; Draw a traffic light in the appropriate state.
(check-expect (draw-light "red") (circle 20 "solid" "red"))
(check-expect (draw-light "yellow") (circle 20 "solid" "yellow"))
(check-expect (draw-light "green") (circle 20 "solid" "green"))
(define (draw-light traffic-light)
  (circle 20 "solid" traffic-light))

;; next-state: TrafficLight -> TrafficLight
;; Yields the next state given a current state.
(check-expect (next-state "red") "green")
(check-expect (next-state "green") "yellow")
(check-expect (next-state "yellow") "red")
(define (next-state traffic-light)
  (cond
    [(string=? "red" traffic-light) "green"]
    [(string=? "green" traffic-light) "yellow"]
    [(string=? "yellow" traffic-light) "red"]))

;; * Main

;; main: TrafficLight -> TrafficLight
;; Drive the program given an initial state for the traffic light.
(define (main initial-traffic-light)
  (big-bang initial-traffic-light
    [to-draw draw-scene]
    [on-tick next-state STATE-DURATION]))

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
