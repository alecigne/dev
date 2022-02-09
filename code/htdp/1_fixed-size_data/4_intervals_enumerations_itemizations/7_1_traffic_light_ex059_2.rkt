;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.7 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Finite State Worlds
;; Traffic Light - ex. 59 v2 (without images)

;; Finish the design of a world program that simulates the traffic
;; light finite state automaton.

;; In this version I don't really follow the design recipe for the `render'
;; function to avoid duplication. Being """clever""" by changing the data
;; definition's granularity to the bulb is not really recommended though.

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Data definitions

;; A TrafficLight is one of the following strings:
;; - "red"
;; - "yellow"
;; - "green"
;; Interpretation: a traffic light!

;; * Constants

;; Radius of the traffic-light bulbs
(define R 20)

;; Space between bulbs
(define SPACE (/ R 2))

;; The scene on which the traffic light will appear
(define SCENE (empty-scene (* R 8) (* R 3)))

;; * Functions

;; render: TrafficLight -> Image
;; Draw a traffic light in its adequate state.
(define (render traffic-light)
  (overlay
   (beside (draw-bulb "red" traffic-light)
           (square SPACE "solid" "white")
           (draw-bulb "yellow" traffic-light)
           (square SPACE "solid" "white")
           (draw-bulb "green" traffic-light))
   SCENE))

;; draw-bulb: Bulb TrafficLight -> Image
;; Create individual bulbs from a traffic light state.
(define (draw-bulb bulb traffic-light)
  (circle R
          (if (string=? traffic-light bulb)
              "solid"
              "outline")
          bulb))

;; next: TrafficLight -> TrafficLight
;; Yield the next state, given a traffic light.
(define (next traffic-light)
  (cond
    [(string=? traffic-light "red") "green"]
    [(string=? traffic-light "yellow") "red"]
    [(string=? traffic-light "green") "yellow"]))

;; * Main

;; main: TrafficLight -> TrafficLight
;; Drive the traffic light program from a traffic light in an initial
;; state.
(define (main traffic-light)
  (big-bang traffic-light
    [to-draw render]
    [on-tick next 3]))

;; * Tests

;; render

(check-expect (render "red")
              (overlay
               (beside (circle R "solid" "red")
                       (square SPACE "solid" "white")
                       (circle R "outline" "yellow")
                       (square SPACE "solid" "white")
                       (circle R "outline" "green"))
               SCENE))

(check-expect (render "yellow")
              (overlay
               (beside (circle R "outline" "red")
                       (square SPACE "solid" "white")
                       (circle R "solid" "yellow")
                       (square SPACE "solid" "white")
                       (circle R "outline" "green"))
               SCENE))

(check-expect (render "green")
              (overlay
               (beside (circle R "outline" "red")
                       (square SPACE "solid" "white")
                       (circle R "outline" "yellow")
                       (square SPACE "solid" "white")
                       (circle R "solid" "green"))
               SCENE))

;; next

(check-expect (next "red") "green")
(check-expect (next "yellow") "red")
(check-expect (next "green") "yellow")

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
