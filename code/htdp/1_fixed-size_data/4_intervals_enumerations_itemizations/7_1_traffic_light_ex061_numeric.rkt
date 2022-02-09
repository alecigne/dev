;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.7 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Finite State Worlds
;; Traffic Light - ex. 61 (numeric version)

;; Finish the design of a world program that simulates the traffic
;; light finite state automaton.

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Data definitions

;; Can't be changed from numbers to strings because of the `next' function.
(define RED 0)
(define GREEN 1)
(define YELLOW 2)

;; A TrafficLight is one of the following symbols:
;; - RED
;; - YELLOW
;; - GREEN
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
    [(equal? traffic-light RED) TL-RED]
    [(equal? traffic-light YELLOW) TL-YELLOW]
    [(equal? traffic-light GREEN) TL-GREEN]))

;; next: TrafficLight -> TrafficLight
;; Yield the next state, given a traffic light.
(define (next traffic-light)
  (modulo (add1 traffic-light) 3))

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

(check-expect (render RED) TL-RED)
(check-expect (render YELLOW) TL-YELLOW)
(check-expect (render GREEN) TL-GREEN)

;; next

(check-expect (next RED) GREEN)
(check-expect (next GREEN) YELLOW)
(check-expect (next YELLOW) RED)

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
