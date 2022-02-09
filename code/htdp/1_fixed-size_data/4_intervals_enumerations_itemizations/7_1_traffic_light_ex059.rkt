;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.7 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Finite State Worlds
;; Traffic Light - ex. 59

;; Finish the design of a world program that simulates the traffic
;; light finite state automaton.

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

;; The traffic light image
(define TL-RED (bitmap "files/tl-red.png"))
(define TL-YELLOW (bitmap "files/tl-yellow.png"))
(define TL-GREEN (bitmap "files/tl-green.png"))

;; * Functions

;; render: TrafficLight -> Image
;; Draw a traffic light in its adequate state.
(define (render traffic-light)
  (cond
    [(string=? traffic-light "red") TL-RED]
    [(string=? traffic-light "yellow") TL-YELLOW]
    [(string=? traffic-light "green") TL-GREEN]))

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

(check-expect (render "red") TL-RED)
(check-expect (render "yellow") TL-YELLOW)
(check-expect (render "green") TL-GREEN)

;; next

(check-expect (next "red") "green")
(check-expect (next "yellow") "red")
(check-expect (next "green") "yellow")

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
