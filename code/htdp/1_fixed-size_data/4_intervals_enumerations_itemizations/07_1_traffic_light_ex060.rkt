;; HtDP2e v8.3.0.5 - https://htdp.org/2021-11-15/Book/index.html
;; I.4.7 Fixed-Size Data / Intervals, Enumerations, and Itemizations / Finite State Worlds
;; Traffic Light - ex. 60

 ;; An alternative data representation for a traffic light program may use
 ;; numbers instead of strings:

    ; An N-TrafficLight is one of:
    ; – 0 interpretation the traffic light shows red
    ; – 1 interpretation the traffic light shows green
    ; – 2 interpretation the traffic light shows yellow

;; It greatly simplifies the definition of tl-next:

    ; N-TrafficLight -> N-TrafficLight
    ; yields the next state, given current state cs
    ; (define (tl-next-numeric cs) (modulo (+ cs 1) 3))

;; Reformulate tl-next’s tests for tl-next-numeric.

;; Does the tl-next function convey its intention more clearly than the
;; tl-next-numeric function? If so, why? If not, why not?

#lang htdp/bsl

(require 2htdp/image)
(require 2htdp/universe)

;; * Data definitions

;; A TrafficLight is one of the following numbers:
;; - 0
;; - 1
;; - 2
;; Interpretation: a traffic light in the red (0), green (1) or yellow (2)
;; state.

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
    [(= traffic-light 0) TL-RED]
    [(= traffic-light 1) TL-GREEN]
    [(= traffic-light 2) TL-YELLOW]))

;; next: TrafficLight -> TrafficLight
;; Yield the next state, given a traffic light.
(define (next traffic-light)
  (modulo (add1 traffic-light) 3))

;; This version conveys its intention less clearly than the other version. It
;; also "bind" the data definition to a numeric format. But I like it :sweat_smile:

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

(check-expect (render 0) TL-RED)
(check-expect (render 1) TL-GREEN)
(check-expect (render 2) TL-YELLOW)

;; next

(check-expect (next 0) 1)
(check-expect (next 1) 2)
(check-expect (next 2) 0)

;; Local Variables:
;; eval: (put 'big-bang 'racket-indent-function 'defun)
;; End:
